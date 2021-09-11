import java.util.*;

public class basic {

    // 925. Long Pressed Name
    public boolean isLongPressedName(String name, String typed) {
        int s1 = name.length();
        int s2 = typed.length();
        if (s1 > s2)
            return false;

        int i = 0, j = 0;
        char ch = name.charAt(0);

        while (i < name.length() && j < typed.length()) {
            if (name.charAt(i) == typed.charAt(j)) {
                ch = name.charAt(i);
                i++;
                j++;
            } else if (ch == typed.charAt(j)) {
                j++;
            } else {
                return false;
            }
        }

        if (i != s1)
            return false;

        while (j < typed.length()) {
            if (typed.charAt(j) != ch)
                return false;
            j++;
        }
        return true;
    }

    // 11. Container With Most Water :-
    // https://leetcode.com/problems/container-with-most-water/
    
     public int maxArea(int[] height) {
        int ans = 0;

        int i = 0;
        int j = height.length - 1;
        while (i < j) {
            int area = Math.min(height[i], height[j]) * (j - i);
            ans = Math.max(ans, area);
            if (height[i] > height[j]) {
                j--;
            } else {
                i++;
            }
        }

        return ans;
    }

    // 977. Squares of a Sorted Array
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int idx = n - 1;
        int i = 0, j = n - 1;

        while (i <= j) {
            if (nums[i] * nums[i] > nums[j] * nums[j]) {
                ans[idx--] = nums[i] * nums[i];
                i++;
            } else {
                ans[idx--] = nums[j] * nums[j];
                j--;
            }
        }

        return ans;

    }

    // 169. Majority Element
    public int majorityElement(int[] nums) {
        int n = nums.length;
        int j = 1;
        int var1 = nums[0];
        int c1 = 1;
        while (j < n) {
            if (nums[j] == var1) {
                c1++;
            } else {
                if (c1 > 0) {
                    c1--;
                } else {
                    var1 = nums[j];
                    c1 = 1;
                }
            }
            j++;
        }

        return var1;

    }

    // 229. Majority Element II
    public boolean isMajority(int[] nums, int var) {
        int count = 0;
        int n = nums.length;

        for (int ele : nums) {
            if (ele == var)
                count++;
        }

        return count > n / 3;
    }

    public List<Integer> majorityElement2(int[] nums) {
        int n = nums.length;
        int var1 = nums[0];
        int var2 = -(int) 1e9;
        int c1 = 1;
        int c2 = 0;

        int j = 1;

        while (j < n) {
            int num = nums[j];
            if (var1 == num) {
                c1++;
            } else if (var2 == num) {
                c2++;
            } else {
                if (c1 == 0) {
                    var1 = num;
                    c1 = 1;
                } else if (c2 == 0) {
                    var2 = num;
                    c2 = 1;
                } else {
                    c1--;
                    c2--;
                }
            }
            j++;
        }

        List<Integer> ans = new ArrayList<>();

        if (isMajority(nums, var1)) {
            ans.add(var1);
        }
        if (isMajority(nums, var2)) {
            ans.add(var2);
        }

        return ans;
    }

    public static ArrayList<Integer> majorityElementGeneral(int[] arr, int k) {
        int n = arr.length;
        ArrayList<Integer> ans = new ArrayList<Integer>();
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int ele : arr){
            if(map.containsKey(ele){
                map.put(ele,map.get(ele)+1);
            }else{
                map.put(ele, 1);
            }
        }

        for(Map.Entry<Integer, Integer> e : map.entrySet()){
            if(e.getValue() > n/k){
                ans.add(e.getKey());
            }
        }

        return ans;
     }
    
     public static void main(String[] args) {

    }
}