public class ArrayDeque<T> {

    /** Invariants
     * size: the number of items in the deque.
     * ratio: items used are proportional to its size,
     * if less than the value, should resize the deque.
     */
    private T[] items;
    private int size;
    private int front;
    private int rear;
    private int capacity = 8;

    private static int REFACTOR = 2;

    /** Creates an empty array deque. */
    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[capacity];
        front = -1;
        rear = -1;
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T x) {
        if (isFull()) {
            resize(capacity * REFACTOR);
        }
        front = minusOne(front);
        items[front] = x;
        size += 1;
    }


    /** Resizes the backing array so that it is of
     * the given capacity.
     */
    private void resize(int capacityNew) {
        T[] a = (T[]) new Object[capacityNew];
        if (front > rear) {
            System.arraycopy(items, 0, a, 0, rear + 1);
            int length = this.capacity - front;
            System.arraycopy(items, front, a, capacityNew - length, length);
            front = capacityNew - length;
        } else {
            System.arraycopy(items, 0, a, 0, size);
            front = 0;
            rear = size - 1;
        }
        capacity = capacityNew;
        items = a;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T x) {
        if (isFull()) {
            resize(capacity * REFACTOR);
        }
        rear = plusOne(rear);
        items[rear] = x;
        size += 1;

    }

    /** Removes and returns the item of the font of the deque. */
    public T removeFirst() {
        if (!isEmpty()) {
            T frontValue = items[front];
            size--;
            front = plusOne(front);
            if (isSparse()) {
                resize((int) (capacity * 0.5));
            }
            return frontValue;
        }
        return null;
    }

    /** Removes and returns the item of the back of the deque. */
    public T removeLast() {
        if (!isEmpty()) {
            T rearValue = items[rear];
            size--;
            rear = minusOne(rear);
            if (isSparse()) {
                resize((int) (capacity * 0.5));
            }
            return rearValue;
        }
        return null;
    }

    /** Gets the item at position index in the deque. */
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return items[(front + index ) % capacity];
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last. */
    public void printDeque() {
        int p = front;
        while (p != rear) {
            System.out.print(items[p] + " ");
            p = plusOne(p);
        }
        System.out.println(items[p]);
    }

    /** Returns true if deque is empty, false otherwise.*/
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns true if deque is full, false otherwise. */
    private boolean isFull() {
        return size == capacity;
    }

    /** Returns true if the usage ratio of the deque < 0.25, false otherwise. */
    private boolean isSparse() {
        if (items.length < 16) {
            return false;
        }
        double ratio = size / (double) capacity;
        return ratio < 0.25;
    }

    /** Calculate the new front position of the queue. */
    private int minusOne(int position) {
        if (position == -1) {
            rear = 0;
            position = 0;
        } else {
            position = position == 0 ? capacity - 1 : position - 1;
        }
        return position;
    }

    private int plusOne(int position) {
        if (position == -1) {
            front = 0;
            position = 0;
        } else {
            position = position == capacity - 1 ? 0 : position + 1;
        }
        return position;
    }


}
