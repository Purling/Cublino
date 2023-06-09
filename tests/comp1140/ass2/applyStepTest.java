package comp1140.ass2;

import comp1140.ass2.gamelogic.Game;
import comp1140.ass2.gamelogic.PurCublino;
import comp1140.ass2.state.Boards;
import comp1140.ass2.state.Die;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;

import static comp1140.ass2.gamelogic.Game.MoveType.*;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ziling Ouyang
 */

@Timeout(value = 1000, unit = MILLISECONDS)
public class applyStepTest {

    int encodedState = 0;
    int moves = 1;

    @Test
    public void testPurGame() {

        for (int i = 0; i < ExampleGames.FULL_PUR_GAME.length - 1; i++) {

            String state = ExampleGames.FULL_PUR_GAME[i][encodedState];
            char gameMode = state.charAt(0);
            Boards.Positions[] positions = Boards.moveToPositions(ExampleGames.FULL_PUR_GAME[i][moves]);
            PurCublino pur = new PurCublino(Character.isUpperCase(gameMode), new Boards(state));
            Boards board = pur.getBoard();
            Boards endBoard = new Boards(ExampleGames.FULL_PUR_GAME[i + 1][encodedState]);

            for (int j = 1; j < positions.length; j++) {
                Die die = board.getAt(positions[j - 1].toString());
                pur.applyStep(die, positions[j].toString());
            }

            assertEquals(endBoard, board, "State \"" + state + "\" with move \"" + Arrays.toString(positions) + "\"");
        }
    }

    @Test
    public void testStepHistoryNumber() {

        for (int i = 0; i < ExampleGames.FULL_PUR_GAME.length - 1; i++) {

            String state = ExampleGames.FULL_PUR_GAME[i][encodedState];
            char gameMode = state.charAt(0);
            Boards.Positions[] positions = Boards.moveToPositions(ExampleGames.FULL_PUR_GAME[i][moves]);
            PurCublino pur = new PurCublino(Character.isUpperCase(gameMode), new Boards(state));
            Boards board = pur.getBoard();

            for (int j = 1; j < positions.length; j++) {
                Die die = board.getAt(positions[j - 1].toString());
                pur.applyStep(die, positions[j].toString());
            }

            assertEquals(positions.length, pur.getStepHistory().size(), "State \"" + state + "\" with move \"" + Arrays.toString(positions) + "\"");
        }
    }

    @Test
    public void testStepHistoryType() {

        for (int i = 0; i < ExampleGames.FULL_PUR_GAME.length - 1; i++) {

            String state = ExampleGames.FULL_PUR_GAME[i][encodedState];
            char gameMode = state.charAt(0);
            Boards.Positions[] positions = Boards.moveToPositions(ExampleGames.FULL_PUR_GAME[i][moves]);
            PurCublino pur = new PurCublino(Character.isUpperCase(gameMode), new Boards(state));
            Boards board = pur.getBoard();

            for (int j = 1; j < positions.length; j++) {
                Die die = board.getAt(positions[j - 1].toString());
                pur.applyStep(die, positions[j].toString());
            }

            assertEquals(ORIGIN, pur.getStepHistory().get(0).getType(), "State \"" + state + "\" with move \"" + Arrays.toString(positions) + "\"");
            assertEquals(positions.length, pur.getStepHistory().size(), "State \"" + state + "\" with move \"" + Arrays.toString(positions) + "\"");
            assertNotEquals(ORIGIN, pur.getStepHistory().get(1).getType(), "State \"" + state + "\" with move \"" + Arrays.toString(positions) + "\"");
            assertNotNull(pur.getStepHistory().get(1).getType(), "State \"" + state + "\" with move \"" + Arrays.toString(positions) + "\"");

            for (int j = 2; j < pur.getStepHistory().size(); j++) {
                assertEquals(JUMP, pur.getStepHistory().get(j).getType(), "State \"" + state + "\" with move \"" + Arrays.toString(positions) + "\"");
            }
        }
    }

