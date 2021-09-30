import java.util.*;

public class faang1 {
   
    //264. Ugly Number II :- https://leetcode.com/problems/ugly-number-ii/
    
    public int nthUglyNumber(int n) {
        int[] dp = new int[n+1];
        dp[1] = 1;

        int p2 =1;
        int p3 =1;
        int p5 =1;

        for(int i=2;i<=n ;i++){
            int min2 = 2* dp[p2];
            int min3 = 3* dp[p3];
            int min5 = 5* dp[p5];

            int min = Math.min(min2,Math.min(min3,min5));

            if(min == min2) p2++;
            if(min == min3) p3++;
            if(min == min5) p5++;

            dp[i] = min;
        }

        return dp[n+1];
    }

    //313. Super Ugly Number :- https://leetcode.com/problems/super-ugly-number/
    
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] arr = new int[primes.length];
        Arrays.fill(arr,1);

        int[] dp = new int[n+1];
        dp[1] = 1;
        for(int i = 2 ; i <= n ; i++){
            int min = 2147483647;
            for(int j = 0 ; j < primes.length;j++){
                min = Math.min(min,primes[j] * dp[arr[j]]);
            }
            dp[i] = min;
            for(int j = 0 ; j < primes.length;j++){
                if(min == primes[j] * dp[arr[j]]){
                    arr[j]++;
                }
            }
        }

        return dp[n];
    }

   // Box Stacking :- https://practice.geeksforgeeks.org/problems/box-stacking/1       // imp

   static class Box implements Comparable<Box>{
       int h,w,d,area;

       public Box(int h,int w,int d){
           this.h = h;
           this.w = w;
           this.d = d;
       }

       @Override
       public int compareTo(Box o){
           return o.area - this.area;
       }
   }

   public static int maxHeight(int[] height, int[] width, int[] length, int n)  { // o(n^2)
       Box[] rot = new Box[n*3];

       // rotate dimension
       for(int i = 0 ; i < n ; i++){
           rot[i*3] = new Box(height[i], Math.max(width[i], length[i]), Math.min(width[i], length[i]));
           rot[i*3+1] = new Box(length[i], Math.max(width[i], height[i]), Math.min(width[i], height[i]));
           rot[i*3+2] = new Box(width[i], Math.max(height[i], length[i]), Math.min(height[i], length[i]));
       }

       //store area
       for(int i = 0 ; i < rot.length ; i++){
           rot[i].area = rot[i].w * rot[i].d;
       }

       //sort 

       Arrays.sort(rot);

       //LDS

       int max = 0;

       int[] dp = new int[n*3];

       for(int i = 0 ; i < n*3;i++){
            dp[i]= 0;
            int m=0;
            int val = rot[i].h;
            Box prev = rot[i];
            for(int j = 0 ; j < i ; j++){
                if(rot[j].d > prev.d && rot[j].w > prev.w){
                    m = Math.max(m,dp[j]);
                }
            }
            dp[i] = m + val;
            max = Math.max(max, dp[i]);

       }
       return max;
   }

}
