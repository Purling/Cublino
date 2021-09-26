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

    public PurCublino(boolean isWhite, Boards board){
        super(isWhite, board);
    }

    @Override
    public void applyStep(Die die, String endPosition) {

        if(board.getAt(endPosition) != null || die == null){
            addToStepHistory(new Move(board, MoveType.INVALID));
        } else {
            if (getStepHistory().size() == 0) addToStepHistory(new Move(board, MoveType.ORIGIN));

            int tipDistance = 1;
            int jumpDistance = 2;
            String diePosition = die.getPosition();
            int distance = Boards.getManhattanDistance(diePosition, endPosition);
            MoveType moveType;

            if (!isMoveBackwards(diePosition, endPosition) && distance == tipDistance) {
                applyTip(die, endPosition);
                moveType = MoveType.TIP;
            } else if (isJumpValid(diePosition, endPosition) && distance == jumpDistance) {
                applyJump(die, endPosition);
                moveType = MoveType.JUMP;
            } else {
                moveType = MoveType.INVALID;
            }
            addToStepHistory(new Move(board, moveType));
        }
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

    public static Boolean checkPurStepValid(String state, String step) {

        int jumpDistance = 2;
        PurCublino purCublino = new PurCublino(Character.isUpperCase(state.charAt(0)), new Boards(state));
        Boards board = purCublino.getBoard();
        Boards.Positions[] positions = Boards.moveToPositions(step);
        String start = positions[0].toString();
        String end = positions[positions.length - 1].toString();

        if(board.getAtPosition(end) != null) return false;

        if (board.isAdjacent(start, end)) {
            return !purCublino.isMoveBackwards(start,end);
        } else if (Boards.getManhattanDistance(start,end) == jumpDistance) {
            return purCublino.isJumpValid(start, end);
        } else {
            return false;
        }
    }

    public boolean isGameValid(Boards board){
        return (isDiceAmountCorrect(board) && !hasBothWon(board));
    }
}
