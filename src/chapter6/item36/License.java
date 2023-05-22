package chapter6.item36;

import java.util.EnumSet;
import java.util.Set;

public class License {
    public enum Type {PC, MOBILE, MSG, MOBILE_MSG, MAIL}


    static class MyUser {
        private final String name;
        private final Set<License.Type> lic;

        public MyUser(String name, Set<License.Type> lic) {
            this.name = name;
            this.lic = lic;
        }
    }

    public static void main(String[] args) {
        MyUser user1 = new MyUser("유저1", EnumSet.of(License.Type.MOBILE, License.Type.MOBILE_MSG));
        System.out.println(user1.lic);
    }
}
