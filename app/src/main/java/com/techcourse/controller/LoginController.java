package com.techcourse.controller;

import com.interface21.webmvc.servlet.mvc.asis.Controller;
import com.techcourse.domain.User;
import com.techcourse.repository.InMemoryUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginController implements Controller {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Override
    public String execute(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
        System.out.println(this.getClass().getClassLoader().getName() + ": LoginController ClassLoader");
        System.out.println(this.getClass().getClassLoader().getName());
        System.out.println(Thread.currentThread().threadId() + "----LoginController. Thread Id");
        System.out.println(Thread.currentThread().getContextClassLoader().getName() + " ---LoginController. Thread ClassLoader");
        System.out.println(Reflections.class.getClassLoader().getName() + " ---LoginController. Reflections.class.getClassLoader");
        if (UserSession.isLoggedIn(req.getSession())) {
            return "redirect:/index.jsp";
        }

//        System.out.println("LoginController");
//        for (User u : InMemoryUserRepository.database.values()) {
//            System.out.println(u);
//        }
        return InMemoryUserRepository.findByAccount(req.getParameter("account"))
                .map(user -> {
                    log.info("User : {}", user);
                    return login(req, user);
                })
                .orElse("redirect:/401.jsp");
    }

    private String login(final HttpServletRequest request, final User user) {
        if (user.checkPassword(request.getParameter("password"))) {
            final var session = request.getSession();
            session.setAttribute(UserSession.SESSION_KEY, user);
            return "redirect:/index.jsp";
        }
        return "redirect:/401.jsp";
    }
}
