package chapter1.item01.flyweight.after;

public class Client {

    public static void main(String[] args) {
        Character c1 = new Character('h', "red", FontFactory.of("Nanum:12"));
        Character c2 = new Character('e', "blue", FontFactory.of("Nanum:12"));
        Character c3 = new Character('l', "yellow", FontFactory.of("Nanum:12"));
        Character c4 = new Character('l', "green", FontFactory.of("Nanum:12"));
        Character c5 = new Character('o', "white", FontFactory.of("Nanum:12"));
    }
}
