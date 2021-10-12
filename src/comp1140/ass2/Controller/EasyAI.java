package comp1140.ass2.Controller;

import comp1140.ass2.GameLogic.ContraCublino;
import comp1140.ass2.State.Boards;
import comp1140.ass2.State.Die;
import comp1140.ass2.gui.guiPieces.GuiDie;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Ziling Ouyang, Zane Gates
 */
public class EasyAI extends Controller { // Maybe split into two i.e., PurEasyAI and ContraEasyAI

    /**
     * Greedy AI for contra
     * @param currentGameState The current condition of the game
     * @return The board after the move determined by the AI is played
     */
    public Boards greedyAI(ContraCublino currentGameState) {
        ContraCublino.ContraMove[] legalMoves = currentGameState.generateLegalMoves();
        List<Integer> evaluatedMoves = Arrays.stream(legalMoves).map((x) -> greedyEvaluation(x.getPossibleState())).collect(Collectors.toList());
        int generatedMoveIndex = evaluatedMoves.indexOf(evaluatedMoves.stream().max(Integer::compareTo).orElse(0));
        assert generatedMoveIndex != -1;
        return legalMoves[generatedMoveIndex].getPossibleState().getBoard();
    }

    /**
     * "AI" which just generates a random move
     * @param currentGameState The current game state
     * @return The board after the move has been applied
     */
    public Boards randomMove(ContraCublino currentGameState) {
        Random rand = new Random();
        int randomMove = rand.nextInt(currentGameState.generateLegalMoves().length);
        return currentGameState.generateLegalMoves()[randomMove].getPossibleState().getBoard();
    }

    /**
     * A heuristic for contra which is simplistic. It looks at how far along the farthest die is
     * @param currentGameState The current condition of the game
     * @return A numerical evaluation of the game state
     */
    public static int greedyEvaluation(ContraCublino currentGameState) {
        boolean isWhite = currentGameState.getCurrentPlayer().isWhite();
        List<Die> playerDice = currentGameState.getCurrentPlayer().getDice();

        if (isWhite) {
            if (playerDice.stream().anyMatch(Die::isWhiteDieFinished)) return 100;
            if (playerDice.stream().anyMatch(Die::isBlackDieFinished)) return -100;
        } else {
            if (playerDice.stream().anyMatch(Die::isBlackDieFinished)) return 100;
            if (playerDice.stream().anyMatch(Die::isWhiteDieFinished)) return -100;
        }
        return playerDice.stream().mapToInt(Die::getY).max().orElse(-99) * (100/7);
    }

    /**
     * If the player is AI, then automatically choose the move;
     */
    public void requestMove(){}

    /**
     * Constructor
     */
    public EasyAI(boolean isWhite) {
        super(false, "Easy AI " + (isWhite ? 1 : 2),
                isWhite ? GuiDie.Skin.PLAIN_WHITE : GuiDie.Skin.PLAIN_BLACK);
    }
}
