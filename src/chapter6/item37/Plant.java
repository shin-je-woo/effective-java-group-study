package chapter6.item37;

import java.util.HashSet;
import java.util.Set;

public class Plant {
    enum LifeCycle { ANNUAL, PERNNIAL, BIENNIAL }

    final String name;
    final LifeCycle lifeCycle;

    Plant(String name, LifeCycle lifeCycle){
        this.name = name;
        this.lifeCycle = lifeCycle;
    }

    @Override
    public String toString() {
        return name;
    }

}
