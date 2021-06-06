import java.util.Arrays;
import java.util.LinkedList;

public class basic {
    public static void print(int[] arr) {
        for (int ele : arr)
            System.out.print(ele + " ");
        System.out.println();
    }

    public static void print2D(int[][] arr) {
        for (int[] a : arr) {
            print(a);
        }

        System.out.println();
    }

    public static int fibo_memo(int n, int[] dp) {              // memorization
        if (n <= 1) {
            return dp[n] = n;
        }

        if (dp[n] != -1)
            return dp[n];

        int ans = fibo_memo(n - 1, dp) + fibo_memo(n - 2, dp);
        return dp[n] = ans;
    }

    public static int fibo_DP(int N, int[] dp) {                // tabulation
        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = n;                                     //return -> continue
                continue;
            }

            int ans = dp[n - 1] + dp[n - 2];// fibo_memo(n - 1, dp) + fibo_memo(n - 2, dp);
            dp[n] = ans;
        }

        return dp[N];
    }

    public static int fibo_Opti(int n) {                        //optimization

        int a = 0, b = 1;
        for (int i = 0; i < n; i++) {
            System.out.print(a + " ");
            int sum = a + b;
            a = b;
            b = sum;
        }

        return a;
    }

    public static void fibo() {
        int n = 6;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        fibo_DP(n, dp);
        System.out.println(fibo_Opti(n));

        print(dp);
    }

    public static int dice_memo(int n, int[] dp) {              // memorization
        if (n == 0) {
            return dp[n] = 1;
        }

        if (dp[n] != -1)
            return dp[n];
        int ans=0;
        // if(n<6){
            
        //     // if(n==1){
        //     //     ans = dice_memo(n - 1, dp) ;
        //     // }else if(n==2){
        //     //     ans = dice_memo(n - 1, dp)+dice_memo(n - 2, dp) ;
        //     // }else if(n==3){
        //     //     ans = dice_memo(n - 1, dp)+dice_memo(n - 2, dp)+dice_memo(n - 3, dp) ;
        //     // }else if(n==4){
        //     //     ans = dice_memo(n - 1, dp)+dice_memo(n - 2, dp)+dice_memo(n - 3, dp)+dice_memo(n - 4, dp) ;
        //     // }else if(n==5){
        //     //     ans = dice_memo(n - 1, dp) +dice_memo(n - 2, dp)+dice_memo(n - 3, dp)+dice_memo(n - 4, dp)+dice_memo(n - 5, dp);
        //     // }
        // } else {
        //     for(int i=1;i<=6;i++){
        //         ans+=dice_memo(n - i, dp) ;
        //     }
        //     //ans = dice_memo(n - 1, dp) +dice_memo(n - 2, dp)+dice_memo(n - 3, dp)+dice_memo(n - 4, dp)+dice_memo(n - 5, dp)+dice_memo(n - 6, dp);
        //}

        for(int dice=1;dice<=6 && n-dice >=0;dice++){
            ans+=dice_memo(n - dice, dp) ;
        }
        
        return dp[n] = ans;
    }

    public static int dice_DP(int N, int[] dp) {                //tabulation
        
        for(int n=0;n<=N;n++){
            if (n == 0) {
            dp[n] = 1;
            continue;
            }

            int ans=0;
            for(int dice=1;dice<=6 && n-dice >=0;dice++){
                ans+=dp[n - dice];
            }
            
            dp[n] = ans;
        }
        return dp[N];
    }

    public static int dice_opti(int n){                         //optimization
        LinkedList<Integer> l=new LinkedList<>();
        int s=1;
        l.addLast(1);
        l.addLast(1);
        for(int i=2;i<=n;i++){
            if(l.size()<7){
                s=s*2;           
                l.addLast(s);         // l.addLast(li.getLast()*2 )
            }else{
                s=s*2-l.removeFirst();
                l.addLast(s);
            }
        }
        return s;             // return li.getLast();
    }

    public static void Dice(){
        int n = 10;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        //dice_memo(n, dp);
        // dice_DP(n, dp);
        
        // System.out.println(dp[n]);

        System.out.println(dice_opti(n));
    }
    
    public static int getMazePaths_memo(int sr, int sc, int dr, int dc,int dir[][],int[][] dp) {
        if(sr==dr && sc==dc){
            return dp[sr][sc]=1;
        }
        if(dp[sr][sc]!=0){
            return dp[sr][sc];
        }
        int ans=0;
        for(int i=0;i<dir.length;i++){
            int r=sr+dir[i][0];
            int c=sc+dir[i][1];
            if(r<=dr && c<=dc && c>=0 && r>=0){
                ans+=getMazePaths_memo(r,c,dr,dc,dir,dp);
            }
        }
        
        return dp[sr][sc]=ans;
    }

    public static int getMazePaths_tab(int SR, int SC, int dr, int dc,int dir[][],int[][] dp) {
        for(int sr=dr;sr>=0;sr--){
            for(int sc=dc;sc>=0;sc--){
                if(sr==dr && sc==dc){
                    dp[sr][sc]=1;
                    continue;
                }
            
                int ans=0;
                for(int i=0;i<dir.length;i++){
                    int r=sr+dir[i][0];
                    int c=sc+dir[i][1];
                    if(r<=dr && c<=dc && c>=0 && r>=0){
                        ans+=dp[r][c];
                    }
                }
                dp[sr][sc]=ans;
            }
        }
        return dp[SR][SC];
        
    }
  
    public static void maze(){
        int n=3;
        int m=3;
        
        int[][] dp=new int[n][m];
        int[][] dir={{1,0},{1,1},{0,1}};
        
        // getMazePaths_memo(0,0,n-1,m-1,dir,dp);
        // System.out.println(dp[0][0]);
        System.out.println(getMazePaths_tab(0,0,n-1,m-1,dir,dp));
    }
   
    public static int getMazeJump_memo(int sr, int sc, int dr, int dc,int dir[][],int[][] dp) {
        if(sr==dr && sc==dc){
            return dp[sr][sc]=1;
        }
        if(dp[sr][sc]!=0){
            return dp[sr][sc];
        }
        int ans=0;
        for(int i=0;i<dir.length;i++){
            for(int rad=1;rad<=Math.max(dr,dc);rad++){
                int r=sr+rad*dir[i][0];
                int c=sc+rad*dir[i][1];
                if(r<=dr && c<=dc && c>=0 && r>=0){
                    ans+=getMazeJump_memo(r,c,dr,dc,dir,dp);
                }else{
                    break;
                }
            }
           
        }
        
        return dp[sr][sc]=ans;
    }

    public static int getMazeJump_tab(int SR, int SC, int dr, int dc,int dir[][],int[][] dp) {
        for(int sr=dr;sr>=0;sr--){
            for(int sc=dc;sc>=0;sc--){
                if(sr==dr && sc==dc){
                    dp[sr][sc]=1;
                    continue;
                }
            
                int ans=0;
                for(int i=0;i<dir.length;i++){
                    for(int rad=1;rad<=Math.max(dr,dc);rad++){                   //dr(row length)
                        int r=sr+rad*dir[i][0];
                        int c=sc+rad*dir[i][1];
                    if(r<=dr && c<=dc && c>=0 && r>=0){
                        ans+=dp[r][c];
                    }
                }
            }
                dp[sr][sc]=ans;
            }
        }
        return dp[SR][SC];
        
    }

    public static void maze_jump(){
        int n=3;
        int m=3;
        
        int[][] dp=new int[n][m];
        int[][] dir={{1,0},{1,1},{0,1}};
        
        // getMazeJump_memo(0,0,n-1,m-1,dir,dp);
        // System.out.println(dp[0][0]);

        System.out.println(getMazeJump_tab(0,0,n-1,m-1,dir,dp));
    }
    public static void main(String[] args) {
        //fibo();
        //Dice();
        //maze();
        maze_jump();
    }
}