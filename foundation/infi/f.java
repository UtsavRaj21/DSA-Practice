import java.io.*;
import java.util.*;

public class f {

  public static int find(int n,int m,int[] a,int[] b)
   {
    Arrays.sort(a);
    Arrays.sort(b);
    int x=0;
     int[] min=new int[n];
     for(int i=0;i<n;i++){
       for(int j=x;j<m; j++){
         if(m-j>n-i){
           if(min[i]==0 || min[i]>Math.abs(a[i]-b[j]) ){
              min[i]=Math.abs(a[i]-b[j]);
              x++;
           }
           else{
             break;
           }
          }else{
            if(min[i]!=0){
              min[i]=Math.min(min[i],Math.abs(a[i]-b[j]));
            }else{
              min[i]=Math.abs(a[i]-b[j]);
              x++;
            }
            break;
          }
        }
      }

      int minsum=0;

        for(int i=0;i<n;i++){

              minsum+=min[i];
        }
        if(minsum>1000000000){
            minsum=minsum%00000007;
        }

        return minsum;
      }

  public static void main (String[] args) {
    // int[] a={6,4};
    // int[] b={3,1,5};

    int[] a={6,11};
    int[] b={3,1,5,8,2,7,10,9,13,12,4};

    // int[] a={8,3,1};
    // int[] b={5,7,4};

    // int[] a={3,1};
    // int[] b={6,4};

    // int[] a={7,8,8};
    // int[] b={1,7,4};
	  System.out.print(find(a.length,b.length,a,b));
		
	}
}