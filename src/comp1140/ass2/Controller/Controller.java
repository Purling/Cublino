package comp1140.ass2.Controller;

public class Controller {
    /**
     * State whether the player is a human or an AI
     */
    boolean isHuman;

    /**
     * Get the information of the player
     * If the player is an AI, then chooseMove will be called for the AIs
     * @return
     */
    public boolean getIsHuman() {
        // Check whether the player is a human or AI
        return isHuman;
    }


    /**
     * If the player is AI, then automatically choose the move;
     */
    public void requestMove(){}
}
