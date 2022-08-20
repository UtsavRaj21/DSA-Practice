public class client1 {
    public static void main(String[] args) {
        stackUsingQueue_push st = new stackUsingQueue_push();
        for (int i = 1; i <= 10; i++) {
            st.push(i * 10);
        }

        while (st.size() != 0) {
            System.out.println(st.pop());
        }
    }
}
