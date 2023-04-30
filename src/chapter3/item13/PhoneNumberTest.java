package chapter3.item13;

public class PhoneNumberTest {

    public static void main(String[] args) {
        PhoneNumber phoneNumber = new PhoneNumber(123, 456, 7890);

        PhoneNumber newPhoneNumber = new PhoneNumber(phoneNumber);
        System.out.println(phoneNumber!=newPhoneNumber);
        System.out.println(phoneNumber.getClass() == newPhoneNumber.getClass());
        System.out.println(phoneNumber.equals(newPhoneNumber));

        PhoneNumber newPhoneNumber2 = PhoneNumber.of(phoneNumber);
        System.out.println(phoneNumber!=newPhoneNumber2);
        System.out.println(phoneNumber.getClass() == newPhoneNumber2.getClass());
        System.out.println(phoneNumber.equals(newPhoneNumber2));

    }
}
