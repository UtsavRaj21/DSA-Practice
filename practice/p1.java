import java.util.*;

public class p1 {

    public static String find(int key , String str){
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < str.length();i++){
            char ch = str.charAt(i);
            if(ch >= 'a' && ch <= 'z'){
                char c = (char)(ch + key);
                sb.append(c);
            }else if(ch >= 'A' && ch <= 'Z'){
                char c = (char)(ch  + key);
                sb.append(c);
            }else{
                char c = (char)(ch  + key);
                sb.append(c);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
     
        int k = 3;
        String str = "as3gAsd";
        String ans = find(k,str);
        System.out.println(ans);

       
    }
}
