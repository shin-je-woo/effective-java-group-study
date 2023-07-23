package chapter12.item87;

import lombok.Getter;
import lombok.Setter;

import java.io.*;


public final class StringList implements Serializable {
    private static final long serialVersionUID = 230723L;

    private transient int size = 0;
    private transient Entry head = null;
    private transient Entry now = null;

    @Setter
    @Getter
    private static class Entry {
        public Entry(String data) {
            this.data = data;
        }

        String data;
        Entry next;
        Entry previous;
    }

    public void add(String s){
        if(head == null){
            head = new Entry(s);
            now = head;
        }else{
            Entry e = new Entry(s);
            now.setNext(e);
            e.setPrevious(now);
            now = e;
        }
        size++;
    }

    @Override
    public String toString() {
        String s = "";
        for(Entry e = head; e != null; e = e.next)
            s += e.data;
        return s;
    }

    /**
     * 이 {@code StringList} 인스턴스를 직렬화한다.
     *
     * @serialData 이 리스트의 크기(포함된 문자열의 개수)를 기록한 후
     * ({@code int}), 이어서 모든 원소를(각각은 {@code String}) 순서대로 기록한다.
     */
    private void writeObject(ObjectOutputStream s) throws IOException{
        // 기본 직렬화 수행
        s.defaultWriteObject();
        s.writeInt(size);

        // 커스텀 직렬화
        // 모든 원소를 올바른 순서로 기록한다.
        for(Entry e = head; e != null; e = e.next)
            s.writeObject(e.data);
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException{
        // 기본 역직렬화
        s.defaultReadObject();
        int numElemnets = s.readInt();

        // 커스텀 역직렬화
        // 모든 원소를 읽어 이 리스트에 삽입한다.
        for(int i = 0; i < numElemnets; i++)
            add((String) s.readObject());
    }

    public static void main(String[] args) throws IOException {
        StringList stringList = new StringList();
        stringList.add("Hello");
        stringList.add(" ");
        stringList.add("world!");
        System.out.println(stringList);
        System.out.println(stringList.size);

        // 외부 파일명
        String fileName = "StringList.ser";

        // 직렬화
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            stringList.writeObject(oos);
        }catch (IOException e){
            e.getStackTrace();
        }

        // 역직렬화
        StringList deserializedObject = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            deserializedObject.readObject(ois);
            System.out.println(deserializedObject);
        }catch (Exception e){
            e.getStackTrace();
        }

    }
}
