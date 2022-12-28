package de.htwberlin.kbe.gruppe4.inter;

public class Rules {
    private boolean drawTwoOnSeven;
    private boolean chooseSuitOnJack;
    private boolean reverseOnAce;

    public Rules() {
        drawTwoOnSeven = false;
        chooseSuitOnJack = false;
        reverseOnAce = false;
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

    
    public boolean isDrawTwoOnSeven() {
        return drawTwoOnSeven;
    }

    public boolean isChooseSuitOnJack() {
        return chooseSuitOnJack;
    }

    public boolean isReverseOnAce() {
        return reverseOnAce;
    }

}