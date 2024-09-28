package com.techcourse.controller;

import com.interface21.context.stereotype.Controller;
import com.interface21.web.bind.annotation.RequestMapping;
import com.interface21.web.bind.annotation.RequestMethod;
import com.interface21.webmvc.servlet.ModelAndView;
import com.interface21.webmvc.servlet.view.JspView;
import com.techcourse.domain.User;
import com.techcourse.repository.InMemoryUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.concurrent.atomic.AtomicLong;
import org.reflections.Reflections;

@Controller
public class RegisterController {

    private final AtomicLong atomicLong = new AtomicLong(2);

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView save(HttpServletRequest req, HttpServletResponse res) {
        System.out.println(this.getClass().getClassLoader().getName());
        System.out.println(Thread.currentThread().threadId() + "----RegisterController.POST Thread Id");
        System.out.println(Thread.currentThread().getContextClassLoader().getName() + " ---RegisterController.POST Thread ClassLoader");
        System.out.println(Reflections.class.getClassLoader().getName() + " ---RegisterController.POST Reflections.class.getClassLoader");
        final var user = new User(
                atomicLong.getAndIncrement(),
                req.getParameter("account"),
                req.getParameter("password"),
                req.getParameter("email")
        );
        InMemoryUserRepository.save(user);

        return new ModelAndView(new JspView("redirect:/index.jsp"));
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView show(HttpServletRequest req, HttpServletResponse res) {
        System.out.println(this.getClass().getClassLoader().getName());
        System.out.println(Thread.currentThread().threadId() + "----RegisterController.GET Thread Id");
        System.out.println(Thread.currentThread().getContextClassLoader().getName() + " ---RegisterController.GET Thread ClassLoader");
        System.out.println(Reflections.class.getClassLoader().getName() + " ---RegisterController.GET Reflections.class.getClassLoader");
        return new ModelAndView(new JspView("/register.jsp"));
    }
}
