package comp1140.ass2.GameLogic;

import comp1140.ass2.State.Boards;
import comp1140.ass2.State.Die;

import java.util.Arrays;

/** A type of cublino
 /* extends from Game class*/

public class PurCublino extends Game {

    public PurCublino(){
        super();
    }

    public PurCublino(boolean isWhite){
        super(isWhite);
    }

    @Override
    protected boolean isMoveLegal() {
        return false;
    }

    @Override
    public boolean isDiceAmountCorrect(Boards board){
        return (board.getBlackPlayer().getDice().length + board.getWhitePlayer().getDice().length == 2 * Boards.BOARD_DIMENSION);
    }

    @Override
    public boolean hasBothWon(Boards board){

        Die[] white = board.getWhitePlayer().getDice();
        Die[] black = board.getBlackPlayer().getDice();

        return Arrays.stream(white).allMatch(Die::isWhiteDieFinished) && Arrays.stream(black).allMatch(Die::isBlackDieFinished);
    }

    public boolean isJumpValid(Boards board,String startPosition, String endPosition) {

        String middle = Boards.getMiddlePosition(startPosition, endPosition);

        return !isMoveBackwards(startPosition,endPosition)
                && (board.getAt(Boards.getPositionX(middle), Boards.getPositionY(middle)) != null)
                && (Boards.sameAxis(startPosition, endPosition));
    }

    public boolean isStepValidPur(String state, String step) {
        return PurCublino.checkPurStepValid(state, step, PurCublino::checkBoardStartAndEndPosition);
   }

    public static boolean checkBoardStartAndEndPosition(Boards board, String start, String end) {
        return board.getAtPosition(start) != null || board.getAtPosition(end) == null;
    }

    public interface EndOccupied {
        boolean test(Boards board, String start, String end);
    }

    public static Boolean checkPurStepValid(String state, String step, EndOccupied endOccupied) {

        Boards board = new Boards(state);
        PurCublino purCublino = new PurCublino(Character.isUpperCase(state.charAt(0)));
        Boards.Positions positions = board.statesToPositions(step);
        String start = positions.getStart();
        String end = positions.getEnd();

        if(endOccupied.test(board, start, end)) return false;

        if (board.isAdjacent(start, end)) {
            return !purCublino.isMoveBackwards(start,end);
        } else if (board.getManhattanDistance(start,end) == 2) {
            return purCublino.isJumpValid(board, start, end);
        } else {
            return false;
        }
    }

    public boolean isGameValid(Boards board){
        return (isDiceAmountCorrect(board) && !hasBothWon(board));
    }
}
