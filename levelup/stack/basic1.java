import java.util.*;

public class basic1{

    //Next Greater Element To The Right
    public static int[] nger(int[] arr){
        int n = arr.length;
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();
        st.push(0);

        for(int i = 1 ;i< arr.length ;i++){
            int val = arr[i];
            while(st.size()!=0 && val > arr[st.peek()]){
                int idx = st.pop();
                res[idx] = val;
            }
            st.add(i);
        }
        while(st.size()>0){
            int idx = st.pop();
            res[idx] = -1;
        }
        return res;
    }

    //Next Greater Element To The Left
    public static int[] ngel(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();
        st.add(n-1);
        for(int i = n-2;i>=0;i--){
            int val = arr[i];
            while(st.size()>0 && arr[st.peek()] < val){
                int idx = st.pop();
                res[idx] = val;
            }
            st.add(i);
        }
        while(st.size()>0){
            int idx = st.pop();
            res[idx] = -1;
        }
        return res;
      }

    //Next Smaller Element To The Right
    public static int[] nser(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();
        st.add(0);
        for(int i = 1;i<n;i++){
            int val = arr[i];
            while(st.size()>0 && arr[st.peek()] > val){
                int idx = st.pop();
                res[idx] = val;
            }
            st.add(i);
        }
        while(st.size()>0){
            int idx = st.pop();
            res[idx] = -1;
        }
        return res;
      }

    //Next Smaller Element To The Left
    public static int[] nsel(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();
        st.add(n-1);
        for(int i = n-2;i>=0;i--){
            int val = arr[i];
            while(st.size()>0 && arr[st.peek()] > val){
                int idx = st.pop();
                res[idx] = val;
            }
            st.add(i);
        }
        while(st.size()>0){
            int idx = st.pop();
            res[idx] = -1;
        }
        return res;
      }
    
    //Next Greater Element I
    public static int[] nextGreaterElement(int[] nums, int[] query) {
        int n = nums.length;
        HashMap<Integer,Integer> map = new HashMap<>();
        Stack<Integer> st = new Stack<>();
        st.add(0);
        for(int i = 1 ; i < n ;i++){
            int val = nums[i];
            while(st.size()>0 && nums[st.peek()] < val){
                int idx = st.pop();
                map.put(nums[idx],nums[i]);
            }
            st.add(i);
        }

        while(st.size()>0){
            int idx = st.pop();
            map.put(nums[idx],-1);
        }
        
        //System.out.println(map);

        int q = query.length;
        int[] res = new int[q];
        for(int i = 0 ; i < q;i++){
            res[i] = map.get(query[i]);
        }
        
        return res;
    }
    
    // Next Greater Element Ii :-  the next element of nums[nums.length - 1] is nums[0]
    public static int[] nextGreaterElementII(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();
        st.push(0);
        Arrays.fill(res,-1);
        for(int id = 1 ;id< 2*nums.length ;id++){
            int i = id%n;
            int val = nums[i];
            while(st.size()>0 && val > nums[st.peek()]){
                int idx = st.pop();
                res[idx] = val;
            }
            st.push(i);
        }
       
        return res;
    }
    
    public static void main(String[] args) {
        
    }
}