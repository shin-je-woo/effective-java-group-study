package chapter12.item90;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Date;

class Period implements Serializable {
    // 불변 가능
    private final Date start;
    private final Date end;

    public Period(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    private static class SerializationProxy implements Serializable {
        private static final long serialVersionUID = 2123123123;
        private final Date start;
        private final Date end;

        public SerializationProxy(Period p) {
            this.start = p.start;
            this.end = p.end;
        }

        // Deserialize -> Object 생성
        private Object readResolve() {
            return new Period(start, end);
        }
        
    }

    // Serialize -> 프록시 인스턴스 반환
    // 결코 바깥 클래스의 직렬화된 인스턴스를 생성해낼 수 없다
    private Object writeReplace() {
        return new SerializationProxy(this);
    }

    // Period 자체의 역직렬화를 방지 -> 역직렬화 시도시, 에러 반환
    private void readObject(ObjectInputStream stream) throws InvalidObjectException {
        throw new InvalidObjectException("프록시가 필요해요.");
    }
}