import java.util.HashMap;

import javax.print.attribute.standard.PresentationDirection;

import java.util.*;

public class basic1 {

   // Number Of Employees Under Every Manager
   public static int getCount(HashMap<String, HashSet<String>> tree, String man, HashMap<String, Number> set) {
      int count = 0;
      if (!tree.containsKey(man)) {
         set.put(man, 0);
         return 1;
      }
      for (String manager : tree.get(man)) {
         count += getCount(tree, manager, set);
      }
      set.put(man, count);
      return count + 1;
   }

   public static void printEmployees(String[][] str) {
      HashMap<String, HashSet<String>> tree = new HashMap<>();
      String ceo = "";

      for (String[] relation : str) {
         String one = relation[0];
         String two = relation[1];
         if (one.equals(two)) {
            ceo = one;
            continue;
         }

         if (tree.containsKey(two)) {
            tree.get(two).add(one);
         } else {
            HashSet<String> set = new HashSet<>();
            set.add(one);
            tree.put(two, set);
         }
      }

      HashMap<String, Number> set = new HashMap<>();

      getCount(tree, ceo, set);

      for (String man : set.keySet()) {
         System.out.println(man + " " + set.get(man));
      }
   }

   // Find Itinerary From Tickets
   public static void tickets(HashMap<String, String> map) {
      HashMap<String, Boolean> set = new HashMap<>();

      for (String city1 : map.keySet()) {
         String city2 = map.get(city1);
         set.put(city2, false);
         if (!map.containsKey(city1)) {
            set.put(city1, true);
         }
      }

      String start = "";
      for (String city : set.keySet()) {
         if (set.get(city) == true) {
            start = city;
            break;
         }
      }
      String res = "";
      while (map.containsKey(start)) {
         res = res + start + " -> ";
         start = map.get(start);
      }
      res = res + start + ".";
      System.out.println(res);
   }

   // Count Distinct Elements In Every Window Of Size K
   public static ArrayList<Integer> solution(int[] arr, int k) {
      HashMap<Integer, Integer> map = new HashMap<>();

      for (int i = 0; i < k - 1; i++) {
         int fp = map.getOrDefault(arr[i], 0);
         map.put(arr[i], fp + 1);
      }

      ArrayList<Integer> res = new ArrayList<>();
      int j = 0;
      for (int i = k - 1; i < arr.length; i++, j++) {
         int fp = map.getOrDefault(arr[i], 0);
         map.put(arr[i], fp + 1);
         res.add(map.size());

         if (map.get(arr[j]) == 1) {
            map.remove(arr[j]);
         } else {
            map.put(arr[j], map.get(arr[j]) - 1);
         }
      }

      return res;
   }

   // Check If An Array Can Be Divided Into Pairs Whose Sum Is Divisible By K
   public static void solution1(int[] arr, int k) {
      // write your code here
      HashSet<Integer> set = new HashSet<>();

      for (int num : arr) {
         int rem = num % k;
         if (rem == 0) {
            if (set.contains(0)) {
               set.remove(0);
            } else {
               set.add(0);
            }
         } else if (set.contains(k - rem)) {
            set.remove(k - rem);
         } else {
            set.add(rem);
         }
      }

      if (set.size() == 0) {
         System.out.println("true");
      } else {
         System.out.println("false");
      }

   }

   // Largest Subarray With Zero Sum
   public static int solution1(int[] arr) {
      // write your code here
      HashMap<Integer, Integer> map = new HashMap<>();
      map.put(0, -1);
      int sum = 0;
      int len = 0;
      for (int i = 0; i < arr.length; i++) {
         sum += arr[i];
         if (!map.containsKey(sum)) {
            map.put(sum, i);
         } else {
            len = Math.max(len, i - map.get(sum));
         }
      }

      return len;
   }

   // Count Of All Subarrays With Zero Sum
   public static int solution2(int[] arr) {
      HashMap<Integer, Integer> map = new HashMap<>();
      map.put(0, 1);
      int count = 0;
      int prefixSum = 0;
      for (int ele : arr) {
         prefixSum += ele;
         if (map.containsKey(prefixSum)) {
            count += map.get(prefixSum);
            map.put(prefixSum, map.get(prefixSum) + 1);
         } else {
            map.put(prefixSum, 1);
         }
      }
      return count;
   }

