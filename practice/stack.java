import java.util.*;

public class stack {
    public static LinkedList<Integer> ll =new LinkedList<Integer>();

    public static int size(){
        return ll.size();
    }

    public static boolean isEmpty(){
        return ll.size() == 0;
    }

    public static void push(int data){
         ll.addFirst(data);
    }

    public static int peek(){
        return ll.getFirst();
    }

    public static int pop(){
        return ll.removeFirst();
    }

    public static void main(String[] args){
        ll.push(10);
        System.out.println(ll.size());
    }


}
