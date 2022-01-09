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

    public static void main(String[] args) {

    }
}
