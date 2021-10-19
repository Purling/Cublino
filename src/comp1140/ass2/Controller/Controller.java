package comp1140.ass2.Controller;

import comp1140.ass2.GameLogic.ContraCublino;
import comp1140.ass2.GameLogic.Game;
import comp1140.ass2.GameLogic.PurCublino;
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
     * @param gui The UI components
     */
    public void requestMove(GuiBoard gui) {
        if (gui.getGame() instanceof PurCublino) {
            if (type != ControllerType.HUMAN) gui.moveComplete();
        } else {
            switch (type) {
                case EASY_AI -> {
                    System.out.println(EasyAI.greedyAI((ContraCublino) gui.getGame()));
                    System.out.println(gui.getGame().getCurrentPlayer());
                    gui.setGame(EasyAI.greedyAI((ContraCublino) gui.getGame()));
                    System.out.println(("\n"));
                    System.out.println(EasyAI.greedyAI((ContraCublino) gui.getGame()));
                    System.out.println(gui.getGame().getCurrentPlayer());
                    gui.moveComplete();
                }

                case DIFFICULT_AI -> {
                    DifficultAI difficultAI = new DifficultAI(gui.getGame());
                    difficultAI.monteCarloMain();
                    gui.moveCompleteAI();
                }
            }
        }
            //TODO: make the move
    }
}
