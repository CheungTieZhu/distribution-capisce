package com.capisce.implement.utils;

import com.capisce.entity.LinkedListNode;

public class LinkedListReverse {
    public static void main(String[] arg) {
        LinkedListNode root = new LinkedListNode(1);
        LinkedListNode node1 = new LinkedListNode(2);
        LinkedListNode node2 = new LinkedListNode(3);
        LinkedListNode node3 = new LinkedListNode(4);
        LinkedListNode node4 = new LinkedListNode(5);
        root.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        LinkedListNode result = recursion(root);
    }

    private static LinkedListNode recursion(LinkedListNode root) {
        if (root == null || root.getNext() == null) {
            return root;
        }
        LinkedListNode temp = recursion(root.getNext());
        root.getNext().setNext(root);
        root.setNext(null);
        return temp;
    }
}
