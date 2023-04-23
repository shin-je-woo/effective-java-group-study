package chapter1.item06;

public class RomanNumerals {

    static boolean isRomanNumeral(String s) {
        return s.matches("^(?=[MDCLXVI])M*(C[MD]|D?C{0,3})"
                + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    }
}