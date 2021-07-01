public class bst2 {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        int bal = 0;
        int ht = 0;

        TreeNode(int val) {
            this.val = val;
            this.bal = 0;
            this.ht = 0;
        }
    }

    public static void updatehtbal(TreeNode root){
        int lh = root.left == null?-1:root.left.ht;
        int rh = root.right == null?-1:root.right.ht;

        root.bal = lh - rh;
        root.ht = Math.max(lh,rh) +1;
    }

    public static TreeNode rightRotation(TreeNode a){
        TreeNode b= a.left;

        TreeNode bKaright = b.right;
        b.right = a;
        a.left = bKaright;

        updatehtbal(a);
        updatehtbal(b);
        return b;
    }

    public static TreeNode leftRotation(TreeNode a){
        TreeNode b= a.right;

        TreeNode bKaleft = b.left;
        b.left = a;
        a.right = bKaleft;

        updatehtbal(a);
        updatehtbal(b);
        return b;
    }

    public static TreeNode getRotation(TreeNode root){
        updatehtbal(root);
        if(root.bal == 2){                                                 // ll,lr
            if(root.left.bal ==1){                                         //ll
                return rightRotation(root);
            }else{                                                         //lr
                root.left = leftRotation(root.left);
                return rightRotation(root);
            }
        }else if(root.bal == -2){                                            //rr,rl
            if(root.right.bal == -1){                                        //rr
                return leftRotation(root);
            }else{                                                           // rl
                root.right = rightRotation(root.right);
                return leftRotation(root);
            }
        }
        return root;
    }



    //================================================================================================

    public static TreeNode add(TreeNode root , int data){
        if(root == null){
            return new TreeNode(data);
        }

        else if(root.val<data){
            root.right = add(root.right,data);
        }else{
            root.left = add(root.left,data);
        }
        root = getRotation(root);
    return root;
    }

    public static int getMax(TreeNode root){
        while(root.right!=null){
            root = root.right;
        }

        return root.val;
    }


    public static TreeNode remove(TreeNode root , int data){
        if(root == null) return root;
        if(root.val<data){
            root.right = remove(root.right,data);
        }else if(root.val>data){
            root.left = remove(root.left,data);
        }else{
            if(root.left == null || root.right == null){
                return root.left!=null ? root.left:root.right;
            }
            int max = getMax(root.left);
            root.val = max;

            root.left = remove(root.left ,max);
        }
        root = getRotation(root);
    return root;
    }
}
