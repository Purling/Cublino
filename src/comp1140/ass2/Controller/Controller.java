package comp1140.ass2.Controller;

import comp1140.ass2.GameLogic.ContraCublino;
import comp1140.ass2.GameLogic.Game;
import comp1140.ass2.GameLogic.PurCublino;
import comp1140.ass2.State.Die;
import comp1140.ass2.gui.guiPieces.GuiBoard;
import comp1140.ass2.gui.guiPieces.GuiDie;
import javafx.application.Platform;

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
            switch (type) {
                case EASY_AI -> {
                    PurCublino.PurMove move = EasyAI.purGreedyMove((PurCublino) game.deepClone());
                    applyGeneratedMove(move.getEncodedMove(), game, move.getPossibleState());
                }

                case DIFFICULT_AI -> {
                    PurCublino.PurMove[] moves = ((PurCublino) game).generatePurMoves();
                    DifficultAI difficultAI = new DifficultAI(game);
                    PurCublino.PurMove move = moves[difficultAI.monteCarloMainPur()];
                    applyGeneratedMove(move.getEncodedMove(), game, move.getPossibleState());
                }
            }

        } else {
            switch (type) {
                case EASY_AI -> {
                    ContraCublino.ContraMove move = EasyAI.greedyAIMoveOnly((ContraCublino) game.deepClone());
                    applyGeneratedMove(move.getEncodedMove(), game, move.getPossibleState());
                }

                case DIFFICULT_AI -> {
                    ContraCublino.ContraMove[] moves = ((ContraCublino) game).generateLegalMoves();
                    DifficultAI difficultAI = new DifficultAI(game);
                    ContraCublino.ContraMove move = moves[difficultAI.monteCarloMainContra()];
                    applyGeneratedMove(move.getEncodedMove(), game, move.getPossibleState());
                }
            }
        }

        if (gui != null && type != ControllerType.HUMAN) Platform.runLater(() -> gui.moveComplete());
    }

    void applyGeneratedMove(String encodedMove, Game game, Game finalState) {
        int startX = encodedMove.charAt(0)-97;
        int startY = encodedMove.charAt(1)-49;
        int endX   = encodedMove.charAt(2)-97;
        int endY   = encodedMove.charAt(3)-49;
        System.out.println(startX +"," + startY + " -> " + endX + "," + endY);
        Die movingDie = game.getBoard().getAt(startX, startY);
        game.applyStep(movingDie, endX + "" + endY);
        for (Die d : game.getCurrentPlayer().getDice()) {
            if (d != movingDie && finalState.getBoard().getAt(d.getX(), d.getY()) == null) {
                d.delete();
            }
        }
        for (Die d : game.getOtherPlayer().getDice()) {
            if (d != movingDie && finalState.getBoard().getAt(d.getX(), d.getY()) == null) {
                d.delete();
            }
        }
    }
}
