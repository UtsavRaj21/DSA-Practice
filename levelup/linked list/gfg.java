import java.util.*;

public class gfg{

    public static class Node {
        int data;
        Node next;
     
       Node(int data) {
           this.data = data;
       }
     }

     public static Node reverse(Node head){
        if(head == null || head.next == null) return head;
        
        Node curr = head;
        Node prev = null;
        
        while(curr != null){
            Node forw = curr.next;
            curr.next = prev;
            prev = curr;
            curr = forw;
        }
        return prev;
    }

    // gfg :- https://practice.geeksforgeeks.org/problems/delete-nodes-having-greater-value-on-right/1#

    public static Node compute(Node head)
    {
        if(head == null || head.next == null) return head;
        Stack<Integer> st = new Stack<>();
        Node curr = head;
        
        while(curr != null){
            st.push(curr.data);
            curr = curr.next;
        }
        
        Node dummy = new Node(-1);
        Node ans = dummy;
        int prev =0;
        while(st.size()>0){
            int c = st.pop();
            if(c>prev){
                ans.next = new Node(c);
                ans = ans.next;
                prev = c;
            }
        }
        
         head = reverse(dummy.next);
        return head;
    }

    // gfg :- https://practice.geeksforgeeks.org/problems/deletion-and-reverse-in-linked-list/1#

    public static Node deleteNode(Node head,int d)
    {
        if(head == null ) return head;
        if(head.data == d && head.next == null ) return null;
        
        if(head.data == d){
            head = reverse(head.next);
            return head;
        }
       
        Node curr=head;
        Node prev = curr;
        curr = curr.next;
        
        while(curr!=null){
            if(curr.data == d){
                prev.next = curr.next;
                curr.next = null;
                break;
            }
            prev = curr;
            curr = curr.next;
        }
        head = reverse(head);
        return head;
    }

    //gfg :- https://practice.geeksforgeeks.org/problems/delete-n-nodes-after-m-nodes-of-a-linked-list/1#
    
    public static void linkdelete(Node head, int M, int N)
    {
        
        Node curr = head;
        
        while(curr != null){
            int m = M;
            Node prev = null;
            while(m-->0){
                if(curr == null) break;
                prev = curr;
                curr = curr.next;
            }
            
            int n=N;
            while(n-->0){
                if(curr == null) break;
                curr = curr.next;
            }
            
            prev.next = curr;
        }
    } 
    
    public static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
    }

    public static void main(String[] args) {
        /*
         * The constructed linked list is: 10->12->11->11->12->11->10
         */
        Node start = new Node(5);
        start.next = new Node(12);
        start.next.next = new Node(19);
        start.next.next.next = new Node(10);
        start.next.next.next.next = new Node(12);
        start.next.next.next.next.next = new Node(1);
        start.next.next.next.next.next.next = new Node(10);

        //Node head = compute(start);
        //Node head = deleteNode(start, 10);

        linkdelete(start,6,3);

        printList(start);

    }
}
