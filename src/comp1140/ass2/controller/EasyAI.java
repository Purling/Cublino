package comp1140.ass2.controller;

import comp1140.ass2.gamelogic.ContraCublino;
import comp1140.ass2.gamelogic.Game;
import comp1140.ass2.gamelogic.PurCublino;
import comp1140.ass2.helperclasses.RoseNode;
import comp1140.ass2.state.Die;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * AI which generates moves which shouldn't challenge the player too much
 *
 * @author Whole group
 */
public class EasyAI {

    /**
     * Empty constructor for EasyAI
     */
    public EasyAI() {
    }

    /**
     * A heuristic for contra which is simplistic. It looks at how far along the farthest die is
     *
     * @param currentGameState The current condition of the game
     * @return A numerical evaluation of the game state
     */
    public static double greedyEvaluation(ContraCublino currentGameState) {
        boolean isWhite = currentGameState.getCurrentPlayer().isWhite();
        List<Die> playerDice = currentGameState.getCurrentPlayer().getDice();
        List<Die> opponentDice = currentGameState.getOtherPlayer().getDice();
        Random randomness = new Random();

        if (isWhite) {
            if (playerDice.stream().anyMatch(Die::isWhiteDieFinished)) return Double.MAX_VALUE;
            if (opponentDice.stream().anyMatch(Die::isBlackDieFinished)) return -Double.MAX_VALUE;
            int weightedExploration = playerDice.stream().map((x) -> 7 - x.getY() * (x.getY())).reduce(0, Integer::sum) * 2;
            return playerDice.stream().mapToInt((x) -> 7 - x.getY()).max().orElse(-99) * (100 / 7) * weightedExploration * randomness.nextInt(20);
        } else {
            if (playerDice.stream().anyMatch(Die::isBlackDieFinished)) return Double.MAX_VALUE;
            if (opponentDice.stream().anyMatch(Die::isWhiteDieFinished)) return -Double.MAX_VALUE;
            double weightedExploration = playerDice.stream().map((x) -> x.getY() * (7 - x.getY())).reduce(0, Integer::sum) * 2;
            return playerDice.stream().mapToInt(Die::getY).max().orElse(-99) * (100 / 7) * weightedExploration * randomness.nextInt(20);
        }
    }

    /**
     * Evaluation heuristic for Contra
     *
     * @param currentGameState The game state which is to be evaluated
     * @return The heuristic evaluation
     */
    public static double fastEvaluation(ContraCublino currentGameState) {
        boolean isWhite = currentGameState.getCurrentPlayer().isWhite();
        List<Die> playerDice = currentGameState.getCurrentPlayer().getDice();

        if (!isWhite) {
            if (playerDice.stream().anyMatch(Die::isWhiteDieFinished)) return Double.MAX_VALUE;
            if (playerDice.stream().anyMatch(Die::isBlackDieFinished)) return -Double.MAX_VALUE;
            return playerDice.stream().mapToInt((x) -> 7 - x.getY()).max().orElse(-99) * (100 / 7);
        } else {
            if (playerDice.stream().anyMatch(Die::isBlackDieFinished)) return Double.MAX_VALUE;
            if (playerDice.stream().anyMatch(Die::isWhiteDieFinished)) return -Double.MAX_VALUE;
            return playerDice.stream().mapToInt(Die::getY).max().orElse(-99) * (100 / 7);
        }
    }

    /**
     * Generate a greedy legal move
     *
     * @param currentGameState The game state which a random move is to be generated from
     * @return The game state after the legal move is played
     */
    public static ContraCublino.ContraMove greedyAIMoveOnly(ContraCublino currentGameState) {
        RoseNode<Game> gameTree = new RoseNode<>(currentGameState);
        DifficultAI.monteCarloExpansion(gameTree);
        gameTree.getChildren().forEach(DifficultAI::monteCarloExpansion);
        List<ContraCublino.ContraMove> legalMoves = List.of(currentGameState.generateLegalMoves());
        List<RoseNode<Game>> leaves = gameTree.getLeaves(new ArrayList<>());
        List<Double> evaluatedMoves = leaves.stream().map((x) -> greedyEvaluation((ContraCublino) x.getState())).collect(Collectors.toList());
        int generatedMoveIndex = evaluatedMoves.indexOf(evaluatedMoves.stream().max(Double::compareTo).orElse(0D));
        RoseNode<Game> wanted = leaves.get(generatedMoveIndex);
        while (wanted.getParent().getParent() != null) {
            wanted = wanted.getParent();
        }
        int generatedIndex = legalMoves.stream().map((x) -> x.getPossibleState().getBoard()).collect(Collectors.toList()).indexOf(wanted.getState().getBoard());
        assert generatedIndex != -1;
        return legalMoves.get(generatedIndex);
    }

