import java.util.*;
import java.io.*;

public class Test2 {
    public static void main(String[] args) throws Exception {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String in[] = read.readLine().split(" ");
        int n = Integer.parseInt(in[0]);
        int d = Integer.parseInt(in[1]);
         ArrayList<Integer> al = new ArrayList<>();
        int count = 0;
        HashMap<String,Integer> map = new HashMap<>();
        while (n-->0) {
            String inp[] = read.readLine().split(" ");
            char ch1 = inp[0].charAt(0);
            char ch2 = inp[1].charAt(0);
            int num = Integer.parseInt(inp[2]);

            StringBuilder sb = new StringBuilder();
            sb.append(ch1);
            sb.append(ch2);

            if(map.containsKey(sb.toString())){
                int val = map.get(sb.toString());
                if(Math.abs(val-num)<=d){
                    count++;
                }
                map.remove(sb.toString());
            }else{
                map.put(sb.reverse().toString(), num);
            }
        }

        System.out.println(count);
    }
}
