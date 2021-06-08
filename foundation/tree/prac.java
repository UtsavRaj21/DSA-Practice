public class prac {
    public static class Node{
        int data = 0;
        Node left = null;
        Node right = null;


        Node(int data){
            this.data = data;
        }
    }
    public static class Pair {
        Node node;
        int state;
    
        Pair(Node node, int state) {
          this.node = node;
          this.state = state;
        }
      }
    
      public static Node construct(Integer[] arr) {
        Node root = new Node(arr[0], null, null);
        Pair rtp = new Pair(root, 1);
    
        Stack<Pair> st = new Stack<>();
        st.push(rtp);
    
        int idx = 0;
        while (st.size() > 0) {
          Pair top = st.peek();
          if (top.state == 1) {
            idx++;
            if (arr[idx] != null) {
              top.node.left = new Node(arr[idx], null, null);
              Pair lp = new Pair(top.node.left, 1);
              st.push(lp);
            } else {
              top.node.left = null;
            }
    
            top.state++;
          } else if (top.state == 2) {
            idx++;
            if (arr[idx] != null) {
              top.node.right = new Node(arr[idx], null, null);
              Pair rp = new Pair(top.node.right, 1);
              st.push(rp);
            } else {
              top.node.right = null;
            }
    
            top.state++;
          } else {
            st.pop();
          }
        }
    
        return root;
      }
    
    public static int sum(Node root) {
        if (root == null)
            return 0;

        int leftSum = sum(root.left);
        int rightSum = sum(root.right);

        return leftSum + root.data + rightSum;
    }

    public static boolean findData(Node root, int data) {
        if (root == null)
            return false;

        return root.data == data || findData(root.left, data) || findData(root.right, data);
    }

    public static int heightInTermsOfNode(Node root) {
        if (root == null)
            return 0;

        int leftHeight = heightInTermsOfNode(root.left);
        int rightHeight = heightInTermsOfNode(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void display(Node node) {
        if (node == null) {
          return;
        }
    
        String str = "";
        str += node.left == null ? "." : node.left.data + "";
        str += " <- " + node.data + " -> ";
        str += node.right == null ? "." : node.right.data + "";
        System.out.println(str);
    
        display(node.left);
        display(node.right);
      }

    // how to contruct a tree
    public static void main(String[] args) throws Exception {
        
        String arr[] = { 50, 25 ,12, n, n, 37 ,30 ,n ,n ,n ,75, 62 ,n, 70, n, n ,87, n, n };
        Node root = construct(arr);
        System.out.println(sum(root));
        System.out.println(findData(root,5));
        System.out.println(heightInTermsOfNode(root));
        
    }
}
