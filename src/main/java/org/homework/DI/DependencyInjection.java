package org.homework.DI;
import org.homework.annotation.Service;
import org.homework.annotation.StartPoint;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

public class DependencyInjection {

    private static final Reflections reflections = new Reflections("org.homework");
    public static void start(){

        Set<Class<?>> startClass = reflections.getTypesAnnotatedWith(StartPoint.class);
        Set<Class<?>> services = reflections.getTypesAnnotatedWith(Service.class);

    for(Class classs:startClass){
        Method[] meth = classs.getMethods();
        Object temp = null;
        try {
            temp = classs.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        try {
            meth[0].invoke(temp,null);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    }

}
