import java.util.*;

import javax.swing.text.AbstractDocument.LeafElement;

public class ques {

    public static void slove(int[] arr,int m){
        int n = arr.length;
        int sum = 0;
        for(int ele : arr){
            sum+=ele;
        }

        if(sum < m){
            System.out.println(0);
            return;
        }

        int min = (int)1e9;
        sum = 0;
        int i=0,j=0;

        while(i<n){
            sum+=arr[i];
            while(sum >= m && j<=i){
                min = Math.min(sum,min);
                sum = sum - arr[j];
                j++;
            }
            i++;
        }

        System.out.println(min);
    }
   
    
    static int max = (int)1e9;
    
    public static void solvedfs(int[] X,int[] Y,int B,int G,int bab ,int gums,int num,int idx){
        if(bab>=B && gums>=G){
            max = Math.min(max,num);
        }

        if(idx == X.length){
            return;
        }

        solvedfs(X, Y, B, G, bab+X[idx], gums+Y[idx], num+1, idx+1);
        solvedfs(X, Y, B, G, bab, gums, num, idx+1);
    }
    
    public static void solve1(int[] X , int[] Y,int B, int G){
        int bab = 0,gums=0;

        solvedfs(X,Y,B,G,bab,gums,0,0);

        max = (max == (int)1e9 ? -1 : max);

        System.out.println(max);
    }
    public static void main(String[] args) {
        int[] X = {1,2,1};
        int[] Y = {1,1,1};
        solve1(X,Y,5,7);
    }
}
