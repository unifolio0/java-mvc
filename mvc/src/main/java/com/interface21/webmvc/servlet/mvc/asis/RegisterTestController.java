package com.interface21.webmvc.servlet.mvc.asis;

import com.interface21.context.stereotype.Controller;
import com.interface21.web.bind.annotation.RequestMapping;
import com.interface21.web.bind.annotation.RequestMethod;
import com.interface21.webmvc.servlet.ModelAndView;
import com.interface21.webmvc.servlet.view.JspView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.concurrent.atomic.AtomicLong;
import org.reflections.Reflections;

@Controller
public class RegisterTestController {

    private final AtomicLong atomicLong = new AtomicLong(2);

    @RequestMapping(value = "/registertest", method = RequestMethod.GET)
    public ModelAndView show(HttpServletRequest req, HttpServletResponse res) {
//        System.out.println(this.getClass().getClassLoader().getName());
//        System.out.println(Thread.currentThread().threadId() + "----RegisterTestController. Thread Id");
//        System.out.println(Thread.currentThread().getContextClassLoader().getName() + " ---RegisterTestController. Thread ClassLoader");
//        System.out.println(Reflections.class.getClassLoader().getName() + " ---RegisterTestController. Reflections.class.getClassLoader");
        return new ModelAndView(new JspView("/register.jsp"));
    }
}