   // Longest Subarray With Equal Number Of Zeroes And Ones
   public static int solution3(int[] arr) {
      HashMap<Integer, Integer> map = new HashMap<>();
      map.put(0, -1);
      int preSum = 0;
      int res = 0;
      for (int i = 0; i < arr.length; i++) {
         int ele = arr[i];
         if (ele == 0) {
            preSum += -1;
         } else {
            preSum += -1;
         }

         if (map.containsKey(preSum)) {
            res = Math.max(res, i - map.get(preSum));
         } else {
            map.put(preSum, i);
         }
      }
      return res;
   }

   // Count Of Subarrays With Equal Number Of Zeroes And Ones
   public static int solution4(int[] arr) {
      int[] nums = new int[arr.length];
      for (int i = 0; i < arr.length; i++) {
         int ele = arr[i];
         if (ele == 0) {
            nums[i] = -1;
         } else {
            nums[i] = 1;
         }
      }
      return solution2(nums);
   }

   // Maximum Size Subarray Sum Equals K
   public static int maxLenSubarray(int[] arr, int k) {
      HashMap<Integer, Integer> map = new HashMap<>();
      map.put(0, -1);
      int preSum = 0;
      int res = 0;
      for (int i = 0; i < arr.length; i++) {
         int ele = arr[i];
         preSum += ele;

         if (map.containsKey(preSum - k)) {
            res = Math.max(res, i - map.get(preSum - k));
         }
         if (!map.containsKey(preSum)) {
            map.put(preSum, i);
         }
      }

      return res;
   }

   //Count Of Subarrays Having Sum Equals To K
   public static int solution6(int[] arr,int k) {
      HashMap<Integer, Integer> map = new HashMap<>();
      map.put(0, 1);
      int count = 0;
      int prefixSum = 0;
      for (int ele : arr) {
         prefixSum += ele;
         if (map.containsKey(prefixSum - k)) {
            count += map.get(prefixSum - k);
           
         } 
         if(!map.containsKey(prefixSum)) {
            map.put(prefixSum, 1);
         }else{
            map.put(prefixSum, map.get(prefixSum) + 1);
         }
      }
      return count;
   }
   
   //Longest Subarray With Sum Divisible By K
   public static int LongestDivisbleByK(int[] arr, int k) {
      HashMap<Integer, Integer> map = new HashMap<>();
      map.put(0, -1);
      int sum = 0;
      int len = 0;
      for (int i = 0; i < arr.length; i++) {
         sum += arr[i];
         int rem = sum % k;
         if(rem < 0 ){
            rem +=k; 
         }
         if (!map.containsKey(rem)) {
            map.put(rem, i);
         } else {
            len = Math.max(len, i - map.get(rem));
         }
      }

      return len;
  }

  //count Subarray With Sum Divisible By K
  public static int CountLongestDivisbleByK(int[] arr, int k) {
   HashMap<Integer, Integer> map = new HashMap<>();
   map.put(0, 1);
   int sum = 0;
   int len = 0;
   for (int i = 0; i < arr.length; i++) {
      sum += arr[i];
      int rem = sum % k;
      if(rem < 0 ){
         rem +=k; 
      }
      if (!map.containsKey(rem)) {
         map.put(rem, 1);
      } else {
         len +=  map.get(rem);
         map.put(rem, map.get(rem)+1);
      }
   }

   return len;
}
   
  //Longest Subarray With Equal Number Of 0s 1s And 2s
   public static int LongestSubarray012(int[] arr) {
      HashMap<String,Integer> map = new HashMap<>();
      int zero =0;
      int one = 0;
      int two = 0;
      int len = 0;
      StringBuilder sb = new StringBuilder();
      sb.append(0);
      sb.append('#');
      sb.append(0);
      map.put(sb.toString(),-1);

      for(int i = 0 ; i < arr.length ;i++){
         int ele = arr[i];
         if(ele == 0){
            zero++;
         }else if(ele == 1){
            one++;
         }else{
            two++;
         }

         int c1 = one - zero;
         int c2 = two - one;
         StringBuilder sb1 = new StringBuilder();
         sb1.append(c1);
         sb1.append('#');
         sb1.append(c2);

         if(map.containsKey(sb1.toString())){
            len = Math.max(len, i - map.get(sb1.toString()));
         }else{
            map.put(sb1.toString(),i);
         }

      }

      return len;
   }
   public static void main(String[] args) {

   }
}