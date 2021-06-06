public class linkedlist{
    private class  Node{
        int data=0;
        Node next=null;
        Node(int data ){
            this.data=node;
        }
    }

    private Node head=null;
    private Node tail=null;
    private int sizeoffLL=0;

    public boolean isEmpty(){
        return this.sizeoffLL==0;
    }

    public int size(){
        return this.sizeoffLL;
    }

    public void display(){
        Node curr=this.head;
        while(curr!=null){
            System.out.println(curr.data+ "->");
            curr=curr.next;
        }
        System.out.println();
    }
    //Exception--------------------------
    private void IndexootofboundExclusiveException(int idx) throws Exception{
        if(idx<0 || idx>this.sizeoffLL){
                throws new Exception("Out Of Bound");
        }
    }

    private void IndexootofboundInclusiveException(int idx) throws Exception{
        if(idx<0 || idx>=this.sizeoffLL){
                throws new Exception("Out Of Bound");
        }
    }

    private void EmptyException() throws Exception{
        if(this.sizeoffLL==0){
            throws new Exception("Empty linkes list");
        }
    }

    //addddddddddd---------------------

    public void addFirst(int data){
        Node node=new Node(data);
        addFirstNode(data);
    }

    private void addFirstNode(Node node){
        if(this.sizeoffLL==0){
            this.head=node;
            this.tail=node;
        }
        else{
            node.next=this.head;
             this.head=node;                                                                                                                                                                                                            
        }
        this.sizeoffLL++;
    }

    public void addLast(int data){
        Node node=new Node(data);
        addLastNode(data);
    }

    private void addLastNode(Node node){
        if(this.sizeoffLL==0){
            this.head=node;
            this.tail=node;
        }
        else{
           this.tail.next=node;
           this.tail=node;                                                                                                                                                                                                     
        }
        this.sizeoffLL++;
    }

    public void addAt(int idx,int data)throws Exception{
        IndexootofboundExclusiveException();
        Node node = new Node(data);
        addAtNode(idx,data);
    }

    private void addAtNode(int id,Node node){
        if(idx==0) return addFirstNode(node);
        else if(idx==this.sizeoffLL) return addLastNode(node);
        else{
            node prev=getNodeAt(idx-1);
            node forw=prev.next;

            prev.next=node;
            node.next=forw;
            this.sizeoffLL++;
        }

    }

    //get-------------------------------------
    public int getFirst() throws Exception{
        EmptyException();
        return this.head.data;
    }

    public int getLast() throws Exception{
        EmptyException();
        return this.tail.data;
    }

    public int getAt(int idx) throws Exception{
        IndexOutOfBoundSizeInclusiveException();
        Node node=getNodeAt(idx);
        return node.data;
    }

    private Node getNodeAt(int idx){
        Node curr=this.head;
        while(idx-->0){
            curr=curr.next;
        }
        return curr;
    }

    //Remove-------------------------------
    public int removeFirst(){
        EmptyException();
        Node node=removeFirstNode();
        return node.data;
    }

    private Node removeFirstNode(){
        Node removeNode=this.head;
        if(this.sizeoffLL==1){
            this.head=null;
            this.tail=null;
        }
        else{
            Node forw=this.head.next;
            removeNode=null;
            this.head=forw;
        }
        this.sizeoffLL--;
        return removeNode;
    }

    public int removeLast(){
        EmptyException();
        Node node=removeLastNode();
        return node.data;
    }

    private Node removeLastNode(){
        Node removeNode=this.tail;
        if(this.sizeoffLL==1){
            this.head=null;
            this.tail=null;
        }
        else{
            Node forw=getNodeAt(this.sizeoffLL-2);
            this.tail=forw;
            this.tail.next=null;
        }
        this.sizeoffLL--;
        return removeNode;
    }

    public int removeAt(int idx)throws Exception{
        EmptyException();
        IndexootofboundInclusiveException(idx);
        Node node=removeNodeAt(int idx);
        return node.data;
    }

    private Node removeNodeAt(int idx){ 
        if(idx==0) return removeFirstNode;
        else if(idx==this.sizeoffLL-1) return removeLastNode;
        else{
            Node prev=getNodeAt(idx-1);
            Node curr=prev.next;
            Node forw=curr.next;

            prev.next=forw;
            curr.next=null;

            this.sizeoffLL--;
        }
    }



}