import java.util.*;

public class bfs_ques {

    // 994 https://leetcode.com/problems/rotting-oranges/

    // 0 :- representing an empty cell,
    // 1 :- representing a fresh orange, or
    // 2 :- representing a rotten orange.

    public int orangesRotting(int[][] grid) {
        LinkedList<Integer> que = new LinkedList<>();
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        int n = grid.length, m = grid[0].length;
        int freshOranges = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    freshOranges++;
                } else if (grid[i][j] == 2) {
                    que.addLast(m * i + j);
                    grid[i][j] = 2; // mark visited
                }
            }
        }

        if (freshOranges == 0) {
            return 0;
        }

        int level = 0;

        while (que.size() != 0) {
            int s = que.size();
            while (s-- > 0) {
                int rIdx = que.removeFirst();

                int sr = rIdx / m;
                int sc = rIdx % m;

                //// mark visited
                for (int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];

                    if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1) {
                        if (--freshOranges == 0) {
                            return level + 1;
                        }
                        grid[r][c] = 2;
                        que.addLast(r * m + c);

                    }
                }

            }
            level++;
        }
        return -1;
    }

    // 1091. Shortest Path in Binary Matrix :-
    // https://leetcode.com/problems/shortest-path-in-binary-matrix/

    public static int shortestPath(int[][] grid, int i, int j, int[][] dir) {
        int n = grid.length, m = grid[0].length;
        if (i == n - 1 && j == m - 1) {
            return 1;
        }

        grid[i][j] = 1;
        int min = (int) 1e9;
        for (int d = 0; d < dir.length; d++) {
            int r = i + dir[d][0];
            int c = j + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 0) {
                min = Math.min(min, shortestPath(grid, r, c, dir));
            }
        }
        grid[i][j] = 0;
        return min+1;

    }

    public static int shortestPathBinaryMatrix_dfs(int[][] grid) {       // TLE
        int n = grid.length, m = grid[0].length;
        if (grid[0][0] == 1 || grid[n - 1][m - 1] == 1)
            return -1;

        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }, { -1, 1 }, { 1, 1 }, { 1, -1 }, { -1, -1 } };

        return shortestPath(grid, 0, 0, dir);

    }

    public static int shortestPathBinaryMatrix_bfs(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        if (n == 0 || m == 0)
            return 0;
        if (grid[0][0] == 1 || grid[n - 1][m - 1] == 1)
            return -1;
        LinkedList<Integer> que = new LinkedList<>();

        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }, { -1, 1 }, { 1, 1 }, { 1, -1 }, { -1, -1 } };
        que.addLast(0);
        int shortestPath = 1;
        while (que.size() != 0) {
            int s = que.size();
            while (s-- > 0) {
                int rIdx = que.removeFirst();
                int sr = rIdx / m;
                int sc = rIdx % m;

                if (sr == n - 1 && sc == m - 1) { 
                    return shortestPath;
                }
                for (int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];

                    if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 0) {
                        grid[r][c] = 1;
                        que.addLast(r * m + c);
                    }
                }

            }
            shortestPath++;
        }
        return -1;
    }

    //542. 01 Matrix :- https://leetcode.com/problems/01-matrix/

    public static int[][] updateMatrix_1(int[][] grid) {
        
        int n = grid.length, m = grid[0].length;
        LinkedList<Integer> que = new LinkedList<>();
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        boolean[][] vis = new boolean[n][m];
        for(int i=0 ; i<n ; i++){
            for(int j =0 ; j< m ; j++){
                if(grid[i][j] == 0){
                    vis[i][j] = true;
                    que.addLast(i * m +j);
                }
            }
        }

        while(que.size()!=0){
            int s = que.size();
            while(s-->0){
                int rIdx = que.removeFirst();
                int sr = rIdx / m;
                int sc = rIdx % m;

                for(int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];

                    if (r >= 0 && c >= 0 && r < n && c < m && !vis[r][c]) {
                        grid[r][c] = grid[sr][sc] + 1;
                        que.addLast(r * m + c);
                        vis[r][c] = true;
                    }
                }

            }
        }
        return grid;
    }

    // 785. Is Graph Bipartite? :- https://leetcode.com/problems/is-graph-bipartite/

    public boolean isBipartite(int[][] graph) {
        int n=graph.length;
        int[] vis=new int[n];
        Arrays.fill(vis,-1);
        for(int i=0;i<n;i++){
            if(vis[i]==-1 && !Bipartite(graph,i,vis)){
                return false;
            }
        }
        return true;
    }
    
    public boolean Bipartite(int[][] graph,int src , int[] vis){
        int color=0;
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);
        while(que.size()!=0){
            int s = que.size();
            while(s-- > 0){
                int rVtx = que.removeFirst();

                if(vis[rVtx]!=-1){
                    if(vis[rVtx]!=color){
                        return false;
                    }
                    continue;
                }

                vis[rVtx] = color;
                for(int e:graph[rVtx]){
                    if(vis[e] == -1){
                        que.addLast(e);
                    }
                }
            }
            color = (color+1)%2;
        }
       
        return true;
    }

    //886 :- https://leetcode.com/problems/possible-bipartition/submissions/ 

    ArrayList<Integer>[] graph;
    
    public boolean possibleBipartition(int n, int[][] dislikes) {
        graph = new ArrayList[n];
        
        for(int i=0;i<n;i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int[] edge :dislikes){
            graph[edge[0]-1].add(edge[1]-1);
            graph[edge[1]-1].add(edge[0]-1);
        }
        
        return isBipartite();
    }

    // leetcode 286 , lintcoe :- https://www.lintcode.com/problem/663/

    public void wallsAndGates(int[][] rooms) {
        int n = rooms.length, m = rooms[0].length;
        LinkedList<Integer> que = new LinkedList<>();
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        boolean[][] vis = new boolean[n][m];
        for(int i=0 ; i<n ; i++){
            for(int j =0 ; j< m ; j++){
                if(rooms[i][j] == 0){
                    vis[i][j] = true;
                    que.addLast(i * m +j);
                }
            }
        }

        while(que.size()!=0){
            int s = que.size();
            while(s-->0){
                int rIdx = que.removeFirst();
                int sr = rIdx / m;
                int sc = rIdx % m;

                for(int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];

                    if (r >= 0 && c >= 0 && r < n && c < m && !vis[r][c] && rooms[r][c]!= -1) {
                        rooms[r][c] = rooms[sr][sc] + 1;
                        que.addLast(r * m + c);
                        vis[r][c] = true;
                    }
                }

            }
        }
    }

    //1376. Time Needed to Inform All Employees

    public static void main(String[] args) {

        int[][] grid = { { 0, 0, 0 }, { 1, 1, 0 }, { 1, 1, 0 } };
        shortestPathBinaryMatrix_bfs(grid);
    }
}
