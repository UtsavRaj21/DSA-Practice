import java.util.*;
import java.util.LinkedList;

public class basic {

    // check for size (edge cases) 0,1,2, even length,odd length

    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode midNode(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static ListNode reverse(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode curr = head;
        ListNode prev = null;

        while (curr != null) {
            ListNode forw = curr.next;
            curr.next = prev;
            prev = curr;
            curr = forw;
        }

        // head = prev ;
        // return head;
        return prev;
    }

    public static boolean Palindrome(ListNode head) {
        if (head == null && head.next == null) {
            return true;
        }
        ListNode mid = midNode(head);
        ListNode nhead = mid.next;
        mid.next = null;
        nhead = reverse(nhead);

        ListNode c1 = head;
        ListNode c2 = nhead;
        boolean res = true;
        while (c2 != null) {
            if (c1.val != c2.val) {
                res = false;
                break;
            }
            c1 = c1.next;
            c2 = c2.next;
        }
        nhead = reverse(nhead);
        mid.next = nhead;

        return res;
    }

    public static void fold(ListNode head) {
        if (head == null && head.next == null) {
            return;
        }

        ListNode mid = midNode(head);
        ListNode nhead = mid.next;
        mid.next = null;
        nhead = reverse(nhead);

        ListNode c1 = head;
        ListNode c2 = nhead;

        while (c2 != null) {
            ListNode f1 = c1.next; // backup
            ListNode f2 = c2.next; // backup

            c1.next = c2; // link
            c2.next = f1; // link

            c1 = f1; // move
            c2 = f2; // move
        }
    }

    public static void unfold1(ListNode head) {
        if (head == null && head.next == null) {
            return;
        }

        ListNode l1 = new ListNode(-1);
        ListNode l2 = new ListNode(-1);
        ListNode p1 = l1, p2 = l2, c1 = head, c2 = head.next;

        while (c1.next != null && c2.next != null) {
            p1.next = c1;
            p2.next = c2;

            p1 = p1.next;
            p2 = p2.next;

            if (c2 != null)
                c1 = c2.next;
            if (c1 != null)
                c2 = c1.next;
        }

        p1.next = null;
        l2.next = reverse(l2.next);
        p1.next = l2.next;

    }

    public static void unfold2(ListNode head) {
        if (head == null || head.next == null)
            return;

        ListNode l1 = new ListNode(-1);
        ListNode l2 = new ListNode(-1);
        ListNode p1 = l1, p2 = l2;

        p1.next = head;
        p2.next = head.next;

        p1 = p1.next;
        p2 = p2.next;

        while (p2 != null && p2.next != null) {
            ListNode f = p2.next;

            p1 = f;
            p2 = f.next;

            p1 = p1.next;
            p2 = p2.next;
        }

        p1.next = null;
        p2 = reverse(l2.next);
        p1.next = p2;
    }

    public static ListNode MergeTwoSortedLinkedList(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 != null ? l1 : l2;
        }
        ListNode ans = new ListNode(-1);
        ListNode prev = ans;
        ListNode c1 = l1;
        ListNode c2 = l2;
        while (c1 != null && c2 != null) {
            if (c1.val < c2.val) {
                prev.next = c1;
                c1 = c1.next;
            } else {
                prev.next = c2;
                c2 = c2.next;
            }
            prev = prev.next;
        }
        prev.next = c1 != null ? c1 : c2;

        return ans.next;
    }

    public static ListNode mergeKLists1(ListNode[] lists) { // O(NK) -->> K=lists.length,N=K*Lamda(avg length of all the
                                                            // k lists)
        if (lists.length == 0)
            return null;
        ListNode ans = new ListNode(-1);
        // ListNode ans =null;
        for (int i = 0; i < lists.length; i++) {
            ans = MergeTwoSortedLinkedList(ans, lists[i]);
        }

        return ans.next;
        // return ans;
    }

    public static ListNode mergeKListsHelper(ListNode[] lists, int si, int ei) {
        if (si == ei) {
            return lists[si];
        }
        int mid = (si + ei) / 2;
        ListNode LeftList = mergeKListsHelper(lists, si, mid);
        ListNode RightList = mergeKListsHelper(lists, mid + ei, ei);

        return MergeTwoSortedLinkedList(LeftList, RightList);
    }

    public static ListNode mergeKLists2(ListNode[] lists) { // T : O(N(LogK)) , S : O(logK)
        if (lists.length == 0)
            return null;
        return mergeKListsHelper(lists, 0, lists.length);
    }

    public static ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode mid = midNode(head);
        ListNode nhead = mid.next;
        mid.next = null;

        ListNode l = mergeSort(head);
        ListNode r = mergeSort(nhead);

