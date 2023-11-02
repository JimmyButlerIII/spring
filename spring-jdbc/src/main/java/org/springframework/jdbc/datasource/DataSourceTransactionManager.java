/*
 * Copyright 2002-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.jdbc.datasource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.lang.Nullable;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;
import org.springframework.transaction.support.ResourceTransactionManager;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionSynchronizationUtils;
import org.springframework.util.Assert;

/**
 * {@link org.springframework.transaction.PlatformTransactionManager}
 * implementation for a single JDBC {@link javax.sql.DataSource}. This class is
 * capable of working in any environment with any JDBC driver, as long as the setup
 * uses a {@code javax.sql.DataSource} as its {@code Connection} factory mechanism.
 * Binds a JDBC Connection from the specified DataSource to the current thread,
 * potentially allowing for one thread-bound Connection per DataSource.
 *
 * <p><b>Note: The DataSource that this transaction manager operates on needs
 * to return independent Connections.</b> The Connections may come from a pool
 * (the typical case), but the DataSource must not return thread-scoped /
 * request-scoped Connections or the like. This transaction manager will
 * associate Connections with thread-bound transactions itself, according
 * to the specified propagation behavior. It assumes that a separate,
 * independent Connection can be obtained even during an ongoing transaction.
 *
 * <p>Application code is required to retrieve the JDBC Connection via
 * {@link DataSourceUtils#getConnection(DataSource)} instead of a standard
 * Java EE-style {@link DataSource#getConnection()} call. Spring classes such as
 * {@link org.springframework.jdbc.core.JdbcTemplate} use this strategy implicitly.
 * If not used in combination with this transaction manager, the
 * {@link DataSourceUtils} lookup strategy behaves exactly like the native
 * DataSource lookup; it can thus be used in a portable fashion.
 *
 * <p>Alternatively, you can allow application code to work with the standard
 * Java EE-style lookup pattern {@link DataSource#getConnection()}, for example for
 * legacy code that is not aware of Spring at all. In that case, define a
 * {@link TransactionAwareDataSourceProxy} for your target DataSource, and pass
 * that proxy DataSource to your DAOs, which will automatically participate in
 * Spring-managed transactions when accessing it.
 *
 * <p>Supports custom isolation levels, and timeouts which get applied as
 * appropriate JDBC statement timeouts. To support the latter, application code
 * must either use {@link org.springframework.jdbc.core.JdbcTemplate}, call
 * {@link DataSourceUtils#applyTransactionTimeout} for each created JDBC Statement,
 * or go through a {@link TransactionAwareDataSourceProxy} which will create
 * timeout-aware JDBC Connections and Statements automatically.
 *
 * <p>Consider defining a {@link LazyConnectionDataSourceProxy} for your target
 * DataSource, pointing both this transaction manager and your DAOs to it.
 * This will lead to optimized handling of "empty" transactions, i.e. of transactions
 * without any JDBC statements executed. A LazyConnectionDataSourceProxy will not fetch
 * an actual JDBC Connection from the target DataSource until a Statement gets executed,
 * lazily applying the specified transaction settings to the target Connection.
 *
 * <p>This transaction manager supports nested transactions via the JDBC 3.0
 * {@link java.sql.Savepoint} mechanism. The
 * {@link #setNestedTransactionAllowed "nestedTransactionAllowed"} flag defaults
 * to "true", since nested transactions will work without restrictions on JDBC
 * drivers that support savepoints (such as the Oracle JDBC driver).
 *
 * <p>This transaction manager can be used as a replacement for the
 * {@link org.springframework.transaction.jta.JtaTransactionManager} in the single
 * resource case, as it does not require a container that supports JTA, typically
 * in combination with a locally defined JDBC DataSource (e.g. an Apache Commons
 * DBCP connection pool). Switching between this local strategy and a JTA
 * environment is just a matter of configuration!
 *
 * <p>As of 4.3.4, this transaction manager triggers flush callbacks on registered
 * transaction synchronizations (if synchronization is generally active), assuming
 * resources operating on the underlying JDBC {@code Connection}. This allows for
 * setup analogous to {@code JtaTransactionManager}, in particular with respect to
 * lazily registered ORM resources (e.g. a Hibernate {@code Session}).
 *
 * <p><b>NOTE: As of 5.3, {@link org.springframework.jdbc.support.JdbcTransactionManager}
 * is available as an extended subclass which includes commit/rollback exception
 * translation, aligned with {@link org.springframework.jdbc.core.JdbcTemplate}.</b>
 *
 * @author Juergen Hoeller
 * @since 02.05.2003
 * @see #setNestedTransactionAllowed
 * @see java.sql.Savepoint
 * @see DataSourceUtils#getConnection(javax.sql.DataSource)
 * @see DataSourceUtils#applyTransactionTimeout
 * @see DataSourceUtils#releaseConnection
 * @see TransactionAwareDataSourceProxy
 * @see LazyConnectionDataSourceProxy
 * @see org.springframework.jdbc.core.JdbcTemplate
 */
