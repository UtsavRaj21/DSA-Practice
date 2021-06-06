public class basic{

    public static class pair{
        int count;
        int max;
        String s1;
        int min;
        String s2;
            pair(int count,int max,String s1,int min , String s2){
                this.count=count;
                this.max=max;
                this.s1=s1;
                this.min=min;
                this.s2=s2;

            }
    }

    public static pair floodfill(int sr,int sc,int dr,int dc,String[] dir,int[][] dirs,boolean[][] vis,String str,pair res){
        if(sr==dr-1 && sc==dc-1){
            System.out.println(str);
            res.count++;
            if(str.length()>res.max){
                res.max=str.length();
                res.s1=str;
            }
            if(str.length()<res.min){
                res.min=str.length();
                res.s2=str;
            }
            return res;
        }
        
        vis[sr][sc]=true;
        for(int i=0;i<dirs.length;i++){
            int r=sr+dirs[i][0];
            int c=sc + dirs[i][1];
            if(r>=0 && c>=0 && r<dr && c<dc && !vis[r][c]){
                res = floodfill(r,c,dr,dc,dir,dirs,vis,str+dir[i]+" ",res);
            }
        }
        vis[sr][sc]=false;
        return res;
    }

    public static void floodfillQ(){
        int n=4;
        int m=4;
        int[][] arr=new int[n][m];
        int[][] dirs={{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{1,-1}};
        String[] dir={"u","e","r","s","d","w","l","n"};
        boolean[][] vis=new boolean[n][m];
        pair res=new pair(0,0,"",(int)1e8,"");
        pair ans =floodfill(0,0,n,m,dir,dirs,vis,"",res);
        System.out.println(ans.count);
        System.out.println((ans.max)/2);
        System.out.println(ans.s1);
        
        System.out.println((ans.min/2));
        System.out.println(ans.s2);
    }

        public static int floodFill_helper(int sr,int sc, int er,int ec,int[][] dir, String[] s ,String ans,boolean[][] vis){
            if(sc == ec && sr == er ){
                System.out.println(ans);
                return 1;
            }
             int count = 0;
             vis[sr][sc] = true;
            for(int d = 0 ; d < dir.length ; d++){
                int r = sr + dir[d][0];
                int c = sc + dir[d][1];
                if(r >= 0 && c >= 0 && r <= er && c <= ec && !vis[r][c]){
                    count += floodFill_helper(r , c , er , ec , dir , s , ans + s[d],vis);
                } 
                
            }
            vis[sr][sc] = false;
            return count;
        }
    
        public static void floodFillA(){
           int n = 4;
           int m = 4;
           int[][] dir ={{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
           String[] s = {"t","tr","r","dr","d","dl","l","tl"};
           boolean[][] vis = new boolean[n][m];
    
           System.out.println(floodFill_helper(0,0,n-1,m-1,dir,s,"",vis));
        }
    


    public static void main(String[] args){
        // floodfillQ();
        floodFillA();
    }


}