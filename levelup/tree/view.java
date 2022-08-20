import java.util.*;
import java.util.LinkedList;

public class view {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void levelOrder(TreeNode root){
        LinkedList<TreeNode> que = new LinkedList<>();
        que.add(root);
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        int level = 0;
        while(que.size()!=0){
            int s = que.size();
            ArrayList<Integer> small = new ArrayList<>();
            while(s-->0){
                TreeNode rNode= que.removeFirst();
                small.add(rNode.val);

                if(rNode.left!=null){
                    que.addLast(rNode.left);
                }
                if(rNode.right!=null){
                    que.addLast(rNode.right);
                }
            }
            ans.add(small);
            level++;
        }

        int count=0;

        for(var list : ans){
            System.out.println(count++ + " -> " + list);
        }
    }

    public static List<Integer> leftView(TreeNode root){
        List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;
        LinkedList<TreeNode> que = new LinkedList<>();
        que.add(root);
        int level = 0;
        while(que.size()!=0){
            int s = que.size();
            ans.add(que.getFirst().val);
            while(s-->0){
                TreeNode rNode= que.removeFirst();

                if(rNode.left!=null){
                    que.addLast(rNode.left);
                }
                if(rNode.right!=null){
                    que.addLast(rNode.right);
                }
            }
        }
        return ans;
    }

    public static List<Integer> RightView(TreeNode root){
        List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;
        LinkedList<TreeNode> que = new LinkedList<>();
        que.add(root);
        int level = 0;
        while(que.size()!=0){
            int s = que.size();
            ans.add(que.getFirst().val);
            while(s-->0){
                TreeNode rNode= que.removeFirst();
                if(rNode.right!=null){
                    que.addLast(rNode.right);
                }
                if(rNode.left!=null){
                    que.addLast(rNode.left);
                }
            }
        }
        return ans;
    }

    public static void widthOfShadow(TreeNode root,int vl,int[] minmax){

        if(root == null) return;

        minmax[0] = Math.min(vl,minmax[0]);
        minmax[1] = Math.max(vl,minmax[1]);

        widthOfShadow(root.left,vl-1,minmax);
        widthOfShadow(root.right,vl+1,minmax);
    }

    public static class vPair{
        TreeNode node = null;
        int vl = 0;
        vPair(TreeNode node,int vl){
            this.vl = vl;
            this.node = node;
        }
    }

    public static void vertical(TreeNode root){
        if(root == null) return;

        int[] minmax = new int[2];
        widthOfShadow(root,0,minmax);
        int len = minmax[1] - minmax[0] +1;

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for(int i=0;i<len;i++){
            ans.add(new ArrayList<>());
        }

        LinkedList<vPair> que = new LinkedList<>();
        que.addLast(new vPair(root,Math.abs(minmax[0])));

        while(que.size()!=0){
            int s = que.size();
            while(s-->0){
                vPair rPair = que.removeFirst();
                TreeNode rNode = rPair.node;
                int vl = rPair.vl;

                ans.get(vl).add(rNode.val);

                if(rNode.left != null){
                    que.addLast(new vPair(rNode.left,vl-1));
                }
                if(rNode.right != null){
                    que.addLast(new vPair(rNode.right,vl+1));
                }
            }
        }

        int count=0;
        for(var list : ans){
            System.out.println(count++ + " -> " + list);
        }
    }

    public static void BottomView(TreeNode root){
        if(root == null) return;

        int[] minmax = new int[2];
        widthOfShadow(root,0,minmax);
        int len = minmax[1] - minmax[0] +1;

       ArrayList<Integer> ans = new ArrayList<>();
        for(int i=0;i<len;i++){
            ans.add(null);
        }

        LinkedList<vPair> que = new LinkedList<>();
        que.addLast(new vPair(root,Math.abs(minmax[0])));

        while(que.size()!=0){
            int s = que.size();
            while(s-->0){
                vPair rPair = que.removeFirst();
                TreeNode rNode = rPair.node;
                int vl = rPair.vl;

                ans.set(vl,rNode.val);

                if(rNode.left != null){
                    que.addLast(new vPair(rNode.left,vl-1));
                }
                if(rNode.right != null){
                    que.addLast(new vPair(rNode.right,vl+1));
                }
            }
        }

        int count=0;
        for(var list : ans){
            System.out.println(count++ + " -> " + list);
        }
    }

