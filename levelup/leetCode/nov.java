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

        public CombinationIterator(String characters, int combinationLength) {
            
        }
        
        public String next() {
            
        }
        
        public boolean hasNext() {
            
        }
    }

    //15 :- 368. Largest Divisible Subset
    public List<Integer> largestDivisibleSubset(int[] nums) {
        
    }
    
    public static void main(String[] args) {
        
    }
}