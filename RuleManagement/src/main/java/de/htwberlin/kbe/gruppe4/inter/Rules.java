package de.htwberlin.kbe.gruppe4.inter;

public class Rules {
    private boolean drawTwoOnSeven;
    private boolean chooseSuitOnJack;
    private boolean reverseOnAce;
    private boolean reversed;
    private Card.Suit suit;

    public Rules() {
        drawTwoOnSeven = false;
        chooseSuitOnJack = false;
        reverseOnAce = false;
        reversed = false;
    }
    public void setSuit(Card.Suit suit){
        this.suit = suit;
    }
    public void setDrawTwoOnSeven(boolean drawTwoOnSeven) {
        this.drawTwoOnSeven = drawTwoOnSeven;
    }

    public void setChooseSuitOnJack(boolean chooseSuitOnJack) {
        this.chooseSuitOnJack = chooseSuitOnJack;
    }

    public void setReverseOnAce(boolean reverseOnAce) {
        this.reverseOnAce = reverseOnAce;
    }

    public Card.Suit getSuit(){
        return suit;
    }
    
    public boolean isDrawTwoOnSeven() {
        return drawTwoOnSeven;
    }

    public boolean isChooseSuitOnJack() {
        return chooseSuitOnJack;
    }

    public boolean isReverseOnAce() {
        return reverseOnAce;
    }
    public void setReversed(boolean reversed) {
        this.reversed = reversed;
    }
    public boolean isReversed() {
        return reversed;
    }

}