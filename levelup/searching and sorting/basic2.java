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
    
    
    
    public static void main(String[] args) {
        
    }
}
