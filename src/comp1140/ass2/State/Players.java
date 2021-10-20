package comp1140.ass2.State;
import comp1140.ass2.Controller.DifficultAI;

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

    /**
     * store the current number of turns the player has already done
     */
    int turn;

    public List<Die> getDice() {
        return myDice;
    }

    /**
     * Create a deep copy of the Players object
     *
     * @return a deep copy of the Players object
     */
    public Players deepClone() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (Players) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
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
