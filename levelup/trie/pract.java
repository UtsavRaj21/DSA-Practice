public class pract {

    public static class Trie {

        /** Initialize your data structure here. */
        public class Node{
            Node[] children;
            boolean isEnd;
            
            Node(){
                this.children = new Node[26];
                this.isEnd = false;
            }
        }

        public Node root ;
        public Trie() {
            root = new Node();
        }
    
        /** Inserts a word into the trie. */
        public void insert(String word) {
            Node ptr = root;
            for(int i =0 ; i < word.length() ; i++){
                char ch = word.charAt(i);
                if(ptr.children[ch-'a']==null){
                    ptr.children[ch-'a'] = new Node();
                }
                ptr = ptr.children[ch-'a'];
            }
            ptr.isEnd = true;
        }
    
        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            Node ptr = root;
            for(int i =0 ; i < word.length() ; i++){
                char ch = word.charAt(i);
                if(ptr.children[ch-'a']==null){
                    return false;
                }
                ptr = ptr.children[ch-'a'];
            }
            if(ptr.isEnd = true){
                return true;
            }
            return false;
        }
    
        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            Node ptr = root;
            for(int i =0 ; i < prefix.length() ; i++){
                char ch = prefix.charAt(i);
                if(ptr.children[ch-'a']==null){
                    return false;
                }
                ptr = ptr.children[ch-'a'];
            }
            return true;
        }
      }
    public static void main(String[] args) {
        
    }
}
