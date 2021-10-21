package comp1140.ass2.helperclasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of the rose tree abstract data structure
 *
 * @author Ziling Ouyang
 */
public class RoseNode<T> implements Serializable {

    /**
     * The visit count of that particular node (for MCTS)
     */
    int visitCount = 0;

    /**
     * The win count of that particular node (for MCTS)
     */
    int winCount = 0;

    /**
     * The information contained within the node
     */
    T state;

    /**
     * The parent of the current node. If null, node is the root
     */
    RoseNode<T> parent;

    /**
     * The depth of the current node
     */
    int depth;

    /**
     * The children of the roseNode
     */
    private final List<RoseNode<T>> children = new ArrayList<>();

    /**
     * Constructor for RoseTree
     */
    public RoseNode(T state) {
        this.state = state;
        this.depth = 0;
    }

    /**
     * Adds a child to the RoseTree node
     *
     * @param child The child to be added to the RoseTree
     */
    public void addChild(RoseNode<T> child) {
        child.parent = this;
        children.add(child);
        child.setDepth(depth + 1);
    }

    /**
     * Increases the visit count by 1
     */
    public void incrementVisitCount() {
        visitCount++;
    }

    /**
     * Increases the win count by 1
     */
    public void incrementWinCount() {
        winCount++;
    }

    /**
     * Setter for depth
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }

    /**
     * Getter for children
     */
    public List<RoseNode<T>> getChildren() {
        return children;
    }

    /**
     * Getter for state
     */
    public T getState() {
        return state;
    }

    /**
     * Getter for visitCount
     */
    public int getVisitCount() {
        return visitCount;
    }

    /**
     * Getter for parent
     */
    public RoseNode<T> getParent() {
        return parent;
    }

    /**
     * Getter for winCount
     */
    public int getWinCount() {
        return winCount;
    }

    /**
     * Get leaves of a tree
     */
    public List<RoseNode<T>> getLeaves(List<RoseNode<T>> leaves) {
        if (getChildren().isEmpty()) {
            leaves.add(this);
            return leaves;
        } else {
            getChildren().forEach((x) -> x.getLeaves(leaves));
        }
        return leaves;
    }

    /**
     * toString for RoseNode
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        StringBuilder wins = new StringBuilder("Win count:" + winCount);
        StringBuilder visits = new StringBuilder("Visit count:" + visitCount);
        if (depth == 0) {
            result.append(wins).append(" ").append(visits).append(state.toString());
        } else {
            result.insert(0, "\n").append(wins).append(" ").append(visits).append(state.toString()); // \n appended at the start because of replaceAll quirk
        }
        result = new StringBuilder(result.toString().replaceAll("\n", "\n" + "    ".repeat(depth)));

        for (RoseNode<T> child : children) {
            result.append(child);
        }
        return result.toString();
    }

}
