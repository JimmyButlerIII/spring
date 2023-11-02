package transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;


public class UserDao {

    @Autowired
	private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    public void insertUser(String userName, String password, String email, Integer age){
		String sql = "insert into users(username, password, email, age) values (?, ?, ?, ?)";
		jdbcTemplate.update(sql, userName, password, email, age);
	}

    /**
     * 按照图书的id来获取图书的价格
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.NESTED)
    public int updateEmail(Integer id, String email){
//		TransactionSynchronizationManager.registerSynchronization(new CustomerTransactionSynchronization("user_dao"));
		String sql = "update users set email=? where id=?";
		jdbcTemplate.update(sql, email, id);
		return 1;
    }


    public String findUser(Integer id){
		String sql = "select email from users where id=?";
		return jdbcTemplate.queryForObject(sql, String.class, id);
    }

	@Transactional(propagation = Propagation.REQUIRED)
	public Integer addAge(Integer id) throws IOException {
		String sql = "update users set age = age + 1 where id = ?";
		Integer result = jdbcTemplate.update(sql, id);
//		errorMethod();
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Integer addScore(Integer id){
		String sql = "update users set score = score + 1 where id=?";
        return jdbcTemplate.update(sql, id);
	}

	public void errorMethod() throws IOException {
		try {
			for (int i = -5 ;i <= 6 ;i++)
				System.out.println(10/i);
		} catch (Exception e) {
			throw new RuntimeException();
//			throw new IOException();
		}
	}
}