package comp1140.ass2.gamelogic;

import comp1140.ass2.helperclasses.DeepCloneable;
import comp1140.ass2.state.Boards;
import comp1140.ass2.state.Die;
import comp1140.ass2.state.Direction;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static comp1140.ass2.state.Boards.BOARD_DIMENSION;

/**
 * A game mode of Cublino that extends from the Game class. It represents the game which is currently being played by the user
 *
 * @author Ziling Ouyang, Yuechen Liu
 */

public class PurCublino extends Game implements Serializable, DeepCloneable<Game> {

    /**
     * The distance of a jump
     */
    private static final int JUMP_DISTANCE = 2;

    /**
     * Constructor for PurCublino
     */
    public PurCublino() {
        super();
    }

    /**
     * Constructor for PurCublino
     */
    public PurCublino(boolean isWhite, Boards board) {
        super(isWhite, board);
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

        if (board.getAt(endPosition) != null) {
            addToStepHistory(new Move(clone, MoveType.INVALID));
            return;
        }

        String diePosition = die.getPosition();
        int distance = Boards.getManhattanDistance(diePosition, endPosition);
        MoveType moveType;
        boolean correctDie = firstEntry || isDieCorrect(die);

        if (isMoveNotBackwards(diePosition, endPosition) && distance == TIP_DISTANCE && correctDie && firstEntry) {
            applyTip(die, endPosition);
            setCurrentMoveDie(die);
            moveType = MoveType.TIP;
        } else if (isJumpValid(diePosition, endPosition) && distance == JUMP_DISTANCE && correctDie) {
            applyJump(die, endPosition);
            setCurrentMoveDie(die);
            moveType = MoveType.JUMP;
        } else {
            moveType = MoveType.INVALID;
        }

        if (firstEntry) {
            clearStepHistory();
            addToStepHistory(new Move(clone, MoveType.ORIGIN));
        }
        addToStepHistory(new Move(clone, moveType));
    }

    /**
     * A boolean function to evaluate if the correct number of dice is on the board.
     *
     * @param board The current board that is being played on
     * @return True if the board contains 14 dice
     */
    @Override
    public boolean isDiceAmountCorrect(Boards board) {
        return (board.getBlackPlayer().getDice().size() + board.getWhitePlayer().getDice().size() == 2 * BOARD_DIMENSION);
    }

    /**
     * Check if both players have simultaneously won
     *
     * @param board The board to be checked
     * @return True if both players have simultaneously won, false otherwise
     */
    @Override
    public boolean hasBothNotWon(Boards board) {

        List<Die> white = board.getWhitePlayer().getDice();
        List<Die> black = board.getBlackPlayer().getDice();

        return !white.stream().allMatch(Die::isWhiteDieFinished) || !black.stream().allMatch(Die::isBlackDieFinished);
    }

    /**
     * Checks if a jump can be performed i.e., the end position is null, it is not moving backwards, there is a die to jump over and the axis is the same
     *
     * @param startPosition The position of the die which will jump
     * @param endPosition   The position the die will jump to
     * @return True if the jump is valid, false otherwise
     */
    public boolean isJumpValid(String startPosition, String endPosition) {
        String middle = Boards.getMiddlePosition(startPosition, endPosition);
        return isMoveNotBackwards(startPosition, endPosition)
                && (board.getAt(Boards.getPositionX(middle), Boards.getPositionY(middle)) != null)
                && (Boards.sameAxis(startPosition, endPosition));
    }

    /**
     * Applies a jump given the die and the position the die will jump to
     *
     * @param initial     The die
     * @param endPosition The position the die will jump to
     */
    public void applyJump(Die initial, String endPosition) {
        String start = initial.getPosition();
        int index = getCurrentPlayer().getDice().indexOf(initial);
        if (board.getAt(endPosition) == null) {
            Die realDie = getCurrentPlayer().getDice().get(index);
            realDie.jump(realDie.getDirection(endPosition));
            initial.setDie(realDie);
            board.setAt(endPosition, realDie);
            board.setAt(start, null);
        }
    }

    /**
     * Checks if the most basic rules (correct number of dice and both players haven't simultaneously won)
     *
     * @param board The board to be checked
     * @return True if the board has no violated rules, false otherwise
     */
    public boolean isGameValid(Boards board) {
        return (isDiceAmountCorrect(board) && hasBothNotWon(board));
    }

