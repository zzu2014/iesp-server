package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import server.dao.AccountDAO;
import server.dao.ContactsDAO;
import server.model.Account;
import server.model.Contact;
import server.model.Contacts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by tangfei on 14-8-6.
 */
@Controller
public class HomeController {

    @Autowired
    private ContactsDAO contactsDAO;

    @Autowired
    private AccountDAO accountDAO;

    @RequestMapping("/register")
    public String resginster() {
        return "register";
    }

    @RequestMapping("/registerControl")
    public void registerControl(Account account, HttpServletResponse response) {

        /*System.out.println(account.getAccountname());
        System.out.println(account.getPassword());*/

        boolean Result = accountDAO.addAccount(account.getAccountname(), account.getPassword());
        /*System.out.println(Result);*/

        String data = null;
        if(Result) {
            data = "{\"result\":\"" + "Success" + "\"}";
        }
        else data = "{\"result\":\"" + "Fail" + "\"}";

        response.setContentType("application/json");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*@RequestMapping("/contactslist")
    public ModelAndView listContacts() {
        System.out.println("contactslist Working");
        List<Contacts> contactsList = contactsDAO.list();
        ModelAndView modelAndView = new ModelAndView("contactslist");
        modelAndView.addObject("contacts", contactsList);
        return modelAndView;
    }*/

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/loginControl")
    public ModelAndView loginControl(HttpServletRequest request) {
        String account = request.getParameter("accountname");
        String password = request.getParameter("password");
        /*System.out.println(request.getParameter("accountname"));
        System.out.println(request.getParameter("password"));*/
        boolean result = accountDAO.checkAccount(request.getParameter("accountname"), request.getParameter("password"));
        System.out.println(result);
        if(!result) {
            return new ModelAndView("redirect:login");
        }

        /*loginUser = request.getParameter("accountname");*/

        ModelAndView modelAndView = new ModelAndView("redirect:home");
        modelAndView.addObject("accountname", request.getParameter("accountname"));
        /*System.out.println(modelAndView.getModel().get("accountname"));*/

        HttpSession httpSession = request.getSession(true);
        httpSession.setAttribute("account", (Object) account);
        System.out.println("Object is " + (String)httpSession.getAttribute("account"));
        System.out.println(httpSession.getId());
        return modelAndView;
    }

    @RequestMapping(value = "/home")
    public ModelAndView home(HttpServletRequest request) {

        String account = (String) request.getSession().getAttribute("account");

        if(account == null ||
                !account.equals(request.getParameter("accountname"))) return new ModelAndView("redirect:/login");

        System.out.println("Session is " + request.getRequestedSessionId());

        /*if(request.getParameter("accountname") == null) {
            System.out.println("没有登陆");
            return new ModelAndView("redirect:/login");
        }*/

        /*Test*/
        /*System.out.println(loginUser);*/
        List<Contacts> contactsList = contactsDAO.list(account);
        System.out.println("size is: " + contactsList.size());
        /*System.out.println(contactsList.get(0).getName());
        System.out.println("contactid is: " + contactsList.get(0).getContactid());*/
        /*END*/

        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("contacts", contactsList);

        return modelAndView;
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request) {
        if(request.getSession().getAttribute("account") == null) return "redirect:/login";
        System.out.println("add Account is " + request.getSession().getAttribute("account"));
        return "add";
    }

    @RequestMapping(value = "/addContact")
    /*BUG*/
    public void addContact(Contacts contacts, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("ADDCONTANT");
        String accountname = (String) request.getSession().getAttribute("account");
        System.out.println("addContact accountname is " + accountname + "," + contacts.getName() + ", " + contacts.getPhone_1());
        boolean result = contactsDAO.addContact(accountname, contacts);
        System.out.println("result is " + result);


        String data = null;
        if(result) data = "{\"result\":\"" + "Success" + "\"}";
        else data = "{\"result\":\"" + "Fail" + "\"}";

        response.setContentType("application/json");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*删除单个联系人*/
    @RequestMapping("/del")
    public void del(Contact contact, HttpServletRequest request, HttpServletResponse response) {
        String account = (String) request.getSession().getAttribute("account");     //获取当前帐户

        System.out.println("account is " + account + ", contactid is " + contact.getContactid());   //debug

        boolean result = contactsDAO.delContact(account, contact.getContactid());     //删除单个联系人

        String data = null;
        if(result) data = "{\"result\":\"" + "Delete Success" + "\"}";
        else data = "{\"result\":\"" + "Delete Fail" + "\"}";

        response.setContentType("application/json");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*修改联系人*/
    @RequestMapping("/change")
    public ModelAndView change(HttpServletRequest request) {
        int contactid = Integer.valueOf(request.getParameter("contactid"));
        System.out.println("已收到修改请求:" + contactid);      //debug
        Contacts contacts = contactsDAO.getOneContact(contactid);
        ModelAndView modelAndView = new ModelAndView("change");
        modelAndView.addObject("contacts", contacts);
        return modelAndView;
    }

    @RequestMapping("/chgContact")
    public void chgContact(Contacts contacts, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("修改请求：");
    }

    /*Test 控制器间跳转*/
    @RequestMapping("/redirect")
    public String redirect() {
        return "redirect:home";
    }
    /*END*/
}
