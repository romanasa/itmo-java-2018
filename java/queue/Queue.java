package queue;
import java.util.function.*;

public interface Queue {

    //pre: element != null
    void enqueue(Object element);
    //post:
    // for all i = 0..size - 1: a'[i] = a[i] && a'[size] = element
    // size' = size + 1

    //pre: size > 0
    Object element();
    // R = a[0]

    //pre: size > 0
    Object dequeue();
    //R = a[0]
    //for all i = 1..size - 1: a'[i - 1] = a[i]
    //size' = size - 1

    //pre: true
    int size();
    //post: R = size && immutable

    //pre: true
    boolean isEmpty();
    //post: R = (size == 0) && immutable

    //pre: true
    void clear();
    //post: size' = 0

    //pre: true
    Queue filter(Predicate<Object> p);
    //post: R = new queue, contains elements matching to the predicate in the same order

    //pre: true
    Queue map(Function<Object, Object> f);
    //post: R = new queue, for all i = 0..size - 1: a'[i] = f(a[i])
}
