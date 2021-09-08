package comp1140.ass2.State;

import comp1140.ass2.State.Die;

public class Players {
    /**
     * Store the information of the player's dices
     */
    Die[] myDice;

    boolean isWhite;

    /**
     * Get the information of a particular die from a series of dices
     * @param i
     * @return
     */
    public Die getDie(int i) {
        return myDice[i];
    }

    public Die[] getDice() {
        return myDice;
    }

    public Players(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public void setDice(Die[] dice) {
        myDice = dice;
    }

    public void setOneDie(int index, Die die) {
        myDice[index] = die;
    }

    @Override
    public String toString() {
        return "" + isWhite;
    }

    public boolean isWhite() {
        return this.isWhite;
    }
}
