package comp1140.ass2;

// The player will be allowed to decide which version of cublino he or she wants to play.
// Option includes PurCublino and ContraCublino
public abstract class Game{
    Board board; // the current board two players are playing on

    int turn; // store the current number of turns the player has already done

    Player currentPlayer;// store the current player that is making his moves

    // Check whether a player has made moves or not.
    // if the player has possible legal moves then at least one move should be made by the player
    boolean hasMadeMove;

    Result result; // the result in terms of winning and tie when the game is finished

    public enum MoveType{Roll, Jump}

    public void makeMove(MoveType t, int x, int y, Direction d){
        // make move (only one move) to the dice
        // multiple moves can be made by calling the function for multiple times
        // the function will not be called when at least one move is applied
        // and the player decides not to make any extra move,
        // or no more legal moves can be applied
    }
    public boolean isGameOver(Board b){
        // determine whether the game is over or not
        return false;
    }

}
