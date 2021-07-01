public class StatckQueueUsingLL{
    public static class linkedlist{
        public class Node{
            int data = 0;
            Node next = null;
            Node(int data){
                this.data = data;
            }
        }

        private  Node head = null;
        private  Node tail = null;
        private int sizeOfLL = 0;

        public  int size(){
            return sizeOfLL;
        }
        //get

        public  int getFirst(){
            if(sizeOfLL==0){
                return -1;
            }
            return this.head.data;
        }

        //add
        public void addFirstNode(Node node){
            if(sizeOfLL==0){
                this.head=this.tail=node;
            }else{
                node.next= this.head;
                this.head=node;
            }
            sizeOfLL++;
        }


        public void addFirst(int data){
            Node node=new Node(data);
            addFirstNode(node);
        }

        public void addLastNode(Node node){
            if(sizeOfLL==0){
                this.head=this.tail=node;
            }else{
                this.tail.next=node;
                this.tail = node;
            }
            sizeOfLL++;
        }


        public void addLast(int data){
            Node node=new Node(data);
            addLastNode(node);
        }

        //remove 

        private Node removeFirstNode(){
            Node node = this.head;
            if(sizeOfLL==1){
                this.head = this.tail = null;
            }else{
                Node forw = node.next;
                node.next = null;
                this.head = forw;
            }
            sizeOfLL--;
            return node;

        }

        public int removeFirst(){
            if(sizeOfLL==0){
                return -1;
            }

            Node node = removeFirstNode();
            return node.data;
        }

        
    }

    public static void main(String[] args) {
        linkedlist ll = new linkedlist();
        ll.addFirst(10);
        ll.addFirst(20);
        ll.addFirst(40);
        ll.addLast(60);
        ll.addLast(70);
        ll.addLast(80);

        System.out.println(ll.size());
        System.out.println(ll.getFirst());

        while(ll.size()>0){
            System.out.print(ll.removeFirst()+"->");
        }

    }
}