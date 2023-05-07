package chapter4.item18;

import java.util.Collection;
import java.util.Set;

public class InstrumnetdSet<E> extends ForwardingSet<E> {
    // 추가된 원소의 수
    private int addCount = 0;

    public InstrumnetdSet(Set<E> s) {
        super(s);
    }

    @Override
    public boolean add(Object o) {
        addCount++;
        return super.add(o);
    }

    @Override
    public boolean addAll(Collection c) {
        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }
}

