package chapter3.item13;

public class  PhoneNumberTest {

    public static void main(String[] args) {
        PhoneNumber phoneNumber = new PhoneNumber(123, 456, 7890);

        //clone
        PhoneNumber clone = phoneNumber.clone();
        System.out.println(phoneNumber!=clone);
        System.out.println(phoneNumber.getClass() == clone.getClass());
        System.out.println(phoneNumber.equals(clone));

        //생성자 복사
        PhoneNumber newPhoneNumber = new PhoneNumber(phoneNumber);
        System.out.println(phoneNumber!=newPhoneNumber);
        System.out.println(phoneNumber.getClass() == newPhoneNumber.getClass());
        System.out.println(phoneNumber.equals(newPhoneNumber));

        //팩터리 복사
        PhoneNumber newPhoneNumber2 = PhoneNumber.of(phoneNumber);
        System.out.println(phoneNumber!=newPhoneNumber2);
        System.out.println(phoneNumber.getClass() == newPhoneNumber2.getClass());
        System.out.println(phoneNumber.equals(newPhoneNumber2));

    }
}
