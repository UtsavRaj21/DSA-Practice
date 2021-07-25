import java.util.*;

public class directedGraph {
    public static class Edge {
        int v = 0, w = 0;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
        graph[u].add(new Edge(v, w));
    }

    public static void bfs_topo(ArrayList<Edge>[] graph, int src, boolean[] vis, ArrayList<Integer> ans) {
        vis[src] = true;

        for (Edge e : graph[src]) {
            if (!vis[e.v]) {
                bfs_topo(graph, e.v, vis, ans);
            }
        }
        ans.add(src);
    }

   
    public static ArrayList<Integer> topologicalOrder_bfs(ArrayList<Edge>[] graph) {
        ArrayList<Integer> ans = new ArrayList<>();
        int N = graph.length;
        boolean[] vis = new boolean[N];

        for (int i = 0; i < N; i++) {
            if (!vis[i]) {
                bfs_topo(graph, i, vis, ans);
            }
        }
        return ans;
    }


    //-1:unvisited  , 0:currentPath , 1 :backTrack

    public static boolean dfs_topo(ArrayList<Edge>[] graph, int src, int[] vis, ArrayList<Integer> ans){
        vis[src] = 0;
        boolean res = false;
        for(Edge e: graph[src]){
            if(vis[e.v] == -1){
                res = res || dfs_topo(graph, e.v, vis, ans);
            }else if(vis[e.v] == 0){
                return true;
            }
        }
        vis[src] =1;
        ans.add(src);
        return res;
    }
        
    public static ArrayList<Integer> topologicalOrder__dfs1(ArrayList<Edge>[] graph) {
        ArrayList<Integer> ans = new ArrayList<>();
        int N = graph.length;
        int[] vis = new int[N];
        boolean cycle = false;
        for (int i = 0; i < N; i++) {
            if (vis[i]==-1) {
                cycle = cycle || dfs_topo(graph, i, vis, ans);
            }
        }
        if(cycle){
            ans.clear();
        }
        return ans;
    }
    
    // Khan's Algo (For loop in Directed Graph)

    public static ArrayList<Integer> khanAlgo(ArrayList<Edge>[] graph) {
        int N = graph.length;
        int[] indegree = new int[N];

        LinkedList<Integer> que = new LinkedList<>();
        ArrayList<Integer> ans = new ArrayList<>();

        // O(E)
        for (int i = 0; i < N; i++) {
            for (Edge e : graph[i]) {
                indegree[e.v]++;
            }
        }

        // O(V)
        for (int i = 0; i < N; i++) {
            if (indegree[i] == 0) {
                que.addLast(i);
            }
        }

        // O(E+V)
        while (que.size() > 0) {
            int s = que.size();
            while (s-- > 0) {
                int rVtx = que.removeFirst();
                ans.add(rVtx);
                for (Edge e : graph[rVtx]) {
                    if (--indegree[e.v] == 0) {
                        que.addLast(e.v);
                    }
                }
            }
        }
        if (ans.size() != N) {
            ans.clear();
        }

        return ans;
    }

