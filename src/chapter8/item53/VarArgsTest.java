package chapter8.item53;

import java.util.Arrays;

public class VarArgsTest {

    public static int minRuntimeEx(int... args) {
        return Arrays.stream(args)
                .min()
                .orElseThrow(() -> new IllegalArgumentException("인수가 1개 이상 필요합니다."));
    }

    public static int min(int initArg, int... args) {
        return Arrays.stream(args)
                .min()
                .orElse(initArg);
    }

    public static void main(String[] args) {
        minRuntimeEx();
        System.out.println(min(1));
    }
}
