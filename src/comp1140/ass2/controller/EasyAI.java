package comp1140.ass2.controller;

import comp1140.ass2.gamelogic.ContraCublino;
import comp1140.ass2.gamelogic.PurCublino;
import comp1140.ass2.state.Die;
import comp1140.ass2.helperclasses.RoseNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Ziling Ouyang, Zane Gates
 */
public class EasyAI { // Maybe split into two i.e., PurEasyAI and ContraEasyAI

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
            int weightedExploration = playerDice.stream().map((x) -> 7 - x.getY() * (x.getY())).reduce(0, Integer::sum) * 10;
            return playerDice.stream().mapToInt((x) -> 7 - x.getY()).max().orElse(-99) * (100 / 7) * weightedExploration * randomness.nextInt(20);
        } else {
            if (playerDice.stream().anyMatch(Die::isBlackDieFinished)) return Double.MAX_VALUE;
            if (opponentDice.stream().anyMatch(Die::isWhiteDieFinished)) return -Double.MAX_VALUE;
            double getOut = playerDice.stream().map(Die::getY).filter(y -> y != 0).reduce(0, Integer::sum);
            double weightedExploration = playerDice.stream().map((x) -> x.getY() * (7 - x.getY())).reduce(0, Integer::sum) * 10;
            return playerDice.stream().mapToInt(Die::getY).max().orElse(-99) * (100 / 7) * weightedExploration * randomness.nextInt(20);
        }
    }

    public static double badEvaluation(ContraCublino currentGameState) {
        boolean isWhite = currentGameState.getCurrentPlayer().isWhite();
        List<Die> playerDice = currentGameState.getCurrentPlayer().getDice();

        if (isWhite) {
            if (playerDice.stream().anyMatch(Die::isWhiteDieFinished)) return 100;
            if (playerDice.stream().anyMatch(Die::isBlackDieFinished)) return -100;
            return playerDice.stream().mapToInt(Die::getY).max().orElse(-99) * (100 / 7);
        } else {
            if (playerDice.stream().anyMatch(Die::isBlackDieFinished)) return 100;
            if (playerDice.stream().anyMatch(Die::isWhiteDieFinished)) return -100;
            return playerDice.stream().mapToInt((x) -> 7 - x.getY()).max().orElse(-99) * (100 / 7);
        }
    }

    /**
     * Greedy AI for contra
     *
     * @param currentGameState The current condition of the game
     * @return The board after the move determined by the AI is played
     */
    public ContraCublino greedyAI(ContraCublino currentGameState) {
//        RoseNode<ContraCublino> gameTree = new RoseNode<>(currentGameState);
//        DifficultAI.monteCarloExpansion(gameTree);
//        gameTree.getChildren().forEach(DifficultAI::monteCarloExpansion);
//        List<RoseNode<ContraCublino>> leaves = gameTree.getLeaves(new ArrayList<>());
//        List<Double> evaluatedMoves = leaves.stream().map((x) -> greedyEvaluation(x.getState())).collect(Collectors.toList());
//        int generatedMoveIndex = evaluatedMoves.indexOf(evaluatedMoves.stream().max(Double::compareTo).orElse(0D));
//        RoseNode<ContraCublino> wanted = leaves.get(generatedMoveIndex);
//        while (wanted.getParent().getParent() != null) {
//            wanted = wanted.getParent();
//        }
//        return wanted.getState();

        ContraCublino.ContraMove[] legalMoves = currentGameState.generateLegalMoves();
        List<Double> evaluatedMoves = Arrays.stream(legalMoves).map((x) -> badEvaluation(x.getPossibleState())).collect(Collectors.toList());
        int generatedMoveIndex = evaluatedMoves.indexOf(evaluatedMoves.stream().max(Double::compareTo).orElse(0D));
        assert generatedMoveIndex != -1;
        return legalMoves[generatedMoveIndex].getPossibleState();
    }

    public static ContraCublino.ContraMove greedyAIMoveOnly(ContraCublino currentGameState) {
        RoseNode<ContraCublino> gameTree = new RoseNode<>(currentGameState);
        DifficultAI.monteCarloExpansionContra(gameTree);
        gameTree.getChildren().forEach(DifficultAI::monteCarloExpansionContra);
        List<ContraCublino.ContraMove> legalMoves = List.of(currentGameState.generateLegalMoves());
        List<RoseNode<ContraCublino>> leaves = gameTree.getLeaves(new ArrayList<>());
        List<Double> evaluatedMoves = leaves.stream().map((x) -> greedyEvaluation(x.getState())).collect(Collectors.toList());
        System.out.println(evaluatedMoves);
        int generatedMoveIndex = evaluatedMoves.indexOf(evaluatedMoves.stream().max(Double::compareTo).orElse(0D));
        RoseNode<ContraCublino> wanted = leaves.get(generatedMoveIndex);
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

    public static ContraCublino.ContraMove moveOnly(ContraCublino currentGameState) {
        Random rand = new Random();
        int randomMove = rand.nextInt(currentGameState.generateLegalMoves().length);
        return currentGameState.generateLegalMoves()[randomMove];
    }

    public static PurCublino randomMove(PurCublino currentGameState) {
        Random rand = new Random();
        int randomMove = rand.nextInt(currentGameState.generatePurMoves().length);
        return currentGameState.generatePurMoves()[randomMove].getPossibleState();
    }

    public static Double greedyEvaluationPur(PurCublino currentGameState){
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

    public static PurCublino greedyAIPur(PurCublino currentGameState){
        PurCublino.PurMove[] legalMoves = currentGameState.generatePurMoves();
        List<Double> evaluatedMoves = Arrays.stream(legalMoves).map((x) -> greedyEvaluationPur(x.getPossibleState())).collect(Collectors.toList());
        int generatedMoveIndex = evaluatedMoves.indexOf(evaluatedMoves.stream().max(Double::compareTo).orElse(0D));
        assert generatedMoveIndex != -1;
        return legalMoves[generatedMoveIndex].getPossibleState();
    }



    public static PurCublino.PurMove purGreedyMove(PurCublino currentState) {
        RoseNode<PurCublino> gameTree = new RoseNode<>(currentState);
        DifficultAI.monteCarloExpansionPur(gameTree);
        gameTree.getChildren().forEach(DifficultAI::monteCarloExpansionPur);
        List<PurCublino.PurMove> legalMoves = List.of(currentState.generatePurMoves());
        List<RoseNode<PurCublino>> leaves = gameTree.getLeaves(new ArrayList<>());
        List<Double> evaluatedMoves = leaves.stream().map((x) -> greedyEvaluationPur(x.getState())).collect(Collectors.toList());
        int generatedMoveIndex = evaluatedMoves.indexOf(evaluatedMoves.stream().max(Double::compareTo).orElse(0D));
        RoseNode<PurCublino> wanted = leaves.get(generatedMoveIndex);
        while (wanted.getParent().getParent() != null) {
            wanted = wanted.getParent();
        }
        int generatedIndex = legalMoves.stream().map((x) -> x.getPossibleState().getBoard()).collect(Collectors.toList()).indexOf(wanted.getState().getBoard());
        assert generatedIndex != -1;
        return legalMoves.get(generatedIndex);
    }

    /**
     * If the player is AI, then automatically choose the move;
     */
    public void requestMove() {
    }
}