package chapter4.item23;

class BadPerson {
    /* 안좋은 예시 */
    enum User {EMPLOYEE, ADMIN};

    // 태그 필드 -> final임으로 쓰이지 않는 필드들까지 초기화해야 한다.
    final User user;

    // EMPLOYEE 일 때
    int empLevel;

    BadPerson(int empLevel) {
        user = User.EMPLOYEE;
        this.empLevel = empLevel;
    }

    // ADMIN 일 때
    BadPerson() {
        user = User.ADMIN;
    }

    String determinePermission(){
        switch (user) {
            case ADMIN:
                return "All";
            case EMPLOYEE:
                return empLevel >= 5 ? "Write" : "Read";
            default:
                throw new AssertionError(user);
        }
    }
}
