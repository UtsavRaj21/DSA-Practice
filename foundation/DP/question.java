import java.io.*;
import java.util.*;

public class question {

    // 62
    public int mazePath_HDV_DP(int SR, int SC, int er, int ec, int[][] dir, int[][] dp) {

        for (int sr = er; sr >= 0; sr--) {
            for (int sc = ec; sc >= 0; sc--) {
                if (sr == er && sc == ec) {
                    dp[sr][sc] = 1;
                    continue;
                }

                int count = 0;
                for (int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];
                    if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                        count += dp[r][c];// mazePath_HDV(r, c, er, ec, dir, dp);
                    }
                }

                dp[sr][sc] = count;
            }
        }

        return dp[SR][SC];
    }

    public int uniquePaths(int n, int m) {
        int[][] dp = new int[n][m];
        int[][] dir = { { 1, 0 }, { 0, 1 } };

        return mazePath_HDV_DP(0, 0, n - 1, m - 1, dir, dp);
    }

    // 63
    public int mazePath_HDV_DP(int SR, int SC, int er, int ec, int[][] dir, int[][] dp, int[][] obstacleGrid) {

        for (int sr = er; sr >= 0; sr--) {
            for (int sc = ec; sc >= 0; sc--) {
                if (sr == er && sc == ec) {
                    dp[sr][sc] = 1;
                    continue;
                }

                int count = 0;
                for (int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];
                    if (r >= 0 && c >= 0 && r <= er && c <= ec && obstacleGrid[r][c] == 0) {
                        count += dp[r][c];// mazePath_HDV(r, c, er, ec, dir, dp);
                    }
                }

                dp[sr][sc] = count;
            }
        }

        return dp[SR][SC];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length, m = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[n - 1][m - 1] == 1)
            return 0;

        int[][] dp = new int[n][m];
        int[][] dir = { { 1, 0 }, { 0, 1 } };

        return mazePath_HDV_DP(0, 0, n - 1, m - 1, dir, dp, obstacleGrid);
    }

    //980

    public int uniquePathsIII(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int ways = n * m;
        int sr = 0, sc = 0, dr = 0, dc = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == -1) {
                    ways--;
                }
                if (grid[i][j] == 1) {
                    sr = i;
                    sc = j;
                }
                if (grid[i][j] == 2) {
                    dr = i;
                    dc = j;
                }
            }
        }

        boolean[][] vis = new boolean[n][m];
        // int[][] dir = new int[n][m];
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        int ans = unique(grid, sr, sc, dr, dc, ways, vis, dir);
        return ans;
    }

    public int unique(int[][] grid,int sr,int sc,int dr, int dc,int ways,boolean[][] vis,int[][] dir){
        int n = grid.length, m = grid[0].length;
        if(sr == dr && sc == dc){
             System.out.println(sr+" "+sc);
            if(ways == 1){
                 System.out.println("yes");
                return 1;
            }
            return 0;
        }
        
        if(ways<1){
            return 0;
        }

        vis[sr][sc] = true;
        int ans=0;
        ways = ways-1;
        System.out.println(sr+" "+sc+" " +ways);
        for(int i=0;i<dir.length;i++){
            int r =sr+ dir[i][0];
            int c = sc+dir[i][1];
             
            if(r>=0 && c>=0 && r<n && c<m && grid[r][c]!=-1 && !vis[r][c]){
               
                ans+=unique(grid, r, c, dr, dc,ways , vis, dir);
            }
        }
        vis[sr][sc] = false;
        return ans;
    }
}