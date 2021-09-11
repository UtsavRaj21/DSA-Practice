import java.util.*;

public class targetSet {

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

    public static int permutation_memo(int[] arr, int tar, int[] dp) {
        if (tar == 0) {
            return dp[tar] = 1;
        }

        if (dp[tar] != -1) {
            return dp[tar];
        }
        int count = 0;

        for (int ele = 0; ele < arr.length; ele++) {
            if (tar - arr[ele] >= 0) {
                count += permutation_memo(arr, tar - arr[ele], dp);
            }
        }

        return dp[tar] = count;
    }

    public static int permutation_tabu(int[] arr, int Tar, int[] dp) {
        for (int tar = 0; tar <= Tar; tar++) {
            if (tar == 0) {
                dp[tar] = 1;
                continue;
            }
            int count = 0;

            for (int ele = 0; ele < arr.length; ele++) {
                if (tar - arr[ele] >= 0) {
                    count += dp[tar - arr[ele]]; // permutation_memo(arr, tar - arr[ele], dp);
                }
            }

            dp[tar] = count;
        }

        return dp[Tar];
    }

    public static int combination_memo(int[] arr, int tar, int n, int[][] dp) {
        if (tar == 0) {
            return dp[n][tar] = 1;
        }

        if (dp[n][tar] != -1)
            return dp[n][tar];
        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (tar - arr[i] >= 0) {
                count += combination_memo(arr, tar - arr[i], i + 1, dp);
            }
        }

