import java.io.*;
import java.util.*;
import java.lang.*;

public class main {

   public static List<String> largestDebt(List<List<String>> debts){
       HashMap<String,Integer> map = new HashMap<>();
       for(List<String> list : debts){
            String p1 = list.get(0);
            String p2 = list.get(1);
            int price = Integer.parseInt(list.get(2));
            if(map.containsKey(p1)){
                int p = map.get(p1);
                map.put(p1,p-price);
            }else{
                map.put(p1,price*(-1));
            }

            if(map.containsKey(p2)){
                int pp = map.get(p2);
                map.put(p2,pp+price);
            }else{
                map.put(p2,price);
            }
       }

       int min = 0;
       for (String name : map.keySet())
        {
            int m= map.get(name);
            min = Math.min(min, m);
        }

        List<String> ans = new ArrayList<>();

        if(min == 0){
            ans.add("Nobody has debt");
        }else{
            for (String name : map.keySet())
            {
                int money= map.get(name);
                if(money == min){
                    ans.add(name);
                }
            }
        }

        return ans;
   }
   
    
    
    public static void main(String[] args){
       
    }
}