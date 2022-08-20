import java.util.*;

public class array{

    //53. Maximum Subarray :- Kadane Algorithm
    public int maxSubArray(int[] nums) {
        // int max =0;
        int oSum = -(int)1e9;
        int cSum= 0;
        for(int ele : nums){
            if(cSum < 0){
                cSum = ele;
            }
            cSum = cSum + ele;
            oSum = Math.max(oSum,cSum);
        }
        return oSum;
    }

    //121. Best Time to Buy and Sell Stock (Single)
     public int maxProfit(int[] prices) {
        int res = 0;
        int min = prices[0];
        
        for(int ele : prices){
            min = Math.min(ele,min);
            int stock = ele - min;
            res = Math.max(res,stock);
        }
        
        return res;
    }
    
    //188. Best Time to Buy and Sell Stock IV :- K Transactions 
    public int maxProfit(int k, int[] prices) {
        int[][] dp = new int[k][prices.length - 1];

        for(int t = 1 ; t <=k ; t++){
            int max = Integer.MIN_VALUE;
            for(int d = 1 ; d < prices.length ; d++){
                if(dp[t-1][d-1] - prices[d-1] > max){
                    max = dp[t-1][d-1] - prices[d-1];
                }

                if(dp[t][d-1] < max + prices[d]){
                    dp[t][d] = max + prices[d];
                }else{
                    dp[t][d] = dp[t][d-1];
                }
            }
        }

        return dp[k][prices.length-1];
    }
    
    //42. Trapping Rain Water
    public int trap(int[] height) {
        int n = height.length;
        int count = 0;
        int i = 0 ;
        int j = n-1;
        int lmax = height[i];
        int rmax = height[j];
        while(i<j){
           lmax = Math.max(lmax,height[i]);
           rmax = Math.max(rmax,height[j]);
           
            if(lmax < rmax){
                count += lmax - height[i];
                i++;
            }else{
                 count += rmax - height[j];
                j--;
            }
            
        }
        return count;
    }
    
    // Chocholate distribution :- 135. Candy
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] left = new int[n];
        int[] right = new int[n];

        Arrays.fill(left,1);
        Arrays.fill(left,1);
        int count = 0 ;
        for(int i = 1 ; i < n ; i++){
            if(ratings[i-1] < ratings[i]){
                left[i] = left[i-1] + 1;
            }
        }

        for(int i = n-2 ; i >=0 ; i--){
            if(ratings[i] > ratings[i+1]){
                right[i] = right[i+1] + 1;
            }
        }

        for(int i = 0 ; i < n ; i++){
            count += Math.max(left[i] , right[i]);
        }

        return count;

    }
    
    //
    public int minFlips(int a, int b, int c) {
        String aa = Integer.toBinaryString(a);
        String bb = Integer.toBinaryString(a);
        String cc = Integer.toBinaryString(a);
        int idx = cc.length();
        int count = 0;
        while(idx >=0 ){
            char ch = cc.charAt(idx);
            if(ch == 1){
                if(aa.charAt(idx) != 1 && bb.charAt(idx) != 1){
                    count++;
                }
            }else{
                if(aa.charAt(idx) == 1){
                    count++;
                }
                if(bb.charAt(idx) == 1){
                    count++;
                }
            }
            idx--;
        }

        return count;
    }
    
    public static void main(String[] args){

    }
}