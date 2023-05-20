package chapter6.item37;

public class TestPhase {
    public static void main(String[] args) {
        System.out.println(Phase.Transition.from(Phase.LIQUID, Phase.GAS));
        System.out.println(Phase.Transition.from(Phase.SOLID, Phase.LIQUID));
    }
}
