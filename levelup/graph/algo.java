import java.util.*;

public class algo {
    public static class Edge {
        int v = 0, w = 0;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));
    }

    public static void display(ArrayList<Edge>[] graph) {
        int N = graph.length;
        for (int i = 0; i < N; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i]) {
                System.out.print("(" + e.v + ", " + e.w + ") ");
            }
            System.out.println();
        }
    }

    static int[] par;
    static int[] size;

    public static int parFind(int src){
        if(par[src] == src){
            return src;
        }

        return par[src] = parFind(par[src]);
    }

    public static void union(int p1,int p2){
        if(size[p1]<size[p2]){
            par[p1] = p2;
            size[p2] += size[p1];
        }else{
            par[p2] = p1;
            size[p1] += size[p2];
        }
    }

    // {{u1,v1,w1},{u2,v2,w2}...}

    //O(v+e)  Or O(v+elog())
    public static void unionFind(int[][] edges,ArrayList<Edge>[] graph) {
        int N = edges.length;
        par = new int[N];
        size = new int[N];

        for (int i = 0; i < N; i++) {
            par  [i] = i;
            size[i] = 1;
        }

        for(int[] edge : edges){
            int u = edge[0] , v = edge[1] , w = edge[2];

            int p1 = parFind(u);
            int p2 = parFind(v);

            if(p1!=p2){
                union(p1,p2);
                addEdge(graph, u, v, w);  // if u want to make graph;
            }
        }
    }

    public static void kruskalAlgo(int[][] edges){
        Arrays.sort(edges,(a,b)->{
            return a[2]-b[2]; 
        });

        int N = edges.length;
        ArrayList<Edge>[] graph = new ArrayList[N]; // if u want to make array;
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        unionFind(edges, graph);
        
    }

    // ArtuicularsPointAndBridge ===================================

    // ArtuicularsPoint(vertex) 
    
    static int[] low,dis;
    static boolean[] articulus,vis;
    static int time = 0,rootcalls=0;
    
    public static void dfs(ArrayList<Edge>[] graph,int src,int par){
        dis[src] = low[src] = time++;
        vis[src] = true;

        for(Edge e: graph[src]){
            if(!vis[e.v]){
                if(par==-1) rootcalls++;
                dfs(graph  , e.v,src);
                if(dis[src] <= low[e.v]){
                    articulus[src] = true;
                }
                low[src] = Math.min(low[src],low[e.v]);
            }else if(e.v != par){
                low[src] = Math.min(low[src],dis[e.v]);
            }
        }
    }
   
    public static void artuicularsPointAndBridge(ArrayList<Edge>[] graph){
        int N = graph.length;
        low = new int[N];
        dis = new int[N];
        articulus = new boolean[N];
        vis = new boolean[N];

        for(int i=0;i<N;i++){
            if(!vis[i]){
                dfs(graph,i,-1);
            }
        }
    }


    // Dijkstra's shortest path algorithm 

    public static class pair{
        int vtx,par,w,wsf;

        pair(int vtx,int par , int w,int wsf){
            this.vtx = vtx;
            this.par = par;
            this.w = w;
            this.wsf = wsf;
        }
        pair(int vtx,int wsf){
            this.vtx = vtx;
            this.wsf = wsf;
        }

       
    }

    public static void dijikstra_01(ArrayList<Edge>[] graph,int src){
        int N = graph.length;
        ArrayList<Edge>[] ngraph = new ArrayList[N];

        for(int i=0;i<N;i++){
            ngraph[i] = new ArrayList<>();
        }

        boolean[] vis = new boolean[N];
        PriorityQueue<pair> pq = new PriorityQueue<>((a,b)->{
            return a.wsf-b.wsf;                                     // for prims (return a.w-b.w ; )
        }) ;

        int[] dis = new int[N];
        int[] par = new int[N];

        pq.add(new pair(src,-1,0,0));

        while(pq.size()!=0){
            pair p = pq.remove();

            if(vis[p.vtx]) continue;
            if(p.par == -1){
                addEdge(ngraph,p.vtx,p.par,p.wsf);
            }
            vis[p.vtx] = true;
            dis[p.vtx] = p.wsf;
            par[p.vtx] =p.par ;

            for(Edge e : graph[p.vtx]){
                if(!vis[e.v]){
                    pq.add(new pair(e.v,p.vtx,e.w,p.wsf+e.w));
                }
            }
        }
    }

    public static void dijikstra_02(ArrayList<Edge>[] graph,int src){
        int N = graph.length;

        PriorityQueue<pair> pq = new PriorityQueue<>((a,b)->{
            return a.wsf-b.wsf;
        }) ;

        int[] dis = new int[N];
        int[] par = new int[N];

        pq.add(new pair(src,0));

        while(pq.size()!=0){
            pair p = pq.remove();

            if(p.wsf >= dis[p.vtx]) continue;
            
            for(Edge e : graph[p.vtx]){
                if(p.wsf+e.w  < dis[e.v]){
                    dis[e.v] = p.wsf+e.w;
                    par[e.v] = p.vtx;
                    pq.add(new pair(e.v,p.wsf+e.w));
                }
            }
        }
    }

    public static class primspair{
        int vtx,w,par;
        primspair(int vtx,int par,int w){
            this.vtx = vtx;
            this.w = w;
            this.par = par;
        }
    }

    public static void primsAlgo_1(ArrayList<Edge>[] graph,int src){
        int N = graph.length;
        ArrayList<Edge>[] ngraph = new ArrayList[N];

        for(int i=0;i<N;i++){
            ngraph[i] = new ArrayList<>();
        }

        boolean[] vis = new boolean[N];
        PriorityQueue<pair> pq = new PriorityQueue<>((a,b)->{
            return a.w-b.w;                                     // for prims (return a.w-b.w ; )
        }) ;

        int[] dis = new int[N];
        int[] par = new int[N];

        pq.add(new pair(src,-1,0,0));

        while(pq.size()!=0){
            pair p = pq.remove();

            if(vis[p.vtx]) continue;
            if(p.par == -1){
                addEdge(ngraph,p.vtx,p.par,p.wsf);
            }
            vis[p.vtx] = true;
            dis[p.vtx] = p.wsf;
            par[p.vtx] =p.par ;

            for(Edge e : graph[p.vtx]){
                if(!vis[e.v]){
                    pq.add(new pair(e.v,p.vtx,e.w,p.wsf+e.w));
                }
            }
        }
    }

    public static void primsAlgo_2(ArrayList<Edge>[] graph,int src){
        int N = graph.length;

        ArrayList<Edge>[] ngraph = new ArrayList[N];
        for(int i=0;i<N;i++){
            ngraph[i] = new ArrayList<>();
        }

        PriorityQueue<primspair> pq = new PriorityQueue<>((a,b)->{
            return a.w-b.w;
        }) ;

        int[] dis = new int[N];         // no use of parents arr
        Arrays.fill(dis,(int)1e9);
        Arrays.fill(par,-1);

        pq.add(new primspair(src,-1,0));

        boolean[] vis = new boolean[N];
        while(pq.size()!=0){
            primspair p = pq.remove();

            if(vis[p.vtx]) continue;
            if(p.par!=-1){
                addEdge(ngraph,p.vtx,p.par,p.w);
            }
            
            vis[p.vtx] = true;
            
            for(Edge e : graph[p.vtx]){
                if(e.w  < dis[e.v]){
                    dis[e.v] =e.w;
                    pq.add(new primspair(e.v,p.vtx,e.w));
                }
            }
        }
    }
}
