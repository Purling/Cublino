package comp1140.ass2.GameLogic;

import comp1140.ass2.State.Boards;
import comp1140.ass2.State.Die;

import java.util.Arrays;

/** A type of cublino
 /* extends from Game class*/

public class ContraCublino extends Game {

    public ContraCublino(){
    }

    private boolean isDiceAmountCorrect(Boards board){
        return (board.getBlackPlayer().getDice().length + board.getWhitePlayer().getDice().length <= 14);
    }

    private boolean isGameInvalid(Boards board){

        Die[] white = board.getWhitePlayer().getDice();
        Die[] black = board.getBlackPlayer().getDice();
        Die[] whiteEnd = Arrays.stream(white).filter(d -> d.getY() == 0).toArray(Die[] :: new);
        Die[] blackEnd = Arrays.stream(black).filter(d -> d.getY() == 6).toArray(Die[] :: new);

        return whiteEnd.length + blackEnd.length > 1;
    }

    public boolean isGameValid(Boards board){
        return (isDiceAmountCorrect(board) && !isGameInvalid(board));
    }
}
