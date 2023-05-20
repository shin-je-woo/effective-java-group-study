package chapter6.item37;

import java.util.*;

public class ExampleArray {
    public static void main(String[] args) {

        Plant[] garden = {new Plant("매화", Plant.LifeCycle.PERNNIAL), new Plant("매화2", Plant.LifeCycle.PERNNIAL), new Plant("매화3", Plant.LifeCycle.ANNUAL)};

        @SuppressWarnings("unchecked")
        Set<Plant>[] plantByLifeCycle = (Set<Plant>[]) new Set[Plant.LifeCycle.values().length];

        for(int i=0; i < plantByLifeCycle.length; i++)
            plantByLifeCycle[i] = new HashSet<>();

        for (Plant p : garden)
            plantByLifeCycle[p.lifeCycle.ordinal()].add(p);


        for(int i=0; i < plantByLifeCycle.length; i++){
            System.out.printf("%s: %s%n", Plant.LifeCycle.values()[i], plantByLifeCycle[i]);
        }
    }






}
