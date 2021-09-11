import java.util.*;
public class stringSet {

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
    
    //516. Longest Palindromic Subsequence :- https://leetcode.com/problems/longest-palindromic-subsequence/

    public static int longestPalindromeSubseq_memo(String s,int i,int j,int[][] dp) {
        if(i>=j){
            return dp[i][j] = (i==j)?1:0;
        }
         if(dp[i][j] != 0){
            return dp[i][j];
        }
 
        if(s.charAt(i)!=s.charAt(j)){
            dp[i][j] = Math.max(longestPalindromeSubseq_memo(s,i+1,j,dp),longestPalindromeSubseq_memo(s,i,j-1,dp));
        }else{
             dp[i][j] = longestPalindromeSubseq_memo(s,i+1,j-1,dp)+2;
        }
 
        return dp[i][j];
     }

    public static int longestPalindromeSubseq_tabu(String s,int I,int J,int[][] dp) {
        int n = s.length();
        for(int gap=0 ; gap < n ; gap++){
            for(int i=0 , j=gap ; j< n;i++,j++){
              if(i>=j){
                     dp[i][j] = (i==j)?1:0;
                  continue;
                 }
             

             if(s.charAt(i)!=s.charAt(j)){
                 dp[i][j] =Math.max(dp[i+1][j],dp[i][j-1]); // Math.max(longestPalindrome_memo(s,i+1,j,dp),longestPalindrome_memo(s,i,j-1,dp));
             }else{
                  dp[i][j] = dp[i+1][j-1];//longestPalindrome_memo(s,i+1,j-1,dp)+2;
             }
          }
        }
    

     return dp[0][n-1];
  }
    
    public static String  longestPalindrome_ReverseEngi(String s,int i,int j,int[][] dp){
        if(i>=j){
            return (i==j)? String.valueOf(s.charAt(i)):"";
        }

        String ans = "";

        if(s.charAt(i) == s.charAt(j)){
            ans =longestPalindrome_ReverseEngi(s,i+1,j-1,dp);
            ans = s.charAt(i) + ans + s.charAt(i);
        }else{
            if(dp[i+1][j] > dp[i][j-1]){
                ans =longestPalindrome_ReverseEngi(s,i+1,j,dp);
                
            }else{
                ans =longestPalindrome_ReverseEngi(s,i,j-1,dp);
                
            }
        }

        return ans;
    }

