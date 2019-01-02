package com.example.web.controller;

import com.example.web.model.User;
import com.example.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Controller
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping("")
    public String index(HttpServletResponse response) {
        return response.encodeRedirectURL("/index");
    }

    @RequestMapping("/index")
    public String home(Model model) {
        return "index";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String gotoRegister() {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Model model, @ModelAttribute(value = "user") User user,
                           HttpServletResponse response) throws Exception {
        String result = service.register(user);
        //model.addAttribute("result", result);
        if(!result.equals("注册成功")){
            response.sendRedirect("/register");
            return "register";
        }
        response.sendRedirect("/yanz");
        return "yanz";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String gotoLogin() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model, @ModelAttribute(value = "user") User user,
                        HttpServletResponse response) throws IOException {
        String result = service.login(user);
        //model.addAttribute("result", result);
        if (result.equals("登陆成功")) {
            //session.setAttribute("user", user.getUser_email());
            response.addCookie(new Cookie("user_email", user.getUser_email()));
            response.sendRedirect("/list");
            return "list";
        }
        response.sendRedirect("/login");
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String loginOut(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws IOException {
        //session.removeAttribute("user");
        Cookie cookie = new Cookie("user_email", null); // Not necessary, but saves bandwidth.
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0); // Don't set to -1 or it will become a session cookie!
        response.addCookie(cookie);
        response.sendRedirect("/index");
        return "index";
    }

    @RequestMapping(value = "/logoff", method = RequestMethod.GET)
    public String loginOff(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws IOException {
        Cookie cookies[]=request.getCookies();
        String userEmail = null;
        userEmail = getEmail(cookies);

        Cookie cookie = new Cookie("user_email", null); // Not necessary, but saves bandwidth.
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0); // Don't set to -1 or it will become a session cookie!
        response.addCookie(cookie);
        response.sendRedirect("/index");

        service.deleteUser(userEmail);
        return "index";
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String gotoInfo(Model model,HttpServletResponse response,HttpServletRequest request) throws IOException {
        //从数据库获取用户信息并展示
        Cookie cookie[]=request.getCookies();
        String userEmail = null;
        userEmail = getEmail(cookie);

        //Object userSession = session.getAttribute("user");
        if(userEmail != null){
            //String userEmail = userSession.toString();
            User user = service.display(userEmail);
            model.addAttribute("user",user);
            return "info";
        }
        response.sendRedirect("/login");
        return "login";
    }

    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public String info(Model model, @ModelAttribute(value = "user") User user, HttpServletResponse response) throws IOException {
        service.update(user);
        response.sendRedirect("/info");
        return "info";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String gotoList(HttpServletResponse response,HttpServletRequest request) throws IOException {
        Cookie cookie[]=request.getCookies();
        String userEmail = null;
        userEmail = getEmail(cookie);
        if(userEmail != null){
            return "list";
        }
        response.sendRedirect("/login");
        return "login";
    }

    @RequestMapping(value = "/single", method = RequestMethod.GET)
    public String gotosingle(HttpServletResponse response,HttpServletRequest request) throws IOException {
        Cookie cookie[]=request.getCookies();
        String userEmail = null;
        userEmail = getEmail(cookie);
        if(userEmail != null){
            return "single";
        }
        response.sendRedirect("/login");
        return "login";
    }

    @RequestMapping(value = "/yanz", method = RequestMethod.GET)
    public String gotoYanz() {
        return "yanz";
    }

    @RequestMapping(value = "/yanz", method = RequestMethod.POST)
    public String yanz(Model model, @ModelAttribute(value = "user") User user, HttpServletResponse response) throws Exception {
        String result = service.yanz(user);
        if(!result.equals("验证成功")){
            return "yanz";
        }

        response.addCookie(new Cookie("user_email", user.getUser_email()));
        response.sendRedirect("/index");
        return "index";
    }

    private String getEmail(Cookie[] cookie) {
        Cookie cook;
        String userEmail = null;
        if (cookie != null) {
            for (int i = 0; i < cookie.length; i++) {
                cook = cookie[i];
                if(cook.getName().equalsIgnoreCase("user_email"))
                    userEmail=cook.getValue();
            }
        }
        return userEmail;
    }

}