    @Test
    public void testInvalidMove() {

        for (int i = 0; i < FULL_WRONG_PUR_GAME.length - 1; i++) {

            String state = FULL_WRONG_PUR_GAME[i][encodedState];
            char gameMode = state.charAt(0);
            Boards.Positions[] positions = Boards.moveToPositions(FULL_WRONG_PUR_GAME[i][moves]);
            PurCublino pur = new PurCublino(Character.isUpperCase(gameMode), new Boards(state));
            Boards board = pur.getBoard();

            for (int j = 1; j < positions.length; j++) {
                Die die = board.getAt(positions[j - 1].toString());
                pur.applyStep(die, positions[j].toString());
            }

            assertTrue(pur.getStepHistory().stream().anyMatch((x) -> x.getType() == Game.MoveType.INVALID), "State \"" + state + "\" with move \"" + Arrays.toString(positions) + "\"");
        }
    }

    @Test
    public void testContainsOriginal() {

        for (int i = 0; i < ExampleGames.FULL_PUR_GAME.length - 1; i++) {

            String state = ExampleGames.FULL_PUR_GAME[i][encodedState];
            char gameMode = state.charAt(0);
            Boards.Positions[] positions = Boards.moveToPositions(ExampleGames.FULL_PUR_GAME[i][moves]);
            PurCublino pur = new PurCublino(Character.isUpperCase(gameMode), new Boards(state));
            Boards board = pur.getBoard();

            for (int j = 1; j < positions.length; j++) {
                Die die = board.getAt(positions[j - 1].toString());
                pur.applyStep(die, positions[j].toString());
            }

            assertEquals(ORIGIN, pur.getStepHistory().get(0).getType(), "State \"" + state + "\" with move \"" + Arrays.toString(positions) + "\"");
            assertEquals(new Boards(state), pur.getStepHistory().get(0).getBoard(), "State \"" + state + "\" with move \"" + Arrays.toString(positions) + "\"");
        }
    }

    @Test
    public void testTypeCorrect() {

        PurCublino pur;
        Boards board;
        Boards.Positions[] positions;
        Die die;

        pur = new PurCublino(Character.isUpperCase('P'), new Boards("PWa1Wb1Wc1Wd1We1Wf1Wg1va7vb7vc7vd7ve7vf7vg7"));
        board = pur.getBoard();
        positions = Boards.moveToPositions("d1d2");
        die = board.getAt(positions[0].toString());
        pur.applyStep(die, positions[1].toString());
        assertEquals(ORIGIN, pur.getStepHistory().get(0).getType(), "Type is not as expected");
        assertEquals(TIP, pur.getStepHistory().get(1).getType(), "Type is not as expected");

        pur = new PurCublino(Character.isUpperCase('P'), new Boards("PWa1Wb1Wc1We1Wf1Wg1Ld2if6va7vb7vc7vd7ve7vg7"));
        board = pur.getBoard();
        positions = Boards.moveToPositions("e1e2c2");
        die = board.getAt(positions[0].toString());
        pur.applyStep(die, positions[1].toString());
        pur.applyStep(die, positions[2].toString());
        assertEquals(ORIGIN, pur.getStepHistory().get(0).getType(), "Type is not as expected");
        assertEquals(TIP, pur.getStepHistory().get(1).getType(), "Type is not as expected");
        assertEquals(JUMP, pur.getStepHistory().get(2).getType(), "Type is not as expected");

        pur = new PurCublino(Character.isUpperCase('p'), new Boards("pWa1Wb1Wc1Wf1Wg1Ld2Bc3id6if6va7vb7vc7ve7vg7"));
        board = pur.getBoard();
        positions = Boards.moveToPositions("b7d7f7f5a1");
        die = board.getAt(positions[0].toString());
        pur.applyStep(die, positions[1].toString());
        pur.applyStep(die, positions[2].toString());
        pur.applyStep(die, positions[3].toString());
        pur.applyStep(die, positions[4].toString());
        assertEquals(ORIGIN, pur.getStepHistory().get(0).getType(), "Type is not as expected");
        assertEquals(JUMP, pur.getStepHistory().get(1).getType(), "Type is not as expected");
        assertEquals(JUMP, pur.getStepHistory().get(2).getType(), "Type is not as expected");
        assertEquals(JUMP, pur.getStepHistory().get(3).getType(), "Type is not as expected");
        assertEquals(INVALID, pur.getStepHistory().get(4).getType(), "Type is not as expected");
    }

