package comp1140.ass2.Controller;

import comp1140.ass2.GameLogic.ContraCublino;
import comp1140.ass2.GameLogic.Game;
import comp1140.ass2.GameLogic.PurCublino;
import comp1140.ass2.gui.guiPieces.GuiBoard;
import comp1140.ass2.gui.guiPieces.GuiDie;

public class Controller {
    public enum ControllerType {HUMAN, EASY_AI, DIFFICULT_AI}

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
    public void requestMove(Game game, GuiBoard gui) {
        if (game instanceof PurCublino) {
            if (type != ControllerType.HUMAN) gui.moveComplete();
        } else {
            switch (type) {
                case EASY_AI -> {
                    Game.DieMove move = Game.findMove(game, EasyAI.greedyAI((ContraCublino) game.deepClone()));
                    game.applyStep(move.getDie(), move.getEndPosition());
                    gui.moveComplete();
                }

                case DIFFICULT_AI -> {
                    DifficultAI difficultAI = new DifficultAI(game);
                    difficultAI.monteCarloMain();
                    gui.moveComplete();
                }
            }
        }
            //TODO: make the move
    }
}
