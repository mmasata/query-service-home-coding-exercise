package com.mmasata.utils;

import com.mmasata.exception.NullValueException;
import com.mmasata.exception.RemoveFromEmptyListException;
import com.mmasata.exception.ValueNotFoundException;

public class LinkedList<T> implements List<T> {

    private Node<T> head = null;
    private Node<T> tail = null;
    private int size = 0;

    @Override
    public void push(T t) {
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

    @Override
    public T pop() {
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

    @Override
    public void insertAfter(T o, T after) {

        if (o == null) {
            throw new NullValueException("Cannot insert new value to list");
        }

        if (after == null) {
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

        //add after some value in linked list
        Node<T> current = head;
        while (current != null && !current.getValue().equals(after)) {
            current = current.getNext();
        }

        if (current == null) {
            throw new ValueNotFoundException("Cannot find the value for which we want to add next value");
        }

        size++;
        Node<T> oldNext = current.getNext();
        current.setNext(newNode);
        newNode.setNext(oldNext);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
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
