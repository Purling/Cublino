package comp1140.ass2.GameLogic;

import comp1140.ass2.Controller.Controller;
import comp1140.ass2.State.Boards;
import comp1140.ass2.State.Die;
import comp1140.ass2.State.Players;
import comp1140.ass2.gui.Board;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * The player will be allowed to decide which version of cublino he or she wants to play.
 * Option includes PurCublino and ContraCublino
 *
 * @author Ziling Ouyang, minor edits by Zane Gates
 */

public abstract class Game implements Serializable {

    public static final int TIP_DISTANCE = 1;
    private final List<Move> stepHistory = new ArrayList<>();
    private final List<Game> turnHistory = new ArrayList<>();
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
    abstract protected boolean hasBothWon(Boards board);

    /**
     * Returns if each player has a legal amount of dice
     *
     * @return True if the dice amount is valid, False otherwise
     */
    abstract protected boolean isDiceAmountCorrect(Boards board);

    /**
     * Getter for currentMoveDie
     */
    public Die getCurrentMoveDie() {
        return currentMoveDie;
    }

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
    public boolean isMoveBackwards(String startPosition, String endPosition) {
        if (currentPlayer.isWhite()) {
            return (Boards.getPositionY(endPosition) - Boards.getPositionY(startPosition)) < 0;
        } else {
            return (Boards.getPositionY(startPosition) - Boards.getPositionY(endPosition)) < 0;
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

    abstract public void applyStep(Die die, String endPosition);

    /**
     * End the player's turn
     */
    public void endTurn() {
        Players temp = currentPlayer;
        currentPlayer = otherPlayer;
        otherPlayer = temp;
        stepHistory.clear();
        turnHistory.clear();
        turnHistory.add(deepClone());
        currentMoveDie = null;
    }

    public void clearStepHistory() {
        stepHistory.clear();
    }

    /**
     * Creates a deep copy of the game
     *
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

    public Players getCurrentPlayer() {
        return currentPlayer;
    }

    public Boards getBoard() {
        return board;
    }

    /**
     * Display all the legal turns according to the current status of the board
     *
     * @return A list of boards representing different possible moves
     */
    public Board[] legalTurns() {
        return null;
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

    /**
     * Determine whether the game is over or not
     *
     * @return True if the game is over and false otherwise
     */
    protected abstract boolean isGameOver();

    public abstract GameResult getWinner();

    /**
     * Given a new state and an old one (assuming that the new state results from a *step* applied to the old state) the methods finds the step
     *
     * @param originalGame The original game
     * @param newGame      The new game after a step has been applied to the original
     * @return The step in DieMove form
     */
    public static DieMove findMove(Game originalGame, Game newGame) {
        DieMove dieMove = new DieMove();
        List<Die> originalCurrentPlayer = originalGame.currentPlayer.getDice();
        List<Die> otherOpponentPlayer = newGame.otherPlayer.getDice();
        Iterator<Die> iter = originalCurrentPlayer.iterator();
        Iterator<Die> iter1 = otherOpponentPlayer.iterator();

        while (iter.hasNext()) {
            Die die = iter.next();

            while (iter1.hasNext()) {
                Die die1 = iter1.next();
                if (die.equals(die1)) {
                    iter.remove();
                    iter1.remove();
                }
            }
        }
        dieMove.setDie(originalCurrentPlayer.get(0));
        if (otherOpponentPlayer.isEmpty()) {
            dieMove.setEndPosition(null);
        } else {
            dieMove.setEndPosition(otherOpponentPlayer.get(0).getPosition());
        }
        return dieMove;
    }

    public enum MoveType {
        TIP, JUMP, ORIGIN, INVALID
    }

    public enum GameResult {
        UNFINISHED, WHITE_WINS, BLACK_WINS, TIE
    }

    public static class DieMove {
        Die die;
        String endPosition;

        /**
         * Empty constructor
         */
        public DieMove() {
        }

        public DieMove(Die die, String endPosition) {
            this.die = die;
            this.endPosition = endPosition;
        }

        /**
         * Getter for endPosition
         */
        public String getEndPosition() {
            return endPosition;
        }

        /**
         * Setter for endPosition
         */
        public void setEndPosition(String endPosition) {
            this.endPosition = endPosition;
        }

        /**
         * Getter for possibleState
         */
        public Die getDie() {
            return die;
        }

        /**
         * Setter for possibleState
         */
        public void setDie(Die die) {
            this.die = die;
        }

        /**
         * To string method
         */
        @Override
        public String toString() {
            return endPosition;
        }
    }

    public class Move implements Serializable {
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

}
