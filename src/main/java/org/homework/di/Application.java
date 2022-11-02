package org.homework.di;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.SQLSyntaxErrorException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.homework.di.annotation.EntryPoint;
import org.homework.di.annotation.Service;
import org.homework.repository.DatabaseConnector;
import org.reflections.Reflections;

public final class Application {

    private static final Set<Object> BEANS = new LinkedHashSet<>();

    private static final Reflections reflections = new Reflections("org.homework");

    private Application() {
    }


    public static void run() {
        prepareBeans();
        new TableValidator((DatabaseConnector)findBean(DatabaseConnector.class)).validate();
        postConstructInvocation();
    }

    

    private static void postConstructInvocation() {
        for (Object bean : BEANS) {
            for (Method declaredMethod : bean.getClass().getDeclaredMethods()) {
                if (declaredMethod.isAnnotationPresent(EntryPoint.class)) {
                    try {
                        declaredMethod.invoke(bean);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    private static void prepareBeans() {
        Set<Class<?>> services = reflections.getTypesAnnotatedWith(Service.class);
        for (Class<?> service : services) {
            Object bean = findBean(service);
            if (bean == null) {
                try {
                    createBean(service);
                } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static Object createBean(Class<?> service) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Object bean = findBean(service);
        if (bean != null) {
            return bean;
        }
        Constructor<?>[] constructors = service.getDeclaredConstructors();
        Constructor<?> constructor;
        if (constructors.length != 0) {
            constructor = constructors[0];
        } else {
            constructor = findSubTypeConstructor(service);
        }

        if (constructor == null) {
            throw new IllegalStateException("Cannot find constructor for type " + service.getCanonicalName());
        }

        if (constructor.getParameters().length == 0) {
            bean = constructor.newInstance();
        } else {
            bean = createBeanWithParameters(constructor);
        }
        BEANS.add(bean);
        return bean;
    }

    private static Constructor<?> findSubTypeConstructor(Class<?> service) {
        Set<Class<?>> subtypes = reflections.getSubTypesOf((Class<Object>) service);
        for (Class<?> subtype : subtypes) {
            if (subtype.isAnnotationPresent(Service.class)) {
                return subtype.getConstructors()[0];
            }
        }
        return null;
    }

    private static Object createBeanWithParameters(Constructor<?> constructor) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Parameter[] parameters = constructor.getParameters();
        Object[] parametersValues = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            Object bean = createBean(parameters[i].getType());
            parametersValues[i] = bean;
        }
        return constructor.newInstance(parametersValues);
    }

    private static Object findBean(Class<?> service) {
        for (Object bean : BEANS) {
            if (service.isInstance(bean)) {
                return bean;
            }
        }
        return null;
    }

}
