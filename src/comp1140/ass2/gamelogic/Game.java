package comp1140.ass2.gamelogic;

import comp1140.ass2.helperclasses.DeepCloneable;
import comp1140.ass2.state.Boards;
import comp1140.ass2.state.Die;
import comp1140.ass2.state.Players;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The player will be allowed to decide which version of Cublino he or she wants to play.
 * Options include PurCublino and ContraCublino
 *
 * @author Ziling Ouyang, minor edits by Zane Gates
 */

public abstract class Game implements Serializable, DeepCloneable<Game> {

    /**
     * The distance moved in a tip step
     */
    public static final int TIP_DISTANCE = 1;

    /**
     * The steps which the game has already performed in that turn
     */
    private final List<Move> stepHistory = new ArrayList<>();

    /**
     * the current board two players are playing on
     */
    Boards board;

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
     * Empty constructor for Game
     */
    public Game() {

    }

    /**
     * Constructor for Game which takes in a value for isWhite
     */
    public Game(boolean isWhite) {
        this.currentPlayer = new Players(isWhite);
    }

    /**
     * Constructor for Game which takes in current player colour and the current board
     */
    public Game(boolean isWhite, Boards board) {
        this.currentPlayer = (isWhite) ? board.getWhitePlayer() : board.getBlackPlayer();
        this.otherPlayer = (isWhite) ? board.getBlackPlayer() : board.getWhitePlayer();
        this.board = board;
    }

    /**
     * Returns if both players have achieved a win condition simultaneously
     *
     * @return True if both players have won, False otherwise
     */
    abstract protected boolean hasBothNotWon(Boards board);

    /**
     * Generates all legal moves that can be played in the current state of the game
     */
    public abstract GameMove[] generateLegalMoves();

    /**
     * Returns if each player has a legal amount of dice
     *
     * @return True if the dice amount is valid, False otherwise
     */
    abstract protected boolean isDiceAmountCorrect(Boards board);

    /**
     * Setter for currentMoveDie
     */
    public void setCurrentMoveDie(Die currentMoveDie) {
        this.currentMoveDie = currentMoveDie;
    }

    /**
     * Returns if the die is going backwards. This depends on the current player
     *
     * @return True if the die is going backwards, False otherwise
     */
    public boolean isMoveNotBackwards(String startPosition, String endPosition) {
        if (currentPlayer.isWhite()) {
            return (Boards.getPositionY(endPosition) - Boards.getPositionY(startPosition)) >= 0;
        } else {
            return (Boards.getPositionY(startPosition) - Boards.getPositionY(endPosition)) >= 0;
        }
    }

    /**
     * Returns if the move is always being performed on one die
     *
     * @return True if the die is the one chosen to be moved already or the currentMoveDie is null
     */
    public boolean isDieCorrect(Die chosenDie) {
        if (currentMoveDie == null) return true;
        return chosenDie.equals(currentMoveDie) && chosenDie.isWhite() == currentPlayer.isWhite();
    }

    /**
     * Apply the given step to the die
     *
     * @param die         The die that will be moved
     * @param endPosition The position the die will end up in
     */
    abstract public void applyStep(Die die, String endPosition);

    /**
     * End the player's turn
     */
    public void endTurn() {
        Players temp = currentPlayer;
        currentPlayer = otherPlayer;
        otherPlayer = temp;
        stepHistory.clear();
        currentMoveDie = null;
    }

    /**
     * Removes all elements from stepHistory list
     */
    public void clearStepHistory() {
        stepHistory.clear();
    }

    /**
     * Implements the deepClone method from DeepCloneable interface
     */
    public Game deepClone() {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ois = new ObjectInputStream(bais);
            return (Game) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Applies a tipping move upon the die
     *
     * @param initial     The die to be moved
     * @param endPosition The position the die will be moved to
     */
    public void applyTip(Die initial, String endPosition) {

        String start = initial.getPosition();
        int index = currentPlayer.getDice().indexOf(initial);
        assert index != -1;

        if (board.getAt(endPosition) == null) {
            Die realDie = currentPlayer.getDice().get(index);
            realDie.tip(currentPlayer.getDice().get(index).getDirection(endPosition));
            board.setAt(endPosition, currentPlayer.getDice().get(index));
            board.setAt(start, null);
            initial.setDie(realDie);
        }
    }

    /**
     * Adds a move to the record of moves within one player's turn
     *
     * @param move The move to be recorded
     */
    public void addToStepHistory(Move move) {
        stepHistory.add(move);
    }

    /**
     * Getter for stepHistory
     */
    public List<Move> getStepHistory() {
        return stepHistory;
    }

    /**
     * Getter for currentPlayer
     */
    public Players getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Getter for board
     */
    public Boards getBoard() {
        return board;
    }

    /**
     * Removes the specified die from either player
     *
     * @param remove The die to be removed
     */
    public void removeDie(Die remove) {
        currentPlayer.getDice().remove(remove);
        otherPlayer.getDice().remove(remove);
        board.setAt(remove.getPosition(), null);
        remove.delete();
    }

    /**
     * Getter for otherPlayer
     */
    public Players getOtherPlayer() {
        return otherPlayer;
    }

    public abstract GameResult getWinner();

    /**
     * Enum representing the different types of moves which can be played and also the original move
     */
    public enum MoveType {
        TIP, JUMP, ORIGIN, INVALID
    }

    /**
     * Enum representing outcomes / states the game can be in
     */
    public enum GameResult {
        UNFINISHED, WHITE_WINS, BLACK_WINS, TIE
    }

    /**
     * Stores a move which has been played or will potentially be played
     */
    public class Move implements Serializable {

        /**
         * The board which has been played already
         */
        Boards historicalBoard;

        /**
         * The type of move
         */
        MoveType type;

        /**
         * Constructor for Move
         */
        public Move(Boards historicalBoard, MoveType type) {
            this.historicalBoard = historicalBoard;
            this.type = type;
        }

        /**
         * Getter for type
         */
        public MoveType getType() {
            return type;
        }

        /**
         * Getter for historicalBoard
         */
        public Boards getBoard() {
            return historicalBoard;
        }
    }

    /**
     * Class to allow for potential moves to be easily read by the GUI and Cublino.java
     *
     * @author Ziling Ouyang, Modified by Yuechen Liu
     */
    public abstract class GameMove {

        /**
         * The actual move that has been played in Game form
         */
        Game possibleState;

        /**
         * The move to be played in an encoded form
         */
        String encodedMove;

        /**
         * Constructor for PurMove
         */
        public GameMove(PurCublino possibleState, String encodedMove) {
            this.possibleState = possibleState;
            this.encodedMove = encodedMove;
        }

        /**
         * Empty constructor
         */
        public GameMove() {
        }

        /**
         * Getter for encodedMove
         */
        public String getEncodedMove() {
            return encodedMove;
        }

        /**
         * Getter for possibleState
         */
        public Game getPossibleState() {
            return possibleState;
        }

        /**
         * To string method
         */
        @Override
        public String toString() {
            return encodedMove;
        }

    }
}
