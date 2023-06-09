package comp1140.ass2.controller;

import comp1140.ass2.gamelogic.ContraCublino;
import comp1140.ass2.gamelogic.Game;
import comp1140.ass2.gamelogic.PurCublino;
import comp1140.ass2.helperclasses.DeepCloneable;
import comp1140.ass2.helperclasses.RoseNode;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * A difficult AI controller i.e., an AI which should challenge the player
 *
 * @author Ziling Ouyang, Yuechen Liu
 */
public class DifficultAI implements DeepCloneable<DifficultAI> {
    private static final long RUN_TIME = 20000;
    private final Game game;
    private final RoseNode<Game> gameTree;

    /**
     * Constructor for DifficultAI
     */
    public DifficultAI(Game game) {
        this.game = game;
        this.gameTree = new RoseNode<>(game);
    }

    /**
     * Expands a node so that its children are the possible states reached after playing a move. If there are no possible moves, the child is the same game but
     * with the turn ended.
     *
     * @param treeNode The node to be expanded
     */
    public static void monteCarloExpansion(RoseNode<Game> treeNode) {
        Game nodeToExpand = treeNode.getState();
        List<Game> children = Arrays.stream((nodeToExpand).generateLegalMoves()).map(ContraCublino.GameMove::getPossibleState)
                .collect(toList());

        if (children.isEmpty()) {
            Game game = treeNode.getState().deepClone(); //deepClone just to be safe. If it's 100% not necessary, delete it
            game.endTurn();
            treeNode.addChild(new RoseNode<>(game));
            return;
        }
        children.forEach(gameState -> {
            RoseNode<Game> node = new RoseNode<>(gameState);
            treeNode.addChild(node);
        });
    }

    /**
     * Implements the deepClone method from DeepCloneable interface
     */
    public DifficultAI deepClone() {
        return new DifficultAI(this.game.deepClone());
    }

    /**
     * Multithreading the MCTS
     */
    public int monteCarloMain() {
        int numberOfThreads = 4;
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        List<Integer> indices = new ArrayList<>();
        List<RoseNode<Game>> trees = new ArrayList<>();
        List<Future<RoseNode<Game>>> futures = new ArrayList<>();
        for (int i = 0; i < numberOfThreads; i++) {
            Callable<RoseNode<Game>> callable = new RunMonteCarlo(this);
            Future<RoseNode<Game>> future = executor.submit(callable);
            futures.add(future);
        }
        for (Future<RoseNode<Game>> future : futures) {
            try {
                int maxChild = getMaxChildIndex(future.get());
                indices.add(maxChild);
                trees.add(future.get().getChildren().get(maxChild));
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
    public RoseNode<Game> monteCarloTreeSearch(RoseNode<Game> gameTree) {
        long start = System.currentTimeMillis();
        long end = start + RUN_TIME;
        Random rand = new Random();
        monteCarloExpansion(gameTree);
        while (System.currentTimeMillis() < end) {
            RoseNode<Game> selectedNode = findNodeContra(gameTree);
            if (selectedNode.getVisitCount() == 0) {
                if (selectedNode.getState() instanceof ContraCublino) {
                    backPropagate(selectedNode, simulateContra((ContraCublino) selectedNode.getState()));
                } else {
                    backPropagate(selectedNode, simulatePur((PurCublino) selectedNode.getState()));
                }
            } else {
                monteCarloExpansion(selectedNode);
                RoseNode<Game> firstChild = selectedNode.getChildren().get(rand.nextInt(selectedNode.getChildren().size()));
                if (selectedNode.getState() instanceof ContraCublino) {
                    backPropagate(selectedNode, simulateContra((ContraCublino) firstChild.getState()));
                } else {
                    backPropagate(selectedNode, simulatePur((PurCublino) firstChild.getState()));
                }
            }
        }
        return (gameTree);
    }

    /**
     * Finds the child node with the most wins / win-visit ratio depending on what is wanted
     *
     * @param treeNode The node from which to get the best child node
     * @return The best child node
     */
    private int getMaxChildIndex(RoseNode<Game> treeNode) {
        List<Integer> winScores = treeNode.getChildren().stream().map(RoseNode::getWinCount).collect(toList());
        return winScores.indexOf(winScores.stream().max(Integer::compareTo).orElseThrow());
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

    /**
     * Simulates a random or semi-random game that is played from the current state given
     *
     * @param pur The current game being played
     * @return The result of the simulation
     */
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
    public RoseNode<Game> findNodeContra(RoseNode<Game> tree) {
        RoseNode<Game> node = tree;
        while (!node.getChildren().isEmpty()) {
            node = findNodeContra(monteCarloSelectionContra(node));
        }
        return node;
    }

    /**
     * Propagates the play out back through the tree
     *
     * @param node   The node which has been played out
     * @param result The result of the play out
     */
    public void backPropagate(RoseNode<Game> node, Game.GameResult result) {
        node.incrementVisitCount();
        boolean isWhite = gameTree.getState().getCurrentPlayer().isWhite();
        switch (result) {
            case WHITE_WINS:
                if (isWhite) node.incrementWinCount();
                break;
            case BLACK_WINS:
                if (!isWhite) node.incrementWinCount();
                break;
        }
        if (node.getParent() == null) return;
        backPropagate(node.getParent(), result);
    }

    /**
     * The mathematical upper confidence bound formula for trees which is used to determine which child node is selected
     *
     * @param node The node which is being evaluated
     * @return The upper confidence bound
     */
    private double upperConfidenceBoundCalculation(RoseNode<Game> node) {
        int explorationParameter = 2; // could also be root 2
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
    public RoseNode<Game> monteCarloSelectionContra(RoseNode<Game> node) {
        List<Double> UCT = node.getChildren().stream().map(this::upperConfidenceBoundCalculation).collect(toList());
        Double largest = UCT.stream().max(Double::compareTo).orElseThrow(); //remove in production
        List<Integer> indices = new ArrayList<>();
        Random rand = new Random(0);

        for (int i = 0; i < UCT.size(); i++) {
            if (UCT.get(i).equals(largest)) indices.add(i);
        }
        return node.getChildren().get(indices.get(rand.nextInt(indices.size())));
    }

    /**
     * A class to implement multithreading
     *
     * @author Ziling Ouyang
     */
    public static class RunMonteCarlo implements Callable<RoseNode<Game>> {
        private final DifficultAI ai;

        /**
         * Constructor for RunMonteCarlo
         */
        RunMonteCarlo(DifficultAI ai) {
            this.ai = ai.deepClone();
        }

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public RoseNode<Game> call() throws Exception {
            return ai.monteCarloTreeSearch(ai.gameTree);
        }
    }
}
