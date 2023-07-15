package chapter4.item18;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;

public class ForwardingSet<E> implements Set<E>{
    // 재사용할 수 있는 전달 클래스
    private final Set<E> s;
    public ForwardingSet(Set<E> s) { this.s = s; }

    public int size() {
        return s.size();
    }

    public boolean isEmpty() {
        return s.isEmpty();
    }

    public boolean contains(Object o) {
        return s.contains(o);
    }

    public Iterator iterator() {
        return s.iterator();
    }

    public Object[] toArray() {
        return s.toArray();
    }

    public boolean add(E e) {
        return s.add(e);
    }

    public boolean addAll(Collection<? extends E> c) {
        return s.addAll(c);
    }

    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    public boolean remove(Object o) {
        return s.remove(o);
    }

    public void clear() {
        s.clear();
    }

    public Spliterator spliterator() {
        return Set.super.spliterator();
    }

    public boolean removeAll(Collection<?> c) {
        return s.removeAll(c);
    }

    public boolean retainAll(Collection<?> c) {
        return s.retainAll(c);
    }

    public boolean containsAll(Collection<?> c) {
        return s.containsAll(c);
    }
}