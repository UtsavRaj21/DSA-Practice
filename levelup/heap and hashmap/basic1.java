import java.util.HashMap;

import java.util.*;
public class basic1{
   
   //Number Of Employees Under Every Manager
   public static int getCount(HashMap<String,HashSet<String>> tree,String man ,  HashMap<String,Number> set){
      int count = 0;
      if(!tree.containsKey(man)){
         set.put(man, 0);
         return 1;
      }
      for(String manager : tree.get(man)){
         count+=getCount(tree,manager,set);
      }
      set.put(man, count);
      return count+1;
   }
   
   public static void printEmployees(String[][] str){
      HashMap<String,HashSet<String>> tree = new HashMap<>();
      String ceo="";

      for(String[] relation : str){
         String one = relation[0];
         String two = relation[1];
         if(one.equals(two)){
            ceo = one;
            continue;
         }

         if(tree.containsKey(two)){
            tree.get(two).add(one);
         }else{
            HashSet<String> set = new HashSet<>();
            set.add(one);
            tree.put(two, set);
         }
      }

      HashMap<String,Number> set = new HashMap<>();

      getCount(tree,ceo,set);

      for(String man : set.keySet()){
         System.out.println(man+" "+set.get(man));
      }
   }

   //Find Itinerary From Tickets
   public static void tickets( HashMap<String,String> map){
      HashMap<String,Boolean> set = new HashMap<>();

      for(String city1 : map.keySet()){
        String city2 = map.get(city1);
        set.put(city2, false);
        if(!map.containsKey(city1)){
           set.put(city1, true);
        }
      }

      String start = "";
      for(String city : set.keySet()){
         if(set.get(city) == true){
            start = city;
            break;
         }
      }
      String res = "";
      while(map.containsKey(start)){
         res = res + start + " -> ";
         start = map.get(start);
      }
      res = res + start + ".";
      System.out.println(res);
   }


   //Count Distinct Elements In Every Window Of Size K
   public static ArrayList<Integer> solution(int[] arr, int k) {
		HashMap<Integer,Integer> map = new HashMap<>();

      for(int i =0 ;i < k-1;i++){
         int fp = map.getOrDefault(arr[i], 0);
         map.put(arr[i], fp+1);
      }

      ArrayList<Integer> res = new ArrayList<>();
      int j=0;
      for(int i = k-1 ; i < arr.length ; i++,j++){
         int fp = map.getOrDefault(arr[i], 0);
         map.put(arr[i], fp+1);
         res.add(map.size());

         if(map.get(arr[j]) == 1){
            map.remove(arr[j]);
         }else{
            map.put(arr[j], map.get(arr[j])-1);
         }
      }


		return res;
	}

   //Check If An Array Can Be Divided Into Pairs Whose Sum Is Divisible By K
   public static void solution1(int[] arr, int k){
		//write your code here
      HashSet<Integer> set = new HashSet<>();

      for(int num : arr){
         int rem = num % k;
         if(rem==0){
            if(set.contains(0)){
               set.remove(0);
            }else{
               set.add(0);
            }
         }
         else if(set.contains(k-rem)){
            set.remove(k-rem);
         }else{
            set.add(rem);
         }
      }

      if(set.size() == 0){
         System.out.println("true");
      }else{
         System.out.println("false");
      }

	}
   
   //Largest Subarray With Zero Sum
   public static int solution(int[] arr) {
		// write your code here
      HashMap<Integer,Integer> map = new HashMap<>();
      map.put(0,-1);
      int sum =0;
      int len =0;
      for(int i = 0 ; i<arr.length;i++){
         sum+=arr[i];
         if(!map.containsKey(sum)){
            map.put(sum,i);
         }else{
            len = Math.max(len,i-map.get(sum));
         }
      }

		return len;
	}
   public static void main(String[] args) {
      
   }
}