package transactional;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:dbconfig.properties")
@EnableTransactionManagement
public class TransactionConfig {
    @Value("${jdbc.driverClassName}")
    private String driverClassname;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

	/**
	 * 事务管理器
	 * @param dataSource
	 * @return
	 */
    @Bean
    public TransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


	/**
	 * 数据库链接
	 * @return
	 */
	@Bean
	public DataSource dataSource() {
		DruidDataSource data = new DruidDataSource();
		data.setDriverClassName(driverClassname);
		data.setUrl(url);
		data.setUsername(username);
		data.setPassword(password);
		return data;
	}


	@Bean
	public UserDao userDao() {
		return new UserDao();
	}

	@Bean
	public UserService userService() {
		return new UserService();
	}

	@Bean
	public CardService cardService() {
		return new CardService();
	}
}