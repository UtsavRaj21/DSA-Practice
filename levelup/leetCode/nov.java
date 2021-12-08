import java.util.* ;
public class nov {

    //13 :- 739. Daily Temperatures
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];

        Stack<Integer> st = new Stack<>();
        st.add(0);
        int i = 1 ;
        while(i<n){
            int val = temperatures[i];
            while(st.size()>0 && temperatures[st.peek()] < val){
                int idx = st.peek();
                res[idx] = i-idx;
                st.pop();
            }
            st.add(i);
            i++;
        }

        while(st.size()>0){
            int idx = st.pop();
            res[idx] = 0;
        }

        return res;
    }

    //14:- 1286. Iterator for Combination
    class CombinationIterator {

        LinkedList<String> que;
        StringBuilder sb;
        public void solve(String str,int start , int len){
            if(len == 0){
                que.addLast(sb.toString());
            }
            for(int i = start ; i < str.length() ;i++){
                sb.append(str.charAt(start));
                solve(str, i+1, len-1);
                sb.deleteCharAt(sb.length()-1);
            }
        }

        public CombinationIterator(String characters, int combinationLength) {
            sb = new StringBuilder();
            que = new LinkedList<>();
            solve(characters,0,combinationLength);
        }
        
        public String next() {
            if(que.size() != 0){
                String s = que.removeFirst();
                return s;
            }
        }
        
        public boolean hasNext() {
            if(que.size() == 0){
                return false;
            }
            return true;
        }
    }

    //15 :- 368. Largest Divisible Subset
    public List<Integer> largestDivisibleSubset(int[] nums) {
        
    }
    
    public static void main(String[] args) {
        
    }
}