package comp1140.ass2.State;

import comp1140.ass2.State.Die;

public class Players {
    /**
     * Store the information of the player's dices
     */
    Die[] myDice;

    boolean isWhite;

    /**
     * store the current number of turns the player has already done
     */
    int turn;

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
        return isWhite ? "White" : "Black";
    }

    public boolean isWhite() {
        return this.isWhite;
    }
}
