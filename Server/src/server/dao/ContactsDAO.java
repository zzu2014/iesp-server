package server.dao;

import server.model.Contacts;

import java.util.List;

/**
 * Created by tangfei on 14-8-7.
 */
public interface ContactsDAO {
    public List<Contacts> list();
}
