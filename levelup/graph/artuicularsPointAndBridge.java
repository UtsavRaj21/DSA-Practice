import java.util.*;
public class artuicularsPointAndBridge {

    //1192. Critical Connections in a Network :- https://leetcode.com/problems/critical-connections-in-a-network/
    
    int[] low,dis;
     boolean[] articulus,vis;
     int time = 0;
    
        List<List<Integer>> ans;
       public void dfs(ArrayList<Integer>[] graph,int src,int par){
        dis[src] = low[src] = time++;
        vis[src] = true;

        for(int e: graph[src]){
            if(!vis[e]){
              
                dfs(graph  , e,src);
                if(dis[src] < low[e]){
                   ArrayList<Integer> a = new ArrayList<>();
                   a.add(src);
                    a.add(e);
                    ans.add(a);
                }
                low[src] = Math.min(low[src],low[e]);
            }else if(e != par){
                low[src] = Math.min(low[src],dis[e]);
            }
        }
    }
    
    
    public List<List<Integer>> criticalConnections(int N, List<List<Integer>> connections) {
        ArrayList<Integer>[] graph = new ArrayList[N];
        
        for(int i =0  ; i<N ;i++){
            graph[i] = new ArrayList<>();
        }
        
        for(List<Integer> ar  : connections){
            graph[ar.get(0)].add(ar.get(1));
            graph[ar.get(1)].add(ar.get(0));
        }
        
        low = new int[N];
        dis = new int[N];
        vis = new boolean[N];
        ans = new ArrayList<>();

        for(int i=0;i<N;i++){
            if(!vis[i]){
                dfs(graph,i,-1);
            }
        }
        
        return ans;
    }
}
