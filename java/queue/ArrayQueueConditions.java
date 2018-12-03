package queue;

public class ArrayQueueConditions {
    //size >= 0
    //inv I: 0 <= head < elements.length
    //
    //for all i = 0..size - 1:
    //   a[i] = elements[(head + i) % elements.length]

    private int size = 0, head = 0;
    private Object[] elements = new Object[5];

    //pre: 0 <= i < elements.length
    private int inc(int i) {
        return i + 1 < elements.length ? i + 1 : 0;
    }
    //post: i = (i + 1) % elements.length

    //pre: 0 <= i < elements.length
    private int dec(int i) {
        return i > 0 ? i - 1 : elements.length - 1;
    }
    //post: i = (i - 1) % elements.length

    //pre: I
    private int getTail() {
        int tail = head + size;
        if (tail >= elements.length) {
            tail -= elements.length;
        }
        return tail;
    }
    //post: tail = (head + size) % elements.length

    //pre: length <= 2 * elements.length
    private void checkCapacity(int length) {
        if (length <= elements.length) {
            return;
        }
        Object[] newElements = new Object[2 * elements.length];
        int nHead = 0, nTail = 0;
        for (int i = head; i != getTail(); i = inc(i)) {
            newElements[nTail] = elements[i];
            nTail = inc(nTail);
        }
        head = nHead;
        elements = newElements;
    }
    //post: elements.length >= length

    //pre: element != null
    public void enqueue(Object element) {
        assert(element != null);
        checkCapacity(size + 2);
        elements[getTail()] = element;
        size++;
    }
    //post:
    //size' = size + 1
    //a'[size] = element, for all i = 0..size - 1: a'[i] = a[i]
    //elements.length >= size + 2

    //pre: I
    public Object element() {
        return elements[head];
    }
    //post: R = a[0]

    //pre: I && size > 0
    public Object dequeue() {
        Object res = element();
        head = inc(head);
        size--;
        return res;
    }
    //post:
    //for all i = 0..size - 2: a'[i] = a[i + 1]
    //size' = size - 1

    //pre: I
    public void push(Object element) {
        assert(element != null);
        checkCapacity(size + 2);
        head = dec(head);
        elements[head] = element;
        size++;
    }
    //post:
    //for all i = 1..size: a'[i] = a[i - 1] && a'[0] = element
    //size' = size + 1

    //pre: I
    public Object peek() {
        int pos = getTail();
        return elements[dec(pos)];
    }
    //post: R = a[size - 1]


    //pre: I && size > 0
    public Object remove() {
        Object res = peek();
        size--;
        return res;
    }
    //post:
    //for all i = 0..size - 1: a'[i] = a[i]
    //size' = size - 1

    //pre: I
    public int size() {
        return size;
    }
    //post: R = size

    //pre: I
    public boolean isEmpty() {
        return size == 0;
    }
    //post: R = (size == 0)

    //pre: I
    public void clear() {
        for (int i = head; i != getTail(); i = inc(i)) {
            elements[i] = null;
        }
        size = 0;
    }
    //post:
    //size = 0
}
