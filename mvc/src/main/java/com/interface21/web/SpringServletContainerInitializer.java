package com.interface21.web;

import com.interface21.core.util.ReflectionUtils;
import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HandlesTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.reflections.Reflections;

@HandlesTypes(WebApplicationInitializer.class)
public class SpringServletContainerInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> webAppInitializerClasses, ServletContext servletContext)
            throws ServletException {
        System.out.println(this.getClass().getClassLoader().getName());
        System.out.println(Thread.currentThread().threadId() + "----SpringServletContainerInitializer. Thread Id");
        System.out.println(Thread.currentThread().getContextClassLoader().getName() + " ---SpringServletContainerInitializer. Thread ClassLoader");
        System.out.println(Reflections.class.getClassLoader().getName() + " ---SpringServletContainerInitializer. Reflections.class.getClassLoader");
        final List<WebApplicationInitializer> initializers = new ArrayList<>();

        if (webAppInitializerClasses != null) {
            for (Class<?> waiClass : webAppInitializerClasses) {
                try {
                    initializers.add((WebApplicationInitializer)
                            ReflectionUtils.accessibleConstructor(waiClass).newInstance());
                } catch (Throwable ex) {
                    throw new ServletException("Failed to instantiate WebApplicationInitializer class", ex);
                }
            }
        }

        if (initializers.isEmpty()) {
            servletContext.log("No Spring WebApplicationInitializer types detected on classpath");
            return;
        }

        for (WebApplicationInitializer initializer : initializers) {
            initializer.onStartup(servletContext);
        }
    }
}
