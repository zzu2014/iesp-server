package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import server.dao.AccountDAO;
import server.dao.ContactsDAO;
import server.model.Account;
import server.model.Contacts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
    public void registerControl(Account account, HttpServletRequest request, HttpServletResponse response) {

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

        if(request.getSession().getAttribute("account") == null ||
                !request.getSession().getAttribute("account").equals(request.getParameter("accountname"))) return new ModelAndView("redirect:/login");

        System.out.println("Session is " + request.getRequestedSessionId());

        if(request.getParameter("accountname") == null) {
            System.out.println("没有登陆");
            return new ModelAndView("redirect:/login");
        }

        /*Test*/
        /*System.out.println(loginUser);*/
        List<Contacts> contactsList = contactsDAO.list(request.getParameter("accountname"));
        System.out.println("size is: " + contactsList.size());
        /*System.out.println(contactsList.get(0).getName());
        System.out.println("contactid is: " + contactsList.get(0).getContactid());*/
        /*END*/

        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("contacts", contactsList);

        return modelAndView;
    }

    /*Test 控制器间跳转*/
    @RequestMapping("/redirect")
    public String redirect() {
        return "redirect:home";
    }
    /*END*/
}
