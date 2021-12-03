import java.util.*;

public class dec{

    //1:-198. House Robber :- Using dp
    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        if(nums.length == 1){
            return nums[0];
        }
        if(nums.length == 2){
            return Math.max(nums[0], nums[1]);
        }
        if(nums.length == 3){
            return Math.max(nums[0]+nums[2], nums[1]);
        }
        dp[0] = nums[0];
        dp[1] = nums[1];
        dp[2] = nums[0] + nums[2];

        for(int i = 3 ; i < n ; i++){
            int max = Math.max(dp[i-2],dp[i-3]);
            dp[i] = nums[i] + max;
        }
        return Math.max(dp[n-1],dp[n-2]);
        
    }
    
    //2:328. Odd Even Linked List
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }
   
    public ListNode oddEvenList(ListNode head) {
        ListNode even = new ListNode() ;
        ListNode odd = new ListNode() ;
        ListNode e = even, o = odd;
        ListNode curr = head;
        while(curr != null){
            if(curr.val % 2 == 0){
                e.next = new ListNode(curr.val);
                e = e.next;
            }else{
                o.next = new ListNode(curr.val);
                o = o.next;
            }
            curr = curr.next;
        }
        o.next = even.next;
        e.next = null;
        return odd.next ;

    }
    
    //3: 152. Maximum Product Subarray
    public int maxProduct(int[] nums) {
        int res = -(int)1e9 ;
        int ans = 1;
        
        for(int i = 0 ; i < nums.length ; i++){
            if(nums[i] == 0){
                ans = 1;
                res = Math.max(0,res);
            }else{
                ans = ans * nums[i];
                res = Math.max(ans,res);
            }
           
        }
         ans =1;
        
        for(int i = nums.length-1 ; i >=0; i--){
            if(nums[i] == 0){
                ans = 1;
                res = Math.max(0,res);
            }else{
                ans = ans * nums[i];
                res = Math.max(ans,res);
            }
           
        }
        
        return res;
    }
    public static void main(String[] args) {
        
    }
}