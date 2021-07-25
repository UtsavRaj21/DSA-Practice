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
