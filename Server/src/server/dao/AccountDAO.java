package server.dao;

import server.model.Account;

import java.util.List;

/**
 * Created by tangfei on 14-8-7.
 */
public interface AccountDAO {
    public List<Account> list();

    public boolean addAccount(String Accountname, String Password);

    public boolean checkAccount(String Accountname, String Password);
}
