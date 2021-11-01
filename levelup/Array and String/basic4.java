import java.util.*;
public class basic4 {
    //853. Car Fleet :- https://leetcode.com/problems/car-fleet/

    public class vPair implements Comparable<vPair>{
        int pos = 0 ;
        int speed = 0;
        double t =0;

        public vPair(int pos,int speed,double t){
            this.pos = pos;
            this.speed = speed;
            this.t = t;

        }

        public int compareTo(vPair o){
            return this.pos - o.pos;
        }


    }
    
    public int carFleet(int target, int[] position, int[] speed) {
        int n = speed.length;
        vPair[] data = new vPair[n];

        for(int i = 0 ; i <n; i++){
            data[i] = new vPair(position[i], speed[i], (target - position[i])/speed[i]);
        }

        Arrays.sort(data);
        int count =1;
        double time = data[n-1].t;

        for(int i = n-2; i >= n ;i--){
            if(time < data[i].t){
                count++;
                time =  data[i].t;
            }
        }
        return count;
    }

    //Digit multiplier ;- https://practice.geeksforgeeks.org/problems/digit-multiplier3000/1
    
    static String getSmallest(Long N) {
        StringBuilder sb = new StringBuilder();
        int d = 9;
        // boolean flag; 
        while(d>0){
            if(N%d == 0){
                sb.append(d);
                N = N / d;
            }else{
                d--;
            }
            if(N== 1){
                sb = sb.reverse();
                return sb.toString();
            }

        }
        return -1+"";
    }
    
    //First negative integer in every window of size k : - https://practice.geeksforgeeks.org/problems/first-negative-integer-in-every-window-of-size-k3345/1
    
    public long[] printFirstNegativeInteger(long A[], int N, int K)
    {
        int n = N-K+1;
        long[] res = new long[n];

       int  negIdx = N;
       for(int i  = N-1 ; i >= N-K ; i--){
        if(A[i] < 0){
            negIdx = i;
        }
       }

       // Iterate from N-k to 0;

       for(int i = N-K ; i >=0;i--){
           if(A[i] < 0){
               negIdx = i;
           }
           if(i+K > negIdx){
                res[i] =A[negIdx];
           }else{
                res[i] = 0;
           }
       }

       return res;
        

    }
    
    //53. Maximum Subarray :- https://leetcode.com/problems/maximum-subarray/

    public int maxSubArray(int[] nums) {
     int csum =0;
     int osum = Integer.MIN_VALUE;

     for(int i = 0 ; i < nums.length ; i++){
        if(csum < 0){
            csum = nums[i];
        }else{
            csum += nums[i];
        }
        osum = Math.max(osum,csum);
     }
     return osum;
     
    }
    
    
    
    
    //=================================================================================================================
    public static void main(String[] args) {
        
    }
}