    public static int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        
        int ans = longestPalindromeSubseq_memo(s,0,n-1,dp);
        display2D(dp);
        String str= longestPalindrome_ReverseEngi(s,0,n-1,dp);         // Palndromic string
        System.out.println(str);
        return ans;
    }


    //5. Longest Palindromic Substring : https://leetcode.com/problems/longest-palindromic-substring/

    public static String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        int len = 0, si = 0;

        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; j++, i++) {
                if (gap == 0)
                    dp[i][j] = true;
                else if (gap == 1 && s.charAt(i) == s.charAt(j))
                    dp[i][j] = true;
                else
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];

                if (dp[i][j] && j - i + 1 > len) {
                    len = j - i + 1;
                    si = i;
                }
            }
        }

        return s.substring(si, si + len);
    }

    //1143. Longest Common Subsequence :-  

    public static int longestCommonSubsequence_memo(String s1,int n,String s2,int m,int[][] dp){
        if(n==0 || m==0){
            return dp[n][m] = 0;
        }


        if(dp[n][m] != -1) return dp[n][m];

        if(s1.charAt(n-1) == s2.charAt(m-1)){
            return dp[n][m] = longestCommonSubsequence_memo(s1,n-1,s2,m-1,dp) +1;
        }else{
            return dp[n][m] = Math.max(longestCommonSubsequence_memo(s1,n-1,s2,m,dp),longestCommonSubsequence_memo(s1,n,s2,m-1,dp));
        }
    }

    public static int longestCommonSubsequence_tabu(String s1,int N,String s2,int M,int[][] dp){
        for(int n = 0 ; n<=N;n++){
            for(int m=0;m<=M;m++){
                if(n==0 || m==0){
                     dp[n][m] = 0;
                     continue;
                }
    
                if(s1.charAt(n-1) == s2.charAt(m-1)){
                    dp[n][m] =dp[n-1][m-1]+1;// longestCommonSubsequence_memo(s1,n-1,s2,m-1,dp) +1;
                }else{
                    dp[n][m] = Math.max(dp[n-1][m],dp[n][m-1]);//Math.max(longestCommonSubsequence_memo(s1,n-1,s2,m,dp),longestCommonSubsequence_memo(s1,n,s2,m-1,dp));
                }
            }
        }

        return dp[N][M]; 
    }

    public static String longestCommonSubsequence_ReverseEngi(String s1,int n,String s2,int m,int[][] dp){
        if(n==0 || m==0){
            return "";
        }

        if(s1.charAt(n-1) == s2.charAt(m-1)){
            return longestCommonSubsequence_ReverseEngi(s1,n-1,s2,m-1,dp) +s1.charAt(n-1);
        }else{
            if(dp[n-1][m] > dp[n][m-1]){
                return longestCommonSubsequence_ReverseEngi(s1,n-1,s2,m,dp);
            }else{
               return longestCommonSubsequence_ReverseEngi(s1,n,s2,m-1,dp);
            }
        }
    }

    public static int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length(),m=text2.length();
        int dp[][] = new int[n+1][m+1];

        for(int[] d : dp) Arrays.fill(d,-1);
        //int ans = longestCommonSubsequence_memo(text1,n,text2,m,dp);
        int ans = longestCommonSubsequence_tabu(text1,n,text2,m,dp);
        String str = longestCommonSubsequence_ReverseEngi(text1,n,text2,m,dp);
        System.out.println(str);
        display2D(dp);
        return ans;
    }
    
     // 1458. Max Dot Product of Two Subsequences

     public int maximun(int... arr) {
        int max = arr[0];
        for (int ele : arr) {
            max = Math.max(ele, max);
        }
        return max;
    }

    public int maxDotProduct_memo(int n1, int n2, int[] nums1, int[] nums2, int[][] dp) {
        if (n1 == 0 || n2 == 0) {
            return dp[n1][n2] = -(int) 1e8;
        }

        if (dp[n1][n2] != -(int) 1e9) {
            return dp[n1][n2];
        }

        int up = maxDotProduct_memo(n1 - 1, n2, nums1, nums2, dp);
        int left = maxDotProduct_memo(n1, n2 - 1, nums1, nums2, dp);

        int upLeft = maxDotProduct_memo(n1 - 1, n2 - 1, nums1, nums2, dp) + (nums1[n1 - 1] * nums2[n2 - 1]);
        int self = nums1[n1 - 1] * nums2[n2 - 1];

        return dp[n1][n2] = maximun(up, left, upLeft, self);
    }

    public int maxDotProduct_dp(int N1, int N2, int[] nums1, int[] nums2, int[][] dp) {
        for (int n1 = 0; n1 <= N1; n1++) {
            for (int n2 = 0; n2 <= N2; n2++) {
                if (n1 == 0 || n2 == 0) {
                    dp[n1][n2] = -(int) 1e8;
                    continue;
                }

                int up = dp[n1 - 1][n2];// maxDotProduct_memo(n1-1, n2, nums1, nums2, dp);
                int left = dp[n1][n2 - 1]; // maxDotProduct_memo(n1, n2-1, nums1, nums2, dp);
                int self = nums1[n1 - 1] * nums2[n2 - 1];
                int upLeft = dp[n1 - 1][n2 - 1] * self;

                dp[n1][n2] = maximun(up, left, upLeft, self);

            }
        }

        return dp[N1][N2];
    }

    public int maxDotProduct(int[] nums1, int[] nums2) {

        int n = nums1.length, m = nums2.length;

        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -(int) 1e9);

        return maxDotProduct_memo(n, m, nums1, nums2, dp);

    }

    // 1035. Uncrossed Lines

    public int maxUncrossedLines_memo(int[] A, int[] B, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = 0;

        }

        if (dp[n][m] != -1)
            return dp[n][m];

        if (A[n - 1] == B[m - 1]) {
            dp[n][m] = maxUncrossedLines_memo(A, B, n - 1, m - 1, dp) + 1; // lcss(s1,s2,n-1,m-1,dp)+1;
        } else {
            dp[n][m] = Math.max(maxUncrossedLines_memo(A, B, n, m - 1, dp), maxUncrossedLines_memo(A, B, n - 1, m, dp));
        }

        return dp[n][m];
    }

    public int maxUncrossedLines_dp(int[] A, int[] B, int N, int M, int[][] dp) {
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = 0;
                    continue;
                }

                if (A[n - 1] == B[m - 1]) {
                    dp[n][m] = dp[n - 1][m - 1] + 1; // lcss(s1,s2,n-1,m-1,dp)+1;
                } else {
                    dp[n][m] = Math.max(dp[n - 1][m], dp[n][m - 1]);
                }
            }
        }
        return dp[N][M];
    }

    public int maxUncrossedLines(int[] A, int[] B) {

        int n = A.length, m = B.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int[] a : dp) {
            Arrays.fill(a, -1);
        }
        // lcss(s1,s2,n,m,dp);
        maxUncrossedLines_dp(A, B, n, m, dp);
        return dp[n][m];
    }

    // 72. Edit Distance

    public int minDistance_memo(String s1, String s2, int n, int m, int[][] dp) {
        if (n == 0) // insert
            dp[n][m] = m;
        else if (m == 0) // delete
            dp[n][m] = n;

        if (dp[n][m] != -1) {
            return dp[n][m];
        }

        if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
            return dp[n][m] = minDistance_memo(s1, s2, n - 1, m - 1, dp);
        }

        int insert = minDistance_memo(s1, s2, n, m - 1, dp);
        int delete = minDistance_memo(s1, s2, n - 1, m, dp);
        int replace = minDistance_memo(s1, s2, n - 1, m - 1, dp);

        return dp[n][m] = Math.min(Math.min(insert, delete), replace) + 1;
    }

    public int minDistance(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] a : dp) {
            Arrays.fill(a, -1);
        }

        return minDistance_memo(s1, s2, n, m, dp);
    }

    // gfg :- Minimum number of deletions and insertions.
    // :https://practice.geeksforgeeks.org/problems/minimum-number-of-deletions-and-insertions0209/1

    public int minDistance_Delete_memo(String s1, String s2, int n, int m, int[][] dp) {
        if (n == 0)
            dp[n][m] = m;
        else if (m == 0)
            dp[n][m] = n;

        if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
            return dp[n][m] = minDistance_Delete_memo(s1, s2, n - 1, m - 1, dp);
        }

        int left = minDistance_Delete_memo(s1, s2, n, m - 1, dp);
        int right = minDistance_Delete_memo(s1, s2, n - 1, m, dp);

        return dp[n][m] = Math.min(left, right) + 1;

    }

    public int minDistance_Delete(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] a : dp) {
            Arrays.fill(a, -1);
        }

        return minDistance_Delete_memo(s1, s2, n, m, dp);
    }

    // 583. Delete Operation for Two Strings

 

    public int minDistanceD(String word1, String word2) {
        int length = word1.length() + word2.length();

        int n = word1.length(), m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] a : dp) {
            Arrays.fill(a, -1);
        }

        int ans = longestCommonSubsequence_memo(word1, n, word2, m, dp);

        return length - 2 * ans;

    }

    // 115. Distinct Subsequences

    public int numDistinct_memo(String s, int n, String t, int m, int[][] dp) {
        if (m == 0 || n < m) {
            return dp[n][m] = (m == 0 ? 1 : 0);
        }

        if (dp[n][m] != -1) {
            return dp[n][m];
        }

        int a = numDistinct_memo(s, n - 1, t, m - 1, dp);
        int b = numDistinct_memo(s, n - 1, t, m, dp);

        if (s.charAt(n - 1) == t.charAt(m - 1)) {
            return dp[n][m] = a + b;
        } else {
            return dp[n][m] = b;
        }
    }

    public int numDistinct_dp(String s, int N, String t, int M, int[][] dp) {
        for (int gap = 0; gap <= N; gap++) {
            for (int n = 0, m = gap; m <= M; m++, n++) {
                if (m == 0 || n < m) {
                    dp[n][m] = (m == 0 ? 1 : 0);
                    continue;
                }

                int a = dp[n - 1][m - 1];// numDistinct_memo(s, n - 1, t, m - 1, dp);
                int b = dp[n - 1][m];// numDistinct_memo(s, n - 1, t, m, dp);

                if (s.charAt(n - 1) == t.charAt(m - 1)) {
                    dp[n][m] = a + b;
                } else {
                    dp[n][m] = b;
                }
            }
        }

        return dp[N][M];

    }

    public int numDistinct(String s, String t) {
        int n = s.length(), m = t.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] a : dp) {
            Arrays.fill(a, -1);
        }

        int ans = numDistinct_memo(s, n, t, m, dp);
        return dp[n][m] = ans;
    }

    // Count subsequences of type a^i, b^j, c^k :- https://practice.geeksforgeeks.org/problems/count-subsequences-of-type-ai-bj-ck4425/1
    
    public int fun(String s) {
        int empty = 1;
        long aCount = 0, bCount = 0, cCount = 0;
        int mod = (int) 1e9 + 7;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == 'a') {
                aCount = aCount + (aCount + empty) % mod;
            } else if (ch == 'b') {
                bCount = bCount + (bCount + aCount) % mod;
            } else if (ch == 'c') {
                cCount = cCount + (cCount + bCount) % mod;
            }
        }

        return (int) (cCount % mod);
    }
 
    //follow up question:- write a code generic , and for b^i, d^j, a^k

    //gfg:- Count Palindromic Subsequences :- https://practice.geeksforgeeks.org/problems/count-palindromic-subsequences/1

    int mod = (int)1e9+7;
    long count(String str,int i,int j,long[][] dp)
    {
        if(i>=j){
            return dp[i][j] = (i==j?1:0);
        }

        if(dp[i][j]!=-1){
            return dp[i][j];
        }

        long common = count(str,i+1,j-1,dp);
        long excludeFirst = count(str,i,j-1,dp);
        long excludeLast = count(str,i+1,j,dp);

        if(str.charAt(i) == str.charAt(j)){
            dp[i][j] = (excludeLast + excludeFirst +1 ) % mod;
        }else{
            dp[i][j] = (excludeLast + excludeFirst -common + mod ) % mod;
        }

        return dp[i][j];
    }
    
    long countPS(String str)
    {
        int n = str.length();
        long dp[][] = new long[n][n];

        for(long[] d:dp) Arrays.fill(d,-1);

        return count(str,0,n-1,dp);
    }

    // tabulation gap strategy

    //44. Wildcard Matching :- https://leetcode.com/problems/wildcard-matching/

    public String removeStars(String str) {
        if (str.length() == 0)
            return "";

        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(0));

        int i = 1;
        while (i < str.length()) {
            while (i < str.length() && sb.charAt(sb.length() - 1) == '*' && str.charAt(i) == '*')
                i++;

            if (i < str.length())
                sb.append(str.charAt(i));
            i++;
        }

        return sb.toString();
    }

    public int Match(String s,String p,int n,int m,int[][] dp){
        if (n == 0 || m == 0) {
            if (n == 0 && m == 0)
                return dp[n][m] = 1;
            else if (m == 1 && p.charAt(m - 1) == '*')
                return dp[n][m] = 1;
            else
                return dp[n][m] = 0;
        }

        if(dp[n][m] != -1){
            return dp[n][m];
        }

        char ch1 = s.charAt(n-1);
        char ch2 = p.charAt(m-1);

        if(ch1 == ch2 || ch2 == '?'){
            return  dp[n][m] = Match(s,p,n-1,m-1,dp);
        }else if(ch2 == '*'){
            boolean res = false;
            res = res || Match(s,p,n-1,m,dp)==1;
            res = res || Match(s,p,n,m-1,dp)==1;
           return  dp[n][m] = res?1:0;
        }else{
             return dp[n][m] = 0;
        }

    }

    public boolean isMatch(String s, String p) {
        p = removeStars(p);
          int n = s.length(), m = p.length();

        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        return Match(s, p, n, m, dp) == 1;
    }

    //132. Palindrome Partitioning II
    
    public int minCut_memo(String s,int si,boolean[][] isPalin,int[] dp){
        if(isPalin[si][s.length()-1]) return dp[si] = 0;

        if(dp[si] !=-1){
            return dp[si];
        }
        int minAns =(int)1e9;
        for(int cut = si ; cut<s.length();cut++){
            if(isPalin[si][cut]){
                 minAns = Math.min(minAns,minCut_memo(s, cut+1, isPalin, dp)+1);
            }
           
        }

        return dp[si]=minAns;
    }
    
    public int minCut(String s) {
        int n = s.length();
        boolean[][] isPalin = new boolean[n][n];

        for(int gap=0;gap<n;gap++){
            for(int i=0,j=gap;j<n;j++,i++){
                if(gap==0){
                    isPalin[i][j] = true;
                }else if(gap == 1 && s.charAt(i) == s.charAt(j)){
                    isPalin[i][j] = true;
                }else{
                    isPalin[i][j] = s.charAt(i) == s.charAt(j) && isPalin[i+1][j-1];
                }
            }
        }

        int[] dp = new int[n+1];
        Arrays.fill(dp,-1);

        return minCut_memo(s,0,isPalin,dp);
    }
    
    //10. Regular Expression Matching :- https://leetcode.com/problems/regular-expression-matching/

    public int Match2(String s,String p,int n,int m,int[][] dp){
        if(n==0 && m==0){
            return dp[n][m] = 1;
        }
        if( m==0){
            return dp[n][m] = 0;
        }

        if(dp[n][m]!=-1) return dp[n][m];

        char ch1 = n > 0 ?s.charAt(n-1):'$';
        char ch2 = p.charAt(m-1);

        if(ch1!='$' && ((ch1 == ch2) || (ch2 == '.')) ){
            return dp[n][m] = Match2(s, p, n-1, m-1, dp);
        }else if(ch2=='*'){
            boolean res = false;
            if(m>1 && n>0 && ((p.charAt(m-2)=='.')||(s.charAt(n-1)==p.charAt(m-2)))){
                res = res || Match2(s, p, n-1, m, dp) == 1;
            }

            res = res || Match2(s, p, n, m-2, dp) == 1;

            return dp[n][m] = res ?1:0;
        }else{
           return  dp[n][m] = 0;
        }
    }

    public boolean isMatch2(String s, String p) {
        p = removeStars(p);
        int n=s.length(),m=p.length();

        int[][] dp = new int[n+1][m+1];

        for (int[] d : dp)
        Arrays.fill(d, -1);

        return Match2(s, p, n, m, dp) == 1;
    }

    public static void main(String[] args) {
        //longestPalindromeSubseq("abcchcbj"); 
         //System.out.println( longestCommonSubsequence("abcde", "ace"));
          //longestPalindrome("bbbab");
     
      
    }
}
