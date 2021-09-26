package comp1140.ass2;

import comp1140.ass2.GameLogic.ContraCublino;
import comp1140.ass2.GameLogic.Game;
import comp1140.ass2.GameLogic.PurCublino;
import comp1140.ass2.State.Boards;
import comp1140.ass2.State.Die;
import comp1140.ass2.State.Direction;

import java.util.Arrays;

import static comp1140.ass2.GameLogic.PurCublino.getWinner;
import static comp1140.ass2.State.Boards.boardToString;

public class Cublino {

    // Dice orientation
    // Applies for both black and white players
    // a - Top: 1 Forward: 2 Left: 3
    // b - Top: 1 Forward: 3 Left: 5
    // c - Top: 1 Forward: 4 Left: 2
    // d - Top: 1 Forward: 5 Left: 4
    // e - Top: 2 Forward: 1 Left: 4
    // f - Top: 2 Forward: 3 Left: 1
    // g - Top: 2 Forward: 4 Left: 6
    // h - Top: 2 Forward: 6 Left: 3
    // i - Top: 3 Forward: 1 Left: 2
    // j - Top: 3 Forward: 2 Left: 6
    // k - Top: 3 Forward: 5 Left: 1
    // l - Top: 3 Forward: 6 Left: 5
    // m - Top: 4 Forward: 1 Left: 5
    // n - Top: 4 Forward: 2 Left: 1
    // o - Top: 4 Forward: 5 Left: 6
    // p - Top: 4 Forward: 6 Left: 2
    // q - Top: 5 Forward: 1 Left: 3
    // r - Top: 5 Forward: 3 Left: 6
    // s - Top: 5 Forward: 4 Left: 1
    // t - Top: 5 Forward: 6 Left: 4
    // u - Top: 6 Forward: 2 Left: 4
    // v - Top: 6 Forward: 3 Left: 2
    // w - Top: 6 Forward: 4 Left: 5
    // x - Top: 6 Forward: 5 Left: 3

