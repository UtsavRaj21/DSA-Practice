/* package codechef; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class test
{   
    static int count1 = 0;
    static int count2 = 0;
    public static int solve_x(int x,int i,int sum){
	    while(i<=x){
         sum+=i;
         i++;
		}
        return sum;
    }

    public static int solve_x1(int x,int i,int y,int sum){
        count1+=sum;
	    while(i<x){
         sum+=i+y;
         i++;
        count1+=sum;

		}
        return sum;
    }
     public static int solve_y(int y, int x,int sum){
        int ans=sum;
        int i=1;
        
	    while(i<y){
         ans+=x;
         x++;
         i++;
		}
        return ans;
    }

    public static int solve_y1(int y, int x,int y1,int i,int sum){
        
        System.out.println(count1);
        System.out.println("count1");
       while(i<y){
        sum+=y1+x-1;
        y1++;
        i++;
        count1+=sum;
        System.out.println(sum);
        System.out.println(count1);
       }
       return sum;
   }

   
	public static void main (String[] args) throws java.lang.Exception
	{
        int x1=2,y1=2,x2=3,y2=4;
        int sumx1 = solve_x(x1,1,0);

        int intial_sum = solve_y(y1,x1,sumx1);
        System.out.println(intial_sum);
       

        
             
        int fx_sum = solve_x1(x2,x1,y1,intial_sum);
        System.out.println(fx_sum);
        int fy_sum = solve_y1(y2,x2,y1,y2-y1,fx_sum);
        System.out.println(fy_sum);
         System.out.println(count1);
        


	}
}


// public static int solve_y2(int x, int i,int sum){

//     count1+=sum;
    
//     while(i<x){
//         sum+=i;
//         i++;
//         count1+=sum;
//         System.out.println(count1);
//        }
//        return sum;
//    }

//    public static int solve_x2(int y, int x,int sum){
    
//     System.out.println("hi");
//    int i=1;
//    while(i<y){
//     sum+=x;
//     x++;
//     i++;
//     count1+=sum;
//     System.out.println(count1);
//    }
//    return sum;
// }