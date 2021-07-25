import java.util.*;

public class dfs_ques {

    // 200 leetcode :- https://leetcode.com/problems/number-of-islands/

    // t(o) :E + V => (n*m)=(row*col)

    public int numIslands(char[][] grid) {
        int count = 0;
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    islands(grid, i, j, dir);
                }
            }
        }
        return count;
    }

    public void islands(char[][] grid, int i, int j, int[][] dir) {
        int n = grid.length, m = grid[0].length;
        grid[i][j] = '0';
        for (int z = 0; z < dir.length; z++) {
            int r = i + dir[z][0];
            int c = j + dir[z][1];

            if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == '1') {
                islands(grid, r, c, dir);
            }
        }
    }

    // 695 :- https://leetcode.com/problems/max-area-of-island/

    // t(o) :E + V => (n*m)=(row*col)

    public int maxAreaOfIsland(int[][] grid) {
        int count = 0, max = 0;
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                count = 0;
                if (grid[i][j] == 1) {
                    count = islands(grid, i, j, dir);
                    max = Math.max(max, count);
                }
            }
        }
        return max;
    }

    public int islands(int[][] grid, int i, int j, int[][] dir) {
        int n = grid.length, m = grid[0].length;
        grid[i][j] = 0;
        int count = 0;
        for (int z = 0; z < dir.length; z++) {
            int r = i + dir[z][0];
            int c = j + dir[z][1];

            if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1) {
                count += islands(grid, r, c, dir);
            }
        }
        return count + 1;
    }

    // 463 :- https://leetcode.com/problems/island-perimeter/

    public int islandPerimeter(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dir = { { 1, 0 }, { 0, 1 } };

        int onceCount = 0, nbrCount = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    onceCount++;
                    for (int d = 0; d < 2; d++) {
                        int r = i + dir[d][0];
                        int c = j + dir[d][1];

                        if (r < n && c < m && grid[r][c] == 1) {
                            nbrCount++;
                        }
                    }
                }
            }
        }

        return 4 * onceCount - 2 * nbrCount;
    }

    // 130 :- https://leetcode.com/problems/surrounded-regions/

    public void surrounded_dfs(char[][] grid, int i, int j, int[][] dir) {
        int n = grid.length, m = grid[0].length;
        grid[i][j] = '$';
        for (int z = 0; z < dir.length; z++) {
            int r = i + dir[z][0];
            int c = j + dir[z][1];

            if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 'O') {
                surrounded_dfs(grid, r, c, dir);
            }
        }

    }

    public void surrounded(char[][] board) {
        int n = board.length;
        int m = board[0].length;

        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if ((i == 0 || j == 0 || i == n - 1 || j == m - 1) && (board[i][j] == 'O')) {
                    surrounded_dfs(board, i, j, dir);
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '$') {
                    board[i][j] = 'O';
                } else {
                    board[i][j] = 'X';
                }
            }
        }

    }

    // https://www.hackerrank.com/challenges/journey-to-the-moon/problem

    public static int moonDFS(ArrayList<Integer>[] graph, int src, boolean[] vis) {
        vis[src] = true;
        int size = 0;
        for (Integer e : graph[src]) {
            if (!vis[e])
                size += moonDFS(graph, e, vis);
        }
        return size + 1;
    }

    public static long journeyToMoon(int n, int[][] astronaut) {
        ArrayList<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        for (int[] a : astronaut) {
            graph[a[0]].add(a[1]);
            graph[a[1]].add(a[0]);
        }

        ArrayList<Integer> sizeArray = new ArrayList<>();
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!vis[i])
                sizeArray.add(moonDFS(graph, i, vis));
        }
        int ssf = 0;
        long res = 0;
        for (int ele : sizeArray) {
            res += ele * ssf;
            ssf += ele;
        }
        return res;

    }

    // 1905 https://leetcode.com/problems/count-sub-islands/

    public boolean countSubIslands_dfs(int[][] grid1, int[][] grid2, int i, int j,int[][] dir) {
        int n = grid2.length , m = grid2[0].length ;
        grid2[i][j] = 0;
        boolean res = true;
        for (int z = 0; z < dir.length; z++) {
            int r = i + dir[z][0];
            int c = j + dir[z][1];

            if (r >= 0 && c >= 0 && r < n && c < m && grid2[r][c] == 1) {
                res = countSubIslands_dfs(grid1, grid2, r, c, dir) && res;
            }
        }
        return res && grid1[i][j] == 1;
    }

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int n = grid2.length , m = grid2[0].length ;
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid2[i][j] == 1) {
                    boolean res = countSubIslands_dfs(grid1, grid2, i, j, dir);
                    if(res) {
                        count = count+1;
                    }
                }
            }
        }
        return count;
    }

    // https://www.lintcode.com/problem/860/

    static StringBuilder sb;

    public static void DistinctIslands(int[][] grid, int i, int j, int[][] dir, String[] dirS) {
        int n = grid.length, m = grid[0].length;
        grid[i][j] = 0;
        for (int z = 0; z < dir.length; z++) {
            int r = i + dir[z][0];
            int c = j + dir[z][1];
            if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1) {
                sb.append(dirS[z]);
                DistinctIslands(grid, r, c, dir, dirS);
            }
        }
        sb.append("b");
    }

    public static int numberofDistinctIslands(int[][] grid) {
        HashSet<String> map = new HashSet<>();
        int n = grid.length, m = grid[0].length;
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        String[] dirS = { "D", "U", "R", "L" };
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    sb = new StringBuilder();
                    DistinctIslands(grid, i, j, dir, dirS);
                    map.add(sb.toString());
                    // System.out.println(sb.toString());
                }
                // System.out.print(i +" "+j+" => ");
            }
        }
        return map.size();
    }

    public static void main(String[] args) {
        int[][] grid = { { 1, 1, 0, 0, 1 }, { 1, 0, 0, 0, 0 }, { 1, 1, 0, 0, 1 }, { 0, 1, 0, 1, 1 } };
        int count = numberofDistinctIslands(grid);
        System.out.println(count);

    }

}
