package chapter9.item67;

import java.util.HashMap;
import java.util.Map;

public class Example {

    Map<String, String> map = new HashMap<>();

    //HashMap에 종속적이고, 추후 변경 필요 시 문제
   HashMap<String, String> hashMap = new HashMap<>();
}
