package server.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import server.dao.AccountDAO;
import server.dao.ContactsDAO;
import server.model.Contacts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by TangFei on 2014/8/12.
 */
@Controller
@RequestMapping("/Test")
public class Test {
    @Autowired
    private ContactsDAO contactsDAO;

    @Autowired
    private AccountDAO accountDAO;

    /*Test 把数据转换成Json格式
    使用方法示例：
    http://127.0.0.1:8080/Test/toJson?accountname=iamtangfei%40gmail.com*/
    @RequestMapping("/toJson")
    public void contactsJson(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(request.getParameter("accountname"));

        List<Contacts> contactsList=contactsDAO.list(request.getParameter("accountname"));

        /*response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");*/

        StringBuffer sb=new StringBuffer();
        sb.append('[');
        for(Contacts contact:contactsList){
            sb.append('{').append("\"contactid\":").append("\""+contact.getContactid()+"\"").append(",");
            sb.append("\"name\":").append("\""+contact.getName()+"\"").append(",");
            sb.append("\"phone_1\":").append("\""+contact.getPhone_1()+"\"").append(",");
            sb.append("\"phone_2\":").append("\""+contact.getPhone_2()+"\"").append(",");
            sb.append("\"phone_3\":").append("\""+contact.getPhone_3()+"\"").append(",");
            sb.append("\"phone_4\":").append("\""+contact.getPhone_4()+"\"").append(",");
            sb.append("\"email_1\":").append("\""+contact.getEmail_1()+"\"").append(",");
            sb.append("\"email_2\":").append("\""+contact.getEmail_2()+"\"").append(",");
            sb.append("\"address_1\":").append("\""+contact.getAddress_1()+"\"").append(",");
            sb.append("\"address_2\":").append("\""+contact.getAddress_2()+"\"").append(",");
            sb.append("\"organization\":").append("\""+contact.getOrganization()+"\"").append(",");
            sb.append("\"birthday\":").append(contact.getBirthday());
            sb.append('}').append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(']');
        System.out.println(new String(sb));


        String filename = request.getParameter("accountname") + "contactsJson.zip";
        File file = new File("D://", filename);
        FileOutputStream outStream = new FileOutputStream(file);
        ZipOutputStream out=new ZipOutputStream(outStream);
        ZipEntry entry = new ZipEntry("contactsJson.json");
        out.putNextEntry(entry);
        out.write(new String(sb).getBytes());
        out.flush();
        out.closeEntry();
        out.close();
    }
    /*END*/
}
