package comp1140.ass2.GameLogic;

import comp1140.ass2.State.Boards;
import comp1140.ass2.gui.Board;
import comp1140.ass2.Controller.Controller;
import comp1140.ass2.State.Die;
import comp1140.ass2.State.Players;

import java.util.ArrayList;
import java.util.List;

/**
 * The player will be allowed to decide which version of cublino he or she wants to play.
 * Option includes PurCublino and ContraCublino
 */

public abstract class Game{

    public enum MoveType {
        TIP, JUMP, ORIGIN, INVALID
    }

    public class Move {
        Boards board;
        MoveType type;

        public Move(Boards board, MoveType type) {
            this.board = board;
            this.type = type;
        }

        public MoveType getType() {
            return type;
        }

        public Boards getBoard() {
            return board;
        }
    }
    /**
     * the current board two players are playing on
     */
    Boards board;

    private List<Move> stepHistory = new ArrayList<>();
    private Game[] turnHistory;

    /**
     * store the current player that is making his moves
     */
    private Players currentPlayer;

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
     * Returns if both players have achieved a win condition simultaneously
     * @return True if both players have won, False otherwise
     */
    abstract protected boolean hasBothWon(Boards board);

    /**
     * Returns if each player has a legal amount of dice
     * @return True if the dice amount is valid, False otherwise
     */
    abstract protected boolean isDiceAmountCorrect(Boards board);

    /**
     * Empty constructor for Game
     */
    public Game(){
    }

    /**
     * Constructor for Game which takes in a value for isWhite
     */
    public Game(boolean isWhite){
        this.currentPlayer = new Players(isWhite);
    }

    public Game(boolean isWhite, Boards board){
        this.currentPlayer = new Players(isWhite);
        this.board = board;
    }

    /**
     * Returns if the die is going backwards. This depends on the current player
     * @return True if the die is going backwards, False otherwise
     */
    public boolean isMoveBackwards(String startPosition, String endPosition){
        if(currentPlayer.isWhite()){
            return (Boards.getPositionY(endPosition) - Boards.getPositionY(startPosition)) < 0;
        } else {
            return (Boards.getPositionY(startPosition) - Boards.getPositionY(endPosition)) < 0;
        }
    }

    abstract protected void applyStep(Die die, String endPosition);

    /**
     * End the player's turn when there is no more legal moves or the player is desired to
     */
    public void endTurn(){
    }

    public void applyTip(Die initial, String endPosition) {

        String start = initial.getPosition();

        if(board.getAt(endPosition) == null) {
            initial.tip(initial.getDirection(endPosition));
            initial.setPosition(endPosition);
            board.setAt(endPosition, initial);
            board.setAt(start,null);
        }
    }

    public void addToStepHistory(Move move) {
        stepHistory.add(move);
    }

    public List<Move> getStepHistory() {
        return stepHistory;
    }

    public Players getCurrentPlayer() {
        return currentPlayer;
    }

    public Boards getBoard() {
        return board;
    }

    /**
     * Display all the legal turns according to the current status of the board
     * @return A list of boards representing different possible moves
     */
    public Board[] legalTurns(){
        return null;
    }

    /**
     * determine whether the game is over or not
     * @param b A board
     * @return True if the game is over and false otherwise
     */
    public boolean isGameOver(Board b){
        return false;
    }

}
