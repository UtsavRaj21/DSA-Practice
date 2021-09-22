import java.util.*;
import java.io.*;

public class s {
  public static int maxLength(int n,int[] a,int[] b) {
    int sum =0;
    for(int i=0;i<n;i++){
      int x = b[i];
      if(x <= a[i]){
        sum += x;
      }else{
        sum+=a[i];
        int y = x-a[i];
        if(y <= a[i+1]){
          sum+=y;
          a[i+1] = a[i+1] - y;
        }else{
          sum+=a[i+1];
          a[i+1] = 0;
        }
      }
    }

    return sum;
  }

  public static void main(String[] args) {
    int[] a = {6,1,1};
    int[] b={1,8};
    int ans = maxLength(2,a,b);
    System.out.println(ans);
  }
}
