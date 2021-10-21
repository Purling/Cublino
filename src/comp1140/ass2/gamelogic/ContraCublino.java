package comp1140.ass2.gamelogic;

import comp1140.ass2.helperclasses.DeepCloneable;
import comp1140.ass2.state.Boards;
import comp1140.ass2.state.Die;
import comp1140.ass2.state.Direction;

import java.io.*;
import java.util.*;

import static comp1140.ass2.gamelogic.Game.GameResult.*;
import static comp1140.ass2.gamelogic.Game.MoveType.TIP;
import static comp1140.ass2.state.Boards.BOARD_DIMENSION;

/**
 * A gamemode of Cublino extends from Game class
 *
 * @author Ziling Ouyang, Yuechen Liu
 */
public class ContraCublino extends Game implements Serializable, DeepCloneable<Game> {

    /**
     * Empty constructor
     */
    public ContraCublino() {
    }

    /**
     * Constructor for ContraCublino
     */
    public ContraCublino(boolean isWhite, Boards board) {
        super(isWhite, board);
    }

    /**
     * Returns if each player has a legal amount of dice
     *
     * @return True if the dice amount is valid, False otherwise
     */
    @Override
    public boolean isDiceAmountCorrect(Boards board) {
        return (board.getBlackPlayer().getDice().size() + board.getWhitePlayer().getDice().size() <= 2 * BOARD_DIMENSION
                && board.getBlackPlayer().getDice().size() + board.getWhitePlayer().getDice().size() >= 2);
    }

    /**
     * Returns if both players have won (an invalid state)
     *
     * @param board The board being played
     * @return True if both players have simultaneously won, false otherwise
     */
    @Override
    public boolean hasBothNotWon(Boards board) {
        List<Die> white = board.getWhitePlayer().getDice();
        List<Die> black = board.getBlackPlayer().getDice();
        return white.stream().filter(Die::isWhiteDieFinished).count() + black.stream().filter(Die::isBlackDieFinished).count() <= 1;
    }

    /**
     * Given a valid die, moves the die to the valid position give. It stores the step that has been applied to the die in the stepHistory
     * variable. If the move is invalid, that is also stored in stepHistory under MoveType INVALID.
     *
     * @param die         The die to be moved
     * @param endPosition The coordinates to move the die to
     */
    @Override
    public void applyStep(Die die, String endPosition) {

        if (die.isWhite() != getCurrentPlayer().isWhite()) return;

        Boards clone = board.deepClone();
        boolean firstEntry = true;
        for (Move x : getStepHistory()) {
            if (x.getType() != MoveType.ORIGIN && x.getType() != MoveType.INVALID) {
                firstEntry = false;
                break;
            }
        }

        if (board.getAt(endPosition) != null) return;
        String diePosition = die.getPosition();
        boolean correctDistance = Boards.getManhattanDistance(diePosition, endPosition) == TIP_DISTANCE;

        if (firstEntry) {
            clearStepHistory();
            addToStepHistory(new Move(clone, MoveType.ORIGIN));
        }

        if (isMoveNotBackwards(diePosition, endPosition) && correctDistance && isDieCorrect(die) && firstEntry) {
            applyTip(die, endPosition);
            addToStepHistory(new Move(clone, TIP));
            setCurrentMoveDie(die.deepClone());
        }

        if (board.getAdjacentDie(die) != null && !(board.getAdjacentDie(die).length == 0)) {
            battle(die);
        }
    }

    /**
     * Method which compares adjacent die of opposing players and eliminates the loser according to the Contra gamemode
     *
     * @param adjacentDie The die that is always a participant in battles
     */
    public void battle(Die adjacentDie) {
        Die[] potentialBattles = Arrays.stream(board.getAdjacentDie(adjacentDie)).filter((x) -> x.isWhite() != adjacentDie.isWhite()).toArray(Die[]::new);
        Set<Die> eliminate = new HashSet<>();

        if (potentialBattles.length == 0) return;

        for (Die die : potentialBattles) {
            if (adjacentDie.getTop() > die.getTop()) {
                eliminate.add(die);
            } else if (adjacentDie.getTop() < die.getTop()) {
                eliminate.add(adjacentDie);
            }
        }
        eliminate.forEach(this::removeDie);
    }