    public static final String[][] FULL_WRONG_PUR_GAME = {
            new String[]{"PWa1Wb1Wc1Wd1We1Wf1Wg1va7vb7vc7vd7ve7vf7vg7", "d1d5"}
            , new String[]{"pWa1Wb1Wc1We1Wf1Wg1Ld2va7vb7vc7vd7ve7vf7vg7", "f7f2"}
            , new String[]{"PWa1Wb1Wc1We1Wf1Wg1Ld2if6va7vb7vc7vd7ve7vg7", "e1e2c5"}
            , new String[]{"pWa1Wb1Wc1Wf1Wg1Lc2Ld2if6va7vb7vc7vd7ve7vg7", "d7d1"}
            , new String[]{"PWa1Wb1Wc1Wf1Wg1Lc2Ld2id6if6va7vb7vc7ve7vg7", "c2c6"}
//            , new String[]{"pWa1Wb1Wc1Wf1Wg1Ld2Bc3id6if6va7vb7vc7ve7vg7", "b7d2f6f1"}
//            , new String[]{"PWa1Wb1Wc1Wf1Wg1Ld2Bc3vf5id6if6va7vc7ve7vg7", "c1c6e4"}
//            , new String[]{"pWa1Wb1Wf1Wg1Ld2Le2Bc3vf5id6if6va7vc7ve7vg7", "c7d2f1"}
            , new String[]{"PWa1Wb1Wf1Wg1Ld2Le2Bc3vf5id6if6va7ve7ff7vg7", "a1c5"}
            , new String[]{"pWb1Wc1Wf1Wg1Ld2Le2Bc3vf5id6if6va7ve7ff7vg7", "f5f1"}
            , new String[]{"PWb1Wc1Wf1Wg1Ld2Le2Bc3if4id6if6va7ve7ff7vg7", "d2d6"}
//            , new String[]{"pWb1Wc1Wf1Wg1Le2Bc3Bd3if4id6if6va7ve7ff7vg7", "f7f1f4"}
            , new String[]{"PWb1Wc1Wf1Wg1Le2Bc3Bd3ff3if4id6if6va7ve7vg7", "f1e6"}
            , new String[]{"pWb1Wc1Ge1Wg1Le2Bc3Bd3ff3if4id6if6va7ve7vg7", "d6d1"}
//            , new String[]{"PWb1Wc1Ge1Wg1Le2Bc3Bd3ff3if4cd5if6va7ve7vg7", "c1c4c1"}
            , new String[]{"pWb1Ge1Wg1Le2Bc3Bd3ff3Lc4if4cd5if6va7ve7vg7", "f4e1"}
//            , new String[]{"PWb1Ge1Wg1Le2Bc3Bd3ff3Lc4qe4cd5if6va7ve7vg7", "d3e7g4"}
            , new String[]{"pWb1Ge1Wg1Le2Bc3ff3Rg3Lc4qe4cd5if6va7ve7vg7", "d5d1"}
            , new String[]{"PWb1Ge1Wg1Le2Bc3ff3Rg3Lc4pd4qe4if6va7ve7vg7", "c3b2"}
            , new String[]{"pWb1Ge1Wg1Le2Fb3ff3Rg3Lc4pd4qe4if6va7ve7vg7", "d4f3"}
            , new String[]{"PWb1Ge1Wg1Le2Fb3ff3Rg3Lc4qe4pf4if6va7ve7vg7", "b1c5"}
            , new String[]{"pSc1Ge1Wg1Le2Fb3ff3Rg3Lc4qe4pf4if6va7ve7vg7", "f4g1"}
            , new String[]{"PSc1Ge1Wg1Le2Fb3ff3Rg3Lc4qe4hg4if6va7ve7vg7", "b3a7"}
//            , new String[]{"pSc1Ge1Wg1Le2Va3ff3Rg3Lc4qe4hg4if6va7ve7vg7", "g4f1d2"}
            , new String[]{"PSc1Ge1Wg1Le2Va3ff3Rg3Lc4pd4qe4if6va7ve7vg7", "e1a1"}
//            , new String[]{"pSc1Wg1Le2Va3Ge3ff3Rg3Lc4pd4qe4if6va7ve7vg7", "d4f1f2d2"}
            , new String[]{"PSc1Wg1pd2Le2Va3Ge3ff3Rg3Lc4qe4if6va7ve7vg7", "c1c5"}
//            , new String[]{"pWg1Kc2pd2Le2Va3Ge3ff3Rg3Lc4qe4if6va7ve7vg7", "e7e1g6"}
            , new String[]{"PWg1Kc2pd2Le2Va3Ge3ff3Rg3Lc4qe4if6ig6va7vg7", "a3b6"}
//            , new String[]{"pWg1Kc2pd2Le2Ge3ff3Rg3Pa4Lc4qe4if6ig6va7vg7", "g7a7f5"}
            , new String[]{"PWg1Kc2pd2Le2Ge3ff3Rg3Pa4Lc4qe4rf5if6ig6va7", "a4a1"}
            , new String[]{"pWg1Kc2pd2Le2Ge3ff3Rg3Lc4qe4Ca5rf5if6ig6va7", "g6b6"}
    };
//            , new String[]{"PWg1Kc2pd2Le2Ge3ff3Rg3Lc4qe4Ca5rf5ie6if6va7", "g1f1"}
//            , new String[]{"pGf1Kc2pd2Le2Ge3ff3Rg3Lc4qe4Ca5rf5ie6if6va7", "e6e5"}
//            , new String[]{"PGf1Kc2pd2Le2Ge3ff3Rg3Lc4qe4Ca5ce5rf5if6va7", "e2f2f4d4"}
//            , new String[]{"pGf1Kc2pd2Ge3ff3Rg3Lc4Td4qe4Ca5ce5rf5if6va7", "a7a6a4"}
//            , new String[]{"PGf1Kc2pd2Ge3ff3Rg3ia4Lc4Td4qe4Ca5ce5rf5if6", "f1e1"}
//            , new String[]{"pCe1Kc2pd2Ge3ff3Rg3ia4Lc4Td4qe4Ca5ce5rf5if6", "e5g5"}
//            , new String[]{"PCe1Kc2pd2Ge3ff3Rg3ia4Lc4Td4qe4Ca5rf5cg5if6", "a5b5"}
//            , new String[]{"pCe1Kc2pd2Ge3ff3Rg3ia4Lc4Td4qe4Gb5rf5cg5if6", "f3d3"}
//            , new String[]{"PCe1Kc2pd2fd3Ge3Rg3ia4Lc4Td4qe4Gb5rf5cg5if6", "c2b2"}
//            , new String[]{"pCe1Xb2pd2fd3Ge3Rg3ia4Lc4Td4qe4Gb5rf5cg5if6", "f5e5"}
//            , new String[]{"PCe1Xb2pd2fd3Ge3Rg3ia4Lc4Td4qe4Gb5be5cg5if6", "b5b6"}
//            , new String[]{"pCe1Xb2pd2fd3Ge3Rg3ia4Lc4Td4qe4be5cg5Jb6if6", "g5f5d5"}
//            , new String[]{"PCe1Xb2pd2fd3Ge3Rg3ia4Lc4Td4qe4sd5be5Jb6if6", "b2a2"}
//            , new String[]{"pCe1Oa2pd2fd3Ge3Rg3ia4Lc4Td4qe4sd5be5Jb6if6", "e4e2c2"}
//            , new String[]{"PCe1Oa2qc2pd2fd3Ge3Rg3ia4Lc4Td4sd5be5Jb6if6", "e3e4"}
//            , new String[]{"pCe1Oa2qc2pd2fd3Rg3ia4Lc4Td4Je4sd5be5Jb6if6", "c2c1"}
//            , new String[]{"Pac1Ce1Oa2pd2fd3Rg3ia4Lc4Td4Je4sd5be5Jb6if6", "g3g4"}
//            , new String[]{"pac1Ce1Oa2pd2fd3ia4Lc4Td4Je4Og4sd5be5Jb6if6", "d3d1b1"}
//            , new String[]{"Pfb1ac1Ce1Oa2pd2ia4Lc4Td4Je4Og4sd5be5Jb6if6", "b6a6"}
//            , new String[]{"pfb1ac1Ce1Oa2pd2ia4Lc4Td4Je4Og4sd5be5Aa6if6", "e5e3"}
//            , new String[]{"Pfb1ac1Ce1Oa2pd2be3ia4Lc4Td4Je4Og4sd5Aa6if6", "e1e2c2"}
//            , new String[]{"pfb1ac1Oa2Ic2pd2be3ia4Lc4Td4Je4Og4sd5Aa6if6", "d5d3d1"}
//            , new String[]{"Pfb1ac1sd1Oa2Ic2pd2be3ia4Lc4Td4Je4Og4Aa6if6", "e4e5"}
//            , new String[]{"pfb1ac1sd1Oa2Ic2pd2be3ia4Lc4Td4Og4Re5Aa6if6", "c1e1"}
//            , new String[]{"Pfb1sd1ae1Oa2Ic2pd2be3ia4Lc4Td4Og4Re5Aa6if6", "c4e4e6"}
//            , new String[]{"pfb1sd1ae1Oa2Ic2pd2be3ia4Td4Og4Re5Aa6Le6if6", "f6f5d5"}
//            , new String[]{"Pfb1sd1ae1Oa2Ic2pd2be3ia4Td4Og4cd5Re5Aa6Le6", "c2e2"}
//            , new String[]{"pfb1sd1ae1Oa2pd2Ie2be3ia4Td4Og4cd5Re5Aa6Le6", "b1a1"}
//            , new String[]{"Pva1sd1ae1Oa2pd2Ie2be3ia4Td4Og4cd5Re5Aa6Le6", "e6d6"}
//            , new String[]{"pva1sd1ae1Oa2pd2Ie2be3ia4Td4Og4cd5Re5Aa6Hd6", "a4b4"}
//            , new String[]{"Pva1sd1ae1Oa2pd2Ie2be3eb4Td4Og4cd5Re5Aa6Hd6", "d6d7"}
//            , new String[]{"pva1sd1ae1Oa2pd2Ie2be3eb4Td4Og4cd5Re5Aa6Ad7", "d5d3f3"}
//            , new String[]{"Pva1sd1ae1Oa2pd2Ie2be3cf3eb4Td4Og4Re5Aa6Ad7", "a6b6"}
//            , new String[]{"pva1sd1ae1Oa2pd2Ie2be3cf3eb4Td4Og4Re5Jb6Ad7", "b4c4"}
//            , new String[]{"Pva1sd1ae1Oa2pd2Ie2be3cf3mc4Td4Og4Re5Jb6Ad7", "g4g5"}
//            , new String[]{"pva1sd1ae1Oa2pd2Ie2be3cf3mc4Td4Re5Gg5Jb6Ad7", "d1c1"}
//            , new String[]{"Pva1wc1ae1Oa2pd2Ie2be3cf3mc4Td4Re5Gg5Jb6Ad7", "d4e4"}
//            , new String[]{"pva1wc1ae1Oa2pd2Ie2be3cf3mc4Pe4Re5Gg5Jb6Ad7", "a1b1d1"}
//            , new String[]{"Pwc1fd1ae1Oa2pd2Ie2be3cf3mc4Pe4Re5Gg5Jb6Ad7", "d7e7"}
//            , new String[]{"pwc1fd1ae1Oa2pd2Ie2be3cf3mc4Pe4Re5Gg5Jb6Je7", "c4d4f4f2"}
//            , new String[]{"Pwc1fd1ae1Oa2pd2Ie2qf2be3cf3Pe4Re5Gg5Jb6Je7", "e5e6"}
//            , new String[]{"pwc1fd1ae1Oa2pd2Ie2qf2be3cf3Pe4Gg5Jb6Oe6Je7", "f2f1"}
//            , new String[]{"Pwc1fd1ae1af1Oa2pd2Ie2be3cf3Pe4Gg5Jb6Oe6Je7", "e2f2f4d4"}
//            , new String[]{"pwc1fd1ae1af1Oa2pd2be3cf3Ed4Pe4Gg5Jb6Oe6Je7", "e3d3"}
//            , new String[]{"Pwc1fd1ae1af1Oa2pd2fd3cf3Ed4Pe4Gg5Jb6Oe6Je7", "e4f4"}
//            , new String[]{"pwc1fd1ae1af1Oa2pd2fd3cf3Ed4Hf4Gg5Jb6Oe6Je7", "e1g1"}
//            , new String[]{"Pwc1fd1af1ag1Oa2pd2fd3cf3Ed4Hf4Gg5Jb6Oe6Je7", "e6d6"}
//            , new String[]{"pwc1fd1af1ag1Oa2pd2fd3cf3Ed4Hf4Gg5Jb6Dd6Je7", "f3f2"}
//            , new String[]{"Pwc1fd1af1ag1Oa2pd2pf2fd3Ed4Hf4Gg5Jb6Dd6Je7", "g5f5"}
//            , new String[]{"pwc1fd1af1ag1Oa2pd2pf2fd3Ed4Hf4Cf5Jb6Dd6Je7", "d1e1"}
//            , new String[]{"Pwc1be1af1ag1Oa2pd2pf2fd3Ed4Hf4Cf5Jb6Dd6Je7", "f4e4c4"}
//            , new String[]{"pwc1be1af1ag1Oa2pd2pf2fd3Pc4Ed4Cf5Jb6Dd6Je7", "c1b1"}
//            , new String[]{"Pgb1be1af1ag1Oa2pd2pf2fd3Pc4Ed4Cf5Jb6Dd6Je7", "b6c6e6"}
//            , new String[]{"pgb1be1af1ag1Oa2pd2pf2fd3Pc4Ed4Cf5Dd6Ue6Je7", "f2e2c2"}
//            , new String[]{"Pgb1be1af1ag1Oa2tc2pd2fd3Pc4Ed4Cf5Dd6Ue6Je7", "f5g5"}
//            , new String[]{"pgb1be1af1ag1Oa2tc2pd2fd3Pc4Ed4Gg5Dd6Ue6Je7", "b1a1"}
//            , new String[]{"Pca1be1af1ag1Oa2tc2pd2fd3Pc4Ed4Gg5Dd6Ue6Je7", "d6d7f7"}
//            , new String[]{"pca1be1af1ag1Oa2tc2pd2fd3Pc4Ed4Gg5Ue6Je7Ef7", "c2e2"}
//            , new String[]{"Pca1be1af1ag1Oa2pd2te2fd3Pc4Ed4Gg5Ue6Je7Ef7", "e6f6"}
//            , new String[]{"pca1be1af1ag1Oa2pd2te2fd3Pc4Ed4Gg5Nf6Je7Ef7", "d2f2"}
//            , new String[]{"Pca1be1af1ag1Oa2te2pf2fd3Pc4Ed4Gg5Nf6Je7Ef7", "e7g7"}
//            , new String[]{"pca1be1af1ag1Oa2te2pf2fd3Pc4Ed4Gg5Nf6Ef7Jg7", "f1d1"}
//            , new String[]{"Pca1ad1be1ag1Oa2te2pf2fd3Pc4Ed4Gg5Nf6Ef7Jg7", "f6g6"}
//            , new String[]{"pca1ad1be1ag1Oa2te2pf2fd3Pc4Ed4Gg5Ag6Ef7Jg7", "d3e3"}
//            , new String[]{"Pca1ad1be1ag1Oa2te2pf2be3Pc4Ed4Gg5Ag6Ef7Jg7", "g7e7"}
//            , new String[]{"pca1ad1be1ag1Oa2te2pf2be3Pc4Ed4Gg5Ag6Je7Ef7", "d1f1"}
//            , new String[]{"Pca1be1af1ag1Oa2te2pf2be3Pc4Ed4Gg5Ag6Je7Ef7", "c4e4"}
//            , new String[]{"pca1be1af1ag1Oa2te2pf2be3Ed4Pe4Gg5Ag6Je7Ef7", "e3f3"}
//            , new String[]{"Pca1be1af1ag1Oa2te2pf2rf3Ed4Pe4Gg5Ag6Je7Ef7", "d4f4"}
//            , new String[]{"pca1be1af1ag1Oa2te2pf2rf3Pe4Ef4Gg5Ag6Je7Ef7", "e2d2"}
//            , new String[]{"Pca1be1af1ag1Oa2ld2pf2rf3Pe4Ef4Gg5Ag6Je7Ef7", "a2a3"}
//            , new String[]{"pca1be1af1ag1ld2pf2Ga3rf3Pe4Ef4Gg5Ag6Je7Ef7", "f2e2"}
//            , new String[]{"Pca1be1af1ag1ld2te2Ga3rf3Pe4Ef4Gg5Ag6Je7Ef7", "f4f5"}
//            , new String[]{"pca1be1af1ag1ld2te2Ga3rf3Pe4Uf5Gg5Ag6Je7Ef7", "e2f2"}
//            , new String[]{"Pca1be1af1ag1ld2pf2Ga3rf3Pe4Uf5Gg5Ag6Je7Ef7", "g5e5"}
//            , new String[]{"pca1be1af1ag1ld2pf2Ga3rf3Pe4Ge5Uf5Ag6Je7Ef7", "d2e2"}
//            , new String[]{"Pca1be1af1ag1te2pf2Ga3rf3Pe4Ge5Uf5Ag6Je7Ef7", "f5f6"}
//            , new String[]{"pca1be1af1ag1te2pf2Ga3rf3Pe4Ge5Tf6Ag6Je7Ef7", "f3g3"}
//            , new String[]{"Pca1be1af1ag1te2pf2Ga3vg3Pe4Ge5Tf6Ag6Je7Ef7", "e4e6"}
//            , new String[]{"pca1be1af1ag1te2pf2Ga3vg3Ge5Pe6Tf6Ag6Je7Ef7", "e2d2"}
//            , new String[]{"Pca1be1af1ag1ld2pf2Ga3vg3Ge5Pe6Tf6Ag6Je7Ef7", "e5f5"}
//            , new String[]{"pca1be1af1ag1ld2pf2Ga3vg3Wf5Pe6Tf6Ag6Je7Ef7", "f2g2"}
//            , new String[]{"Pca1be1af1ag1ld2hg2Ga3vg3Wf5Pe6Tf6Ag6Je7Ef7", "g6g7"}
//            , new String[]{"pca1be1af1ag1ld2hg2Ga3vg3Wf5Pe6Tf6Je7Ef7Qg7", "d2d1"}
//            , new String[]{"Pca1wd1be1af1ag1hg2Ga3vg3Wf5Pe6Tf6Je7Ef7Qg7", "f7d7"}
//            , new String[]{"pca1wd1be1af1ag1hg2Ga3vg3Wf5Pe6Tf6Ed7Je7Qg7", "d1c1"}
//            , new String[]{"Pca1gc1be1af1ag1hg2Ga3vg3Wf5Pe6Tf6Ed7Je7Qg7", "f5e5"}
//            , new String[]{"pca1gc1be1af1ag1hg2Ga3vg3Ge5Pe6Tf6Ed7Je7Qg7", "e1d1"}
//            , new String[]{"Pca1gc1fd1af1ag1hg2Ga3vg3Ge5Pe6Tf6Ed7Je7Qg7", "e5d5"}
//            , new String[]{"pca1gc1fd1af1ag1hg2Ga3vg3Cd5Pe6Tf6Ed7Je7Qg7", "g1e1"}
//            , new String[]{"Pca1gc1fd1ae1af1hg2Ga3vg3Cd5Pe6Tf6Ed7Je7Qg7", "d7f7"}
//            , new String[]{"pca1gc1fd1ae1af1hg2Ga3vg3Cd5Pe6Tf6Je7Ef7Qg7", "g3g1"}
//            , new String[]{"Pca1gc1fd1ae1af1vg1hg2Ga3Cd5Pe6Tf6Je7Ef7Qg7", "e6g6"}
//            , new String[]{"pca1gc1fd1ae1af1vg1hg2Ga3Cd5Tf6Pg6Je7Ef7Qg7", "c1b1"}
//            , new String[]{"Pca1cb1fd1ae1af1vg1hg2Ga3Cd5Tf6Pg6Je7Ef7Qg7", "d5c5"}
//            , new String[]{"pca1cb1fd1ae1af1vg1hg2Ga3Sc5Tf6Pg6Je7Ef7Qg7", "b1c1"}
//            , new String[]{"Pca1gc1fd1ae1af1vg1hg2Ga3Sc5Tf6Pg6Je7Ef7Qg7", "f6e6"}
//            , new String[]{"pca1gc1fd1ae1af1vg1hg2Ga3Sc5Le6Pg6Je7Ef7Qg7", "a1b1"}
//            , new String[]{"Pgb1gc1fd1ae1af1vg1hg2Ga3Sc5Le6Pg6Je7Ef7Qg7", "g6f6d6"}
//            , new String[]{"pgb1gc1fd1ae1af1vg1hg2Ga3Sc5Td6Le6Je7Ef7Qg7", "b1a1"}
//            , new String[]{"Pca1gc1fd1ae1af1vg1hg2Ga3Sc5Td6Le6Je7Ef7Qg7", "d6d7"}
//            , new String[]{"pca1gc1fd1ae1af1vg1hg2Ga3Sc5Le6Dd7Je7Ef7Qg7", "g2f2"}
//            , new String[]{"Pca1gc1fd1ae1af1vg1pf2Ga3Sc5Le6Dd7Je7Ef7Qg7", "c5c6"}
//            , new String[]{"pca1gc1fd1ae1af1vg1pf2Ga3Kc6Le6Dd7Je7Ef7Qg7", "f2g2"}
//            , new String[]{"Pca1gc1fd1ae1af1vg1hg2Ga3Kc6Le6Dd7Je7Ef7Qg7", "a3b3"}
//            , new String[]{"pca1gc1fd1ae1af1vg1hg2Wb3Kc6Le6Dd7Je7Ef7Qg7", "a1b1"}
//            , new String[]{"Pgb1gc1fd1ae1af1vg1hg2Wb3Kc6Le6Dd7Je7Ef7Qg7", "e7c7"}
//            , new String[]{"pgb1gc1fd1ae1af1vg1hg2Wb3Kc6Le6Jc7Dd7Ef7Qg7", "g2f2"}
//            , new String[]{"Pgb1gc1fd1ae1af1vg1pf2Wb3Kc6Le6Jc7Dd7Ef7Qg7", "c7e7"}
//            , new String[]{"pgb1gc1fd1ae1af1vg1pf2Wb3Kc6Le6Dd7Je7Ef7Qg7", "b1a1"}
//            , new String[]{"Pca1gc1fd1ae1af1vg1pf2Wb3Kc6Le6Dd7Je7Ef7Qg7", "b3b4"}
//            , new String[]{"pca1gc1fd1ae1af1vg1pf2Lb4Kc6Le6Dd7Je7Ef7Qg7", "f2e2"}
//            , new String[]{"Pca1gc1fd1ae1af1vg1te2Lb4Kc6Le6Dd7Je7Ef7Qg7", "e7c7"}
//            , new String[]{"pca1gc1fd1ae1af1vg1te2Lb4Kc6Le6Jc7Dd7Ef7Qg7", "e2f2"}
//            , new String[]{"Pca1gc1fd1ae1af1vg1pf2Lb4Kc6Le6Jc7Dd7Ef7Qg7", "c6d6f6"}
//            , new String[]{"pca1gc1fd1ae1af1vg1pf2Lb4Le6Df6Jc7Dd7Ef7Qg7", "f2g2"}
//            , new String[]{"Pca1gc1fd1ae1af1vg1hg2Lb4Le6Df6Jc7Dd7Ef7Qg7", "f6g6"}
//            , new String[]{"pca1gc1fd1ae1af1vg1hg2Lb4Le6Og6Jc7Dd7Ef7Qg7", "c1b1"}
//            , new String[]{"Pca1cb1fd1ae1af1vg1hg2Lb4Le6Og6Jc7Dd7Ef7Qg7", "e6f6"}
//            , new String[]{"pca1cb1fd1ae1af1vg1hg2Lb4Tf6Og6Jc7Dd7Ef7Qg7", "g2f2"}
//            , new String[]{"Pca1cb1fd1ae1af1vg1pf2Lb4Tf6Og6Jc7Dd7Ef7Qg7", "f7e7"}
//            , new String[]{"pca1cb1fd1ae1af1vg1pf2Lb4Tf6Og6Jc7Dd7Ie7Qg7", "d1c1"}
//            , new String[]{"Pca1cb1vc1ae1af1vg1pf2Lb4Tf6Og6Jc7Dd7Ie7Qg7", "g6e6"}
//            , new String[]{"pca1cb1vc1ae1af1vg1pf2Lb4Oe6Tf6Jc7Dd7Ie7Qg7", "f1d1"}
//            , new String[]{"Pca1cb1vc1ad1ae1vg1pf2Lb4Oe6Tf6Jc7Dd7Ie7Qg7", "b4c4"}
//            , new String[]{"pca1cb1vc1ad1ae1vg1pf2Tc4Oe6Tf6Jc7Dd7Ie7Qg7", "f2g2"}
//            , new String[]{"Pca1cb1vc1ad1ae1vg1hg2Tc4Oe6Tf6Jc7Dd7Ie7Qg7", "g7f7"}
//            , new String[]{"pca1cb1vc1ad1ae1vg1hg2Tc4Oe6Tf6Jc7Dd7Ie7Mf7", "g1f1"}
//            , new String[]{"Pca1cb1vc1ad1ae1rf1hg2Tc4Oe6Tf6Jc7Dd7Ie7Mf7", "d7b7"}
//            , new String[]{"pca1cb1vc1ad1ae1rf1hg2Tc4Oe6Tf6Db7Jc7Ie7Mf7", "g2g1"}
//    };
}
