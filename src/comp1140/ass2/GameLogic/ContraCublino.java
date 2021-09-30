package comp1140.ass2.GameLogic;

import comp1140.ass2.State.Boards;
import comp1140.ass2.State.Die;

import java.util.List;

/** A gamemode of Cublino extends from Game class
 *
 * @author Ziling Ouyang, Yuechen Liu
 */
public class ContraCublino extends Game {

    public ContraCublino(){
    }

    @Override
    protected void applyStep(Die die, String endPosition) {
    }

    @Override
    public boolean isDiceAmountCorrect(Boards board){
        return (board.getBlackPlayer().getDice().size() + board.getWhitePlayer().getDice().size() <= 2 * Boards.BOARD_DIMENSION
        && board.getBlackPlayer().getDice().size() + board.getWhitePlayer().getDice().size() >= 2);
    }

    @Override
    public boolean hasBothWon(Boards board){

        List<Die> white = board.getWhitePlayer().getDice();
        List<Die> black = board.getBlackPlayer().getDice();

        return white.stream().filter(Die::isWhiteDieFinished).count() + black.stream().filter(Die::isBlackDieFinished).count() > 1;
    }

    public boolean isGameValid(Boards board){
        return (isDiceAmountCorrect(board) && !hasBothWon(board));
    }
}
