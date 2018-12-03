package queue;

public class ArrayQueue extends AbstractQueue {
    private int head = 0;
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

    protected void enqueueImpl(Object element) {
        assert(element != null);
        checkCapacity(size + 2);
        elements[getTail()] = element;
    }

    protected Object elementImpl() {
        return elements[head];
    }

    protected Object dequeueImpl() {
        Object res = element();
        head = inc(head);
        return res;
    }

    protected void clearImpl() {
        for (int i = head; i != getTail(); i = inc(i)) {
            elements[i] = null;
        }
    }
}
