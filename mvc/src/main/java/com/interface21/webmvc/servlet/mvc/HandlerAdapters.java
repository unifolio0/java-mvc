package com.interface21.webmvc.servlet.mvc;

import com.interface21.HandlerManagementManager;
import com.interface21.webmvc.servlet.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class HandlerAdapters {

    private final List<HandlerAdapter> handlerAdapters;

    public HandlerAdapters() {
        this.handlerAdapters = new ArrayList<>();
    }

    public void initialize() {
//        handlerAdapters.addAll(List.of(handlerAdapter));
        HandlerManagementManager handlerManagementManager = HandlerManagementManager.getInstance();
        List<HandlerAdapter> adapters = handlerManagementManager.getHandler(HandlerAdapter.class);
        handlerAdapters.addAll(adapters);
    }

    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HandlerAdapter handlerAdapter = handlerAdapters.stream()
                .filter(adapter -> adapter.supports(handler))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 HandlerAdapter가 없습니다"));
        return handlerAdapter.handle(request, response, handler);
    }
}
