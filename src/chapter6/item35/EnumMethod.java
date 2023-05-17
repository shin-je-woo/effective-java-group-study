package chapter6.item35;

public class EnumMethod {
    public static void main(String[] args) {
        DayOfWeek day = DayOfWeek.TUESDAY;
        int ordinal = day.ordinal();

        System.out.println("Ordinal: " + ordinal);
        // Output: Ordinal: 1

        // 이후에 새로운 상수를 추가하면 기존 코드와 호환성이 깨짐
        DayOfWeek newDay = DayOfWeek.valueOf("WEEKEND");
        System.out.println("Ordinal of WEEKEND: " + newDay.ordinal());
//         Output: Ordinal of WEEKEND: 7
        
        
        DayOfWeekFiled nD = DayOfWeekFiled.MONDAY;
        System.out.println(nD.getDisplayName());
        
    }
}
