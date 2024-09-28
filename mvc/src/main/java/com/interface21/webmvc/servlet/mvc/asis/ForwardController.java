package com.interface21.webmvc.servlet.mvc.asis;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Objects;
import org.reflections.Reflections;

public class ForwardController implements Controller {

    private final String path;

    public ForwardController(final String path) {
        this.path = Objects.requireNonNull(path);
    }

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) {
        System.out.println(this.getClass().getClassLoader().getName());
        System.out.println(Thread.currentThread().threadId() + "----ForwardController. Thread Id");
        System.out.println(Thread.currentThread().getContextClassLoader().getName() + " ---ForwardController. Thread ClassLoader");
        System.out.println(Reflections.class.getClassLoader().getName() + " ---ForwardController. Reflections.class.getClassLoader");
        return path;
    }
}
