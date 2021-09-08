package comp1140.ass2.GameLogic;

import comp1140.ass2.State.Boards;
import comp1140.ass2.State.Die;

import java.util.Arrays;

/** A type of cublino
 /* extends from Game class*/

public class PurCublino extends Game {

    public PurCublino(){
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

    public boolean isGameValid(Boards board){
        return (isDiceAmountCorrect(board) && !hasBothWon(board));
    }
}
