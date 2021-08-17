import java.util.*;

public class mazeQuestion {


    //787 · The Maze :- LintCode  : https://www.lintcode.com/problem/787/

    public boolean hasPath1(int[][] maze, int[] start, int[] destination) {
       int n = maze.length , m = maze[0].length;

       boolean[][] vis = new boolean[n][m];
       int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};

       LinkedList<Integer> que = new LinkedList<>();
       int sr = start[0] , sc = start[1] , dr = destination[0] , dc = destination[1];
       que.add(sr * m + sc);

       vis[sr][sc] = true;

       int len = Math.max(n,m);

       while(que.size()!=0){
           int s = que.size();
           while(s-->0){
               int rvtx = que.removeFirst();
               int i = rvtx / m;
               int j = rvtx % m;

               for(int[] d : dir){
                   int fr = i; int fc = j;
                   for(int rad = 1 ; rad < len ; rad++){
                       int r = i + rad * d[0];
                       int c = j + rad * d[1];
                        if(r>=0 && c>=0 && r<n && c<m && maze[r][c] == 0){
                            fr = r;
                            fc = c;
                        }else{
                            break;
                        }

                        if(vis[fr][fc]) continue;

                        vis[fr][fc] = true;
                        que.addLast(fr*m+fc);
                        if(fr == dr && fc == dc){
                            return true;
                        }
                   }
               }
           }
       }
       return false;
    }

    public boolean hasPath2(int[][] maze, int[] start, int[] destination) {
        int n = maze.length , m = maze[0].length;
 
        boolean[][] vis = new boolean[n][m];
        int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
 
        LinkedList<Integer> que = new LinkedList<>();
        int sr = start[0] , sc = start[1] , dr = destination[0] , dc = destination[1];
        que.add(sr * m + sc);
 
        vis[sr][sc] = true;
 
        int len = Math.max(n,m);
 
        while(que.size()!=0){
            int s = que.size();
            while(s-->0){
                int rvtx = que.removeFirst();
                int i = rvtx / m;
                int j = rvtx % m;
 
                for(int[] d : dir){
                    int r = i; int c = j;
                   while(r>=0 && c>=0 && r<n && c<m && maze[r][c] == 0){           // radius method 
                        r+=d[0];
                        c+=d[1];
                   }

                   r-=d[0];
                   c-=d[1];

                    if(vis[r][c]) continue;

                    vis[r][c] = true;
                    que.addLast(r*m+c);
                    if(r == dr && c == dc){
                        return true;
                    }
                    
                }
            }
        }
        return false;
     }

     //788 · The Maze II  : LintCode :- https://www.lintcode.com/problem/788/

    
     public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int n = maze.length , m = maze[0].length;
 
        class pair implements Comparable<pair>{                                    //internal pair Local Pair
            int r;
            int c;
            int dis;
            pair(int r,int c,int dis){
                this.r = r;
                this.c =c ;
                this.dis = dis;
            }

            public int compareTo(pair o){
                return this.dis - o.dis;
            }
        }

        int[][] distance = new int[n][m];

        for(int i = 0 ; i < n*m ; i++){
            distance[i/m][i%m] = (int)1e9;
        }
        int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
 
        PriorityQueue<pair> que = new PriorityQueue<>();
        int sr = start[0] , sc = start[1] , dr = destination[0] , dc = destination[1];
        que.add(new pair(sr, sc, 0));

 
        int len = Math.max(n,m);
 
        while(que.size()!=0){
            int s = que.size();
            while(s-->0){

                pair rpair = que.remove();
                
                for(int[] d : dir){
                    int r = rpair.r;
                    int c = rpair.c;
                    int l = rpair.dis;
                   while(r>=0 && c>=0 && r<n && c<m && maze[r][c] == 0){           // radius method 
                        r+=d[0];
                        c+=d[1];
                        l++;
                   }

                   r-=d[0];
                   c-=d[1];
                   l--;

                    if(l >= distance[r][c]) continue;

                    que.add(new pair(r,c,l));
                    distance[r][c]= l;
                    if(r == dr && c == dc){
                        return l;
                    }
                    
                }
            }
        }
        return -1;
    }
}
