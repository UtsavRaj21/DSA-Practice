import java.util.*;

public class dsaSheet1 {

    // Minimize the Heights II
    int getMinDiff(int[] arr, int n, int k) {

        Arrays.sort(arr);
        int ans = arr[0] - arr[n - 1];
        int min = arr[0] + k;
        int max = arr[n - 1] - k;

        for (int i = 1; i < n; i++) {
            if (arr[i] >= k) {
                int mi = Math.min(arr[i] - k, min);
                int ma = Math.max(arr[i - 1] + k, max);

                ans = Math.min(ans, ma - mi);
            }
        }
        return ans;
    }

    //
    static int minJumps(int[] arr){
        if(arr[0] = 0) return -1;
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp,n);
        for(int i= n-1 ; i >=0 ; i--){
            if(arr[i] == 0){
                continue;
            }else if(arr[i] >= n-i-1){
                dp[i] = 1;
            }else{
                int max = n;
                for(int j =1;j<=arr[i];i++){
                    max = Math.min(max,dp[i+j]);
                }
                dp[i] = max+1;
            }
        }
        return dp[0] == n ? -1 : dp[0];
    }

    

    public static void main(String[] args) {

    }
}
