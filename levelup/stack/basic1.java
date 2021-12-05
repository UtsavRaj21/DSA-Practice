import java.util.*;

public class basic1 {

    // Next Greater Element To The Right
    public static int[] nger(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();
        st.push(0);

        for (int i = 1; i < arr.length; i++) {
            int val = arr[i];
            while (st.size() != 0 && val > arr[st.peek()]) {
                int idx = st.pop();
                res[idx] = val;
            }
            st.add(i);
        }
        while (st.size() > 0) {
            int idx = st.pop();
            res[idx] = -1;
        }
        return res;
    }

    // Next Greater Element To The Left
    public static int[] ngel(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();
        st.add(n - 1);
        for (int i = n - 2; i >= 0; i--) {
            int val = arr[i];
            while (st.size() > 0 && arr[st.peek()] < val) {
                int idx = st.pop();
                res[idx] = val;
            }
            st.add(i);
        }
        while (st.size() > 0) {
            int idx = st.pop();
            res[idx] = -1;
        }
        return res;
    }

    // Next Smaller Element To The Right
    public static int[] nser(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();
        st.add(0);
        for (int i = 1; i < n; i++) {
            int val = arr[i];
            while (st.size() > 0 && arr[st.peek()] > val) {
                int idx = st.pop();
                res[idx] = val;
            }
            st.add(i);
        }
        while (st.size() > 0) {
            int idx = st.pop();
            res[idx] = n;
        }
        return res;
    }

    // Next Smaller Element To The Left
    public static int[] nsel(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();
        st.add(n - 1);
        for (int i = n - 2; i >= 0; i--) {
            int val = arr[i];
            while (st.size() > 0 && arr[st.peek()] > val) {
                int idx = st.pop();
                res[idx] = val;
            }
            st.add(i);
        }
        while (st.size() > 0) {
            int idx = st.pop();
            res[idx] = -1;
        }
        return res;
    }

    // Next Greater Element I
    public static int[] nextGreaterElement(int[] nums, int[] query) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        Stack<Integer> st = new Stack<>();
        st.add(0);
        for (int i = 1; i < n; i++) {
            int val = nums[i];
            while (st.size() > 0 && nums[st.peek()] < val) {
                int idx = st.pop();
                map.put(nums[idx], nums[i]);
            }
            st.add(i);
        }

        while (st.size() > 0) {
            int idx = st.pop();
            map.put(nums[idx], -1);
        }

        // System.out.println(map);

        int q = query.length;
        int[] res = new int[q];
        for (int i = 0; i < q; i++) {
            res[i] = map.get(query[i]);
        }

        return res;
    }

    // Next Greater Element Ii :- the next element of nums[nums.length - 1] is
    // nums[0]
    public static int[] nextGreaterElementII(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();
        st.push(0);
        Arrays.fill(res, -1);
        for (int id = 1; id < 2 * nums.length; id++) {
            int i = id % n;
            int val = nums[i];
            while (st.size() > 0 && val > nums[st.peek()]) {
                int idx = st.pop();
                res[idx] = val;
            }
            st.push(i);
        }

        return res;
    }

    // 84. Largest Rectangle in Histogram
    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] rs = nser(heights);
        int[] ls = nsel(heights);
        int max = 0;

        for (int i = 0; i < n; i++) {
            int width = rs[i] - ls[i] - 1;
            max = Math.max(max, width * heights[i]);
        }
        return max;
    }

    // Maximal Rectangle
    public static int maximalRectangle(int[][] ar) {
        int res = 0;
        int[] ht = new int[ar[0].length];
        for (int i = 0; i < ar.length; i++) {
            for (int j = 0; j < ar[0].length; j++) {
                if (ar[i][j] == 0) {
                    ht[j] = 0;
                } else {
                    ht[j] += 1;
                }
            }
            res = Math.max(res, largestRectangleArea(ht));
        }
        return res;
    }

    // Validate Stack Sequences
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed.length != popped.length)
            return false;
        int i = 0;
        int j = 0;
        Stack<Integer> st = new Stack<Integer>();
        while (i < pushed.length) {
            if (st.size() == 0) {
                st.add(pushed[i++]);
            } else if (st.peek() == popped[j]) {
                st.pop();
                j++;
            } else {
                st.add(pushed[i++]);
            }
        }

        while (j < popped.length) {
            if (st.peek() != popped[j]) {
                return false;
            }
            st.pop();
            j++;
        }
        return st.size() == 0 ? true : false;
    }

    // Minimum Add To Make Parentheses Valid
    public static int minAddToMakeValid(String S) {
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            if (st.size() == 0 || ch == '(') {
                st.push(ch);
            } else if (ch == ')' && st.peek() == '(') {
                st.pop();
            } else {
                st.push(ch);
            }
        }
        return st.size();
    }

    // 1963. Minimum Number of Swaps to Make the String Balanced
    public int minSwaps(String S) {
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            if (st.size() == 0 || ch == '[') {
                st.push(ch);
            } else if (ch == ']' && st.peek() == '[') {
                st.pop();
            } else {
                st.push(ch);
            }
        }
        int pair = st.size() / 2;
        return (int) Math.ceil(pair / 2.0);
    }

    // Count the Reversals
    public int countRev(String S) {
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            if (st.size() == 0 || ch == '{') {
                st.push(ch);
            } else if (ch == '}' && st.peek() == '{') {
                st.pop();
            } else {
                st.push(ch);
            }
        }
        if (st.size() % 2 != 0)
            return -1;
        int op = 0;
        int cl = 0;
        while (st.size() > 0) {
            char ch = st.pop();
            if (ch == '{') {
                op++;
            } else {
                cl++;
            }
        }
        return (int) Math.ceil(op / 2.0) + (int) Math.ceil(cl / 2.0);
    }

    public static void main(String[] args) {

    }
}