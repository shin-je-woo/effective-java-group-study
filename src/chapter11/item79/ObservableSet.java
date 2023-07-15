package chapter11.item79;

import chapter4.item18.ForwardingSet;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ObservableSet<E> extends ForwardingSet<E> {
    public ObservableSet(Set<E> s) {
        super(s);
    }

    private final List<SetObserver<E>> observers = new ArrayList<>();
//    private final List<SetObserver<E>> observers = new CopyOnWriteArrayList();

    public void addObserver(SetObserver<E> observer){
        synchronized (observers) {
            observers.add(observer);
        }
    }

    public boolean removeObserver(SetObserver<E> observer){
        synchronized (observers){
            return observers.remove(observer);
        }
    }

    private void notifyElementAdded(E element){
        synchronized (observers){
            for(SetObserver<E> observer : observers){
                observer.added(this, element);
            }
        }
    }

    @Override
    public boolean add(E elemnet) {
        boolean added = super.add(elemnet);
        if(added)
            notifyElementAdded(elemnet);
        return added;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean result = false;
        for (E elemnet : c)
            result |= add(elemnet); // result = reuslt | added
        return result;
    }

    public static void main(String[] args) {
        ObservableSet<Integer> set = new ObservableSet<>(new HashSet<>());

        // 파라미터는 BiConsumer
//        set.addObserver((s, e) -> System.out.println(e));


        set.addObserver(new SetObserver<>(){
           public void added(ObservableSet<Integer> s, Integer e){
               if(e == 2) {
                   // 79-1. 외계인 메서드를 호출하는 경우
                   s.removeObserver(this);


                   // 79-2. 실행자 서비스(백그라운드 스레드)를 사용하는 경우
                   /*ExecutorService exec = Executors.newSingleThreadExecutor();
                   try{
                       exec.submit(() -> s.removeObserver(this)).get();
                   } catch (ExecutionException | InterruptedException ex){
                       throw new AssertionError(ex);
                   }finally {
                       exec.shutdown();
                   }*/
               }
           }
        });

        // add 후 notifyElementAdded 동작
        for (int i = 0; i < 10; i++) set.add(i);
    }
}
