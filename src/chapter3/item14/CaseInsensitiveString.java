package chapter3.item14;

import java.util.Objects;

public class CaseInsensitiveString implements Comparable<CaseInsensitiveString>{
    private final String s;

    CaseInsensitiveString(String s){
        this.s = Objects.requireNonNull(s);
    }

    @Override
    public int compareTo(CaseInsensitiveString cis) {
        // CASE_INSENSITIVE_ORDER : String에서 Comparator 구현, 대소문자 무시
        return String.CASE_INSENSITIVE_ORDER.compare(s, cis.s);
    }
}
