import java.util.*;

import org.graalvm.compiler.hotspot.nodes.FastAcquireBiasedLockNode;

public class faang {

    public class Edge {
        int v = 0;
        int w = 0;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    // 127. Word Ladder :- https://leetcode.com/problems/word-ladder/submissions/

    public static String toString(char[] a) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < a.length; i++) {
            sb.append(a[i]);
        }

        return sb.toString();
    }

    public static void search(char[] chArr, HashMap<String, Boolean> map, LinkedList<String> que) {
        String s = toString(chArr);
        for (int i = 0; i < chArr.length; i++) {
            char[] arr = s.toCharArray();
            for (char j = 'a'; j <= 'z'; j++) {
                arr[i] = j;
                String str = toString(arr);
                System.out.println(str);
                if (map.containsKey(str) && !map.get(str)) {
                    map.replace(str, true);
                    que.add(str);

                }
            }
        }
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashMap<String, Boolean> map = new HashMap<>();
        for (String s : wordList) {
            map.put(s, false);
        }

        if (!map.containsKey(endWord)) {
            return 0;
        }

        LinkedList<String> que = new LinkedList<>();

        que.add(beginWord);

        int level = 1;
        while (que.size() != 0) {
            int s = que.size();
            while (s-- > 0) {
                String rStr = que.removeFirst();
                if (rStr.equals(endWord)) {
                    return level;
                }
                char[] chArr = rStr.toCharArray();
                search(chArr, map, que);

            }
            level++;
        }
        return 0;
    }

    // 773. Sliding Puzzle :- https://leetcode.com/problems/sliding-puzzle/

    public static String swap(String str, int i, int j) {
        StringBuilder sb = new StringBuilder(str);

        sb.setCharAt(i, str.charAt(j));
        sb.setCharAt(j, str.charAt(i));

        return sb.toString();
    }

    public static int slidingPuzzle(int[][] board) {
        HashSet<String> map = new HashSet<>();
        String target = "123450";

        LinkedList<String> que = new LinkedList<>();

        int[][] dir = { { 1, 3 }, { 0, 2, 4 }, { 1, 5 }, { 0, 4 }, { 1, 3, 5 }, { 2, 4 } };

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                sb.append(board[i][j]);
            }
        }

        String str = sb.toString();
        if (str.equals(target))
            return 0;
        map.add(str);
        que.add(str);
        System.out.println(str);

        int level = 0;

        while (que.size() != 0) {
            int s = que.size();
            while (s-- > 0) {
                String rstr = que.removeFirst();

                int idx = -1;

                for (int i = 0; i < rstr.length(); i++) {
                    if (rstr.charAt(i) == '0') {
                        idx = i;
                        break;
                    }

                }

                for (int e : dir[idx]) {
                    String st = rstr;
                    String swapString = swap(st, idx, e);
                    // System.out.println(swapString);
                    if (!map.contains(swapString)) {
                        if (swapString.equals(target))
                            return ++level;
                        map.add(swapString);
                        que.add(swapString);
                    }
                }
            }
            level++;
        }

        return -1;
    }

    // Eulerian Path in an Undirected Graph

    public static int eulerPath(int N, int graph[][]) { // (n-2) even indegree , (2)odd indegree
        int[] arr = new int[N];
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                if (graph[i][j] == 1) {
                    arr[i]++;

                }

            }
        }

        int even = 0;
        int odd = 0;
        for (int ele : arr) {
            if (ele % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }

        if (odd == 2) {
            return 1;
        }

        return 0;
    }

    // Euler Circuit in an Undirected Graph

    public boolean isEularCircuitExist(int V, ArrayList<ArrayList<Integer>> adj) // even indegree
    {
        int[] arr = new int[V];
        for (ArrayList<Integer> a : adj) {
            for (Integer ele : a) {
                arr[ele]++;
            }

        }

        for (int ele : arr) {
            if (ele % 2 != 0) {
                return false;
            }
        }
        return true;
    }

    public static String similarity(String str, int i, int j) {
        StringBuilder sb = new StringBuilder(str);
        char ch1 = str.charAt(i);
        char ch2 = str.charAt(j);
        sb.setCharAt(i, ch2);
        sb.setCharAt(j, ch1);

        return sb.toString();
    }

    public static int kSimilarity(String s1, String s2) {
        if (s1.equals(s2))
            return 0;

        LinkedList<String> que = new LinkedList<>();
        que.add(s1);
        HashSet<String> set = new HashSet<>();
        set.add(s1);
        int level = 0;
        while (que.size() != 0) {
            int s = que.size();
            while (s-- > 0) {
                String rstr = que.removeFirst();
                for (int i = 0; i < rstr.length(); i++) {
                    for (int j = i + 1; j < rstr.length(); j++) {
                        String nstr = similarity(rstr, i, j);

                        if (nstr.equals(s2)) {
                            return level + 1;
                        }
                        if (!set.contains(nstr)) {
                            set.add(nstr);
                            que.addLast(nstr);
                        }
                    }
                }
            }
            level++;
        }
        return 0;
    }

    // 934. Shortest Bridge

    public int shortestBridge(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        LinkedList<Integer> que = new LinkedList<>();
        LinkedList<Integer> Mainque = new LinkedList<>();
        boolean[][] vis = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    int idx = i * n + j;
                    que.add(idx);
                    vis[i][j] = true;
                    break;
                }
            }
            if (que.size() > 0) {
                break;
            }
        }

        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        while (que.size() > 0) {
            int rNo = que.removeFirst();
            int sr = rNo / m;
            int sc = rNo % m;
            Mainque.add(rNo);
            for (int d = 0; d < dir.length; d++) {
                int r = sr + dir[d][0];
                int c = sc + dir[d][1];

                if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1 && !vis[r][c]) {
                    vis[r][c] = true;
                    que.add(r * n + c);
                }

            }
        }

        int level = 0;
        while (Mainque.size() > 0) {
            int s = Mainque.size();
            System.out.print(level + "  ");
            while (s-- > 0) {
                int rNo = Mainque.removeFirst();
                int sr = rNo / m;
                int sc = rNo % m;
                for (int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];

                    if (r >= 0 && c >= 0 && r < n && c < m && !vis[r][c]) {
                        vis[r][c] = true;
                        if (grid[r][c] == 1) {
                            return level;
                        } else {
                            Mainque.add(r * n + c);
                        }

                    }

                }
            }
            level++;
        }

        return -1;
    }

    

    public static void main(String[] args) {
        String s1 = "abcdeabcdeabcdeabcde", s2 = "aaaabbbbccccddddeeee";
        System.out.println(kSimilarity(s1, s2));
    }
}
