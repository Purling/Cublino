package comp1140.ass2.state;

import comp1140.ass2.helperclasses.DeepCloneable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A representation of a player by their colour and dice
 *
 * @author Ziling Ouyang, Zane Gates
 */
public class Players implements Serializable, DeepCloneable<Players> {

    /**
     * Store the information of the player's dices
     */
    private final List<Die> myDice = new ArrayList<>();

    /**
     * The colour of the player
     */
    boolean isWhite;

    /**
     * Constructor for Players
     */
    public Players(boolean isWhite) {
        this.isWhite = isWhite;
    }

    /**
     * Getter for myDice
     */
    public List<Die> getDice() {
        return myDice;
    }

    /**
     * Implements the deepClone method from DeepCloneable interface
     */
    public Players deepClone() {
        Players obj = new Players(this.isWhite);
        this.myDice.forEach((die) -> obj.addToDice(die.deepClone()));
        return obj;
    }

    /**
     * Adds a Die to the list of dice which each player has
     */
    public void addToDice(Die die) {
        myDice.add(die);
    }

    /**
     * Overriding toString method
     */
    @Override
    public String toString() {
        return isWhite ? "White" : "Black";
    }

    /**
     * Getter for isWhite
     */
    public boolean isWhite() {
        return this.isWhite;
    }
}
