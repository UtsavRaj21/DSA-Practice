import java.util.*;

public class jan {

    //
    public int numPairsDivisibleBy60(int[] time) {
        int count =0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int ele : time){
            int val = ele % 60;
            int rem = 60 - val;

            if(val==0){
                if(map.containsKey(val)){
                    count += map.get(val);
                }
            }else if(map.containsKey(rem)){
                count += map.get(rem);
            }

            if(map.containsKey(val)){
                map.put(val,map.get(val) + 1);
            }else{
                map.put(val, 1);
            }
        }
        return count;

        //997. Find the Town Judge
         
    //
    public int findJudge(int n, int[][] trust) {
        if (n == 1 && trust.length == 0)
            return 1;
        HashMap<Integer, Integer> map = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();
        for (int[] pair : trust) {
            int a = pair[0];
            int b = pair[1];
            set.add(a);
            if (!map.containsKey(b)) {
                map.put(b, 1);
            } else {
                map.put(b, map.get(b) + 1);
            }
        }

        for (int key : map.keySet()) {
            if (map.get(key) == n - 1) {
                if (!set.contains(key)) {
                    return key;
                }

            }
        }

        return -1;
    }

    // 1009. Complement of Base 10 Integer
    public int bitwiseComplement(int n) {
        String str = Integer.toBinaryString(n);
        StringBuilder sb = new StringBuilder(str);
        StringBuilder sb1 = new StringBuilder();
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '0')
                sb1.append("1");
            else
                sb1.append("0");
        }
        int res = Integer.parseInt(sb1.toString(), 2);
        return res;
    }

    // 131. Palindrome Partitioning
    public List<List<String>> partition(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        List<List<String>> result = new ArrayList<>();
        dfs(result, s, 0, new ArrayList<>(), dp);
        return result;
    }

    void dfs(List<List<String>> result, String s, int start, List<String> currentList, boolean[][] dp) {
        if (start >= s.length())
            result.add(new ArrayList<>(currentList));
        for (int end = start; end < s.length(); end++) {
            if (s.charAt(start) == s.charAt(end) && (end - start <= 2 || dp[start + 1][end - 1])) {
                dp[start][end] = true;
                currentList.add(s.substring(start, end + 1));
                dfs(result, s, end + 1, currentList, dp);
                currentList.remove(currentList.size() - 1);
            }
        }
    }

    //
    public boolean carPooling(int[][] trips, int capacity) {
        Arrays.sort(trips, (a, b) -> {
            return a[1] - b[1];
        });
        int num = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int i = 0;
        while (i < trips.length) {
            if (pq.size() == 0) {
                num += trips[i][0];
                map.put(trips[i][2], trips[i][0]);
                pq.add(trips[i][2]);
                i++;
            } else if (pq.peek() <= trips[i][1]) {
                int rem = pq.peek();
                num = num - map.get(pq.peek());
                pq.remove();
                map.remove(rem);
            } else {
                num += trips[i][0];
                if (map.containsKey(trips[i][2])) {
                    map.put(trips[i][2], map.get(trips[i][2]) + trips[i][0]);
                } else {
                    map.put(trips[i][2], trips[i][0]);
                    pq.add(trips[i][2]);
                }
                i++;
            }
            System.out.println(num);
            if (num > capacity) {
                return false;
            }
        }

        return true;
    }

    //
    public boolean isRobotBounded(String instructions) {
        char dir = 'N';
        int x = 0;
        int y = 0;
        int r = 0;
        for (int i = 0; i < instructions.length(); i++) {
            char ch = instructions.charAt(i);
            if (ch == 'G') {
                if (dir == 'N') {
                    y++;
                } else if (dir == 'E') {
                    x++;
                } else if (dir == 'S') {
                    y--;
                } else if (dir == 'W') {
                    x--;
                }
            } else if (ch == 'L') {
                if (dir == 'N') {
                    dir = 'E';
                } else if (dir == 'E') {
                    dir = 'S';
                } else if (dir == 'S') {
                    dir = 'W';
                } else if (dir == 'W') {
                    dir = 'N';
                }
            } else if (ch == 'R') {
                if (dir == 'N') {
                    dir = 'W';
                } else if (dir == 'E') {
                    dir = 'N';
                } else if (dir == 'S') {
                    dir = 'E';
                } else if (dir == 'W') {
                    dir = 'S';
                }
            }
        }

        return ((x==0 && y==0) || dir !=  'N');
    }

    //67. Add Binary
    public String addBinary(String a, String b) {
      StringBuilder sb = new StringBuilder();
      int i = a.length()-1,j=b.length()-1;
      int carry = 0;
      while(i>=0 || j>=0){
          int aval = i !=-1 ? a.charAt(i--) - '0' : 0;
          int bval = j !=-1 ? b.charAt(j--) - '0' : 0;

          int sum = aval + bval + carry;
          carry = sum / 2;
          int rem = sum % 2;
          sb.append(rem);
      }
      //System.out.println(carry);
    if(carry > 0){
        sb.append(1);
    }

    sb = sb.reverse();
    
    return sb.toString();
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

    //849. Maximize Distance to Closest Person
    public int maxDistToClosest(int[] seats) {
        int n = seats.length;
        int[] left = new int[n];
        int[] right = new int[n];

        int p = Integer.MAX_VALUE;
        boolean flag = false;

        for(int i = 0 ; i  < n ; i++){
            if(flag == false && seats[i] != 1){
                left[i] = p;
            }else if(seats[i] == 1){
                p=0;
                left[i] = 0;
                flag = true;
            }else{
                p++;
                left[i] = p;
            }
        }

         p = Integer.MAX_VALUE;
         flag = false;

        for(int i = n-1 ; i >= 0 ; i--){
            if(flag == false && seats[i] != 1){
                right[i] = p;
            }else if(seats[i] == 1){
                p=0;
                right[i] = 0;
                flag = true;
            }else{
                p++;
                right[i] = p;
            }
        }

        int res =0;

        for(int i = 0 ; i < n ; i++){
            res = Math.max(res,Math.min(left[i],right[i]));
        }

        return res;
    }

    //72. Edit Distance
    static int[][] dp;
    public int min(String s1,String s2,int i,int j){
        if(i == 0){
            dp[i][j] = j;
            return j;
        }

        if(j == 0){
            dp[i][j] = i;
            return i;
        }

        if(dp[i][j] != -1){
            return dp[i][j];
        }

        if(s1.charAt(i-1) == s2.charAt(j-1)){
            return dp[i][j] = min(s1,s2,i-1,j-1);
        }

        int replace = min(s1, s2, i-1, j-1) + 1;
        int delete = min(s1, s2, i-1, j)+1;
        int insert = min(s1, s2, i, j-1);

        return dp[i][j] = Math.min(replace,Math.min(insert,delete));
    }
    public int minDistance(String word1, String word2) {
        dp = new int[word1.length()+1][word2.length()+1];
        for(int[] a : dp){
            Arrays.fill(a,-1);
        }
        return min(word1,word2,word1.length()-1,word2.length()-1);
    }
    
    //
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        for(int i = 0 ; i < s.length() ;i++){
            char ch = s.charAt(i);
            if(ch == '('){
               st.push(ch);
            }else if(ch == ')'){
               if(st.size() == 0){
                   return false;
               }else if(st.peek() != '('){
                   return false;
               }else{
                   st.pop();
               }
            }else if(ch == '['){
                st.push(ch);
            }else if(ch == ']'){
                if(st.size() == 0){
                    return false;
                }else if(st.peek() != '['){
                    return false;
                }else{
                    st.pop();
                }
            }else if(ch == '{'){
                st.push(ch);
            }else{
                if(st.size() == 0){
                    return false;
                }else if(st.peek() != '{'){
                    return false;
                }else{
                    st.pop();
                }
            }
        }

        return st.size() == 0;
    }
    
    //139. Word Break
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] bdp = new boolean[n+1];
        int len =0;
        HashSet<String> map = new HashSet<>();
        for(String s :wordDict ){
            len = Math.max(len,s.length());
            map.add(s);
        }
        bdp[0] = true;

        for(int i = 0 ; i < n ;i++){
            if(!bdp[i]) continue;

            for(int l = 1 ; i+l <= n && l <=len ; l++){
                if(map.contains(s.substring(i, i+l))) bdp[i+l] = true;
            }
        }

        return bdp[n];
    }
    
   //
   public int coin(int[] coins,int idx,int sum,int[][] dp){
       if(idx==0 && sum==0){
           return dp[idx][sum] = 1;
       }
       if(idx == 0){
           return dp[idx][sum] = 0;
       }
       if(sum == 0){
            return dp[idx][sum] = 1;
       }

       if(dp[idx][sum] != -1){
           return dp[idx][sum];
       }

       if(coins[idx - 1] >= sum){
            return dp[idx][sum] = Math.min(coin(coins,idx-1,sum - coins[idx-1],dp)+1,coin(coins, idx-1, sum, dp));
       }else{
            return dp[idx][sum] = coin(coins, idx-1, sum, dp);
       }


   }
   
    public int coinChange(int[] coins, int amount){
        int n = coins.length;
        int[][] dp = new int[n+1][amount+1];

        for(int[] a : dp){
            Arrays.fill(a,-1);
        }

        coin(coins,n,amount,dp);

        return dp[n][amount];
    }
    
    public static void main(String[] args) {

    }
}
