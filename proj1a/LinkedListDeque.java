public class LinkedListDeque<T> {
    private class ListNode {
        private T value;
        private ListNode prev;
        private ListNode next;

        ListNode() {
            value = null;
            prev = null;
            next = null;
        }
        ListNode(T x, ListNode l, ListNode n) {
            value = x;
            prev = l;
            next = n;
        }
    }

    /** Invariants:
     * size: the number of items in the deque.
     * sentinel: point the sentinel node.
     */
    private int size;
    private ListNode sentinel;

    /** Creates an empty deque. */
    public LinkedListDeque() {
        size = 0;
        sentinel = new ListNode();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }


    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        if (size == 0) {
            sentinel.next = new ListNode(item, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        } else {
            sentinel.next = new ListNode(item, sentinel, sentinel.next);
            sentinel.next.next.prev = sentinel.next;
        }
        size += 1;
    }

    /** Adds an item of type T to the end of the deque. */
    public  void addLast(T item) {
        if (size == 0) {
            sentinel.next = new ListNode(item, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        } else {
            sentinel.prev = new ListNode(item, sentinel.prev, sentinel.prev.next);
            sentinel.prev.prev.next = sentinel.prev;
        }
        size += 1;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        /* if(size == 0) {
            return true;
        }
        else {
            return false;
        }*/
        return size == 0;
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        ListNode p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.value);
            System.out.print(" ");
            p = p.next;
        }

    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     * */
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            T tmp = sentinel.next.value;
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
            size -= 1;
            return tmp;
        }

    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null. */
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            T tmp = sentinel.prev.value;
            sentinel.prev.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
            size -= 1;
            return tmp;
        }
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item,
     * and so forth. If no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index) {
        ListNode p = sentinel.next;
        int i = 0;
        while (p != sentinel) {
            if (i == index) {
                return p.value;
            }
            i += 1;
            p = p.next;
        }
        return null;
    }

    private T getR(ListNode N, int index) {
        if (index == 0) {
            return N.value;
        } else {
            return getR(N.next, index - 1);
        }
    }

    /** Same as Get, but uses recursive. */
    public T getRecursive(int index) {
        return getR(sentinel.next, index);
    }
/*
    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        LinkedListDeque<Integer> dq = new LinkedListDeque<Integer>();
        dq.addFirst(6);
        dq.addLast(7);
        dq.addFirst(8);
        dq.addLast(9);
        dq.printDeque();
        dq.getRecursive(2);
        dq.removeFirst();
        dq.removeLast();
    }
    */

}
