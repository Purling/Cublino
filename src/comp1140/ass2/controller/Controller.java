package comp1140.ass2.controller;

import comp1140.ass2.gamelogic.ContraCublino;
import comp1140.ass2.gamelogic.Game;
import comp1140.ass2.gamelogic.PurCublino;
import comp1140.ass2.gui.guipieces.GuiBoard;
import comp1140.ass2.gui.guipieces.GuiDie;
import comp1140.ass2.state.Die;
import javafx.application.Platform;

/**
 * Represents one of the players in the game, which can be
 * owned by either a human or an AI of graduated difficulties
 * <p>
 * Author: Whole group
 */
public class Controller {

    /**
     * The type of controller e.g., Human, AI
     */
    ControllerType type;

    /**
     * The name of the player
     */
    String name;

    /**
     * The texture of the dice
     */
    GuiDie.Skin diceSkin;

    /**
     * Constructs a controller with all of relevant settings
     *
     * @param type     the owner of this controller
     * @param name     the in-game name
     * @param diceSkin the die skin used
     */
    public Controller(ControllerType type, String name, GuiDie.Skin diceSkin) {
        this.type = type;
        this.name = name;
        this.diceSkin = diceSkin;
    }

    /**
     * Getter for type
     */
    public ControllerType getType() {
        return type;
    }

    /**
     * Getter for diceSkin
     */
    public GuiDie.Skin getDiceSkin() {
        return diceSkin;
    }

    /**
     * Getter for name
     */
    public String getName() {
        return name;
    }

    /**
     * If the player is AI, then automatically choose the move;
     *
     * @param game The game state from which to make a move
     * @param gui  The board that should be updated once a move has been made
     */
    public void requestMove(Game game, GuiBoard gui) {
        // Call the relevant AI on the relevant type of game, if not controlled by a HUMAN
        // Once the move has been calculated, apply it to the initial game state
        if (game instanceof PurCublino) {
            switch (type) {
                case EASY_AI -> {
                    PurCublino.PurMove move = EasyAI.purGreedyMove((PurCublino) game.deepClone());
                    applyGeneratedMove(move.getEncodedMove(), game, move.getPossibleState());
                }

                case DIFFICULT_AI -> {
                    PurCublino.PurMove[] moves = ((PurCublino) game).generateLegalMoves();
                    DifficultAI difficultAI = new DifficultAI(game);
                    PurCublino.PurMove move = moves[difficultAI.monteCarloMain()];
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
                    ContraCublino.ContraMove move = moves[difficultAI.monteCarloMain()];
                    applyGeneratedMove(move.getEncodedMove(), game, move.getPossibleState());
                }
            }
        }

        // If a move has been made and an associated gui is provided, alert the GUI that a move has been made
        if (gui != null && type != ControllerType.HUMAN) Platform.runLater(() -> {
            gui.moveComplete();
        });
    }

    /**
     * Applies an encoded move to a game to make it match the given state after the move has been made
     * while preserving object references to the initial game state and its dice
     *
     * @param encodedMove the encoded move in either Contra or Pur
     * @param game        the initial game to make the application from
     * @param finalState  the resulting state that should be identical to the final game
     */
    private void applyGeneratedMove(String encodedMove, Game game, Game finalState) {
        // From the string encoding of the move, discern the starting and ending coordinates
        int startX = encodedMove.charAt(0) - 97;
        int startY = encodedMove.charAt(1) - 49;
        int endX = encodedMove.charAt(2) - 97;
        int endY = encodedMove.charAt(3) - 49;

        Die movingDie = game.getBoard().getAt(startX, startY);
        game.applyStep(movingDie, endX + "" + endY);

        // Any other dice that are not present on the final board must have been removed,
        // which we will indicate through the deleted die flag
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

    /**
     * Enum representing the types of controllers
     */
    public enum ControllerType {HUMAN, EASY_AI, DIFFICULT_AI}
}
