import java.util.*;

public class gfg {

    public static class Node{
        public int data;
        public Node left,right;
        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    public static void leftcall(Node root,ArrayList<Integer> left){
        if(root == null){
            left.add(0);
            return;
        }
        
        left.add(root.data);
        leftcall(root.left,left);
        leftcall(root.right,left);
    }
    
    public static void rightcall(Node root,ArrayList<Integer> right){
        if(root == null){
            right.add(0);
            return;
        }
        
        right.add(root.data);
        rightcall(root.right,right);
        rightcall(root.left,right);
    }
    
//    public static long imgMultiply(Node root)                     wrong
//    {
//        ArrayList<Integer> left = new ArrayList<>();
//        ArrayList<Integer> right = new ArrayList<>();
//        left.add(root.data);
//          right.add(root.data);
       
//        leftcall(root.left,left);
//        rightcall(root.right,right);
       
//        long sum =0 ;
       
//        for(int i = 0 ;i<left.size();i++){
//            sum = (sum+(left.get(i) * right.get(i))) % 1000000007;
//        }
       
//        return sum;
       
//    }

   public static void main(String args[]) {
    Node root = new Node(1);
    root.left = new Node(2);
    root.right = new Node(3);
    root.left.left = new Node(4);
    root.left.right = new Node(5);
    root.right.left = new Node(6);
    root.right.right = new Node(7);
    root.right.right.left = new Node(8);
    root.right.right.left.left = new Node(9);

    // System.out.println(imgMultiply(root));
    }
}