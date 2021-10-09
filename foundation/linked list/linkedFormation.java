public class linkedFormation {
    

      public static class LinkedList {
        public class Node{
          int data = 0 ;
          Node next= null;
          Node(int data){
            this.data = data;
          }
        }

        private Node head = null;
        private Node tail = null;
        private int sizeOfLL = 0;

        //size
        public int size(){
          return sizeOfLL;
        }

        //add
        private void addFirstHelper(Node node){
          if(sizeOfLL == 0){
            this.head = this.tail = node;
          }else{
            node.next = this.head;
            this.head = node; 
          }
          this.sizeOfLL++;
        }
        public void addFirst(int data){
          Node node = new Node(data);
          addFirstHelper(node);
        } 

        private void addLastHelper(Node node){
          if(sizeOfLL == 0){
            this.head = this.tail = node;
          }else{
            this.tail.next = node; 
            this.tail = node;
          }
          this.sizeOfLL++;
        }
        public void addLast(int data){
          Node node = new Node(data);
          addLastHelper(node);
        } 
        
        //remove
        private int removeFirstHelper(){
         Node rnode = this.head;
         if(this.sizeOfLL == 1){
           this.head = this.tail = null;
         }else{
           Node forw = this.head.next;
           this.head.next = null;
           this.head = forw;
         }

         return rnode.data;
        }
        public int removeFirst(){
         if(this.sizeOfLL == 0) {
           return -1;
         }
          return removeFirstHelper();
        }
     
        public void display() {
            for (Node temp = head; temp != null; temp = temp.next) {
              System.out.print(temp.data + " ");
            }
            System.out.println();
          }
        }

        

        public static void main(String[] args) {
            LinkedList ll  = new LinkedList();
            ll.addFirst(1);
            ll.addFirst(2);
            ll.addFirst(3);
            ll.addFirst(4);
            ll.addFirst(5);
            ll.addLast(6);
            System.out.println(ll.size());

            System.out.println(ll.removeFirst());

            ll.display();
        }
}
