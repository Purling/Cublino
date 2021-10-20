package comp1140.ass2.Controller;

import comp1140.ass2.GameLogic.ContraCublino;
import comp1140.ass2.GameLogic.Game;
import comp1140.ass2.GameLogic.PurCublino;
import comp1140.ass2.State.Die;
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
                    ContraCublino.ContraMove move = EasyAI.greedyAIMoveOnly((ContraCublino) game.deepClone());
                    System.out.println(move.getEncodedMove());
                    int startX = move.getEncodedMove().charAt(0)-97;
                    int startY = move.getEncodedMove().charAt(1)-49;
                    int endX   = move.getEncodedMove().charAt(2)-97;
                    int endY   = move.getEncodedMove().charAt(3)-49;
                    System.out.println(startX +"," + startY + " -> " + endX + "," + endY);
                    Die movingDie = game.getBoard().getAt(startX, startY);
                    game.applyStep(movingDie, endX + "" + endY);
                    for (Die d : game.getCurrentPlayer().getDice()) {
                        if (d != movingDie && move.getPossibleState().getBoard().getAt(d.getX(), d.getY()) == null) {
                            d.delete();
                        }
                    }
                    for (Die d : game.getOtherPlayer().getDice()) {
                        if (d != movingDie && move.getPossibleState().getBoard().getAt(d.getX(), d.getY()) == null) {
                            d.delete();
                        }
                    }
                    gui.moveComplete();
                }

                case DIFFICULT_AI -> {
                    ContraCublino.ContraMove[] moves = ((ContraCublino) game).generateLegalMoves();
                    DifficultAI difficultAI = new DifficultAI(game);
                    ContraCublino.ContraMove move = moves[difficultAI.monteCarloMain()];
                    System.out.println(move.getEncodedMove());
                    int startX = move.getEncodedMove().charAt(0)-97;
                    int startY = move.getEncodedMove().charAt(1)-49;
                    int endX   = move.getEncodedMove().charAt(2)-97;
                    int endY   = move.getEncodedMove().charAt(3)-49;
                    System.out.println(startX +"," + startY + " -> " + endX + "," + endY);
                    Die movingDie = game.getBoard().getAt(startX, startY);
                    game.applyStep(movingDie, endX + "" + endY);
                    for (Die d : game.getCurrentPlayer().getDice()) {
                        if (d != movingDie && move.getPossibleState().getBoard().getAt(d.getX(), d.getY()) == null) {
                            d.delete();
                        }
                    }
                    for (Die d : game.getOtherPlayer().getDice()) {
                        if (d != movingDie && move.getPossibleState().getBoard().getAt(d.getX(), d.getY()) == null) {
                            d.delete();
                        }
                    }
                    gui.moveComplete();
                }
            }
        }
            //TODO: make the move
    }
}
