package chapter8.item51;

public class Game {

    //도우미 클래스
    static class Card {
        private String rank;
        private String suit;
    }

    //기존
    public void dealing(String gamerName, String rank, String suit) {
        System.out.println("gamerName = " + gamerName);
        System.out.println("ran, = " + rank);
        System.out.println("suit = " + suit);
    }

    //도우미 클래스 활용
    public void dealing(String gamerName, Card card) {
        System.out.println("gamerName = " + gamerName);
        System.out.println("card = " + card);
    }

    //빌더 패턴 활용
    public void dealing(String gamerName, CardBuilder cardBuilder) {
        if(!cardBuilder.execute()){
            throw new IllegalArgumentException("error");
        }
        Card card = new Card();
        card.rank = cardBuilder.getRank();
        card.suit = cardBuilder.getSuit();
        dealing(gamerName, card);
    }

}
