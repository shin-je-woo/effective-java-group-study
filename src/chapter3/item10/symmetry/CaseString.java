package chapter3.item10.symmetry;

import java.util.Objects;

public class CaseString {
    private final String str;

    public CaseString(String str) {
        this.str = Objects.requireNonNull(str);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof CaseString) {
            return str.equalsIgnoreCase(((CaseString) o).str);
        }
        if (o instanceof String) {
            return str.equalsIgnoreCase((String) o);
        }
        return false;
    }

    // 이 문제를 해결하려면 CaseString과 String을 equlas 해보겠다는 허황된 꿈을 버려야 한다!
//    @Override public boolean equals(Object o) {
//        return o instanceof CaseString &&
//                ((CaseString) o).str.equalsIgnoreCase(str);
//    }
}
