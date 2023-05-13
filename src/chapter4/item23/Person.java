package chapter4.item23;

abstract class Person {
    abstract String determinePermission();
}

class Admin extends Person{
    @Override
    String determinePermission() {
        return "All";
    }
}

class Employee extends Person{
    final int empLevel;

    Employee(int empLevel) {
        this.empLevel = empLevel;
    }

    @Override
    String determinePermission() {
        return empLevel >= 5 ? "Write" : "Read";
    }
}

class MasterEmployee extends Employee{
    MasterEmployee() {
        super(0);
    }
}