package com.techcourse.controller;

import com.interface21.webmvc.servlet.mvc.asis.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LogoutController implements Controller {

    @Override
    public String execute(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
//        System.out.println(this.getClass().getClassLoader().getName());
//        System.out.println(Thread.currentThread().threadId() + "----LogoutController. Thread Id");
//        System.out.println(Thread.currentThread().getContextClassLoader().getName() + " ---LogoutController. Thread ClassLoader");
//        System.out.println(Reflections.class.getClassLoader().getName() + " ---LogoutController. Reflections.class.getClassLoader");
        final var session = req.getSession();
        session.removeAttribute(UserSession.SESSION_KEY);
        return "redirect:/";
    }
}
