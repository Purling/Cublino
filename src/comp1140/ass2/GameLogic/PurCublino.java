package comp1140.ass2.GameLogic;

import comp1140.ass2.State.Boards;
import comp1140.ass2.State.Die;

import java.util.List;

/** A gamemode of Cublino that extends from the Game class
 *
 * @author Ziling Ouyang, Yuechen Liu
 */

public class PurCublino extends Game {

    public PurCublino(){
        super();
    }

    public PurCublino(boolean isWhite, Boards board){
        super(isWhite, board);
    }

    @Override
    public void applyStep(Die die, String endPosition) {

        Boards clone = board.deepClone();

        if(board.getAt(endPosition) != null || die == null){
            addToStepHistory(new Move(clone, MoveType.INVALID));
        } else {
            if (getStepHistory().size() == 0) addToStepHistory(new Move(clone, MoveType.ORIGIN));

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
            addToStepHistory(new Move(clone, moveType));
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
