package com.capisce.entity;

public class LinkedListNode {
    private LinkedListNode next;
    private int value;

    public LinkedListNode(int value) {
        this.value = value;
    }

    public void setNext(LinkedListNode next) {
        this.next = next;
    }

    public LinkedListNode getNext() {
        return this.next;
    }
}
