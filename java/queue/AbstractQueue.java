package queue;

public abstract class AbstractQueue implements Queue {

    protected int size = 0;

    protected abstract void enqueueImpl(Object element);

    public void enqueue(Object element) {
        enqueueImpl(element);
        size++;
    }

    protected abstract Object elementImpl();

    public Object element() {
        assert(size > 0);
        return elementImpl();
    }

    protected abstract Object dequeueImpl();

    public Object dequeue() {
        assert(size > 0);
        Object element = dequeueImpl();
        size--;
        return element;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    protected abstract void clearImpl();

    public void clear() {
        clearImpl();
        size = 0;
    }
}
