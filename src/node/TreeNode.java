package node;

import com.sun.source.tree.Tree;

public class TreeNode implements Comparable<TreeNode> {
    private int count;
    private char symbol;
    private TreeNode left;
    private TreeNode right;

    public TreeNode() {
        setSymbol('x');
        setCount(0);
        setLeft(null);
        setRight(null);
    } // end of default constructor

    public TreeNode(char symbol, int count, TreeNode left, TreeNode right) {
        setSymbol(symbol);
        setCount(count);
        setLeft(left);
        setRight(right);
    } // end of constructor

    public void setCount(int count) {
        this.count = count;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public int getCount() {
        return count;
    }

    public char getSymbol() {
        return symbol;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    public int compareTo(TreeNode other) {
        if (count == other.getCount()) return 0;
        else if (count < other.getCount()) return -1;
        else return 1;
        // return Integer.compare(count, other.getCount());
    }
} // end of TreeNode class
