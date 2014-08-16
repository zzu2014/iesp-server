package server.Test;

import com.sun.org.apache.bcel.internal.util.ClassPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import server.dao.AccountDAO;
import server.dao.ContactsDAO;
import server.model.Contacts;

import javax.servlet.ServletContext;
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

    private static final int BUFFER_SIZE = 4096;

    /*Test 把数据转换成Json格式，压缩并下载到客户端
    使用方法示例：
    http://127.0.0.1:8080/Test/toJson?accountname=iamtangfei%40gmail.com*/
    @RequestMapping("/toJson")
    public void contactsJson(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(request.getParameter("accountname"));

        List<Contacts> contactsList = contactsDAO.list(request.getParameter("accountname"));

        for(int i = 0; i < contactsList.size(); i++) {
            System.out.println(i + " " + contactsList.get(i).getEmail_2());
        }

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
        if(sb.length() == 1) sb.deleteCharAt(0);
        System.out.println(new String(sb));

        String filename = request.getParameter("accountname") + "contactsJson.zip";

        ServletContext context = request.getServletContext();
        String appPath = context.getRealPath("");
        System.out.println("appPath is " + appPath);

        String fullPath = appPath + "\\" + filename;
        /*String fullPath = "C:\\Users\\TangFei\\IdeaProjects\\ContactsBackup\\out\\artifacts\\Server_war_exploded\\resources\\temp\\" + filename;*/
        System.out.println("fullPath is " + fullPath);

        File file = new File(fullPath);
        FileOutputStream outStream = new FileOutputStream(file);
        ZipOutputStream out= new ZipOutputStream(outStream);
        ZipEntry entry = new ZipEntry("contactsJson.json");
        out.putNextEntry(entry);
        out.write(new String(sb).getBytes("UTF-8"));
        out.flush();
        out.closeEntry();
        out.close();

        FileInputStream fileInputStream = new FileInputStream(file);
        response.setContentLength((int) file.length());

        response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
        response.setContentType("application/octet-stream; charset=" + "Shift_JIS");
        response.encodeURL("Shift_JIS");

        OutputStream outputStream = response.getOutputStream();

        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;

        while((bytesRead = fileInputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        fileInputStream.close();
        outputStream.close();

        file.delete();
    }
    /*END*/

    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {


        String filePath = "/resources/download/FirefoxSetup31.0.exe";

        ServletContext context = request.getServletContext();
        String appPath = context.getRealPath("");
        System.out.println("appPath is " + appPath);

        String fullPath = appPath + filePath;
        System.out.println("fullPath is " + fullPath);
        File downloadFile = new File(fullPath);
        System.out.println("FileName is " + downloadFile.getName());
        FileInputStream inputStream = new FileInputStream(downloadFile);

        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        System.out.println("Mime Type is " + mimeType);

        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());

        /*String headerkey = "Content-Dispostion";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerkey, headerValue);*/

        response.setHeader("Content-Disposition", "attachment;filename=" + downloadFile.getName());
        response.setContentType("application/octet-stream; charset=" + "Shift_JIS");
        response.encodeURL("Shift_JIS");

        OutputStream outputStream = response.getOutputStream();

        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;

        while((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outputStream.close();
    }

}
