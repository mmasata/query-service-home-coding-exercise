package com.mmasata.utils;

/**
 * Interface representing a list.
 *
 * @param <T> the type of elements in the list
 */
public interface List<T> {

    /**
     * Adds a new element to the end of the list.
     *
     * @param t the element to be added to the list
     */
    void push(T t);

    /**
     * Removes and returns the last element in the list.
     *
     * @return the last element in the list
     */
    T pop();

    /**
     * Inserts a new element after a specified element in the LinkedList.
     *
     * @param o     the element to be inserted
     * @param after the element after which the new element should be inserted
     */
    void insertAfter(T o, T after);

    /**
     * Retrieves the number of elements in the list.
     *
     * @return the number of elements in the list
     */
    int size();
}