        return dp[n][tar] = count;
    }

    public static int combination_tabu(int[] arr, int Tar) {
        int[] dp = new int[Tar + 1];
        dp[0] = 1;
        for (int ele : arr) {
            for (int tar = 0; tar <= Tar; tar++) {
                if (tar - ele >= 0) {
                    dp[tar] += dp[tar - ele];
                }
            }
        }

        display(dp);
        return dp[Tar];
    }

    public static void coinset() {
        int[] arr = { 2, 3, 5, 7 };
        int tar = 10;
        int n = arr.length;
        // int dp[] = new int[tar+1];
        // Arrays.fill(dp,-1);
        // int ans = permutation_memo(arr,tar,dp);
        // int ans = permutation_tabu(arr,tar,dp);
        int[][] dp = new int[n][tar + 1];
        int ans = combination_memo(arr, tar, n, dp);

        display2D(dp);
        System.out.println(ans);
    }

    // 322. Coin Change

    public static int change_memo(int[] coins, int amount, int[] dp) {
        if (amount == 0) {
            return dp[amount] = 0;
        }

        if (dp[amount] != -1) {
            return dp[amount];
        }

        int count = (int) 1e9;

        for (int a = coins.length - 1; a >= 0; a--) {
            if (amount - coins[a] >= 0) {
                count = Math.min(count, change_memo(coins, amount - coins[a], dp));
            }
        }
        return dp[amount] = count + 1;
    }

    public static int change_tabu(int[] coins, int Amount, int[] dp) {
        for (int amount = 0; amount <= Amount; amount++) {
            if (amount == 0) {
                dp[amount] = 0;
                continue;
            }
            int count = (int) 1e9;

            for (int a = coins.length - 1; a >= 0; a--) {
                if (amount - coins[a] >= 0) {
                    count = Math.min(count, change_memo(coins, amount - coins[a], dp));
                }
            }
            dp[amount] = count + 1;
        }
        return dp[Amount];
    }

    public static int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);

        int ans = change_memo(coins, amount, dp);

        return ans == 1000000001 ? -1 : ans;
    }

    // 518. Coin Change 2

    // https://www.geeksforgeeks.org/find-number-of-solutions-of-a-linear-equation-of-n-variables/

    // 377. Combination Sum IV

    public static int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        Arrays.fill(dp, -1);
        // Arrays.sort(nums);
        return combination(nums, target, dp);
    }

    public static int combination(int[] nums, int tar, int[] dp) {
        if (tar == 0) {
            return dp[tar] = 1;
        }

        if (dp[tar] != -1) {
            return dp[tar];
        }

        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (tar - nums[i] >= 0) {
                count += combination(nums, tar - nums[i], dp);
            }

        }
        return dp[tar] = count;
    }

    public static int targetSum_memo(int[] arr, int tar, int n, int[][] dp) {
        if (tar == 0 || n == 0)
            return dp[n][tar] = (tar == 0 ? 1 : 0);

        if (dp[n][tar] != -1)
            return dp[n][tar];
        int count = 0;

        if (tar - arr[n - 1] >= 0) {
            count += targetSum_memo(arr, tar - arr[n - 1], n - 1, dp);
        }
        count += targetSum_memo(arr, tar, n - 1, dp);

        return dp[n][tar] = count;

    }

    public static int targetSum_tabu(int[] arr, int Tar, int N, int[][] dp) {
        for (int n = 0; n <= N; n++) {
            for (int tar = 0; tar <= Tar; tar++) {
                if (tar == 0 || n == 0) {
                    dp[n][tar] = (tar == 0 ? 1 : 0);
                    continue;
                }

                int count = 0;

                if (tar - arr[n - 1] >= 0) {
                    count += dp[n - 1][tar - arr[n - 1]]; // targetSum_memo(arr, tar - arr[n-1],n-1, dp);
                }
                count += dp[n - 1][tar];// targetSum_memo(arr, tar,n-1, dp);

                dp[n][tar] = count;
            }
        }
        return dp[N][Tar];

    }

    public static void targetSum() {
        int[] arr = { 2, 3, 5, 7 };
        int tar = 10;
        int n = arr.length;

        int[][] dp = new int[n + 1][tar + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        // int ans = targetSum_memo(arr,tar,n,dp);
        int ans = targetSum_tabu(arr, tar, n, dp);

        display2D(dp);
        System.out.println(ans);
    }

    // 0 - 1 Knapsack Problem

    public static int knapSack(int w, int wt[], int val[], int n, int[][] dp) {
        if (w == 0 || n == 0) {
            return dp[n][w] = 0;
        }
        if (dp[n][w] != -1)
            return dp[n][w];

        int maxAns = knapSack(w, wt, val, n - 1, dp);

        if (w - wt[n - 1] >= 0) {
            maxAns = Math.max(maxAns, knapSack(w - wt[n - 1], wt, val, n - 1, dp) + val[n - 1]);
        }

        return dp[n][w] = maxAns;
    }

    public static int knapSack(int w, int wt[], int val[], int n) {
        int[][] dp = new int[n + 1][w + 1];

        for (int[] d : dp)
            Arrays.fill(d, -1);

        return knapSack(w, wt, val, n, dp);
    }

    // Knapsack with Duplicate Items

    public static int knapSack(int N, int W, int val[], int wt[]) // two array dp
    {
        int[][] dp = new int[N + 1][W + 1];
        for (int n = 0; n <= N; n++) {
            for (int w = 0; w <= W; w++) {
                if (w == 0 || n == 0) {
                    dp[n][w] = 0;
                    continue;
                }

                int maxAns = dp[n - 1][w];

                if (w - wt[n - 1] >= 0) {
                    maxAns = Math.max(maxAns, dp[n][w - wt[n - 1]] + val[n - 1]);// knapSack(w-wt[n-1],wt,val,n,dp) +
                                                                                 // val[n-1]);
                }

                dp[n][w] = maxAns;
            }
        }

        return dp[N][W];
    }

    public static int knapSack2(int N, int W, int val[], int wt[]) { // one array dp
        int[] dp = new int[W + 1];
        for (int w = 1; w <= W; w++) {
            for (int i = 0; i < N; i++) {
                if (w - wt[i] >= 0)
                    dp[w] = Math.max(dp[w], dp[w - wt[i]] + val[i]);
            }
        }

        return dp[W];
    }

    // 416. Partition Equal Subset Sum :-
    // https://leetcode.com/problems/partition-equal-subset-sum/

    public static int canPartition(int[] nums, int tar, int n, int[][] dp) {
        if (n == 0 || tar == 0) {
            return dp[n][tar] = (tar == 0 ? 1 : 0);
        }
        if (dp[n][tar] != -1)
            return dp[n][tar];
        boolean rev = false;
        if (tar - nums[n - 1] >= 0) {
            rev = rev || canPartition(nums, tar - nums[n - 1], n - 1, dp) == 1;
        }
        rev = rev || canPartition(nums, tar, n - 1, dp) == 1;

        return dp[n][tar] = rev ? 1 : 0;
    }

    public static boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int ele : nums)
            sum += ele;

        if (n == 0 || sum % 2 != 0)
            return false;
        int dp[][] = new int[n + 1][(sum / 2) + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        return canPartition(nums, sum / 2, n, dp) == 1;

    }

    public int findTargetSumWays(int[] nums, int tar, int n, int[][] dp, int originalSum) {
        if (n == 0) {
            return dp[n][originalSum] = (tar == originalSum ? 1 : 0);
        }

        if (dp[n][originalSum] != -1)
            return dp[n][originalSum];

        int count = 0;

        count += findTargetSumWays(nums, tar, n - 1, dp, originalSum - nums[n - 1]);

        count += findTargetSumWays(nums, tar, n - 1, dp, originalSum + nums[n - 1]);

        return dp[n][originalSum] = count;
    }

    public int findTargetSumWays(int[] nums, int tar) {
        int n = nums.length;
        int sum = 0;
        for (int ele : nums)
            sum += ele;

        if (n == 0)
            return -1;
        if (sum < tar || tar < -sum)
            return 0;

        int dp[][] = new int[n + 1][2 * sum + 2];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        return findTargetSumWays(nums, sum + tar, n, dp, sum);
    }

    public static void main(String[] args) {
        // coinset();
        targetSum();
    }
}
