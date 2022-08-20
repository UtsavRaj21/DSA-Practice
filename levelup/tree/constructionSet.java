
import java.util.*;

public class constructionSet {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static TreeNode constructFromInOrder(int[] inOrder, int si, int ei) {
        if (si > ei)
            return null;

        int mid = (si + ei) / 2;
        TreeNode root = new TreeNode(inOrder[mid]);

        root.left = constructFromInOrder(inOrder, si, mid - 1);
        root.right = constructFromInOrder(inOrder, mid + 1, ei);

        return root;
    }

    static int idx = 0;

    public static TreeNode bstFromPreorder(int[] preorder, int lr, int rr) {
        if (idx == preorder.length || preorder[idx] < lr || preorder[idx] > rr)
            return null;

        TreeNode node = new TreeNode(preorder[idx++]);

        node.left = bstFromPreorder(preorder, lr, node.val);
        node.right = bstFromPreorder(preorder, node.val, rr);

        return node;
    }

    public static TreeNode bstFromPreorder(int[] preorder) {
        idx = 0;
        return bstFromPreorder(preorder, -(int) 1e9, (int) 1e9);
    }

    public static TreeNode bstFromPostOrder(int[] postOrder, int lr, int rr) {                                                                                                                                                                                                                                                                                                                                                                                         
        if (idx == -1 || postOrder[idx] < lr || postOrder[idx] > rr)
            return null;

        TreeNode node = new TreeNode(postOrder[idx--]);

        node.right = bstFromPreorder(postOrder, node.val, rr);
        node.left = bstFromPreorder(postOrder, lr, node.val);

        return node;
    }

    public static TreeNode bstFromPostOrder(int[] postOrder) {
        idx = postOrder.length - 1;
        return bstFromPostOrder(postOrder, -(int) 1e9, (int) 1e9);
    }

    public static class bstLPair {
        TreeNode par = null;
        int lr = 0;
        int rr = 0;

        bstLPair(TreeNode par, int lr, int rr) {
            this.par = par;
            this.lr = lr;
            this.rr = rr;
        }
    }

    public static TreeNode constructBSTFromLevelOrder(int[] LevelOrder) {
        if (LevelOrder.length == 0)
            return null;
        LinkedList<bstLPair> que = new LinkedList<>();
        TreeNode root = new TreeNode(LevelOrder[0]);
        int idx = 1;

        que.addLast(new bstLPair(root, -(int) 1e9, root.val));
        que.addLast(new bstLPair(root, root.val, (int) 1e9));

        while (idx < LevelOrder.length) {
            bstLPair rp = que.removeFirst();
            int lr = rp.lr, rr = rp.rr;
            if (LevelOrder[idx] < lr || LevelOrder[idx] > rr)
                continue;

            TreeNode node = new TreeNode(LevelOrder[idx++]);
            if (node.val < rp.par.val)
                rp.par.left = node;
            else
                rp.par.right = node;

            que.addLast(new bstLPair(node, rp.lr, node.val));
            que.addLast(new bstLPair(node, node.val, rp.rr));
        }

        return root;
    }

    public static TreeNode preOrIn(int[] pre, int psi, int pei, int[] in, int isi, int iei) {
        if (isi > iei)
            return null;
        int idx = isi;
        while (in[idx] != pre[psi])
            idx++;

        int tel = idx - isi;
        TreeNode root = new TreeNode(pre[psi]);

        root.left = preOrIn(pre, psi + 1, psi + tel, in, isi, idx - 1);
        root.right = preOrIn(pre, psi + tel + 1, pei, in, idx + 1, iei);

        return root;
    }

    public static TreeNode postOrIn(int[] post, int psi, int pei, int[] in, int isi, int iei) {
        if (isi > iei)
            return null;
        int idx = isi;
        while (in[idx] != post[pei])
            idx++;

        int tel = idx - isi;
        TreeNode root = new TreeNode(post[pei]);

        root.left = postOrIn(post, psi, psi + tel - 1, in, isi, idx - 1);
        root.right = postOrIn(post, psi + tel, pei - 1, in, idx + 1, iei);

        return root;
    }

    public static TreeNode postOrpre(int[] pre, int psi, int pei, int[] post, int ppsi, int ppei) {
       if(psi>pei) return null;
       TreeNode root = new TreeNode(pre[psi]);
       if(psi == pei) return root;

       int idx = ppsi;
       while(post[idx] != pre[psi+1]){
           idx++;
       }
       int tno = idx-ppsi+1;

       root.left = postOrpre(pre, psi+1, psi+tno, post, ppsi, idx);
       root.right = postOrpre(pre, psi+tno+1, pei, post, idx+1, ppei);

       return root;
    }

     // HM_:https://practice.geeksforgeeks.org/problems/construct-tree-from-inorder-and-levelorder/1

     public static TreeNode inlevel_construct(int[] level,int lsi,int lei,int[] in,int isi,int iei){
         if(level.length == 0) return null;
         TreeNode root = new TreeNode(level[0]);
         if(level.length == 1){
             return root;
         }

       int idx = isi;
       HashMap<Integer,Integer> map = new HashMap<>();
       while(in[idx] != level[0]){
           map.put(in[idx], 1);
           idx++;
       }

       int[] levelleft = new int[idx-isi];
       int[] levelright=new int[iei-idx];
       int l=0,r=0;

       for(int i=1;i<level.length;i++){
            if(map.containsKey(level[i])){
                levelleft[l]=level[i];
                l++;
            }else{
                levelright[r]=level[i];
                r++;
            }
       }

       root.left = inlevel_construct(levelleft, 0, levelleft.length-1, in,isi, idx-1);
       root.right =inlevel_construct(levelright, 0, levelright.length-1, in, idx+1, iei);

       return root;

    }
    
