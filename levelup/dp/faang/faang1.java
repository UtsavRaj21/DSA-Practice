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

   //Form a palindrome :- https://practice.geeksforgeeks.org/problems/form-a-palindrome1455/1#
   public static int countMin(String str)
   {
       int n = str.length();
       int[][] dp = new int[n][n];
       for(int[] d : dp) Arrays.fill(d,-1);

       int max =1;
        for(int gap = 0 ; gap<n;gap++){
            for(int i = 0 ,j = gap ; j<n;i++,j++){
                if(gap == 1){
                    dp[i][j] = 1;
                    
                }

                else if(gap == 1){
                    if(str.charAt(i) == str.charAt(j)){
                        dp[i][j] = 1;
                    }
                   
                }

                else if (str.charAt(i) == str.charAt(j)){
                    if(dp[i+1][j-1] !=-1){
                        dp[i][j] = dp[i+1][j-1] + 2;
                    }else{
                        dp[i][j] = 1;
                    }
                }else{

                }

                max = Math.max(max,dp[i][j]);
            }
       }

       return str.length() - max;
   }

   //Maximum Product Cutting | DP-36

   public static int maxProd(int n){
        int dp[] = new int[n+1];
        for(int i = 0 ; i <=n ;i++){
            if(i==0||i==1){
                dp[i] = 0;
                continue;
            }

            int max_val = 0;

            for(int j = 1 ; j <= i/2 ; j++){
                max_val = Math.max(max_val, Math.max(j * (i-j) , j * dp[i-j]));
            }
            dp[i] = max_val;
        }

        return dp[n];
        
    }

    //Optimal Strategy For A Game :- https://practice.geeksforgeeks.org/problems/optimal-strategy-for-a-game-1587115620/1

    static long countMaximum(int arr[], int n)
    {
        long[][] dp = new long[n][n];
        
        for(int gap = 0 ; gap<n;gap++){
            for(int i = 0 , j = gap ; j < n ; j++,i++){
                if(gap ==0){
                    dp[i][j] = arr[i];
                }else if(gap == 1){
                    dp[i][j] = Math.max(arr[i],arr[j]);
                }else{
                    long val1 = arr[i] + Math.min(dp[i+2][j],dp[i+1][j-1]);
                     long val2 = arr[j] + Math.min(dp[i][j-2],dp[i+1][j-1]);
                     
                     dp[i][j] = Math.max(val1,val2);
                }
            }
        }
        
        return dp[0][n-1];
    }

    //Mobile numeric keypad :- https://practice.geeksforgeeks.org/problems/mobile-numeric-keypad5456/1#

    public static long getCount(int N)
	{
        if(N==1) return 10;
		long[][] dp1 = new long[4][3];
        long[][] dp2 = new long[4][3];
        for(long[] a : dp1){
            Arrays.fill(a,1);
        }

        dp1[3][0] = 0;
        dp1[3][2] = 0;

        int[][] dir = {{0,0},{0,1},{0,-1},{1,0},{-1,0}};
        long sum = 0 ;
        for(int n = 1 ; n < N ; n++){
             sum = 0 ;
            for(int i = 0 ; i < 4; i++){
                for(int j = 0 ; j < 3 ; j++){
                    if(dp1[i][j] == 0) continue;
                    long s = 0;
                    for(int d = 0 ; d < 5;d++){
                        int r = i + dir[d][0];
                        int c = j + dir[d][1];

                        if( r >=0 && c>=0 && r<4 && c<3 && dp1[r][c] != 0){
                            s+=dp1[r][c];
                        }
                    }
                    dp2[i][j] = s;
                    sum+=s;
                }
            }
            for(int i = 0 ; i < 4; i++){
                for(int j = 0 ; j < 3 ; j++){
                    dp1[i][j] = dp2[i][j];
                }
            }
        }
        return sum;
	}
   
    //Find number of solutions of a linear equation of n variables :- https://www.geeksforgeeks.org/find-number-of-solutions-of-a-linear-equation-of-n-variables/
   
    public static int countSol(int coeff[], int n, int rhs)
    {
        int[] dp = new int[rhs+1];
        dp[0] = 1;

        for(int i =0 ;i < n;i++){
            for(int j = coeff[i];j<=rhs;j++){
                dp[j]+= dp[i-j];
            }
        }

        return dp[rhs];
    }
    
    //97. Interleaving String :- https://leetcode.com/problems/interleaving-string/
    public boolean leave(String s1, String s2, String s3,int i,int j,Boolean[][] dp){
        if(i == s1.length() && j == s2.length()){
            return true;
        }

        if(dp[i][j] != null){
            return dp[i][j];
        } 

        if ( i < s1.length() && s1.charAt(i) == s3.charAt(i+j)){
            Boolean f1 = leave(s1,s2,s3,i+1,j,dp);
            if(f1){
                dp[i][j] = true;
                return true;
            }
        }

        if ( j < s2.length() && s2.charAt(j) == s3.charAt(i+j)){
            Boolean f1 = leave(s1,s2,s3,i,j+1,dp);
            if(f1){
                dp[i][j] = true;
                return true;
            }
        }
         dp[i][j] =false;

        return false;


    }
    
    public boolean isInterleave(String s1, String s2, String s3) {
        if((s1.length() + s2.length() )!= s3.length()){
            return false;
        }

        Boolean[][] dp = new Boolean[s1.length()+1][s2.length()+1];

        return leave(s1,s2,s3,0,0,dp);
    }
   
    //688. Knight Probability in Chessboard

    public double knightProbability(int n, int k, int row, int column) {
        double[][] curr = new double[n][n];
        double[][] next = new double[n][n];

        curr[row][column] = 1;

        int[][] dir = {{-2,1},{-1,2},{2,1},{1,2},{2,-1},{1,-2},{-2,-1},{-1,-2}};

        for(int m = 0 ; m < k ; m++){
            for(int i = 0 ; i < n ; i++){
                for(int j = 0 ; j < n ; j++){
                    if(curr[i][j] != 0){

                        for(int d =0 ; d < dir.length ; d++){
                            int r = i+dir[d][0];
                            int c = j+dir[d][1];
                            if(r >=0 && c>=0 && r<n && c<n){
                                next[r][c]+=curr[i][j]/8.0;
                            }
                        }
                    }
                }
            }

            curr = next;
            next = new double[n][n];
        }

        double sum = 0;
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                sum+=curr[i][j];
            }
        }
        return sum;
    }
   
    public static void main(String[] args) {
        //System.out.println(maxProd(4));
        System.out.println(getCount(2));
    }
}
