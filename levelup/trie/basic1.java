import java.util.*;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;

public class basic1{

    // Implementation of Trie
    public static class Trie {

        public class Node{
            Node[] children;
            boolean isEnd;
            Node(){
                this.children = new Node[26];
                this.isEnd = false;
            }
        }

        private Node root = null;
        /** Initialize your data structure here. */
        public Trie() {
           root = new Node();  
        }
    
        /** Inserts a word into the trie. */
        public void insert(String word) {
             Node ptr  = root;
             for(int i = 0 ; i < word.length();i++){
                 char ch = word.charAt(i);
                 if(ptr.children[ch-'a']==null){
                     Node nn = new  Node();
                     ptr.children[ch-'a'] = nn;
                 }
                 ptr = ptr.children[ch-'a'];
             }
             ptr.isEnd = true;

        }
    
        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            Node ptr = root;
            for(int i = 0 ; i < word.length();i++){
                char ch = word.charAt(i);
                if(ptr.children[ch-'a'] == null){
                    return false;
                }else{
                    ptr = ptr.children[ch-'a'];
                }
            }
            if(ptr.isEnd == false){
                return false;
            }
            return true;
        }
    
        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            Node ptr = root;
            for(int i = 0 ; i < prefix.length();i++){
                char ch = prefix.charAt(i);
                if(ptr.children[ch-'a'] == null){
                    return false;
                }else{
                    ptr = ptr.children[ch-'a'];
                }
            }
            return true;
        }
      }

    //Design Add And Search Words Data Structure
    public static class WordDictionary {

        public class Node{
            Node[] children;
            boolean isEnd;
            Node(){
                this.children = new Node[26];
                this.isEnd = false;
            }
        }

        private Node root = null;
        /** Initialize your data structure here. */
        public WordDictionary() {
            root = new Node();
        }
    
        /** Adds a word into the data structure. */
        public void addWord(String word) {
            Node ptr  = root;
            for(int i = 0 ; i < word.length();i++){
                char ch = word.charAt(i);
                if(ptr.children[ch-'a']==null){
                    Node nn = new  Node();
                    ptr.children[ch-'a'] = nn;
                }
                ptr = ptr.children[ch-'a'];
            }
            ptr.isEnd = true;
        }
    
        /**
         * Returns if the word is in the data structure. A word could contain the dot
         * character '.' to represent any one letter.
         */
        public boolean search(String word) {
            Node ptr = root;
            return searchFind(word,ptr,0);
        }

        public boolean searchFind(String word,Node ptr,int idx){
             if(idx == word.length()){
                return ptr.isEnd;
            }
            char ch = word.charAt(idx);
            if(ch=='.'){
                for(int i = 0 ; i < 26 ; i++){
                    Node child = ptr.children[i];
                    if(child!=null && searchFind(word,child,idx+1) == true){
                        return true;
                    }
                }
            }else if(ptr.children[ch-'a'] != null){
               Node child = ptr.children[ch-'a'];
               return searchFind(word, child, idx+1);
            }
            return false;
        }
      }
    
    //Word Search Ii 
   
    public static class Node{
        Node[] children;
        boolean isEnd;
        int freq;
        Node(){
            this.children = new Node[26];
            this.isEnd = false;
            this.freq = 0;
        }
    }
    
    public static void add(String word,Node root){
        Node ptr  = root;
        for(int i = 0 ; i < word.length();i++){
            char ch = word.charAt(i);
            if(ptr.children[ch-'a']==null){
                Node nn = new  Node();
                ptr.children[ch-'a'] = nn;
            }
           
            ptr = ptr.children[ch-'a'];
            ptr.freq++;
        }
        ptr.isEnd = true;
    }

    static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};

    public static int search(char[][] board,int i , int j,Node root, boolean[][] vis,
        ArrayList<String> res,StringBuilder sb){
        char ch = board[i][j];
        if(root.children[ch-'a'] == null){
            return 0;
        }
        root = root.children[ch-'a'];
        if(root.freq == 0){
            return 0;
        }
        sb.append(ch);
        vis[i][j] = true;  
        
        int count =0;
        if(root.isEnd==true){
            res.add(sb.toString());
            root.isEnd = false;
            count++;
        }

        for(int d = 0 ; d < dir.length ;d++ ){
            int r = i+dir[d][0];
            int c = j+dir[d][1];

            if(r>=0 && c>=0 && r<board.length && c< board[0].length && !vis[r][c]){
                count+=search(board, r, c, root, vis, res, sb);
            }
        }
        vis[i][j] = false;
        sb.deleteCharAt(sb.length()-1);
        root.freq = root.freq - count;
        return count;
    }

    public static ArrayList<String> findWords(char[][] board, String[] words) {
        ArrayList<String> res = new ArrayList<>();
        Node root = new Node();
        for(String word : words){
            add(word,root);
        }

    //travel on board to find word

    boolean[][] vis = new boolean[board.length][board[0].length];
        for(int i = 0 ; i < board.length; i++){
            for(int j =0 ; j < board[0].length ; j++){
                StringBuilder sb = new StringBuilder();
                search(board,i,j,root,vis,res,sb);
            }
        }
        return res;
    }

    //Replace Words 

    public static int searchWord(String word,Node root,StringBuilder sb,StringBuilder psf){
        char c = word.charAt(0);
        if(root.children[c-'a'] == null){
            return -1;
        }
        Node ptr = root;
        for(int i = 0 ; i < word.length() ;i++){
            char ch = word.charAt(i);
            if(root.children[ch-'a'] == null){
                return -1;
            }
            psf.append(ch);
            root = root.children[ch-'a'];
            if(root.isEnd == true){
                sb.append(psf.toString());
                return 1;
            }
        }
        return -1;
    }

    public static String replaceWords(String dictionary[], String sentence) {
        Node root = new Node();
        for(String word : dictionary){
            add(word, root);
        }

        StringBuilder sb = new StringBuilder();

        // String[] arr = sentence.split(" ");
        // // for(int i = 0;i<arr.length;i++){
        // //      System.out.println(arr[i]);
        // // }
        
        

        // for(int i = 0;i<arr.length;i++){
        //     StringBuilder  psf = new StringBuilder();
        //     int n = searchWord(arr[i],root,sb,psf);
        //     if(n==-1){
        //         sb.append(arr[i]);
        //     }
        //     if(i!=arr.length-1){
        //         sb.append(" ");
        //     }
        // }
        
        //******************************** OR *****************************
        StringTokenizer st = new StringTokenizer(sentence);  
         while (st.hasMoreTokens()) {  
             String word = st.nextToken();  
             StringBuilder  psf = new StringBuilder();
            int n = searchWord(word,root,sb,psf);
            if(n==-1){
                sb.append(word);
            }
            sb.append(" ");
         }
         sb.deleteCharAt(sb.length()-1);

        //****************************************** */
        
        return sb.toString();
    }
    
    //Map Sum Pairs

    public static class MapSum { 
        public class NodeSum{
            NodeSum[] children;
            boolean isEnd;
            int n;
            NodeSum(){
                this.children = new NodeSum[26];
                this.isEnd = false;
                this.n =0;
            }
        }

        /** Initialize your data structure here. */
         HashMap<String,Integer> map ;
            NodeSum root;
        public MapSum() {
            root = new NodeSum();
            map =new HashMap<>();
        }
    
        public void insert(String word, int val) {
            int p = 0;
            if(map.containsKey(word)){
                p = map.get(word);
            }
            NodeSum ptr = root;
            map.put(word,val);
            for(int i = 0 ; i < word.length() ;i++){
                char ch = word.charAt(i);
                if(ptr.children[ch-'a'] == null){
                    ptr.children[ch-'a'] = new NodeSum();
                }
                ptr = ptr.children[ch-'a'];
                ptr.n = ptr.n + val - p;
            }
            ptr.isEnd = true;
        }
    
        public int sum(String prefix) {
            NodeSum ptr =root;
            for(int i = 0 ; i < prefix.length() ;i++){
                char ch = prefix.charAt(i); 
                if(ptr.children[ch-'a'] == null){
                    return 0;
                }
                ptr = ptr.children[ch-'a'];
               
            }
            return ptr.n;
        }
      }
    
    //Concatenated Words 
    
    public static class NodeCon{
        NodeCon[] children;
        String s;
        NodeCon(){
            this.children = new NodeCon[26];
        }
    }
    
    private static HashSet<String> set;

    public static void insertContact(String word , NodeCon root){
        if(word.length() == 0)return;
        NodeCon ptr = root;
        for(int i = 0 ; i < word.length(); i++){
            char ch = word.charAt(i);
            if(ptr.children[ch-'a'] == null){
                ptr.children[ch-'a'] = new NodeCon();
            }
            ptr = ptr.children[ch-'a'];
        }
        ptr.s = word;
    }
   
    public static void matchCharacter(NodeCon p1 , NodeCon p2 , NodeCon root){
        if(p1.s != null && p2.s!=null){
            set.add(p1.s);
            return;
        }

        if(p2.s != null){
            matchCharacter(p1, root , root);
        }

        for(int i = 0 ; i < 26 ; i++){
            if(p1.children[i] != null && p2.children[i] != null){
                matchCharacter(p1.children[i],p2.children[i],root);
            }
        }
    }
   
    public static void dfsTree(NodeCon ptr,NodeCon root){
        if(ptr.s != null){
            matchCharacter(ptr,root,root);
        }

        for(NodeCon child : ptr.children){
            if(child!=null){
                dfsTree(child, root);
            }
        }
   }

    public static List<String> findAllConcatenatedWordsInADict(String[] words) {
        set = new HashSet<>();
        NodeCon root = new NodeCon();
        for(String word : words){
            insertContact(word,root);
        }
        dfsTree(root,root);
        List<String> res = new ArrayList<>(set);
        return res;
    }

    //720. Longest Word in Dictionary
    public static class Longest{
        Longest[] children;
        String s;
        Longest(){
            this.children = new Longest[26];
        }
    }

    public static void addLongest(String word,Longest root){
        Longest ptr = root;
        for(int i = 0 ; i < word.length() ;i++){
            char ch = word.charAt(i);
            if(ptr.children[ch-'a'] == null){
                ptr.children[ch-'a'] = new Longest();
            }
            ptr = ptr.children[ch-'a'];
        }
        ptr.s = word ;
    }

    static String res ;
    static int len;

    public static void searchLongest(int idx,Longest ptr ,Longest root){
        if(ptr.children[idx] == null){
            return;
        }
        ptr = ptr.children[idx];
        if(ptr.s == null){
            return;
        }
        
        
        if(len<ptr.s.length()){
            len =ptr.s.length();
            res = ptr.s;
        }
        

        for(int i = 0 ; i < 26 ; i++){
            searchLongest(i,ptr ,root);
        }
        
    }
    
    public String longestWord(String[] words) {
        Longest root = new Longest();
        for(String word : words){
            addLongest(word,root);
        }
         res = "";
         len =0;
        for(int i = 0 ; i < 26 ; i++){
           
            searchLongest(i,root ,root);
        }
        return res;
    }
    
    //Stream Of Characters

    public static class StreamChecker {
        public static class Node{
            Node[] children;
            boolean isEnd ;
            Node(){
                this.children = new Node[26];
                this.isEnd = false;
            }
        }

        static Node root;

        public static void insert(String word,Node root){
            Node ptr =root;
            for(int i = word.length()-1;i>=0;i--){
                char ch = word.charAt(i);
                if(ptr.children[ch-'a'] == null){
                    ptr.children[ch-'a'] = new Node();
                }
                ptr = ptr.children[ch-'a'];
            }
            ptr.isEnd = true;
        }
        public StreamChecker(String[] words) {
             root = new Node();
            sb = new StringBuilder();
            for(var word : words){
                insert(word, root);
            }
        }

        static StringBuilder sb;
    
        public boolean query(char letter) {
            sb.append(letter);
            return find();
        }
        public boolean find(){
            Node ptr = root;
            for(int i = sb.length() -1;i>=0;i--){
                char ch = sb.charAt(i);
                if(ptr.children[ch-'a'] == null){
                    return false;
                }
                ptr= ptr.children[ch-'a'];
                if(ptr.isEnd){
                    return true;
                }

            }
            return false ;
        }
      }
    
    //1638. Count Substrings That Differ by One Character

    public int diffChar(String s1,String s2){
        int diff = 0;
        int i=0;
        int j=0;
        while(i<s1.length()){
            if(s1.charAt(i) != s2.charAt(j)){
                diff++;
            }
            if(diff== 2){
                break ;
            }
            i++;
            j++;

        }
        return diff;

    }
    public int countSubstrings(String s, String t) {
        int res =0;
        for(int i=0;i<s.length();i++){
            for(int j=0;j<t.length();j++){
                int a = i;
                int b = j;
                StringBuilder sb1 = new StringBuilder();
                StringBuilder sb2 = new StringBuilder();
                while(a<s.length() && b< t.length()){
                    sb1.append(s.charAt(a));
                    sb2.append(t.charAt(b));
                    int n = diffChar(sb1.toString(),sb2.toString());
                    if(n>1){
                        break;
                    }
                    res+=n;
                    a++;
                    b++;
                }
            }
        }
        return res;
    }
      
    //421. Maximum XOR of Two Numbers in an Array
    public static class Xor{
        Xor left;
        Xor right;
    }

    public static void addXor(int num ,Xor root){
        int bitIndex = 30;
        Xor curr = root;
        while(bitIndex >= 0){
            int mask = 1<<bitIndex;
            int bit = (num&mask) > 0 ? 1:0;

            if(bit ==0){
                if(curr.left == null){
                    curr.left = new Xor();
                }
                curr = curr.left ;
            }else{
                if(curr.right == null){
                    curr.right = new Xor();
                }
                curr = curr.right ;
            }
            bitIndex--;
        }
    }

    public static int findXor(int val, Xor root){
        int bitIndex = 30;
        Xor curr = root;
        int ans = 0;
        while(bitIndex >= 0){
            int mask = 1<<bitIndex;
            int bit = (val&mask) > 0 ?1 : 0 ;
            if(bit == 0){
                if(curr.left!=null){
                    curr = curr.left;
                }else{
                    curr = curr.right;
                    ans |= mask ;
                }
            }else{
                if(curr.right!=null){
                    curr = curr.right;
                    ans |= mask ;
                }else{
                    curr = curr.left;
                }
            }

            bitIndex--;
        }
        return ans;
    }   
    public static int findMaximumXOR(int[] nums) {
        int max =0;
       Xor root = new Xor();
       for(int num : nums){
            addXor(num,root);
       }

       for(int val : nums){
           int find = Integer.MAX_VALUE ^ val;
           int res = findXor(find,root);
           max = Math.max(max, res ^ val);
       }

       return max;
    }
    
    public static void main(String[] args) {
        for(int i = 0 ; i < 26 ;i++){
            System.out.println((char)('a'+i));
        }
        
     }
 }