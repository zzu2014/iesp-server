package server.controller;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import server.dao.ContactListDAO;
import server.dao.ContactsDAO;
import server.dao.UnZipDAO;
import server.model.Contacts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by TangFei on 2014/8/14.
 */
@Controller
public class UploadController {

    @Autowired
    private ContactsDAO contactsDAO;

    @Autowired
    public ContactListDAO contactListDAO;

    @Autowired
    private UnZipDAO unZipDAO;

    @RequestMapping("/upload2")           //将从客户端得到的zip文件解压缩
    private String upload2(HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException, JSONException, ParseException {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)) {
            System.out.println(request instanceof MultipartHttpServletRequest);
            MultipartHttpServletRequest mullipartRequest = (MultipartHttpServletRequest)request;
            Iterator<String> iter = mullipartRequest.getFileNames();
            while(iter.hasNext()){
                MultipartFile file = mullipartRequest.getFile((String)iter.next());
                if(file != null){
                    String account = (String)request.getSession().getAttribute("account");
                    String path = request.getServletContext().getRealPath("");
                    System.out.println("path is " + path);
                    path = path + "\\" + account + file.getName();
                    System.out.println("fullpath is " + path);
                    /*String path = "f:\\test.zip";//文件保存的路径*/
                    //将从客服端上传的文件保存到path路径下
                    file.transferTo(new File(path));
                    //将path路径下的zip文件解压,返回jsonString数组
                    String jsonString = unZipDAO.unzip_method(path);
                    System.out.println(jsonString);
                    //将jsonString数组中的联系人信息保存到ContactsList_2中
                    List<Contacts> ContactsList_2 = contactListDAO.stringToContacts(jsonString);  //将
                    System.out.println("DEBUG");
                    /*获取该request.getParameter("accountname")账户下的联系人,得到ContadctsList_1*/
                    System.out.print("account " + request.getParameter("accountname"));
                    /*List<Contacts> ContactsList_1 = contactsDAO.list(request.getParameter("accountname"));*/
                    System.out.print("Session account " + request.getSession().getAttribute("account"));
                    List<Contacts> ContactsList_1 = contactsDAO.list(account);
                    System.out.println("DEBUG1");
					ContactsList_2 = contactListDAO.cmp(ContactsList_1, ContactsList_2);
                    System.out.println("DEBUG2");
					/*
					    将ConContactsList_2插入到request.getParameter("accountname")账户下
				    */
                    //删除保存在本地的zip文件
                    if(ContactsList_2.size() > 0) {
                        contactsDAO.insertMutliContact(account, ContactsList_2);
                        System.out.println("执行插入");
                    }
                    /*if(ContactsList_2 != null)
                        contactsDAO.insertMutliContact(account, ContactsList_2);*/
                    for(Contacts ct : ContactsList_2) {
                        System.out.println("ContactsList_2_1 name : " + ct.getName());
                        System.out.println("ContactsList_2_1 birthday : " + ct.getBirthday());
                    }
                    System.out.println("DEBUG3");
                    File f = new File(path);
                    if(f.exists())  f.delete();
                    System.out.println("文件已删除");     //*************
                }
            }
        }
        System.out.println("上传成功");
        return null;
    }

    @RequestMapping("/toUpload")
    private String toUpload(){
        return "upload";
    }
}