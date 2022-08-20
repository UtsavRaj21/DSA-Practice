public class practice {
    
    // public static class LinkedList{
    //     public class Node{
    //         int data = 0;
    //         Node next = null;

    //         Node(int data){
    //             this.data = data;
    //         }
    //     }

    //     private Node head = null;
    //     private Node tail = null;
    //     public int sizeOfLL = 0;

    //     public boolean isEmpty(){
    //         return this.sizeOfLL == 0 ;
    //     }

    //     public int size(){
    //         return this.sizeOfLL;
    //     }

    //     public void addFirst(int data){
    //         if(this.head == null || this.tail == null){
    //             this.head = this.tail = new Node(data);
    //         }else{
    //             Node nNode = new Node(data);
    //             nNode.next = this.head;
    //             this.head = nNode;
    //         }
    //         this.sizeOfLL++;
    //     }

    //     public void addLast(int data){
    //         if(this.head == null || this.tail == null){
    //             this.head = this.tail = new Node(data);
    //         }else{
    //             Node nNode = new Node(data);
    //             this.tail.next = nNode;
    //             this.tail = nNode;
    //         }
    //         this.sizeOfLL++;
    //     }

    //     public int getFirst(){
    //         return this.head.data;
    //     }

    //     public int getLast(){
    //         return this.tail.data;
    //     }

    //     public int removeFirst(){
    //         int rVal = head.data;
    //         if(this.sizeOfLL == 1){
    //             this.head = this.tail = null;
                
    //         }else{
    //             Node forw = this.head.next;
    //             this.head.next = null;
    //             this.head = forw;
    //         }
    //         this.sizeOfLL--;
    //         return rVal;
    //     }
    // }

    public static class LinkedList{
        public class Node{
            int data=0;
            Node next = null;
            Node(int data){
                this.data = data;
            }
        }

        private Node head = null;
        private Node tail = null;
        private int sizeOfLL = 0;

        public int size(){
            return this.sizeOfLL;
        }

        public boolean isEmpty(){
            return this.sizeOfLL == 0;
        }

        public void addFirst(int val){
            if(head==null && tail == null){
                this.head = this.tail = new Node(val);
            }else{
                Node node = new Node(val);
                node.next = this.head;
                this.head = node;
            }
            this.sizeOfLL++;
        }
        public void addLast(int data){
            if(head==null && tail == null){
                this.head = this.tail = new Node(data);
            }else{
                Node node = new Node(data);
                this.tail.next = node;
                this.tail = node;
            }
            this.sizeOfLL++;
        }

        public Node removeFirst(){
            Node rNode = this.head;
            if(this.sizeOfLL==1){
                this.head = this.tail = null;
            }else{
                Node forw = this.head.next;
                this.head.next = null;
                this.head = forw;
            }
            this.sizeOfLL--;
            return rNode;
        }

        public Node getFirst(){
            return this.head;
        }
    }

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.addFirst(3);
        ll.addFirst(2);
        ll.addFirst(1);
        ll.addLast(10);
        ll.addLast(5);

        System.out.println(ll.size());
        System.out.println(ll.isEmpty());
        while(ll.size()!=0){
            System.out.println(ll.removeFirst().data);
        }
        System.out.println(ll.isEmpty());
    }

}