    /**
     * "AI" which just generates a random move
     *
     * @param currentGameState The current game state
     * @return The game after the move has been applied
     */
    public static ContraCublino randomMove(ContraCublino currentGameState) {
        Random rand = new Random();
        int randomMove = rand.nextInt(currentGameState.generateLegalMoves().length);
        return currentGameState.generateLegalMoves()[randomMove].getPossibleState();
    }

    /**
     * Generate a random legal move
     *
     * @param currentGameState The game state which a random move is to be generated from
     * @return The game state after the legal move is played
     */
    public static PurCublino randomMove(PurCublino currentGameState) {
        Random rand = new Random();
        int randomMove = rand.nextInt(currentGameState.generateLegalMoves().length);
        PurCublino returnState = currentGameState.generateLegalMoves()[randomMove].getPossibleState();
        returnState.endTurn();
        return returnState;
    }

    /**
     * Evaluation heuristic for Pur
     *
     * @param currentGameState The game state which is to be evaluated
     * @return The heuristic evaluation
     */
    public static Double greedyEvaluationPur(PurCublino currentGameState) {
        boolean isWhite = currentGameState.getCurrentPlayer().isWhite();
        List<Die> playerDice = currentGameState.getCurrentPlayer().getDice();
        List<Die> opponentDice = currentGameState.getOtherPlayer().getDice();

        if (isWhite) {
            if (playerDice.stream().allMatch(Die::isWhiteDieFinished)) return Double.MAX_VALUE;
            if (opponentDice.stream().allMatch(Die::isBlackDieFinished)) return -Double.MAX_VALUE;
            Double positionMark = (double) (2 * playerDice.stream().mapToInt(Die::evaluateApproachingDie).sum());
            return playerDice.stream().mapToInt(Die::getY).sum() + positionMark;
        } else {
            if (playerDice.stream().allMatch(Die::isBlackDieFinished)) return Double.MAX_VALUE;
            if (opponentDice.stream().allMatch(Die::isWhiteDieFinished)) return -Double.MAX_VALUE;
            Double positionMark = (double) (2 * playerDice.stream().mapToInt(Die::evaluateApproachingDie).sum());
            return playerDice.stream().mapToInt((x) -> 7 - x.getY()).sum() + positionMark;
        }
    }

    /**
     * A greedy AI for Pur
     *
     * @param currentGameState The game state that moves are generated from
     * @return The move that was chosen by the AI
     */
    public static PurCublino greedyAIPur(PurCublino currentGameState) {
        PurCublino.PurMove[] legalMoves = currentGameState.generateLegalMoves();
        List<Double> evaluatedMoves = Arrays.stream(legalMoves).map((x) -> greedyEvaluationPur(x.getPossibleState())).collect(Collectors.toList());
        int generatedMoveIndex = evaluatedMoves.indexOf(evaluatedMoves.stream().max(Double::compareTo).orElse(0D));
        return legalMoves[generatedMoveIndex].getPossibleState();
    }

    /**
     * Returns the greedyAI generated move for pur but in a format easily readable by the GUI
     *
     * @param currentState The current state the game is in
     * @return The potential move to be made
     */
    public static PurCublino.PurMove purGreedyMove(PurCublino currentState) {
        RoseNode<Game> gameTree = new RoseNode<>(currentState);
        DifficultAI.monteCarloExpansion(gameTree);
        gameTree.getChildren().forEach(DifficultAI::monteCarloExpansion);
        List<PurCublino.PurMove> legalMoves = List.of(currentState.generateLegalMoves());
        List<RoseNode<Game>> leaves = gameTree.getLeaves(new ArrayList<>());
        List<Double> evaluatedMoves = leaves.stream().map((x) -> greedyEvaluationPur((PurCublino) x.getState())).collect(Collectors.toList());
        int generatedMoveIndex = evaluatedMoves.indexOf(evaluatedMoves.stream().max(Double::compareTo).orElse(0D));
        RoseNode<Game> wanted = leaves.get(generatedMoveIndex);
        while (wanted.getParent().getParent() != null) {
            wanted = wanted.getParent();
        }
        int generatedIndex = legalMoves.stream().map((x) -> x.getPossibleState().getBoard()).collect(Collectors.toList()).indexOf(wanted.getState().getBoard());
        return legalMoves.get(generatedIndex);
    }

    /**
     * Greedy AI for contra
     *
     * @param currentGameState The current condition of the game
     * @return The board after the move determined by the AI is played
     */
    public ContraCublino greedyAI(ContraCublino currentGameState) {
        ContraCublino.ContraMove[] legalMoves = currentGameState.generateLegalMoves();
        List<Double> evaluatedMoves = Arrays.stream(legalMoves).map((x) -> fastEvaluation(x.getPossibleState())).collect(Collectors.toList());
        int generatedMoveIndex = evaluatedMoves.indexOf(evaluatedMoves.stream().max(Double::compareTo).orElse(0D));
        return legalMoves[generatedMoveIndex].getPossibleState();
    }
}
