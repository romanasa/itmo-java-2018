package queue;

public class ArrayQueueModule {

    //n = head + size
    //elements.length > size

    private static int size = 0, head = 0;
    private static Object[] elements = new Object[5];

    private static int inc(int i) {
        return i + 1 < elements.length ? i + 1 : 0;
    }

    private static int dec(int i) {
        return i > 0 ? i - 1 : elements.length - 1;
    }

    //0 <= head < elements.length && 0 <= size < elements.length
    private static int getTail() {
        int tail = head + size;
        if (tail >= elements.length) {
            tail -= elements.length;
        }
        return tail;
    }
    //R = (head + size) % elements.length

    private static void checkCapacity(int length) {
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

    public static void enqueue(Object element) {
        assert(element != null);
        checkCapacity(size + 2);
        elements[getTail()] = element;
        size++;
    }
    //(for all i = 0..n - 1 q'[i] = q[i]) && q[n] = element

    public static Object element() {
        return elements[head];
    }

    public static Object dequeue() {
        Object res = element();
        head = inc(head);
        size--;
        return res;
    }

    public static void push(Object element) {
        assert(element != null);
        checkCapacity(size + 2);
        head = dec(head);
        elements[head] = element;
        size++;
    }

    public static Object peek() {
        int pos = getTail();
        return elements[dec(pos)];
    }

    public static Object remove() {
        Object res = peek();
        size--;
        return res;
    }

    public static int size() {
        return size;
    }

    public static boolean isEmpty() {
        return size == 0;
    }

    public static void clear() {
        for (int i = head; i != getTail(); i = inc(i)) {
            elements[i] = null;
        }
        size = 0;
    }
}
