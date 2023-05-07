package chapter4.item18;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class InstrumnetdHashSet<E> extends HashSet<E> {
    // 추가된 원소의 수
    private int addCount = 0;

    public InstrumnetdHashSet() {
    }

    public InstrumnetdHashSet(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    @Override
    public boolean add(E e) { // add를 재정의하여 나오는 현상
        addCount++;
        return super.add(e);
    }

    // AbstractCollection
    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }
}

