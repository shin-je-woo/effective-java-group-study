package chapter6.item36;

import java.util.EnumSet;
import java.util.Set;

public class Licenses {
    public static final int PC          = 1 << 0; // 1
    public static final int MOBILE      = 1 << 1; // 2
    public static final int MSG         = 1 << 2; // 4
    public static final int MOBILE_MSG  = 1 << 3; // 8
    public static final int MAIL        = 1 << 4; // 16

    int flags = 0;
    public void applyLicense(int licenses){
        this.flags = licenses;
        if((flags & PC) != 0) System.out.println("PC");
        if((flags & MSG) != 0) System.out.println("MSG");
    }

    public static void main(String[] args) {
        Licenses license = new Licenses();
        license.applyLicense(PC | MSG);
    }
}
