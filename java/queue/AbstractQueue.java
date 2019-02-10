package queue;

import java.util.function.Function;
import java.util.function.Predicate;

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

    protected abstract Queue copyQueue();

    protected abstract Queue emptyQueue();

    public Queue filter(Predicate<Object> p) {
        Queue cur = copyQueue();
        Queue res = emptyQueue();
        while (cur.size() > 0) {
            Object elem = cur.dequeue();
            if (p.test(elem)) {
                res.enqueue(elem);
            }
        }
        return res;
    }

    public Queue map(Function<Object, Object> f) {
        Queue cur = copyQueue();
        Queue res = emptyQueue();
        while (cur.size() > 0) {
            Object elem = cur.dequeue();
            res.enqueue(f.apply(elem));
        }
        return res;
    }
}
