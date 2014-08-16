package server.dao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import server.model.Contacts;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TangFei on 2014/8/14.
 */
public class ContactListDAOImpl implements ContactListDAO {
    @Override
    public List<Contacts> stringToContacts(String jsonString) throws JSONException, ParseException {
        List<Contacts> Listcontacts = new ArrayList<Contacts>();

        JSONArray jsonArray = new JSONArray(jsonString);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            Contacts ct = new Contacts();
            ct.setContactid(0);
            System.out.println("debug1");
            if(jsonObj.has("name"))
            ct.setName((String)jsonObj.get("name"));
            System.out.println("debug2");
            if(jsonObj.has("phone_1"))
            ct.setPhone_1((String)jsonObj.get("phone_1"));
            System.out.println("debug3");
            if(jsonObj.has("phone_2"))
            ct.setPhone_2((String)jsonObj.get("phone_2"));
            System.out.println("debug4");
            if(jsonObj.has("phone_3"))
            ct.setPhone_3((String)jsonObj.get("phone_3"));
            System.out.println("debug5");
            if(jsonObj.has("phone_4"))
            ct.setPhone_4((String)jsonObj.get("phone_4"));
            System.out.println("debug6");
            if(jsonObj.has("email_1"))
            ct.setEmail_1((String)jsonObj.get("email_1"));
            System.out.println("debug7");
            if(jsonObj.has("email_2"))
            ct.setEmail_2((String)jsonObj.get("email_2"));
            System.out.println("debug8");
            if(jsonObj.has("address_1"))
            ct.setAddress_1((String)jsonObj.get("address_1"));
            System.out.println("debug9");
            if(jsonObj.has("address_2"))
            ct.setAddress_2((String) jsonObj.get("address_2"));
            System.out.println("debug10");

            /*if(jsonObj.get("birthday") != null)
                ct.setBirthday((Date)jsonObj.get("birthday"));
            System.out.println("Json " + jsonObj.get("birthday"));*/
            //String birthday = new SimpleDateFormat("yyyy-MM-dd").format(jsonObj.get("birthday"));

            //System.out.println(jsonObj.get("birthday") instanceof Date);
            /*if(jsonObj.get("birthday") != null)
                ct.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse((String) jsonObj.get("birthday")));*/
            /*if(jsonObj.get("birthday") instanceof Date) {
                ct.setBirthday((Date) jsonObj.get("birthday"));
            }*/
            System.out.println("JsonObj birthday is " + jsonObj.get("birthday").getClass());
            System.out.println("JsonObj birthday is " + jsonObj.get("birthday"));
            //if(jsonObj.get("birthday") != null) {
            if(!jsonObj.get("birthday").equals(null)) {
//                ct.setBirthday((Date) new SimpleDateFormat("yyyy-MM-dd").parse((String) jsonObj.get("birthday")));
                System.out.println("Date is " + Date.valueOf((String) jsonObj.get("birthday")));
                ct.setBirthday(Date.valueOf((String) jsonObj.get("birthday")));
                System.out.println("cast to birthday is " + ct.getBirthday());
            }
            /*if(jsonObj.get("birthday").equals(null)) {
                System.out.println("birthday is  null");
            }
            else {
                System.out.println("birthday_1  " + (String) jsonObj.get("birthday"));
                ct.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse((String) jsonObj.get("birthday")));
                System.out.println("birthday_2  " + ct.getBirthday());
            }*/


            System.out.println("debug11");
            if(jsonObj.has("organization"))
            ct.setOrganization((String)jsonObj.get("organization"));
            /*ct.setData_1(null);
            ct.setData_2(null);
            ct.setData_3(null);*/
            System.out.println("debug12");
            Listcontacts.add(ct);
        }
        return Listcontacts;
    }

    @Override
    public List<Contacts> cmp(List<Contacts> ContactsList_1, List<Contacts> ContactsList_2) {

        List<Contacts> c3 = new ArrayList<Contacts>();
        boolean bool = false;

        for(Contacts i: ContactsList_2){
            for(Contacts j: ContactsList_1){
                if(i.getName().equals(j.getName()) && i.getPhone_1().equals(j.getPhone_1()) &&
                        i.getPhone_2().equals(j.getPhone_2()) && i.getPhone_3().equals(j.getPhone_3()) &&
                        i.getPhone_4().equals(j.getPhone_4()) && i.getEmail_1().equals(j.getEmail_1()) &&
                        i.getEmail_2().equals(j.getEmail_2()) && i.getAddress_1().equals(j.getAddress_1()) &&
                        i.getAddress_2().equals(j.getAddress_2()) && //i.getBirthday().equals(new SimpleDateFormat("yyyy-MM-dd").format(j.getBirthday())) &&
                        i.getOrganization().equals(j.getOrganization())){
                    if(i.getBirthday() == null && j.getBirthday() == null)
                           bool = true;
                    if(i.getBirthday() != null && j.getBirthday() != null){
                        if(i.getBirthday().equals(new SimpleDateFormat("yyyy-MM-dd").format(j.getBirthday())))
                            bool = true;
                    }
                    bool = true;
                }
                if(bool == true)  break;
            }
            if(!bool) c3.add(i);
            bool = false;
        }
        return c3;
    }
}
