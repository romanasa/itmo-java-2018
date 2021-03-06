package queue;

import java.util.function.Function;
import java.util.function.Predicate;

public class LinkedQueue extends AbstractQueue {

    private class node {
        node prev, next;
        Object value;

        node(Object x) {
            prev = null;
            next = null;
            value = x;
        }
    }

    private node head, tail;

    protected void enqueueImpl(Object element) {
        node cur = new node(element);
        if (size == 0) {
            head = cur;
            tail = cur;
        } else {
            tail.next = cur;
            cur.prev = tail;
            tail = cur;
        }
    }

    protected Object elementImpl() {
        return head.value;
    }

    protected Object dequeueImpl() {
        Object res = elementImpl();
        head = head.next;
        return res;
    }

    protected void clearImpl() {
        head = null;
        tail = null;
    }

    protected Queue emptyQueue() {
        return new LinkedQueue();
    }

    protected Queue copyQueue() {
        Queue cur = emptyQueue();
        node curNode = head;
        for (int i = 0; i < size; i++) {
            cur.enqueue(curNode.value);
            curNode = curNode.next;
        }
        return cur;
    }
}
