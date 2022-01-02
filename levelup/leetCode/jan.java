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
    }
    public static void main(String[] args) {
        
    }
}
