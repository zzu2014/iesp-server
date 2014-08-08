package server.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import server.model.Account;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by tangfei on 14-8-7.
 */
public class AccountDAOImpl implements AccountDAO {
    private DataSource dataSource;

    public AccountDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public AccountDAOImpl(String s) {
    }

    @Override
    public List<Account> list() {
        return null;
    }


    @Override
    public boolean addAccount(String Accountname, String Password) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT accountname from users where accountname = '" + Accountname + "';";


        List<String> AccountList;
        AccountList = jdbcTemplate.query(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("accountname");
            }
        });


        if(AccountList.size() > 0) {
            return false;
        }

        sql = "INSERT INTO users (accountname, password) VALUES('" +
                Accountname + "', '" + Password + "');";
        int line = jdbcTemplate.update(sql);
        if(line == 0) return false;
        return true;
    }

    @Override
    public boolean checkAccount(String Accountname, String Password) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT password FROM users WHERE accountname = '" +
                Accountname + "';";
        List<String> PasswordList;

        PasswordList = jdbcTemplate.query(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("password");
            }
        });

        if(PasswordList.size() == 0) return false;
        if(PasswordList.size() > 1) {
            System.out.println("SQL ERROR!");
            return false;
        }
        if(!Password.equals(PasswordList.get(0))) {
            return false;
        }

        return true;
    }
}