     public static TreeNode inlevel(int[] level,int[] in){
         int n = in.length-1;
         return inlevel_construct(level,0,n,in,0,n);
     }

     // https://www.geeksforgeeks.org/check-if-given-preorder-inorder-and-postorder-traversals-are-of-same-tree/


     public static boolean checkPost(TreeNode root,int[] post,int[] idx ){
         if(root == null) return true;

         boolean left = checkPost(root.left, post,idx);
         boolean right = checkPost(root.right, post,idx);

         if(!left || !right){
             return false;
         }
         if(root.val != post[idx[0]]){
             return false;
         }
         idx[0] = idx[0]++;
         return true;
     }

     public static Boolean checkTree(int[] in, int[] pre ,int[] post){
         int n = in.length-1;
         TreeNode root = preOrIn(pre, 0, n, in, 0, n);
         int[] idx = new int[1];
         idx[0] = 0;
         boolean ans = checkPost(root,post,idx);
         return ans;
     }
    
     // static String str="";

    // public static void seria(TreeNode root){
    //     if(root == null){
    //         str = str+"null,";
    //         // System.out.println(str);
    //         return;
    //     }
    //     str = str+root.val+",";
    //     seria(root.left);
    //     seria(root.right);
    // }

    public static void seria(TreeNode root,StringBuilder sb){
        if(root == null){
            sb.append("#,");
            // System.out.println(str);
            return;
        }
        sb.append(root.val+",");
        seria(root.left,sb);
        seria(root.right,sb);
    }

    public static String serialize_preorder(TreeNode root){
        StringBuilder sb = new StringBuilder();
        seria(root,sb);
        return sb.toString();
    }

    public static TreeNode preConstruct(String[] pre,int[] idx,int n) {
       if(idx[0]>n || pre[idx[0]].equals("#")){
           idx[0]++;
           return null;
       }

       int i =idx[0]++;

       int data = Integer.parseInt(pre[i]);
       TreeNode root = new TreeNode(data);

       root.left = preConstruct(pre,idx,n);
       root.right = preConstruct(pre,idx,n);

       return root;
    }


    public static TreeNode deserialize_preorder(String s) {
        String[] pre = s.split(",");
        int n=pre.length-1;
        int[] idx = new int[1];
        idx[0] = 0;
        TreeNode root = preConstruct(pre,idx,n); 
        return root;
    }

    public static String serialize_levelOrder(TreeNode root) {
        if (root == null)
            return "";
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);
        StringBuilder sb = new StringBuilder();

        while (que.size() != 0) {
            TreeNode rnode = que.removeFirst();
            if (rnode == null) {
                sb.append("# ");
                continue;
            } else
                sb.append(rnode.val + " ");

            que.addLast(rnode.left);
            que.addLast(rnode.right);
        }

        return sb.toString();
    }

    public static TreeNode deserialize_Levelorder(String str) {
        if (str.length() == 0)
            return null;

        LinkedList<TreeNode> que = new LinkedList<>();
        String[] arr = str.split(" ");
        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));

        que.addLast(root);

        int i = 1, n = arr.length;
        while (i < n) {
            TreeNode rnode = que.removeFirst();
            if (i < n && !arr[i].equals("#")) {
                TreeNode lci = new TreeNode(Integer.parseInt(arr[i]));
                rnode.left = lci;
                que.addLast(lci);
            }
            i++;

            if (i < n && !arr[i].equals("#")) {
                TreeNode rci = new TreeNode(Integer.parseInt(arr[i]));
                rnode.right = rci;
                que.addLast(rci);
            }
            i++;
        }

        return root;
    }

    // Q) screenShot(152)

    // public TreeNode inorderSuccessor(TreeNode node) {

    //     TreeNode right = node.right;
    //     if (right != null) {
    //         while (right.left != null) {
    //             right = right.left;
    //         }

    //         return right;
    //     }

    //     while (node.parent != null && node.parent.left != node) {
    //         node = node.parent;
    //     }

    //     return node.parent;
    // }

    public static void display(TreeNode node) {
        if (node == null) {
          return;
        }

        String str = "";
        str += node.left == null ? "." : node.left.val + "";
        str += " -> " + node.val + " <- ";
        str += node.right == null ? "." : node.right.val + "";
        System.out.println(str);

        display(node.left);
        display(node.right);
      }

    public static void main(String args[]) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.right.right.left = new TreeNode(8);
        root.right.right.left.left = new TreeNode(9);

        //String s = serialize_preorder(root);
        //System.out.println(s);
        // deserialize(s);
        int inOrder[] = {4, 2, 5, 1, 3};
        int preOrder[] = {1, 2, 4, 5, 3};
        int postOrder[] = {4, 5, 2, 3, 1};

        // Boolean ans = checkTree(inOrder, preOrder, postOrder);
        // System.out.println(ans);
        int level[] = {2 ,7 ,15, 12 ,6 ,9, 5 ,11, 4};
        int in[] = {12, 7, 5, 6, 11 ,2 ,15, 4 ,9};

        TreeNode node = inlevel(level,in);
        display(node);

        String str = scn.next();
        String[] arr = str.split(" ");
        ListNode head = new ListNode(-1);
        ListNode curr = head;
        int n = arr.length;
        int i=0;
        while(i<n){
            curr.next = new ListNode(Integer.valueOf(arr[i]));
            curr = curr.next;
            i++; 
        }

        head = head.next;
        
    }
}