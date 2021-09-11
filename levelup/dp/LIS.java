import java.util.*;

public class LIS {

    //300. Longest Increasing Subsequence

   // LIS -> Left To right
   public static int[] LIS_LR(int[] nums) {
    int n = nums.length;
    int[] dp = new int[n];

    int maxLen = 0;
    for (int i = 0; i < n; i++) {
        dp[i] = 1;
        for (int j = i - 1; j >= 0; j--) {
            if (nums[i] > nums[j]) {
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        maxLen = Math.max(maxLen, dp[i]);
    }

    return dp;
   }

    // LIS -> Right to Left === LDS
    public static int[] LIS_RL(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];

        int maxLen = 0;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }

        return dp;
    }
    
    //Longest Bitonic subsequence 
    public static int LongestBitonicSequence(int[] nums) {
        int[] LIS = LIS_LR(nums);
        int[] LDS = LIS_RL(nums);

        int n = nums.length;
        int max=0;
        for(int i=0;i<n;i++){
            max = Math.max(max, LIS[i]+LDS[i]-1);
        }

        return max;
    }
    
    // LDS -> left to Right
    public static int[] LDS_LR(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];

        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] < nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }

        return dp;
    }

    // LDS -> right to left === LIS
    public static int[] LDS_RL(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];

        int maxLen = 0;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (nums[i] < nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }

        return dp;
    }

     // https://practice.geeksforgeeks.org/problems/maximum-sum-increasing-subsequence4749/1
    public static int maxSumIS(int arr[], int n) {
        if(n==0) return 0;
	    
	    int maxAns = 0;
	    
	    int[] dp = new int[n];
	    
	    for(int i=0;i<n;i++){
	        dp[i] = arr[i];
	        for(int j=i-1;j>=0;j--){
	            if(arr[j] < arr[i]){
	                dp[i] = Math.max(dp[i],dp[j]+arr[i]);
	            }
	        }
	        
	        maxAns = Math.max(dp[i] , maxAns);
	    }
	    return maxAns;
    }

    // https://practice.geeksforgeeks.org/problems/maximum-sum-bitonic-subsequence1857/1
    public static int[] maxSumBS_LIS(int arr[], int n) {
        if(n==0) return new int[0];
	    
	    int maxAns = 0;
	    
	    int[] dp = new int[n];
	    
	    for(int i=0;i<n;i++){
	        dp[i] = arr[i];
	        for(int j=i-1;j>=0;j--){
	            if(arr[j] < arr[i]){
	                dp[i] = Math.max(dp[i],dp[j]+arr[i]);
	            }
	        }
	        
	        maxAns = Math.max(dp[i] , maxAns);
	    }

        return dp;
    }
   
    public static int[] maxSumBS_LDS(int arr[], int n) {
        if(n==0) return new int[0];
	    
	    int maxAns = 0;
	    
	    int[] dp = new int[n];
	    
	    for(int i=n-1;i>=0;i--){
	        dp[i] = arr[i];
	        for(int j=i+1;j<n;j++){
	            if(arr[j] < arr[i]){
	                dp[i] = Math.max(dp[i],dp[j]+arr[i]);
	            }
	        }
	        
	        maxAns = Math.max(dp[i] , maxAns);
	    }

        return dp;
    }
    
    public static int maxSumBS(int arr[], int n) {
        int[] LISumSeq = maxSumBS_LIS(arr,n);
        int[] LDSumSeq = maxSumBS_LDS(arr,n);

        int maxAns=0;
        for(int i=0;i<n;i++){
            maxAns = Math.max(maxAns , LISumSeq[i]+LDSumSeq[i]-arr[i]);
        }

        return maxAns;
    }
    
    //gfg :- Building Bridges
   
    public static int MaximumNonOverlapingBridges(int[][] points) {
        int n = points.length;
        int[] dp=new int[n];
       Arrays.sort(points,(a,b)->{
           return a[0]-b[0];
       });

       int maxLen=0;
       for(int i = 0 ; i<n;i++){
           dp[i] = 1;
           for(int j=i-1;j>=0;j--){
               if(points[j][1]<=points[i][1]){
                   dp[i] = Math.max(dp[i],dp[j]+1);
               }
           }
           maxLen = Math.max(maxLen, dp[i]);
       }

        return maxLen;

    }

    // minimum Number of deletion to be performed to make array sorted (array
    // contain -1e7 <= ele <= 1e7)

    public static int minDeletion(int[] arr) {
       int n = arr.length;
       int[] dp=new int[n];

       int maxLen=0;
       for(int i=0;i<n;i++){
           dp[i] = 1;
           for(int j=i-1;j>=0;j--){
               if(arr[j] < arr[i]){
                   dp[i]= Math.max(dp[i],dp[j]+1);
               }
           }
           maxLen = Math.max(maxLen, dp[i]);
       }

       return n-maxLen;
    }

    public static int LIS_Rec(int[] arr, int ei, int[] dp) {
        if (dp[ei] != 0)
            return dp[ei];

        int maxLen = 1;
        for (int i = ei - 1; i >= 0; i--) {
            if (arr[i] < arr[ei]) {
                maxLen = Math.max(maxLen, LIS_Rec(arr, i, dp) + 1);
            }
        }

        return dp[ei] = maxLen;
    }

    public static int LIS_Rec(int[] arr) {
        int maxLen = 1, n = arr.length;
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            maxLen = Math.max(maxLen, LIS_Rec(arr, i, dp));
        }

        return maxLen;
    }
    
    public static void LIS_RiverseEngi(int[] arr){
        int[] lis = LIS_LR(arr);
        int n = arr.length;
        int maxEle = 0,num = 1;
        for(int ele =0;ele<n;ele++){
            if(maxEle <lis[ele] ){
                maxEle = lis[ele] ;
                num = ele;
            }
        }

        int[] ans = new int[maxEle];
        ans[0] = arr[num];
        int m = maxEle,idx=1;

        while(m-->0){
            for(int i = 0;i<n;i++){
                if(lis[i] == m && arr[i] < arr[num]){
                    num = i;
                    ans[idx++] = arr[i];
                    break;
                }
            }
        }

        for(int ele : ans){
            System.out.print(ele+" ");
        }
    }

    //673. Number of Longest Increasing Subsequence :- https://leetcode.com/problems/number-of-longest-increasing-subsequence/
    public static int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int[] count = new int[n];
        int maxLen = 0,maxCount=0;

        for(int i=0;i<n;i++){
            dp[i] = 1;
            count[i] = 1;
            for(int j=i-1;j>=0;j--){
                if(nums[j] < nums[i]){
                    if(dp[j]+1 > dp[i]){
                        dp[i] = dp[j]+1;
                        count[i] = count[j];
                    }else if(dp[j]+1 == dp[i]){
                        count[i]+=count[j];
                    }
                }
            }

            if(maxLen<dp[i]){
                maxLen = dp[i];
                maxCount = count[i];
            }else if(maxLen == dp[i]){
                maxCount+=count[i];
            }
        }

        return maxCount;
    }
    public static void main(String[] args) {
        int[][] points = {{8,1},{1,2},{4,3},{3,4},{5,5},{2,6},{6,7},{7,8}};
        int arr[] = {0,8,4,12,2,10,6,14,1,9,5,13,3,11,7,15,14};
        LIS_RiverseEngi(arr);
      
        // System.out.println( MaximumNonOverlapingBridges(points));
    }
}
