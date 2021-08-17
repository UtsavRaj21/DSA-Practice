import java.util.*;

public class dsuquestion {
    static int[] par;
    static int[] size;

    public static int parFind(int src){
        if(par[src] == src){
            return src;
        }

        return par[src] = parFind(par[src]);
    }

    //695 Max Area of Island :- https://leetcode.com/problems/max-area-of-island/
    public static int maxAreaOfIsland(int[][] grid){
        int n = grid.length,m=grid[0].length;
        if(n == 0 || m==0) return 0;
        par = new int[n*m];
        size = new int[n*m];

        int[][] dir = {{1,0},{0,1}};

        int count=0;

        for(int i = 0 ; i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j] == 1){
                    count++;
                }
                par[i*m+j] =i*m+j;
                size[i*m+j] = 1;
            }
        }
        int maxArea = 0;
        for(int i = 0 ; i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j] == 1){
                    int p1 = parFind(i*m+j);

                    for(int d=0;d<dir.length;d++){
                        int r = i+dir[d][0];
                        int c = j+dir[d][1];

                        if(r>=0 && c>=0 && r<n && c<m && grid[r][c] ==1){
                            int p2 = parFind(r*m+c);
                            if(p1!=p2){
                                par[p2] = p1;
                                size[p1] += size[p2];
                            }
                        }
                    }
                    maxArea=Math.max(maxArea,size[p1]);
                }
            }
        }
        return maxArea;
    }

    //1061
    public static void smallestEquivalentString(String s1,String s2,String s3){
        par = new int[26];
        for(int i=0 ; i<26 ;i++){
            par[i] = i;
        }

        for(int i =0;i<s1.length();i++){
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            int p1 =parFind(c1 -'a');
            int p2 =parFind(c2 - 'a');

            par[p1] = Math.min(p1,p2);
            par[p2] = Math.min(p1,p2);
        }

        StringBuilder sb = new StringBuilder();
        for(int i =0;i<s3.length();i++){
            char c3 = s3.charAt(i);
            char ch = (char)(parFind(c3-'a' )+'a');
            sb.append(ch);
        }

       System.out.println(sb.toString());

    }

    // 990. Satisfiability of Equality Equations :- https://leetcode.com/problems/satisfiability-of-equality-equations/

    public boolean equationsPossible(String[] equations) {
        par = new int[26];
        for(int i=0 ; i<26 ;i++){
            par[i] = i;
        }
        
        for(String s : equations){
            char c1 = s.charAt(0), c2 = s.charAt(1),c3 = s.charAt(3);
            int p1 = parFind(c1-'a');
            int p2 = parFind(c3-'a');
            
            if(c2 == '=' && p1!=p2){
                par[p2] = p1;
            }
        }
        
        for(String s : equations){
            char c1 = s.charAt(0), c2 = s.charAt(1),c3 = s.charAt(3);
            int p1 = parFind(c1-'a');
            int p2 = parFind(c3-'a');
            
            if(c2 == '!' && p1==p2){
                return false;
            }
        }
        return true;
    }

    //839. Similar String Groups :- https://leetcode.com/problems/similar-string-groups/

    public boolean  isSimilar(String s1,String s2) {
        int count =0;
        for(int i=0;i<s1.length() ; i++){
            if((s1.charAt(i)!=s2.charAt(i)) && (++count > 2)){
                return false;
            }
        }
        return true;
    }
    
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        par = new int[n];
        int group=n;
        
        for(int i = 0 ; i<n ;i++) par[i] = i;
        
        for(int i =0;i<n;i++){
            for(int j = i+1 ; j< n ;j++){
                if(isSimilar(strs[i],strs[j])){
                    int p1 = parFind(i);
                    int p2 = parFind(j);
                    
                    if(p1!=p2){
                        group--;
                        par[p2] = p1;
                    }
                }
            }
        }
        return group;
    }

   // 434 · Number of Islands II

   class Point {
         int x;
         int y;
         Point() { x = 0; y = 0; }
         Point(int a, int b) { x = a; y = b; }
     }

   

    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        List<Integer> ans = new ArrayList<>();
        if(n==1 && m==1) return ans;
        int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        par = new int[n*m];
        Arrays.fill(par,-1);
        int count = 0;
        for(Point p : operators){
            int i = p.x;
            int j = p.y;

            if(par[i*m+j] == -1){
                count++;
                par[i * m + j] = i * m + j;

                int p1 = parFind(i * m + j);
                  for (int d = 0; d < dir.length; d++) {
                    int r = i + dir[d][0];
                    int c = j + dir[d][1];

                    if (r >= 0 && c >= 0 && r < n && c < m && par[r * m + c] != -1) {
                        int p2 = parFind(r * m + c);
                        if (p1 != p2) {
                            count--;
                            par[p2] = p1;
                        }
                    }
                }

            }
            ans.add(count);
        }
        return ans;
    }

    //924. Minimize Malware Spread :- https://leetcode.com/problems/minimize-malware-spread/
    
    static int[] poc;         // population of country
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int n = graph.length;
        par = new int[n];
         poc = new int[n];
        
        for(int i = 0 ; i < n ; i++){
            par[i] = i;
            poc[i] = 1;
        }
        
        for(int i = 0 ; i < n ;i++){
             int p1 = parFind(i);
            for(int j=0 ; j<n ; j++){
                if(i!=j){
                 
                    if(graph[i][j] == 1){
                        int p2 =  parFind(j);
                            
                        if(p1!=p2){
                            par[p2] = p1;
                            poc[p1]+=poc[p2];
                        }
                    }
                }
            }
        }
        
        Arrays.sort(initial);
        
        int[] ipc = new int[n];    // infected person in country
        
        for(int ip : initial){
            int c = parFind(ip);
            ipc[c]++;
        }
        
        int maxPopulated = 0 ;
        int c  = initial[0];
        
        for(int ip : initial){
            int p = parFind(ip);
            if(ipc[p] == 1 && maxPopulated < poc[p]){
                maxPopulated = poc[p];
                c=ip;
            }
        }
        
        return c;
        
        
    }
    public static void main(String[] args) {
        String s1 = "parker";
        String s2 = "morris";
        String s3 = "parser";
        smallestEquivalentString(s1,s2,s3);
    }
}
