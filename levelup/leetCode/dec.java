import java.util.*;

import javax.naming.spi.DirStateFactory.Result;

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

    //7 : 1290. Convert Binary Number in a Linked List to Integer

    public int count(ListNode head){
        ListNode curr = head;
        int c = -1;
        while(curr!=null){
            c++;
            curr = curr.next;
        }
        return c;
    }
    
    public int getDecimalValue(ListNode head) {
        int count = count(head);
        int res = 0;
        ListNode curr = head;
        while(count >= 0){
            if(curr.val == 1){
                res = res + (int)Math.pow(2, count);
            }
            count--;
            curr = curr.next;
           
        }
        return res;

    }
    
    //8 :- 563. Binary Tree Tilt
    public int[] find(TreeNode root){
        if(root == null){
            int[] base = new int[2];
            return base;
        }
        
        int[] left = find(root.left);
        int[] right = find(root.right);
        int[] res = new int[2];
        int diff = Math.abs(left[0] - right[0]);
        res[0] = left[0] + right[0] + root.val;
        res[1] = left[1] + right[1] + diff;
        
        return res;
    }
    
    public int findTilt(TreeNode root) {
        if(root == null) return 0;
        return find(root)[1];
    }

    // 9 :- 1306. Jump Game III :- dfs
    public boolean Reach(int[] arr, int start,boolean[] vis){
        if(arr[start] == 0){
            return true;
        }
        vis[start] = true;
        boolean flag = false;
        
        int forw = start + arr[start];
        int back = start - arr[start];
        if(arr.length > forw && !vis[forw]){
            flag = flag || Reach(arr, forw,vis);
        }
        if(back>=0 && !vis[back]){
            flag = flag || Reach(arr, back,vis);
        }
        vis[start] = false;
        return flag;
    }
    
    public boolean canReach(int[] arr, int start) {
        boolean[] vis = new boolean[arr.length];
        return Reach(arr,start,vis);
    }

    //10 :- 790. Domino and Tromino Tiling
    public int numTilings(int n) {
        if (n <= 2) {
            return n;
        }
        int MOD = (int)1e9 + 7;
        long fCurrent = 5;
        long fPrevious = 2;
        long fBeforePrevious = 1;
        for (int k = 4; k < n + 1; ++k) {
            long tmp = fPrevious;
            fPrevious = fCurrent;
            fCurrent = (2 * fCurrent + fBeforePrevious) % MOD;
            fBeforePrevious = tmp;
        }
        return (int) (fCurrent % MOD);
    }
    
    //11 :- 878. Nth Magical Number
    public int gcd(int a,int b){
        if(b==0){
            return a;
        }
        return gcd(b,a%b);
    }

    public int lcm(int a,int b){
        return (a*b)/gcd(a,b);
    }
    
    public long evaluate(int a,int b, int lcm,long mid){
        return mid/a + mid/b + mid/lcm;
    }
    
    public int nthMagicalNumber(int n, int a, int b) {
        long low = 0;
        long high = (long)1e17;
        int lcm = lcm(a, b);
        long res = 0;
        while(low <= high){
            long mid = low + (high - low)/2;
            if(evaluate(a,b,lcm,mid) < n){
                low = mid + 1;
            }else{
                res = mid;
                high = mid - 1;
            }
        }
        int mod = (int)1e9+7;
       return (int)(res % mod);
    }
    
    //2)416. Partition Equal Subset Sum
    
    
    //13) 1446. Consecutive Characters
    public int maxPower(String s) {
        if(s.length() == 1) return 1;
        char pr = s.charAt(0);
        int res = 1;
        int count = 1;
        for(int i = 1 ;i <s.length() ; i++){
            char ch = s.charAt(i);
            if(ch==pr){
                count++;
            }else{
                pr = ch;
                count = 1;
            }
            res = Math.max(res,count);
        }
        return res;
    }
    
    public static void main(String[] args) {

    }
}