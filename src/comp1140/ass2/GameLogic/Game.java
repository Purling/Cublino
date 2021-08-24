package comp1140.ass2.GameLogic;

import comp1140.ass2.Board;
import comp1140.ass2.Controller.Controller;
import comp1140.ass2.State.Die;
import comp1140.ass2.State.Direction;
import comp1140.ass2.State.Players;

/**
 * The player will be allowed to decide which version of cublino he or she wants to play.
 * Option includes PurCublino and ContraCublino
 */

public abstract class Game{
    /**
     * the current board two players are playing on
     */
    Board board;

    /**
     * store the current number of turns the player has already done
     */
    int turn;

    /**
     * store the current player that is making his moves
     */
    Players currentPlayer;

    /**
     * The die that is currently selected to be moved
     */
    Die currentMoveDie;

    /**
     * the status of the two players
     */
    Controller whitePlayer;
    Controller blackPlayer;
    /**
     * Check whether a player has made moves or not.
     * if the player has possible legal moves then at least one move should be made by the player
     */
    boolean hasMadeMove;

    /**
     * the result in terms of winning and tie when the game is finished
     */
    Result result;

    /**
     * Indicate the type of move that is going to be made
     * Either roll or jump
     */
    public enum MoveType{Roll, Jump}

    /**
     * make move (only one move) to the dice
     * multiple moves can be made by calling the function for multiple times
     * the function will not be called when at least one move is applied
     * and the player decides not to make any extra move,
     * or no more legal moves can be applied
     * @param t
     * @param x
     * @param y
     * @param d
     */
    public void makeMove(MoveType t, int x, int y, Direction d){
    }

    /**
     * End the player's turn when there is no more legal moves or the player is desired to
     */
    public void endTurn(){
    }

    /**
     * determine whether a move is legal or not
     * @return
     */
    public boolean isLegalMove(){
        return false;
    }

    /**
     * Display all the legal turns according to the current status of the board
     * @return
     */
    public Board[] legalTurns(){
        return null;
    }
    /**
     * determine whether the game is over or not
     * @param b
     * @return
     */
    public boolean isGameOver(Board b){
        return false;
    }

}
