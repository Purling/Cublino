package comp1140.ass2.State;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ziling Ouyang, Zane Gates
 */
public class Players implements Serializable{
    /**
     * Store the information of the player's dices
     */
    private List<Die> myDice = new ArrayList<>();

    boolean isWhite;

    public List<Die> getDice() {
        return myDice;
    }

    /**
     * Create a deep copy of the Players object
     *
     * @return a deep copy of the Players object
     */
    public Players deepClone() {
        Players obj = new Players(this.isWhite);
        this.myDice.forEach((die) -> obj.addToDice(die.deepClone()));
        return obj;
    }

    public void addToDice(Die die) {
        myDice.add(die);
    }

    public Players(boolean isWhite) {
        this.isWhite = isWhite;
    }

    @Override
    public String toString() {
        return isWhite ? "White" : "Black";
    }

    public boolean isWhite() {
        return this.isWhite;
    }
}