    public static void TopView(TreeNode root){
        if(root == null) return;

        int[] minmax = new int[2];
        widthOfShadow(root,0,minmax);
        int len = minmax[1] - minmax[0] +1;

       ArrayList<Integer> ans = new ArrayList<>();
        for(int i=0;i<len;i++){
            ans.add(null);
        }

        LinkedList<vPair> que = new LinkedList<>();
        que.addLast(new vPair(root,Math.abs(minmax[0])));

        while(que.size()!=0){
            int s = que.size();
            while(s-->0){
                vPair rPair = que.removeFirst();
                TreeNode rNode = rPair.node;
                int vl = rPair.vl;
                if(ans.get(vl) == null){
                    ans.set(vl,rNode.val);
                }
                

                if(rNode.left != null){
                    que.addLast(new vPair(rNode.left,vl-1));
                }
                if(rNode.right != null){
                    que.addLast(new vPair(rNode.right,vl+1));
                }
            }
        }

        int count=0;
        for(var list : ans){
            System.out.println(count++ + " -> " + list);
        }
    }
    
    public static void diagonal_1(TreeNode root) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
           if(root == null) return ;

        int[] minmax = new int[2];
        widthOfShadow(root,0,minmax);
        int len = minmax[1] - minmax[0] +1;

        for(int i=0;i<len;i++){
            ans.add(new ArrayList<>());
        }

        LinkedList<vPair> que = new LinkedList<>();
        que.addLast(new vPair(root,Math.abs(minmax[0])));

