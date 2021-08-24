package comp1140.ass2.State;

import comp1140.ass2.State.Die;

public class Players {
    /**
     * Store the information of the player's dices
     */
    Die[] myDice;

    /**
     * Get the information of a particular dice from a series of dices
     * @param i
     * @return
     */

    public Die getDice(int i) {
        return myDice[i];
    }
}
