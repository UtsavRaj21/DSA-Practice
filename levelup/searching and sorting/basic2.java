import java.util.*;

import javax.swing.RootPaneContainer;
public class basic2 {

    //875. :- Koko Eating Bananas
    public static int minEatingSpeed(int[]piles,int h) {
        int lo = 1;
        int hi = 1;
        for(int val : piles){
            hi = Math.max(hi,val);
        }
        System.out.println(hi);
        int res = (int)1e9;
        while(lo<=hi){
            int mid = lo+(hi-lo)/2;
            System.out.print(mid+" ");
            int sum = 0;
            for(int i =0;i<piles.length;i++){
                sum+=(int)Math.ceil(piles[i]*1.0/mid);
            }
            if(sum <= h){
                res = Math.min(mid, res);
                hi = mid - 1;
            }else{
                lo = mid+1;
            }
        }
        return res;
    }
    
    //1283. Find the Smallest Divisor Given a Threshold
    public int smallestDivisor(int[] nums, int threshold) {
        int lo = 1;
        int hi = 1;
        for(int val : nums){
            hi = Math.max(hi,val);
        }
        System.out.println(hi);
        int res = (int)1e9;
        while(lo<=hi){
            int mid = lo+(hi-lo)/2;
            System.out.print(mid+" ");
            int sum = 0;
            for(int i =0;i<nums.length;i++){
                sum+=(int)Math.ceil(nums[i]*1.0/mid);
            }
            if(sum <= threshold){
                res = Math.min(mid, res);
                hi = mid - 1;
            }else{
                lo = mid+1;
            }
        }
        return res;
    }
    
   //Allocate Minimum Number Of Pages
   public static boolean isBurdenPossible(int[] arr , int m,int mid){
        int student = 1;
        int sum = 0;
        for(int i = 0;i < arr.length ;i++){
            sum+=arr[i];
            if(sum > mid){
                student++;
                sum = arr[i];
            }
        }
        return student <= m;
   }
   
   public static int minPages(int[]arr, int m) {
    if(m>arr.length){
        return -1;
    }
     int sum =0;
     int max = Integer.MIN_VALUE;
     for(var val  : arr){
         sum+=val;
         max = Math.max(max, val);
     } 
     int burden = -1;
     int lo = max;
     int hi = sum;

     while(lo<=hi){
         int mid = lo + (hi-lo)/2;
         if(isBurdenPossible(arr,m,mid)){
            burden = mid;
            hi = mid - 1;
         }else{
            lo = mid +1;
         }
     }
     return burden;
  }
    
  //Count Zeros In A Sorted Matrix
  public static int countZeros(int[][]mat) {
    int count =0;
    int i=0;
    int j = mat[0].length-1;
    while(i<mat.length && j >=0){
        if(mat[i][j] == 1){
            j--;
        }else{
            count+=j+1;
            i++;
        }
    }
    return count;
  }

    //Counting Elements In Two Arrays
    public static int ceil(int[] arr2,int num){
        int ceil = arr2.length;
        int low = 0;
        int high = arr2.length-1;
        
        while(low <= high){
            int mid = low + (high-low)/2;
            if(num < arr2[mid]){
                ceil = mid;
                high = mid-1;
            }else{
                low = mid+1;
            }
        }
        return ceil;
    }

    public static int[] find(int[]arr1, int[]arr2) { // t: O(nLogm) s: (1) //->...... n = arr1.length , m = arr1.length
        Arrays.sort(arr2);
        int n = arr1.length;
        int[] res = new int[n];
        for(int i = 0 ;i < n;i++){
            int ceil = ceil(arr2,arr1[i]);
            res[i] = ceil;
        }
        return res;
      }

    public static int floor(int[] arr2,int num){
        int floor = -1;
        int low = 0;
        int high = arr2.length-1;
        
        while(low <= high){
            int mid = low + (high-low)/2;
            if(num <= arr2[mid]){
                high = mid-1;
            }else{
                floor =mid;
                low = mid+1;
            }
        }

        return floor;
    }

    //OR

    public static int[] find1(int[]arr1, int[]arr2) { //t:O(n) s:O(n)
        int max =0;
        for(int val : arr1){
            max = Math.max(max,val);
        }
        for(int val : arr2){
            max = Math.max(max,val);
        }

        int[] arr = new int[max];

        for(int val : arr2){
            arr[val]++;
        }
        int sum = 0;
        for(int i =0;i<=max;i++){
            arr[i] = arr[i] + sum;
            sum = arr[i];
        }
        int[] res = new int[arr1.length];
        int idx=0;
        for(int val : arr1){
           res[idx++] = arr[val];
        }
        return res;
        
      }
   
      //Count Zeros Xor Pairs

    public static int countPairs(int[]arr) { 
       int count =0;
       HashMap<Integer,Integer> map = new HashMap<>();
       for(int val : arr){
           if(map.containsKey(val)){
               map.put(val,map.get(val)+1);
           }else{
               map.put(val, 1);
           }
       }

       for (int val : map.keySet())
       {
          
           int num = map.get(val);
           count+=(num*(num-1))/2;
       }
       return count;
    }
      
    //facing the sun
     public static int countBuildings(int[]ht) {
        int count = 1;
        int max = ht[0];
        for(int i = 1 ; i < ht.length ; i++){
            if(max<=ht[i]){
                count++;
                max = ht[i];
            }
        }
        return count;
      }
    
    //Distinct Absolute Array Elements
    public static int count(int[]arr) {
       int count = 0 ;
       int n = arr.length;
       int prev = -(int)1e9,next = (int)1e9,left =0,right = n-1;

        while(left<=right){
            int leftval = Math.abs(arr[left]);
            int rightval = Math.abs(arr[right]);

            if(rightval == leftval){
                if(leftval != prev && rightval != next){
                    count++;
                }
                prev = leftval;
                next = rightval;
                left++;
                right--;
            }else if(leftval > rightval){
                if(leftval != prev){
                    count++;
                }
                prev = leftval;
                left++;
            }else{
                if(rightval != next){
                    count++;
                }
                next = rightval;
                right--;
            }
        }
       return count;
      }
      
    //Find The Element That Appears Once In Sorted Array
    
    public static int findSingleElement(int[]arr) { //Xor O(n)  
        int res = arr[0];
        for(int i = 1 ; i < arr.length ;i++){
            res = res ^ arr[i];
        }
        return res;
    }
      
    public static boolean shouldPunish(int[]roll, int[]marks, double avg) {
        int count =0;
        int sum = 0;
        for(var val : marks){
            sum+=val;
        }
        int k=0;

        for(int i = 0 ; i < roll.length;i++){
            for(int j = 0 ; j< roll.length - i-1 ; j++){
                if(roll[j] > roll[j+1]){
                    count+=2;
                    int temp = roll[j];
                    roll[j] = roll[j+1];
                    roll[j+1] = temp;
                }
            }
        }
        
        double newAvg = (sum - count)/roll.length;
        
        return newAvg >= avg;
    }
      

    
        public static int largestPerimeter(int[]nums) {
         Arrays.sort(nums);
         int n = nums.length;
         for(int i =n-1 ; i>1 ; i-- ){
            if(nums[i] < nums[i-1]+nums[i-2]){
                return nums[i]+nums[i-1]+nums[i-2];
            }
         }
          return 0;
        }
      public static void main(String[] args) {
        
    }
}
