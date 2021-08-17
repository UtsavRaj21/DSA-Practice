import java.util.*;

public class bellanfordques {

    //787. Cheapest Flights Within K Stops:- https://leetcode.com/problems/cheapest-flights-within-k-stops/

   // T(o): k(v+e)

    public int findCheapestPrice(int N, int[][] flights, int src, int dst, int k) {
        int[] prev = new int[N];

       Arrays.fill(prev,(int)1e9);

       prev[src] = 0;
       boolean negativeCycle = false;
       for(int i =1 ;i<=k+1;i++){      // k
           int[] curr = new int[N];
           for(int j =0;j < N ; j++){     //v
               curr[j] = prev[j];
           }
   
           for(int[] e : flights){       //e
               int u = e[0] , v =e[1] , w = e[2];
               if(prev[u] != (int)1e9 && prev[u] + w <curr[v]){
                   curr[v] = prev[u] +w;
               }
           }
           prev = curr;
       }
       return prev[dst] == (int)1e9 ? -1 : prev[dst];
   }

   //1334. Find the City With the Smallest Number of Neighbors at a Threshold Distance

   public int findTheCity1(int n, int[][] edges, int distanceThreshold) {              // more compleity
    ArrayList<Integer>[] cityVisited = new ArrayList[n];
    
    for(int i = 0 ; i < n ;i++){
        cityVisited[i] = new ArrayList<>();
    }
      int[][] mat = new int[n][n];
    for (int[] d : mat)
        Arrays.fill(d, (int) 1e9);

    for (int[] e : edges){
        mat[e[0]][e[1]] = e[2];
        mat[e[1]][e[0]] = e[2];
    }
        

    for (int i = 0; i < n; i++)
        mat[i][i] = 0;

    
    for(int k = 0 ; k < n ; k++){
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                // if(i==j) continue;
                int ndis= mat[i][k] + mat[k][j];
                mat[i][j] = Math.min(mat[i][j], mat[i][k] + mat[k][j]);
                if(ndis <= distanceThreshold){
                    cityVisited[i].add(j);
                }
                
            }
        }
    }
    
    
      for(int i = 0 ; i < n ; i++) {
         ArrayList<Integer> a = cityVisited[i];
        ArrayList<Integer> newList = new ArrayList<Integer>();
      
        for (Integer element : a) {
            if (!newList.contains(element)) {

                newList.add(element);
            }
        }
          cityVisited[i] = newList;
          
    }
    
    int min = (int)1e9;
    int idx = 0;
    
    
    
    for(int i = 0 ; i<n ;i++){
        System.out.println(cityVisited[i]);
        if( cityVisited[i].size() <= min){
            min =  cityVisited[i].size();
            idx = i;
        }
    }
    
     for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                 System.out.print(mat[i][j]+" ");
            }
          System.out.println();
     }
    return idx;
}

   public int findTheCity2(int n, int[][] edges, int distanceThreshold) {              // less compleity
    ArrayList<Integer>[] cityVisited = new ArrayList[n];
    
    for(int i = 0 ; i < n ;i++){
        cityVisited[i] = new ArrayList<>();
    }
      int[][] mat = new int[n][n];
    for (int[] d : mat)
        Arrays.fill(d, (int) 1e9);

    for (int[] e : edges){
        mat[e[0]][e[1]] = e[2];
        mat[e[1]][e[0]] = e[2];
    }
        

    for (int i = 0; i < n; i++)
        mat[i][i] = 0;

    
    for(int k = 0 ; k < n ; k++){
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                mat[i][j] = Math.min(mat[i][j], mat[i][k] + mat[k][j]); 
            }
        }
    }
    
    int min = (int)1e9;
    int idx = 0;
    

    for(int j = 0 ; j<mat.length;j++){
        int c = 0;
        for(int i = 0 ; i < mat[0].length ; i++){
            if(mat[j][i] <= distanceThreshold){
                c++;
            }
        }
        
        if(c<=min){
            min = c;
            idx =j;
        }
    }
    return idx;
}
       
}
