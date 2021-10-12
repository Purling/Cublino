package comp1140.ass2.Controller;

import comp1140.ass2.gui.guiPieces.GuiDie;

public class Controller {
    /**
     * State whether the player is a human or an AI
     */
    boolean isHuman;

    String name;
    GuiDie.Skin diceSkin;

    public Controller(boolean isHuman, String name, GuiDie.Skin diceSkin) {
        this.isHuman = isHuman;
        this.name = name;
        this.diceSkin = diceSkin;
    }

    /**
     * Get the information of the player
     * If the player is an AI, then chooseMove will be called for the AIs
     * @return boolean isHuman
     */
    public boolean getIsHuman() {
        // Check whether the player is a human or AI
        return isHuman;
    }

    public GuiDie.Skin getDiceSkin() {
        return diceSkin;
    }

    public String getName() {
        return name;
    }

    /**
     * If the player is AI, then automatically choose the move;
     */
    public void requestMove(){}
}
