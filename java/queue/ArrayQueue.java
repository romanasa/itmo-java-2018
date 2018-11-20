package queue;

public class ArrayQueue {

    private int size = 0, head = 0;
    private Object[] elements = new Object[5];

    private int inc(int i) {
        return i + 1 < elements.length ? i + 1 : 0;
    }

    private int dec(int i) {
        return i > 0 ? i - 1 : elements.length - 1;
    }

    private int getTail() {
        int tail = head + size;
        if (tail >= elements.length) {
            tail -= elements.length;
        }
        return tail;
    }

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

    public void enqueue(Object element) {
        assert(element != null);
        checkCapacity(size + 2);
        elements[getTail()] = element;
        size++;
    }

    public Object element() {
        return elements[head];
    }

    public Object dequeue() {
        Object res = element();
        head = inc(head);
        size--;
        return res;
    }


    public void push(Object element) {
        assert(element != null);
        checkCapacity(size + 2);
        head = dec(head);
        elements[head] = element;
        size++;
    }

    public Object peek() {
        int pos = getTail();
        return elements[dec(pos)];
    }

    public Object remove() {
        Object res = peek();
        size--;
        return res;
    }


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        for (int i = head; i != getTail(); i = inc(i)) {
            elements[i] = null;
        }
        size = 0;
    }
}
