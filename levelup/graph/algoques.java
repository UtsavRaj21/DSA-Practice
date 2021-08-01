import java.util.*;

public class algoques {

    static int[] par;
    static int[] size;

    public static int parFind(int src){
        if(par[src] == src){
            return src;
        }

        return par[src] = parFind(par[src]);
    }

    //LeetCode: Optimize Water Distribution in a Village 

    public static int mincost(int n,int[] wells,int[][] pipes){
        ArrayList<int[]> allpipes = new ArrayList<>();
        for(int[] a: pipes) allpipes.add(a);
        for(int i=0 ; i<wells.length;i++){
            allpipes.add(new int[]{0,i+1,wells[i]});
        }

        par = new int[n+1];
        int ans = 0;

        for(int i =0  ;i<=n;i++){
            par[i] = i;
        }

        for(int[] a : allpipes){
            int u = a[0],v=a[1],w=a[2];
            int p1 = parFind(u),p2=parFind(v);
            if(p1!=p2){
                par[p2] = p1;
                ans+=w;
            }
        }

        return ans;
    }

    //hackerEarth : Mr. President ----------------
    
    public static int mrPresident(int[][] edges, int N, int K) {
        par = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            par[i] = i;
        }

        Arrays.sort(edges, (a, b) -> {
            return a[2] - b[2];
        });

        ArrayList<Integer> roads = new ArrayList<>();

        int components = N, mcost = 0;
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            int p1 = parFind(u), p2 = parFind(v);
            if (p1 != p2) {
                par[p1] = p2;
                components--;
                mcost += w;
                roads.add(w);
            }
        }

        if (components > 1)
            return -1;

        int superroad = 0;
        for (int i = roads.size() - 1; i >= 0; i--) {
            if (mcost <= K)
                break;
            mcost = mcost - roads.get(i) + 1;
            superroad++;
        }

        return mcost <= K ? superroad : -1;
    }

    //959. Regions Cut By Slashes :- https://leetcode.com/problems/regions-cut-by-slashes/

    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        par = new int[(n+1)*(n+1)];
        size = new int[(n+1)*(n+1)];

        for(int i =0 ;i<par.length;i++){
            par[i] = i;
            int r = i/n,c=i%n;
            if(r==0 || c==0 || r==n || c==n){
                par[i] = 0; 
            }
        }

        int max = 1;

        for(int i =0 ; i<n;i++){
            String s = grid[i];
            for(int j =0;j<s.length();j++){
                if(s.charAt(j) == ' '){
                    continue;
                }
                int idx1 =0;
                int idx2 =0;
                if(s.charAt(j) == '/'){
                     idx1 = i*n+(j+1);
                     idx2= (i+1)*n+j;
                    
                }else if(s.substring(j,j+1).equals("\\")){
                     idx1 = i*n+j;
                     idx2 = (i+1)*n+(j+1);
                    
                }
                int p1 = parFind(idx1);
                int p2 = parFind(idx2);
                if(p1!=p2){
                    par[p2] = p1;

                }else{
                    max++;
                }
               
            }
        }
        return max;
    }

    // 815. Bus Routes

    public int numBusesToDestination(int[][] routes, int source, int target) {
        int n = routes.length;
        HashMap<Integer,ArrayList<Integer>> map = new HashMap<>();
        for(int bus =0;bus<routes.length;bus++){
            
            for(int busStand:routes[bus]){
                map.putIfAbsent(bus, new ArrayList<>());;
                map.get(busStand).add(bus);
            }
        }

        HashSet<Integer> busStandVis = new HashSet<>();
        boolean[] busVis = new boolean[n];

        int interchange = 0;
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(source);
        busStandVis.add(source);

        while(que.size()>0){
            int s = que.size();
            while(s-->0){
                int busStand = que.removeFirst();
                for(int bus : map.get(busStand)){
                    if(busVis[bus]){
                        continue;
                    }
                    for(int upcomingBusStand : routes[bus]){
                        if(!busStandVis.contains(busStand)){
                            busStandVis.add(busStand);
                            que.add(busStand);
                            if(upcomingBusStand == target){
                                return interchange +1;
                            }
                        }
                    }
                    busVis[bus] = true;
                }
            }
            interchange++;
        }
        return interchange;
    }
    
    //685. Redundant Connection II :- https://leetcode.com/problems/redundant-connection-ii/

    public int[] findRedundantDirectedConnection(int[][] edges) {
        int m = edges.length;
        int n = m+1;
        par = new int[n];
        for(int i = 0 ; i< n; i++){
            par[i]=i;
        }

        int[] arr = new int[n];
        int[] ans1=  null;
        int[] ans2=  null;
        for(int[] edge: edges){
            int u = edge[0] , v = edge[1];
            if(arr[v]>0){
                ans1 = new int[]{arr[v],v};
                ans2 = edge;
            }
            arr[v] = u;
        }

        for(int[] edge: edges){
            int u = edge[0] , v = edge[1];
            if(ans2 == edge) continue;
            int p1 = parFind(u);
            int p2 = parFind(v);
            if(p1!=p2){
                par[p2] = p1;
            }else{
                return ans1 == null?edge:ans1;
            }
        }

        return ans2;
    }
    public static void main(String[] args) {
        // int n =3;
        // int[] wells = {1,2,2};
        // int[][] pipes = {{1,2,1},{2,3,1}};
        // System.out.println(mincost(n,wells,pipes));

        Scanner s = new Scanner(System.in);
        String name = s.nextLine();
        String[] arr = name.split(" ");
        
        int n = Integer.parseInt(arr[0]);
        int m = Integer.parseInt(arr[1]);
        int k = Integer.parseInt(arr[2]);

        int[][] edges = new int[m][3];

        for(int i = 0 ; i < m ; i++){
            String na = s.nextLine();
            String[] a = na.split(" ");
            int u = Integer.parseInt(a[0]);
            int v = Integer.parseInt(a[1]);
            int w = Integer.parseInt(a[2]);

            edges[i][0] = u;
            edges[i][1] = v;
            edges[i][2] = w;
        }

        System.out.println(mrPresident(edges,n,k));
    }
}
