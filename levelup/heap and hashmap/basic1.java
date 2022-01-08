import java.util.HashMap;

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

   //Count Subarray With Equal Number Of 0s 1s And 2s
   public static int CountSubarray012(int[] arr) {
      HashMap<String,Integer> map = new HashMap<>();
      int zero =0;
      int one = 0;
      int two = 0;
      int count = 0;
      StringBuilder sb = new StringBuilder();
      sb.append(0);
      sb.append('#');
      sb.append(0);
      map.put(sb.toString(),1);

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
            count += map.get(sb1.toString());
            map.put(sb1.toString(),map.get(sb1.toString())+1);
         }else{
            map.put(sb1.toString(),1);
         }

      }

      return count;
   }
   
   //Task Completion
   public static void completeTask(int n, int m, int[] arr) {
      HashSet<Integer> set = new HashSet<>();
      for(int ele : arr){
         set.add(ele);
      }

      ArrayList<Integer> s1 = new ArrayList<>();
      ArrayList<Integer> s2 = new ArrayList<>();
      boolean flag = true;

      for(int i = 1 ; i <= n ; i++){
         if(set.contains(i)){
            continue;
         }

         if(flag){
            s1.add(i);
            flag = false;
         }else{
            s2.add(i);
            flag = true;
         }
      }
      for(int ele : s1){
         System.out.print(ele+" ");
      }
      System.out.println();
      for(int ele : s2){
         System.out.print(ele+" ");
      }
	}

   //Smallest Substring Of A String Containing All Characters Of Another String
   
   public static String smallestSubstring(String s1, String s2){
		HashMap<Character,Integer> fmap = new HashMap<>();
      for(int i = 0 ; i < s2.length() ; i++){
         char ch = s2.charAt(i);
         fmap.put(ch,fmap.getOrDefault(ch, 0) + 1);
      }

      int acq = -1;
      int rel = -1;
      int count =0;
      int require = s1.length();
      String ans ="";
      HashMap<Character,Integer> map = new HashMap<>();
      while(true){
         boolean acFlag = false;
         boolean reFlag = false;

         while(acq < s1.length() -1 && count < require){
            acq++;
            char c = s1.charAt(acq);
            map.put(c,map.getOrDefault(c,0)+1);
            
            if(map.get(c) <= fmap.getOrDefault(c, 0)){
               count++;
            }
            acFlag = true;
         }

         while(rel < acq && count == require){
            String tempAns = s1.substring(rel+1,acq+1);
            if(ans.length() == 0 || tempAns.length() < ans.length()){
               ans = tempAns;
            }

            rel++;
            char c = s1.charAt(rel);
            map.put(c, map.get(c)-1);
            if(map.getOrDefault(c,0) < fmap.getOrDefault(c, 0)){
               count--;
            }
            reFlag = true;
         }
         if(!acFlag && !reFlag){
            break;
         }
      }

      return ans;
	}

   //Smallest Substring Of A String Containing All Unique Characters Of Itself
   public static int smallestSubstringUniqueChar(String str){
		HashSet<Character> set = new HashSet<>();
      for(int i = 0 ; i < str.length() ; i++){
         set.add(str.charAt(i));
      }
      StringBuilder sb = new StringBuilder();
      for(char ch : set){
         sb.append(ch);
      }

      return smallestSubstring(str,sb.toString()).length();
	}
  
  //Longest Substring With Non Repeating Characters
  public static int longestSubStringUniqueChar(String str) {
   HashMap<Character,Integer> fmap = new HashMap<>();
   int acq =-1;
   int rel = -1;
   int ans = 0;
   while(true){
      boolean acqFlag = false;
      boolean relFlag = false;
      while(acq < str.length()-1){
         acqFlag = true;
         acq++;
         char ch = str.charAt(acq);
         fmap.put(ch,fmap.getOrDefault(ch, 0) + 1);

         if(fmap.get(ch) == 2){
            break;
         }else{
            int temp = acq-rel;
            ans = Math.max(ans,temp);
         }
         
      }

      while(rel < acq){
         relFlag = true;
         rel++;
         char ch = str.charAt(rel);
         fmap.put(ch,fmap.getOrDefault(ch, 0) - 1);

         if(fmap.get(ch) == 1){
            break;
         }
         
      }

      if(!acqFlag && !relFlag){
         break;
      }
   }

   return ans;
}
  
  //Count Of Substrings Having All Unique Characters
   public static int CountSubStringUniqueChar(String str){
     int acq =-1;
     int rel = -1;
     int count = 0;
     HashMap<Character,Integer> fmap = new HashMap<>();
     while(true){
        boolean acqFlag = false;
        boolean relFlag = false;

        while(acq < str.length()-1){
           acqFlag = true;
           acq++;
           char ch = str.charAt(acq);
           fmap.put(ch, fmap.getOrDefault(ch, 0) + 1);
           if(fmap.get(ch) == 2){
              break;
           }else{
              count += acq - rel;
           }
        }
        while(rel < acq  ){
         relFlag = true;
         rel++;
         char ch = str.charAt(rel);
         fmap.put(ch, fmap.getOrDefault(ch, 0) - 1);
         if(fmap.get(ch) == 1){
            count += acq - rel;
            break;
         }
      }

      if(!acqFlag && !relFlag){
         break;
      }
     }

     return count;
  }
   
  //Longest Substring With Exactly K Unique Characters
  public static int LongestSubStringUniqueCharWithKDigit(String str, int k){
   int count =-1;
   int acq =-1;
   int rel = -1;
   HashMap<Character,Integer> map = new HashMap<>();
   while(true){
      boolean acqFlag = false;
      boolean relFlag = false;

      while(acq < str.length() -1){
         acq++;
         acqFlag = true;
         char ch = str.charAt(acq);
         map.put(ch,map.getOrDefault(ch, 0) + 1);
         if(map.size() == k){
            count = Math.max(count, acq - rel);
         }else if(map.size() > k){
            break;
         }
      }
      while(rel<acq){
         rel++;
         relFlag = true;
         char ch = str.charAt(rel);
         map.put(ch,map.getOrDefault(ch, 0) - 1);
         if(map.get(ch) == 0){
            map.remove(ch);
         }
         if(map.size() == k){
            break;
         }
      }
      if(!acqFlag && !relFlag){
         break;
      }
   }
   return count;
  }
  
  //Count Substring With Exactly K Unique Characters
  private static int handleWhenK1(String str) {
   int acq = -1;
   int rel = -1;
   int count = 0;
   HashMap<Character, Integer> map = new HashMap<>();
   while(true) {
       boolean flag1 = false;
       boolean flag2 = false;

       while(acq < str.length() - 1) {
           flag1 = true;
           acq++;
           char ch = str.charAt(acq);
           map.put(ch, map.getOrDefault(ch, 0) + 1);

           if(map.size() == 2) {
               map.remove(ch);
               acq--;
               break;
           }
       }

       while(rel < acq) {
           count += acq - rel;
           rel++;
           char ch = str.charAt(rel);
           map.put(ch, map.get(ch) - 1);
           if(map.get(ch) == 0) map.remove(ch);

           if(map.size() == 0) break;
       }

       if(flag1 == false && flag2 == false) {
           break;
       }
   }

   return count;
}


  public static int countSubStringExactlyK(String str, int k) {
   if(k == 1) {
       return handleWhenK1(str);
   }
   HashMap<Character, Integer> mapb = new HashMap<>();
   HashMap<Character, Integer> maps = new HashMap<>();

   int count = 0;

   int is = -1;
   int ib = -1;
   int j = -1;

   while(true) {
       boolean flag1 = false;
       boolean flag2 = false;
       boolean flag3 = false;
       // k - 1 for map small
       while(is < str.length() - 1) {
           flag1 = true;
           is++;
           char ch = str.charAt(is);
           maps.put(ch, maps.getOrDefault(ch, 0) + 1);
           if(maps.size() == k) {
               maps.remove(ch);
               is--;
               break;
           }
       }
       // k for map big
       while(ib < str.length() - 1) {
           flag2 = true;
           ib++;
           char ch = str.charAt(ib);
           mapb.put(ch, mapb.getOrDefault(ch, 0) + 1);
           if(mapb.size() == k + 1) {
               mapb.remove(ch);
               ib--;
               break;
           }
       }
       // release
       while(j < is) {
           flag3 = true;
           if(mapb.size() == k && maps.size() == k - 1) {
               count += ib - is;
           }
           j++;
           char ch = str.charAt(j);
           // remove from maps
           maps.put(ch, maps.get(ch) - 1);
           if(maps.get(ch) == 0) maps.remove(ch);
           // remove from mapb
           mapb.put(ch, mapb.get(ch) - 1);
           if(mapb.get(ch) == 0) mapb.remove(ch);

           if(maps.size() < k - 1|| mapb.size() < k) {
               break;
           }
       }
       if(flag1 == false && flag2 == false  && flag3 == false) break;
   }
   return count;
}
  public static void main(String[] args) {

   }
}