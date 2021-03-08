package com.leetcode.solution.algorithm;

import lombok.AllArgsConstructor;

import java.util.StringJoiner;

/**
 * @Author: shixiaolong
 * @Date: 2019-12-02 13:38
 */
public class List {

    Node head;


    /**
     * @param a
     * @return
     */
    public static ListNode removeRepeat(ListNode a) {

        if (a == null) {
            return null;
        }
        ListNode head = a;
        for (; ; ) {
            ListNode next = head.next;
            if (next == null) {
                break;
            }
            if (next.val == head.val) {
                head.next = next.next;
            } else {
                head = next;
            }
        }
        return a;
    }

    /**
     * @param a
     * @param b
     * @return
     */
    public static Node add(Node<Integer> a, Node<Integer> b) {

        Node<Integer> heada = a;
        Node<Integer> headb = b;

        Node<Integer> head = null;
        Node<Integer> headc = null;

        int tmp = 0;
        for (; ; ) {
            int v = tmp;

            if (a == null && b == null) {
                if (tmp > 0) {
                    headc.next = new Node<>(v, null);
                }
                break;
            }
            if (heada != null) {
                v += heada.v;
                heada = heada.next;
            }
            if (headb != null) {
                v += headb.v;
                headb = headb.next;
            }

            tmp = v / 10;
            v = v % 10;

            if (head == null) {
                headc = head = new Node<>(v, null);
            } else {
                headc.next = new Node<>(v, null);
                headc = headc.next;
            }

        }

        return head;

    }


    /**
     * @param head
     * @return 1, 2, 3, 4, 5
     */
    public static ListNode findTailN(ListNode head, int n) {
        ListNode result = head;

        for (int i = 0; i < n; i++) {
            if (head == null) {
                return null;
            }
            head = head.next;
        }

        for (; ; ) {
            if (head == null) {
                return result;
            }
            head = head.next;
            result = result.next;
        }
    }


    /**
     * https://leetcode.com/problems/reverse-linked-list/submissions/
     *
     * @param head
     * @return
     */
    public static Node reverse(Node head) {


        Node result = null;
        for (; ; ) {
            if (head == null) {
                return result;
            }
            Node temp = head.next;
            head.next = result;
            result = head;
            head = temp;
        }

    }


    /**
     * https://leetcode.com/problems/reverse-linked-list-ii/
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public static ListNode reverseBetween(ListNode head, int m, int n) {

        int i = 1;
        ListNode preM = null, temp = head;
        ListNode _head = null;
        ListNode _tail = null;

        if (m == n) {
            return head;
        }

        for (; ; ) {
            ListNode next = temp.next;
            if (i == m - 1) {
                preM = temp;
            } else if (i == m) {
                _tail = _head = temp;
            } else if (i == n) {
                _tail.next = temp.next;
                if (preM == null) {
                    ListNode tmp = _head;
                    _head = temp;
                    _head.next = tmp;
                    return _head;
                }
                preM.next = temp;
                temp.next = _head;
                return head;
            } else if (i > m && i < n) {
                ListNode tmp = _head;
                _head = temp;
                _head.next = tmp;
            }
            temp = next;
            i++;

        }

    }

    /**
     * https://leetcode.com/problems/merge-k-sorted-lists/
     *
     * @param lists
     * @return
     */
    public static ListNode mergeKLists(ListNode[] lists) {

        ListNode result = null, head = null;

        for (; ; ) {
            ListNode min = null;
            int p = -1;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] != null) {
                    if (min == null) {
                        min = lists[i];
                        p = i;
                    } else if (lists[i].val < min.val) {
                        p = i;
                        min = lists[i];
                    }
                }
            }

            if (p == -1) {
                break;
            }
            lists[p] = min.next;
            if (head == null) {
                head = result = min;
            } else {
                result.next = min;
                result = result.next;
            }
            min.next = null;
        }

        return head;

    }


    public static ListNode mergeKLists2(ListNode[] lists) {

        ListNode head = null;

        for (ListNode listNode : lists) {
            head = mergeTwoLists(head, listNode);
        }

        return head;

    }

    /**
     * https://leetcode.com/problems/merge-two-sorted-lists/
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode result = null, head = null;

        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        if (l1.val <= l2.val) {
            result = head = l1;
            l1 = l1.next;
        } else {
            result = head = l2;
            l2 = l2.next;
        }

        for (; ; ) {

            if (l1 == null && l2 == null) {
                return head;
            }

            if (l1 == null) {
                result.next = l2;
                l2 = null;
            } else if (l2 == null) {
                result.next = l1;
                l1 = null;
            } else {
                if (l1.val > l2.val) {
                    result.next = l2;
                    l2 = l2.next;
                    result = result.next;

                } else {
                    result.next = l1;
                    l1 = l1.next;
                    result = result.next;
                }
            }

        }


    }

    /**
     * https://leetcode.com/problems/linked-list-cycle-ii/
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        return null;
    }


    public static ListNode sortList(ListNode head) {


//        for (; ; ) {
//
//        }

        return head;

    }


    public static ListNode reorderList(ListNode head) {

        return head;
    }


    public static ListNode create(int... vals) {

        ListNode head = null;
        ListNode next = null;

        for (int i : vals) {
            if (head == null) {
                next = head = new ListNode(i);
            } else {
                next.next = new ListNode(i);
                next = next.next;
            }
        }

        return head;
    }


    public static void main(String[] args) {

//        Node head = new Node(0, null);
//        for (int i = 1; i < 10; i++) {
//            head = new Node<>(i, head);
//        }
//
//        Node t = head;
//        while (t != null) {
//            System.out.print(t.v + ",");
//            t = t.next;
//        }
//        System.out.print("\n");
//
//        Node t2 = reverse(head);
//        while (t2 != null) {
//            System.out.print(t2.v + ",");
//            t2 = t2.next;
//        }
//        System.out.print("\n");


        ListNode listNode1 = create(0,1, 2, 2, 3, 3,4, 4, 5,7);
        ListNode listNode2 = create(2, 2, 3, 4, 5);
        ListNode listNode3 = create(6, 7, 8);

        System.out.println(print(removeRepeat(listNode1)));

//        print(reverseBetween(listNode, 3, 4));

//        print(mergeKLists(new ListNode[]{listNode1, listNode2, listNode3}));

//        print(mergeTwoLists(listNode1, listNode2));
//
//        System.out.println(findTailN(listNode1, 5));
//        System.out.println(findTailN(listNode1, 2));
//        System.out.println(findTailN(listNode1, 1));

    }

    @AllArgsConstructor
    private static class Node<T> {
        private T v;
        private Node next;
    }

    public static String print(ListNode t) {
        StringJoiner sj = new StringJoiner(",", "[", "]");
        while (t != null) {
            sj.add(String.valueOf(t.val));
            t = t.next;
        }
        return sj.toString();
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", ListNode.class.getSimpleName() + "[", "]")
                    .add("val=" + val)
                    .toString();
        }
    }
}
