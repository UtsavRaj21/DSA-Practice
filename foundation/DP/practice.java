import java.io.*;
import java.util.*;

public class practice {

    public static void main(String[] args) throws Exception {
        int n=10;
        int[] arr={1,1,1,4,9,8,1,1,10,1};
       Integer[] dp=new Integer[n+1];

        climb(0,n,dp,arr);
        System.out.print(dp[0]);
    }
    
    // public static void climb_dp(int Sr,int n,Integer[] dp,int[] arr){
    //     for(int sr=n;sr>=0;sr--){
    //         if(sr==n) {
    //             dp[n]=0;
    //             continue;
    //         }
            
    //         if(arr[sr]>0){
    //             int min =Integer.MAX_VALUE;
    //             for(int i=1;i<=arr[sr] && sr+i<=n;i++){
    //                 if(dp[sr+i]!=null){
    //                     min=Math.min(min,dp[sr+i]);
    //                 }
    //             }
    //             if(min!=Integer.MAX_VALUE){
    //                 dp[sr]=min+1;
    //             }
    //         }
    //     }
    //     System.out.println(dp[0]);
        
    // }

    // public static int climb(int sr,int n,Integer[] dp,int[] arr){
    //         if(sr==n) {
    //            return dp[n]=0;
    //         }
    //        if(dp[sr]!=null){
    //            return dp[sr];
    //        }
    //         int min=0;
    //         if(arr[sr]>0){
    //              min =Integer.MAX_VALUE;
    //             for(int i=1;i<=arr[sr] && sr+i<=n;i++){
    //                 if(dp[sr+i]!=null){
    //                     min=Math.min(min,climb(sr+i, n, dp, arr));
    //                 }
    //             }
                
    //         }
    //         if(min!=Integer.MAX_VALUE){
    //            return  dp[sr]=min+1;
    //         }
    // }

}