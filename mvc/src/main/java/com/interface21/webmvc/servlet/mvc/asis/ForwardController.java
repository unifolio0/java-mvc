package com.interface21.webmvc.servlet.mvc.asis;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Objects;

public class ForwardController implements Controller {

    private final String path;

    public ForwardController(final String path) {
//        System.out.println("*****************ForwardController");
//        String[] classpath = System.getProperty("java.class.path").split(";");
//        Arrays.stream(classpath).forEach(System.out::println);
        this.path = Objects.requireNonNull(path);
    }

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) {
//        System.out.println(this.getClass().getClassLoader().getName());
//        System.out.println(Thread.currentThread().threadId() + "----ForwardController. Thread Id");
//        System.out.println(Thread.currentThread().getContextClassLoader().getName() + " ---ForwardController. Thread ClassLoader");
//        System.out.println(Reflections.class.getClassLoader().getName() + " ---ForwardController. Reflections.class.getClassLoader");
        return path;
    }
}
