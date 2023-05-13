package chapter5.item28;

public class ConvariantTest {

    public static void main(String[] args) {
        Object[] array = new Long[1]; // 배열은 공변이기 때문에 이와 같은 선언이 가능
        array[0] = "공변";
    }

}
