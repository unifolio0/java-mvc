package com.interface21.webmvc.servlet.mvc;

import com.interface21.HandlerManagementManager;
import com.interface21.webmvc.servlet.mvc.tobe.HandlerMapping;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class HandlerMappings {

    private final List<HandlerMapping> handlerMappings;

    public HandlerMappings() {
        this.handlerMappings = new ArrayList<>();
    }

    public void initialize() {
        System.out.println(Thread.currentThread().getContextClassLoader().getName() + "----HandlerMappings. Thread ClassLoader");
        System.out.println(this.getClass().getClassLoader().getName() + "----HandlerMappings. this.getClass().getClassLoader().getName()");
//        handlerMappings.addAll(List.of(handlerMapping));
//        handlerMappings.forEach(HandlerMapping::initialize);
        HandlerManagementManager handlerManagementManager = HandlerManagementManager.getInstance();
        List<HandlerMapping> mappings = handlerManagementManager.getHandler(HandlerMapping.class);
        mappings.forEach(handlerMapping -> {
//            System.out.println(handlerMapping.getClass().getClassLoader().getName() + " ---HandlerMappings: " + handlerMapping.getClass().getName());
            handlerMapping.initialize();
        });
        handlerMappings.addAll(mappings);
    }

    public Object getHandler(HttpServletRequest request) {
        for (HandlerMapping handlerMapping : handlerMappings) {
            try {
                return handlerMapping.getHandler(request);
            } catch (IllegalArgumentException e) {}
        }
        throw new IllegalArgumentException("일치하는 handler가 없습니다");
    }
}
