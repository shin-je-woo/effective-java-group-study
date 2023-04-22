package chapter1.item04;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.lang.Math;
import java.util.Collections;

public class DateUtil {
    // 인스턴스화 방지
    private DateUtil() {
        throw new AssertionError();
    }

    private static Date now = new Date();
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd 'at' HH:mm:ss z");

    public static String today(){
        return formatter.format(now);
    }

}


