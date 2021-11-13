import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class faang1 {

    //209. Minimum Size Subarray Sum
    public int minSubArrayLen(int target, int[] nums) {
        int min = Integer.MAX_VALUE;

        int sum = 0;
        int left = 0;

        for(int i = 0 ; i < nums.length ; i++){
            sum+=nums[i];

            while(sums>=target){
                min = Math.min(min,i+1-left);
                val-=nums[left];
                left++;
            }
        }

        return min;
    }

    //667. Beautiful Arrangement II
    public int[] constructArray(int n, int k) {
        int[] ans = new int[n];

        int idx = 0;
        int top = 1,bot = n;
        while(top <= bot){
            if(k%2 == 1){
                ans[idx++] = top++;
            }else{
                ans[idx++] = bot--;
            }
            if(k>1){
                k--;
            }
        }
        return ans;
    }
   
   //719. Find K-th Smallest Pair Distance
   public int smallestDistancePair(int[] nums, int k) {
      ArrayList<Integer> res = new ArrayList<>();
      
      for(int i = 0 ; i < nums.length ; i++){
          for(int j = i+1 ; j < nums.length ; j++){
              ans.add(Math.abs(nums[i]-nums[j]));
          }
      }

      Collection.sort(res);
      return res.get(k+1);
    }

    //152. Maximum Product Subarray

    public int maxProduct(int[] nums) {
        int prod = 1;
        int res = Integer.MIN_VALUE;
        int n = nums.length;
        //left
        for(int i = 0 ; i < n;i++){
            if(nums[i] == 0){
                res = Math.max(nums[i],res);
                prod = 1;
            }else{
                prod *= nums[i];
                res = Math.max(res,prod);
            }
        }
        //right
        prod = 1;
        for(int i = n-1 ; i >=0;i--){
            if(nums[i] == 0){
                res = Math.max(nums[i],res);
                prod = 1;
            }else{
                prod *= nums[i];
                res = Math.max(res,prod);
            }
        }

        return res;
    }
   
    //https://practice.geeksforgeeks.org/problems/max-sum-in-sub-arrays0824/1#

    public static long pairWithMaxSum(long arr[], long n)
    {
        long res = 0;
        for(int i =0;i < n-1 ; i++){
            res = Math.max(arr[i]+arr[i+1],res);
        }
        return res;
    }
    
    //134. Gas Station
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int gasSum = 0 , costSum = 0;

        for(int i = 0 ;i < gas.length ; i++){
            gasSum+=gas[i];
            costSum+=cost[i];
        }

        if(gasSum - costSum < 0) return -1;

        int prefix =0,min = Integer.MAX_VALUE,idx=0;

        for(int i = 0 ; i<gas.length;i++){
            prefix+=gas[i] - cost[i];
            if(prefix < min){
                min = prefix;
                idx = i;
            }
        }

        return (idx +1) % gas.length;
    }
    
    //1007. Minimum Domino Rotations For Equal Row
    
    public int minDominoRotations(int[] tops, int[] bottoms) {
        int val1 = tops[0],val2 = bottoms[0];
        int c1 =0 , c2 =0 ,c3=0,c4=0;

        for(int i = 0 ; i < tops.length;i++){
            if(c1!=Integer.MAX_VALUE){
                if(tops[i] == val1){

                }else if(bottoms[i] == val1){
                    c1++;
                }else{
                    c1 = Integer.MAX_VALUE;
                }
            }
            if(c2!=Integer.MAX_VALUE){
                if(tops[i] == val2){

                }else if(bottoms[i] == val2){
                    c2++;
                }else{
                    c2 = Integer.MAX_VALUE;
                }
            }
            if(c3!=Integer.MAX_VALUE){
                if(bottoms[i] == val1){

                }else if(tops[i] == val1){
                    c3++;
                }else{
                    c3 = Integer.MAX_VALUE;
                }
            }
            if(c4!=Integer.MAX_VALUE){
                if(bottoms[i] == val2){

                }else if(tops[i] == val2){
                    c4++;
                }else{
                    c4 = Integer.MAX_VALUE;
                }
            }
        }

        int min = Math.min(Math.min(c1,c2),Math.min(c3, c4));

        return min == Integer.MAX_VALUE ? -1 : min;
    }
    
    //632. Smallest Range Covering Elements from K Lists

    public class RHelper implements Comparable<RHelper>{
        int val;
        int r;
        int c;

        public RHelper(int val, int r, int c) {
            this.val = val;
            this.r = r;
            this.c = c;
        }

        public int compareTo(RHelper o) {
            return this.val - o.val;
        }
    }

    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<RHelper> que = new PriorityQueue<>();
        int max = Integer.MIN_VALUE;
        int sp=0;
        int end=0;
        int length = Integer.MAX_VALUE;

        for(int i = 0 ; i < nums.size();i++){
            int val = nums.get(i).get(0);
            que.add(new RHelper(val, i, 0));
            max = Math.max(max, val);
        }

        while(que.size()>0){
            RHelper rNum = que.remove();
            int cmin = rNum.val;
            int cmax = max;
            
            if(cmax - cmin < length){
                length = cmax - cmin;
                sp = cmin;
                end = cmax;
            }

            if(rNum.c+1 < nums.get(rNum.r).size()){
                que.add(new RHelper(nums.get(rNum.r).get(rNum.c+1),rNum.r,rNum.c+1));
                max = Math.max(max,nums.get(rNum.r).get(rNum.c+1));
            }else{
                break;
            }
        }

        int[] arr = {sp,end};
        return arr;
    }

    //209. Minimum Size Subarray Sum

    public int minSubArrayLen(int target, int[] nums) {
        int min = Integer.MAX_VALUE;
 
         int sum = 0;
         int left = 0;
 
         for(int i = 0 ; i < nums.length ; i++){
             sum+=nums[i];
 
             while(sum>=target){
                 min = Math.min(min,i+1-left);
                 sum-=nums[left];
                 left++;
             }
         }
 
         return min == Integer.MAX_VALUE ? 0 : min ;
     }
    
     //643. Maximum Average Subarray I

     public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        double res = Integer.MIN_VALUE * 1.0;
        for(int i = 0 ;i<k-1;i++){
            sum+=nums[i];
        }
        for(int i = k-1 ; i < nums.length  ;i++){
            sum+=nums[i];
            double avg = (sum * 1.0) / k;
            res = Math.max(res, avg);
            sum-=nums[i-k+1];
        }
        return ans;
    }

    //442. Find All Duplicates in an Array
    public List<Integer> findDuplicates(int[] nums) {
        public List<Integer> findDuplicates(int[] nums) {
            List<Integer> ans = new ArrayList<>();
            for(int i = 0 ; i < nums.length ; i++){
                int idx = Math.abs(nums[i]);
                int val = nums[idx-1];
                if(val < 0){
                    ans.add(Math.abs(nums[i]));
                }else{
                    nums[idx-1] = -1 * nums[idx-1];
                }
            }
            return ans;
        }
    }
    
    //43. Multiply Strings
    public String multiply(String num1, String num2) {
        int l1 = num1.length();
        int l2 = num2.length();
        int pf =1;
        if(l1==0 || l2 ==0) return '0';

        int i = l1-1;
        int[] res = new int[l1+l2];
        while(i>=0){
            int num = num1.charAt(i) - '0';
            int k = l1+l2-pf;
            int j = l2-1;
            int carry = 0;
            while(j>=0){
                int mul = num * (num2.charAt(j)-'0') + carry +res[k];
                carry = mul / 10;
                res[k] = mul  %10;
                k--;
                j--

            }
            if(carry > 0){
                res[k] = carry + res[k];
            }
            i--;
            pf++;
        }
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        for(int val : res){
            if(val == 0 && flag == false){
                continue;
            }else{
                sb.append(val);
                flag = true;
            }
        }
        return sb.toString();

    }
    
    //936. Stamping The Sequence
    public boolean canReplace(char[] tchar,int pos,char[] schar){
        for(int i = 0 ; i < schar.length ; i++){
            if(tchar[i+pos] != '?' && tchar[i+pos] != schar[i]){
                return false;
            }
        }
        return true;
    }

    public static int replace(char[] tchar , int pos , int len , int count){
        for(int i = 0 ; i < len; i++){
            if(tchar[i+pos] != '?'){
                tchar[i+pos] = '?';
                count++;
            }
        }
        return count;
    }
    
    public int[] movesToStamp(String stamp, String target) {
        char[] tchar = target.toCharArray();
        char[] schar = stamp.toCharArray();
        int count =0;
        boolean[] visited = new boolean[tchar.length];
        List<Integer> res = new ArrayList<>();
        boolean didchange = false;
        while(count != tchar.length){
            
            for(int i = 0 ; i <= tchar.length - schar.length ;i++){
                if(!visited[i] && canReplace(tchar,i,schar)){
                    count = replace(tchar,i,schar.length,count);
                    visited[i] = true;
                    didchange = true;
                    res.add(i);

                    if(count == tchar.length){
                        break;
                    }
                }
            }
           
        }
         if(!didchange){
                return new int[0];
        }
        int[] ans = new int[res.size()];
        int k=0;
        for(int i = res.size()-1;i>=0;i--){
            ans[k++] = res.get(i);
        }

        return ans;
    }

    //452. Minimum Number of Arrows to Burst Balloons

    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points,(a,b)->
            Integer.compare(a[1],b[1])
            );
       int arrows = 1 , end = points[0][1];
       for(int i = 1 ; i < points.length ; i++){
           if(points[i][0] > end){
               arrows++;
               end = points[i][1];
           }
       }

       return arrows;
    }
    public static void main(String[] args) {
        
    }
}
