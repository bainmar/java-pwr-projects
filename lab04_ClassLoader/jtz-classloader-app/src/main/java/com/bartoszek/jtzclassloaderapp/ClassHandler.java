package com.bartoszek.jtzclassloaderapp;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;

import processing.StatusListener;


public class ClassHandler {
    private static List<Class<?>> list;

    public static List<String> getProcessorInfo() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Object o, r;
        Method m;
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            o = list.get(i).getDeclaredConstructor().newInstance();
            m = list.get(i).getMethod("getInfo");
            r = m.invoke(o);
            stringList.add((String) r);
        }
        return stringList;
    }

    public static void runTask(int i, String text, StatusListener listener) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchMethodException, SecurityException {
        Object o, r;
        Method m;
        o = list.get(i).getDeclaredConstructor().newInstance();
        m = list.get(i).getMethod("submitTask", String.class, StatusListener.class);
        r = m.invoke(o, new Object[]{text, listener});
    }

    public static String getResult(int i) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchMethodException, SecurityException {
        Object o, r;
        Method m;
        Class<?>[] argTypes = new Class[]{};
        o = list.get(i).getDeclaredConstructor().newInstance();
        m = list.get(i).getMethod("getResult", argTypes);
        return (String) (r = m.invoke(o, new Object[]{}));
    }
}