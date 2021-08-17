import java.util.*;

public class dijiskstraques {

    // 743. Network Delay Time :-
    // https://leetcode.com/problems/cheapest-flights-within-k-stops/

    public int networkDelayTime(int[][] times, int n, int k) {
        int N = n + 1;
        // {v,w}
        ArrayList<int[]>[] graph = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] a : times) {
            int u = a[0], v = a[1], w = a[2];
            graph[u].add(new int[] { v, w });
        }

        int[] dis = new int[N];
        Arrays.fill(dis, (int) 1e9);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1]; // for prims (return a.w-b.w ; )
        });

        pq.add(new int[] { k, 0 });
        dis[k] = 0;

        while (pq.size() != 0) {
            int[] rp = pq.remove();

            int vtx = rp[0];
            int wsf = rp[1];
            if (wsf > dis[vtx])
                continue;

            for (int[] e : graph[vtx]) {
                int v = e[0], w = e[1];
                if (wsf + w < dis[v]) {
                    dis[v] = wsf + w;
                    pq.add(new int[] { v, wsf + w });
                }
            }
        }

        int max = 0;
        for (int i = 1; i < N; i++) {
            if (dis[i] == (int) 1e9)
                return -1;
            max = Math.max(max, dis[i]);
        }

        return max;
    }

    //787. Cheapest Flights Within K Stops

    public int findCheapestPrice1(int N, int[][] flights, int src, int dst, int k) {
        ArrayList<int[]>[] graph = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] a : flights) {
            int u = a[0], v = a[1], w = a[2];
            graph[u].add(new int[] { v, w });
        }

        int[] dis = new int[N];
        Arrays.fill(dis, (int) 1e9);
        dis[src] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[2] - b[2];
        });

        pq.add(new int[] { src,0,-1  });
        int max = (int)1e9;
        while(pq.size()!=0){
            int[] rv = pq.remove();
            int vtx =  rv[0];
            int wsf = rv[1];
            int stop = rv[2];
            if(stop > k) break;
            
            if(vtx == dst) {
                max = Math.min(max,wsf);
            }

             for(int[] e : graph[vtx]){
                int v = e[0] , w = e[1] ;

                if(wsf + w < dis[v] && stop< k){
                    dis[v] = wsf + w;
                    pq.add(new int[] { v, wsf + w  ,stop+1});
                }
            }
        }
         
         if(max!=(int)1e9) return max;

        return -1;

    }

    public int findCheapestPrice2(int N, int[][] flights, int src, int dst, int k) {
        ArrayList<int[]>[] graph = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] a : flights) {
            int u = a[0], v = a[1], w = a[2];
            graph[u].add(new int[] { v, w });
        }

        // {vtx , cost , stop}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });

        pq.add(new int[] { src,0,0});
        int max = (int)1e9;
        k++;
        while(pq.size()!=0){
            int[] rv = pq.remove();
            int vtx =  rv[0];
            int wsf = rv[1];
            int stop = rv[2];
            if(stop > k) continue;
            
            if(vtx == dst) {
                return wsf;
            }

             for(int[] e : graph[vtx]){
                int v = e[0] , w = e[1] ;
                 pq.add(new int[] { v, wsf + w  ,stop+1});
            }
        }

        return -1;

    }

    
}
