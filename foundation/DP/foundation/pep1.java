import java.util.*;

public class pep1 {

    // Fibonacci-dp
    public static int fibbonaci(int n) {
        int a = 0;
        int b = 1;
        for (int i = 1; i <= n; i++) {
            int c = b + a;
            a = b;
            b = c;
        }
        System.out.println(a);
    }

    // Climb Stairs
    public static int climbStairs(int n) {
        int[] dp = new int[n + 1];
        if (n < 3)
            return n;
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        if (n == 3)
            return 4;
        dp[3] = 4;
        for (int i = 4; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[n - 3];
        }
        return dp[n];
    }

    // Climb Stairs With Variable Jumps
    public static void climb(int n, int[] arr) {
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            int val = arr[i];
            int sum = 0;
            int j = i + 1;
            while (val-- > 0 && j < n) {
                sum += dp[j++];
            }
            dp[i] = sum;
        }
        System.out.println(dp[0]);
    }

    // Climb Stairs With Minimum Moves
    public static void min(int n, int[] arr) {
        int dp[] = new int[n + 1];
        Arrays.fill(dp, n);
        dp[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 1; j <= arr[i] && i + j < n + 1; j++) {
                dp[i] = Math.min(dp[i], dp[i + j]);
            }
            dp[i] = dp[i] == n ? n : dp[i] + 1;
        }

        if (dp[0] != n) {
            System.out.println(dp[0]);
        } else {
            System.out.println("null");
        }

    }

    // Min Cost In Maze Traversal
    public static void MinCost(int n, int m, int[][] arr) {
        int[][] dp = new int[n][m];
        for (int j = m - 1; j >= 0; j--) {
            for (int i = n - 1; i >= 0; i--) {
                if (i == n - 1 && j == m - 1) {
                    dp[i][j] = arr[i][j];
                    continue;
                }
                int min = Integer.MAX_VALUE;
                if (i + 1 < n) {
                    min = Math.min(min, dp[i + 1][j]);
                }
                if (j + 1 < m) {
                    min = Math.min(min, dp[i][j + 1]);
                }
                dp[i][j] = min + arr[i][j];
            }
        }
        System.out.println(dp[0][0]);
    }

    // Goldmine
    public static void GoldMine(int n,int m,int[][] arr){
        int[][] dp = new int[n][m];
        int res = 0;
        for(int j = m-1 ; j>=0 ; j--){
            for(int i = n-1 ; i >= 0 ;i--){
                if(j == m-1){
                    dp[i][j] = arr[i][j];
                    continue;
                }
                int max = 0;
                if(i-1<=0){
                    max = Math.max(max,dp[i-1][j+1])
                }
                max = Math.max(max,dp[i][j+1]);
                if(i+1<n){
                    max = Math.max(max,dp[i+1][j+1]);
                }
                dp[i][j] = max + arr[i][j];
                if(j==0){
                    res = Math.max(res, dp[i][j]);
                }
            }
            
        }
        System.out.println(res);
    }

    // Target Sum Subsets - Dp
    public static void target(int n, int[] arr, int tar) {
        boolean[][] dp = new boolean[n + 1][tar + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = false;
                } else if (j == 0) {
                    dp[i][j] = true;
                } else {
                    if (dp[i - 1][j] == true) {
                        dp[i][j] = true;
                    }
                    int val = arr[i - 1];
                    if (j >= val) {
                        if (dp[i - 1][j - val] == true) {
                            dp[i][j] = true;
                        }
                    }
                }
            }
        }
        System.out.println(dp[n][tar]);

    }

    // Coin Change Combination
    public static void coinChange(int n, int[] arr, int amt) {
        int[] dp = new int[amt + 1];
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < dp.length; j++) {
                dp[j] += dp[j - arr[i]];
            }
        }
        System.out.println(dp[amt]);
    }

    // Coin Change Permutations
    public static void CoinPermutation(int n, int[] arr, int amt) {
        int[] dp = new int[amt + 1];
        dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                int coin = arr[j];
                if (i >= coin) {
                    dp[i] += dp[i = coin];
                }
            }
        }
        System.out.println(dp[amt]);
    }

    // Zero One Knapsack
    public static void knapsack01(int n, int[] val, int[] wt, int tar) {
        int[][] dp = new int[n + 1][tar + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (j >= wt[i - 1]) {
                    int rem = j - wt[i - 1];
                    if (dp[i - 1][rem] + val[i - 1] > dp[i - 1][j]) {
                        dp[i][j] = dp[i - 1][rem] + val[i - 1];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        System.out.println(dp[n][tar]);
    }

    // knapsack_unbounded
    public static void knapsack_unbounded(int n, int[] val, int[] wt, int tar) {
        int[] dp = new int[tar + 1];
        for (int i = 1; i < dp.length; i++) {
            int max = 0;
            for (int j = 0; j < wt.length; j++) {
                if (i > wt[j]) {
                    max = Math.max(max, dp[i - wt[j]] + val[j]);
                } else if (i == wt[j]) {
                    max = Math.max(max, val[j]);
                }
            }
            dp[i] = max;
        }
    }

    // Count Binary Strings
    public static void count(int n) {
        int[][] dp = new int[2][n + 1];
        dp[0][1] = 1;
        dp[1][1] = 1;
        for (int i = 2; i < n + 1; i++) {
            dp[0][i] = dp[1][i - 1];
            dp[1][i] = dp[1][i - 1] + dp[0][i - 1];
        }

        System.out.println(dp[0][n] + dp[1][n]);
    }

    // Arrange Buildings
    public static void Arrange(int n) {
        int[][] dp = new int[2][n + 1];

        dp[0][1] = 1;
        dp[1][1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[0][i] = dp[1][i - 1];
            dp[1][i] = dp[1][i - 1] + dp[0][i - 1];
        }

        int res = dp[0][n] + dp[1][n];
        System.out.println(res * res);
    }

    // Count Encodings
    public static void Encodings(String str) {
        int n = str.length();
        int[] dp = new int[n];
        if (str.charAt(0) != '0') {
            dp[0] = 1;
        } else {
            dp[0] = 0;
        }

        for (int i = 1; i < n; i++) {
            if (str.charAt(i) == '0' && str.charAt(i - 1) == '0') {
                dp[i] = 0;
            } else if (str.charAt(i) == '0' && str.charAt(i - 1) != '0') {
                if (str.charAt(i - 1) <= '2') {
                    dp[i] = i > 1 ? dp[i - 2] : 1;
                } else {
                    dp[i] = 0;
                }
            } else if (str.charAt(i) != '0' && str.charAt(i - 1) == '0') {
                dp[i] = dp[i - 1];
            } else {
                dp[i] += dp[i - 1];
                if (Integer.parseInt(str.substring(i - 1, i + 1)) <= 26) {
                    dp[i] += (i > 1 ? dp[i - 2] : 1);
                }
            }
        }

        System.out.println(dp[n - 1]);
    }

    // Count A+b+c+ Subsequences
    public static void Subsequences(String str) {
        int a = 0;
        int b = 0;
        int c = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'a') {
                a = 2 * a + 1;
            } else if (str.charAt(i) == 'b') {
                b = b * 2 + a;
            } else if (str.charAt(i) == 'c') {
                c = c * 2 + b;
            }
        }
        System.out.println(c);
    }

    // Maximum Sum Non Adjacent Elements
    public static void Maximum_Sum_Non_Adjacent(int n, int[] arr) {
        int inc = arr[0];
        int exc = 0;

        for (int i = 1; i < n; i++) {
            int ninc = exc + arr[i];
            int nexc = Math.max(inc, exc);

            inc = ninc;
            exc = nexc;
        }
        System.out.println(Math.max(inc, exc));

    }

    // Friends Pairing
    public static void Pairing(int n) {
        int[] dp = new int[n + 1];
        if (n < 3) {
            System.out.println(n);
        }
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + (i - 1) * dp[i - 2];
        }

        System.out.println(dp[n]);
    }

    // Paint House :- NO Consecutive color
    public static void PaintHouse(int n, int[] red, int[] blue, int[] green) {
        int r = red[0];
        int b = blue[0];
        int g = green[0];

        for (int i = 1; i < n; i++) {
            int nr = red[i] + Math.min(b, g);
            int nb = blue[i] + Math.min(r, g);
            int ng = green[i] + Math.min(b, r);

            r = nr;
            b = nb;
            g = ng;
        }
        System.out.println(Math.min(r, Math.min(b, g)));
    }

    // Paint House - Many Colors :- NO Consecutive color
    public static void PaintColorManyHouse(int n, int c, int[][] arr) {
        int small = Integer.MAX_VALUE;
        int nsmall = Integer.MAX_VALUE;
        int[][] dp = new int[n][c];

        for (int j = 0; j < c; j++) {
            dp[0][j] = arr[0][j];
            if (small > arr[0][j]) {
                nsmall = small;
                small = arr[0][j];
            } else if (nsmall > arr[0][j]) {
                nsmall = arr[0][j];
            }
        }

        // System.out.println(small);
        // System.out.println(nsmall);

        for (int i = 1; i < n; i++) {
            int s = Integer.MAX_VALUE;
            int ns = Integer.MAX_VALUE;
            for (int j = 0; j < c; j++) {
                int val = 0;
                if (dp[i - 1][j] == small) {
                    val = arr[i][j] + nsmall;
                } else {
                    val = arr[i][j] + small;
                }
                dp[i][j] = val;
                if (s > val) {
                    ns = s;
                    s = val;
                } else if (ns > val) {
                    ns = val;
                }
            }
            small = s;
            nsmall = ns;
        }

        System.out.println(small);
    }

    // Paint Fence :- not more than two consecutive fences have same colors.
    public static void PaintFence(int n, int c) {
        int same = c * 1;
        int diff = c * (c - 1);
        int sum = same + diff;

        for (int i = 3; i <= n; i++) {
            same = diff * 1;
            diff = sum * (c - 1);
            sum = same + diff;
        }

        System.out.println(sum);
    }

    // Partition Into Subsets
    public static long partitionKSubset(int n, int k) {
        long[][] dp = new long[k + 1][n + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (i == j) {
                    dp[i][j] = 1;
                } else if (j > i) {
                    dp[i][j] = dp[i - 1][j - 1] + (i) * dp[i][j - 1];
                }
            }
        }

        return dp[k][n];
    }

    // Buy And Sell Stocks - Infinite Transactions Allowed
    public static void InfiniteTransaction(int n, int[] arr) {
        int profit = 0;
        int sd = 0;
        int bd = 0;

        for (int i = 1; i < n; i++) {
            if (arr[i] >= arr[i - 1]) {
                sd++;
            } else {
                profit += arr[sd] - arr[bd];
                sd = bd = i;
            }
        }
        profit += arr[sd] - arr[bd];
        System.out.println(profit);
    }
    // ******************* BASS :-Buy And Sell Stocks
    // ********************************************

    // Buy And Sell Stocks - One Transaction Allowed
    public static void oneTransaction(int n, int[] arr) {
        int min = Integer.MAX_VALUE;
        int op = 0; // overall profit
        int tp = 0; // today profit

        for (int i = 0; i < n; i++) {
            min = Math.min(min, arr[i]);
            tp = arr[i] - min;
            op = Math.max(op, tp);
        }

        System.out.println(op);
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }

        // ************************ */
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = scn.nextInt();
            }

        }
        System.out.println();
    }
}
