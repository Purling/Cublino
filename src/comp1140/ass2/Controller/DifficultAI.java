package comp1140.ass2.Controller;

import comp1140.ass2.GameLogic.ContraCublino;
import comp1140.ass2.GameLogic.Game;
import comp1140.ass2.State.Boards;
import comp1140.ass2.helperclasses.RoseNode;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * A difficult AI controller
 */
public class DifficultAI implements Serializable {
    private static final long RUN_TIME = 30000;
    private RoseNode<ContraCublino> contraGameTree;
    private Game game;

    /**
     * Constructor for DifficultAI
     */
    public DifficultAI(Game game) {
        this.game = game;
        if (game instanceof ContraCublino) {
            RoseNode<ContraCublino> gameTree = new RoseNode<>((ContraCublino) game);
            this.contraGameTree = gameTree;
        }
    }

    public static void main(String[] args) {
        Boards board = new Boards("CWa1Wb1Wc1Wd1We1Wf1Lg2ic6va7vb7vd7ve7vf7vg7");
//        Boards board = new Boards("cWa1Wb1Lc2Wd1We1Wf1Lg2ic6va7vb7vd7ve7vf7vg7");
        ContraCublino contra = new ContraCublino(true, board); // remember to switch isWhite to false if black is the current player
        RoseNode<ContraCublino> tree = new RoseNode(contra);
//        DifficultAI difficultAI = new DifficultAI(tree);
//        difficultAI.monteCarloMain();
    }

