public class aug{

    //659. Split Array into Consecutive Subsequences
    class Solution {
        public boolean isPossible(int[] nums) {
            HashMap<Integer,Integer> fm = new HashMap<>();
            HashMap<Integer,Integer> hm = new HashMap<>();
            
            for(int ele : nums){
                fm.put(ele , fm.getOrDefault(ele,0)+1);
            }
            
            for(int ele : nums){
                if(fm.get(ele) == 0){
                    continue;
                }
                
                if(hm.getOrDefault(ele,0)>0){
                    
                    fm.put(ele,fm.get(ele)-1);
                    hm.put(ele+1,hm.getOrDefault(ele+1,0)+1);
                    hm.put(ele,hm.getOrDefault(ele,0)-1);
                    
                }else if(fm.getOrDefault(ele,0)+1>0 && fm.getOrDefault(ele+1,0)>0 && fm.getOrDefault(ele+2,0)>0 ){
                    fm.put(ele,fm.get(ele)-1);
                    fm.put(ele+1,fm.get(ele+1)-1);
                    fm.put(ele+2,fm.get(ele+2)-1);
                    hm.put(ele+3,hm.getOrDefault(ele+3,0)+1);
                }else {
                    return false;
                }
            }
            
            return true;
        }
    }

    //871. Minimum Number of Refueling Stops
    class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        if(target <= startFuel){
            return 0;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
            return b-a;
        });
        int i = 0 , level = 0 , far = startFuel;
        while(far < target){
            while(i < stations.length && far >= stations[i][0]){
                pq.add(stations[i][1]);
                i++;
            }
            
            if(pq.size()==0){
                return -1;
            }
            
            far += pq.remove();
            level++;
        }
        
        return level;
    }
}
    public static void main(String[] args) {
        
    }
}