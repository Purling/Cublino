package comp1140.ass2.Controller;

import comp1140.ass2.GameLogic.Game;
import comp1140.ass2.gui.guiPieces.GuiBoard;
import comp1140.ass2.gui.guiPieces.GuiDie;

public class Controller {
    public enum ControllerType {HUMAN, EASY_AI, DIFFICULT_AI};

    ControllerType type;
    String name;
    GuiDie.Skin diceSkin;

    /**
     * Constructor for Controller
     */
    public Controller(ControllerType type, String name, GuiDie.Skin diceSkin) {
        this.type = type;
        this.name = name;
        this.diceSkin = diceSkin;
    }

    public ControllerType getType() {
        return type;
    }

    public GuiDie.Skin getDiceSkin() {
        return diceSkin;
    }

    public String getName() {
        return name;
    }

    /**
     * If the player is AI, then automatically choose the move;
     * @param game
     */
    public void requestMove(Game game, GuiBoard gui) {
        //TODO: make the move
        if (type != ControllerType.HUMAN) gui.moveComplete();
    }
}
