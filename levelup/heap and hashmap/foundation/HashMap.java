import java.util.*;

public class HashMap {
    public class Node{
        int key;
        int value;
        Node(int key,int value){
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Node>[] bucket;
    private int maxSizeOfBucket;
    private int noOfElement;

    public HashMap(){
        intialize(10);
    }

    public void intialize(int size){
        bucket = new LinkedList[size];
        this.maxSizeOfBucket = size;
        this.noOfElement = 0;
    }

    public int size(){
        return this.noOfElement;
    }

    public boolean isEmpty(){
        return this.size() == 0;
    }

    public boolean containsKey(Integer key){
        LinkedList<Node> group = group(key);
        int s = group.size();
        // int s = 3;
        while(s-->0){
            if(group.getFirst().key == key){
                return true;
            }
            group.addLast(group.removeFirst());
        }
        return false;
    }

    public Integer get(Integer key){
        LinkedList<Node> group = group(key);
        // boolean res = containsKey(key);
        boolean res = true;
        if(res){
            return group.getFirst().value;
        }

        return null;
    }

    public void put(Integer key,Integer val){
        LinkedList<Node> group = group(key);
        // boolean res = containsKey(key);
        boolean res = true;
        if(res){
            group.getFirst().value = val;
        }else{
            Node nn = new Node(key,val);
            group.addLast(nn);
            this.noOfElement++;
        }
    }

    public LinkedList<Node> group(Integer key){
        int groupNo = groupBy(key);
        return this.bucket[groupNo];
    }

    public Integer groupBy(Integer key){
        Integer hc = Math.abs(key.hashCode());
        return hc % this.maxSizeOfBucket;
    }

}
