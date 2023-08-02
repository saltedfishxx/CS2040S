import com.sun.source.tree.Tree;

/**
 * ScapeGoat Tree class
 * <p>
 * This class contains some basic code for implementing a ScapeGoat tree. This version does not include any of the
 * functionality for choosing which node to scapegoat. It includes only code for inserting a node, and the code for
 * rebuilding a subtree.
 */

public class SGTree {

    // Designates which child in a binary tree
    enum Child {LEFT, RIGHT}

    /**
     * TreeNode class.
     * <p>
     * This class holds the data for a node in a binary tree.
     * <p>
     * Note: we have made things public here to facilitate problem set grading/testing. In general, making everything
     * public like this is a bad idea!
     */
    public static class TreeNode {
        int key;
        public TreeNode left = null;
        public TreeNode right = null;
        int weight;

        TreeNode(int k) {
            this.key = k;
            this.weight = 1;
        }
    }

    // Root of the binary tree
    public TreeNode root = null;


    /**
     * Counts the number of nodes in the specified subtree
     *
     * @param node  the parent node, not to be counted
     * @param child the specified subtree
     * @return number of nodes
     */
    public int countNodes(TreeNode node, Child child) {
        // TODO: Implement this
        //return helper method depending on which child subtree
        if (child == Child.LEFT) return count(node.left);
        else return count(node.right);

    }

    /**
     * Recursive method to count number of nodes in tree
     *
     * @param node the current node
     * @return number of nodes
     */
    public int count(TreeNode node) {
        if (node == null)
            return 0;
        int l = count(node.left);
        int r = count(node.right);
        return 1 + l + r;
    }

    int count = 0;
    /**
     * Builds an array of nodes in the specified subtree
     *
     * @param node  the parent node, not to be included in returned array
     * @param child the specified subtree
     * @return array of nodes
     */
    public TreeNode[] enumerateNodes(TreeNode node, Child child) {
        // TODO: Implement this
        TreeNode[] arr = new TreeNode[countNodes(node, child)];
        count = 0;
        //return helper method depending on which child is the subtree
        if (child == Child.LEFT)
            inOrder(node.left, arr);
        else
            inOrder(node.right, arr);
        return arr;
    }

    /**
     * Recursive method to traverse in order and record nodes into an array
     *
     * @param node  the node, not to be included in returned array
     * @param arr   the array of nodes to return
     * @return array of nodes
     */

    public void inOrder(TreeNode node, TreeNode[] arr) {
        if (node != null) {
            //in order: traverse left, record node, traverse right
            inOrder(node.left, arr);
            arr[count++] = node;
            inOrder(node.right, arr);
        }
    }

    /**
     * Builds a tree from the list of nodes
     * Returns the node that is the new root of the subtree
     *
     * @param nodeList ordered array of nodes
     * @return the new root node
     */
    public TreeNode buildTree(TreeNode[] nodeList) {
        // TODO: Implement this
        return addNode(nodeList, 0, nodeList.length - 1);
    }

    /**
     * Helper recursion method to build subtree
     * Returns the node that is the new root of the subtree
     *
     * @param list  ordered array of nodes
     * @param start starting index
     * @param end   ending index
     * @return the new root node
     */
    public TreeNode addNode(TreeNode[] list, int start, int end) {
        //take middle value as root
        //add values less than element to left of tree, and right for more than element, recurse
        if (start > end)
            return null;
        int mid = start + (end - start) / 2;
        TreeNode root = list[mid];
        root.left = addNode(list, start, mid - 1);
        root.right = addNode(list, mid + 1, end);
        return root;
    }

    /**
     * Determines if a node is balanced. If the node is balanced, this should return true. Otherwise, it should return
     * false. A node is unbalanced if either of its children has weight greater than 2/3 of its weight.
     *
     * @param node a node to check balance on
     * @return true if the node is balanced, false otherwise
     */
    public static boolean checkBalance(TreeNode node) {
        if (node == null) {
            return true;
        }
        int maxWeight = (node.weight * 2) / 3;
        return (node.left == null || node.left.weight <= maxWeight) &&
                (node.right == null || node.right.weight <= maxWeight);
    }


    /**
     * Rebuilds the specified subtree of a node.
     *
     * @param node  the part of the subtree to rebuild
     * @param child specifies which child is the root of the subtree to rebuild
     */
    public void rebuild(TreeNode node, Child child) {
        // Error checking: cannot rebuild null tree
        if (node == null) return;
        // First, retrieve a list of all the nodes of the subtree rooted at child
        TreeNode[] nodeList = enumerateNodes(node, child);
        // Then, build a new subtree from that list
        TreeNode newChild = buildTree(nodeList);
        // Finally, replace the specified child with the new subtree
        if (child == Child.LEFT) {
            node.left = newChild;
            fixWeights(node, Child.LEFT);
        } else if (child == Child.RIGHT) {
            node.right = newChild;
            fixWeights(node, Child.RIGHT);
        }
    }

    /**
     * Inserts a key into the tree.
     *
     * @param key the key to insert
     */
    public void insert(int key) {
        if (root == null) {
            root = new TreeNode(key);
            return;
        }

        TreeNode node = root;

        while (true) {
            if (key <= node.key) {
                if (node.left == null) break;
                node.weight += 1;
                if (!checkBalance(node))
                    rebuild(node, Child.LEFT);
                node = node.left;
            } else {
                if (node.right == null) break;
                node.weight += 1;
                if (!checkBalance(node))
                    rebuild(node, Child.RIGHT);
                node = node.right;
            }
        }

        if (key <= node.key) {
            node.left = new TreeNode(key);
        } else {
            node.right = new TreeNode(key);
        }
    }

    /**
     * Updates the weights in a tree
     *
     * @param n node
     * @param child the left/right child of the node
     */
    public void fixWeights(TreeNode n, Child child) {
        if (child == Child.LEFT) {
            fixWeightsHelper(n.left);
        } else {
            fixWeightsHelper(n.right);
        }
    }

    /**
     * Recursive implementation to update the weights in a tree
     *
     * @param n node
     */
    public void fixWeightsHelper(TreeNode n) {
        if (n != null) {
            n.weight = 1 + countNodes(n, Child.LEFT) + countNodes(n, Child.RIGHT);
            fixWeightsHelper(n.left);
            fixWeightsHelper(n.right);
        }
    }

    // Simple main function for debugging purposes
    public static void main(String[] args) {
        SGTree tree = new SGTree();
        for (int i = 0; i < 100; i++) {
            tree.insert(i);
        }
        tree.rebuild(tree.root, Child.RIGHT);
    }
}
