import java.util.*;
import java.io.*;

public class p {

    private static void optimal_sequence(int N,int[] dp,ArrayList<Integer>[] arr) {
        for(int n=N;n>=0;n--){
            if(n==N) {
              dp[n]=1;
              arr[n].add(n);
              continue;  
            }
            int s1=(int)1e8,s2=(int)1e8,s3=(int)1e8;
            if(n+1<=N){
                s1=dp[n+1];
            }
            if(n*2<=N){
                s2=dp[n*2];
            }
            if(n*3<=N){
                s3=dp[n*3];
            }
            int ans=Math.min(Math.min(s1,s2),s3);

            if(ans==dp[n+1]){
                arr[n]=arr[n+1];
            }else if(ans==dp[n*2]){
                arr[n]=arr[n*2];
            }else{
                arr[n]=arr[n*3];
            }

            arr[n].add(n);
            //System.out.println(arr[n]);
            dp[n]=ans+1;
        
        }
        
    }

    public static void main(String[] args) {
    
        int n=10;
        int[] dp=new int[n+1];
        ArrayList<Integer>[] arr=new ArrayList[n+1];
        for (int i = 0; i < n+1; i++) {
            arr[i] = new ArrayList<>();
        }
        optimal_sequence(n,dp,arr);
        
        System.out.println(dp[1]-1);
        System.out.println(arr[1]);
        
        // for (int i = 0; i < n+1; i++) {
        //     for (int j = 0; j < arr[i].size(); j++) {
        //         System.out.print(arr[i].get(j) + " ");
        //     }
        //     System.out.println();
        // }
    
    }
}
