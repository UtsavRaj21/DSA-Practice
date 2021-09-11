import java.util.*;

public class cutSet {

    public static void display(int[] dp) {
        for (int ele : dp) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static void display2D(int[][] dp) {
        for (int[] ar : dp) {
            display(ar);
        }
        System.out.println();
    }

    public static int mcm_memo(int[] arr, int si, int ei, int[][] dp) {
        if (si + 1 == ei) {
            return dp[si][ei] = 0;
        }

        if (dp[si][ei] != -1)
            return dp[si][ei];

        int minAns = (int) 1e9;
        for (int cut = si + 1; cut < ei; cut++) {
            int leftans = mcm_memo(arr, si, cut, dp);
            int rightans = mcm_memo(arr, cut, ei, dp);

            int ans = leftans + arr[si] * arr[cut] * arr[ei] + rightans;
            minAns = Math.min(ans, minAns);
        }

        return dp[si][ei] = minAns;
    }

    public static int mcm_tabu(int[] arr, int Si, int Ei, int[][] dp, String[][] sdp) {

        for (int gap = 1; gap < arr.length; gap++) {
            for (int si = 0, ei = gap; ei < arr.length; ei++, si++) {
                if (si + 1 == ei) {
                    dp[si][ei] = 0;
                    sdp[si][ei] = (char) (si + 'A') + "";
                    continue;
                }

                String minStr = "";
                int minAns = (int) 1e9;
                for (int cut = si + 1; cut < ei; cut++) {
                    int leftans = dp[si][cut];// mcm_memo(arr,si,cut,dp);
                    int rightans = dp[cut][ei];// mcm_memo(arr,cut,ei,dp);

                    int ans = leftans + arr[si] * arr[cut] * arr[ei] + rightans;
                    if (minAns > ans) {
                        minAns = ans;
                        minStr = '(' + sdp[si][cut] + sdp[cut][ei] + ')';
                    }
                }

                dp[si][ei] = minAns;
                sdp[si][ei] = minStr;

            }
        }
        System.out.println(sdp[Si][Ei]);
        return dp[Si][Ei];

    }

    public static void mcm() {
        int[] arr = { 4, 2, 3, 1, 3, 4 };
        int n = arr.length;

        int[][] dp = new int[n][n];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        String[][] sdp = new String[n][n];
        for (String[] d : sdp) {
            Arrays.fill(d, "");
        }

        // int ans = mcm_memo(arr,0,arr.length-1,dp);
        int ans = mcm_tabu(arr, 0, arr.length - 1, dp, sdp);
        display2D(dp);
        System.out.println(ans);
    }

   //Minimum and Maximum values of an expression with * and + :- https://www.geeksforgeeks.org/minimum-maximum-values-expression/
   
    public static class minmaxPair {
        int maxval = -(int) 1e9;
        int minval = (int) 1e9;

        minmaxPair(int minval, int maxval) {
            this.minval = minval;
            this.maxval = maxval;
        }

        minmaxPair() {

        }
    }

    public static minmaxPair evolution(minmaxPair left, minmaxPair right, char operator) {
        if (operator == '+') {
            return new minmaxPair(left.minval + right.minval, left.maxval + right.maxval);
        }
        return new minmaxPair(left.minval * right.minval, left.maxval * right.maxval);

    }

    public static minmaxPair minmaxEvoluation(String str, int si, int ei, minmaxPair[][] dp) {
        if (si == ei) {
            int ch = str.charAt(si) - '0';
            return new minmaxPair(ch, ch);
        }

        if (dp[si][ei] != null)
            return dp[si][ei];
        minmaxPair myans = new minmaxPair();
        for (int cut = si + 1; cut < ei; cut += 2) {
            minmaxPair leftpair = minmaxEvoluation(str, si, cut - 1, dp);
            minmaxPair righPair = minmaxEvoluation(str, cut + 1, ei, dp);

            minmaxPair eval = evolution(leftpair, righPair, str.charAt(cut));

            myans.minval = Math.min(myans.minval, eval.minval);
            myans.maxval = Math.max(myans.maxval, eval.maxval);
        }

        return dp[si][ei] = myans;
    }

    public static void minmaxEvoluation() {
        String str = "1+2*3+4*5";
        int n = str.length();
        minmaxPair dp[][] = new minmaxPair[n][n];
        minmaxPair ans = minmaxEvoluation(str, 0, str.length() - 1, dp);
        System.out.println("min:-" + ans.minval);
        System.out.println("max:-" + ans.maxval);
    }

    // 312. Burst Balloons

    public int maxCoins(int[] nums, int si, int ei, int[][] dp) {
        if (dp[si][ei] != 0) {
            return dp[si][ei];
        }
        int maxVal = 0;

        int lval = si == 0 ? 1 : nums[si - 1];
        int rval = ei == nums.length - 1 ? 1 : nums[ei + 1];
        for (int cut = si; cut <= ei; cut++) {
            int left = cut == si ? 0 : maxCoins(nums, si, cut - 1, dp);
            int right = cut == ei ? 0 : maxCoins(nums, cut + 1, ei, dp);

            int ans = left + lval * nums[cut] * rval + right;
            maxVal = Math.max(ans, maxVal);
        }

        return dp[si][ei] = maxVal;


    }

    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];

