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

    public int[] robHouse(TreeNode root) {
        if (root == null) {
            return new int[2];
        }

        int[] left = robHouse(root.left);
        int[] right = robHouse(root.right);

        int[] ans = new int[2];
        ans[0] = left[1] + right[1] + root.val;
        ans[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return ans;
    }

    public int rob(TreeNode root) {
        int[] ans = robHouse(root);
        return Math.max(ans[0], ans[1]);
    }

    // 6 : 1217. Minimum Cost to Move Chips to The Same Position
    public int minCostToMoveChips(int[] position) {
        int even = 0, odd = 0;
        for (int i = 0; i < position.length; i++) {
            if (position[i] % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }

        return Math.min(odd, even);
    }

    // 7 : 1290. Convert Binary Number in a Linked List to Integer

    public int count(ListNode head) {
        ListNode curr = head;
        int c = -1;
        while (curr != null) {
            c++;
            curr = curr.next;
        }
        return c;
    }

    public int getDecimalValue(ListNode head) {
        int count = count(head);
        int res = 0;
        ListNode curr = head;
        while (count >= 0) {
            if (curr.val == 1) {
                res = res + (int) Math.pow(2, count);
            }
            count--;
            curr = curr.next;

        }
        return res;

    }

    // 8 :- 563. Binary Tree Tilt
    public int[] find(TreeNode root) {
        if (root == null) {
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
        if (root == null)
            return 0;
        return find(root)[1];
    }

    // 9 :- 1306. Jump Game III :- dfs
    public boolean Reach(int[] arr, int start, boolean[] vis) {
        if (arr[start] == 0) {
            return true;
        }
        vis[start] = true;
        boolean flag = false;

        int forw = start + arr[start];
        int back = start - arr[start];
        if (arr.length > forw && !vis[forw]) {
            flag = flag || Reach(arr, forw, vis);
        }
        if (back >= 0 && !vis[back]) {
            flag = flag || Reach(arr, back, vis);
        }
        vis[start] = false;
        return flag;
    }

    public boolean canReach(int[] arr, int start) {
        boolean[] vis = new boolean[arr.length];
        return Reach(arr, start, vis);
    }

    // 10 :- 790. Domino and Tromino Tiling
    public int numTilings(int n) {
        if (n <= 2) {
            return n;
        }
        int MOD = (int) 1e9 + 7;
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

    // 11 :- 878. Nth Magical Number
    public int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }

    public long evaluate(int a, int b, int lcm, long mid) {
        return mid / a + mid / b + mid / lcm;
    }

    public int nthMagicalNumber(int n, int a, int b) {
        long low = 0;
        long high = (long) 1e17;
        int lcm = lcm(a, b);
        long res = 0;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (evaluate(a, b, lcm, mid) < n) {
                low = mid + 1;
            } else {
                res = mid;
                high = mid - 1;
            }
        }
        int mod = (int) 1e9 + 7;
        return (int) (res % mod);
    }

    // 2)416. Partition Equal Subset Sum

    // 13) 1446. Consecutive Characters
    public int maxPower(String s) {
        if (s.length() == 1)
            return 1;
        char pr = s.charAt(0);
        int res = 1;
        int count = 1;
        for (int i = 1; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == pr) {
                count++;
            } else {
                pr = ch;
                count = 1;
            }
            res = Math.max(res, count);
        }
        return res;
    }

    // 14)938. Range Sum of BST

    public void range(TreeNode root, int low, int high, int[] sum) {
        if (root == null) {
            return;
        }

        range(root.left, low, high, sum);
        if (low <= root.val && high >= root.val) {
            sum[0] += root.val;
        }
        // System.out.println(root.val+" "+sum[0]);
        range(root.right, low, high, sum);
    }

    public int rangeSumBST(TreeNode root, int low, int high) {
        int[] sum = new int[1];
        range(root, low, high, sum);
        return sum[0];

    }

    // 147. Insertion Sort List
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode curr = head.next;
        ListNode start = head;
        ListNode prev = head;
        while (curr != null) {
            ListNode forw = curr.next;
            if (prev.val > curr.val) {
                prev.next = forw;
                if (start.val > curr.val) {
                    ListNode ll = new ListNode(curr.val);
                    ll.next = head;
                    start = ll;
                    head = ll;
                } else {
                    while (start != prev) {
                        int val = start.next.val;
                        if (val > curr.val) {
                            ListNode ll = new ListNode(curr.val);
                            ll.next = start.next;
                            start.next = ll;
                            start = head;
                            break;
                        }
                        start = start.next;
                    }
                }
            } else {
                prev = curr;
            }

            curr = forw;
        }
        return head;
    }

    //16) 310. Minimum Height Trees
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if(n==0){
            return res;
        }

        if(n==1){
            res.add(0);
        }

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        
        int[] degree = new int[n];
        for(int i=0;i<n;i++){
            graph.add(new ArrayList<>());
        }

        for(int[] e : edges){
            degree[e[0]]++;
            degree[e[1]]++;
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        Queue<Integer> que = new LinkedList<>();
        for(int i =0;i<n;i++){
            if(degree[i] == 1){
                que.add(i);
            }
        }

        while(n>2){
            int size = que.size();
            n-=size;
            while(size-->0){
                int val = que.poll();
                for(int i : graph.get(val)){
                    degree[i]--;
                    if(degree[i] == 1){
                        que.add(i);
                    }
                }
            }
        }

        res.addAll(que);
        return res;
    }
   
    //17) 221. Maximal Square
    public int maximalSquare(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int[][] dp = new int[n+1][m+1];
        int max = 0;
        for(int i = 0 ; i <= n ;i++){
            for(int j = 0 ; j <= m ;j++){
                if(i==0||j==0){
                    continue;
                }

                if(matrix[i-1][j-1] == '1'){
                    dp[i][j]= Math.min(dp[i-1][j-1], Math.min(dp[i][j-1],dp[i-1][j])) + 1;
                    max = Math.max(max,dp[i][j]);
                }
            }
        }
        return max*max;
    }
    
    //extra:- Longest common substring and common substring
    public static void find(String input){
        String[] arr = input.split(" ");
        int n = arr.length;
        String str = arr[0];
        int s = str.length();
        String res = "";
        ArrayList<String> al = new ArrayList<>();
        for(int i = 0 ; i < s ;i++){
            for(int j = i+1 ; j <=s ;j++){
                String ss = str.substring(i,j);

                int k =1;
                for(int p = 1 ; p < n ; p++){
                    if(!arr[p].contains(ss)){
                        break;
                    }
                    k++;
                }
                if(k==n && ss.length() >=3){
                    if(res.length() <= ss.length()){
                        if(res.length() == ss.length() && res.compareTo(ss) < 0){
                            if(!al.contains(ss)){
                                al.add(ss);
                            }
                            
                        }else{
                            al.add(res);
                            res = ss;
                        }
                        
                    }else{
                        if(!al.contains(ss)){
                            al.add(ss);
                        }
                        
                    }
                }
            }
        }

        if(res.length() == 0){
            System.out.println("No ");
        }else{
            System.out.println("Longest Common Substring : "+res);
            if(al.size()>1){
                System.out.print("Common Substring : ");
                Collections.sort(al);
                for(String sb : al){
                    System.out.print(sb+" ");
                }
            }
        }
    }
    
    //18)
    public int atMostNGivenDigitSet(String[] digits, int n) {
        String num = String.valueOf(n);
        int count = 0;
        for(int i = 1 ; i < num.length() ;i++){
            count+=Math.pow(digits.length, i);
        }

        int i = 0;
        while(i<num.length()){
            int j =0;
            while(j<digits.length && num.charAt(i)-'0' > Integer.parseInt(digits[j])){
                count+=Math.pow(digits.length,num.length()-i-1);
                j++;
            }

            if(j==digits.length || Integer.parseInt(digits[j]) > num.charAt(i) - '0'){
                break;
            }
            i++;
        }

        if(i==num.length()){
            count++;
        }
        return count;
    }

    //19) 394. Decode String

    private int pos = 0;
    public String decodeString(String s) {
    	int n = s.length(), repeat = 0;
    	StringBuilder buf = new StringBuilder();
    	while (pos < n) {
    		char c = s.charAt(pos);
    		if (c >= 'a' && c <= 'z') {
    			buf.append(c);
    		} else if (c >= '0' && c <= '9') {
    			repeat = repeat * 10 + (c - '0');
    		} else if (c == '[') {
    			pos++;  // skip the char '['
    			String str = decodeString(s);  // pos is diff for each call
    			for (int i = 0; i < repeat; i++)
   					buf.append(str);
    			repeat = 0;  // reset the value for the next input
    		} else if (c == ']')
    			break;
    		pos++;
    	}
    	return buf.toString();
    }

    //20) 1200. Minimum Absolute Difference
     public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        long min = (long)1e16;
        List<List<Integer>> res = new ArrayList<>();

        for(int i = 0 ; i < arr.length-1 ;i++){
            long diff = Math.abs(arr[i]-arr[i+1]);
            if(diff == min){
                ArrayList<Integer> base = new ArrayList<>();
                base.add(arr[i]);
                base.add(arr[i+1]);
                res.add(base);
            }
            if(diff < min){
                min = diff;
                res.clear();
                ArrayList<Integer> base = new ArrayList<>();
                base.add(arr[i]);
                base.add(arr[i+1]);
                res.add(base);
            }
        }

        return res;
    }
    
    //21) power of two

    // 22)143. Reorder List 
    public ListNode reverse(ListNode head){
        ListNode curr = head;
        ListNode prev = null;
        while(curr!=null){
            ListNode forw = curr.next;
            curr.next = prev;
            prev = curr;
            curr = forw;
        }
        return prev;
    }
    
    public ListNode mid(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
    
    public void reorderList(ListNode head) {
        if(head == null || head.next == null ||head.next.next == null){
            return;
        }
        ListNode mid = mid(head);
        ListNode nhead = mid.next;
        mid.next = null;

        nhead = reverse(nhead);

        ListNode h1  = head,h2 = nhead;
        while(h2!=null){
            ListNode forw1 = h1.next;
            ListNode forw2 = h2.next;

            h1.next = h2;
            h2.next = forw1;

            h1 = forw1;
            h2 = forw2;

        }


    }
    
    //
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if(numCourses == 1){
            int[] base = {0};
            return base;
        }
        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        for(int i = 0 ;i < numCourses ; i++){
            graph[i] = new ArrayList<>();
        }
        int[] inOrder = new int[numCourses];
        for(int[] e : prerequisites){
            graph[e[1]].add(e[0]);
            inOrder[e[0]]++;
        }

        LinkedList<Integer> que = new LinkedList<>();

        for(int i = 0 ; i < numCourses ; i++){
            if(inOrder[i] == 0){
                que.addLast(i);
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();

        while(que.size()>0){
            int s = que.size();
            while(s-->0){
                int vtx = que.removeFirst();
                ans.add(vtx);
                for(int ele : graph[vtx]){
                    inOrder[ele]--;
                    if(inOrder[ele] == 0){
                        
                        que.addLast(ele);
                    }
                }
            }
        }

        if(ans.size() == 0 || ans.size() != numCourses){
            return new int[0];
        }

        int[] res = new int[numCourses];
        for(int i = 0 ; i <numCourses ;i++){
            res[i] = ans.get(i);
        }
        return res;
    }
    
    
    public static void main(String[] args) {
        find("hel hello hellow");
    }
}