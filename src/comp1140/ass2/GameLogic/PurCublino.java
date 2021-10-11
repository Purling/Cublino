package comp1140.ass2.GameLogic;

import comp1140.ass2.State.Boards;
import comp1140.ass2.State.Die;

import java.util.List;

/** A gamemode of Cublino that extends from the Game class
 *
 * @author Ziling Ouyang, Yuechen Liu
 */

public class PurCublino extends Game {

    private static final int JUMP_DISTANCE = 2;

    public PurCublino(){
        super();
    }

    public PurCublino(boolean isWhite, Boards board){
        super(isWhite, board);
    }

    /**
     * Given a valid die, moves the die to the valid position give. It stores the step that has been applied to the die in the stepHistory
     * variable. If the move is invalid, that is also stored in stepHistory under MoveType INVALID.
     *
     * @param die The die to be moved
     * @param endPosition The coordinates to move the die to
     */
    @Override
    public void applyStep(Die die, String endPosition) { //TODO Implement a deep copy of the currentMove into Move array
        if (die.isWhite() != getCurrentPlayer().isWhite()) return;

        Boards clone = board.deepClone();
        boolean firstEntry = true;
        for (Move x : getStepHistory()) {
            if (x.getType() != MoveType.ORIGIN && x.getType() != MoveType.INVALID) firstEntry = false;
        }

        if (board.getAt(endPosition) != null) {
            addToStepHistory(new Move(clone, MoveType.INVALID));
            return;
        }


        String diePosition = die.getPosition();
        int distance = Boards.getManhattanDistance(diePosition, endPosition);
        MoveType moveType;
        boolean correctDie = firstEntry || isDieCorrect(die);

        if (!isMoveBackwards(diePosition, endPosition) && distance == TIP_DISTANCE && correctDie && firstEntry) {
            applyTip(die, endPosition);
            setCurrentMoveDie(die);
            moveType = MoveType.TIP;
        } else if (isJumpValid(diePosition, endPosition) && distance == JUMP_DISTANCE && correctDie) {
            applyJump(die, endPosition);
            setCurrentMoveDie(die);
            moveType = MoveType.JUMP;
        } else {
            moveType = MoveType.INVALID;
        }

        System.out.println(firstEntry);
        if (firstEntry) {
            clearStepHistory();
            addToStepHistory(new Move(clone, MoveType.ORIGIN));
        }
        addToStepHistory(new Move(clone, moveType));

        System.out.println(getStepHistory());
    }

    /**
     *  A boolean function to evaluate if the correct number of dice is on the board.
     * @param board The current board that is being played on
     * @return True if the board contains 14 dice
     */
    @Override
    public boolean isDiceAmountCorrect(Boards board){
        return (board.getBlackPlayer().getDice().size() + board.getWhitePlayer().getDice().size() == 2 * Boards.BOARD_DIMENSION);
    }

    @Override
    public boolean hasBothWon(Boards board){

        List<Die> white = board.getWhitePlayer().getDice();
        List<Die> black = board.getBlackPlayer().getDice();

        return white.stream().allMatch(Die::isWhiteDieFinished) && black.stream().allMatch(Die::isBlackDieFinished);
    }

    public boolean isJumpValid(String startPosition, String endPosition) {

        String middle = Boards.getMiddlePosition(startPosition, endPosition);

        return !isMoveBackwards(startPosition,endPosition)
                && (board.getAt(Boards.getPositionX(middle), Boards.getPositionY(middle)) != null)
                && (Boards.sameAxis(startPosition, endPosition));
    }

    public void applyJump(Die initial, String endPosition) {

        String start = initial.getPosition();

        if(board.getAt(endPosition) == null) {
            initial.setPosition(endPosition);
            board.setAt(endPosition, initial);
            board.setAt(start,null);
        }
    }

    public boolean isGameValid(Boards board){
        return (isDiceAmountCorrect(board) && !hasBothWon(board));
    }

    public static int getWinner(String state){

        int p1 = 0;
        int p2 = 0;
        int p1s = 0;
        int p2s = 0;
        int i = 0;
        while (i < 14){
            String s = state.substring(3*i+1, 3*i+4);
            if (s.charAt(0) >= 'A' && s.charAt(0) <= 'Z' && s.charAt(2) == '7'){
                p1++;
                p1s += (s.charAt(0) - 'A')/4 + 1;
                i++;
            }
            else if (s.charAt(0) >= 'a' && s.charAt(0) <= 'z' && s.charAt(2) == '1'){
                p2++;
                p2s += (s.charAt(0) - 'a')/4 + 1;
                i++;
            }
            else i++;
        }
        if (p1 == 7 || p2 == 7) {
            if (p1s > p2s) return 1;
            else if(p1s < p2s) return 2;
            else return 3;
        }
        return 0;
    }
}
