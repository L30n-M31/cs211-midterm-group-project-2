package node;

public class TreeNode implements Comparable<TreeNode> {

    private int count;
    private char symbol;
    private TreeNode left;
    private TreeNode right;

    /**
     * Constructs a TreeNode with the given symbol, count, left child, and right child.
     * @param symbol The symbol to be stored in this node.
     * @param count  The count associated with the symbol in this node.
     * @param left   The left child node.
     * @param right  The right child node.
     */
    public TreeNode(char symbol, int count, TreeNode left, TreeNode right) {
        setSymbol(symbol);
        setCount(count);
        setLeft(left);
        setRight(right);
    } // end of constructor

    /**
     * Sets the count associated with the symbol in this node.
     * @param count The count to be set.
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Sets the symbol to be stored in this node.
     * @param symbol The symbol to be set.
     */
    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    /**
     * Sets the left child node.
     * @param left The left child node to be set.
     */
    public void setLeft(TreeNode left) {
        this.left = left;
    }

    /**
     * Sets the right child node.
     * @param right The right child node to be set.
     */
    public void setRight(TreeNode right) {
        this.right = right;
    }

    /**
     * Returns the count associated with the symbol in this node.
     * @return The count associated with the symbol.
     */
    public int getCount() {
        return count;
    }

    /**
     * Returns the symbol stored in this node.
     * @return The symbol stored in this node.
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * Returns the left child node.
     * @return The left child node.
     */
    public TreeNode getLeft() {
        return left;
    }

    /**
     * Returns the right child node.
     * @return The right child node.
     */
    public TreeNode getRight() {
        return right;
    }

    /**
     * Compares this TreeNode with another TreeNode based on their counts.
     * @param other The TreeNode to be compared with.
     * @return A negative integer, zero, or a positive integer as this node's count
     *         is less than, equal to, or greater than the other node's count.
     */
    @Override
    public int compareTo(TreeNode other) {
        return Integer.compare(count, other.getCount());
    }
} // end of TreeNode class