    /**
     * Returns any legal moves that can be made by the current player given the game up to that point
     *
     * @return An array of games which represent each possible move that can be made by the player
     */
    public ContraMove[] generateLegalMoves() {
        List<Die> possibleDie = getCurrentPlayer().getDice();
        List<ContraMove> possibleMoves = new ArrayList<>();

        for (Die die : possibleDie) {
            for (Direction direction : Direction.values()) {
                if (isDirectionClear(direction, die)) {
                    ContraCublino clone = deepClone();
                    Die dieClone = die.deepClone();
                    clone.applyStep(dieClone, dieClone.getPositionOver(direction, 1));
                    clone.endTurn();
                    ContraMove move = new ContraMove(clone, Die.dieToEncoding(die).substring(1) + Die.dieToEncoding(dieClone).substring(1));
                    possibleMoves.add(move);
                }
            }
        }
        return possibleMoves.toArray(ContraMove[]::new);
    }

    /**
     * Implements the deepClone method from DeepCloneable interface
     */
    public ContraCublino deepClone() {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ois = new ObjectInputStream(bais);
            return (ContraCublino) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        } finally {
            try {
                if (oos != null) oos.close();
                if (ois != null) ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Determine whether the game is over or not
     *
     * @return True if the game is over and false otherwise
     */
    public boolean isGameOver() {
        List<Die> white = board.getWhitePlayer().getDice();
        List<Die> black = board.getBlackPlayer().getDice();
        return white.stream().anyMatch(Die::isWhiteDieFinished) || black.stream().anyMatch(Die::isBlackDieFinished);
    }

    /**
     * Assuming that the game is finished, gives the winner of the game
     *
     * @return An enum indicating that white or black has won or that there was a tie
     */
    public GameResult getWinner() {
        List<Die> white = board.getWhitePlayer().getDice();
        List<Die> black = board.getBlackPlayer().getDice();
        if (white.stream().anyMatch(Die::isWhiteDieFinished) || black.isEmpty()) return WHITE_WINS;
        if (black.stream().anyMatch(Die::isBlackDieFinished) || white.isEmpty()) return BLACK_WINS;
        return UNFINISHED;
    }

    /**
     * Method which evaluates if the space directly ahead in the specified direction (Manhattan distance of 1) is clear
     *
     * @param direction The direction which needs to be checked
     * @param die       The die from which the origin is based
     * @return True if the space is clear, false otherwise
     */
    public boolean isDirectionClear(Direction direction, Die die) {
        return switch (direction) {
            case RIGHT -> ((die.getX() + 1) < BOARD_DIMENSION && (board.getAt(die.getX() + 1, die.getY()) == null));
            case LEFT -> ((die.getX()) > 0 && (board.getAt(die.getX() - 1, die.getY()) == null));
            case UP -> (die.isWhite() && (die.getY() + 1) < BOARD_DIMENSION && (board.getAt(die.getX(), die.getY() + 1) == null));
            case DOWN -> (!die.isWhite() && (die.getY()) > 0 && (board.getAt(die.getX(), die.getY() - 1) == null));
        };
    }

    /**
     * Evaluates whether the game is valid i.e. It has an appropriate amount of dice and has not reached an invalid state
     *
     * @param board The board to be evaluated
     * @return True if the game is valid, false otherwise
     */
    public boolean isGameValid(Boards board) {
        return (isDiceAmountCorrect(board) && hasBothNotWon(board));
    }

    /**
     * Overriding toString method
     */
    @Override
    public String toString() {
        return board.getStringRepresentation();
    }

    public class ContraMove {
        /**
         * The actual move that has been played in Game form
         */
        ContraCublino possibleState;

        /**
         * The move to be played in an encoded form
         */
        String encodedMove;

        /**
         * Constructor for ContraMove
         */
        public ContraMove(ContraCublino possibleState, String encodedMove) {
            this.possibleState = possibleState;
            this.encodedMove = encodedMove;
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
        public ContraCublino getPossibleState() {
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