        return MergeTwoSortedLinkedList(l, r);

    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n <= 0) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;

        while (n-- > 0) {
            fast = fast.next;
            if (fast == null && n > 0) {
                return head;
            }
        }

        if (fast == null) {
            ListNode remove = slow;
            head = remove.next;
            remove.next = null;
            return head;

        }

        while (fast.next != null) {
            // prev = slow ;
            slow = slow.next;
            fast = fast.next;
        }
        ListNode remove = slow.next;
        slow.next = remove.next;
        remove.prev = slow;
        remove.next =remove.prev =  null;

        return head;
    }

    public static ListNode segregateEvenOdd(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode even = new ListNode(-1);
        ListNode odd = new ListNode(-1);

        ListNode p1 = even, p2 = odd;

        ListNode curr = head;

        while (curr != null) {
            if (curr.val % 2 == 0) {
                p1.next = curr;
                p1 = curr;
            } else {
                p2.next = curr;
                p2 = curr;
            }
            curr = curr.next;
        }

        p1.next = odd.next;
        p2.next = null;
        head = even.next;
        even.next = odd.next = null; // optional
        return head;
    }

    public static ListNode segregate01(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode zero = new ListNode(-1);
        ListNode one = new ListNode(-1);

        ListNode p1 = zero, p2 = one;

        ListNode curr = head;

        while (curr != null) {
            if (curr.val == 0) {
                p1.next = curr;
                p1 = curr;
            } else {
                p2.next = curr;
                p2 = curr;
            }
            curr = curr.next;
        }

        p1.next = one.next;
        p2.next = null;
        head = zero.next;
        zero.next = one.next = null; // optional
        return head;
    }

    public static ListNode segregateOnLastIndex(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode last = head;
        ListNode curr = head;

        while (last.next != null) {
            last = last.next;
        }

        ListNode ans = new ListNode(last.val);
        ListNode p1 = ans;

        while (curr.next != null) {
            if (last.val < curr.val) {
                p1.next = curr;
                p1 = p1.next;

            }
            curr = curr.next;
        }
        p1.next = null;

        return ans;

    }

    public static ListNode segregatePivotNode(ListNode head, int pivotIdx) {
        if (head == null || head.next == null)
            return head;
        ListNode small = new ListNode(-1);
        ListNode larger = new ListNode(-1);
        ListNode pivotNode = head;
        while (pivotIdx-- > 0) {
            pivotNode = pivotNode.next;
        }

        ListNode sp = small, lp = larger;
        ListNode curr = head;
        while (curr != null) {
            if (curr == pivotNode) {
                curr = curr.next;
                continue;
            } else if (curr.val <= pivotNode.val) {
                sp.next = curr;
                sp = sp.next;
            } else {
                lp.next = curr;
                lp = lp.next;
            }
            curr = curr.next;
        }
        sp.next = pivotNode;
        pivotNode.next = larger.next;
        lp.next = null;
        head = small.next; // head change
        return pivotNode;

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 != null ? l1 : l2;
        }
        ListNode head = reverse(l1);
        ListNode nhead = reverse(l2);
        ListNode dummy = new ListNode(-1);
        ListNode ans = dummy;

        int carry = 0;
        while (head != null || nhead != null) {
            int x = 0, y = 0;
            if (head != null) {
                x = head.val;
                head = head.next;
            }
            if (nhead != null) {
                y = nhead.val;
                nhead = nhead.next;
            }
            int sum = x + y + carry;
            int r = sum % 10;
            carry = sum / 10;
            ans.next = new ListNode(r);
            ans = ans.next;

        }
        if (carry > 0) {
            ans.next = new ListNode(carry);
        }
        ans = dummy.next;
        dummy.next = null;
        ans = reverse(ans);

        return ans;

    }

    public static ListNode SubtractTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 != null ? l1 : l2;
        }
        ListNode head = reverse(l1);
        ListNode nhead = reverse(l2);
        ListNode dummy = new ListNode(-1);
        ListNode ans = dummy;

        int borrow = 0;
        int diff = 0;
        while (head != null || nhead != null) {
            int x = 0, y = 0;
            if (head != null) {
                x = head.val;
                head = head.next;
            }
            if (nhead != null) {
                y = nhead.val;
                nhead = nhead.next;
            }

            if (x - y - borrow < 0) {
                diff = x + 10 - y - borrow;
                borrow = 1;
            } else {
                diff = x - y - borrow;
                borrow = 0;
            }

            ans.next = new ListNode(diff);
            ans = ans.next;

        }
        ans = dummy.next;
        dummy.next = null;
        ans = reverse(ans);
        while (ans != null && ans.val == 0) {
            ans = ans.next;
        }
        return ans;

    }

    public static boolean isCyclePresentInLL(ListNode head) {
        if (head == null || head.next == null)
            return false;

        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

    public static ListNode cycleNode(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }

        if (fast != slow)
            return null;

        ListNode cycle = fast;
        ListNode start = head;

        while (cycle != start) {
            cycle = cycle.next;
            start = start.next;
        }

        return start;
    }

    //imp
    public static ListNode cycleNode2(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }

        if (fast != slow)
            return null;

        ListNode cyclenode = fast;
        slow = head;

        int a = 0, b = 0, c = 0, bc = 0, ndash = 0, n = 0; // a= tail ,b = cylcenode.next to meetingpoint , c
                                                           // =meetingpoint.next to cyclenode , n=cycle befor meeting ,
                                                           // ndash = cycle after meeting , bc = total nodes in
                                                           // cycle(b+c)

        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
            if (fast == cyclenode)
                ndash++;
            a++;
        }

        fast = cyclenode;
        fast = fast.next;
        bc = 1;
        while (fast != cyclenode) {
            fast = fast.next;
            bc++;
        }

        n = ndash + 1;
        c = a - (bc * ndash);
        b = bc - c;

        return cyclenode;
    }

    public static ListNode IntersectionNodeInTwoLLFloyad(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode fast = headA;
        while (fast.next != null) {
            fast = fast.next;
        }

        fast.next = headB;
        ListNode ans = cycleNode(headA);
        return ans;
    }

    public static int length(ListNode head) {
        if (head == null)
            return 0;

        ListNode curr = head;
        int len = 0;
        while (curr != null) {
            curr = curr.next;
            len++;
        }
        return len;
    }

    public static ListNode IntersectionNodeInTwoLLDiff(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        int lenA = length(headA);
        int lenB = length(headB);

        ListNode biggestNode = lenA > lenB ? headA : headB;
        ListNode smallestNode = lenA > lenB ? headB : headA;

        int diff = Math.abs(lenA - lenB);

        while (diff-- > 0) {
            biggestNode = biggestNode.next;
        }

        while (biggestNode != smallestNode) {
            biggestNode = biggestNode.next;
            smallestNode = smallestNode.next;
        }
        return smallestNode;
    }

    static ListNode th = null, tt = null;

    public static void addFirst(ListNode node) {
        if (th == null)
            th = tt = node;
        else {
            node.next = th;
            th = node;
        }
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head.next == null || k == 1)
            return head;

        int len = length(head);

        ListNode curr = head, ph = null, pt = null;

        while (curr != null && len >= k) {
            int itr = k;
            while (itr-- > 0) {
                ListNode forw = curr.next;
                curr.next = null;
                addFirst(curr);
                curr = forw;
            }

            if (ph == null) {
                ph = th;
                pt = tt;
            } else {
                pt.next = th;
                pt = tt;
            }

            th = null;
            tt = null;
            len -= k;
        }

        pt.next = curr;
        return ph;
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || head.next == null)
            return head;
        if (left == right)
            return head;
        ListNode curr = head;
        ListNode prev = null;
        int itr = 1;
        while (itr <= right) {
            if (itr >= left && itr <= right) {
                ListNode forw = curr.next;
                curr.next = null;
                addFirst(curr);
                curr = forw;
            } else {
                prev = curr;
                curr = curr.next;
            }
            itr++;
        }
        if (left == 1) {
            head = th;
        } else {
            prev.next = th;
        }
        tt.next = curr;

        return head;
    }

    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode ans = new ListNode(-1);
        ListNode dummy = ans;

        int prev = -101;

        ListNode curr = head;
        while (curr != null) {
            if (prev != curr.val) {
                dummy.next = curr;
                dummy = dummy.next;
                prev = curr.val;
            }
            curr = curr.next;
        }
        dummy.next = null;

        return ans.next;

    }

    public static ListNode deleteDuplicatesAll(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode dummy = new ListNode(-1);
        ListNode ans = dummy;
        ListNode prev = head;
        ListNode curr = head.next;
        boolean flag = true;
        while (curr != null) {
            if (prev.val != curr.val) {
                if (flag) {
                    ans.next = prev;
                    ans = ans.next;
                }
                prev = curr;
                flag = true;
            } else {
                flag = false;
            }
            curr = curr.next;
        }
        ans.next = null;
        if (flag) {
            ans.next = prev;
        }
        return dummy.next;
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

    public static void main(String[] args) {
        /*
         * The constructed linked list is: 10->12->11->11->12->11->10
         */
        ListNode start = new ListNode(10);
        start.next = new ListNode(12);
        start.next.next = new ListNode(11);
        start.next.next.next = new ListNode(11);
        start.next.next.next.next = new ListNode(12);
        start.next.next.next.next.next = new ListNode(11);
        start.next.next.next.next.next.next = new ListNode(10);

        ListNode head = midNode(start);
        printList(head);

    }
}