    /**
     * Create a deep copy of the DifficultAI object
     *
     * @return a deep copy of the DifficultAI object
     */
    public DifficultAI deepClone() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (DifficultAI) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    /**
     * Multithreading the MCTS
     */
    public void monteCarloMain() {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<Integer> indices = new ArrayList<>();
        List<RoseNode<ContraCublino>> trees = new ArrayList<>();
        List<Future<RoseNode<ContraCublino>>> futures = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Callable<RoseNode<ContraCublino>> callable = new RunMonteCarlo(this);
            Future<RoseNode<ContraCublino>> future = executor.submit(callable);
            futures.add(future);
        }
        for(Future<RoseNode<ContraCublino>> future : futures){
            try {
                indices.add(getMaxChildIndex(future.get()));
                trees.add(future.get());
                executor.shutdown();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        HashSet<Integer> nodes = new HashSet<>(indices);
        if (nodes.size() == indices.size()) {
            List<Integer> wins = trees.stream().map(RoseNode::getWinCount).collect(toList());
            int bestChild = wins.indexOf(wins.stream().max(Integer::compareTo).orElse(0));
            game = trees.get(bestChild).getState();
        } else {
            List<Integer> frequencies = indices.stream().map((x) -> Collections.frequency(indices, x)).collect(Collectors.toList());
            int maxFrequency = frequencies.stream().max(Integer::compareTo).orElse(0);
            game = trees.get(indices.get(frequencies.indexOf(maxFrequency))).getState();
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
        monteCarloExpansion(gameTree);
        while (System.currentTimeMillis() < end) { // can potentially exit earlier
            RoseNode<ContraCublino> selectedNode = findNode(gameTree);
            if (selectedNode.getVisitCount() == 0) {
                backPropagate(selectedNode, simulate(selectedNode.getState()));
            } else {
                monteCarloExpansion(selectedNode);
                RoseNode<ContraCublino> firstChild = selectedNode.getChildren().get(rand.nextInt(selectedNode.getChildren().size()));
                backPropagate(firstChild, simulate(firstChild.getState()));
            }
        }
        System.out.println("Win count:" + gameTree.getWinCount() + "  Visit count:" + gameTree.getVisitCount());
        for (RoseNode<ContraCublino> state : gameTree.getChildren()) {
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
    private Boards getMaxChild(RoseNode<ContraCublino> treeNode) { // Can also make this based on percentages
        List<Integer> winScores = treeNode.getChildren().stream().map(RoseNode::getWinCount).collect(toList());
        int indexWanted = winScores.indexOf(winScores.stream().max(Integer::compareTo).orElseThrow());
        return treeNode.getChildren().get(indexWanted).getState().getBoard(); // Get rid of in production
    }

    /**
     * Finds the child node with the most wins / win-visit ratio depending on what is wanted
     *
     * @param treeNode The node from which to get the best child node
     * @return The best child node
     */
    private int getMaxChildIndex(RoseNode<ContraCublino> treeNode) { // Can also make this based on percentages
        List<Integer> winScores = treeNode.getChildren().stream().map(RoseNode::getWinCount).collect(toList());
        return winScores.indexOf(winScores.stream().max(Integer::compareTo).orElseThrow());
    }

    /**
     * Expands a node so that its children are the possible states reached after playing a move. If there are no possible moves, the child is the same game but
     * with the turn ended.
     *
     * @param treeNode The node to be expanded
     */
    private void monteCarloExpansion(RoseNode<ContraCublino> treeNode) { // Consider where this should be
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

    /**
     * Simulates a random or semi-random game that is played from the current state given
     *
     * @param contra The current game being played
     * @return The result of the simulation
     */
    public Game.GameResult simulate(ContraCublino contra) {
        // Really need to optimise simulate, it is one of the things preventing the tree search from being very fast
        // Random moves is probably a bit too slow,
        // greedy with lookahead of 1 is probably too stupid. Maybe try greedy with a lookahead of 2 or 3 in combination with random
        int counter = 1;
        while (contra.getWinner().equals(Game.GameResult.UNFINISHED)) {
            boolean everyThird = (counter % 3) == 0;
            // Can be any move making mechanism. e.g., minimax, greedy, etc
            // Currently a combination of random and greedy
            if (everyThird) contra = EasyAI.randomMove(contra);
            if (contra.getWinner().equals(Game.GameResult.UNFINISHED)) contra = EasyAI.greedyAI(contra);
            if (contra.getWinner().equals(Game.GameResult.UNFINISHED) && everyThird) contra = EasyAI.randomMove(contra);
            counter++;
        }
        return contra.getWinner();
    }

    /**
     * Finds the leaf node to "play out"
     *
     * @param tree The tree from which to find the leaf node
     * @return The leaf node
     */
    public RoseNode<ContraCublino> findNode(RoseNode<ContraCublino> tree) {
        RoseNode<ContraCublino> node = tree;
        while (!node.getChildren().isEmpty()) {
            node = findNode(monteCarloSelection(node));
        }
        return node;
    }

    /**
     * Propagates the play out back through the tree
     *
     * @param node   The node which has been played out
     * @param result The result of the play out
     */
    public void backPropagate(RoseNode<ContraCublino> node, Game.GameResult result) {
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
        backPropagate(node.getParent(), result);
    }

    /**
     * The mathematical upper confidence bound formula for trees which is used to determine which child node is selected
     *
     * @param node The node which is being evaluated
     * @return The upper confidence bound
     */
    private double upperConfidenceBoundCalculation(RoseNode<ContraCublino> node) {
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
    public RoseNode<ContraCublino> monteCarloSelection(RoseNode<ContraCublino> node) {
        List<Double> UCT = node.getChildren().stream().map(this::upperConfidenceBoundCalculation).collect(toList());
        Double largest = UCT.stream().max(Double::compareTo).orElseThrow(); //remove in production
        List<Integer> indices = new ArrayList<>();
        Random rand = new Random(0);

        for (int i = 0; i < UCT.size(); i++) {
            if (UCT.get(i).equals(largest)) indices.add(i);
        }
        return node.getChildren().get(indices.get(rand.nextInt(indices.size())));
    }

    public static class RunMonteCarlo implements Callable<RoseNode<ContraCublino>> {
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
        public RoseNode<ContraCublino> call() throws Exception {
            return ai.monteCarloTreeSearch(ai.contraGameTree);
        }
    }
}
