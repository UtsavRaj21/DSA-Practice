import java.util.*;

public class p1{
    public static class ListNode
    {
        int val;
        ListNode next;
 
        ListNode(int val)
        {
            this.val = val;
        }
    }
   
    public static ListNode removeduplicatesUnsortedList(ListNode head){
        if(head==null || head.next == null) return head;

        HashMap<Integer,Integer> map = new HashMap<>();

        ListNode curr= head;
        while(curr != null){
            map.put(curr.val,1);
            curr = curr.next;
        }

        ListNode dummy =new ListNode(-1);
        ListNode ans =dummy;

        for(int ele:map.keySet()){
            ans.next =new ListNode(ele);
            ans= ans.next;
        }

        ans.next = null;
        return dummy.next;
    }
   
    //19
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        
        if(head == null || n<=0){
            return head;
        }
       ListNode slow = head;
       ListNode fast = head;
       
       while(n-->0){
           fast=fast.next;
          if(fast==null && n>0){
              return head;
          }
       }
       
      if(fast == null){
          ListNode remove = slow;
          head = remove.next;
          remove.next = null;
          return head;
        
      }
       
       while(fast.next!=null){
        
           slow = slow.next;
           fast = fast.next;
       }
       ListNode remove = slow.next;
       slow .next = remove.next;
       remove.next = null;
       
       return head;
    
    }
   
    //83
    public ListNode deleteDuplicatesSortedList(ListNode head) {
        if(head == null || head.next == null) return head;
        
        ListNode ans = new ListNode(-1);
        ListNode dummy = ans;
        
        int prev = -101;
        // dummy.next = head
        // dummy = dummy.next;
        
        ListNode curr= head;
        while(curr!=null){
            if(prev!=curr.val){
                dummy.next = curr;
                dummy = dummy.next;
                prev = curr.val;
            }
            curr=curr.next;
        }
        dummy.next = null;
        
        return ans.next;
        
    }

    //237
    public static void deleteNode(ListNode node) {
        node.val= node.next.val;
        node.next = node.next.next;
    }

    //1019
    public static int[] nextLargerNodes(ListNode head) {
        ArrayList<Integer> al = new ArrayList<Integer>();
        ListNode curr =head;
        
        while(curr!=null){
            al.add(curr.val);
            curr = curr.next;
        }
        
        int[] arr = new int[al.size()];
        Stack<Integer> st = new Stack<Integer>();
        
        for(int i=0 ; i< arr.length;i++){
            while(st.size()>0 && al.get(i)>al.get(st.peek())){
                int pos = st.peek();
                arr[pos]= al.get(i);
                st.pop();
            }
            st.push(i);
        }
        
        while(st.size()>0){
            arr[st.pop()] = 0;
        }
        
        return arr;
    }

     //1669
     public static ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        
        ListNode p1=list1,p2=list2,ans=dummy;
        int a1=a;
        while(a-->0){
            ans.next = p1;
            
            ans = ans.next;
            p1=p1.next;
        }
        // System.out.p
        
        while(p2!=null){
            ans.next = p2;
           
            ans = ans.next;
             p2=p2.next;
        }
        int c=b-a1+1;
        System.out.println(c);
        while(c-- > 0){
             p1 = p1.next;
        }
        
        while(p1!=null){
             ans.next = p1;
            p1=p1.next;
            ans = ans.next;
        }
        
        return dummy.next;
        
    }

    //92
    public ListNode reverseBetween(ListNode head, int left, int right) {
        
        
        
        while(left<right){
            ListNode slow=head;
            ListNode fast=head;
            int n=right-left;
            for(int i=0;i<n;i++){
            fast=fast.next;
            }

            for(int i=1;i<left;i++){
                fast=fast.next;
                slow=slow.next;
            }
            int temp=slow.val;
            slow.val=fast.val;
            fast.val=temp;
            left++;
            right--;
        }
        
        
        return head;
    }

    //1721
    public ListNode swapNodes(ListNode head, int k) {
        
        if(head  == null || head.next == null) return head;
      
        
        ListNode slow = head;
        ListNode fast = head;
        
       
        while(k-->1){
            fast = fast.next;
        }
        
        ListNode f = fast;
        while(f.next != null){
            slow = slow.next;
            f = f.next;
        }
        
        int temp = fast.val;
        fast.val = slow.val;
        slow.val = temp;

        
        
        return head;
    }

    public static void printList(ListNode head)
    {
        while (head != null)
        {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }
    
    public static void main(String[] args)
    {
        /* The constructed linked list is:
         10->12->11->11->12->11->10*/
        ListNode start = new ListNode(10);
        start.next = new ListNode(12);
        start.next.next = new ListNode(11);
        start.next.next.next = new ListNode(11);
        start.next.next.next.next = new ListNode(12);
        start.next.next.next.next.next = new ListNode(11);
        start.next.next.next.next.next.next = new ListNode(10);
 
        

        ListNode head = removeduplicatesUnsortedList(start);
        printList(head);
 
        
    }
}