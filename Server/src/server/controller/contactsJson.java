/*
package cn.com.contacts;
 
import java.io.IOException; 
import java.io.PrintWriter; 
 
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
 

@RequestMapping("/contactsJson")
public void contactsJson(HttpServletRequest request, HttpServletResponse response){
        System.out.println(request.getParameter("accountname"));

        List<Contacts>contactsList=contactsDAO.list(request.getParameter("accountname"));
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        try{
        PrintWriter out=response.getWriter();
        StringBuffer sb=new StringBuffer();//��ݻ���
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
        out.write(new String(sb));
        out.flush();
        out.close();//�ر�
        }catch(Exception e){
        e.printStackTrace();
        }
        }*/
