import java.util.*;

public class stack {
    private static LinkedList<Integer> ll = new LinkedList<Integer>();

    public static int size() {
        return ll.size();
    }

    public static boolean isEmpty() {
        return ll.size() == 0;
    }

    public  static void push(int data) {
        ll.addFirst(data);
    }

    public static int top() {
        return ll.getFirst();
    }

    public static int pop() {
        return ll.removeFirst();
    }

    public static void main(String[] args){
        ll.push(10);
        ll.push(20);
        ll.push(30);
        ll.push(40);
        while(ll.size()>0){
            System.out.println(ll.pop());

        }
        System.out.println(ll.size());

    }
}