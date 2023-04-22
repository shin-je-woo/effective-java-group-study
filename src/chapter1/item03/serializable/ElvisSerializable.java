package chapter1.item03.serializable;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class ElvisSerializable implements Serializable {
        private static final ElvisSerializable INSTANCE = new ElvisSerializable();

        private ElvisSerializable() { }

        public static ElvisSerializable getInstance() {
            return INSTANCE;
        }

        private Object readResolve() throws ObjectStreamException {
            // 직렬화된 Elvis 객체는 싱글턴 객체로 교체된다
            // 역직렬화 시 자동으로 호출
            return INSTANCE;
        }
    }