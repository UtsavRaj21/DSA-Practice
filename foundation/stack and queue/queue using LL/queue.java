import java.util.*;

public class queue {
    private static LinkedList<Integer> ll = new LinkedList<Integer>();

    public static int size() {
        return ll.size();
    }

    public static boolean isEmpty() {
        return ll.size() == 0;
    }

    public static void add(int data) {
        ll.addLast(data);
    }

    public static int front() {
        return ll.getFirst();
    }

    public static int remove() {
        return ll.removeFirst();
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