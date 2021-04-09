package cn.demo.utils;

import org.springframework.beans.BeanUtils;

public interface Convert<S, T> {

    default T doForward(S s, Class<T> clazz) {
        try {
            T t = clazz.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(s, t);
            return t;
        } catch (Exception e) {
            throw new AssertionError("正向转化失败方法!");
        }
    }

    default S doBackward(T t, Class<S> clazz) {
        try {
            S s = clazz.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(t, s);
            return s;
        } catch (Exception e) {
            throw new AssertionError("逆向转化失败方法!");
        }
    }
}