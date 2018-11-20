package queue;

public class ArrayQueueADT {

    private int size = 0, head = 0;
    private Object[] elements = new Object[5];

    private static int inc(ArrayQueueADT queue, int i) {
        return i + 1 < queue.elements.length ? i + 1 : 0;
    }

    private static int dec(ArrayQueueADT queue, int i) {
        return i > 0 ? i - 1 : queue.elements.length - 1;
    }

    private static int getTail(ArrayQueueADT queue) {
        int tail = queue.head + queue.size;
        if (tail >= queue.elements.length) {
            tail -= queue.elements.length;
        }
        return tail;
    }

    private static void checkCapacity(ArrayQueueADT queue, int length) {
        if (length <= queue.elements.length) {
            return;
        }
        Object[] newElements = new Object[2 * queue.elements.length];
        int nHead = 0, nTail = 0;
        for (int i = queue.head; i != getTail(queue); i = inc(queue, i)) {
            newElements[nTail] = queue.elements[i];
            nTail = inc(queue, nTail);
        }
        queue.head = nHead;
        queue.elements = newElements;
    }

    public static void enqueue(ArrayQueueADT queue, Object element) {
        assert(element != null);
        checkCapacity(queue, queue.size + 2);
        queue.elements[getTail(queue)] = element;
        queue.size++;
    }

    public static Object element(ArrayQueueADT queue) {
        return queue.elements[queue.head];
    }

    public static Object dequeue(ArrayQueueADT queue) {
        Object res = element(queue);
        queue.head = inc(queue, queue.head);
        queue.size--;
        return res;
    }

    public static void push(ArrayQueueADT queue, Object element) {
        assert(element != null);
        checkCapacity(queue, queue.size + 2);
        queue.head = dec(queue, queue.head);
        queue.elements[queue.head] = element;
        queue.size++;
    }

    public static Object peek(ArrayQueueADT queue) {
        int pos = getTail(queue);
        return queue.elements[dec(queue, pos)];
    }

    public static Object remove(ArrayQueueADT queue) {
        Object res = peek(queue);
        queue.size--;
        return res;
    }


    public static int size(ArrayQueueADT queue) {
        return queue.size;
    }

    public static boolean isEmpty(ArrayQueueADT queue) {
        return queue.size == 0;
    }

    public static void clear(ArrayQueueADT queue) {
        for (int i = queue.head; i != getTail(queue); i = inc(queue, i)) {
            queue.elements[i] = null;
        }
        queue.size = 0;
    }
}
