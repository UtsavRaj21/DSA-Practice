import java.util.*;

public class basic3 {

    public static void printPrimeUsingSieve(int n) {
        // pre-calculation

        boolean[] isprime = new boolean[n + 1];
        Arrays.fill(isprime, true);

        for (int i = 2; i * i <= n; i++) {
            if (isprime[i] == false) {
                continue;
            }
            for (int j = i + i; j <= n; j += i) {
                isprime[j] = false;
            }
        }

        // for quering

        for (int i = 2; i <= n; i++) {
            if (isprime[i] == true) {
                System.out.print(i + " ");
            }
        }
    }

    // segmented sieve

    public static ArrayList<Integer> sieve(int n) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        boolean[] isprime = new boolean[n + 1];

        for (int i = 2; i * i <= n; i++) {
            if (isprime[i] == true) {
                continue;
            }
            for (int j = i + i; j <= n; j += i) {
                isprime[j] = true;
            }
        }

        // for quering

        for (int i = 2; i <= n; i++) {
            if (isprime[i] == false) {
                ans.add(i);
            }
        }
        return ans;
    }

    public static void segmentedSieveAlgo(int a, int b) {
        int rootb = (int) Math.sqrt(b);
        ArrayList<Integer> primes = sieve(rootb);

        int n = b - a;
        boolean[] isprime = new boolean[n + 1];
        // isprime[i] == true -> not prime
        // isprime[i] == false --> prime

        for (int prime : primes) {
            int mul = (int) Math.ceil(a * 1.0 / prime);
            if (mul == 1)
                mul++;
            int si = mul * prime - a;
            for (int i = si; i < isprime.length; i += prime) {
                isprime[i] = true;
            }
        }

        // travel and print prime

        for (int i = 0; i < isprime.length; i++) {
            if (isprime[i] == false && i + a != 1) {
                System.out.println(i + a + " ");
            }
        }
    }

    // Find Pair Given Difference :gfg

    public boolean findPair(int arr[], int n, int target) {
        Arrays.sort(arr);
        int i = 0, j = 1;
        while (i < n && j < n) {
            if ((i != j) && (arr[j] - arr[i] == target)) {
                return true;
            } else if (arr[j] - arr[i] < target) {
                j++;
            } else {
                i++;
            }
        }
        return false;
    }

    // 881. Boats to Save People :-
    // https://leetcode.com/problems/boats-to-save-people/

    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int left = 0;
        int right = people.length - 1;
        int boats = 0;
        while (left <= right) {
            int sum = people[left] + people[right];
            if (sum > limit) {
                right--;
            } else {
                left++;
                right--;
            }
            boats++;
        }

        return boats;
    }

    // 763. Partition Labels :- https://leetcode.com/problems/partition-labels/

    public List<Integer> partitionLabels(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), i);
        }

        int max = 0;
        int prev = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            max = Math.max(max, map.get(ch));
            if (max == i) {
                ans.add(i - prev + 1);
                prev = i + 1;
            }
        }

        return ans;
    }

    // 754. Reach a Number :- https://leetcode.com/problems/reach-a-number/

    public int reachNumber(int target) {
        int sum = 0;
        int i = 1;
        int jump = 0;
        target = Math.abs(target);
        while (sum < target) {
            sum += i;
            i++;
            jump++;
        }
        int diff = sum - target;

        if (diff % 2 != 0) {
            if (i % 2 == 0) {
                jump += 2;
            } else {
                jump++;
            }
        }

        return jump;
    }

    // 867. Transpose Matrix :- https://leetcode.com/problems/transpose-matrix/

    public int[][] transpose(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        int[][] res = new int[row][col];

        for (int r = 0; r < col; r++) {
            for (int c = 0; c < row; c++) {
                res[r][c] = matrix[c][r];
            }
        }

        return res;
    }

    // portal N*N -->> change in matrix (No extra space)

    public static void transpose2(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    // 48. Rotate Image
    public void rotate(int[][] matrix) {
        transpose2(matrix);

        // rev col

        for (int r = 0; r < matrix.length; r++) {
            int left = 0;
            int right = matrix[0].length - 1;

            while (left < right) {
                int temp = matrix[r][left];
                matrix[r][left] = matrix[r][right];
                matrix[r][right] = temp;
                left++;
                right--;
            }
        }
    }

    // 838. Push Dominoes

    public void solveConfig(char[] arr, int i, int j) {
        if (arr[i] == 'L' && arr[j] == 'L') {
            for (int k = i + 1; k < j; k++)
                arr[k] = 'L';
        } else if (arr[i] == 'R' && arr[j] == 'R') {
            for (int k = i + 1; k < j; k++)
                arr[k] = 'R';
        } else if (arr[i] == 'L' && arr[j] == 'R') {
            // nothing to do
        } else {
            int left = i + 1;
            int right = j - 1;
            while (left < right) {
                arr[left] = 'R';
                arr[right] = 'L';

                left++;
                right--;
            }
        }
    }

    public String pushDominoes(String dominoes) {
        int n = dominoes.length();
        char[] arr = new char[n + 2];
        arr[0] = 'L';
        arr[n + 1] = 'R';

        for (int i = 0; i < n; i++) {
            arr[i + 1] = dominoes.charAt(i);
        }

        int i = 0;
        int j = 1;

        StringBuilder sb = new StringBuilder();

        while (j < arr.length) {
            while (arr[j] == '.') {
                j++;
            }

            if (j - i > 1) {
                solveConfig(arr, i, j);
            }

            i = j;
            j = j + 1;

        }
        StringBuilder str = new StringBuilder();
        for (int k = 1; k < arr.length - 1; k++) {
            str.append(arr[k]);
        }
        return str.toString();
    }

    // 829. Consecutive Numbers Sum :-
    // https://leetcode.com/problems/consecutive-numbers-sum/

    public int consecutiveNumbersSum(int n) {
        int count = 0;
        for (int k = 1; k * (k - 1) < 2 * n; k++) {
            int numerator = (n - (k - 1)) / 2 * k;
            if (numerator % 2 == 0) {
                count++;
            }
        }

        return count;
    }

    // Add String : Portal

    public static String addStrings(String num1, String num2) {
        // write your code here
    }

    // Multify String : Portal

    public static String multiplication(String num1, String num2) {
        // write your code here
    }

    // 42. Trapping Rain Water :- https://leetcode.com/problems/trapping-rain-water/

    public int trap(int[] height) {

    }

    //239. Sliding Window Maximum :- https://leetcode.com/problems/sliding-window-maximum/
    
    public int[] ngre(int[] arr){
        int[] ngr = new int[arr.length];
        Stack<Integer> st = new Stack<Integer>();
             // st.push(0);
        for(int i = 0 ; i < arr.length;i++){
            while(st.size() > 0 && arr[i] > arr[st.peek()]){
                ngr[st.pop()] = i;
            }
            st.push(i);
        }

        while(st.size() >0){
            ngr[st.pop()] = arr.length;
        }

        return ngr;
    }
    
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ngr = ngre(nums);
        int[] res = new int[n-k+1];
        for(int ele : ngr){
            System.out.print(ele+" ");
        }
        int j=0;

        for(int i = 0 ; i < res.length ; i++){
            if(j<i)j++;

            while(ngr[j] < i+k){
                j=ngr[j];
            }
            res[i] = nums[j];
        }

        return res;
    }

    public class Interval {
        int start;
        int end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    // meeting rooms lintcode 920. https://www.lintcode.com/problem/920/
    public boolean canAttendMeetings(List<Interval> intervals) {
        if(intervals.size() == 0) return true;
        Collections.sort(intervals,( Interval a, Interval b)->{
        return a.start - b.start;
    });

    int end = intervals.get(0).end;

    for(int i = 1 ; i < intervals.size();i++){
        int st = intervals.get(i).start;
        if(st < end){
            return false;
        }else{
            end = intervals.get(i).end;
        }

    }
    return true;
    }

    // meeting rooms 2 lintcode 919. https://www.lintcode.com/problem/919/
    public int minMeetingRooms(List<Interval> intervals) {
        int n = intervals.size();
        int[] start = new int[n];
        int[] end = new int[n];
        for(int i =0;i<n;i++){
            start[i] = intervals.get(i).start;
            end[i] = intervals.get(i).end;
        }

        int max = 0;
        int rooms=0;
        int i=0;
        int j =0;
        while(i<n){
            if(start[i] <= end[j]){
                rooms++;
                i++;
            }else{
              rooms--;
                j++;
            }
            max = Math.max(max, rooms);
        }
        return max;
    }


    // leetcode 56. https://leetcode.com/problems/merge-intervals/
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals,(a , b)->
            Integer.compare(a[0], b[0])
        );

        ArrayList<int[]> ans = new ArrayList<>();
        int start1 = intervals[0][0];
        int end1 = intervals[0][1];

        for(int i = 1 ; i < intervals.length;i++){
            int start2= intervals[i][0];
            int end2 =  intervals[i][1];

            if(start2 > end1){
                ans.add(new int[]{start1,end1});
                start1 = start2;
                end1 = end2;
            }else{
                end1 = Math.max(end1, end2);
            }
        }

        ans.add(new int[]{start1,end1});

        // int[][] res = new int[ans.size()][2];
        // for(int i = 0 ; i < ans.size();i++){
        //     res[i][0] = ans.get(i)[0];
        //     res[i][1] = ans.get(i)[1];
        // }

        // return res;
        return ans.toArray(new int[ans.size()][]);
    }

    // leetcode 986. https://leetcode.com/problems/interval-list-intersections/
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        ArrayList<int[]> ans = new ArrayList<>();
        int i =0; 
        int j =0;

        while(i < firstList.length && j < secondList.length){
            int st = Math.max(firstList[i][0],secondList[j][0]);
            int end = Math.min(firstList[i][1],secondList[j][1]);

            if(st <= end){
                ans.add(new int[]{st,end});
            }

            if(firstList[i][1] < secondList[j][1]){
                i++;
            }else{
                j++;
            }
        }
        return ans.toArray(new int[ans.size()][]);
    }

    // leetcode 57. https://leetcode.com/problems/insert-interval/
    public int[][] insert(int[][] intervals, int[] newInterval) {
        //H.W
    }

    public static void main(String[] args) {

    }
}