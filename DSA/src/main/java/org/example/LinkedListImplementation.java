package org.example;

public class LinkedListImplementation {

    private static class Node {
        int val;
        Node next;

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }

        public Node(int val) {
            this.val = val;
        }
    }

    static Node head;

    public static void addFront(int num) {
        Node node = new Node(num);

        node.next = head;
        head = node;
    }

    public static void insertAtLast(int num) {
        Node node = new Node(num);
        if (head == null) {
            head = node;
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }

    public static int lengthOfLL(Node head) {
        int length = 0;
        Node temp = head;
        while (temp != null && temp.next != null) {
            temp = temp.next;
            length++;
        }
        return length;
    }

    public static void printLL(Node head) {
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    static void main() {
        /*
        Node node1 = new Node(10);
        Node node2 = new Node(20);
        Node node3 = new Node(30);
        Node node4 = new Node(40);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        printLL(node1);
         */

        /*
        addFront(10);
        addFront(20);
        addFront(30);
        addFront(40);
        printLL(head);
         */

        /*
        insertAtLast(10);
        insertAtLast(20);
        insertAtLast(30);
        insertAtLast(40);
        printLL(head);
         */

        Node node1 = new Node(10);
        Node node2 = new Node(20);
        Node node3 = new Node(30);
        Node node4 = new Node(40);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        printLL(node1);
        System.out.println("Length of Linked List: " + lengthOfLL(node1));
    }

}
