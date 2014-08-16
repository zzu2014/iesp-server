package server.dao;

import server.model.Contacts;

import java.util.List;

/**
 * Created by tangfei on 14-8-7.
 */
public interface ContactsDAO {
    public List<Contacts> list(String accountname);

    public boolean addContact(String accountname, Contacts contacts);

    public void insertMutliContact(String accountname, List<Contacts> ContactsList_2);
}
