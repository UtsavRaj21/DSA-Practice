import java.util.*;
public class practice {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static TreeNode getRightMost(TreeNode root , TreeNode par){
        while(root.right == null || root.right == par){
            return root;
        }
        return getRightMost(root.right,par);
    }
    
    public static ArrayList<Integer> morrisInorder(TreeNode root){
        ArrayList<Integer> ans = new ArrayList<Integer>();
        if(root == null) return ans;
        TreeNode curr = root;
        while(curr != null){
            TreeNode left = curr.left;
            if(left == null){
                ans.add(curr.val);
                curr = curr.right;
            }else{
                TreeNode rightMost = getRightMost(left,curr);
                if(rightMost.right == null){
                    rightMost.right = curr;
                    curr= curr.left;
                }else{
                    rightMost.right = null;
                    ans.add(curr.val);
                    curr = curr.right;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(50);                                // Bst Tree
        root.left = new TreeNode(25);
        root.right = new TreeNode(75);
        root.left.left = new TreeNode(13);
        root.left.right = new TreeNode(37);
        root.right.left = new TreeNode(67);
        root.right.right = new TreeNode(90);
        root.right.right.left = new TreeNode(88);
        root.right.right.left.left = new TreeNode(79);

        ArrayList<Integer> ans = morrisInorder(root);
        for(int ele : ans){
            System.out.print(ele + " -> ");
        }
    }
}
