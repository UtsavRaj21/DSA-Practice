import java.util.*;

public class basic2 {

    // 849. Maximize Distance to Closest Person

    public int maxDistToClosest(int[] seats) {
        int n = seats.length;
        int left = (int) 1e9, right = (int) 1e9, max = 0, idx = -1;
        for (int i = 0; i < n; i++) {
            if (seats[i] == 1) {
                seats[i] = -1;
                left = i;
            } else {
                seats[i] = Math.abs(left - i);
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            if (seats[i] == -1) {
                seats[i] = -1;
                right = i;
            } else {
                seats[i] = Math.min(seats[i], Math.abs(right - i));
                if (seats[i] > max) {
                    max = seats[i];
                    idx = i;
                }
            }
        }
        return max;
    }

    // 41. First Missing Positive

    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        boolean one = false;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                one = true;
            }
            if (nums[i] <= 0 || nums[i] > n) {
                nums[i] = 1;
            }
        }

        if (!one)
            return 1;

        for (int i = 0; i < n; i++) {
            int idx = Math.abs(nums[i]) - 1;
            nums[idx] = -Math.abs(nums[idx]);
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;

    }

    // 912 · Best Meeting Point912 · Best Meeting Point :-
    // https://www.lintcode.com/problem/912/

    public int minTotalDistance(int[][] grid) {
        // Write your code here
        ArrayList<Integer> xcord = new ArrayList<>();
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    xcord.add(r);
                }
            }
        }

        ArrayList<Integer> ycord = new ArrayList<>();
        for (int c = 0; c < grid[0].length; c++) {
            for (int r = 0; r < grid.length; r++) {
                if (grid[r][c] == 1) {
                    ycord.add(c);
                }
            }
        }
        // find meeting point from median
        int x = xcord.get(xcord.size() / 2);
        int y = ycord.get(ycord.size() / 2);

        int sum = 0;
        for (int ele : xcord) {
            sum += Math.abs(ele - x);
        }
        for (int ele : ycord) {
            sum += Math.abs(ele - y);
        }
        return sum;
    }

    // 670. Maximum Swap

    public static void swap(char[] arr, int i, int j) {
        char ch = arr[i];
        arr[i] = arr[j];
        arr[j] = ch;
    }

    public static String maximumSwap(String num) {
        char[] arr = num.toCharArray();
        int[] last = new int[10];

        for (int i = 0; i < arr.length; i++) {
            last[arr[i]] = i;
        }
        boolean flag = false;

        for (int i = 0; i < arr.length; i++) {
            int d = arr[i];
            for (int j = 9; j > d; j--) {
                if (last[j] > i) {
                    swap(arr, i, last[j]);
                    flag = true;
                }
            }
            if (flag)
                break;
        }

        return String.valueOf(arr);
    }

    public int maximumSwap(int num) {
        String s = maximumSwap(num + "");
        return Integer.parseInt(s);
    }

    // 560. Subarray Sum Equals K

    public int subarraySum(int[] nums, int k) {
        int n = nums.length;

        int st = 0, et = 1;
        int sum = 0;
        while (et < n) {
            if (sum + nums[et] == k) {
                return et - st + 1;
            } else if (sum + nums[et] > k) {
                st++;
            } else {
                sum += nums[et];
                et++;
            }
        }
        return -1;
    }

    //Pep Website
    // 2 Sum - Target Sum Unique Pairs
    public static List<List<Integer>> twoSum(int[] arr, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = arr.length;
        int l = 0, r = n - 1;
        Arrays.sort(arr);
        while (l < r) {
            int sum = arr[l] + arr[r];
            if (sum == target) {
                List<Integer> res = new ArrayList<>();
                res.add(arr[l]);
                res.add(arr[r]);
                ans.add(res);
                l++;
                r--;
            } else if (sum > target) {
                r--;
            } else {
                l++;
            }

            while (l < r && arr[l] == arr[l - 1]) {   // do not repeat same combination
                l++;
            }
        }
        return ans;
    }

    //3 Sum - Target Sum Unique Triplet :- https://leetcode.com/problems/3sum/
    
    public static List<List<Integer>> twoSum_helper(int[] arr, int target,int st) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = arr.length;
        int l = st, r = n - 1;
      
        while (l < r) {
             if(l!=st && arr[l] == arr[l-1]){
                 l++;
                continue;
            }
            int sum = arr[l] + arr[r];
            if (sum == target) {
                List<Integer> res = new ArrayList<>();
                res.add(arr[l]);
                res.add(arr[r]);
                ans.add(res);
                l++;
                r--;
            } else if (sum > target) {
                r--;
            } else {
                l++;
            }
        }
        return ans;
    }
    
    public static List<List<Integer>> threeSum(int[] nums, int targ) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for(int i =0;i < nums.length-2;i++){
            if(i!=0 && nums[i] == nums[i-1]){
                continue;
            }
            int val1 = nums[i];
            List<List<Integer>> subres = twoSum_helper(nums, targ-val1,i+1);

            for(List<Integer> list : subres){
                list.add(val1);
                ans.add(list);
            }
        }
        return ans;
      }
    
    public List<List<Integer>> threeSum(int[] nums) {
        return threeSum(nums,0);
    }

    //4 Sum - Target Sum With Unique Quadruple :- https://leetcode.com/problems/4sum/
    
    public static List<List<Integer>> threeSum_helper(int[] nums, int targ,int st) {
        List<List<Integer>> ans = new ArrayList<>();
        for(int i =st;i < nums.length-2;i++){
            if(i!=st && nums[i] == nums[i-1]){
                continue;
            }
            int val1 = nums[i];
            List<List<Integer>> subres = twoSum_helper(nums, targ-val1,i+1);

            for(List<Integer> list : subres){
                list.add(val1);
                ans.add(list);
            }
        }
        return ans;
      }
    
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        for(int i =0;i < nums.length-3;i++){
            if(i!=0 && nums[i] == nums[i-1]){
                continue;
            }
            int val1 = nums[i];
            List<List<Integer>> subres = twoSum_helper(nums, target-val1,i+1);

            for(List<Integer> list : subres){
                list.add(val1);
                ans.add(list);
            }
        }
        return ans;
      }

    //K Sum - Target Sum Unique Set : PEP

    public static List<List<Integer>> kSum_helper(int[] nums, int target, int k,int st) {
        List<List<Integer>> ans = new ArrayList<>();
        if(k==2){
            ans = twoSum_helper(nums, target, st);
        }else{
            for(int i =st;i < nums.length-2;i++){
                if(i!=st && nums[i] == nums[i-1]){
                    continue;
                }
                int val1 = nums[i];
                List<List<Integer>> subres =  kSum_helper(nums, target-val1,k-1,i+1);
    
                for(List<Integer> list : subres){
                    list.add(val1);
                    ans.add(list);
                }
            }
        }

        return ans;
    }
   
    public static List<List<Integer>> kSum(int[] arr, int target, int k) {
        Arrays.sort(arr);
       return kSum_helper(arr, target, k , 0);
    }

    //Complex Number Multiplication : PEP

    public static String complexNumberMultiply(String num1, String num2) {
        int a1 = Integer.parseInt(num1.substring(0,num1.indexOf("+")));
        int b1 = Integer.parseInt(num1.substring(num1.indexOf("+")+1,num1.length()-1));
        int a2 = Integer.parseInt(num2.substring(0,num2.indexOf("+")));
        int b2 = Integer.parseInt(num2.substring(num2.indexOf("+")+1,num2.length()-1));

        int r = a1*a2 - b1*b2;
        int c = a1*b2 + a2*b1;
      

        return r + "+" + c+"i";
      }

      //Minimum Platforms :- https://practice.geeksforgeeks.org/problems/minimum-platforms-1587115620/1

      static int findPlatform(int arr[], int dep[], int n) {
          Arrays.sort(arr);
          Arrays.sort(dep);
          int plat = 0;
          int max =0;
          int i=0,j=0;
          while(i<arr.length){
              if(arr[i] <= dep[j]){
                  plat++;
                  i++;
              }else{
                plat--;
                  j++;
              }
              max = Math.max(max, plat);
          }
          return max;
      }

    //Find Pair Given Difference :- https://practice.geeksforgeeks.org/problems/find-pair-given-difference1559/1
    
    public boolean findPair(int arr[], int n, int target) {
        Arrays.sort(arr);
        int i = 0,j=1;
        while(i < n && j<n){
            if((i!=j) && (arr[j] - arr[i] == target)){
                return true;
            }else if(arr[j] - arr[i] < target){
                j++;
            }else{
                i++;
            }
        }
        return false;
    }
}
