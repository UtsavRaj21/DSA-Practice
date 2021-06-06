import java.util.*;
import java.io.*;

public class prac {

    public static void permute(int[] nums) {
  
        ArrayList<ArrayList<Integer>> ans=new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> a=new ArrayList<Integer>();
        boolean[] vis=new boolean[nums.length];
        per(nums,a,ans,vis,0);
        System.out.println(a);
        System.out.print(ans);
    }
    
    public static void per(int[] nums,ArrayList<Integer> a,ArrayList<ArrayList<Integer>> ans,boolean[] vis,int bn){
        if(a.size()==nums.length){
            System.out.println("1");
            
            ans.add(a);
            return;
        }
        
        for(int i=bn;i<nums.length;i++){
            if(!vis[i]){
                vis[i]=true;
                 a.add(nums[i]);
                per(nums,a,ans,vis,0);
                // a.remove(a.get(a.size()-1));
                vis[i]=false;
            }
            
        }
    }

    public static void main(String[] args) {
        int[] nums={1,2,3};
        permute(nums);
    }
}
