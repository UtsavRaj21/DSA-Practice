import java.util.*;

public class copyRandom {
    
    public static class ListNode {
        int val = 0;
        ListNode next = null;
        ListNode random = null;

        ListNode(int val) {
            this.val = val;
        }
    }
    
    public static void copyList(ListNode head){
        ListNode curr = head;
        
        while(curr!=null){
            ListNode forw = curr.next;
            ListNode node = new ListNode(curr.val);
            curr.next = node;
            node.next = forw;
            
            curr = forw;
        }
    }
    
    public static void copyRandom(ListNode head){
        ListNode curr = head;
        
        while(curr!=null){
           ListNode Random = curr.random;
           
           if(Random != null){
               curr.next.random = Random.next;
           }
           
           curr = curr.next.next;
        }
    }
    
    public static ListNode extractCopy(ListNode head){
        ListNode curr = head;
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        while(curr!=null){
          prev.next = curr.next;
          curr.next = curr.next.next;
          
          prev = prev.next;
          curr = curr.next;
        }
        
        return dummy.next;
    }

    public static ListNode copyRandomList(ListNode head) {
       copyList(head);
       copyRandom(head);
       return extractCopy(head);
    }

    // OR 

    public static ListNode copyRandomList_2(ListNode head) {
        if(head == null) return head;
        
        HashMap<ListNode,ListNode> map = new HashMap<>();
        
        ListNode nhead = new ListNode(-1);
        ListNode prev = nhead;
        ListNode curr = head;
        
        while(curr != null){
            ListNode node = new ListNode(curr.val);
            prev.next = node ;
            
            map.put(curr,node);
            
            curr = curr.next;
            prev = prev.next;
        }
        nhead = nhead.next;
        
        ListNode c1 = head;
        ListNode c2 = nhead;
        
        while(c1 != null){
            c2.random = (c1.random != null) ? map.get(c1.random) : null;
            
            c1  = c1.next;
            c2 = c2.next;
        }
        
        return nhead;
    }
}
