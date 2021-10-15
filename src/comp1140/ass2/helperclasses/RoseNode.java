package comp1140.ass2.helperclasses;

import java.util.ArrayList;
import java.util.List;

public class RoseNode<T> {

    int visitCount = 0;
    int winCount = 0;
    T state;
    RoseNode<T> parent;
    private List<RoseNode<T>> children = new ArrayList<>();
    int depth;

    /**
     * Constructor for RoseTree
     */
    public RoseNode(T state) {
        this.state = state;
        this.depth = 0;
    }

    /**
     * Adds a child to the RoseTree node
     * @param child The child to be added to the RoseTree
     * @return The child added
     */
    public RoseNode<T> addChild(RoseNode<T> child) {
        child.parent = this;
        children.add(child);
        child.setDepth(depth + 1);
        return child;
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
     * Setter for parent
     */
    public void setParent(RoseNode<T> parent) {
        this.parent = parent;
    }

    /**
     * Setter for children
     */
    public void addChildren(List<RoseNode<T>> children) {
        this.children = children;
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
     * toString for RoseNode
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (depth == 0) {
            result.append(state.toString());
        } else {
            result.insert(0, "\n").append(state.toString()); // \n appended at the start because of replaceAll quirk
        }
        result = new StringBuilder(result.toString().replaceAll("\n", "\n" + "    ".repeat(depth)));
        if(children == null) return state.toString();

        for(RoseNode<T> child : children) {
            result.append(child);
        }
        return result.toString();
    }

}
