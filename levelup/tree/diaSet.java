import java.util.*;

public class diaSet {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static class Node {
        int data = 0;
        Node left = null;
        Node right = null;

        Node(int data) {
            this.data = data;
        }
    }

    public static int size(TreeNode root) {
        return root == null ? 0 : size(root.left) + size(root.right) + 1;
    }

    public static int height(TreeNode root) {
        return root == null ? -1 : Math.max(height(root.left), height(root.right)) + 1;
    }

    // 543
    public static int dia_01(TreeNode root) { // t:O(n^2)
        if (root == null) {
            return 0;
        }

        int ld = dia_01(root.left);
        int rd = dia_01(root.right);

        int lh = height(root.left);
        int rh = height(root.right);

        return Math.max(Math.max(ld, rd), rh + lh + 2);
    }

    // t:O(n) {diameter,height} most imp
    public static int[] dia_02(TreeNode root) {
        if (root == null) {
            return new int[] { 0, -1 };
        }

        int[] ld = dia_02(root.left);
        int[] rd = dia_02(root.right);

        int[] myans = new int[2];
        myans[0] = Math.max(Math.max(ld[0], rd[0]), ld[1] + rd[1] + 2);
        myans[1] = Math.max(ld[1], rd[1]) + 1;
        return myans;
    }

    // using static .... int[0] = max diameter from left and right side; t:O(n)
    public static int dia_03(TreeNode root, int[] data) {
        if (root == null) {
            return -1;
        }

        int lh = dia_03(root.left, data);
        int rh = dia_03(root.right, data);

        data[0] = Math.max(data[0], lh + rh + 2);

        return Math.max(lh, rh) + 1;
    }

    // 112

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null)
            return false;

        if (root.left == null && root.right == null) {
            return targetSum - root.val == 0 ? true : false;
        }

        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    // 113

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> small = new ArrayList<>();

        pathSum_helper(root, targetSum, ans, small);
        return ans;
    }

    public void pathSum_helper(TreeNode root, int targetSum, List<List<Integer>> ans, List<Integer> small) {
        if (root == null)
            return;

        if (root.left == null && root.right == null) {
            if (targetSum - root.val == 0) {
                small.add(root.val);
                ans.add(new ArrayList<Integer>(small));
                small.remove(small.size() - 1);
            }
            return;
        }
        small.add(root.val);

        pathSum_helper(root.left, targetSum - root.val, ans, small);
        pathSum_helper(root.right, targetSum - root.val, ans, small);

        small.remove(small.size() - 1);
    }

    // gfg -- https://practice.geeksforgeeks.org/problems/maximum-path-sum/1

    public static class leafToLeafPair {
        int LTLMaxSum = -(int) 1e9; // Leaves to Leaves Max Sum
        int NTLMaxSum = -(int) 1e9; // Node to Leaves Max Sum.
    }

    public static leafToLeafPair maxLeafSum(Node root) {
        if (root == null) {
            return new leafToLeafPair();
        }

        if (root.left == null && root.right == null) {
            leafToLeafPair base = new leafToLeafPair();
            base.NTLMaxSum = root.data;
            return base;
        }

        leafToLeafPair lRes = maxLeafSum(root.left);
        leafToLeafPair rRes = maxLeafSum(root.right);

        leafToLeafPair myRes = new leafToLeafPair();
        myRes.LTLMaxSum = Math.max(lRes.LTLMaxSum, rRes.LTLMaxSum);

        if (root.left != null && root.right != null) {
            myRes.LTLMaxSum = Math.max(myRes.LTLMaxSum, lRes.NTLMaxSum + root.data + rRes.NTLMaxSum);
        }

        myRes.NTLMaxSum = Math.max(lRes.NTLMaxSum, rRes.NTLMaxSum) + root.data;
        return myRes;
    }

    public static int maxPathSum(Node root) {
        int ans = maxLeafSum(root).LTLMaxSum;
        int ans2 = maxLeafSum(root).NTLMaxSum;

        return ans != -(int) 1e9 ? ans : Math.max(ans, ans2);
    }

    // 124 https://leetcode.com/problems/binary-tree-maximum-path-sum/submissions/

    public class Pair {
        int NTN = -(int) 1e9;
        int max = -(int) 1e9;
    }

    public int getMax(int... arr) {
        int max = arr[0];
        for (int ele : arr) {
            max = Math.max(ele, max);
        }
        return max;
    }

    public Pair maxPath_1(TreeNode root) {
        Pair myans = new Pair();
        if (root == null)
            return myans;

        Pair left = maxPath_1(root.left);
        Pair right = maxPath_1(root.right);

        int onesided = Math.max(left.NTN, right.NTN) + root.val;
        myans.max = getMax(left.max, right.max, root.val, left.NTN + right.NTN + root.val, onesided);

        myans.NTN = Math.max(onesided, root.val);

        return myans;
    }

    // {max,ntn}

    public int[] maxPath_2(TreeNode root, int[] arr) {
        if (root == null)
            return new int[] { -(int) 1e9, -(int) 1e9 };

        int[] left = maxPath_2(root.left, arr);
        int[] right = maxPath_2(root.right, arr);

        int[] ans = new int[2];
        int onesided = Math.max(left[1], right[1]) + root.val;

        ans[0] = getMax(left[0], right[0], root.val, left[1] + right[1] + root.val, onesided);
        ans[1] = Math.max(onesided, root.val);
        return ans;

    }

    public int maxPathSum_(TreeNode root) {
        return maxPath_1(root).max;
        // int[] arr = {-(int)1e9,-(int)1e9};
        // return maxPath_2(root,arr).[0];

    }

    // Camera https://leetcode.com/problems/binary-tree-cameras/submissions/

    // -1:camera required
    // 0 : already covered
    // 1 : i have camera

    public int min(TreeNode root, int[] ans) {
        if (root == null)
            return 0;

        int left = min(root.left, ans);
        int right = min(root.right, ans);

        if (left == -1 || right == -1) {
            ans[0]++;
            return 1;
        }

        if (left == 1 || right == 1) {
            return 0;
        }

        return -1;
    }

    public int minCameraCover(TreeNode root) {
        int[] ans = new int[1];
        int a = min(root, ans);
        if (a == -1)
            ans[0]++;
        return ans[0];
    }

    // 337 -- house Robber https://leetcode.com/problems/house-robber-iii/

    public int[] robber(TreeNode root, int[] arr) {
        if (root == null)
            return new int[2];

        int[] left = robber(root.left, arr);
        int[] right = robber(root.right, arr);

        int[] ans = new int[2];
        ans[0] = left[1] + right[1] + root.val;
        ans[1] = Math.max(left[1], left[0]) + Math.max(right[1], right[0]);
        return ans;
    }

    public int rob(TreeNode root) {
        int[] ans = new int[2];
        ans = robber(root, ans);
        return Math.max(ans[0], ans[1]);
    }

    // 1372 -- Longest ZigZag Path -- https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/submissions/

    public int[] longest(TreeNode root, int[] arr) {
        if (root == null)
            return new int[] { -1, -1, -1 };

        int[] left = longest(root.left, arr);
        int[] right = longest(root.right, arr);

        int[] myans = new int[3];
        myans[0] = left[1] + 1;
        myans[1] = right[0] + 1;
        myans[2] = Math.max(Math.max(left[2], right[2]), Math.max(myans[0], myans[1]));
        return myans;
    }

    public int longestZigZag(TreeNode root) {
        int[] arr = new int[3];
        arr = longest(root, arr);
        return arr[2];

    }

}