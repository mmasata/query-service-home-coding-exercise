package com.mmasata.utils;

public interface List<T> {

    void push(T t);

    T pop();

    void insertAfter(T o, T after);
}
