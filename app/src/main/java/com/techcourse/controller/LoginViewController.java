package com.techcourse.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.interface21.webmvc.servlet.mvc.asis.Controller;

public class LoginViewController implements Controller {

    private static final Logger log = LoggerFactory.getLogger(LoginViewController.class);

    @Override
    public String execute(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
        System.out.println(this.getClass().getClassLoader().getName());
        System.out.println(Thread.currentThread().threadId() + "----LoginViewController. Thread Id");
        System.out.println(Thread.currentThread().getContextClassLoader().getName() + " ---LoginViewController. Thread ClassLoader");
        System.out.println(Reflections.class.getClassLoader().getName() + " ---LoginViewController. Reflections.class.getClassLoader");
        return UserSession.getUserFrom(req.getSession())
                .map(user -> {
                    log.info("logged in {}", user.getAccount());
                    return "redirect:/index.jsp";
                })
                .orElse("/login.jsp");
    }
}
