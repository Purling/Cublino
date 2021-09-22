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
    public Boards applyStep(Boards board, Die die, String endPosition) {

        if(board.getAt(endPosition) != null) return null;
        int tipDistance = 1;
        int jumpDistance = 2;
        addToStepHistory(new Move(board, null));

        if(!isMoveBackwards(die.getPosition(), endPosition) && Boards.getManhattanDistance(die.getPosition(), endPosition) == tipDistance) {
            board.applyTip(die, endPosition);
            addToStepHistory(new Move(board, moveType.TIP));
            return board;
        } else if(!isMoveBackwards(die.getPosition(), endPosition)
                && board.getAt(Boards.getMiddlePosition(die.getPosition(), endPosition)) != null
                && Boards.getManhattanDistance(die.getPosition(), endPosition) == jumpDistance) {
            board.applyJump(die, endPosition);
            addToStepHistory(new Move(board, moveType.JUMP));
            return board;
        }
        return null;
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

    public static Boolean checkPurStepValid(String state, String step) {

        int jumpDistance = 2;
        Boards board = new Boards(state);
        PurCublino purCublino = new PurCublino(Character.isUpperCase(state.charAt(0)));
        Boards.Positions positions = board.stepToPositions(step);
        String start = positions.getStart();
        String end = positions.getEnd();

        if(board.getAtPosition(end) != null) return false;

        if (board.isAdjacent(start, end)) {
            return !purCublino.isMoveBackwards(start,end);
        } else if (Boards.getManhattanDistance(start,end) == jumpDistance) {
            return purCublino.isJumpValid(board, start, end);
        } else {
            return false;
        }
    }

    public boolean isGameValid(Boards board){
        return (isDiceAmountCorrect(board) && !hasBothWon(board));
    }
}