        return maxCoins(nums, 0, n - 1, dp);
    }

    //Boolean Parenthesization 

    public static class bPair{
        int Ttotal = 0;
        int Ftotal = 0;
        bPair(int Ttotal , int Ftotal){
            this.Ttotal = Ttotal;
            this.Ftotal = Ftotal;
        }
        bPair(){

        }
    }

    public static void Evaluation(bPair left , bPair right,char oper , bPair ans){
        
        int totalways = (left.Ttotal*left.Ftotal) + (right.Ttotal*right.Ftotal);
        
        if(oper == '|'){
            int Fcount = (left.Ftotal * right.Ftotal);
            ans.Ftotal+=Fcount;
            ans.Ttotal = totalways - Fcount;
        }else if(oper == '&'){
            int Tcount = (left.Ttotal * right.Ttotal);
            ans.Ttotal+=Tcount;
            ans.Ftotal = totalways - Tcount;
        }else{
            int Tcount = (left.Ttotal*right.Ftotal) + (right.Ttotal*left.Ftotal);
            ans.Ttotal += Tcount;
            ans.Ftotal += totalways - Tcount;
        }
    }

    public static bPair countWays(String str,int si,int ei,bPair[][] dp){
        if (si == ei) {
            int t = str.charAt(si) == 'T' ? 1 : 0;
            int f = str.charAt(si) == 'F' ? 1 : 0;
         
            bPair base = new bPair(t, f);
            return dp[si][ei] = base;
        }
       if(dp[si][ei] != null) return dp[si][ei];

       bPair ans = new bPair();
        for(int cut = si+1;cut<ei;cut+=2){
           bPair lp = countWays(str, si, cut-1, dp);
           bPair rp = countWays(str, cut+1,ei, dp);
           
           char operator = str.charAt(cut);
            Evaluation(lp, rp, operator, ans);
       }
       return dp[si][ei] = ans;
    }
    
    public static int countWays(int N, String S){
        bPair[][] dp = new bPair[N][N];
        return countWays(S, 0, N-1, dp).Ttotal;
    }
  
    //Optimal Binary Search Tree :- https://www.geeksforgeeks.org/optimal-binary-search-tree-dp-24/

    public static int sumofFreq(int i , int j , int[] freq){
        int sum =0;
        for(int s = i ; s<=j ; s++){
            sum+=freq[s];
        }
        return sum;
    }
    
    public static int obst_memo(int[] nodes , int[] freq , int si,int ei,int[][] dp){
        if(dp[si][ei] !=-1) return dp[si][ei];

        int minAns = (int)1e9;
        int sum =0;
        for(int cut = si;cut <= ei ; cut++){
            int leftRes = cut == si ? 0 : obst_memo(nodes, freq, si, cut -1, dp);
            int RightRes = cut == ei ? 0 : obst_memo(nodes, freq, cut +1,ei, dp);
            sum += freq[cut];
            //int ans = leftRes + sumofFreq(si,ei,freq) + RightRes; // O(n^4)
            int ans = leftRes + RightRes;                           //// O(n^3)
            minAns = Math.min(ans,minAns);

        }

        //return dp[si][ei] = minAns;
        return dp[si][ei] = minAns +sum;
    }

    public static void obst(){
    int keys[] = {10, 12, 20}, freq[] = {34, 8, 50};
    int n = keys.length;
    int dp[][] = new int[n][n];
    for(int[] d: dp) Arrays.fill(d,-1);

    int ans =  obst_memo(keys, freq, 0, n-1, dp);
    System.out.println(ans);
  }
  
  // 95. Unique Binary Search Trees II

  public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode() {}
         TreeNode(int val) { this.val = val; }
         TreeNode(int val, TreeNode left, TreeNode right) {
             this.val = val;
             this.left = left;
             this.right = right;
         }
     }
  

     public void generateAllBst(int num ,  List<TreeNode> left, List<TreeNode> right, List<TreeNode> ans){
         if(left.size()!=0 && right.size()!=0){
             for(int i=0;i<left.size();i++){
                 for(int j=0;j<right.size();j++){
                     TreeNode root = new TreeNode(num);
                     root.left = left.get(i);
                     root.right = right.get(j);
                     ans.add(root);
                 }
             }
         }else if(left.size()!=0){
            for(int i=0;i<left.size();i++){
                TreeNode root = new TreeNode(num);
                root.left = left.get(i);
                ans.add(root);
            }
         }else if(right.size()!=0){
            for(int j=0;j<right.size();j++){
                TreeNode root = new TreeNode(num);
                root.right = right.get(j);
                ans.add(root);
            }      
        }else{
            ans.add(new TreeNode(num));
        }
     }
     
     public List<TreeNode> generateTrees(int si,int ei) {
        List<TreeNode> ans = new ArrayList<>();

        for(int cut = si ; cut<=ei ; cut++){
            List<TreeNode> left = generateTrees(si,cut-1);
            List<TreeNode> right = generateTrees(cut+1,ei);

            generateAllBst(cut , left,right,ans);
        }

        return ans;
    }
     
    public List<TreeNode> generateTrees(int n) {
        return generateTrees(1,n);
    }

    //1039. Minimum Score Triangulation of Polygon

    public int minScoreTriangulation(int[] values,int si,int ei,int[][] dp) {
        if(ei-si<=1) return dp[si][ei] =0;

        if(dp[si][ei] !=-1) return dp[si][ei];
        int minAns = (int) 1e9;

        for(int cut= si+1;cut<ei;cut++){
            int leftRes = minScoreTriangulation(values, si, cut, dp);
            int rightRes = minScoreTriangulation(values, cut,ei, dp);

            int ans = leftRes + values[si] * values[cut] * values[ei] + rightRes;

            minAns = Math.min(minAns,ans);
        }
        return dp[si][ei] = minAns;
    }

    public int minScoreTriangulation(int[] values) {
        int n = values.length;

        int[][] dp = new int[n][n];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        return minScoreTriangulation(values, 0, n-1, dp);
    }
   
    //139. Word Break

    public boolean wordBreak(String str, List<String> wordDict) {
        int n = str.length();
        boolean[] bdp = new boolean[n+1];
        int len =0;
        HashSet<String> map = new HashSet<>();
        for(String s :wordDict ){
            len = Math.max(len,s.length());
            map.add(s);
        }
        bdp[0] = true;

        for(int i = 0 ; i < n ;i++){
            if(!bdp[i]) continue;

            for(int l = 1 ; i+l <= n && l <=len ; l++){
                if(map.contains(str.substring(i, i+l))) bdp[i+l] = true;
            }
        }

        return bdp[n];
    }

    //140. Word Break II

    public void dfs(String str , int idx,int len,String asf,List<String> wordDict,boolean[] dp,List<String> ans){
        if(idx == str.length()){
            ans.add(asf.substring(0,asf.length()-1));
            return;
        }

        for(int l = 1; l+idx <= str.length() && l <= len ; l++){
            if(dp[idx+l] && wordDict.contains(str.subSequence(idx, idx + l))){
                dfs(str, idx+l, len, asf+str.subSequence(idx, idx + l)+" ", wordDict, dp, ans);
            }
        }
    }

    public List<String> wordBreak_II(String str, List<String> wordDict) {
        int n = str.length();
        boolean[] bdp = new boolean[n+1];
        int len =0;
        HashSet<String> map = new HashSet<>();
        for(String s :wordDict ){
            len = Math.max(len,s.length());
            map.add(s);
        }
        bdp[0] = true;

        for(int i = 0 ; i < n ;i++){
            if(!bdp[i]) continue;

            for(int l = 1 ; i+l <= n && l <=len ; l++){
                if(map.contains(str.substring(i, i+l))) bdp[i+l] = true;
            }
        }

        List<String> ans = new ArrayList<>();
        if(!bdp[n]) return ans;
        
        dfs(str,0,len,"",wordDict,bdp,ans);
        return ans;

        
    }
    
    public static void main(String[] args) {
        // mcm();
        //minmaxEvoluation();
        //countWays(7,"T|T&F^T");
        obst();
    }
}
