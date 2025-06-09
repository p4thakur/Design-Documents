package org.example.Rippling.DeliveryProblem.MusicPlayer;

public class DoublyLinkedList {
    ListNode head, tail;

    public DoublyLinkedList() {
        head = new ListNode(-1);
        tail = new ListNode(-1);
        head.next = tail;
        tail.prev = head;
    }

    public void addFirst(ListNode node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    public void remove(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public boolean isEmpty() {
        return head.next == tail;
    }

    public ListNode getFirst() {
        return head.next;
    }
}