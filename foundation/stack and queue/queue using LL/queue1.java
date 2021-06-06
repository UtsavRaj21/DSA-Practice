
public class queue1 {
    private static LinkedList<Integer> ll = new LinkedList<Integer>();
    private class Node {
        int data = 0;
        Node next = null;

        Node(int data) {
            this.data = data;
        }
    }

    private Node head = null;
    private Node tail = null;
    private int elementCount = 0;

    private void addLast(int data) {
        Node node = new Node(data);
        if (this.elementCount == 0) {
            this.head = this.tail = node;
        } else {
            this.tail.next = node;
            this.tail = node;
        }

        this.elementCount++;
    }

    private int removeFirst() {
        Node rn = this.head;
        if (this.elementCount == 1) {
            this.head = this.tail = null;
        } else {
            this.head = rn.next;
        }

        rn.next = null;
        this.elementCount--;

        return rn.data;
    }

    public int size() {
        return elementCount;
    }

    public boolean isEmpty() {
        return elementCount == 0;
    }

    public void add(int data) {
        addLast(data);
    }

    public int front() {
        return this.head.data;
    }

    public int remove() {
        return removeFirst();
    }

    public static void main(String[] args){
        ll.add(10);
        ll.add(20);
        ll.add(30);
        ll.add(40);
        while(ll.size()>0){
            System.out.println(ll.remove());
        }
        
        System.out.println(ll.size());

    }
}