        while(que.size()!=0){
            int s = que.size();
            while(s-->0){
                vPair rPair = que.removeFirst();
                TreeNode rNode = rPair.node;
                int vl = rPair.vl;

                ans.get(vl).add(rNode.val);

                if(rNode.left != null){
                    que.addLast(new vPair(rNode.left,vl-1));
                }
                if(rNode.right != null){
                    que.addLast(new vPair(rNode.right,vl));
                }
            }
        }
        int count=0;
        for(var list : ans){
            if(list.size()==0) break;
            System.out.println(count++ + " -> " + list);
        }
      }

    public static void diagonal_2(TreeNode root)
      {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
           if(root == null) return ;

        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);

        while(que.size()!=0){
            int s = que.size();
            ArrayList<Integer> small = new ArrayList<>();
            while(s-->0){
                TreeNode rNode = que.removeFirst();
                while(rNode!=null){
                    small.add(rNode.val);
                    if(rNode.left != null){
                        que.addLast(rNode.left);
                    }
                    rNode = rNode.right;
                }
            }
            ans.add(small);
        }
        int count=0;
        for(var list : ans){
            if(list.size()==0) break;
            System.out.println(count++ + " -> " + list);
        }
      }

    public static void diagonal_2AntiClock(TreeNode root) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
             if(root == null) return ;
  
          LinkedList<TreeNode> que = new LinkedList<>();
          que.addLast(root);
  
          while(que.size()!=0){
              int s = que.size();
              ArrayList<Integer> small = new ArrayList<>();
              while(s-->0){
                  TreeNode rNode = que.removeFirst();
                  while(rNode!=null){
                      small.add(rNode.val);
                      if(rNode.right != null){
                          que.addLast(rNode.right);
                      }
                      rNode = rNode.left;
                  }
              }
              ans.add(small);
          }
          int count=0;
          for(var list : ans){
              if(list.size()==0) break;
              System.out.println(count++ + " -> " + list);
          }
      }

    public static void verticalSumOrder(TreeNode root){
        if(root == null) return;

        int[] minmax = new int[2];
        widthOfShadow(root,0,minmax);
        int len = minmax[1] - minmax[0] +1;

       ArrayList<Integer> ans = new ArrayList<>();
        for(int i=0;i<len;i++){
            ans.add(0);
        }

        LinkedList<vPair> que = new LinkedList<>();
        que.addLast(new vPair(root,Math.abs(minmax[0])));

        while(que.size()!=0){
            int s = que.size();
            while(s-->0){
                vPair rPair = que.removeFirst();
                TreeNode rNode = rPair.node;
                int vl = rPair.vl;

                ans.set(vl,ans.get(vl)+rNode.val);

                if(rNode.left != null){
                    que.addLast(new vPair(rNode.left,vl-1));
                }
                if(rNode.right != null){
                    que.addLast(new vPair(rNode.right,vl+1));
                }
            }
        }
        int count=0;
      for(var list : ans){
          System.out.println(count++ + " -> " + list);
      }
    }

    public static void diagonalOrderSum(TreeNode root)
    {
        ArrayList<Integer> ans = new ArrayList<>();
         if(root == null) return ;

      LinkedList<TreeNode> que = new LinkedList<>();
      que.addLast(root);

      while(que.size()!=0){
          int s = que.size();
          int sum =0;
          while(s-->0){
              TreeNode rNode = que.removeFirst();
              while(rNode!=null){
                  sum+=rNode.val;
                  if(rNode.left != null){
                      que.addLast(rNode.left);
                  }
                  rNode = rNode.right;
              }
          }
          ans.add(sum);
      }
      int count=0;
      for(var list : ans){
          System.out.println(count++ + " -> " + list);
      }
    }

    public static class ListNode{
        int data =0;
        ListNode next = null;
        ListNode prev = null;
        ListNode(int data){
            this.data= data;
        }
    }

    public static void verticalSumOrder_2(TreeNode root){
        if(root == null) return;
        ListNode curr = new ListNode(0); 
        verticalSumOrder_2Helper(root,curr);
    }

    public static void verticalSumOrder_2Helper(TreeNode root,ListNode node){
        node.data += root.val;
        if(root.left!=null){
            if(node.prev == null){
                ListNode nNode = new ListNode(0);
                nNode.next = node;
                node.prev = nNode;
            }
            verticalSumOrder_2Helper(root.left , node.prev);
        }

        if(root.right!=null){
            if(node.next == null){
                ListNode nNode = new ListNode(0);
                nNode.prev = node;
                node.next = nNode;
            }
            verticalSumOrder_2Helper(root.right , node.next);
        }
    }

    public static void DiagonalSumOrder_2(TreeNode root){
        if(root == null) return;
        ListNode curr = new ListNode(0); 
        DiagonalSumOrder_2Helper(root,curr);
    }

    public static void DiagonalSumOrder_2Helper(TreeNode root,ListNode node){
        node.data = root.val;
        if(root.left!=null){
            if(node.prev == null){
                ListNode nNode = new ListNode(0);
                nNode.next = node;
                node.prev = nNode;
            }
            verticalSumOrder_2Helper(root.left , node.prev);
        }

        if(root.right!=null){
            verticalSumOrder_2Helper(root.right , node.next);
        }
    }

    //987

    public static void vertical_Pq(TreeNode root){
        if(root == null) return;

        int[] minmax = new int[2];
        widthOfShadow(root,0,minmax);
        int len = minmax[1] - minmax[0] +1;

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for(int i=0;i<len;i++){
            ans.add(new ArrayList<>());
        }

        PriorityQueue<vPair> que = new PriorityQueue<>((a,b)->{
            if(a.vl!=b.vl){
                return a.vl - b.vl;
            }
            return a.node.val - b.node.val;
        });

        PriorityQueue<vPair> child = new PriorityQueue<>((a,b)->{
            if(a.vl!=b.vl){
                return a.vl - b.vl;
            }
            return a.node.val - b.node.val;
        });
        que.add(new vPair(root,Math.abs(minmax[0])));

        while(que.size()!=0){
            int s = que.size();
            while(s-->0){
                vPair rPair = que.remove();
                TreeNode rNode = rPair.node;
                int vl = rPair.vl;

                ans.get(vl).add(rNode.val);

                if(rNode.left != null){
                    child.add(new vPair(rNode.left,vl-1));
                }
                if(rNode.right != null){
                    child.add(new vPair(rNode.right,vl+1));
                }
            }

            PriorityQueue<vPair> temp = que;
            que = child;
            child = temp;
        }

        int count=0;
        for(var list : ans){
            System.out.println(count++ + " -> " + list);
        }
    }
    
    public static void list(Node head , int n){
       
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
        // levelOrder(root);

        // int[] minmax = new int[2];
        // widthOfShadow(root,0,minmax);
        // int len = minmax[1] - minmax[0] +1;
        // System.out.println(len);

        //vertical(root);
        //BootomView(root);
        //TopView(root);
        // diagonal_1(root);
        // diagonal_2(root);
        //diagonal_2AntiClock(root);

        // verticalSumOrder(root);
        //diagonalOrderSum(root);

        verticalSumOrder_2(root);
    }
}
