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
    public static void main(String[] args) {
        
    }
}