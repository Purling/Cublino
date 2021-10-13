package comp1140.ass2.GameLogic;

import comp1140.ass2.State.Boards;
import comp1140.ass2.State.Die;
import comp1140.ass2.State.Direction;

import java.io.*;
import java.util.*;

import static comp1140.ass2.GameLogic.Game.MoveType.TIP;
import static comp1140.ass2.State.Boards.BOARD_DIMENSION;

/** A gamemode of Cublino extends from Game class
 *
 * @author Ziling Ouyang, Yuechen Liu
 */
public class ContraCublino extends Game implements Serializable {

    public class ContraMove {
        ContraCublino possibleState;
        String encodedMove;

        public ContraMove(ContraCublino possibleState, String encodedMove) {
            this.possibleState = possibleState;
            this.encodedMove = encodedMove;
        }

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

    public ContraCublino(){
    }

    /**
     * Constructor for ContraCublino
     */
    public ContraCublino(boolean isWhite, Boards board){
        super(isWhite, board);
    }

    @Override
    public boolean isDiceAmountCorrect(Boards board){
        return (board.getBlackPlayer().getDice().size() + board.getWhitePlayer().getDice().size() <= 2 * BOARD_DIMENSION
        && board.getBlackPlayer().getDice().size() + board.getWhitePlayer().getDice().size() >= 2);
    }

    /**
     * Returns if both players have won (an invalid state)
     * @param board The board being played
     * @return True if both players have simultaneously won, false otherwise
     */
    @Override
    public boolean hasBothWon(Boards board){
        List<Die> white = board.getWhitePlayer().getDice();
        List<Die> black = board.getBlackPlayer().getDice();
        return white.stream().filter(Die::isWhiteDieFinished).count() + black.stream().filter(Die::isBlackDieFinished).count() > 1;
    }

    /**
     * Given a valid die, moves the die to the valid position give. It stores the step that has been applied to the die in the stepHistory
     * variable. If the move is invalid, that is also stored in stepHistory under MoveType INVALID.
     *
     * @param die The die to be moved
     * @param endPosition The coordinates to move the die to
     */
    @Override
    public void applyStep(Die die, String endPosition) {
        if (die.isWhite() != getCurrentPlayer().isWhite()) return;

        Boards clone = board.deepClone();
        boolean firstEntry = getStepHistory().isEmpty();

        if (board.getAt(endPosition) != null) return;
        if (firstEntry) addToStepHistory(new Move(clone, MoveType.ORIGIN));
        String diePosition = die.getPosition();
        boolean correctDistance = Boards.getManhattanDistance(diePosition, endPosition) == TIP_DISTANCE;

        if (!isMoveBackwards(diePosition, endPosition) && correctDistance && isDieCorrect(die) && firstEntry) {
            applyTip(die, endPosition);
            addToStepHistory(new Move(clone, TIP));
            setCurrentMoveDie(die.deepClone());
        }

        if(board.getAdjacentDie(die) != null && !(board.getAdjacentDie(die).length == 0)) {
            battle(die);
        }
    }

    /**
     * Method which compares adjacent die of opposing players and eliminates the loser according to the Contra gamemode
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
     * @return An array of games which represent each possible move that can be made by the player
     */
    public ContraMove[] generateLegalMoves() { // Can probably be abstracted
        List<Die> possibleDie = getCurrentPlayer().getDice();
        List<ContraMove> possibleMoves = new ArrayList<>();

        for (Die die : possibleDie) {
            for(Direction direction : Direction.values()) {
                if (isDirectionClear(direction, die)) {
                    ContraCublino clone = deepClone();
                    Die dieClone = die.deepClone();
                    clone.applyStep(dieClone, dieClone.getPositionOneOver(direction));
                    clone.endTurn();
                    // FIXME Remove the magic numbers below
                    ContraMove move = new ContraMove(clone, Die.dieToEnc(die).substring(1) + Die.dieToEnc(dieClone).substring(1));
                    possibleMoves.add(move);
                }
            }
        }
        return possibleMoves.toArray(ContraMove[]::new);
    }

    /**
     * Creates a deep copy of the ContraCublino
     * @return A deep copy the ContraCublino
     */
    public ContraCublino deepClone() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (ContraCublino) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    /**
     * Method which evaluates if the space directly ahead in the specified direction (Manhattan distance of 1) is clear
     * @param direction The direction which needs to be checked
     * @param die The die from which the origin is based
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
     * @param board The board to be evaluated
     * @return True if the game is valid, false otherwise
     */
    public boolean isGameValid(Boards board){
        return (isDiceAmountCorrect(board) && !hasBothWon(board));
    }

    @Override
    public String toString() {
        return board.getStringRepresentation();
    }
}
