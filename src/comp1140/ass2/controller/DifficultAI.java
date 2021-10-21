package comp1140.ass2.controller;

import comp1140.ass2.gamelogic.ContraCublino;
import comp1140.ass2.gamelogic.Game;
import comp1140.ass2.gamelogic.PurCublino;
import comp1140.ass2.state.Boards;
import comp1140.ass2.helperclasses.RoseNode;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * A difficult AI controller
 */
public class DifficultAI {
    private static final long RUN_TIME = 20000;
    private RoseNode<ContraCublino> contraGameTree;
    private RoseNode<PurCublino> purGameTree;
    private Game game;

    /**
     * Constructor for DifficultAI
     */
    public DifficultAI(Game game) {
        this.game = game;
        if (game instanceof ContraCublino) {
            this.contraGameTree = new RoseNode<>((ContraCublino) game);
        }
        else if(game instanceof PurCublino) {
            this.purGameTree = new RoseNode<>((PurCublino) game);
        }
    }

    // For debugging purposes only
    public static void main(String[] args) {
//        Boards ContraBoard = new Boards("CWa1Wb1Wc1Wd1We1Wf1Lg2ic6va7vb7vd7ve7vf7vg7");
//        Boards PurBoard = new Boards("PWa1Wb1Lc2Wd1We1Wf1Lg2ic6va7vb7vd7ve7vf7vg7");
//        ContraCublino contra = new ContraCublino(true, ContraBoard); // remember to switch isWhite to false if black is the current player
//        PurCublino pur = new PurCublino(true, PurBoard);
//        DifficultAI difficultAIContra = new DifficultAI(contra);
//        DifficultAI difficultAIPur = new DifficultAI(pur);
//        difficultAIContra.monteCarloMainContra();
//        difficultAIPur.monteCarloMainPur();

//        Boards ContraBoard = new Boards("CWa1Wb1Wc1Wd1We1Wf1Lg2ic6va7vb7vd7ve7vf7vg7");
        Boards PurBoard = new Boards("pWa1Wb1Wc1Wd1We1Wf1Wg1va7vb7vc7vd7ve7vf7vg7");
//        ContraCublino contra = new ContraCublino(true, ContraBoard); // remember to switch isWhite to false if black is the current player
        PurCublino pur = new PurCublino(true, PurBoard);
//        DifficultAI difficultAIContra = new DifficultAI(contra);
        DifficultAI difficultAIPur = new DifficultAI(pur);
        EasyAI.greedyAIPur(pur);
        System.out.println(EasyAI.greedyAIPur(pur).getBoard().getStringRepresentation());
//        difficultAIContra.monteCarloMainContra();
//        difficultAIPur.monteCarloMainPur();
    }

    /**
     * Create a deep copy of the DifficultAI object
     *
     * @return a deep copy of the DifficultAI object
     */
    public DifficultAI deepClone() {
        return new DifficultAI(this.game.deepClone());
    }

