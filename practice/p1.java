import java.util.*;

public class p1 {

    public static String solve(String num){
        StringBuilder sb = new StringBuilder();
        int c=1;
        char prev = num.charAt(0);
        for(int i = 1; i<num.length();i++){
            char ch = num.charAt(i);
            if(ch==prev){
                c++;
            }else{
                sb.append(c);
                sb.append(prev);
                prev = ch;
                c=1;
            }
        }

        if(c>0){
            sb.append(c);
            sb.append(prev);
        }

        return sb.toString();
    }

    static String ShoutOutLoud(int n){
        String s=Integer.toString(1);
        String ans = "1";
        for(int i = 1;i<n;i++){
           ans = solve(s);
          s=ans;
        }
        return ans;
    }

    public static void main(String[] args) {
       int n = 4;
      
        ShoutOutLoud(n);
       
    }
}
