package chapter1.item07;

import java.util.Arrays;
import java.util.EmptyStackException;

class Stack{
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack(){
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e){
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop(){
        if (size == 0)
            throw new EmptyStackException();
        Object result = elements[--size];
        // 다 쓴 참조 해제
//        elements[size] = null;
        return result;
    }

    public Object get(int index){
        return elements[index];
    }

    private void ensureCapacity(){
        if(elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }
}

public class StackTest {
    public static void main(String[] args) {
        Stack stack = new Stack();
        for(int i = 0; i < 5; i++){
            stack.push(new Object());
        }

        System.out.println("pop------------------------------------");
        for(int i = 0; i < 5; i++){
            System.out.println(stack.pop());
        }

        System.out.println();
        System.out.println("get------------------------------------");
        for(int i = 0; i < 5; i++){
            System.out.println(stack.get(i));
        }
    }


}