    /**
     * Multithreading the MCTS
     */
    public int monteCarloMainContra() {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<Integer> indices = new ArrayList<>();
        List<RoseNode<ContraCublino>> trees = new ArrayList<>();
        List<Future<RoseNode<ContraCublino>>> futures = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Callable<RoseNode<ContraCublino>> callable = new RunMonteCarloContra(this);
            Future<RoseNode<ContraCublino>> future = executor.submit(callable);
            futures.add(future);
        }
        for(Future<RoseNode<ContraCublino>> future : futures){
            try {

                indices.add(getMaxChildIndexContra(future.get()));
                trees.add(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
        HashSet<Integer> nodes = new HashSet<>(indices);
        if (nodes.size() == indices.size()) {
            List<Integer> wins = trees.stream().map(RoseNode::getWinCount).collect(toList());
            return wins.indexOf(wins.stream().max(Integer::compareTo).orElse(0));
        } else {
            List<Integer> frequencies = indices.stream().map((x) -> Collections.frequency(indices, x)).collect(Collectors.toList());
            if (frequencies.stream().allMatch((x) -> x == 2)) { // TODO fix this so it actually sums up the relevant (i.e., most voted) choices instead of all
                List<Integer> wins = trees.stream().map(RoseNode::getWinCount).collect(toList());
                return wins.indexOf(wins.stream().max(Integer::compareTo).orElse(0));
            }
            int maxFrequency = frequencies.stream().max(Integer::compareTo).orElse(0);
            return indices.get(frequencies.indexOf(maxFrequency));
        }
    }

    public int monteCarloMainPur() {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<Integer> indices = new ArrayList<>();
        List<RoseNode<PurCublino>> trees = new ArrayList<>();
        List<Future<RoseNode<PurCublino>>> futures = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Callable<RoseNode<PurCublino>> callable = new RunMonteCarloPur(this);
            Future<RoseNode<PurCublino>> future = executor.submit(callable);
            futures.add(future);
        }
        for(Future<RoseNode<PurCublino>> future : futures){
            try {

                indices.add(getMaxChildIndexPur(future.get()));
                trees.add(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
        HashSet<Integer> nodes = new HashSet<>(indices);
        if (nodes.size() == indices.size()) {
            List<Integer> wins = trees.stream().map(RoseNode::getWinCount).collect(toList());
            return wins.indexOf(wins.stream().max(Integer::compareTo).orElse(0));
        } else {
            List<Integer> frequencies = indices.stream().map((x) -> Collections.frequency(indices, x)).collect(Collectors.toList());
            if (frequencies.stream().allMatch((x) -> x == 2)) {
                List<Integer> wins = trees.stream().map(RoseNode::getWinCount).collect(toList());
                return wins.indexOf(wins.stream().max(Integer::compareTo).orElse(0));
            }
            int maxFrequency = frequencies.stream().max(Integer::compareTo).orElse(0);
            return indices.get(frequencies.indexOf(maxFrequency));
        }
    }

    /**
     * Implements the Monte Carlo Tree search and returns the next best move
     *
     * @return The next best move as a board
     */
    public RoseNode<ContraCublino> monteCarloTreeSearch(RoseNode<ContraCublino> gameTree) { // Assumes that a new gameTree is generated each time
        long start = System.currentTimeMillis();
        long end = start + RUN_TIME;
        Random rand = new Random();
        monteCarloExpansionContra(gameTree);
        while (System.currentTimeMillis() < end) { // can potentially exit earlier
            RoseNode<ContraCublino> selectedNode = findNodeContra(gameTree);
            if (selectedNode.getVisitCount() == 0) {
                backPropagateContra(selectedNode, simulateContra(selectedNode.getState()));
            } else {
                monteCarloExpansionContra(selectedNode);
                RoseNode<ContraCublino> firstChild = selectedNode.getChildren().get(rand.nextInt(selectedNode.getChildren().size()));
                backPropagateContra(firstChild, simulateContra(firstChild.getState()));
            }
        }
        System.out.println("Win count:" + gameTree.getWinCount() + "  Visit count:" + gameTree.getVisitCount());
        for (RoseNode<ContraCublino> state : gameTree.getChildren()) {
            System.out.println("Win count:" + state.getWinCount() + "  Visit count:" + state.getVisitCount());
        }
        return (gameTree);
    }

    public RoseNode<PurCublino> monteCarloTreeSearchPur(RoseNode<PurCublino> gameTree) { // Assumes that a new gameTree is generated each time
        long start = System.currentTimeMillis();
        long end = start + RUN_TIME;
        Random rand = new Random();
        monteCarloExpansionPur(gameTree);
        while (System.currentTimeMillis() < end) { // can potentially exit earlier
            RoseNode<PurCublino> selectedNode = findNodePur(gameTree);
            if (selectedNode.getVisitCount() == 0) {
                backPropagatePur(selectedNode, simulatePur(selectedNode.getState()));
            } else {
                monteCarloExpansionPur(selectedNode);
                RoseNode<PurCublino> firstChild = selectedNode.getChildren().get(rand.nextInt(selectedNode.getChildren().size()));
                backPropagatePur(firstChild, simulatePur(firstChild.getState()));
            }
        }
        System.out.println("Win count:" + gameTree.getWinCount() + "  Visit count:" + gameTree.getVisitCount());
        for (RoseNode<PurCublino> state : gameTree.getChildren()) {
            System.out.println("Win count:" + state.getWinCount() + "  Visit count:" + state.getVisitCount());
        }
        return (gameTree);
    }

    /**
     * Finds the child node with the most wins / win-visit ratio depending on what is wanted
     *
     * @param treeNode The node from which to get the best child node
     * @return The best child node
     */
    private int getMaxChildIndexContra(RoseNode<ContraCublino> treeNode) { // Can also make this based on percentages think about the case of more than 1 "best"
        List<Integer> winScores = treeNode.getChildren().stream().map(RoseNode::getWinCount).collect(toList());
        return winScores.indexOf(winScores.stream().max(Integer::compareTo).orElseThrow());
    }

    private int getMaxChildIndexPur(RoseNode<PurCublino> treeNode) {
        List<Integer> winScores = treeNode.getChildren().stream().map(RoseNode::getWinCount).collect(toList());
        return winScores.indexOf(winScores.stream().max(Integer::compareTo).orElseThrow());
    }

    /**
     * Expands a node so that its children are the possible states reached after playing a move. If there are no possible moves, the child is the same game but
     * with the turn ended.
     *
     * @param treeNode The node to be expanded
     */
    public static void monteCarloExpansionContra(RoseNode<ContraCublino> treeNode) { // Consider where this should be
        ContraCublino nodeToExpand = treeNode.getState();
        List<ContraCublino> children = Arrays.stream(nodeToExpand.generateLegalMoves()).map(ContraCublino.ContraMove::getPossibleState)
                .collect(toList());
        if (children.isEmpty()) {
            ContraCublino game = treeNode.getState().deepClone(); //deepClone just to be safe. If it's 100% not necessary, delete it
            game.endTurn();
            treeNode.addChild(new RoseNode<>(game));
            return;
        }
        children.forEach(gameState -> {
            RoseNode<ContraCublino> node = new RoseNode<>(gameState);
            treeNode.addChild(node);
        });
    }

    public static void monteCarloExpansionPur(RoseNode<PurCublino> treeNode) { // Consider where this should be
        PurCublino nodeToExpand = treeNode.getState();
        List<PurCublino> children = Arrays.stream(nodeToExpand.generatePurMoves()).map(PurCublino.PurMove::getPossibleState)
                .collect(toList());
        if (children.isEmpty()) {
            PurCublino game = treeNode.getState().deepClone(); //deepClone just to be safe. If it's 100% not necessary, delete it
            game.endTurn();
            treeNode.addChild(new RoseNode<>(game));
            return;
        }
        children.forEach(gameState -> {
            RoseNode<PurCublino> node = new RoseNode<>(gameState);
            treeNode.addChild(node);
        });
    }

    /**
     * Simulates a random or semi-random game that is played from the current state given
     *
     * @param contra The current game being played
     * @return The result of the simulation
     */
    public Game.GameResult simulateContra(ContraCublino contra) {
        // Really need to optimise simulate, it is one of the things preventing the tree search from being very fast
        // Random moves is probably a bit too slow,
        // greedy with lookahead of 1 is probably too stupid. Maybe try greedy with a lookahead of 2 or 3 in combination with random
        int counter = 1;
        EasyAI easy = new EasyAI();
        while (contra.getWinner().equals(Game.GameResult.UNFINISHED)) {
            boolean everyThird = (counter % 3) == 0;
            // Can be any move making mechanism. e.g., minimax, greedy, etc
            // Currently a combination of random and greedy
            if (everyThird) contra = EasyAI.randomMove(contra);
            if (contra.getWinner().equals(Game.GameResult.UNFINISHED)) contra = easy.greedyAI(contra);
            if (contra.getWinner().equals(Game.GameResult.UNFINISHED) && everyThird) contra = EasyAI.randomMove(contra);
            counter++;
        }
        return contra.getWinner();
    }

    public Game.GameResult simulatePur(PurCublino pur) {
        // Really need to optimise simulate, it is one of the things preventing the tree search from being very fast
        // Random moves is probably a bit too slow,
        // greedy with lookahead of 1 is probably too stupid. Maybe try greedy with a lookahead of 2 or 3 in combination with random
        int counter = 1;
        while (pur.getWinner().equals(Game.GameResult.UNFINISHED)) {
            boolean everyThird = (counter % 3) == 0;
            // Can be any move making mechanism. e.g., minimax, greedy, etc
            // Currently a combination of random and greedy
            if (everyThird) pur = EasyAI.randomMove(pur);
            if (pur.getWinner().equals(Game.GameResult.UNFINISHED)) pur = EasyAI.greedyAIPur(pur);
            if (pur.getWinner().equals(Game.GameResult.UNFINISHED) && everyThird) pur = EasyAI.randomMove(pur);
            counter++;
        }
        return pur.getWinner();
    }
    /**
     * Finds the leaf node to "play out"
     *
     * @param tree The tree from which to find the leaf node
     * @return The leaf node
     */
    public RoseNode<ContraCublino> findNodeContra(RoseNode<ContraCublino> tree) {
        RoseNode<ContraCublino> node = tree;
        while (!node.getChildren().isEmpty()) {
            node = findNodeContra(monteCarloSelectionContra(node));
        }
        return node;
    }

    public RoseNode<PurCublino> findNodePur(RoseNode<PurCublino> tree) {
        RoseNode<PurCublino> node = tree;
        while (!node.getChildren().isEmpty()) {
            node = findNodePur(monteCarloSelectionPur(node));
        }
        return node;
    }

    /**
     * Propagates the play out back through the tree
     *
     * @param node   The node which has been played out
     * @param result The result of the play out
     */
    public void backPropagateContra(RoseNode<ContraCublino> node, Game.GameResult result) {
        node.incrementVisitCount();
        boolean isWhite = contraGameTree.getState().getCurrentPlayer().isWhite();
        switch (result) {
            case WHITE_WINS:
                if (isWhite) node.incrementWinCount();
                break;
            case BLACK_WINS:
                if (!isWhite) node.incrementWinCount();
                break;
        }
        if (node.getParent() == null) return;
        backPropagateContra(node.getParent(), result);
    }

    public void backPropagatePur(RoseNode<PurCublino> node, Game.GameResult result) {
        node.incrementVisitCount();
        boolean isWhite = purGameTree.getState().getCurrentPlayer().isWhite();
        switch (result) {
            case WHITE_WINS:
                if (isWhite) node.incrementWinCount();
                break;
            case BLACK_WINS:
                if (!isWhite) node.incrementWinCount();
                break;
        }
        if (node.getParent() == null) return;
        backPropagatePur(node.getParent(), result);
    }

    /**
     * The mathematical upper confidence bound formula for trees which is used to determine which child node is selected
     *
     * @param node The node which is being evaluated
     * @return The upper confidence bound
     */
    private double upperConfidenceBoundCalculationContra(RoseNode<ContraCublino> node) {
        int explorationParameter = 2; // could also be root 2
        double nodeVisits = node.getVisitCount();
        if (nodeVisits == 0.00D) return Double.MAX_VALUE;
        return (node.getWinCount() / nodeVisits) + explorationParameter * Math.sqrt(Math.log(node.getParent().getVisitCount()) / nodeVisits);
    }

    private double upperConfidenceBoundCalculationPur(RoseNode<PurCublino> node) {
        int explorationParameter = 2;
        double nodeVisits = node.getVisitCount();
        if (nodeVisits == 0.00D) return Double.MAX_VALUE;
        return (node.getWinCount() / nodeVisits) + explorationParameter * Math.sqrt(Math.log(node.getParent().getVisitCount()) / nodeVisits);
    }
    /**
     * Selects a node from a set of nodes to be expanded
     *
     * @param node The node whose children are to be selected from
     * @return The chosen node
     */
    public RoseNode<ContraCublino> monteCarloSelectionContra(RoseNode<ContraCublino> node) {
        List<Double> UCT = node.getChildren().stream().map(this::upperConfidenceBoundCalculationContra).collect(toList());
        Double largest = UCT.stream().max(Double::compareTo).orElseThrow(); //remove in production
        List<Integer> indices = new ArrayList<>();
        Random rand = new Random(0);

        for (int i = 0; i < UCT.size(); i++) {
            if (UCT.get(i).equals(largest)) indices.add(i);
        }
        return node.getChildren().get(indices.get(rand.nextInt(indices.size())));
    }

    public RoseNode<PurCublino> monteCarloSelectionPur(RoseNode<PurCublino> node) {
        List<Double> UCT = node.getChildren().stream().map(this::upperConfidenceBoundCalculationPur).collect(toList());
        Double largest = UCT.stream().max(Double::compareTo).orElseThrow();
        List<Integer> indices = new ArrayList<>();
        Random rand = new Random(0);

        for (int i = 0; i < UCT.size(); i++) {
            if (UCT.get(i).equals(largest)) indices.add(i);
        }
        return node.getChildren().get(indices.get(rand.nextInt(indices.size())));
    }


    public static class RunMonteCarloContra implements Callable<RoseNode<ContraCublino>> {
        private final DifficultAI ai;

        /**
         * Constructor for RunMonteCarlo
         */
        RunMonteCarloContra(DifficultAI ai) {
            this.ai = ai.deepClone();
        }

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public RoseNode<ContraCublino> call() throws Exception {
            return ai.monteCarloTreeSearch(ai.contraGameTree);
        }
    }

    public static class RunMonteCarloPur implements Callable<RoseNode<PurCublino>> {
        private final DifficultAI purAI;

        /**
         * Constructor for RunMonteCarlo
         */
        RunMonteCarloPur(DifficultAI ai) {
            this.purAI = ai.deepClone();
        }

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public RoseNode<PurCublino> call() throws Exception {
            return purAI.monteCarloTreeSearchPur(purAI.purGameTree);
        }
    }

}
