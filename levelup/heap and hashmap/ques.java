import java.util.*;

import javax.swing.text.AbstractDocument.LeafElement;

public class ques {

    public static int solve(int[] arr){
        HashSet<Integer> set = new HashSet<>();
        for(int ele : arr){
            set.add(ele);
        }
        int count = 0;
        for(int i = 0 ; i < arr.length-1; i++){
            for(int j = i+1 ; j < arr.length ;j++){
                int s = arr[i] + arr[j];
                if(set.contains(s)){
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] X = {5,10,15,-5,20};
        int c = solve(X);
        System.out.println(c);
    }
}