    /**
     * Gets the winner from a completed Pur game
     *
     * @return An enum representing the winner
     */
    public GameResult getWinner() {
        int p1 = 0;
        int p2 = 0;
        int p1s = 0;
        int p2s = 0;

        for (Die d : board.getWhitePlayer().getDice()) {
            if (d.getPosition().charAt(1) == '6') {
                p1++;
                p1s += d.getTop();
            }
        }

        for (Die d : board.getBlackPlayer().getDice()) {
            if (d.getPosition().charAt(1) == '0') {
                p2++;
                p2s += d.getTop();
            }
        }

        if (p1 == 7 || p2 == 7) {
            if (p1s > p2s) return GameResult.WHITE_WINS;
            else if (p1s < p2s) return GameResult.BLACK_WINS;
            else return GameResult.TIE;
        } else return GameResult.UNFINISHED;
    }

    /**
     * Implements the deepClone method from DeepCloneable interface
     */
    public PurCublino deepClone() {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ois = new ObjectInputStream(bais);
            return (PurCublino) ois.readObject();
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
     * Generates the moves which can be reached by a tip
     *
     * @return List of moves which can be reached with only a tip
     */
    public List<PurMove> generatePurTip() {
        List<Die> possibleDie = getCurrentPlayer().getDice();
        List<PurMove> possibleMoves = new ArrayList<>();

        for (Die die : possibleDie) {
            for (Direction direction : Direction.values()) {
                if (isValidTipDirection(direction, die)) {
                    String move = "";
                    PurCublino clone = deepClone();
                    Die dieClone = die.deepClone();
                    clone.applyTip(dieClone, dieClone.getPositionOver(direction, 1));
                    move += Die.dieToEncoding(die).substring(1) + Die.dieToEncoding(dieClone).substring(1);
                    PurMove m = new PurMove(clone, move);
                    possibleMoves.add(m);
                }
            }
        }
        return possibleMoves;
    }

    /**
     * Based on the current state of the board, generates any possible sequence of steps i.e., a move
     *
     * @return Every possible move from the current board state
     */
    public PurMove[] generateLegalMoves() {
        List<PurMove> basicMoves = Stream.concat(generatePurJump().stream(), generatePurTip().stream()).collect(Collectors.toList());
        List<List<PurCublino.PurMove>> expanded = basicMoves.stream().map((x) -> x.getPossibleState().generatePurJump(x.getEncodedMove())).collect(Collectors.toList());
        List<PurCublino.PurMove> flat = expanded.stream().flatMap(List::stream).collect(Collectors.toList());
        List<PurCublino.PurMove> concat = Stream.concat(basicMoves.stream(), flat.stream()).collect(Collectors.toList());
        return Stream.concat(concat.stream(), generatePurMovesRecursion(flat).stream()).toArray(PurMove[]::new);
    }

    /**
     * A method to house the recursion required to generate moves for Pur
     *
     * @param movesToExpand A list of moves to expand into further states
     * @return moveToExpand but expanded by another branch of moves
     */
    public List<PurMove> generatePurMovesRecursion(List<PurMove> movesToExpand) {
        if (movesToExpand.isEmpty()) return new ArrayList<>();
        List<List<PurCublino.PurMove>> expanded = movesToExpand.stream().map((x) -> x.getPossibleState().generatePurJump(x.getEncodedMove())).collect(Collectors.toList());
        List<PurCublino.PurMove> flat = expanded.stream().flatMap(List::stream).collect(Collectors.toList());
        return Stream.concat(flat.stream(), generatePurMovesRecursion(flat).stream()).collect(Collectors.toList());
    }

    /**
     * Overloading generatePurJump so that it keeps track of moves that have already been played
     *
     * @param historicalMoves The moves which have already been played, in String form
     * @return The list of moves conditioned on the fact that they can't repeat moves that have already taken place
     */
    public List<PurMove> generatePurJump(String historicalMoves) {
        List<PurMove> possibleMoves = new ArrayList<>();
        Boards.Positions[] givenMoves = Boards.moveToPositions(historicalMoves);
        List<String> moves = Arrays.stream(givenMoves).map(Boards.Positions::toString).collect(Collectors.toList());
        Die die = board.getAt(moves.get(moves.size() - 1));

        for (Direction direction : Direction.values()) {
            if (isValidJumpDirection(direction, die)) {
                String move = historicalMoves.substring(0, historicalMoves.length() - 2);
                PurCublino clone = deepClone();
                Die dieClone = die.deepClone();
                clone.applyJump(dieClone, dieClone.getPositionOver(direction, 2));
                if (!moves.contains(dieClone.getPosition())) {
                    move += Die.dieToEncoding(die).substring(1) + Die.dieToEncoding(dieClone).substring(1);
                    PurMove m = new PurMove(clone, move);
                    possibleMoves.add(m);
                }
            }
        }
        return possibleMoves;
    }

    /**
     * Generates all possible future Game's which can be achieved from 1 jump step from the current game state
     *
     * @return All possible future Game's which can be achieved from 1 jump step from the current game state
     */
    public List<PurMove> generatePurJump() {
        List<Die> possibleDie = getCurrentPlayer().getDice();
        List<PurMove> possibleMoves = new ArrayList<>();

        for (Die die : possibleDie) {
            for (Direction direction : Direction.values()) {
                if (isValidJumpDirection(direction, die)) {
                    String move = "";
                    PurCublino clone = deepClone();
                    Die dieClone = die.deepClone();
                    clone.applyJump(dieClone, dieClone.getPositionOver(direction, 2));
                    move += Die.dieToEncoding(die).substring(1) + Die.dieToEncoding(dieClone).substring(1);
                    PurMove m = new PurMove(clone, move);
                    possibleMoves.add(m);
                }
            }
        }
        return possibleMoves;
    }

    /**
     * Confirms whether a jump in the selected direction is possible
     *
     * @param direction The direction of the jump
     * @param die       The die that will jump
     * @return True if possible, false otherwise
     */
    public boolean isValidJumpDirection(Direction direction, Die die) {
        return switch (direction) {
            case UP -> die.isWhite() && die.getY() + 2 < BOARD_DIMENSION
                    && (board.getAt(die.getX(), die.getY() + 1) != null)
                    && (board.getAt(die.getX(), die.getY() + 2) == null);
            case DOWN -> (!die.isWhite()) && die.getY() - 1 > 0
                    && (board.getAt(die.getX(), die.getY() - 1) != null)
                    && (board.getAt(die.getX(), die.getY() - 2) == null);
            case LEFT -> (die.getX() - 1) > 0
                    && (board.getAt(die.getX() - 1, die.getY()) != null)
                    && (board.getAt(die.getX() - 2, die.getY()) == null);
            case RIGHT -> (die.getX() + 2) < BOARD_DIMENSION
                    && (board.getAt(die.getX() + 1, die.getY()) != null)
                    && (board.getAt(die.getX() + 2, die.getY()) == null);
        };
    }

    /**
     * Confirms whether a tip in the selected direction would be possible
     *
     * @param direction The selected direction
     * @param die       The die to be tipped
     * @return True if possible, false otherwise
     */
    public boolean isValidTipDirection(Direction direction, Die die) {
        return switch (direction) {
            case RIGHT -> (die.getX() + 1) < BOARD_DIMENSION
                    && (board.getAt(die.getX() + 1, die.getY()) == null);
            case LEFT -> (die.getX()) > 0 && (board.getAt(die.getX() - 1, die.getY()) == null);
            case UP -> die.isWhite() && (die.getY() + 1) < BOARD_DIMENSION
                    && (board.getAt(die.getX(), die.getY() + 1) == null);
            case DOWN -> (!die.isWhite()) && (die.getY()) > 0
                    && (board.getAt(die.getX(), die.getY() - 1) == null);
        };
    }


    /**
     * Class to allow for potential moves to be easily read by the GUI and Cublino.java
     *
     * @author Ziling Ouyang, Modified by Yuechen Liu
     */
    public class PurMove extends GameMove {

        /**
         * The actual move that has been played in Game form
         */
        PurCublino possibleState;

        /**
         * The move to be played in an encoded form
         */
        String encodedMove;

        /**
         * Constructor for PurMove
         */
        public PurMove(PurCublino possibleState, String encodedMove) {
            super();
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
        public PurCublino getPossibleState() {
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