    // 207. Course Schedule :- https://leetcode.com/problems/course-schedule/

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int N = numCourses;
        ArrayList<Integer>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        for (int[] edge : prerequisites) {
            graph[edge[0]].add(edge[1]);
        }
        return khanAlgo_207(numCourses, graph);

    }

    public boolean khanAlgo_207(int N, ArrayList<Integer>[] graph) {
        int[] indegree = new int[N];

        for (int i = 0; i < N; i++) {
            for (int e : graph[i]) {
                indegree[e]++;
            }
        }

        LinkedList<Integer> que = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            if (indegree[i] == 0) {
                que.addLast(i);
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();

        while (que.size() > 0) {
            int s = que.size();
            while (s-- > 0) {
                int rVtx = que.removeFirst();
                ans.add(rVtx);
                for (int e : graph[rVtx]) {
                    if (--indegree[e] == 0) {
                        que.addLast(e);
                    }
                }
            }
        }
        if (ans.size() != N) {
            return false;
        }

        return true;
    }

    // 210. Course Schedule II :- https://leetcode.com/problems/course-schedule-ii/

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 1)
            return new int[1];
        int N = numCourses;
        ArrayList<Integer>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        for (int[] edge : prerequisites) {
            graph[edge[0]].add(edge[1]);
        }
        int[] ans = khanAlgo_210(numCourses, graph);

        int[] a = new int[N];
        int d = 0;
        boolean check = false;
        for (int i = N - 1; i >= 0; i--) {
            if (ans[i] != 0) {
                check = true;
            }
            if (ans[i] == -1)
                return new int[0];
            a[d++] = ans[i];
        }
        if (check) {
            return a;
        }

        return new int[0];
    }

    public int[] khanAlgo_210(int N, ArrayList<Integer>[] graph) {
        int[] indegree = new int[N];

        for (int i = 0; i < N; i++) {
            for (int e : graph[i]) {
                indegree[e]++;
            }
        }

        LinkedList<Integer> que = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            if (indegree[i] == 0) {
                que.addLast(i);
            }
        }

        int[] ans = new int[N];
        Arrays.fill(ans, -1);
        int i = 0;
        while (que.size() > 0) {
            int s = que.size();
            while (s-- > 0) {
                int rVtx = que.removeFirst();
                ans[i++] = rVtx;
                for (int e : graph[rVtx]) {
                    if (--indegree[e] == 0) {
                        que.addLast(e);
                    }
                }
            }
        }
        return ans;
    }

    // 329. Longest Increasing Path in a Matrix :- // https://leetcode.com/problems/longest-increasing-path-in-a-matrix/submissions/

            // -----by making graph // complexity is little bit high
    public int longestIncreasingPath_1(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        int N = n * m;
        ArrayList<Integer>[] graph = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        for (int i = 0; i < N; i++) {
            int sr = i / m;
            int sc = i % m;
            for (int d = 0; d < 4; d++) {
                int r = sr + dir[d][0];
                int c = sc + dir[d][1];

                if (r >= 0 && c >= 0 && r < n && c < m && matrix[sr][sc] < matrix[r][c]) {
                    graph[i].add(r * m + c);
                }
            }
        }

        int[] indegree = new int[N];

        for (int i = 0; i < N; i++) {
            for (int e : graph[i]) {
                indegree[e]++;
            }
        }

        LinkedList<Integer> que = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            if (indegree[i] == 0) {
                que.addLast(i);
            }
        }

        int level = 0;

        while (que.size() > 0) {
            int s = que.size();
            while (s-- > 0) {
                int rVtx = que.removeFirst();

                for (int e : graph[rVtx]) {
                    if (--indegree[e] == 0) {
                        que.addLast(e);
                    }
                }
            }
            level = level + 1;
        }
        return level;
    }

            // -----by dont making graph // complexity is little bit low
    public int longestIncreasingPath_2(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        int N = n * m;
        int[] indegree = new int[N];

        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        for (int i = 0; i < N; i++) {
            int sr = i / m;
            int sc = i % m;
            for (int d = 0; d < 4; d++) {
                int r = sr + dir[d][0];
                int c = sc + dir[d][1];

                if (r >= 0 && c >= 0 && r < n && c < m && matrix[sr][sc] < matrix[r][c]) {
                    int idx = r * m + c;
                    indegree[idx]++;
                }
            }
        }

        LinkedList<Integer> que = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            if (indegree[i] == 0) {
                que.addLast(i);
            }
        }

        int level = 0;

        while (que.size() > 0) {
            int s = que.size();
            while (s-- > 0) {
                int rVtx = que.removeFirst();
                int sr = rVtx / m;
                int sc = rVtx % m;
                for (int d = 0; d < 4; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];

                    if (r >= 0 && c >= 0 && r < n && c < m && matrix[sr][sc] < matrix[r][c]) {
                        int idx = r * m + c;
                        if (--indegree[idx] == 0) {
                            que.addLast(idx);
                        }
                    }
                }
            }
            level = level + 1;
        }
        return level;
    }

    // O(2E)
    public static void display(ArrayList<Edge>[] graph) {
        int N = graph.length;
        for (int i = 0; i < N; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i]) {
                System.out.print("(" + e.v + ", " + e.w + ") ");
            }
            System.out.println();
        }
    }

    public static void constructGraph() {
        int N = 7;
        ArrayList<Edge>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        addEdge(graph, 0, 1, 10);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 40);
        addEdge(graph, 3, 0, 10);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 2);
        addEdge(graph, 5, 6, 3);
        addEdge(graph, 6, 4, 8);

        display(graph);
        boolean[] vis = new boolean[N];
        ArrayList<Integer> ans = topologicalOrder__dfs1(graph);
        System.out.println(ans);

    }

        public static void main(String[] args) {
            constructGraph();
        }
}
    
