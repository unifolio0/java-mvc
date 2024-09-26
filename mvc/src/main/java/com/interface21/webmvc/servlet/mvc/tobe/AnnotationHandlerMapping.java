package com.interface21.webmvc.servlet.mvc.tobe;

import com.interface21.HandlerManagementManager;
import com.interface21.context.stereotype.Controller;
import com.interface21.web.bind.annotation.RequestMethod;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Set;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnnotationHandlerMapping implements HandlerMapping {

    private static final Logger log = LoggerFactory.getLogger(AnnotationHandlerMapping.class);

    private final HandlerExecutions handlerExecutions;

    public AnnotationHandlerMapping() {
        this.handlerExecutions = new HandlerExecutions();
    }

    @Override
    public void initialize() {
        System.out.println(this.getClass().getClassLoader().getName());
        System.out.println("new Reflections(ClasspathHelper.forJavaClassPath())에서 만든거 넣어주기\n");
        Reflections reflections = new Reflections(ClasspathHelper.forJavaClassPath());

        Set<Class<?>> controllerClasses = reflections.getTypesAnnotatedWith(Controller.class);
        controllerClasses.stream()
                .map(clazz -> {
                    System.out.println(clazz.getClassLoader().getName() + " --- AnnotationHandlerMapping: " + clazz.getName());
                    return clazz.getDeclaredMethods();
                })
                .forEach(handlerExecutions::addHandlerExecution);

        System.out.println("HandlerManagementManager에서 만든거 넣어주기");
        HandlerManagementManager handlerManagementManager = HandlerManagementManager.getInstance();
        handlerManagementManager.getAnnotationHandler(Controller.class).stream()
                .map(object -> {
                    System.out.println(object.getClass().getClassLoader().getName() + " ---AnnotationHandlerMapping: " + object.getClass().getName());
                    return object.getClass().getDeclaredMethods();
                })
                .forEach(handlerExecutions::addHandlerExecution);

        log.info("Initialized AnnotationHandlerMapping!");
    }

    @Override
    public Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String requestMethod = request.getMethod();
        HandlerKey handlerKey = new HandlerKey(requestURI, RequestMethod.valueOf(requestMethod));
        if (handlerExecutions.containsHandlerKey(handlerKey)) {
            return handlerExecutions.get(handlerKey);
        }
        throw new IllegalArgumentException("일치하는 handlerkey가 없습니다");
    }
}
