import java.util.*;

public class basic2 {

    //636. Exclusive Time of Functions
    private class ETHelper{
        int id,start,cet;//cet:child execution time;
        ETHelper(int id,int start , int cet){
            this.id = id;
            this.start = start;
            this.cet = cet;
        }
    }
    
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        Stack<ETHelper> st = new Stack<>();

        for(String str : logs){
            String[] info = str.split(":");
            int id = Integer.parseInt(info[0]);
            String status = info[1];
            int timeSpamp = Integer.parseInt(info[2]);

            if(status.equals("start")){
                st.push(new ETHelper(id, timeSpamp, 0));
            }else{
                int fn_time = timeSpamp - st.peek().start + 1;  //function time
                int exe_time = fn_time - st.peek().cet;    //execution time
                res[id]+=exe_time;
                st.pop();
                if(st.size() > 0){
                    st.peek().cet+=fn_time;
                }
            }
        }
        return res;
    }

    //456. 132 Pattern
    // public boolean find132pattern(int[] nums) {
        
    // }

    //735. Asteroid Collision
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<>();
        for(int i = 0 ; i < asteroids.length ;i++){
            int val = asteroids[i];
            if(val>0){
                st.push(val);
            }else{
                while(st.size()>0 && st.peek() > 0 && st.peek() < Math.abs(val)){
                    st.pop();
                }
                if(st.size() > 0 && st.peek() == Math.abs(val) ){
                    st.pop();
                }else if (st.size() == 0 || st.peek() < 0){
                   st.push(val); 
                }
            }
        }
        int[] res = new int[st.size()];
        int s = st.size();
        for(int i = s-1 ; i >= 0 ;i--){
            res[i] = st.pop();
        }
        return res;
    }
    
    //402. Remove K Digits
    public String removeKdigits(String num, int k) {
        Stack<Character> st = new Stack<>();
        for(int i = 0 ; i < num.length() ;i++){
            char ch = num.charAt(i);
            if(k!=0){
                if(st.size() == 0 || ch >= st.peek()){
                    st.push(ch);
                }else{
                     while( k > 0 && st.size()>0 && st.peek() > ch){
                         st.pop();
                         k--;
                      }
                     st.push(ch);
                }
            }else{
                st.push(ch);
            }
        }

        while(k>0){
            st.pop();
            k--;
        }
        StringBuilder sb = new StringBuilder();
        while(st.size()>0){
            sb.append(st.pop());
        }
        sb = sb.reverse();
        
        while(sb.length()>0){
            if(sb.charAt(0) == '0'){
                sb.deleteCharAt(0);
            }else{
                break;
            }
        }
        if(sb.length() == 0) return "0";
        return sb.toString() ;

    }
    
    public static void main(String[] args) {
        
    }
}
