package de.htwberlin.kbe.gruppe4.inter;

public class Rules {
    private boolean drawTwoOnSeven;
    private boolean chooseSuitOnJack;
    private boolean reverseOnAce;
    private boolean chosenSuitTemporarilyEnabled;
    private Card.Suit suit;

    public Rules() {
        drawTwoOnSeven = false;
        chooseSuitOnJack = false;
        reverseOnAce = false;
        chosenSuitTemporarilyEnabled = false;
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
    public void setChosenSuitTemporarilyEnabled(boolean chosenSuitTemporarilyEnabled) {
        this.chosenSuitTemporarilyEnabled = chosenSuitTemporarilyEnabled;
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
    public boolean isChosenSuitTemporarilyEnabled() {
        return chosenSuitTemporarilyEnabled;
    }

}