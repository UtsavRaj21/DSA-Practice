import java.util.*;

public class basic1 {

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
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int ele : arr) {
            if (map.containsKey(ele)) {
                map.put(ele, map.get(ele) + 1);
            } else {
                map.put(ele, 1);
            }
        }

        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            if (e.getValue() > n / k) {
                ans.add(e.getKey());
            }
        }

        return ans;
    }

    // 556. Next Greater Element III

    public static int dipIndex(char[] arr) {
        int idx = -1;

        for (int i = arr.length - 1; i > 0; i--) {
            if (arr[i - 1] < arr[i]) {
                idx = i - 1;
                break;
            }
        }
        return idx;
    }

    public static int ceilIndex(char[] arr, int dip) {
        int idx = arr.length - 1;

        while (arr[dip] >= arr[idx]) {
            idx--;
        }
        return idx;
    }

    public static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

    }

    public static void reverse(char[] arr, int i, int j) {
        while (i < j) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }

    }

    public static String nextGreaterElement_(String str) {
        char[] arr = str.toCharArray();
        int dipIdx = dipIndex(arr);

        if (dipIdx == -1)
            return "-1";

        int ceilIdx = ceilIndex(arr, dipIdx);
        swap(arr, dipIdx, ceilIdx);
        reverse(arr, dipIdx + 1, arr.length - 1);
        return String.valueOf(arr);
    }

    public static int nextGreaterElement(int n) {
        if (n < 10)
            return -1;
        String numString = nextGreaterElement_("" + n);
        long num = Long.parseLong(numString);
        if (num == -1)
            return -1;

        if (num <= Integer.MAX_VALUE) {
            return (int) num;
        } else {
            return -1;
        }
    }

    // 905. Sort Array By Parity

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public int[] sortArrayByParity(int[] nums) {
        int i = 0, j = 0;

        while (i < nums.length) {
            if (nums[i] % 2 != 0) {
                i++;
            } else {
                swap(nums, i, j);
                i++;
                j++;
            }
        }

        return nums;
    }

    // 628. Maximum Product of Three Numbers

    public int maximumProduct(int[] nums) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];

            if (max1 < val) {
                max3 = max2;
                max2 = max1;
                max1 = val;
            } else if (max2 < val) {
                max3 = max2;
                max2 = val;
            } else if (max3 < val) {
                max3 = val;
            }

            if (min1 > val) {
                min2 = min1;
                min1 = val;
            } else if (min2 > val) {
                min2 = val;
            }
        }

        return Math.max(max1 * max2 * max3, max1 * min1 * min2);
    }

    // 769. Max Chunks To Make Sorted

    public int maxChunksToSorted(int[] arr) {
        int max = 0;
        int chunks = 0;

        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            if (max == i) {
                chunks++;
            }
        }

        return chunks;
    }

    // 768. Max Chunks To Make Sorted II

    public int maxChunksToSorted_2(int[] arr) {
        int n = arr.length;
        int[] maxarr = new int[n];
        int[] minarr = new int[n + 1];

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, arr[i]);
            maxarr[i] = max;
        }

        minarr[n] = (int) 1e9;
        int min = (int) 1e9;

        for (int i = n - 1; i >= 0; i--) {
            min = Math.min(min, arr[i]);
            minarr[i] = min;
        }

        int chunks = 0;

        for (int i = 0; i < n; i++) {
            if (maxarr[i] <= minarr[i + 1]) {
                chunks++;
            }
        }

        return chunks;
    }

    // leetcode 747.
    // https://leetcode.com/problems/largest-number-at-least-twice-of-others/

    public int dominantIndex(int[] nums) {
        if (nums.length == 1)
            return 0;
        int indx = 0;
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max1) {
                max2 = max1;
                max1 = nums[i];
                indx = i;
            } else if (nums[i] > max2) {
                max2 = nums[i];
            }
        }
        return max1 >= 2 * max2 ? indx : -1;
    }

    // leetcode 345. https://leetcode.com/problems/reverse-vowels-of-a-string/

    private boolean isVowel(char ch) {
        String str = "AEIOUaeiou";
        return str.contains(ch + "");
    }

    public String reverseVowels(String s) {
        char[] arr = s.toCharArray();

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            while (left < right && isVowel(arr[left]) == false) {
                left++;
            }

            while (left < right && isVowel(arr[right]) == false) {
                right--;
            }

            swap(arr, left, right);
            left++;
            right--;
        }
        return String.valueOf(arr);
    }

    // 795. Number of Subarrays with Bounded Maximum

    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int i = 0, j = 0, prev_count = 0, count = 0;

        while (i < nums.length) {
            if (left <= nums[i] && nums[i] <= right) {
                count += i - j + 1;
                prev_count = i - j + 1;
            } else if (nums[i] < left) {
                count += prev_count;
            } else {
                prev_count = 0;
                j = i + 1;
            }
            i++;
        }

        return count;
    }

    // Wave Array :-
    // https://practice.geeksforgeeks.org/problems/wave-array-1587115621/1#

    public static void swapNum(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void wiggleSort(int[] arr) {
        // write your code here
        int i = 0;
        while (i < arr.length - 1) {
            if (i % 2 == 1) { // odd Index
                if (arr[i] < arr[i + 1]) {
                    swapNum(arr, i, i + 1);
                }
            } else {
                if (arr[i] > arr[i + 1]) {
                    swapNum(arr, i, i + 1);
                }
            }
        }
    }

    public static void convertToWave(int arr[], int n) {

        int i = 0;
        while (i < arr.length - 1) {
            if (i % 2 == 1) { // odd Index
                if (arr[i] > arr[i + 1]) {
                    swapNum(arr, i, i + 1);
                }
            } else {
                if (arr[i] < arr[i + 1]) {
                    swapNum(arr, i, i + 1);
                }
            }
            i++;
        }

    }

    // 324. Wiggle Sort II :- https://leetcode.com/problems/wiggle-sort-ii/

    public void wiggleSort_2(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = nums[i];
        }

        Arrays.sort(arr);
        int j = n - 1;
        int i = 1;
        while (i < n) {
            nums[i] = arr[j];
            j--;
            i += 2;
        }
        i = 0;
        while (i < n) {
            nums[i] = arr[j];
            j--;
            i += 2;
        }

    }

    // 903 · Range Addition :- https://www.lintcode.com/problem/range-addition

    public int[] getModifiedArray(int n, int[][] query) {
        int[] arr = new int[n];
        for (int i = 0; i < query.length; i++) {
            int st = query[i][0];
            int end = query[i][1];
            int val = query[i][2];

            arr[st] += val;
            if (end != n - 1) {
                arr[end + 1] -= val;
            }

        }

        for (int i = 1; i < n; i++) {
            arr[i] += arr[i - 1];
        }

        return arr;
    }

    // 238. Product of Array Except Self

    public int[] productExceptSelf(int[] nums) {
        int[] sol = new int[nums.length];
        if (nums.length == 0)
            return sol;
        sol[0] = 1;

        int sumleft = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sol[i] = sol[i - 1] * nums[i - 1];
        }

        int sumright = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            sol[i] = sol[i] * sumright;

            sumright *= nums[i];
        }

        return sol;

    }

    // 119. Pascal's Triangle II

    public List<Integer> getRow(int rowIndex) {
        List<Integer> ans = new ArrayList<Integer>();
        long val = 1;
        for (int col = 0; col <= rowIndex; col++) {
            ans.add((int) val);
            val = (val * (rowIndex - col)) / (col + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        // stderr
    }
}