package org.example;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class LinkedList2Questions {
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

    // Leetcode - Medium - 328. Odd Even Linked List

    /**
     * Given the head of a singly linked list, group all the nodes with odd indices together
     * followed by the nodes with even indices, and return the reordered list.
     * The first node is considered odd, and the second node is even, and so on.
     * Note that the relative order inside both the even and odd groups should remain as it was in the input.
     * You must solve the problem in O(1) extra space complexity and O(n) time complexity.
     * <p>
     * Example 1:
     * Input: head = [1,2,3,4,5]
     * Output: [1,3,5,2,4]
     * <p>
     * Example 2:
     * Input: head = [2,1,3,5,6,4,7]
     * Output: [2,3,6,7,1,5,4]
     */
    static Node oddEvenList(Node head) {
        Node oHead = head;
        Node oTail = head;

        if (head == null || head.next == null) {
            return head;
        }
        Node eHead = head.next;
        Node eTail = head.next;
        Node temp = head.next.next;
        int c = 2;
        while (temp != null) {
            c++;
            if (c % 2 == 0) {
                eTail.next = temp;   // Link the tail node with next even node
                eTail = eTail.next;  // Move even tail to the next even node, basically the one we just linked
            } else {
                oTail.next = temp;
                oTail = oTail.next;
            }
            temp = temp.next;
        }
        oTail.next = eHead;
        eTail.next = null;
        return oHead;
    }

    private static void printLL(Node head) {
        while (head != null) {
//            System.out.println(head.val);
            if (head.next != null) {
                System.out.print(head.val + ", ");
            } else {
                System.out.print(head.val);
            }
            head = head.next;
        }
    }

    // GFG : Medium : Segregate Evens and Odds in a Linked List

    /**
     * Given a link list, modify the list such that all the even numbers appear before all the odd numbers
     * in the modified list. The order of appearance of numbers within each segregation should be the
     * same as that in the original list.
     * NOTE: Don't create a new linked list, instead rearrange the provided one.
     * <p>
     * Examples:
     * Input: Linked list: 17->15->8->9->2->4->6
     * Output: 8->2->4->6->17->15->9
     * <p>
     * Input: Linked List: 1 -> 3 -> 5 -> 7
     * Output: 1->3->5->7
     */
    static Node segregateNodes(Node head) {
        Node eHead = new Node(-1);
        Node oHead = new Node(-1);

        Node eTail = eHead;
        Node oTail = oHead;
        Node temp = head;
        while (temp != null) {
            int val = temp.val;
            if (val % 2 == 0) {
                eTail.next = temp;    // Set the next node in even List as current node
                eTail = eTail.next;   // Move the tail to this node
            } else {
                oTail.next = temp;
                oTail = oTail.next;
            }
            temp = temp.next;
        }
        eTail.next = oHead.next;  // oHead is -1. So, oHead.next will be the actual head node
        oTail.next = null;
        return eHead.next;   // eHead is -1, So, eHead.next will be the actual head node
    }

    // Leetcode - Easy - 21. Merge Two Sorted Lists

    /**
     * You are given the heads of two sorted linked lists list1 and list2.
     * Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.
     * Return the head of the merged linked list.
     * <p>
     * Example 1:
     * Input: list1 = [1,2,4], list2 = [1,3,4]
     * Output: [1,1,2,3,4,4]
     * <p>
     * Example 2:
     * Input: list1 = [], list2 = []
     * Output: []
     * <p>
     * Example 3:
     * Input: list1 = [], list2 = [0]
     * Output: [0]
     */
    static Node mergeTwoSortedList(Node n1, Node n2) {
        Node dummy = new Node(-1);
        Node d = dummy;
        while (n1 != null && n2 != null) {
            if (n1.val > n2.val) {
                d.next = n2;
                n2 = n2.next;
            } else {
                d.next = n1;
                n1 = n1.next;
            }
            d = d.next;
        }
        if (n1 != null) {
            d.next = n1;
        }
        if (n2 != null) {
            d.next = n2;
        }
        return dummy.next;
    }

    static Node middleNode(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Leetcode - Medium - 148. Sort List

    /**
     * Given the head of a linked list, return the list after sorting it in ascending order.
     * <p>
     * Example 1:
     * Input: head = [4,2,1,3]
     * Output: [1,2,3,4]
     * <p>
     * Example 2:
     * Input: head = [-1,5,3,4,0]
     * Output: [-1,0,3,4,5]
     * <p>
     * Example 3:
     * Input: head = []
     * Output: []
     */
    static Node sortList(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        // Step 1: Find middle
        // Will break the list into two half, sort and merge them
        Node middle = middleNode(head);

        Node ll2 = middle.next;
        middle.next = null;
        Node ll1 = head;

        // Step 2: Sort both halves
        Node ans1 = sortList(ll1);
        Node ans2 = sortList(ll2);

        // Step 3: Merge both responses and return
        return mergeTwoSortedList(ans1, ans2);
    }

    // Leetcode - Medium - 146. LRU Cache - V.V.Important

    /**
     * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
     * <p>
     * Implement the LRUCache class:
     * <p>
     * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
     * int get(int key) Return the value of the key if the key exists, otherwise return -1.
     * void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache.
     * If the number of keys exceeds the capacity from this operation, evict the least recently used key.
     * The functions get and put must each run in O(1) average time complexity.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input
     * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
     * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
     * Output
     * [null, null, null, 1, null, -1, null, -1, 3, 4]
     * <p>
     * Explanation
     * LRUCache lRUCache = new LRUCache(2);
     * lRUCache.put(1, 1); // cache is {1=1}
     * lRUCache.put(2, 2); // cache is {1=1, 2=2}
     * lRUCache.get(1);    // return 1
     * lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
     * lRUCache.get(2);    // returns -1 (not found)
     * lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
     * lRUCache.get(1);    // return -1 (not found)
     * lRUCache.get(3);    // return 3
     * lRUCache.get(4);    // return 4
     */
    // We will be using doubly linked list in this as we need linkage both sides
    private static class DoublyNode {
        int key;
        int val;
        DoublyNode next;
        DoublyNode prev;

        DoublyNode(int key, int val) {
            this.key = key;
            this.val = val;
        }

        DoublyNode() {
        }

        @Override
        public String toString() {
            return "DoublyNode{" +
                    "key=" + key +
                    ", val=" + val +
                    '}';
        }
    }

    private static DoublyNode headNode;
    private static DoublyNode tailNode;
    private static int cap;
    private static LinkedHashMap<Integer, DoublyNode> hm;

    static void addFirst(DoublyNode node) {
        DoublyNode currentFirst = headNode.next;   // Assuming head node is -1
        node.next = currentFirst;
        currentFirst.prev = node;
        headNode.next = node;
        node.prev = headNode;
        hm.put(node.key, node);
    }

    static void removeNode(DoublyNode node) {
        DoublyNode prevNode = node.prev;
        DoublyNode nextNode = node.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        hm.remove(node.key);
    }

    static void put(int key, int value) {
        if (hm.containsKey(key)) {
            DoublyNode node = hm.get(key);
            node.val = value;
            removeNode(node);
            addFirst(node);
        } else {
            if (hm.size() == cap) {
                DoublyNode lastNode = tailNode.prev;
                removeNode(lastNode);
                DoublyNode newEntry = new DoublyNode(key, value);
                addFirst(newEntry);
            } else {
                DoublyNode newEntry = new DoublyNode(key, value);
                addFirst(newEntry);
            }
        }
    }

    static int get(int key) {
        if (!hm.containsKey(key)) {
            return -1;
        } else {
            DoublyNode node = hm.get(key);
            removeNode(node);
            addFirst(node);
            return node.val;
        }
    }

    static void LRUCache(int capacity) {
        hm = new LinkedHashMap<>();
        cap = capacity;
        headNode = new DoublyNode();
        tailNode = new DoublyNode();
        headNode.next = tailNode;
        tailNode.prev = headNode;
    }

    private static class NodeR {
        int val;
        NodeR next;
        NodeR random;

        @Override
        public String toString() {
            return "NodeR{" +
                    "val=" + val +
                    ", next=" + next +
                    ", random=" + random +
                    '}';
        }

        NodeR(int val, NodeR node, NodeR random) {
            this.val = val;
            this.next = node;
            this.random = random;
        }

        NodeR(int val) {
            this.val = val;
        }
    }

    // Leetcode - Medium - 138. Copy List with Random Pointer

    /**
     * A linked list of length n is given such that each node contains an additional random pointer,
     * which could point to any node in the list, or null.
     * <p>
     * Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes,
     * where each new node has its value set to the value of its corresponding original node.
     * Both the next and random pointer of the new nodes should point to new nodes in the copied
     * list such that the pointers in the original list and copied list represent the same list state.
     * None of the pointers in the new list should point to nodes in the original list.
     * <p>
     * For example, if there are two nodes X and Y in the original list, where X.random --> Y,
     * then for the corresponding two nodes x and y in the copied list, x.random --> y.
     * <p>
     * Return the head of the copied linked list.
     * <p>
     * The linked list is represented in the input/output as a list of n nodes.
     * Each node is represented as a pair of [val, random_index] where:
     * <p>
     * val: an integer representing Node.val
     * random_index: the index of the node (range from 0 to n-1) that the random pointer points to,
     * or null if it does not point to any node.
     * Your code will only be given the head of the original linked list.
     * <p>
     * Example 1:
     * Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
     * Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
     * <p>
     * Example 2:
     * Input: head = [[1,1],[2,1]]
     * Output: [[1,1],[2,1]]
     * <p>
     * Example 3:
     * Input: head = [[3,null],[3,0],[3,null]]
     * Output: [[3,null],[3,0],[3,null]]
     */
    static NodeR copyRandomList(NodeR head) {
        // Step1: Create dummy nodes
        createDummyNodes(head);

        // Step2: Set Random nodes
        setRandomNodes(head);

        // Step3: Fetch the copy
        return createAndReturnCopy(head);
    }

    static void createDummyNodes(NodeR head) {
        NodeR temp = head;
        while (temp != null) {
            NodeR dummy = new NodeR(temp.val);
            NodeR future = temp.next;
            temp.next = dummy;
            dummy.next = future;
            temp = future;
        }
    }

    static void setRandomNodes(NodeR head) {
        NodeR temp = head;
        while (temp != null) {
            if (temp.random != null) {
                temp.next.random = temp.random.next;
            }
            temp = temp.next.next;
        }
    }

    static NodeR createAndReturnCopy(NodeR head) {
        NodeR tHead = new NodeR(-1);
        NodeR tTail = tHead;
        while (head != null) {
            tTail.next = head.next;
            head.next = head.next.next;
            tTail = tTail.next;
            head = head.next;
        }
        return tHead.next;
    }

    private static void printLL(NodeR head) {
        while (head != null) {
//            System.out.println(head.val);
            System.out.println(head.val + ", " + (head.random != null ? head.random.val : null));
            head = head.next;
        }
    }

    static void main() {
        /*
        Node a = new Node(2);
        Node b = new Node(3);
        Node c = new Node(5);
        Node d = new Node(6);
        Node e = new Node(4);
        Node f = new Node(7);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;
        System.out.println("Actual linked list:");
        printLL(a);
        System.out.println();
        Node evenOdd = oddEvenList(a);
        System.out.println("After even odd transformation");
        printLL(evenOdd);
        System.out.println();
         */

        /*
        Node n1 = new Node(17);
        Node n2 = new Node(15);
        Node n3 = new Node(8);
        Node n4 = new Node(9);
        Node n5 = new Node(2);
        Node n6 = new Node(4);
        Node n7 = new Node(6);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        System.out.println("Actual Linked List: ");
        printLL(n1);
        System.out.println();
        Node segregateList = segregateNodes(n1);
        System.out.println("After segregation");
        printLL(segregateList);
        System.out.println();

        Node a1 = new Node(1);
        Node a2 = new Node(3);
        Node a3 = new Node(5);
        Node a4 = new Node(7);
        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
        System.out.println("Actual List: ");
        printLL(a1);
        System.out.println();
        Node updatedList = segregateNodes(a1);
        System.out.println("After Segregation: ");
        printLL(updatedList);
        System.out.println();
         */

        /*
        Node nn1 = new Node(1);
        Node nn2 = new Node(2);
        Node nn3 = new Node(4);
        nn1.next = nn2;
        nn2.next = nn3;
        System.out.println("Original Lists");
        printLL(nn1);
        System.out.println();
        Node nm1 = new Node(1);
        Node nm2 = new Node(3);
        Node nm3 = new Node(4);
        nm1.next = nm2;
        nm2.next = nm3;
        printLL(nm1);
        System.out.println();
        System.out.println("Merging");
        Node mergeListHead = mergeTwoSortedList(nn1, nm1);
        System.out.println("After merging response: ");
        printLL(mergeListHead);
        System.out.println();
         */

        /*
        Node node1 = new Node(-1);
        Node node2 = new Node(5);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(0);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        System.out.println("Original list: ");
        printLL(node1);
        System.out.println();
        System.out.println("After sorting: ");
        printLL(sortList(node1));
        System.out.println();
         */

        /*
        LRUCache(2);
        put(1, 1);
        System.out.println(hm.toString());
        put(2, 2);
        System.out.println(hm);
        System.out.println("Getting 1 : " + get(1));
        System.out.println(hm);
        put(3, 3);
        System.out.println(hm);
        System.out.println("Getting 2 : " + get(2));
        System.out.println(hm);
        put(4, 4);
        System.out.println(hm);
        System.out.println("Getting 1 : " + get(1));
        System.out.println(hm);
        System.out.println("Getting 3 : " + get(3));
        System.out.println(hm);
        System.out.println("Getting 4 : " + get(4));
        System.out.println(hm);
         */
        // [[7,null],[13,0],[11,4],[10,2],[1,0]]
        NodeR nnr1 = new NodeR(7);
        NodeR nnr2 = new NodeR(13);
        NodeR nnr3 = new NodeR(11);
        NodeR nnr4 = new NodeR(10);
        NodeR nnr5 = new NodeR(1);
        nnr1.next = nnr2;
        nnr1.random = null;
        nnr2.next = nnr3;
        nnr2.random = nnr1;
        nnr3.next = nnr4;
        nnr3.random = nnr4;
        nnr4.next = nnr5;
        nnr4.random = nnr1;
        System.out.println("Initial linked list: ");
        printLL(nnr1);
        System.out.println();
        NodeR newHead = copyRandomList(nnr1);
        System.out.println("After copying: ");
        printLL(newHead);

    }
}
