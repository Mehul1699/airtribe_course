package org.example;

public class LinkedListQuestions {

    private static class Node {
        int val;
        Node next;

        Node(int val, Node node) {
            this.val = val;
            this.next = node;
        }

        Node(int val) {
            this.val = val;
        }
    }

    // Leetcode - Easy - 206. Reverse Linked List
    private static Node reverseLinkedList(Node head) {
        Node prev = null;
        Node curr = head;
        Node future;
        while (curr != null) {
            future = curr.next;
            curr.next = prev;
            prev = curr;
            curr = future;
        }
        return prev;
    }

    // Leetcode - Easy - 876. Middle of the Linked List - Will work fine for odd length and will return first second in case of Even length
    private static Node middleNode(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Same Middle Node code but this will return first middle in case of even length - Preferable
    private static Node middleNode2(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Leetcode - Easy - 234. Palindrome Linked List
    private static boolean isPalindrome(Node head) {
        // Step1 - Find the middle
        Node middle = middleNode2(head);

        //Step2 - Break into 2 LL
        Node ll2 = middle.next;
        middle.next = null;

        // Step3 - Reverse the second list
        Node h1 = head;
        Node h2 = reverseLinkedList(ll2);
        while (h1 != null && h2 != null) {
            if (h1.val != h2.val)
                return false;
            h1 = h1.next;
            h2 = h2.next;
        }
        return true;
    }

    // Leetcode - Medium - 19. Remove Nth Node From End of List
    private static Node removeNthElement(Node head, int n) {
        Node fast = head;
        Node slow = head;
        while (n != 0) {
            fast = fast.next;
            n--;
        }
        if (fast == null)
            return head.next;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    // Leetcode - Medium - 143. Reorder List
    private static void reorderList(Node head) {
        // Step1 - Find middle
        Node middle = middleNode2(head);
        // Step2 - Break into two separate Linked list
        Node ll2 = middle.next;
        middle.next = null;

        Node h1 = head;
        // Step3 - Reverse the second list
        Node h2 = reverseLinkedList(ll2);
        // Step4 - Keep track of next and previous element and reorder the list
        Node f1;
        Node f2;
        while (h1 != null && h2 != null) {
            f1 = h1.next;
            f2 = h2.next;
            h1.next = h2;
            h2.next = f1;
            h1 = f1;
            h2 = f2;
        }
        printLL(head);
    }

    private static void printLL(Node head) {
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    static void main() {
        Node node1 = new Node(10);
        Node node2 = new Node(20);
        Node node3 = new Node(30);
        Node node4 = new Node(40);
        Node node5 = new Node(50);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        System.out.println("Actual Linked List: ");
//        printLL(node1);
        /*
        Node reverse = reverseLinkedList(node1);
        System.out.println("Reverse Linked List: ");
        printLL(reverse);
         */

        /*
        System.out.println("Middle Node: " + middleNode(node1).val);
        System.out.println("Middle Node 2: " + middleNode2(node1).val);
         */


        Node a = new Node(10);
        Node b = new Node(20);
        Node c = new Node(30);
        Node d = new Node(20);
        Node e = new Node(10);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        /*
        System.out.println("Is the Linked List: ");
        printLL(a);
        System.out.println("Palindrome? " + isPalindrome(a));
         */

        /*
        printLL(a);
        int n = 2;
        System.out.println("Linked List after removing: " + n + " element from end");
        Node nthRemoved = removeNthElement(a, n);
        printLL(nthRemoved);
         */

        printLL(node1);
        System.out.println("Linked List after reordering");
        reorderList(node1);
    }

}
