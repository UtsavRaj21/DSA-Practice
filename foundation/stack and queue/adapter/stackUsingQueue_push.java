import java.util.Queue;
import java.util.LinkedList;

public class stackUsingQueue_push {
    Queue<Integer> que = new LinkedList<>();
    Queue<Integer> temp = new LinkedList<>();

    public int size() {
        return que.size();
    }

    public boolean isEmpty() {
        return que.isEmpty();
    }

    // O(1)
    public void push(int data) {
        que.add(data);
    }

    // O(1)
    public int top() {
        while (que.size()>1) {
            temp.add(que.remove());
        }
        int peekVal=que.remove();
        temp.add(peekVal);

        while(temp.size()!=0){
            que.add(temp.remove());
        }
        return peekVal;
    }

    // O(n)
    public int pop() {
        while (que.size()>1) {
            temp.add(que.remove());
        }
        int peekVal=que.remove();

        while(temp.size()!=0){
            que.add(temp.remove());
        }
        return peekVal;
    }
}
