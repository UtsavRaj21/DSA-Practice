import java.util.*;
import java.io.*;

public class Test2 {


    // public static boolean reverse(String str){
    //     if((str.length()/2) == idx){
    //         if(str.length() % 2 != 0){
    //             idx++;
    //         }
    //         return true;
    //     }

    //     char ch = str.charAt(idx++);
    //     boolean res = reverse(str);
    //     if(!res){
    //         return false;
    //     }
    //     char reCh = str.charAt(idx++);

    //     if(ch >= 'A' && ch <= 'Z'){
    //         int j = ch - 'A';
    //         ch = (char)('a' + j);
    //     }
    //     if(reCh >= 'A' && reCh <= 'Z'){
    //         int j = reCh - 'A';
    //         reCh = (char)('a' + j);
    //     }

    //     return ch == reCh ?true : false;
    // }

    // public static Character solve(char ch2){
    //     if(ch2 >= 'A' && ch2 <= 'Z'){
    //         int j = ch2 - 'A';
    //         ch2 = (char)('a' + j);
    //     }
    //     return ch2;
    // }

    // public static boolean reverse2(String str,int i,int j){
    //     if(i >=j){
    //         return true;
    //     }

    //     char ch1 = str.charAt(i);
    //     char ch2 = str.charAt(j);
    //     ch1 = solve(ch1);
    //     ch2 = solve(ch2);
    //     if(ch1 != ch2){
    //         return false;
    //     }
    //     return reverse2(str,++i,--j);
        
    // }

    public static String repeat(String str,int idx){
        if(str.length()-1 == idx){
            String base ="";
            base = base + str.charAt(idx);
            return base;
        }

        char ch = str.charAt(idx++);
        String res = repeat(str, idx);
        if(res.charAt(0) == ch){
            res = ch+"*"+res;
        }else{
            res = ch + res;
        }

        return res;
    }
    public static void main(String[] args) throws Exception {
        String str = "abbcddt";
        //int idx2 = str.length()-1;
        // System.out.println(reverse(str));
        System.out.println(repeat(str,0));
    }
}
