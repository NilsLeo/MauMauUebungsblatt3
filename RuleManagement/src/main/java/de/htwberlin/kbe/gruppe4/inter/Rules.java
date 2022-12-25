package de.htwberlin.kbe.gruppe4.inter;

public class Rules {
    private boolean drawTwoOnSeven;
    private boolean chooseSuitOnJack;
    private boolean reverseOnAce;
    private boolean drawUntilPlay;
    private boolean announceMauMau;

    public Rules() {
        drawTwoOnSeven = false;
        chooseSuitOnJack = false;
        reverseOnAce = false;
        drawUntilPlay = false;
        announceMauMau = false;
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

    public void setDrawUntilPlay(boolean drawUntilPlay) {
        this.drawUntilPlay = drawUntilPlay;
    }

    public void setAnnounceMauMau(boolean announceMauMau) {
        this.announceMauMau = announceMauMau;
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

    public boolean isDrawUntilPlay() {
        return drawUntilPlay;
    }

    public boolean isAnnounceMauMau() {
        return announceMauMau;
    }
}