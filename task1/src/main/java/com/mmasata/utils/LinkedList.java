package com.mmasata.utils;

import com.mmasata.exception.NullValueException;
import com.mmasata.exception.RemoveFromEmptyListException;
import com.mmasata.exception.ValueNotFoundException;

/**
 * LinkedList is a class that implements the List interface. It represents a linked list data structure
 * where elements are stored in individual nodes that are connected by references.
 *
 * @param <T> the type of elements stored in the linked list
 */
public class LinkedList<T> implements List<T> {

    private Node<T> head = null;
    private Node<T> tail = null;
    private int size = 0;

    /**
     * Adds a new element to the end of the list.
     *
     * @param t the element to be added to the list
     * @throws NullValueException if the provided element is null
     */
    @Override
    public synchronized void push(T t) {
        if (t == null) {
            throw new NullValueException("Cannot push null value to list");
        }

        var newNode = new Node<>(t);

        //push first element
        if (head == null) {
            head = tail = newNode;
        }
        //push another element
        else {
            tail.setNext(newNode);
            tail = newNode;
        }

        size++;
    }

    /**
     * Removes and returns the last element in the list.
     *
     * @return the last element in the list
     * @throws RemoveFromEmptyListException if the list is empty
     */
    @Override
    public synchronized T pop() {
        // nothing to pop
        if (head == null) {
            throw new RemoveFromEmptyListException("Cannot pop from already empty list");
        }

        Node<T> newTail = head;
        Node<T> oldTail = tail;

        // pop head
        if (size == 1) {
            size = 0;
            head = tail = null;
            return oldTail.getValue();
        }

        // pop tail
        while (newTail.getNext() != tail) {
            newTail = newTail.getNext();
        }

        newTail.setNext(null);
        tail = newTail;
        size--;

        return oldTail.getValue();
    }

    /**
     * Inserts a new element after a specified element in the LinkedList.
     *
     * @param o     the element to be inserted
     * @param after the element after which the new element should be inserted
     * @throws NullValueException     if the provided element is null
     * @throws ValueNotFoundException if the specified element is not found in the LinkedList
     */
    @Override
    public synchronized void insertAfter(T o, T after) {

        if (o == null) {
            throw new NullValueException("Cannot insert null value to list");
        }

        if (after == null || head == null) {
            throw new ValueNotFoundException("Cannot find the value for which we want to add next value");
        }

        Node<T> newNode = new Node<>(o);

        //add after the current tail (set new tail)
        if (tail.getValue().equals(after)) {
            size++;
            tail.setNext(newNode);
            tail = newNode;
            return;
        }

        //search for value in LinkedList
        Node<T> current = head;
        while (current != null && !current.getValue().equals(after)) {
            current = current.getNext();
        }

        // value is not in LinkedList
        if (current == null) {
            throw new ValueNotFoundException("Cannot find the value for which we want to add next value");
        }

        //add new value after found value
        size++;
        Node<T> oldNext = current.getNext();
        current.setNext(newNode);
        newNode.setNext(oldNext);
    }

    /**
     * Returns the size of the LinkedList.
     *
     * @return the number of elements in the LinkedList
     */
    @Override
    public synchronized int size() {
        return size;
    }

    @Override
    public synchronized String toString() {
        var sb = new StringBuilder();
        sb.append("[");

        var current = head;
        while (current != null) {
            if (current != head) {
                sb.append(", ");
            }

            sb.append(current.getValue());
            current = current.getNext();
        }

        sb.append("]");
        return sb.toString();
    }

}
