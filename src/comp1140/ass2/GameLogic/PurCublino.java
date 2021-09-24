package comp1140.ass2.GameLogic;

import comp1140.ass2.State.Boards;
import comp1140.ass2.State.Die;

import java.util.List;

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
        String diePosition = die.getPosition();

        if(getStepHistory().size() == 0){
            addToStepHistory(new Move(board, null));
        }

        if(!isMoveBackwards(diePosition, endPosition) && Boards.getManhattanDistance(diePosition, endPosition) == tipDistance) {
            board.applyTip(die, endPosition);
            addToStepHistory(new Move(board, moveType.TIP));
            return board;
        } else if(!isMoveBackwards(diePosition, endPosition) && board.getAt(Boards.getMiddlePosition(diePosition, endPosition)) != null
                && Boards.getManhattanDistance(diePosition, endPosition) == jumpDistance) {
            board.applyJump(die, endPosition);
            addToStepHistory(new Move(board, moveType.JUMP));
            return board;
        }
        return null;
    }

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
        Boards.Positions[] positions = Boards.moveToPositions(step);
        String start = positions[0].toString();
        String end = positions[positions.length - 1].toString();

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
