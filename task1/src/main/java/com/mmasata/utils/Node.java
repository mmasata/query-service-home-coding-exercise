package com.mmasata.utils;

/**
 * Represents a Node in a linked list.
 *
 * @param <T> the type of value stored in the Node
 */
public class Node<T> {

    private final T value;

    private Node<T> next;

    /**
     * Constructs a new Node with the specified value.
     *
     * @param value the value to be stored in the Node
     */
    public Node(T value) {
        this.value = value;
    }

    /**
     * Returns the value stored in this Node.
     *
     * @return the value stored in this Node
     */
    public T getValue() {
        return value;
    }

    /**
     * Returns the next Node in the linked list.
     *
     * @return the next Node in the linked list
     */
    protected Node<T> getNext() {
        return next;
    }

    /**
     * Sets the next Node in the linked list.
     *
     * @param next the next Node to be set
     */
    protected void setNext(Node<T> next) {
        this.next = next;
    }

}
