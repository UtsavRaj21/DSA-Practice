public class sortedListToBST {

    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static ListNode mid(ListNode head){
        if(head==null || head.next == null) return head;

        ListNode slow = head;
        ListNode fast = head;

        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next ;
        }
        return slow;
    }

    public static TreeNode make(ListNode head){
        if(head == null ) return null;
        if(head.next == null){
            return new TreeNode(head.val);
        }
        ListNode prev = null;
        ListNode curr = head;
        ListNode mid = mid(head);
        while(curr != mid){
            prev = curr;
            curr = curr.next;
        }
        prev.next = null;
        ListNode nhead = mid.next;
        mid.next = null;
        TreeNode root = new TreeNode(mid.val);

        root.left = (prev != null ) ? make(head):make(null);
        root.right = make(nhead);

        return root;



    }

    public static  TreeNode sortedListtobst(ListNode head) {
        return make(head);
    }

    public static void main(String[] args) {
        /*
         * The constructed linked list is: 10->12->11->11->12->11->10
         */
        ListNode start = new ListNode(-10);
        start.next = new ListNode(-2);
        start.next.next = new ListNode(0);
        start.next.next.next = new ListNode(1);
        start.next.next.next.next = new ListNode(2);
        start.next.next.next.next.next = new ListNode(11);
        start.next.next.next.next.next.next = new ListNode(100);

        TreeNode root = sortedListtobst(start);
    }
}
