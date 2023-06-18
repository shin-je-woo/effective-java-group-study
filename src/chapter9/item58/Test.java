package chapter9.item58;

import java.util.*;

public class Test {
    static class Deck{
        enum Suit{CLUB, DIAMOND, HEART, SPADE}
        enum Rank{ACE, DEUCE, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, THE, JACK, QUEEN, KING}

        static Collection<Suit> suits = Arrays.asList(Suit.values());
        static Collection<Rank> ranks = Arrays.asList(Rank.values());
        static List<Card> deck = new ArrayList<>();

        public Deck() {
            for(Iterator<Suit> i = suits.iterator(); i.hasNext(); ){
                for(Iterator<Rank> j = ranks.iterator(); j.hasNext(); ){
                    deck.add(new Card(i.next(), j.next()));
                }
            }
        }

        class Card {
            Suit suit;
            Rank rank;

            public Card(Suit suit, Rank rank) {
                this.suit = suit;
                this.rank = rank;
            }
        }
    }

    public static void main(String[] args) {
        Deck deck = new Deck();
    }
}
