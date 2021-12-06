import java.util.*;

public class dec {

    // 1:-198. House Robber :- Using dp
    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        if (nums.length == 3) {
            return Math.max(nums[0] + nums[2], nums[1]);
        }
        dp[0] = nums[0];
        dp[1] = nums[1];
        dp[2] = nums[0] + nums[2];

        for (int i = 3; i < n; i++) {
            int max = Math.max(dp[i - 2], dp[i - 3]);
            dp[i] = nums[i] + max;
        }
        return Math.max(dp[n - 1], dp[n - 2]);

    }

    // 2:328. Odd Even Linked List
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode oddEvenList(ListNode head) {
        ListNode even = new ListNode();
        ListNode odd = new ListNode();
        ListNode e = even, o = odd;
        ListNode curr = head;
        while (curr != null) {
            if (curr.val % 2 == 0) {
                e.next = new ListNode(curr.val);
                e = e.next;
            } else {
                o.next = new ListNode(curr.val);
                o = o.next;
            }
            curr = curr.next;
        }
        o.next = even.next;
        e.next = null;
        return odd.next;

    }

    // 3: 152. Maximum Product Subarray
    public int maxProduct(int[] nums) {
        int res = -(int) 1e9;
        int ans = 1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                ans = 1;
                res = Math.max(0, res);
            } else {
                ans = ans * nums[i];
                res = Math.max(ans, res);
            }

        }
        ans = 1;

        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == 0) {
                ans = 1;
                res = Math.max(0, res);
            } else {
                ans = ans * nums[i];
                res = Math.max(ans, res);
            }

        }

        return res;
    }

    // 4 : 1032. Stream of Characters
    class StreamChecker {

        public class Node {
            Node[] children;
            boolean isEnd;

            Node() {
                this.children = new Node[26];
                this.isEnd = false;
            }
        }

        Node root;

        StringBuilder sb;

        public void insert(String word, Node root) {
            Node ptr = root;
            for (int i = word.length() - 1; i >= 0; i--) {
                char ch = word.charAt(i);
                if (ptr.children[ch - 'a'] == null) {
                    ptr.children[ch - 'a'] = new Node();
                }
                ptr = ptr.children[ch - 'a'];
            }
            ptr.isEnd = true;
        }

        public StreamChecker(String[] words) {
            root = new Node();
            sb = new StringBuilder();
            for (String word : words) {
                insert(word, root);
            }
        }

        public boolean find() {
            Node ptr = root;
            for (int i = sb.length() - 1; i >= 0; i--) {
                char ch = sb.charAt(i);
                if (ptr.children[ch - 'a'] == null) {
                    return false;
                }
                ptr = ptr.children[ch - 'a'];
                if (ptr.isEnd == true) {
                    return true;
                }
            }
            return false;
        }

        public boolean query(char letter) {

            sb.append(letter);
            return find();
        }
    }

    // 5 : 337. House Robber III
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int[] robHouse(TreeNode root){
        if(root ==  null){
            return new int[2];
        }

        int[] left = robHouse(root.left) ;
        int[] right = robHouse(root.right) ;

        int[] ans = new int[2];
        ans[0] = left[1] + right[1] + root.val;
        ans[1] = Math.max(left[0],left[1]) + Math.max(right[0], right[1]);

        return ans;
    }
   
    public int rob(TreeNode root) {
        int[] ans = robHouse(root);
        return Math.max(ans[0], ans[1]);
    }

    //6 : 1217. Minimum Cost to Move Chips to The Same Position
    public int minCostToMoveChips(int[] position) {
      int even = 0 , odd = 0;
        for(int i =0;i<position.length ;i++){
            if(position[i] % 2 ==0){
                even++;
            }else{
                odd++;
            }
        }
        
        return Math.min(odd,even);
    }
    public static void main(String[] args) {

    }
}