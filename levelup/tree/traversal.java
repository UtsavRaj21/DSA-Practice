import java.util.*;

public class traversal {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static TreeNode getRightMost(TreeNode node, TreeNode curr) {
        while (node.right != null && node.right != curr) {
            node = node.right;
        }
        return node;
    }

    public static ArrayList<Integer> morrisInorder(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        if (root == null)
            return null;
        TreeNode curr = root;
        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {
                ans.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode rightMostNode = getRightMost(left, curr);
                if (rightMostNode.right == null) {
                    rightMostNode.right = curr;
                    curr = curr.left;
                } else {
                    rightMostNode.right = null;
                    ans.add(curr.val);
                    curr = curr.right;
                }
            }
        }
        return ans;
    }

    public static ArrayList<Integer> morrisPreorder(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        if (root == null)
            return null;
        TreeNode curr = root;
        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {
                ans.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode rightMostNode = getRightMost(left, curr);
                if (rightMostNode.right == null) {
                    rightMostNode.right = curr;
                    ans.add(curr.val);
                    curr = curr.left;
                } else {
                    rightMostNode.right = null;
                    curr = curr.right;
                }
            }
        }
        return ans;
    }

    public static boolean validBst_helper1(TreeNode root, TreeNode[] prev) {
        if (root == null) {
            return true;
        }

        if (!validBst_helper1(root.left, prev))
            return false;

        if (prev[0] != null && prev[0].val >= root.val)
            return false;
        prev[0] = root;

        if (!validBst_helper1(root.right, prev))
            return false;

        return true;
    }

    public static boolean validBst_helper2(TreeNode root){
        if(root == null) return true;
        TreeNode prev = null;
        TreeNode curr = root;

        while(curr!=null){
            TreeNode left = curr.left;
            if(left == null){
                if(prev !=null && prev.val >= curr.val) {
                    return false;
                }
                prev = curr;
                curr = curr.right;
            }else{
                TreeNode rightMostNode = getRightMost(left, curr);
                if(rightMostNode.right == null){
                    rightMostNode.right = curr;
                    curr= curr.left;
                }else{
                    rightMostNode.right = null;
                    if(prev !=null && prev.val >= curr.val) {
                        return false;
                    }
                    prev = curr;
                    curr = curr.right;
                }
            }
        }
        return true;
    }

    public static void validBst(TreeNode root) {
        TreeNode[] prev = { null };
        // Boolean ans = validBst_helper1(root, prev);                 // recursion t:O(n)  ,  s:recursion space

        Boolean ans = validBst_helper2(root);                           // morris t:O(n)  ,  s:O(1)  better case

        System.out.println(ans);
    }

    public static int kthSmallest(TreeNode root,int k) {
        TreeNode curr = root;
        while (curr != null ) {
            TreeNode left = curr.left;
            if (left == null) {
                if(--k == 0) return curr.val;
                curr = curr.right;
            } else {
                TreeNode rightMostNode = getRightMost(left, curr);
                if (rightMostNode.right == null) {
                    rightMostNode.right = curr;
                    curr = curr.left;
                } else {
                    rightMostNode.right = null;
                    if(--k == 0) return curr.val;
                    curr = curr.right;
                }
            }
        }
        return 0;
    }

    public static TreeNode getLeftMost(TreeNode node, TreeNode curr) {
        while (node.left != null && node.left != curr) {
            node = node.left;
        }
        return node;
    }
    
    public static int kthLargest(TreeNode root,int k) {
        TreeNode curr = root;
        while (curr != null ) {
            TreeNode right = curr.right;
            if (right == null) {
                if(--k == 0) return curr.val;
                curr = curr.left;
            } else {
                TreeNode leftMostNode = getLeftMost(right, curr);
                if (leftMostNode.left == null) {
                    leftMostNode.left = curr;
                    curr = curr.right;
                } else {
                    leftMostNode.left = null;
                    if(--k == 0) return curr.val;
                    curr = curr.left;
                }
            }
        }
        return 0;
    }
    
    public static void kthElement(TreeNode root){
        //int small = kthSmallest(root,3);

        int large = kthLargest(root,3);
        System.out.println(large);
    }
    
    public static TreeNode bToDLL(TreeNode root) {44
      
        TreeNode dummy = new TreeNode(-1);
        TreeNode prev = dummy;
        TreeNode curr = root;
        while(curr != null){
            TreeNode left = curr.left;
            if(left == null){
                prev.right = curr;
                curr.left = prev;
                prev = curr;
                curr = curr.right;
            }else{
                TreeNode rightMostNode = getRightMost(left, curr);
                if (rightMostNode.right == null) {
                    rightMostNode.right = curr;
                    curr = curr.left;
                } else {
                    rightMostNode.right = null;
                    prev.right = curr;
                    curr.left = prev;
                    prev = curr;
                    curr = curr.right;
                }
            }
        }

        TreeNode ans = dummy.right;
        dummy.right = ans.left = null;

        prev.right = ans;
        ans.left = prev;

        return ans;
      }
      
   // BST Itrerator (173) --------------------------------------------------------------------------

   class BSTIterator {

    TreeNode curr = null;

    public BSTIterator(TreeNode root) {
        curr = root;
    }

    private TreeNode getRightMostNode(TreeNode node, TreeNode curr) {
        while (node.right != null && node.right != curr) {
            node = node.right;
        }

        return node;
    }

    public int next() {
        int rv = -1;
        while (this.curr != null) {
            TreeNode left = this.curr.left;
            if (left == null) {
                rv = this.curr.val;
                this.curr = this.curr.right;
                break;
            } else {
                TreeNode rightMost = getRightMostNode(left, this.curr);
                if (rightMost.right == null) {
                    rightMost.right = this.curr;
                    this.curr = this.curr.left;
                } else {
                    rightMost.right = null;
                    rv = this.curr.val;
                    this.curr = this.curr.right;
                    break;
                }
            }

        }
        return rv;

    }

    public boolean hasNext() {
        return this.curr != null;
    }
}
  
//------------------------------------------------------------------------------------------------  
      
    public static void printList(TreeNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.right;
        }
    }
    
    public static void print(ArrayList<Integer> ans) {
        for (var ele : ans) {
            System.out.print(ele + " -> ");
        }
    }

    public static void main(String args[]) {
        // TreeNode root = new TreeNode(1);                              // Not Bst Tree
        // root.left = new TreeNode(2);
        // root.right = new TreeNode(3);
        // root.left.left = new TreeNode(4);
        // root.left.right = new TreeNode(5);
        // root.right.left = new TreeNode(6);
        // root.right.right = new TreeNode(7);
        // root.right.right.left = new TreeNode(8);
        // root.right.right.left.left = new TreeNode(9);

        TreeNode root = new TreeNode(50);                                // Bst Tree
        root.left = new TreeNode(25);
        root.right = new TreeNode(75);
        root.left.left = new TreeNode(13);
        root.left.right = new TreeNode(37);
        root.right.left = new TreeNode(67);
        root.right.right = new TreeNode(90);
        root.right.right.left = new TreeNode(88);
        root.right.right.left.left = new TreeNode(79);

        // ArrayList<Integer> ans = morrisInorder(root);
        // ArrayList<Integer> ans = morrisPreorder(root);
        // print(ans);

        //validBst(root);

        kthElement(root);
    }
}
