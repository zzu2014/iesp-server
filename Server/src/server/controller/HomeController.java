package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import server.dao.AccountDAO;
import server.dao.ContactsDAO;
import server.model.Account;
import server.model.Contacts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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

        System.out.println(account.getAccountname());
        System.out.println(account.getPassword());

        boolean Result = accountDAO.addAccount(account.getAccountname(), account.getPassword());
        System.out.println(Result);

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
    public ModelAndView loginControl(Account account, HttpServletRequest request, HttpServletResponse response) {
        /*System.out.println(account.getAccountname());
        System.out.println(account.getPassword());*/
        boolean result = accountDAO.checkAccount(account.getAccountname(), account.getPassword());
        System.out.println(result);

        /*if(result) {
            *//*ModelAndView modelAndView = new ModelAndView("logsuccess");
            modelAndView.addObject("accountname", account.getAccountname());*//*
            *//*data = "{\"result\":'" + "Success" + "'}";
            out.print(data);*//*
            ModelMap modelMap = new ModelMap("accountname", account.getAccountname());
            return new ModelAndView("redirect:/home", modelMap);
        }
        else {
            *//*data = "{\"result\":\"" + "用户名不存在或密码错误" + "\"}";
            out.print(data);*//*

            *//*response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(data);*//*


        }*/

        String data = null;
        if(result) {
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

        if(result) {
            ModelMap modelMap = new ModelMap("accountname", account.getAccountname());
            return new ModelAndView("redirect:/home", modelMap);
        }

        return null;
    }

    @RequestMapping("home")
    public String home(HttpServletRequest request) {
        System.out.println(request.getParameter("accountname"));
        return "home";
    }
}
