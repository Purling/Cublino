package comp1140.ass2.GameLogic;

import comp1140.ass2.State.Boards;
import comp1140.ass2.State.Die;

import java.util.*;

import static comp1140.ass2.GameLogic.Game.MoveType.TIP;

/** A gamemode of Cublino extends from Game class
 *
 * @author Ziling Ouyang, Yuechen Liu
 */
public class ContraCublino extends Game {

    public ContraCublino(){
    }

    /**
     * Constructor for ContraCublino
     */
    public ContraCublino(boolean isWhite, Boards board){
        super(isWhite, board);
    }

    @Override
    public boolean isDiceAmountCorrect(Boards board){
        return (board.getBlackPlayer().getDice().size() + board.getWhitePlayer().getDice().size() <= 2 * Boards.BOARD_DIMENSION
        && board.getBlackPlayer().getDice().size() + board.getWhitePlayer().getDice().size() >= 2);
    }

    /**
     * Returns if both players have won (an invalid state)
     * @param board The board being played
     * @return True if both players have simultaneously won, false otherwise
     */
    @Override
    public boolean hasBothWon(Boards board){
        List<Die> white = board.getWhitePlayer().getDice();
        List<Die> black = board.getBlackPlayer().getDice();
        return white.stream().filter(Die::isWhiteDieFinished).count() + black.stream().filter(Die::isBlackDieFinished).count() > 1;
    }

    /**
     * Given a valid die, moves the die to the valid position give. It stores the step that has been applied to the die in the stepHistory
     * variable. If the move is invalid, that is also stored in stepHistory under MoveType INVALID.
     *
     * @param die The die to be moved
     * @param endPosition The coordinates to move the die to
     */
    @Override
    public void applyStep(Die die, String endPosition) {
        if (die.isWhite() != getCurrentPlayer().isWhite()) return;

        Boards clone = board.deepClone();
        boolean firstEntry = getStepHistory().isEmpty();

        if (board.getAt(endPosition) != null) return;
        if (firstEntry) addToStepHistory(new Move(clone, MoveType.ORIGIN));
        String diePosition = die.getPosition();
        boolean correctDistance = Boards.getManhattanDistance(diePosition, endPosition) == TIP_DISTANCE;

        if (!isMoveBackwards(diePosition, endPosition) && correctDistance && isDieCorrect(die) && firstEntry) {
            applyTip(die, endPosition);
            addToStepHistory(new Move(clone, TIP));
            setCurrentMoveDie(die.deepClone());
        }

        if(board.getAdjacentDie(die) != null && !(board.getAdjacentDie(die).length == 0)) {
            battle(die);
        }
    }

    /**
     * Method which compares adjacent die of opposing players and eliminates the loser according to the Contra gamemode
     */
    public void battle(Die adjacentDie) {
        Die[] potentialBattles = Arrays.stream(board.getAdjacentDie(adjacentDie)).filter((x) -> x.isWhite() != adjacentDie.isWhite()).toArray(Die[]::new);
        Set<Die> eliminate = new HashSet<>();

        if (potentialBattles.length == 0) return;

        for (Die die : potentialBattles) {
            if (adjacentDie.getTop() > die.getTop()) {
                eliminate.add(die);
            } else if (adjacentDie.getTop() < die.getTop()) {
                eliminate.add(adjacentDie);
            }
        }
        eliminate.forEach(this::removeDie);
    }

    public boolean isGameValid(Boards board){
        return (isDiceAmountCorrect(board) && !hasBothWon(board));
    }
}
