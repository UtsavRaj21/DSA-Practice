public class test {

    public static int find(char[][] grid,int k){
        int rs=0,cs=0,re=0,ce = 0;              //row start , col start , row end , col end
        int n = grid.length , m = grid[0].length;
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                char c = grid[i][j];
                if(c=='S'){
                    rs = i;
                    cs = j;
                }
                if(c=='E'){
                    re = i;
                    ce = j;
                }
            }
        }

        boolean[][] vis = new boolean[n][m];
        int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};

        solve(grid,)

    }
    public static void main(String[] args) {
        char[][] arr = {{'a','b'}};
        solve(arr,3);
    }
}
