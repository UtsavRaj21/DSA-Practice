import java.util.*;

public class faang {

    public static class Edge {
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

    // 332. Reconstruct Itinerary :- Euler Circuit in an directed Graph
    static HashMap<String, PriorityQueue<String>> graph;
    static LinkedList<String> ans;

    public static List<String> findItinerary(List<List<String>> tickets) {
        graph = new HashMap<>();
        ans = new LinkedList<>();

        for (List<String> ticket : tickets) {
            PriorityQueue<String> temp = graph.getOrDefault(ticket.get(0), new PriorityQueue<String>());
            temp.add(ticket.get(1));
            graph.put(ticket.get(0), temp);
        }

        dfs("JFK");
        return ans;
    }

    public static void dfs(String str) {
        PriorityQueue<String> rque = graph.get(str);

        while (rque != null && rque.size() > 0) {
            String s = rque.remove();
            dfs(s);
        }
        ans.addFirst(str);
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

    // Find the Maximum Flow

    static int sum = 0;

    public static void solve(int N, int M, ArrayList<ArrayList<Integer>> Edges) {
        ArrayList<Edge>[] graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (ArrayList<Integer> l : Edges) {
            graph[l.get(0)].add(new Edge(l.get(1), l.get(2)));
            graph[l.get(1)].add(new Edge(l.get(0), l.get(2)));
        }

        boolean[] vis = new boolean[N + 1];

        forOne(graph, 1, N, vis);
    }

    public static void forOne(ArrayList<Edge>[] graph, int src, int tar, boolean[] vis) {
        boolean flag = false;
        for (Edge e : graph[src]) {
            int min = (int) 1e9;
            int v = e.v, w = e.w;
            if (w > 0) {
                int m = dfs(graph, v, tar, vis, min);

                if (m != ((int) 1e9)) {
                    e.w = e.w - m;
                    flag = true;
                    break;
                }
            }
        }
        if (flag) {
            forOne(graph, 1, tar, vis);
        }
    }

    public static int dfs(ArrayList<Edge>[] graph, int src, int tar, boolean[] vis, int min) {
        if (src == tar) {
            sum += min;
            return min;
        }
        boolean flag = false;
        vis[src] = true;
        int m = (int) 1e9;
        for (Edge e : graph[src]) {
            if (!vis[e.v] && e.w > 0) {
                m = dfs(graph, e.v, tar, vis, min);
                if (m != ((int) 1e9)) {
                    e.w = e.w - m;
                    flag = true;
                    break;
                }
            }
        }
        vis[src] = false;
        return m;
    }

    // 947. Most Stones Removed with Same Row or Column

    static int[] par;
    static int[] size;

    public static int parFind(int src) {
        if (par[src] == src) {
            return src;
        }

        return par[src] = parFind(par[src]);
    }

    public static void union(int p1, int p2) {
        if (size[p1] < size[p2]) {
            par[p1] = p2;
            size[p2] += size[p1];
        } else {
            par[p2] = p1;
            size[p1] += size[p2];
        }

    }

    public static int removeStones(int[][] stones) {
        int n = stones.length, m = stones[0].length;
        par = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            par[i] = i;
            size[i] = 0;
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    int p1 = parFind(i);
                    int p2 = parFind(j);
                    if (p1 != p2) {
                        union(p1, p2);
                        count++;
                    }

                }
            }
        }

        return count;
    }

    // 1034. Coloring A Border

    public static void dfs(int[][] grid, int row, int col, int clr, int[][] dir) {
        grid[row][col] = -clr;
        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = row + dir[d][0];
            int c = col + dir[d][1];

            if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && Math.abs(grid[r][c]) == clr) {
                count++;
                if (grid[r][c] == clr) {
                    dfs(grid, r, c, clr, dir);
                }

            }

            if (count == 4) {
                grid[row][col] = clr;
            }
        }
    }

    public static int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int num = grid[row][col];
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        dfs(grid, row, col, num, dir);

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] < 0) {
                    grid[r][c] = color;
                }
            }
        }

        return grid;
    }

    //Vertices and edges :- https://www.hackerearth.com/practice/algorithms/graphs/shortest-path-algorithms/practice-problems/algorithm/allsomenone-78013449/

    public static class Pair{
        int vtx;
        int wsf;
        StringBuilder str;

        Pair(int vtx,int wsf,StringBuilder str){
            this.vtx = vtx;
            this.wsf = wsf;
            this.str = str;
        }

    }
    public static void Vertices_and_edges(ArrayList<Edge>[] graph,int src,int des){
       
        PriorityQueue<Pair> que = new PriorityQueue<>((a,b)->{
            return a.wsf-b.wsf;
        });
        StringBuilder sb  = new StringBuilder();

        que.add(new Pair(1, 0,sb));
        boolean[] vis = new boolean[des+1];
        String s= "";

        while(que.size()>0){
            Pair rPair = que.remove();
            int vtx = rPair.vtx;
            int wsf = rPair.wsf;
            if(vtx == des){
                s = rPair.str.append(des).toString();
                break;
            }
            if(vis[vtx]) continue;
            vis[vtx] = true;

            for(Edge e : graph[vtx]){
                if(!vis[e.v]){
                    que.add(new Pair(e.v, wsf + e.w,rPair.str.append(vtx+" ")));
                }
            }
        }

        String[] arr = new String[des+1];
        Arrays.fill(arr,"none");
        String[] a = s.split(" ");

        for(String ss : a){
            arr[Integer.parseInt(ss)] = "all";
        }

        for(int i = 1 ; i< arr.length ; i++){
            System.out.println(arr[i]);
        }
    }
    public static void main(String[] args) {
        // String s1 = "abcdeabcdeabcdeabcde", s2 = "aaaabbbbccccddddeeee";
        // System.out.println(kSimilarity(s1, s2));

        Scanner scn = new Scanner(System.in);
        String st = scn.nextLine();
        String[] ar = st.split(" ");
        int n = Integer.parseInt(ar[0]);
        int e = Integer.parseInt(ar[1]);

        ArrayList<Edge>[] graph = new ArrayList[n+1];

        for(int i =0 ;i<n+1;i++){
            graph[i] = new ArrayList<Edge>();
        }

        for(int i = 0 ; i < e ; i++){
            String s = scn.nextLine();
            String[] arr = s.split(" ");
            int u = Integer.parseInt(arr[0]);
            int v = Integer.parseInt(arr[1]);
            int w = Integer.parseInt(arr[2]);

            graph[u].add(new Edge(v,w));
            graph[v].add(new Edge(u,w));
        }

        Vertices_and_edges(graph,1,n);
    }
}
