import java.util.*;

public class basic {
    /*
     * 1.faith 2.recursive tree 3.recursion code 4.memorization 5.observation
     * 6.tabulation 7.optimization
     */

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

    // fibonacci series

    // F(n) = F(n-1)+F(n-2);

    public static int fib_memo(int n, int[] dp) {
        if (n <= 1)
            return dp[n] = n;

        if (dp[n] != 0) {
            return dp[n];
        }

        int ans = fib_memo(n - 1, dp) + fib_memo(n - 2, dp);
        return dp[n] = ans;
    }

    public static int fib_tabu(int N, int[] dp) {
        for (int n = 0; n < N; n++) {
            if (n <= 1) {
                dp[n] = n;
                continue;
            }
            if (dp[n] != 0) {
                continue;

            }

            int ans = dp[n - 1] + dp[n - 2];

        }
        return dp[N];
    }

    public static int fibo_opti(int n) {
        int a = 0, b = 1;
        for (int i = 0; i < n; i++) {
            int c = a + b;
            a = b;
            b = c;
        }

        return a;
    }

    // total no of paths from all given direction
    public static int mazePath_memo(int sr, int sc, int er, int ec, int[][] dir, int[][] dp) {
        int n = dp.length, m = dp[0].length;
        if (sr == er && sc == ec) {
            dp[sr][sc] = 1;
            return dp[sr][sc];
        }

        if (dp[sr][sc] != 0) {
            return dp[sr][sc];
        }

        int count = 0;

        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m) {
                count += mazePath_memo(r, c, er, ec, dir, dp);
            }
        }

        return dp[sr][sc] = count;
    }

    public static int mazePath_tabu(int SR, int SC, int er, int ec, int[][] dir, int[][] dp) {
        int n = dp.length, m = dp[0].length;
        for (int sr = er - 1; sr >= SR; sr--) {
            for (int sc = ec - 1; sc >= SC; sc--) {
                if (sr == er && sc == ec) {
                    dp[sr][sc] = 1;
                    continue;
                }

                int count = 0;

                for (int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];

                    if (r >= 0 && c >= 0 && r < er && c < ec) {
                        count += dp[r][c];// mazePath_memo(r,c,er,ec,dir,dp);
                    }
                }
                dp[sr][sc] = count;
            }
        }

        return dp[SR][SC];
    }

    public static int mazePathJump_tabu(int SR, int SC, int er, int ec, int[][] dir, int[][] dp) {
        int n = dp.length, m = dp[0].length;
        for (int sr = er; sr >= SR; sr--) {
            for (int sc = ec; sc >= SC; sc--) {
                if (sr == er && sc == ec) {
                    dp[sr][sc] = 1;
                    continue;
                }

                int count = 0;

                for (int[] d : dir) {
                    int r = sr + d[0];
                    int c = sc + d[1];

                    while (r <= er && c <= ec) {
                        count += dp[r][c];// mazePath_memo(r,c,er,ec,dir,dp);
                        r += d[0];
                        c += d[1];
                    }
                }
                dp[sr][sc] = count;
                // System.out.println(dp[sr][sc]);
            }
        }

        return dp[SR][SC];
    }

    // goldmine
    // :-https://practice.geeksforgeeks.org/problems/gold-mine-problem2608/1

    public static int goldMine_memo_(int sr, int sc, int[][] mat, int[][] dir, int[][] dp) {
        int n = mat.length, m = mat[0].length;
        if (sc == m - 1) {
            return dp[sr][sc] = mat[sr][sc];
        }

        if (dp[sr][sc] != 0)
            return dp[sr][sc];
        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m) {
                count = Math.max(count, goldMine_memo_(r, c, mat, dir, dp));
            }
        }

        return dp[sr][sc] = count + mat[sr][sc];
    }

    public static int goldMine_memo(int n, int m, int[][] mat) {
        int[][] dir = { { -1, 1 }, { 1, 1 }, { 0, 1 } };
        int[][] dp = new int[n][m];

        int maxGold = 0;
        for (int r = 0; r < n; r++) {
            maxGold = Math.max(maxGold, goldMine_memo_(r, 0, mat, dir, dp));
        }

        return maxGold;
    }

    public static int goldMine_Tabu(int SR, int SC, int[][] mat, int[][] dir, int[][] dp) {
        for (int sc = mat[0].length - 1; sc >= SC; sc--) {
            for (int sr = mat.length - 1; sr >= SR; sr--) {
                if (sc == mat[0].length - 1) {
                    dp[sr][sc] = mat[sr][sc];
                    continue;
                }

                int maxGold = 0;
                for (int[] d : dir) {
                    int r = sr + d[0];
                    int c = sc + d[1];

                    if (r >= 0 && c >= 0 && r < mat.length && c < mat[0].length)
                        maxGold = Math.max(maxGold, dp[r][c] + mat[sr][sc]);
                }

                dp[sr][sc] = maxGold;
            }
        }

        int maxGold = 0;
        for (int r = 0; r < mat.length; r++) {
            maxGold = Math.max(maxGold, dp[r][0]);
        }

        return maxGold;
    }

    // 1219. Path with Maximum Gold :https://leetcode.com/problems/path-with-maximum-gold/

    public int getMaximumGold(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
        boolean[][] vis = new boolean[n][m];
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] != 0) {
                    int s = get(i, j, n, m, dir, vis, grid);
                    System.out.println(s);
                    max = Math.max(max, s);
                }

            }
        }

        return max;
    }

    public static int get(int sr, int sc, int dr, int dc, int[][] dirs, boolean[][] vis, int[][] grid) {
        int res = grid[sr][sc];
        vis[sr][sc] = true;
        int max = 0;
        for (int i = 0; i < dirs.length; i++) {
            int r = sr + dirs[i][0];
            int c = sc + dirs[i][1];
            if (r >= 0 && c >= 0 && r < dr && c < dc && !vis[r][c] && grid[r][c] != 0) {
                max = Math.max(max, get(r, c, dr, dc, dirs, vis, grid));
            }
        }
        vis[sr][sc] = false;
        return res + max;
    }

     //  70. Climbing Stairs

    public int fibo_memo(int n, int[] dp){
        if (n <= 1) {
                return dp[n] = 1;
            }
    
            if (dp[n] != -1)
                return dp[n];
    
            int ans = fibo_memo(n - 1, dp) + fibo_memo(n - 2, dp);
            return dp[n] = ans;
        }
        
        public int fibo_DP(int N, int[] dp){
            for(int n=0;n<=N;n++){
            if (n <= 1) {
                dp[n] = 1;
                continue;
            }
    
            // if (dp[n] != -1)
            //     return dp[n];
    
            int ans = dp[n - 1] + dp[n - 2];// fibo_memo(n - 1, dp) + fibo_memo(n - 2, dp);
                 dp[n] = ans;
            }
              return  dp[N];
        }

        public int climbStairs_opti(int n) {
            int a = 1, b = 1;
            for (int i = 0; i < n; i++) {
                int sum = a + b;
                a = b;
                b = sum;
            }
    
            return a;
        }

        public int climbStairs(int n) {
            int[] dp = new int[n + 1];
            Arrays.fill(dp, -1);
            //fibo_memo(n,dp);
            fibo_DP(n,dp);
                return dp[n];
        }

        //746. Min Cost Climbing Stairs :- https://leetcode.com/problems/min-cost-climbing-stairs/

 
        
         public int ClimbingStairs_memo(int n,int[] dp,int[] cost){
             if(n<=1){
                  return dp[n] = cost[n];
                  
             }
             if (dp[n] != -1)
                return dp[n];
             
             int climb=Math.min(ClimbingStairs_memo(n-1,dp,cost),ClimbingStairs_memo(n-2,dp,cost)) ;
             if(n!=cost.length){
                 return dp[n] = climb+cost[n];
             }
             else{
                  return dp[n] = climb;
             }
                
         }
        
        public int ClimbingStairs_DP(int N,int[] dp,int[] cost){
            for(int n=0;n<=N;n++){
             if(n<=1){
                   dp[n] = cost[n];
                  continue;
             }
             // if (dp[n] != -1)
             //    return dp[n];
             
             int climb=Math.min(dp[n-1],dp[n-2]);  //Math.min(ClimbingStairs(n-1,dp,cost),ClimbingStairs(n-2,dp,cost)) 
             if(n!=cost.length){
                 dp[n] = climb+cost[n];
             }
             else{
                  dp[n] = climb;
             }
            }
            return dp[N];
         }

         public int ClimbingStairs_opti(int[] cost){
             int a = cost[0],b = cost[1];
             for(int i =2;i<=cost.length;i++){
                 int minCost = Math.min(a,b) + (i==cost.length?0:cost[i]);
                 a=b;
                 b=minCost;
             }

             return b;
         }

         public int minCostClimbingStairs(int[] cost) {
            int[] dp = new int[cost.length + 1];
            
            Arrays.fill(dp, -1);
            //ClimbingStairs(cost.length,dp,cost);
            ClimbingStairs_DP(cost.length,dp,cost);
            return dp[cost.length];  
        }
       
    

    public static void mazePath_Set() {
        int sr = 0, sc = 0, er = 2, ec = 2;
        int[][] dp = new int[er + 1][ec + 1];
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 1, 1 } };
        // System.out.println(mazePath_memo(sr, sc, er, ec, dir, dp));
        // System.out.println(mazePath_tabu(sr, sc, er, ec, dir, dp));
        System.out.println(mazePathJump_tabu(sr, sc, er, ec, dir, dp));

        display2D(dp);
    }

    // board

    public static int boardPath_memo(int sp,int ep,int[] dp){
        if(sp == ep){
            dp[sp] = 1;
            return dp[sp];
        }

        if(dp[sp]!=0) return dp[sp];
        int count =0;
        for(int dice=1;dice<=6 && sp+dice<=ep ; dice++){
            count+=boardPath_memo(dice+sp, ep, dp);
        }

        return dp[sp] = count;
    }

    public static int boardPath_tabu(int SP,int ep,int[] dp){
        for(int sp=ep;sp>=SP;sp--){
            if(sp == ep){
                dp[sp] = 1;
                continue;
            }
    
            int count =0;
            for(int dice=1;dice<=6 && sp+dice<=ep ; dice++){
                count+=dp[sp+dice];//boardPath_memo(dice+sp, ep, dp);
            }
    
            dp[sp] = count;
        }
        return dp[SP];
    }

    public static int boardPath_opti(int SP,int ep){

        LinkedList<Integer> l = new LinkedList<Integer>();
        l.addLast(1);
        l.addLast(1);
        for(int i = 2 ; i <= ep ; i++){
            if(i<=6){
                l.addLast(2*l.getLast());
            }else{
                l.addLast(2*l.getLast() - l.removeFirst());                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
            }
        }
        return l.getLast();
    }

    public static void board_path() {
        int sp = 0, ep = 10;
        int[] dp = new int[ep + 1];
        //System.out.println(boardPath_memo(sp, ep, dp));
         //System.out.println(boardPath_tabu(sp, ep, dp));
         System.out.println(boardPath_opti(sp, ep));
        // display(dp);
    }

    public static void main(String[] args) {
        //mazePath_Set();
         board_path() ;
}
}