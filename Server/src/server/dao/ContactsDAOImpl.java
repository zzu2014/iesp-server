package server.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import server.model.Account;
import server.model.Contacts;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DuplicateKeyException;
/**
 * Created by tangfei on 14-8-7.
 */
public class ContactsDAOImpl implements ContactsDAO {
    private DataSource dataSource;

    public ContactsDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /*@Override
    public List<Contacts> list() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        *//*sql query language*//*
        String sql = "SELECT userid, name, phone_1, phone_2, phone_3, phone_4, email_1, email_2, " +
                "address_1, address_2, birthday, organization from contacts";

        List<Contacts> contactsList;

        *//*query contacts*//*
        contactsList = jdbcTemplate.query(sql, new RowMapper<Contacts>() {
            @Override
            public Contacts mapRow(ResultSet resultSet, int i) throws SQLException {
                Contacts contacts = new Contacts();
                contacts.setUserid(resultSet.getInt("userid"));
                contacts.setName(resultSet.getString("name"));
                contacts.setPhone_1(resultSet.getString("phone_1"));
                contacts.setPhone_2(resultSet.getString("phone_2"));
                contacts.setPhone_3(resultSet.getString("phone_3"));
                contacts.setPhone_4(resultSet.getString("phone_4"));
                contacts.setEmail_1(resultSet.getString("email_1"));
                contacts.setEmail_2(resultSet.getString("email_2"));
                contacts.setAddress_1(resultSet.getString("address_1"));
                contacts.setAddress_2(resultSet.getString("address_2"));
                contacts.setOrganization(resultSet.getString("organization"));
                contacts.setBirthday(resultSet.getDate("birthday"));
                return contacts;
            }
        });*/

    @Override
    public List<Contacts> list(String accountname) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT userid FROM users WHERE accountname = '" + accountname + "'";
        List<Account> AccountList;
        AccountList = jdbcTemplate.query(sql, new RowMapper<Account>() {
            @Override
            public Account mapRow(ResultSet resultSet, int i) throws SQLException {
                Account account = new Account();
                account.setUserid(resultSet.getInt("userid"));
                return account;
            }
        });

        int userid = AccountList.get(0).getUserid();
        System.out.println("userid is: " + userid);

        /*sql query language*/
        sql = "SELECT userid, contactid, name, phone_1, phone_2, phone_3, phone_4, email_1, email_2, " +
                "address_1, address_2, birthday, organization from contacts WHERE userid='" + userid +
                "'ORDER BY contactid";

        System.out.println("BUG1");

        List<Contacts> contactsList;

        /*query contacts*/
        contactsList = jdbcTemplate.query(sql, new RowMapper<Contacts>() {
            @Override
            public Contacts mapRow(ResultSet resultSet, int i) throws SQLException {
                Contacts contacts = new Contacts();
                contacts.setUserid(resultSet.getInt("userid"));
                contacts.setContactid(resultSet.getInt("contactid"));
                contacts.setName(resultSet.getString("name"));
                contacts.setPhone_1(resultSet.getString("phone_1"));
                contacts.setPhone_2(resultSet.getString("phone_2"));
                contacts.setPhone_3(resultSet.getString("phone_3"));
                contacts.setPhone_4(resultSet.getString("phone_4"));
                contacts.setEmail_1(resultSet.getString("email_1"));
                contacts.setEmail_2(resultSet.getString("email_2"));
                contacts.setAddress_1(resultSet.getString("address_1"));
                contacts.setAddress_2(resultSet.getString("address_2"));
                contacts.setOrganization(resultSet.getString("organization"));
                contacts.setBirthday(resultSet.getDate("birthday"));
                return contacts;
            }
        });

        return contactsList;
    }
}
