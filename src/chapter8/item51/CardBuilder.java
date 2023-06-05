package chapter8.item51;

public class CardBuilder {
    private String rank;
    private String suit;

    public CardBuilder setRank(String rank) {
        this.rank = rank;
        return this;
    }

    public CardBuilder setSuit(String suit) {
        this.suit = suit;;
        return this;
    }

    public boolean  execute() {
        if (rank.isEmpty() || suit.isEmpty() ) {
            throw new IllegalArgumentException("");
        }
        return true;
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }
}
