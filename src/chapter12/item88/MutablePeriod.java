package chapter12.item88;

import java.io.*;
import java.util.Date;

public class MutablePeriod {

    //Period2 인스턴스
    public final Period2 period;
    //시작 시각 필드 - 외부에서 접근할 수 없어야함
    public final Date start;
    //종료 시각 필드 - 외부에서 접근할 수 없어야함
    public final Date end;

    public MutablePeriod() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);

            // 불변식을 유지하는 Period 를 직렬화한다.
            out.writeObject(new Period2(new Date(), new Date()));

            /*
             * 악의적인 private Date 필드의 바이트스트림을 주입한다.
             */
            byte[] ref = { 0x71, 0, 0x7e, 0, 5 };
            bos.write(ref);                       // 시작 start 필드

            ref[4] = 4;
            bos.write(ref);                       // 종료 end 필드

            // 역직렬화 과정에서 Period 객체의 Date 참조를 훔친다.
            ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));

            period = (Period2) in.readObject();
            start  = (Date) in.readObject();
            end    = (Date) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new AssertionError(e);
        }
    }
}