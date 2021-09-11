import java.io.*;
import java.util.*;

public class a1 {

    // static int count =0;
    
    // public static void fun(int[] a,int[] b) {
    //     count += a[0];
    //     int p=0;
    //     for(int i = 1;i<a.length;i++){
    //         int x = a[i];
    //         if(p>0){
    //             x = x-p; 
    //         }
    //         count += Math.max(x,b[i-1]);
    //         if(x<b[i-1]){
    //             p=b[i-1]-x;
    //         }
    //     }
    //     count+=b[b.length-1];
    // }
    // public static void main(String[] args) {
    //     int[] a = {7,8,9};
    //     int[] b = {1,2,3};

    //     fun(a,b);
    //     System.out.println(count);
    // }

    
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String n = scn.next();
         int count =0;

        for(int i = 0 ;i<n.length();i++){
            char ch = n.charAt(i);
            if(ch=='a' ||ch=='e'|| ch=='i'||ch=='o'||ch=='u'){
                count++;
            }
        }

        System.out.println(count);
    
    }
}
