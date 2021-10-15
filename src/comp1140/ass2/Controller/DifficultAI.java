package comp1140.ass2.Controller;

import comp1140.ass2.GameLogic.ContraCublino;
import comp1140.ass2.GameLogic.Game;
import comp1140.ass2.State.Boards;
import comp1140.ass2.gui.guiPieces.GuiDie;
import comp1140.ass2.helperclasses.RoseNode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A difficult AI controller
 */
public class DifficultAI extends Controller {
    private static final long RUN_TIME = 100000;
    private RoseNode<ContraCublino> gameTree;

    /**
     * Constructor for DifficultAI
     */
    public DifficultAI(RoseNode<ContraCublino> gameTree) {
        this.gameTree = gameTree;
    }

    public DifficultAI(boolean isWhite) {
        super(false, "Hard AI " + (isWhite ? 1 : 2),
                isWhite ? GuiDie.Skin.PLAIN_WHITE : GuiDie.Skin.PLAIN_BLACK);
    }

    public static void main(String[] args) {
        Boards board = new Boards("CWa1Wb1Wc1Wd1We1Wf1Wg1va7vb7vc7vd7ve7vf7vg7");
        ContraCublino contra = new ContraCublino(true, board);
        RoseNode<ContraCublino> tree = new RoseNode(contra);
        DifficultAI difficultAI = new DifficultAI(tree);
        System.out.println(difficultAI.monteCarloTreeSearch());
//        difficultAI.monteCarloExpansion(difficultAI.gameTree);
//        contra.generateLegalMoves();
//        for (RoseNode<ContraCublino> node : difficultAI.gameTree.getChildren()) {
//            difficultAI.monteCarloExpansion(node);
//        }
//        System.out.println(difficultAI.gameTree);
//        System.out.println(difficultAI.simulate(contra));
    }

    /**
     * Implements the Monte Carlo Tree search and returns the next best move
     * @return The next best move as a board
     */
    public Boards monteCarloTreeSearch() { // Assumes that a new gameTree is generated each time
        long start = System.currentTimeMillis();
        long end = start + RUN_TIME;
        monteCarloExpansion(gameTree);
        while (System.currentTimeMillis() < end) { // can potentially exit earlier
            RoseNode<ContraCublino> selectedNode = findNode(gameTree);
            if (selectedNode.getVisitCount() == 0) {
                backPropagate(selectedNode, simulate(selectedNode.getState()));
            } else {
                monteCarloExpansion(selectedNode);
                RoseNode<ContraCublino> firstChild = selectedNode.getChildren().get(0);
                backPropagate(firstChild, simulate(firstChild.getState()));
            }
        }
        return getMaxChild(gameTree);
    }

    /**
     * Finds the child node with the most wins / win-visit ratio depending on what is wanted
     * @param treeNode The node from which to get the best child node
     * @return The best child node
     */
    private Boards getMaxChild(RoseNode<ContraCublino> treeNode) { // Can also make this based on percentages
        List<Integer> winScores = treeNode.getChildren().stream().map(RoseNode::getWinCount).collect(Collectors.toList());
        int indexWanted = winScores.indexOf(winScores.stream().max(Integer::compareTo).orElseThrow());
        return treeNode.getChildren().get(indexWanted).getState().getBoard(); // Get rid of in production
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
                .collect(Collectors.toList());
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
        // greedy with lookahead of 1 is probably too stupid. Maybe try greedy with a lookahead of 2 or 3.
        EasyAI easyAI = new EasyAI();
        while (!contra.isGameOver()) {
            contra = easyAI.greedyAI(contra); // Can be any move making mechanism. e.g., minimax, greedy, etc
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
        if (node.getParent() == null) return;
        boolean isWhite = gameTree.getState().getCurrentPlayer().isWhite();
        switch (result) {
            case WHITE_WINS:
                if (isWhite) node.incrementWinCount();
                break;
            case BLACK_WINS:
                if (!isWhite) node.incrementWinCount();
                break;
        }
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
        List<Double> UCT = node.getChildren().stream().map(this::upperConfidenceBoundCalculation).collect(Collectors.toList());
        int index = UCT.indexOf(UCT.stream().max(Double::compareTo).orElseThrow()); //remove in production
        return node.getChildren().get(index);
    }

    /**
     * Setter for gameTree
     */
    public void setGameTree(RoseNode<ContraCublino> gameTree) {
        this.gameTree = gameTree;
    }
}
