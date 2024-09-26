package com.interface21;

import com.interface21.core.util.ReflectionUtils;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;

public class HandlerManagementScanner {

    private HandlerManagementScanner() {}

    public static Set<Class<?>> scanHandlerHelper(Class<?> clazz, Class<? extends Annotation> annotation) {
        Reflections reflections = new Reflections(ClasspathHelper.forJavaClassPath());
        return reflections.getTypesAnnotatedWith(annotation, true);
    }

    public static List<Object> scanHandlerHelper1(Class<?> clazz, Class<? extends Annotation> annotation) {
        System.out.println(" ---HandlerManagementScanner.scanHandlerHelper1");
        return scanHandlerHelper(clazz, annotation).stream()
                .map(HandlerManagementScanner::createObject)
                .toList();
    }

    public static <T> List<T> scanSubTypeOf(Class<?> clazz, Class<T> type) {
        System.out.println(" ---HandlerManagementScanner.scanSubTypeOf");
        Reflections reflections = new Reflections(ClasspathHelper.forJavaClassPath());
        return reflections.getSubTypesOf(type).stream()
                .map(HandlerManagementScanner::createObject)
                .map(type::cast)
                .toList();
    }

    private static Object createObject(Class<?> clazz) {
        System.out.println(clazz.getClassLoader().getName() + " ---HandlerManagementScanner.createObject: " + clazz.getName());
        try {
            return ReflectionUtils.accessibleConstructor(clazz).newInstance();
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("기본 생성자가 존재하지 않습니다");
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }
}
