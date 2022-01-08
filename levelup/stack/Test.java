import java.util.*;
import java.io.*;
public class Test {
    public static class WordDictionary {

        public class Node{
            Node[] children;
            boolean isEnd;
            int no ;
            Node(){
                this.children = new Node[26];
                this.isEnd = false;
                this.no = 0;
            }
        }
        private Node root = null;
        public WordDictionary() {
            root = new Node();
        }
        public void addWord(String word) {
            Node ptr  = root;
            for(int i = 0 ; i < word.length();i++){
                char ch = word.charAt(i);
                if(ptr.children[ch-'a']==null){
                    Node nn = new  Node();
                    ptr.children[ch-'a'] = nn;
                }
                ptr.no = ptr.no+1;
                ptr = ptr.children[ch-'a'];
            }
            ptr.isEnd = true;
        }
    
        
        public int search(String word) {
            Node ptr = root;
            return searchFind(word,ptr,0);
        }

        public int searchFind(String word,Node ptr,int idx){
             if(idx == word.length()){
                return ptr.no;
            }
            char ch = word.charAt(idx);
            if(ptr.children[ch-'a'] != null){
               Node child = ptr.children[ch-'a'];
               return searchFind(word, child, idx+1);
            }
            return 0;
        }
      }


  public static void main(String[] args) throws Exception {
    BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

    WordDictionary obj = new WordDictionary();

    int n = Integer.parseInt(read.readLine());
    ArrayList<Integer> al = new ArrayList<>();
    while (n-->0) {
      String inp[] = read.readLine().split(" ");

      if (inp[0].equals("add")) {
        obj.addWord(inp[1]);
      } else if (inp[0].equals("find")) {
        al.add(obj.search(inp[1]));
      }
    }

    for(int ele : al){
        System.out.println(ele);
    }

  }
}
