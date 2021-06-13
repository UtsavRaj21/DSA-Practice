import java.util.ArrayList;

public class tree {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static int size(TreeNode root) {
        if (root == null)
            return 0;

        return size(root.left) + size(root.right) + 1;
    }

    public static int height(TreeNode root) {
        if (root == null)
            return -1;

        return Math.max(height(root.left), height(root.right)) + 1;
    }

    public static int Maximum(TreeNode root) {
        if (root == null)
            return -(int) 1e9;

        return Math.max(Math.max(Maximum(root.left), Maximum(root.right)), root.val);
    }

    public static boolean find(TreeNode root, int data) {
        if (root == null)
            return false;

        return root.val == data || find(root.left, data) || find(root.right, data);
    }

    public static ArrayList<Integer> NodeToRootPath(TreeNode root, int data) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        NodeToRootPath_helper(root, data, ans);
        return ans;
    }

    public static boolean NodeToRootPath_helper(TreeNode root, int data, ArrayList<Integer> ans) {
        if (root == null) {
            return false;
        }

        boolean res = root.val == data || NodeToRootPath_helper(root.left, data, ans)
                || NodeToRootPath_helper(root.right, data, ans);
        if (res) {
            ans.add(root.val);
        }

        return res;
    }

    public static void rootToLeafPath_helper(TreeNode root, ArrayList<ArrayList<Integer>> a, ArrayList<Integer> ans) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            ans.add(root.val);
            a.add(new ArrayList<Integer>(ans));
            ans.remove(ans.size() - 1);
            return;
        }

        ans.add(root.val);

        rootToLeafPath_helper(root.left, a, ans);
        rootToLeafPath_helper(root.right, a, ans);

        ans.remove(ans.size() - 1);

    }

    public static ArrayList<ArrayList<Integer>> rootToLeafPath(TreeNode root) {
        ArrayList<ArrayList<Integer>> a = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> ans = new ArrayList<Integer>();
        rootToLeafPath_helper(root, a, ans);
        return a;
    }

    public static void exactlyOneChild_helper(TreeNode root, ArrayList<Integer> ans) {
        if (root == null || (root.left == null && root.right == null))
            return;

        if ((root.left == null || root.right == null) ) {
            ans.add(root.val);
        }
        exactlyOneChild_helper(root.left, ans);
        exactlyOneChild_helper(root.right, ans);
    }

    public static ArrayList<Integer> exactlyOneChild(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        exactlyOneChild_helper(root, ans);
        return ans;
    }

    static int x = 0; 

    public static int countExactlyOneChild(TreeNode root) {
        if (root == null)
            return 0;

        if ((root.left == null || root.right == null) && (root.left != null || root.right != null)) {
            x++;
        }
        countExactlyOneChild(root.left);
        countExactlyOneChild(root.right);
        return x;
    }
//==============================================================================================================================================
    public static boolean nodetoroadPAth(TreeNode root, TreeNode target,ArrayList<TreeNode> path) {
        if(root == null) return false ;
        
        boolean res = root.val == target.val || nodetoroadPAth(root.left,target,path) ||nodetoroadPAth(root.right,target,path); 
        
        if(res){
            path.add(root);
        }
        return res; 
    }
    
    public static void Dephts(TreeNode root, int k,TreeNode block ,ArrayList<Integer> ans) {
        if(root == null || root == block || k<0) return ;
        
        if(k==0){
            ans.add(root.val);
        }
         Dephts(root.left,k-1,block,ans);
         Dephts(root.right,k-1,block,ans);
    }
    
    //863
     public static ArrayList<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        ArrayList<TreeNode> path = new ArrayList<>();
        
        nodetoroadPAth(root,target,path);
        
        TreeNode block = null;
        ArrayList<Integer> ans = new ArrayList<Integer>();
        
        for(int i=0 ; i<path.size() ;i++){
            Dephts(path.get(i),k-i,block,ans);
            block = path.get(i);
        }
        
        return ans;
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------

public static void kDown(TreeNode root, TreeNode blockNode, int K, ArrayList<Integer> ans) {
    if (root == null || root == blockNode || K < 0)
        return;

    if (K == 0) {
        ans.add(root.val);
        return;  

    }

    kDown(root.left, blockNode, K - 1, ans);
    kDown(root.right, blockNode, K - 1, ans);
}


public static int distanceK_01(TreeNode root, TreeNode target, int k, ArrayList<Integer> ans) {
    if (root == null)
        return -1;
    if (root == target) {
        kDown(root, null, k, ans);
        return 1;
    }

    int ld = distanceK_01(root.left, target, k, ans);
    if (ld != -1) {
        kDown(root, root.left, k - ld, ans);
        return ld + 1;
    }

    int rd = distanceK_01(root.right, target, k, ans);
    if (rd != -1) {
        kDown(root, root.right, k - rd, ans);
        return rd + 1;
    }

    return -1;
}
    
//=============================================================================================================================================================
    public static ArrayList<ArrayList<Integer>> BurningTree1(TreeNode root,TreeNode target){
        ArrayList<ArrayList<Integer>> ans = new  ArrayList<ArrayList<Integer>>();

        for(int i=0;i<2*height(root);i++){
            ArrayList<Integer> a = distanceK(root , target , i);
            if(a.size()==0) break;
            ans.add(a);
        }

        return ans;
    }

//------------------------------------------------------------------------------------------------------------------------------------------
    //=============OR=====================

    public static void kdown(TreeNode root, int time, TreeNode blockNode, ArrayList<ArrayList<Integer>> ans) {
        if (root == null || root == blockNode)
            return;

        if (time == ans.size())
            ans.add(new ArrayList<>());
        ans.get(time).add(root.val);

        kdown(root.left, time + 1, blockNode, ans);
        kdown(root.right, time + 1, blockNode, ans);
    }

    public static int burningTree_helper(TreeNode root, int target, ArrayList<ArrayList<Integer>> ans) {
        if (root == null)
            return -1;
        if (root.val == target) {
            kdown(root, 0, null, ans);
            return 1;
        }

        int ld = burningTree_helper(root.left, target, ans);
        if (ld != -1) {
            kdown(root, ld, root.left, ans);
            return ld + 1;
        }

        int rd = burningTree_helper(root.right, target, ans);
        if (rd != -1) {
            kdown(root, rd, root.right, ans);
            return rd + 1;
        }

        return -1;
    }

    public static void burningTree2(TreeNode root, int target,ArrayList<ArrayList<Integer>> ans) {    // o(n) = 2n ..... o(s) = recurisive space + nothing
        
        burningTree_helper(root, target, ans);
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
        int x = 5;
        // ArrayList<ArrayList<Integer>> ans = rootToLeafPath(root);
        // for(ArrayList<Integer> a : ans){
        // System.out.println(a);;
        // }

        // ArrayList<Integer> ans = exactlyOneChild(root);
        // System.out.println(ans);

        //System.out.println(countExactlyOneChild(root));

        // ArrayList<Integer> ans = distanceK(root,root.right.right, 2);
        //  System.out.println(ans);
        // ArrayList<Integer> ans = new  ArrayList<Integer>();

        //  int p = distanceK_01(root,root.right.right,2, ans);
        // System.out.println(ans);

        //  ArrayList<ArrayList<Integer>> ans = BurningTree(root,root.right.right.left.left);
        //  for(ArrayList<Integer> a : ans){
        //     System.out.println(a);;
        // }
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
         burningTree2(root,7,ans);
         for(ArrayList<Integer> a : ans){
            System.out.println(a);;
        }

     
    }

}