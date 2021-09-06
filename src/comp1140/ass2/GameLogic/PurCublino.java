package comp1140.ass2.GameLogic;

import comp1140.ass2.State.Boards;
import comp1140.ass2.State.Die;

import java.util.Arrays;

/** A type of cublino
 /* extends from Game class*/

public class PurCublino extends Game {

    public PurCublino(){
    }

    private boolean isDiceAmountCorrect(Boards board){
        return (board.getBlackPlayer().getDice().length == 7 && board.getWhitePlayer().getDice().length == 7);
    }

    private boolean isGameInvalid(Boards board){

        Die[] white = board.getWhitePlayer().getDice();
        Die[] black = board.getBlackPlayer().getDice();

        return Arrays.stream(white).allMatch(d -> d.getY() == 0) && Arrays.stream(black).allMatch(d -> d.getY() == 6);
    }

    public boolean isGameValid(Boards board){
        return (isDiceAmountCorrect(board) && !isGameInvalid(board));
    }
}