    /**
     * Task 3:
     * Determine whether the input state is well formed or not.
     * Note: you don't have to consider whether the state is valid for this task.
     * A state is well formed if it has the following format:
     * [variant][dice]*
     * where [variant] and [dice] are replaced by the corresponding substrings below. Note that [dice]* means zero or
     * more repetitions of the [dice] substring.
     *
     * 1. [variant] The variant substring is a single character which is either an upper or lower case 'p' or 'c' - The
     * letter encodes which variant the of the rules the game is using and the case represents the turn of the current
     * player.
     *
     * 2. [dice] The dice substring contains three characters. The first character can be either an upper or lower case
     * letter in the range 'a' to 'x'. The letter encodes the orientation of the dice and the case encodes which player
     * the dice belongs two. The next two characters encode the position of the dice in the format [column][row] where
     * the column is one character in the range 'a' to 'g' and the row is one character in the range '1' to '7'.
     *
     * See the "Encoding Game State" section in the README for more details.
     *
     * @param state a string representing a game state
     * @return true if the input state is well formed, otherwise false
     */
    public static Boolean isStateWellFormed(String state) {
        if (state != null && state.length() % 3 == 1) {
            if (state.charAt(0) == 'p' || state.charAt(0) == 'c'
                    || state.charAt(0) == 'P' || state.charAt(0) == 'C') {
                int l = (state.length() - 1) / 3;
                if (l != 0) {
                    int i = 1;
                    while (i < state.length()){
                        String x;
                        if (i+3 == state.length()) {
                            x = state.substring(i);
                        }
                        else {
                            x = state.substring(i, i + 3);
                        }

                        if (x.charAt(0) < 'A' || (x.charAt(0) > 'X' && x.charAt(0) < 'a')
                                || x.charAt(0) > 'x') {
                            return false;
                        }
                        else if (x.charAt(1) > 'g' || x.charAt(1) < 'a') {
                            return false;
                        }
                        else if (x.charAt(2) > '7' || x.charAt(2) < '1') {
                            return false;
                        }
                        i+=3;
                    }
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Task 4:
     * Determine whether the input state is valid.
     * A game state is valid if it satisfies the conditions below. Note that there are some shared conditions and
     * some conditions which are specific to each variant of the game. For this task you are expected to check states
     * from both variants.
     *
     * [Both Variants]
     * 1. The game state is well formed.
     * 2. No two dice occupy the same position on the board.
     *
     * [Pur]
     * 1. Each player has exactly seven dice.
     * 2. Both players do not have all seven of their dice on the opponent's end of the board (as the game would have
     * already finished before this)
     *
     * [Contra]
     * 1. Each player has no more than seven dice.
     * 2. No more than one player has a dice on the opponent's end of the board.
     *
     * @param state a string representing a game state
     * @return true if the input state is valid, otherwise false
     */
    public static Boolean isStateValid(String state) {

        char purMode = 'p';
        int noDie = 1;

        if(!isStateWellFormed(state) || (state.toLowerCase().charAt(0) == purMode && state.length() == noDie)) return false;

        char gameMode = state.toLowerCase().charAt(0);
        Boards board = new Boards();
        board.setWhiteAndBlackPlayer(state);
        PurCublino purCublino = new PurCublino();
        ContraCublino contraCublino = new ContraCublino();

        if(board.containsOverlappingPieces()) return false;

        if(gameMode == purMode){
            return purCublino.isGameValid(board);
        } else {
            return contraCublino.isGameValid(board);
        }
    }

    /**
     * Task 6:
     * Determine whether a state represents a finished Pur game, and if so who the winner is.
     *
     * A game of Cublino Pur is finished once one player has reached the opponent's end of the board with all seven of
     * their dice. Each player then adds the numbers facing upwards on their dice which have reached the opponent's end
     * of the board. The player with the highest total wins.
     *
     * You may assume that all states input into this method will be of the Pur variant and that the state will be
     * valid.
     *
     * @param state a Pur game state
     * @return 1 if player one has won, 2 if player two has won, 3 if the result is a draw, otherwise 0.
     */
    public static int isGameOverPur(String state) {
        if (state.charAt(0) != 'p' && state.charAt(0) != 'P') return 0;
        else return getWinner(state);
    }
    /**
     * Task 7:
     * Determine whether a single step of a move is valid for a given Pur game.
     * A step is encoded as follows: [position][position]. The [position] substring is a two character string encoding a
     * position on the board. The first position represents the starting position of the dice making the step and the
     * second position represents the ending position of the dice making the step. You may assume that the step strings
     * input into this method are well formed according to the above specification.
     *
     * A step is valid if it satisfies the following conditions:
     * 1. It represents either a tilt or a jump of a dice.
     * 2. The ending position of the step is not occupied.
     * 3. The step moves towards the opponent's end of the board or horizontally (along its current row).
     * 3. If it is a jump step, there is a dice in the position which is jumped over.
     *
     * You may assume that all states input into this method will be of the Pur variant and that the state will be
     * valid.
     *
     * @param state a Pur game state
     * @param step a string representing a single step of a move
     * @return true if the step is valid for the given state, otherwise false
     */
    public static Boolean isValidStepPur(String state, String step) {
        return PurCublino.checkPurStepValid(state, step);
    }

    /**
     * Task 8:
     * Determine whether a move (sequence of steps) is valid for a given game.
     * A move is encoded as follows [position]*. The [position] substring encodes a position on the board. This encoding
     * lists a sequence of positions that a dice will have as it makes a move. Note that [position]* means zero or more
     * repetitions of the [position] substring. You may assume that the move strings input into this method are well
     * formed according to the above specification.
     *
     * A move is valid if it satisfies the following conditions:
     * 1. The starting position of the move contains a dice belonging to the player who's turn it is.
     * 2. All steps in the move are valid.
     * 3. The move contains at least one step.
     * 4. Only the first step may be a tipping step.
     * 5. The starting and ending positions of the moved dice are different.
     *
     * You may assume that all states input into this method will be of the Pur variant and that the state will be
     * valid.
     *
     * @param state a Pur game state
     * @param move a string representing a move
     * @return true if the move is valid for the given state, otherwise false
     */
    public static Boolean isValidMovePur(String state, String move) {

        Boards board = new Boards(state);
        PurCublino pur = new PurCublino(Character.isUpperCase(state.charAt(0)));

        if(move.length() == 0) return false;
        Boards.Positions[] stepPositions = Boards.moveToPositions(move);

        if((board.getAt(stepPositions[0].getCoordinate()) != null)
                && pur.getCurrentPlayer().isWhite() == board.getAt(stepPositions[0].getCoordinate()).isDieWhite()){
            for(int i = 1; i < stepPositions.length; i++) {
                if (board != null) {
                    Die die = board.getAt(stepPositions[i - 1].getCoordinate());
                    board = pur.applyStep(board, die, stepPositions[i].getCoordinate());
                } else {
                    return false;
                }
            }
        }

        if(board == null || stepPositions[0].equals(stepPositions[stepPositions.length - 1])) return false;
        if(pur.getStepHistory().size() > 0) {
            pur.getStepHistory().remove(1); // Only first move can be a tip
            return pur.getStepHistory().stream().noneMatch((x) -> x.getType() == Game.moveType.TIP);
        } else {
            return false;
        }
    }

    /**
     * Task 9:
     * Given a Pur game state and a move to play, determine the state that results from that move being played.
     * If the move ends the game, the turn should be the player who would have played next had the game not ended. If
     * the move is invalid the game state should remain unchanged.
     *
     * You may assume that all states input into this method will be of the Pur variant and that the state will be
     * valid.
     *
     * @param state a Pur game state
     * @param move a move being played
     * @return the resulting state after the move has been applied
     */
    public static String applyMovePur(String state, String move) {

        if (!isValidMovePur(state, move)) return state;
        else{
            Boards board = new Boards(state);
            PurCublino pur = new PurCublino(Character.isUpperCase(state.charAt(0)));
            Boards.Positions[] stepPositions = Boards.moveToPositions(move);
            char p;
            if(state.charAt(0) == 'p') p = 'P';
            else p = 'p';

            for(int i = 1; i < stepPositions.length; i++){
                Die die = board.getAt(stepPositions[i - 1].getCoordinate());
                board = pur.applyStep(board, die, stepPositions[i].getCoordinate());
            }
            return p+boardToString(board);

        }
//        else {
//            Boards board = new Boards(state);
//            Boards.Positions[] position = Boards.moveToPositions(move);
//            Die die = board.getAt(position[0].getCoordinate());
//            for (int i =0; i<move.length()-4; i+=2) {
//                String m = move.substring(i, i + 4);
//                Direction d = Boards.getD(m);
//                if (Boards.isTip(m)){
//                    die.tip(d);
//                }
//                else{
//                    die.jump(d);
//                }
//            }
//            String mLast = move.substring(move.length() - 4);
//            Direction dLast = Boards.getD(mLast);
//            if (Boards.isTip(mLast)){
//                die.tip(dLast);
//            }
//            else {
//                die.jump(dLast);
//            }
//            int left = die.getLeft();
//            int top = die.getTop();
//            int front = die.getFront();
//            int[] orientation = {top, front, left};
//            int[][] orientations = new int[][] {
//                    {1, 2, 3},
//                    {1, 3, 5},
//                    {1, 4, 2},
//                    {1, 5, 4},
//                    {2, 1, 4},
//                    {2, 3, 1},
//                    {2, 4, 6},
//                    {2, 6, 3},
//                    {3, 1, 2},
//                    {3, 2, 6},
//                    {3, 5, 1},
//                    {3, 6, 5},
//                    {4, 1, 5},
//                    {4, 2, 1},
//                    {4, 5, 6},
//                    {4, 6, 2},
//                    {5, 1, 3},
//                    {5, 3, 6},
//                    {5, 4, 1},
//                    {5, 6, 4},
//                    {6, 2, 4},
//                    {6, 3, 2},
//                    {6, 4, 5},
//                    {6, 5, 3}
//            };
//
//            int index = Arrays.asList(orientations).indexOf(orientation);
//
//            int ind = state.indexOf(move.substring(0,2));
//
//            char dice;
//            if(state.charAt(ind-1) >= 'A' && state.charAt(ind-1) <= 'Z'){
//                dice = (char) ('A'+ index);
//            }
//            else{
//                dice = (char) ('a' + index);
//            }
//            String replace = dice + move.substring(move.length()-2);
//            StringBuilder update = new StringBuilder();
//
//            if (state.charAt(0) == 'P') update.append('p');
//            else if(state.charAt(0) == 'p') update.append('P');
//
//            if (dice >= 'a'){
//                update.append(state, 1, 22);
//                for(int i = 22; i <= 42; i += 3){
//                    String s = String.valueOf(state.charAt(i)) + String.valueOf(state.charAt(i + 1)) + String.valueOf(state.charAt(i + 2));
//                    if(state.charAt(i) > dice){
//                        update.append(s);
//                    }
//                    else if(state.charAt(i) <= dice){
//                        if (state.charAt(i + 1) >= replace.charAt(1)) {
//                            if (state.charAt(i + 2) >= replace.charAt(2)) {
//                                update.append(replace);
//                                update.append(state.substring(i));
//                                break;
//                            }
//                        }
//                        update.append(s);
//                    }
//                    update.append(replace);
//                }
//            }
//
//            else if (dice <= 'Z'){
//                for(int i = 1; i <= 22; i += 3){
//                    String s = String.valueOf(state.charAt(i)) + String.valueOf(state.charAt(i + 1)) + String.valueOf(state.charAt(i + 2));
//                    if(state.charAt(i) > dice){
//                        update.append(s);
//                    }
//                    else if(state.charAt(i) <= dice){
//                        if (state.charAt(i + 1) >= replace.charAt(1)) {
//                            if (state.charAt(i + 2) >= replace.charAt(2)) {
//                                update.append(replace);
//                                update.append(state.substring(i));
//                                break;
//                            }
//                        }
//                        update.append(s);
//                    }
//                }
//
//            }
//
//            return update.toString().replaceFirst(state.charAt(ind-1)+move.substring(0,2), "");
//
//        }
    }

    /**
     * Task 11:
     * Given a valid Pur game state, return a valid move.
     * This task imposes an additional constraint that moves returned must not revisit positions previously occupied as
     * part of the move (ie. a move may not contain a jumping move followed by another jumping move back to the previous
     * position).
     *
     * You may assume that all states input into this method will be of the Pur variant and that the state will be
     * valid.
     *
     * @param state a Pur game state
     * @return a valid move for the current game state.
     */
    public static String generateMovePur(String state) {
        return null; // FIXME Task 11 (D)
        // FIXME Task 13 (HD): Implement a "smart" generateMove()
    }

    /**
     * Task 14a:
     * Determine whether a state represents a finished Contra game, and if so who the winner is.
     *
     * A player wins a game of Cublino Contra by reaching the opponent's end of the board with one of their dice.
     *
     * You may assume that all states input into this method will be of the Contra variant and that the state will be
     * valid.
     *
     * @param state a Contra game state
     * @return 1 if player one has won, 2 if player two has won, otherwise 0.
     */
    public static int isGameOverContra(String state) {
        return -1; // FIXME Task 14a (HD)
    }

    /**
     * Task 14b:
     * Given a Contra game state and a move to play, determine the state that results from that move being played.
     * If the move ends the game, the turn should be the player who would have played next had the game not ended. If
     * the move is invalid the game state should remain unchanged. See the README for what constitutes a valid Contra
     * move and the rules for updating the game state.
     *
     * You may assume that all states input into this method will be of the Contra variant and that the state will be
     * valid.
     *
     * @param state a Contra game state
     * @param move a move being played
     * @return the resulting state after the move has been applied
     */
    public static String applyMoveContra(String state, String move) {
        return null; // FIXME Task 14b (HD)
    }

    /**
     * Task 14c:
     * Given a valid Contra game state, return a valid move.
     *
     * You may assume that all states input into this method will be of the Contra variant and that the state will be
     * valid.
     *
     * @param state the Pur game state
     * @return a move for the current game state.
     */
    public static String generateMoveContra(String state) {
        return null; // FIXME Task 14c (HD)
    }

}
