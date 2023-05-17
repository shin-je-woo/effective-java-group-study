package chapter6.item35;

enum DayOfWeekFiled {
    MONDAY("1"),
    TUESDAY("2"),
    WEDNESDAY("3"),
    THURSDAY("4"),
    FRIDAY("5"),
    SATURDAY("6"),
    SUNDAY("7");

    private final String displayName;

    DayOfWeekFiled(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}