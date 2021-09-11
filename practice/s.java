import java.util.*; 
import java.io.*;
public class s{
    
    public static int solve(int n,int[][] arr){
      int x = arr[0][0];
      int y = arr[0][1];
      int level =1;

      for(int i=1;i<n;i++){
        if(y<arr[i][0]){
          level++;
          x=arr[i][0];
          y=arr[i][1];
        }
      }

      return level;
    }
      public static void main (String[] args) {  
       int n=3;
       int[][] arr = {{1,3},{2,4},{5,7}};
       System.out.println(solve(n,arr) );
       
        

        
      }
}