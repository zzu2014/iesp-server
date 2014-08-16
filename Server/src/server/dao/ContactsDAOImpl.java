package server.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import server.model.Account;
import server.model.Contacts;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
/**
 * Created by tangfei on 14-8-7.
 */
public class ContactsDAOImpl implements ContactsDAO {
    private DataSource dataSource;

    public ContactsDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    private AccountDAO accountDAO;

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
        /*System.out.println("userid is: " + userid);*/

        /*sql query language*/
        sql = "SELECT userid, contactid, name, phone_1, phone_2, phone_3, phone_4, email_1, email_2, " +
                "address_1, address_2, birthday, organization from contacts WHERE userid='" + userid +
                "'ORDER BY contactid";

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

    @Override
    public boolean addContact(String accountname, Contacts contacts) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        System.out.println("ADD");
        int userid = accountDAO.getUserid(accountname);
        System.out.println("userid is " + userid);
        if(userid == 0) {
            System.out.println("BUG");
            return false;
        }

        String birthday;
        /*have a bug*/
        System.out.println("add Bug");

        String sql = null;
        if(contacts.getBirthday() != null) {
            birthday = new SimpleDateFormat("yyyy-MM-dd").format(contacts.getBirthday());
            sql = "INSERT INTO contacts(userid, name, phone_1, phone_2, phone_3, phone_4," +
                    "email_1, email_2, address_1, address_2, birthday, organization) VALUES('" + userid + "', '" +
                    contacts.getName() + "', '" + contacts.getPhone_1() + "', '" + contacts.getPhone_2() + "', '" + contacts.getPhone_3() +
                    "', '" + contacts.getPhone_4() + "', '" + contacts.getEmail_1() + "', '" + contacts.getEmail_2() +
                    "', '" + contacts.getAddress_1() + "', '" + contacts.getAddress_2() + "', '" + birthday +
                    "', '" + contacts.getOrganization() + "');";
        }
        else {
            sql = "INSERT INTO contacts(userid, name, phone_1, phone_2, phone_3, phone_4," +
                    "email_1, email_2, address_1, address_2, organization) VALUES('" + userid + "', '" +
                    contacts.getName() + "', '" + contacts.getPhone_1() + "', '" + contacts.getPhone_2() + "', '" + contacts.getPhone_3() +
                    "', '" + contacts.getPhone_4() + "', '" + contacts.getEmail_1() + "', '" + contacts.getEmail_2() +
                    "', '" + contacts.getAddress_1() + "', '" + contacts.getAddress_2() +
                    "', '" + contacts.getOrganization() + "');";
        }
        System.out.println("sql is " + sql);
        int result = jdbcTemplate.update(sql);
        System.out.println(result);
        if(result == 0) return false;
        return true;
    }

    @Override
    public void insertMutliContact(String accountname, List<Contacts> ContactsList_2) {

        final Integer User_Id;

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
        User_Id = AccountList.get(0).getUserid();

        final List<Contacts> ContactsList = ContactsList_2;

        String sql_insert="insert into contacts(userid,name,phone_1,phone_2,phone_3,phone_4,email_1, email_2,address_1,address_2,birthday,organization)" +
                " values(?,?,?,?,?,?,?,?,?,?,?,?);";
        jdbcTemplate.batchUpdate(sql_insert, new BatchPreparedStatementSetter()
        {
            public void setValues(PreparedStatement ps,int i)throws SQLException
            {
                Integer userid = User_Id;
                String name=ContactsList.get(i).getName();
                String phone_1=ContactsList.get(i).getPhone_1();
                String phone_2=ContactsList.get(i).getPhone_2();
                String phone_3=ContactsList.get(i).getPhone_3();
                String phone_4=ContactsList.get(i).getPhone_4();
                String email_1=ContactsList.get(i).getEmail_1();
                String email_2=ContactsList.get(i).getEmail_2();
                String address_1=ContactsList.get(i).getAddress_1();
                String address_2=ContactsList.get(i).getAddress_2();
                //Date birthday=(Date) ContactsList.get(i).getBirthday();
                //String birthday = new SimpleDateFormat("yyyy-MM-dd").format(ContactsList.get(i).getBirthday());
                //if (ContactsList.get(i).getBirthday() instanceof Date)
                    Date birthday = (Date)ContactsList.get(i).getBirthday();
                String organization=ContactsList.get(i).getOrganization();
                ps.setInt(1, userid);
                ps.setString(2, name);
                ps.setString(3, phone_1);
                ps.setString(4, phone_2);
                ps.setString(5, phone_3);
                ps.setString(6, phone_4);
                ps.setString(7, email_1);
                ps.setString(8, email_2);
                ps.setString(9, address_1);
                ps.setString(10, address_2);
                ps.setDate(11, birthday);
                ps.setString(12, organization);
            }
            public int getBatchSize(){
                return ContactsList.size();
            }
        });
    }

    @Override
    public boolean delContact(String accountname, int contact_id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int userid = accountDAO.getUserid(accountname);
        String sql = "DELETE FROM contacts WHERE userid = '" + userid +
                "' and contactid = '" + contact_id + "';";
        int result = jdbcTemplate.update(sql);
        if(result == 1) return true;
        else System.out.println("删除单个联系人发生错误！");
        return false;
    }

    @Override
    public Contacts getOneContact(int contactid) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM contacts WHERE contactid = '" + contactid + "';";
        List<Contacts> contactsList = jdbcTemplate.query(sql, new RowMapper<Contacts>() {
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
                contacts.setAddress_1(resultSet.getString("address_2"));
                contacts.setBirthday(resultSet.getDate("birthday"));
                contacts.setOrganization(resultSet.getString("organization"));
                return contacts;
            }
        });
        if(contactsList.size() != 1) {
            System.out.println("获取单个联系人信息出错！");
            return null;
        }
        return contactsList.get(0);
    }

    @Override
    public boolean chgContact(int contactid, Contacts contacts) {

        return false;
    }
}
