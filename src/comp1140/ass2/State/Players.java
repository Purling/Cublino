package comp1140.ass2.State;
import java.util.ArrayList;
import java.util.List;

public class Players {
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
