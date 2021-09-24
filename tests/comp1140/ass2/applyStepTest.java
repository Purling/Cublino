package comp1140.ass2;

import comp1140.ass2.GameLogic.PurCublino;
import comp1140.ass2.State.Boards;
import comp1140.ass2.State.Die;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;

import static comp1140.ass2.Cublino.applyMovePur;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Timeout(value = 1000, unit = MILLISECONDS)
public class applyStepTest {

    @Test
    public void testPurGame(){

        int encodedState = 0;
        int moves = 1;

        for (int i = 0; i < ExampleGames.FULL_PUR_GAME.length - 1; i++) {

            String state = ExampleGames.FULL_PUR_GAME[i][encodedState];
            char gameMode = state.charAt(0);
            Boards.Positions[] positions = Boards.moveToPositions(ExampleGames.FULL_PUR_GAME[i][moves]);
            PurCublino pur = new PurCublino(Character.isUpperCase(gameMode));
            Boards board = new Boards(state);
            Boards endBoard = new Boards(ExampleGames.FULL_PUR_GAME[i+1][encodedState]);

            for(int j = 1; j < positions.length; j++){
                Die die1 = board.getAt(positions[j - 1].toString());
                board = pur.applyStep(board, die1, positions[j].toString());
            }

            assertEquals(endBoard, board, "State \"" + state + "\" with move \"" + Arrays.toString(positions) + "\"");
        }
    }

    // Create tests for edge cases e.g., invalid cases
}
