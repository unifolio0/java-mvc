package com.interface21.webmvc.servlet.mvc.tobe;

import com.interface21.webmvc.servlet.mvc.HandlerManagement;
import jakarta.servlet.http.HttpServletRequest;

@HandlerManagement
public interface HandlerMapping {

    void initialize();

    Object getHandler(HttpServletRequest request);
}
