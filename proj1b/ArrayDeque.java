public class ArrayDeque<T> implements Deque<T> {

    /** Invariants
     * size: the number of items in the deque.
     * ratio: items used are proportional to its size, if less than the value, should resize the deque.
     */
    private T[] items;
    private int size;
    private int front;
    private int rear;

    private static int REFACTOR = 2;

    /** Creates an empty array deque. */
    public ArrayDeque(){
        size = 0;
        items = (T[]) new Object[8];
        front = -1;
        rear = -1;
    }

    /** Adds an item of type T to the front of the deque. */
    @Override
    public void addFirst(T x){
        if (isFull()) {
            resize(items.length * REFACTOR);
        }
        front = minusOne(front);
        items[front] = x;
        size += 1;
    }

    /** Adds an item of type T to the back of the deque. */
    @Override
    public void addLast(T x){
        if (isFull()) {
            resize(items.length * REFACTOR);
        }
        rear = plusOne(rear);
        items[rear] = x;
        size += 1;

    }

    /** Removes and returns the item of the font of the deque. */
    @Override
    public T removeFirst(){
        T frontValue = items[front];
        items[front] = null;
        size--;
        front = plusOne(front);
        if (isSparse()) {
            resize((int) (items.length * 0.5));
        }
        return frontValue;
    }

    /** Removes and returns the item of the back of the deque. */
    @Override
    public T removeLast() {
        T rearValue = items[rear];
        items[rear] = null;
        size--;
        rear = minusOne(rear);
        if (isSparse()) {
            resize((int) (items.length * 0.5));
        }
        return rearValue;
    }

    /** Gets the item at position index in the deque. */
    @Override
    public T get(int index){
        return items[index];
    }

    /** Returns the number of items in the deque. */
    @Override
    public int size(){
        return size;
    }

    /** Prints the items in the deque from first to last. */
    @Override
    public void printDeque() {
        int p = front;
        while (p != rear) {
            System.out.print(items[p] + " ");
            p = plusOne(p);
        }
        System.out.println(items[p]);
    }

    /** Returns true if deque is empty, false otherwise.*/
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns true if deque is full, false otherwise. */
    private boolean isFull() {
        return size == items.length;
    }

    /** Returns true if the usage ratio of the deque < 0.25, false otherwise. */
    private boolean isSparse() {
        if (items.length < 16) {
            return false;
        }
        double ratio = size / (double) items.length;
        return ratio < 0.25;
    }

    /** Calculate the new front position of the queue. */
    private int minusOne(int position) {
        if ( position == -1) {
            rear = 0;
            position = 0;
        } else {
            position = position == 0 ? items.length - 1 : position - 1;
        }
        return position;
    }

    private int plusOne(int position) {
        if ( position == -1) {
            front = 0;
            position = 0;
        } else {
            position = position == items.length - 1 ? 0 : position + 1;
        }
        return position;
    }
    /** Resizes the backing array so that it is of
     * the given capacity.
     */
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        if (front > rear) {
            System.arraycopy(items, 0, a, 0, rear + 1);
            int length = items.length - front;
            System.arraycopy(items, front, a, capacity - length, length);
            front = capacity - length;
        } else {
            System.arraycopy(items, front, a, 0, size);
            front = 0;
            rear = size - 1;
        }
        items = a;
    }

}