@SuppressWarnings("serial")
public class DataSourceTransactionManager extends AbstractPlatformTransactionManager
		implements ResourceTransactionManager, InitializingBean {

	@Nullable
	private DataSource dataSource;

	private boolean enforceReadOnly = false;


	/**
	 * Create a new DataSourceTransactionManager instance.
	 * A DataSource has to be set to be able to use it.
	 * @see #setDataSource
	 */
	public DataSourceTransactionManager() {
		setNestedTransactionAllowed(true);
	}

	/**
	 * Create a new DataSourceTransactionManager instance.
	 * @param dataSource the JDBC DataSource to manage transactions for
	 */
	public DataSourceTransactionManager(DataSource dataSource) {
		this();
		setDataSource(dataSource);
		afterPropertiesSet();
	}


	/**
	 * Set the JDBC DataSource that this instance should manage transactions for.
	 * <p>This will typically be a locally defined DataSource, for example an
	 * Apache Commons DBCP connection pool. Alternatively, you can also drive
	 * transactions for a non-XA J2EE DataSource fetched from JNDI. For an XA
	 * DataSource, use JtaTransactionManager.
	 * <p>The DataSource specified here should be the target DataSource to manage
	 * transactions for, not a TransactionAwareDataSourceProxy. Only data access
	 * code may work with TransactionAwareDataSourceProxy, while the transaction
	 * manager needs to work on the underlying target DataSource. If there's
	 * nevertheless a TransactionAwareDataSourceProxy passed in, it will be
	 * unwrapped to extract its target DataSource.
	 * <p><b>The DataSource passed in here needs to return independent Connections.</b>
	 * The Connections may come from a pool (the typical case), but the DataSource
	 * must not return thread-scoped / request-scoped Connections or the like.
	 * @see TransactionAwareDataSourceProxy
	 * @see org.springframework.transaction.jta.JtaTransactionManager
	 */
	public void setDataSource(@Nullable DataSource dataSource) {
		if (dataSource instanceof TransactionAwareDataSourceProxy) {
			// If we got a TransactionAwareDataSourceProxy, we need to perform transactions
			// for its underlying target DataSource, else data access code won't see
			// properly exposed transactions (i.e. transactions for the target DataSource).
			this.dataSource = ((TransactionAwareDataSourceProxy) dataSource).getTargetDataSource();
		}
		else {
			this.dataSource = dataSource;
		}
	}

	/**
	 * Return the JDBC DataSource that this instance manages transactions for.
	 */
	@Nullable
	public DataSource getDataSource() {
		return this.dataSource;
	}

	/**
	 * Obtain the DataSource for actual use.
	 * @return the DataSource (never {@code null})
	 * @throws IllegalStateException in case of no DataSource set
	 * @since 5.0
	 */
	protected DataSource obtainDataSource() {
		DataSource dataSource = getDataSource();
		Assert.state(dataSource != null, "No DataSource set");
		return dataSource;
	}

	/**
	 * Specify whether to enforce the read-only nature of a transaction
	 * (as indicated by {@link TransactionDefinition#isReadOnly()}
	 * through an explicit statement on the transactional connection:
	 * "SET TRANSACTION READ ONLY" as understood by Oracle, MySQL and Postgres.
	 * <p>The exact treatment, including any SQL statement executed on the connection,
	 * can be customized through {@link #prepareTransactionalConnection}.
	 * <p>This mode of read-only handling goes beyond the {@link Connection#setReadOnly}
	 * hint that Spring applies by default. In contrast to that standard JDBC hint,
	 * "SET TRANSACTION READ ONLY" enforces an isolation-level-like connection mode
	 * where data manipulation statements are strictly disallowed. Also, on Oracle,
	 * this read-only mode provides read consistency for the entire transaction.
	 * <p>Note that older Oracle JDBC drivers (9i, 10g) used to enforce this read-only
	 * mode even for {@code Connection.setReadOnly(true}. However, with recent drivers,
	 * this strong enforcement needs to be applied explicitly, e.g. through this flag.
	 * @since 4.3.7
	 * @see #prepareTransactionalConnection
	 */
	public void setEnforceReadOnly(boolean enforceReadOnly) {
		this.enforceReadOnly = enforceReadOnly;
	}

	/**
	 * Return whether to enforce the read-only nature of a transaction
	 * through an explicit statement on the transactional connection.
	 * @since 4.3.7
	 * @see #setEnforceReadOnly
	 */
	public boolean isEnforceReadOnly() {
		return this.enforceReadOnly;
	}

	@Override
	public void afterPropertiesSet() {
		if (getDataSource() == null) {
			throw new IllegalArgumentException("Property 'dataSource' is required");
		}
	}


	@Override
	public Object getResourceFactory() {
		return obtainDataSource();
	}

	@Override
	protected Object doGetTransaction() {
		// 创建一个DataSourceTransactionObject对象，该对象继承了JdbcTransactionObjectSupport，该对象中有一个ConnectionHolder属性
		DataSourceTransactionObject txObject = new DataSourceTransactionObject();
		// 是否允许当前事务设置保持点
		txObject.setSavepointAllowed(isNestedTransactionAllowed());
		/**
		 * TransactionSynchronizationManager 事务同步管理器对象(该类中都是局部线程变量)
		 * 用来保存当前事务的信息,我们第一次从这里去线程变量中获取 事务连接持有器对象 通过数据源为key去获取
		 * 由于第一次进来开始事务 我们的事务同步管理器中没有被存放.所以此时获取出来的conHolder为null
		 */
		ConnectionHolder conHolder =
				(ConnectionHolder) TransactionSynchronizationManager.getResource(obtainDataSource());
		// 非新创建连接则写false
		txObject.setConnectionHolder(conHolder, false);
		return txObject;
	}

	@Override
	protected boolean isExistingTransaction(Object transaction) {
		// 若第一次进来开始事务，txObject.hasConnectionHolder()返回的null那么表示不存在事务
		DataSourceTransactionObject txObject = (DataSourceTransactionObject) transaction;
		return (txObject.hasConnectionHolder() && txObject.getConnectionHolder().isTransactionActive());
	}

	@Override
	protected void doBegin(Object transaction, TransactionDefinition definition) {
		// 获取事务对象
		DataSourceTransactionObject txObject = (DataSourceTransactionObject) transaction;
		Connection con = null;

		try {
			// 判断是否有连接持有器，若没有则创建一个连接持有器
			if (!txObject.hasConnectionHolder() ||
					txObject.getConnectionHolder().isSynchronizedWithTransaction()) {
				Connection newCon = obtainDataSource().getConnection();
				if (logger.isDebugEnabled()) {
					logger.debug("Acquired Connection [" + newCon + "] for JDBC transaction");
				}
				// 这里设置newConnectionHolder=true
				txObject.setConnectionHolder(new ConnectionHolder(newCon), true);
			}
			// 标记当前的连接是一个同步事务
			txObject.getConnectionHolder().setSynchronizedWithTransaction(true);
			// 获取连接持有器中的连接
			con = txObject.getConnectionHolder().getConnection();
			// 获取事务的隔离级别
			Integer previousIsolationLevel = DataSourceUtils.prepareConnectionForTransaction(con, definition);
			// 设置之前的隔离级别
			txObject.setPreviousIsolationLevel(previousIsolationLevel);
			// 设置是否只读
			txObject.setReadOnly(definition.isReadOnly());

			// Switch to manual commit if necessary. This is very expensive in some JDBC drivers,
			// so we don't want to do it unnecessarily (for example if we've explicitly
			// configured the connection pool to set it already).
			// 判断是否自动提交，若是则设置为手动提交
			if (con.getAutoCommit()) {
				txObject.setMustRestoreAutoCommit(true);
				if (logger.isDebugEnabled()) {
					logger.debug("Switching JDBC Connection [" + con + "] to manual commit");
				}
				// 关闭自动提交，就开启了事务
				con.setAutoCommit(false);
			}

			// 如果当前事务为只读事务，手动设置为只读事务
			prepareTransactionalConnection(con, definition);
			// 设置当前事务为激活状态
			txObject.getConnectionHolder().setTransactionActive(true);
			// 获取事务超时时间
			int timeout = determineTimeout(definition);
			// 若超时时间不为默认值，则设置超时时间
			if (timeout != TransactionDefinition.TIMEOUT_DEFAULT) {
				txObject.getConnectionHolder().setTimeoutInSeconds(timeout);
			}

			// Bind the connection holder to the thread.
			// 将连接持有器绑定到当前线程
			if (txObject.isNewConnectionHolder()) {
				TransactionSynchronizationManager.bindResource(obtainDataSource(), txObject.getConnectionHolder());
			}
		}

		// 若出现异常，则释放新连接
		catch (Throwable ex) {
			if (txObject.isNewConnectionHolder()) {
				DataSourceUtils.releaseConnection(con, obtainDataSource());
				txObject.setConnectionHolder(null, false);
			}
			throw new CannotCreateTransactionException("Could not open JDBC Connection for transaction", ex);
		}
	}

	@Override
	protected Object doSuspend(Object transaction) {
		DataSourceTransactionObject txObject = (DataSourceTransactionObject) transaction;
		// 清空d当前数据库链接持有器
		txObject.setConnectionHolder(null);
		// 解除当前线程绑定的资源
		return TransactionSynchronizationManager.unbindResource(obtainDataSource());
	}

	@Override
	protected void doResume(@Nullable Object transaction, Object suspendedResources) {
		TransactionSynchronizationManager.bindResource(obtainDataSource(), suspendedResources);
	}

	@Override
	protected void doCommit(DefaultTransactionStatus status) {
		DataSourceTransactionObject txObject = (DataSourceTransactionObject) status.getTransaction();
		Connection con = txObject.getConnectionHolder().getConnection();
		if (status.isDebug()) {
			logger.debug("Committing JDBC transaction on Connection [" + con + "]");
		}
		try {
			con.commit();
		}
		catch (SQLException ex) {
			throw translateException("JDBC commit", ex);
		}
	}

	@Override
	protected void doRollback(DefaultTransactionStatus status) {
		DataSourceTransactionObject txObject = (DataSourceTransactionObject) status.getTransaction();
		Connection con = txObject.getConnectionHolder().getConnection();
		if (status.isDebug()) {
			logger.debug("Rolling back JDBC transaction on Connection [" + con + "]");
		}
		try {
			// jdbc的回滚
			con.rollback();
		}
		catch (SQLException ex) {
			throw translateException("JDBC rollback", ex);
		}
	}

	@Override
	protected void doSetRollbackOnly(DefaultTransactionStatus status) {
		DataSourceTransactionObject txObject = (DataSourceTransactionObject) status.getTransaction();
		if (status.isDebug()) {
			logger.debug("Setting JDBC transaction [" + txObject.getConnectionHolder().getConnection() +
					"] rollback-only");
		}
		txObject.setRollbackOnly();
	}

	@Override
	protected void doCleanupAfterCompletion(Object transaction) {
		DataSourceTransactionObject txObject = (DataSourceTransactionObject) transaction;

		// Remove the connection holder from the thread, if exposed.
		// 清除当前线程绑定的资源
		if (txObject.isNewConnectionHolder()) {
			TransactionSynchronizationManager.unbindResource(obtainDataSource());
		}

		// Reset connection.
		Connection con = txObject.getConnectionHolder().getConnection();
		try {
			if (txObject.isMustRestoreAutoCommit()) {
				con.setAutoCommit(true);
			}
			DataSourceUtils.resetConnectionAfterTransaction(
					con, txObject.getPreviousIsolationLevel(), txObject.isReadOnly());
		}
		catch (Throwable ex) {
			logger.debug("Could not reset JDBC Connection after transaction", ex);
		}
		// 若当前事务为新创建的连接，则释放连接
		if (txObject.isNewConnectionHolder()) {
			if (logger.isDebugEnabled()) {
				logger.debug("Releasing JDBC Connection [" + con + "] after transaction");
			}
			DataSourceUtils.releaseConnection(con, this.dataSource);
		}

		txObject.getConnectionHolder().clear();
	}


	/**
	 * Prepare the transactional {@code Connection} right after transaction begin.
	 * <p>The default implementation executes a "SET TRANSACTION READ ONLY" statement
	 * if the {@link #setEnforceReadOnly "enforceReadOnly"} flag is set to {@code true}
	 * and the transaction definition indicates a read-only transaction.
	 * <p>The "SET TRANSACTION READ ONLY" is understood by Oracle, MySQL and Postgres
	 * and may work with other databases as well. If you'd like to adapt this treatment,
	 * override this method accordingly.
	 * @param con the transactional JDBC Connection
	 * @param definition the current transaction definition
	 * @throws SQLException if thrown by JDBC API
	 * @since 4.3.7
	 * @see #setEnforceReadOnly
	 */
	protected void prepareTransactionalConnection(Connection con, TransactionDefinition definition)
			throws SQLException {

		if (isEnforceReadOnly() && definition.isReadOnly()) {
			try (Statement stmt = con.createStatement()) {
				stmt.executeUpdate("SET TRANSACTION READ ONLY");
			}
		}
	}

	/**
	 * Translate the given JDBC commit/rollback exception to a common Spring
	 * exception to propagate from the {@link #commit}/{@link #rollback} call.
	 * <p>The default implementation throws a {@link TransactionSystemException}.
	 * Subclasses may specifically identify concurrency failures etc.
	 * @param task the task description (commit or rollback)
	 * @param ex the SQLException thrown from commit/rollback
	 * @return the translated exception to throw, either a
	 * {@link org.springframework.dao.DataAccessException} or a
	 * {@link org.springframework.transaction.TransactionException}
	 * @since 5.3
	 */
	protected RuntimeException translateException(String task, SQLException ex) {
		return new TransactionSystemException(task + " failed", ex);
	}


	/**
	 * DataSource transaction object, representing a ConnectionHolder.
	 * Used as transaction object by DataSourceTransactionManager.
	 */
	private static class DataSourceTransactionObject extends JdbcTransactionObjectSupport {

		private boolean newConnectionHolder;

		private boolean mustRestoreAutoCommit;

		public void setConnectionHolder(@Nullable ConnectionHolder connectionHolder, boolean newConnectionHolder) {
			super.setConnectionHolder(connectionHolder);
			this.newConnectionHolder = newConnectionHolder;
		}

		public boolean isNewConnectionHolder() {
			return this.newConnectionHolder;
		}

		public void setMustRestoreAutoCommit(boolean mustRestoreAutoCommit) {
			this.mustRestoreAutoCommit = mustRestoreAutoCommit;
		}

		public boolean isMustRestoreAutoCommit() {
			return this.mustRestoreAutoCommit;
		}

		public void setRollbackOnly() {
			getConnectionHolder().setRollbackOnly();
		}

		@Override
		public boolean isRollbackOnly() {
			return getConnectionHolder().isRollbackOnly();
		}

		@Override
		public void flush() {
			if (TransactionSynchronizationManager.isSynchronizationActive()) {
				TransactionSynchronizationUtils.triggerFlush();
			}
		}
	}

}
