package server.dao;

/**
 * Created by TangFei on 2014/8/14.
 */

import java.text.ParseException;
import java.util.List;
import org.json.JSONException;
import server.model.Contacts;


public interface ContactListDAO {
    public List<Contacts> stringToContacts(String jsonString) throws JSONException, ParseException;

    public abstract List<Contacts> cmp(List<Contacts> ContactsList_1, List<Contacts> ContactsList_2);
}
