package chapter6.item36;

import java.util.EnumSet;
import java.util.Set;

public class License {
    public enum Type {PC, MOBILE, MSG, MOBILE_MSG, MAIL}

    public void applyLicense(Set<Type> licenses){
        System.out.println(licenses);
    }

    public static void main(String[] args) {
        License license = new License();
        license.applyLicense(EnumSet.of(Type.MOBILE, Type.MOBILE_MSG));
    }
}
