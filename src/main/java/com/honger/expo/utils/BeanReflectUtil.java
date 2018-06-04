package com.honger.expo.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by chenjian on 2018/4/19.
 */
public class BeanReflectUtil {
    public static <S, T> void beanSet(S s, T t) throws InvocationTargetException, IllegalAccessException {
        Method[] srcMethods = s.getClass().getMethods();
        Method[] tarMethods = t.getClass().getMethods();

        for (Method m : tarMethods) {
            String name = m.getName();
            if (name.contains("set")) {
                String substring = name.substring(3);
                String tmpGetMethod = "get" + substring;
                for (Method m1 : srcMethods) {
                    if (m1.getName().equals(tmpGetMethod)) {
                        m.invoke(t, m1.invoke(s));
                        break;
                    }
                }
            }
        }
    }
}
