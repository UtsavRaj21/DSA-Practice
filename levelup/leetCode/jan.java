import java.util.*;

public class jan {

    //
    public int numPairsDivisibleBy60(int[] time) {
        int count =0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int ele : time){
            int val = ele % 60;
            int rem = 60 - val;

            if(val==0){
                if(map.containsKey(val)){
                    count += map.get(val);
                }
            }else if(map.containsKey(rem)){
                count += map.get(rem);
            }

            if(map.containsKey(val)){
                map.put(val,map.get(val) + 1);
            }else{
                map.put(val, 1);
            }
        }
        return count;

        //997. Find the Town Judge
         public int findJudge(int n, int[][] trust) {
            if(n ==  1 && trust.length == 0) return 1;
            HashMap<Integer,Integer> map = new HashMap<>();
             HashSet<Integer> set = new HashSet<>();
                 for(int[] pair : trust){
                     int a = pair[0];
                     int b = pair[1];
                     set.add(a);
                     if(!map.containsKey(b)){
                          map.put(b,1);
                     }else{
                          map.put(b,map.get(b) + 1);
                     }
                 }
     
                 for(int key : map.keySet()){
                     if(map.get(key) == n-1){
                         if(!set.contains(key)){
                             return key;
                         }
                         
                     }
                 }
     
                 return -1;
        }
    

    public static void main(String[] args) {

    }
}
