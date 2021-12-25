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
    public boolean find132pattern(int[] nums) {
        int n =nums.length;
        int[] arrMin = new int[n];
        arrMin[0] = nums[0];
        for(int i = 1 ; i < n ; i++){
            arrMin[i] = Math.min(nums[i],arrMin[i-1]);
        }

        Stack<Integer> st = new Stack<>();
        
        for(int i = n-1;i>0;i--){
            while(st.size()>0 && st.peek() <= arrMin[i]){
                st.pop();
            }

            if(st.size() > 0 && st.peek() < nums[i]){
                return true;
            }

            st.push(nums[i]);
        }
        return false;

    }

    //21 ) 231. Power of Two

    public boolean isPowerOfTwo(int n) {
        if(n <= 0) return false;
        int rsb = n & -n;
        if(n-rsb == 0){
            return true;
        }else{
            return false;
        }
    }

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
    
    //316. Remove Duplicate Letters
    public String removeDuplicateLetters(String s) {
        HashMap<Character,Integer> fmap = new HashMap<>();   // frequency map
        HashMap<Character,Boolean> pmap = new HashMap<>();   // presence map

        for(int i = 0 ; i < s.length() ; i++){
            char ch = s.charAt(i);
            int p = fmap.getOrDefault(ch,0);
            fmap.put(ch, p + 1);
        }

        LinkedList<Character> st = new LinkedList<>();

        for(int i = 0 ; i < s.length() ; i++){
            char ch = s.charAt(i);
            int fq = fmap.get(ch);
            fmap.put(ch, fq-1);

            if(pmap.containsKey(ch) == true && pmap.get(ch)==true) continue;

            while(st.size() > 0 && st.getLast()>ch && fmap.get(st.getLast()) > 0){
                char rch = st.removeLast();
                pmap.put(rch, false);
            }
            st.addLast(ch);
            pmap.put(ch,true);
        }
        StringBuilder sb = new StringBuilder();
        for(char ch : st){
            sb.append(ch);
        }
        return sb.toString();
    }
   
    public static String reverseWord(String str){
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        return sb.toString();
    }
    
    //***************************************************************************************** */
    
    //42. Trapping Rain Water

    //Using Stack
    public int trap(int[] height) {
        int n = height.length;
        Stack<Integer> st = new Stack<>();
        st.add(0);
        int count = 0;
        for(int i = 1 ;i < n ; i++){
            int val = height[i];
            while(st.size()>1 && height[st.peek()] < val){
                int htIdx = st.pop();
                int htVal = height[htIdx];
                if(st.size()>0){
                    int rmax = val;
                    int rIdx = i;
                    int lidx = st.peek();
                    int lmax = height[lidx];

                    int width = rIdx - lidx - 1;
                    int amount = Math.min(rmax,lmax) - htVal;
                    count+= (amount * width);
                }
                

            }
            st.add(i);
        }
        return count;
    }
    
    //Two Pointer
    public int trap(int[] height) {
        int n = height.length;
        int count = 0;
        int i = 0 ;
        int j = n-1;
        int lmax = height[i];
        int rmax = height[j];
        while(i<j){
            lmax = Math.max(lmax, height[i]);
            rmax = Math.max(rmax, height[j]);
            if(lmax < rmax){
                count+=(lmax - height[i]);
                i++;
            }else{
                count+= (rmax - height[j]);
                j--;
            }
        }
        return count;
    }
    
    //407. Trapping Rain Water II

    private class trwHelper implements Comparable<trwHelper>{
        int r;
        int c;
        int ht;
        public trwHelper(int r, int c, int ht){
            this.r = r;
            this.c = c;
            this.ht = ht;
        }

        public int compareTo(trwHelper o){
            return this.ht - o.ht;
        }
    }

    public void addBoundary(int n , int m,int[][] htm , boolean[][] vis , PriorityQueue<trwHelper> pq){
        //top

        for(int c = 0 ; c < m ; c++){
            if(!vis[0][c]){
                pq.add(new trwHelper(0, c, htm[0][c]));
                vis[0][c] = true;
            }
        }

        //right

        for(int r = 0 ; r < n ; r++){
            if(!vis[r][m-1]){
                pq.add(new trwHelper(r, m-1, htm[r][m-1]));
                vis[r][m-1] = true;
            }
        }

        //bottom

        for(int c = 0 ; c < m ; c++){
            if(!vis[n-1][c]){
                pq.add(new trwHelper(n-1, c, htm[n-1][c]));
                vis[n-1][c] = true;
            }
        }

        //left
        for(int r = 0 ; r < n ; r++){
            if(!vis[r][0]){
                pq.add(new trwHelper(r, 0, htm[r][0]));
                vis[r][0] = true;
            }
        }
    }

    public int trapRainWater(int[][] htm) {
        int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
        int n = htm.length;
        int m = htm[0].length;
        boolean[][] vis = new boolean[n][m];
        PriorityQueue<trwHelper> pq = new PriorityQueue<>();

        addBoundary(n,m,htm,vis,pq);
        int water  = 0;

        while(pq.size()>0){
            trwHelper rem = pq.remove();
            for(int d = 0 ; d < dir.length ; d++){
                int r = rem.r + dir[d][0];
                int c = rem.c + dir[d][1];

                if(r>=0 && r<n && c>=0 && c<m && !vis[r][c]){
                    vis[r][c] = true;
                    if(htm[r][c] < rem.ht){
                        water += (rem.ht - htm[r][c]);
                        pq.add(new trwHelper(r, c, rem.ht));
                    }else{
                        pq.add(new trwHelper(r, c, htm[r][c]));
                    }
                }
            }
            
        }
        return water;
    }

    //************************************************************************************************* */

    //*************************************************************************************************

    //224. Basic Calculator

    public int calculate(String s) {
        int n = s.length();
        int sum = 0 ;
        Stack<Integer> st = new Stack<>();
        int sign = 1;
        for(int i = 0 ; i < n ; i++){
            char ch = s.charAt(i);
            if(ch==' '){
                continue;
            }else if(ch>='0' &&ch <= '9'){
                long num = 0;
                while(i<n && s.charAt(i) >='0' && s.charAt(i) <='9'){
                    num*=10;
                    num+=s.charAt(i)-'0';
                    i++;
                }
                i--;
                num = sign*num;
                sum+=num;
                sign = 1;
            }else if(ch=='('){
                st.push(sum);
                st.push(sign);
                sum=0;
                sign=1;
            }else if(ch==')'){
                int si = sum*st.pop();
                int v = st.pop();
                sum = si+v;
            }else if(ch=='-'){
                sign*=-1;
            }else{
                //dont do any thing 
            }   
        }
        return sum;
    }
    
    // 227. Basic Calculator II
    
    public int evaluate(int val1, int val2, int oper) {
        int val = 0;

        if (oper == '*') {
            val = val1 * val2;
        } else if (oper == '/') {
            val = val1 / val2;
        } else if (oper == '+') {
            val = val1 + val2;
        } else if (oper == '-') {
            val = val1 - val2;
        }
        return val;
    }

    public int priority(char ch) {
        if (ch == '*' || ch == '/') {
            return 2;
        } else if (ch == '+' || ch == '-') {
            return 1;
        }
        return 0;
    }

    public int calculate2(String s) {
       Stack<Integer> vStack = new Stack<>();
       Stack<Character> oStack = new Stack<>();
        int n = s.length();
        for(int i = 0 ; i < n ; i++){
            char ch = s.charAt(i);
            if(ch==' '){
                continue;
            }else if(ch>='0' && ch<='9'){
                int j = i;
                StringBuilder sb= new StringBuilder();
                while(j<n && s.charAt(j) >= '0' && s.charAt(j) <='9'){
                    sb.append(s.charAt(j));
                    j++;
                }
                i = j-1;
                vStack.push(Integer.parseInt(sb.toString()));
            }else{
                while(oStack.size()>0 && (priority(oStack.peek()) >= priority(ch))){
                    char c = oStack.pop();
                    int val2 = vStack.pop();
                    int val1 = vStack.pop();
                    int val = evaluate(val1, val2, c);
                    vStack.push(val);
                }
                oStack.push(ch);
            }
        }
        while(oStack.size()>0 ){
            char c = oStack.pop();
            int val2 = vStack.pop();
            int val1 = vStack.pop();
            int val = evaluate(val1, val2, c);
            vStack.push(val);
        }

        return vStack.peek();
    }

    //********************************************************************************************** */
    
    //Number Of Valid Subarrays
    public static int validSubarrays(int[] nums) {
        int count = 0;
        int n = nums.length;
        Stack<Integer> st = new Stack<>();
        int[] nser = new int[n];
        for(int i = 0 ; i < n ; i++){
            int val = nums[i];
            while(st.size() > 0 && nums[st.peek()] > val){
                nser[st.pop()] = i;
            }
            st.push(i);
        }

        while(st.size()> 0){
            nser[st.pop()] = n;
        }
        //take contribution
        for(int i = 0 ; i < n ; i++){
            count+=(nser[i] - i);
        }
        return count;
    }
    
    //Lexicographically Smallest Subsequence
    public static int[] smallest(int[] nums, int K) {
        Stack<Integer> st = new Stack<>();
        int n = nums.length;
        int k = n-K;

        for(int i = 0 ; i < n ; i++){
            while(k>0 && nums[st.peek()] > nums[i]){
                st.pop();
                k--;
            }
        }

        while(k>0){
            st.pop();
        }
        int[] res = new int[st.size()];
        for(int i = res.length-1;i>=0 ;i--){
            res[i] = st.pop();
        }

        return res;
     }
    
    //Design A Stack With Increment Operation
    //1: void push(int x) Adds x to the top of the stack if the stack 
    //        hasn't reached the maxSize.
    //2: int pop() Pops and returns the top of stack or -1 if the stack is empty.
    //3: void inc(int k, int val) Increments the bottom k elements of the stack
    //         by val. If there are less than k elements in the stack, just 
    //         increment all the elements in the stack.
    public static class CustomStack {
    
        int value[];
        int increment[];
        int index;
        
        public CustomStack(int maxSize) {
            value = new int[maxSize];
            increment = new int[maxSize];
            index=-1;
        }
        
        public void push(int x) {
            // complete this function
            if(index+1<value.length){
                index++;
                value[index] = x;
            }
        }
        
        public int pop() {
            // complete this function
            if(index == -1){
                return -1;
            }
            int r = value[index] + increment[index];
            if(index>0){
                increment[index-1] += increment[index];
            }
            increment[index] = 0;
            index--;
            return r;
        }
        
        public void increment(int k, int val) {
            if(index == -1) return;
            if(k>index){
                increment[index]+=val;
            }else{
                increment[k-1] += val;
            }
        } 
    }
    
    //641. Design Circular Deque
    class MyCircularDeque {

        private class Node{
            int data;
            Node next;

            public Node(int data){
                this.data = data;
            }
        }

        int size;
        int limit;
        Node head = null;
        Node tail = null;
        
        public MyCircularDeque(int k) {
            this.limit = k;
            this.size = 0;
        }
        
        public boolean insertFront(int value) {
            if(this.limit == this.size) return false;
            if(this.size == 0){
                head = tail = new Node(value);
               
            }else{
                Node nn = new Node(value);
                nn.next = head ;
                head = nn;
            }
            this.size++;
            return true;
        }
        
        public boolean insertLast(int value) {
            if(this.limit == this.size) return false;
            if(this.size == 0){
                head = tail = new Node(value);
               
            }else{
                Node nn = new Node(value);
                tail.next = nn ;
                tail = nn;
            }
            this.size++;
            return true;
        }
        
        public boolean deleteFront() {
            if(this.size == 0){
                return false;
            }
            if(this.size == 1){
                this.head = this.tail = null;
            }else{
                this.head = this.head.next;
            }
            this.size--;
            return true;
        }
        
        public boolean deleteLast() {
            if(this.size == 0){
                return false;
            }
            if(this.size == 1){
                this.head = this.tail = null;
            }else{
                Node nn = head;
                while(nn.next!=this.tail){
                    nn = nn.next;
                }
                nn.next = null;
                this.tail = nn;
            }
            this.size--;
            return true;
        }
        
        public int getFront() {
            if(this.size == 0) return -1;
            return head.data;
        }
        
        public int getRear() {
            if(this.size == 0) return -1;
            return tail.data;
        }
        
        public boolean isEmpty() {
            return this.size == 0;
        }
        
        public boolean isFull() {
            return this.limit == this.size;
        }
    }
    
    
     public static void main(String[] args) {
        
    }
}
