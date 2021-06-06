import java.util.*;

public class quickLinked {
    public static Scanner scn = new Scanner(System.in);
    
    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode tail(ListNode head){
        if(head == null || head.next == null){
            return head;
        }

        ListNode curr =head;
        while(curr.next!=null){
            curr = curr.next;
        }
        return curr;
    }

    public static int length(ListNode head){
        if(head == null ){
            return 0;
        }
        int len =0 ;
        ListNode curr = head;
        while(curr!=null){
            curr = curr.next;
            len++;
        }
        return len;
    }

    public static ListNode[] segrigate(ListNode head,int pivotIdx){

        ListNode curr = head;
        ListNode pivotNode = head;

        ListNode small = new ListNode(-1);
        ListNode larger = new ListNode(-1);

        ListNode sp=small,lp = larger;

        while(pivotIdx-->0){
            pivotNode = pivotNode.next;
        }

        while(curr!=null){
            if(curr!=pivotNode){
                if(curr.val < pivotNode.val){
                    sp.next = curr;
                    sp = sp.next;
                }else{
                    lp.next = curr;
                    lp = lp.next;
                }
            }
            curr = curr.next;
        }
        pivotNode.next = null;
        sp.next = null;
        lp.next = null;

        ListNode lh = small.next;
        ListNode lt = small.next !=null ? sp :null;
        ListNode rh = larger.next;
        ListNode rt = larger.next !=null ? lp :null;
        
        return new ListNode[]{lh,lt,pivotNode,rh,rt};

        
    }
    
    public static ListNode[] quickSortHelper(ListNode head,ListNode tail) {
        if(head == null || tail == null){
            return new ListNode[]{head,tail};
        }

        int len = length(head);
        int pivotIdx = len/2;

        ListNode[] segrigatElements = segrigate(head,pivotIdx);

        ListNode[] left = quickSortHelper(segrigatElements[0],segrigatElements[1]);
        ListNode[] right = quickSortHelper(segrigatElements[3], segrigatElements[4]);

        return mergeSort(left,segrigatElements[2],right);

    }

    public static ListNode[] mergeSort(ListNode[] left,ListNode pivotNode,ListNode[] right){

        ListNode head = null;
        ListNode tail = null;
        if(left[0] != null && right[0]!=null){
            left[1].next = pivotNode;
            pivotNode.next = right[0];
            head =left[0]; 
            tail = right[1];
        }else if(right[0]!=null){
            head = pivotNode;
            pivotNode.next = right[0];
            tail = right[1];
        }else if(left[0] !=null){
            head = left[0];
            left[1].next = pivotNode;
            tail = pivotNode;
        }else{
            head=tail=pivotNode;
        }


        return new ListNode[]{head,tail};
    }

    
    public static ListNode quickSort(ListNode head) {
        ListNode tail = tail(head);
        return quickSortHelper(head, tail)[0];
        
    }


    public static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

    public static ListNode createList(int n) {
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        while (n-- > 0) {
            prev.next = new ListNode(scn.nextInt());
            prev = prev.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        ListNode h1 = createList(n);

        ListNode head = quickSort(h1);
        printList(head);
    }
}
