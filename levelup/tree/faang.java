import java.util.*;

public class faang {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // BST TO GST
    // https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/submissions/

    public TreeNode solve(TreeNode root, int[] c) {
        if (root == null) {
            return null;
        }
        solve(root.right, c);
        root.val = c[0] = root.val + c[0];
        solve(root.left, c);
        return root;
    }

    public TreeNode bstToGst(TreeNode root) {
        int[] c = new int[1];
        return solve(root, c);

    }

    // 1305. All Elements in Two Binary Search Trees
    // https://leetcode.com/problems/all-elements-in-two-binary-search-trees/

    public void get(TreeNode root, List<Integer> l) {
        if (root == null) {
            return;
        }
        get(root.left, l);
        l.add(root.val);
        get(root.right, l);
    }

    public List<Integer> merge(List<Integer> l1, List<Integer> l2) {
        if (l1.size() == 0 || l2.size() == 0) {
            return l1.size() == 0 ? l2 : l1;
        }
        List<Integer> ans = new ArrayList<Integer>();
        int p = 0, q = 0;
        while (l1.size() > p && l2.size() > q) {
            if (l1.get(p) < l2.get(q)) {
                ans.add(l1.get(p));
                p++;
            } else {
                ans.add(l2.get(q));
                q++;
            }
        }

        while (l1.size() > p) {
            ans.add(l1.get(p));
            p++;
        }
        while (l2.size() > q) {
            ans.add(l2.get(q));
            q++;
        }
        return ans;
    }

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> l1 = new ArrayList<Integer>();
        List<Integer> l2 = new ArrayList<Integer>();
        List<Integer> ans = new ArrayList<Integer>();

        get(root1, l1);
        get(root2, l2);

        ans = merge(l1, l2);

        return ans;
    }

    // 107. Binary Tree Level Order Traversal II : -
    // https://leetcode.com/problems/binary-tree-level-order-traversal-ii/

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null)
            return ans;

        LinkedList<TreeNode> que = new LinkedList<>();

        que.addLast(root);

        while (que.size() != 0) {
            int s = que.size();
            List<Integer> small = new ArrayList<>();
            while (s-- > 0) {
                TreeNode node = que.removeFirst();
                small.add(node.val);

                if (node.left != null) {
                    que.addLast(node.left);
                }
                if (node.right != null) {
                    que.addLast(node.right);
                }
            }
            ans.add(small);
        }

        List<List<Integer>> f = new ArrayList<>();

        for (int i = ans.size() - 1; i >= 0; i--) {
            f.add(ans.get(i));
        }

        return f;
    }

    // 114. Flatten Binary Tree to Linked List :-
    // https://leetcode.com/problems/flatten-binary-tree-to-linked-list/

    public TreeNode rightMostNode(TreeNode node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public void flatten(TreeNode root) {
        if (root == null)
            return;

        TreeNode curr = root;
        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {
                curr = curr.right;
            } else {
                TreeNode rightNode = rightMostNode(left);
                rightNode.right = curr.right;
                curr.right = left;
                curr.left = null;
                curr = curr.right;
            }
        }
    }

    // Clone a Binary Tree :-
    // https://practice.geeksforgeeks.org/problems/clone-a-binary-tree/1

    static class Tree {
        int data;
        Tree left, right, random;

        Tree(int d) {
            data = d;
            left = null;
            right = null;
            random = null;
        }
    }

    public static void updateRandomPointers(Tree root, Map<Tree, Tree> map) {
        // base case
        if (map.get(root) == null) {
            return;
        }

        // update the random pointer of the cloned node
        map.get(root).random = map.get(root.random);

        // recur for the left and right subtree
        updateRandomPointers(root.left, map);
        updateRandomPointers(root.right, map);
    }

    public static Tree cloneLeftRightPointers(Tree root, Map<Tree, Tree> map) {
        // base case
        if (root == null) {
            return null;
        }

        // clone all fields of the root node except the random pointer

        // create a new node with the same data as the root node
        map.put(root, new Tree(root.data));

        // clone the left and right subtree
        map.get(root).left = cloneLeftRightPointers(root.left, map);
        map.get(root).right = cloneLeftRightPointers(root.right, map);

        // return cloned root node
        return map.get(root);
    }

    public static Tree cloneSpecialBinaryTree(Tree root) {
        // create a map to store mappings from a node to its clone
        Map<Tree, Tree> map = new HashMap<>();

        // clone data, left, and right children for each node of the original
        // binary tree, and put references into the map
        cloneLeftRightPointers(root, map);

        // update random pointers from the original binary tree in the map
        updateRandomPointers(root, map);

        // return the cloned root node
        return map.get(root);
    }

    public static Tree cloneTree(Tree tree) {
        return cloneSpecialBinaryTree(tree);
    }

    // https://leetcode.com/problems/populating-next-right-pointers-in-each-node/

    static class Node {
        int val;
        Node left;
        Node right;
        Node next;

        Node() {
        }

        Node(int _val) {
            val = _val;
        }

        Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public static Node connect(Node root) {
        LinkedList<Node> que = new LinkedList<>();

        que.addLast(root);
        root.next = null;
        int idx = 1;
        while (que.size() != 0) {
            int i = idx;
            idx = idx * 2;
            int size = que.size();
            while (size-- > 0) {
                Node rNode = que.removeFirst();
                if (--i == 0) {
                    rNode.next = null;
                } else {
                    rNode.next = que.getFirst();
                }

                if (rNode.left != null) {
                    que.addLast(rNode.left);
                }
                if (rNode.right != null) {
                    que.addLast(rNode.right);
                }
            }
        }
        return root;
    }

    // 979. Distribute Coins in Binary Tree :-https://leetcode.com/problems/distribute-coins-in-binary-tree/

    int ans;
    public int distributeCoins(TreeNode root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int l = dfs(root.left);
        int r = dfs(root.right);
        ans += Math.abs(l) + Math.abs(r);
        return root.val - 1 + r + l;
    }

    //--------------------------------------------------------------------------------------------------------------------------------------
    public static void display(Node root) {
        if (root == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(root.left + " -> " + root.val + " <- " + root.right + "( " + root.next + " )");
        System.out.println(sb.toString());

        display(root.left);
        display(root.right);
    }

    public static void main(String args[]) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.right.left = new Node(8);
        root.right.right.left.left = new Node(9);

        // String s = serialize_preorder(root);
        // System.out.println(s);
        // deserialize(s);
        // int inOrder[] = {4, 2, 5, 1, 3};
        // int preOrder[] = {1, 2, 4, 5, 3};
        // int postOrder[] = {4, 5, 2, 3, 1};

        // // Boolean ans = checkTree(inOrder, preOrder, postOrder);
        // // System.out.println(ans);
        // int level[] = {2 ,7 ,15, 12 ,6 ,9, 5 ,11, 4};
        // int in[] = {12, 7, 5, 6, 11 ,2 ,15, 4 ,9};

        // TreeNode node = inlevel(level,in);
        // display(node);

        connect(root);
        display(root);

    }

}

// 146067726