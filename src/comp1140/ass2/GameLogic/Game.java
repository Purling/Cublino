package comp1140.ass2.GameLogic;

import comp1140.ass2.State.Boards;
import comp1140.ass2.gui.Board;
import comp1140.ass2.Controller.Controller;
import comp1140.ass2.State.Die;
import comp1140.ass2.State.Players;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The player will be allowed to decide which version of cublino he or she wants to play.
 * Option includes PurCublino and ContraCublino
 *
 * @author Ziling Ouyang, minor edits by Zane Gates
 */

public abstract class Game implements Serializable{

    public enum MoveType {
        TIP, JUMP, ORIGIN, INVALID
    }

    public class Move {
        Boards historicalBoard;
        MoveType type;

        public Move(Boards historicalBoard, MoveType type) {
            this.historicalBoard = historicalBoard;
            this.type = type;
        }

        public MoveType getType() {
            return type;
        }

        public Boards getBoard() {
            return historicalBoard;
        }
    }
    /**
     * the current board two players are playing on
     */
    Boards board;

    public static final int TIP_DISTANCE = 1;

    private List<Move> stepHistory = new ArrayList<>();
    private List<Game> turnHistory = new ArrayList<>();

    /**
     * store the current player that is making his moves
     */
    private Players currentPlayer;

    /**
     * store the player who is not making a move
     */
    private Players otherPlayer;

    /**
     * The die that is currently selected to be moved
     */
    private Die currentMoveDie;

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
     * Setter for currentMoveDie
     */
    public void setCurrentMoveDie(Die currentMoveDie) {
        this.currentMoveDie = currentMoveDie;
    }

    /**
     * Getter for currentMoveDie
     */
    public Die getCurrentMoveDie() {
        return currentMoveDie;
    }

    /**
     * Constructor for Game which takes in a value for isWhite
     */
    public Game(boolean isWhite){
        this.currentPlayer = new Players(isWhite);
    }

    /**
     * Constructor for Game which takes in current player colour and the current board
     */
    public Game(boolean isWhite, Boards board){
        this.currentPlayer = (isWhite) ? board.getWhitePlayer() : board.getBlackPlayer();
        this.otherPlayer = (isWhite) ? board.getBlackPlayer() : board.getWhitePlayer();
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

    /**
     * Returns if the move is always being performed on one die
     * @return True if the die is the one chosen to be moved already or the currentMoveDie is null
     */
    public boolean isDieCorrect(Die chosenDie) {
        if(currentMoveDie == null) return true;
        return chosenDie.equals(currentMoveDie) && chosenDie.isWhite() == currentPlayer.isWhite();
    }

    abstract public void applyStep(Die die, String endPosition);

    /**
     * End the player's turn
     */
    public void endTurn() {
        Players temp = currentPlayer;
        currentPlayer = otherPlayer;
        otherPlayer = temp;
        stepHistory.clear();
        turnHistory.add(this.deepClone());
        currentMoveDie = null;
    }

    /**
     * Creates a deep copy of the game
     * @return A deep copy the game
     */
    public Game deepClone() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (Game) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
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

    /**
     * Adds a move to the record of moves within one player's turn
     * @param move The move to be recorded
     */
    public void addToStepHistory(Move move) {
        stepHistory.add(move);
    }

    /**
     * Adds a turn to the record of player turns
     * @param game The gamestate at the end of a player's turn
     */
    public void addToTurnHistory(Game game) {
        turnHistory.add(game);
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
     * Removes the specified die from either player
     * @param remove The die to be removed
     */
    public void removeDie (Die remove) {
        currentPlayer.getDice().remove(remove);
        otherPlayer.getDice().remove(remove);
        board.setAt(remove.getPosition(), null);
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
