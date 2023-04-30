package chapter3.item13;

public class HashTable implements Cloneable {

    private Entry[] buckets = new Entry[10];

    private static class Entry {
        final Object key;
        Object value;
        Entry next;

        Entry(Object key, Object value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public void add(Object key, Object value) {
            this.next = new Entry(key, value, null);
        }

        //재귀호출 떄문에 리스트의 원소 수만큼 스택 프레임을 소비하여, 리스트가 길면 스택 오버플로우를 이르킬 수 있따.
        /*

        public Entry deepCopy() {
            return new Entry(key, value, next == null ? null : next.deepCopy());
        }
*/

        public Entry deepCopy() {
            Entry result = new Entry(key, value, next);
            for (Entry p = result ; p.next != null ; p = p.next) {
                p.next = new Entry(p.next.key, p.next.value, p.next.next);
            }
            return result;
        }
    }

    /**
     * TODO hasTable -> entryH[],
     * TODO copy -> entryC[]
     * TODO entryH[0] == entryC[0]
     *
     * @return
     */
//    @Override
//    public HashTable clone() {
//        HashTable result = null;
//        try {
//            result = (HashTable)super.clone();
//            result.buckets = this.buckets.clone(); // p82, shallow copy 라서 위험하다.
//            return result;
//        } catch (CloneNotSupportedException e) {
//            throw  new AssertionError();
//        }
//    }

    /**
     * TODO hasTable -> entryH[],
     * TODO copy -> entryC[]
     * TODO entryH[0] != entryC[0]
     *
     * @return
     */
    @Override
    public synchronized HashTable clone() {
        HashTable result = null;
        try {
            result = (HashTable)super.clone();
            result.buckets = new Entry[this.buckets.length];
            //result.buckets = CreateNewBucket();
            for (int i = 0 ; i < this.buckets.length; i++) {
                if (buckets[i] != null) {
                    result.buckets[i] = this.buckets[i].deepCopy(); // p83, deep copy
                }
            }
            return result;
        } catch (CloneNotSupportedException e) {
            throw  new AssertionError();
        }
    }

    private Entry[] CreateNewBucket() {
        return new Entry[this.buckets.length];
    }

    public static void main(String[] args) {
        HashTable hashTable = new HashTable();
        Entry entry = new Entry(new Object(), new Object(), null);
        hashTable.buckets[0] = entry;
        HashTable clone = hashTable.clone();
        System.out.println(hashTable.buckets[0] == entry);
        System.out.println(hashTable.buckets[0] == clone.buckets[0]);
    }
}
