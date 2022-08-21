//Acquire and release group
import java.util.*;

public class basic2 {
    
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
  int reluire = s2.length();
  String ans ="";
  HashMap<Character,Integer> map = new HashMap<>();
  while(true){
     boolean acFlag = false;
     boolean reFlag = false;

     while(acq < s1.length() -1 && count < reluire){
        acq++;
        char c = s1.charAt(acq);
        map.put(c,map.getOrDefault(c,0)+1);
        
        if(map.get(c) <= fmap.getOrDefault(c, 0)){
           count++;
        }
        acFlag = true;
     }

     while(rel < acq && count == reluire){
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

    //Maximum Consecutive Ones - 1 :- Flip one zero
    public static int MaxConsecutiveOnes(int[] arr){
        int count0 = 0;
        int ans = 0;
        int acq = -1;
        int rel = -1;

        while(true){
            boolean acqFlag = false;
            boolean relFlag = false;

            while(acq < arr.length - 1){
            acqFlag = true;
            acq++;
            int ele = arr[acq];
            if(ele == 0){
                count0++;
            }
            if(count0 == 2){
                break;
            }else{
                ans = Math.max(ans, acq - rel);
            }
            }

            while(rel < acq){
                relFlag = true;
                rel++;
                int ele = arr[rel];
                if(ele == 0){
                count0--;
                break;
                }
            }

            if(!acqFlag && !relFlag){
                break;
            }
        }

        return ans;
    }

    //Maximum Consecutive Ones - 1 :- Flip K zero
    public static int MaxConsecutiveOnes(int[] arr,int k){
    int count0 = 0;
    int ans =0;
    int j = -1;
    for(int i = 0 ; i < arr.length ;i++){
        int ele = arr[i];
        if(ele == 0) count0++;

        while(k<count0){
            j++;
            if(arr[j] == 0){
            count0--;
            }
        }

        ans = Math.max(ans, i - j);
    }

    return ans;
    }

    //Largest Subarray With Contiguous Elements
    public static int LargestSubarrayWithContiguousElements(int[] arr) {
        int ans = 0;

    for(int i = 0; i < arr.length ; i++){
        int max= arr[i];
        int min = arr[i];
        HashSet<Integer> set = new HashSet<>();
        for(int j = i ; j < arr.length; j++){
            if(set.contains(arr[j])){
            break;
            }
            set.add(arr[j]);
            min = Math.min(min, arr[j]);
            max = Math.max(max, arr[j]);

            if(max - min == j-i){
            ans = Math.max(ans , j-i+1);
            }
        }
    }
    return ans;
    }

    //	Longest Substring With At Most K Unique Characters
    public static int LongestSubstringAtMostKUnique(String str, int k) {
		HashMap<Character,Integer> map = new HashMap<>();
         int len = 0;
		int acq = -1;
        int rel  = -1;

        while(true){
            boolean acqFlag = false;
            boolean relFlag = false;

            while(acq < str.length()-1){
                acq++;
                acqFlag = true;
                char ch = str.charAt(acq);  
                map.put(ch, map.getOrDefault(ch, 0)+1);
                if(map.size()>k){
                    break;
                }else{
                    len = Math.max(len, acq-rel);
                }
            }

            while(rel < acq){
                rel++;
                relFlag = true;
                char ch = str.charAt(rel);
                if(map.get(ch) == 1){
                    map.remove(ch);
                    break;
                }else{
                    map.put(ch, map.get(ch)-1);
                }
            }

            if(!acqFlag && !relFlag){
                break;
            }
        }
		return len;
	}

    //Count Of Substrings Having At Most K Unique Characters

    public static int CountSubstringAtMostKUnique(String str, int k) {
		HashMap<Character,Integer> map = new HashMap<>();
         int count = 0;
		int acq = -1;
        int rel  = -1;

        while(true){
            boolean acqFlag = false;
            boolean relFlag = false;

            while(acq < str.length()-1){
                acq++;
                acqFlag = true;
                char ch = str.charAt(acq);  
                map.put(ch, map.getOrDefault(ch, 0)+1);
                if(map.size()>k){
                    break;
                }else{
                    count += acq - rel;
                }
            }

            if(acq == str.length()-1 && map.size()<=k){
                break;
            }

            while(rel < acq){
                rel++;
                relFlag = true;
                char ch = str.charAt(rel);
                if(map.get(ch) == 1){
                    count += acq - rel;
                    map.remove(ch);
                    break;
                }else{
                    map.put(ch, map.get(ch)-1);
                }
            }

            if(!acqFlag && !relFlag){
                break;
            }
        }
		return count;
	}

    static int solve(List<Integer> arr, int n, int k)
    {
        int subArrayLen =n+1;
        HashMap<Integer, Integer> map = new HashMap<>();
        int i = 0, j = 0;
       
        while (j < n) {
            map.put(arr.get(j), map.getOrDefault(arr.get(j), 0) + 1);
            j++;
           
            if (map.size() < k)
                continue;
            while (map.size() == k)
            {
                int windowLen = (j - 1) - i + 1;
               
                if (windowLen < subArrayLen) {
                    subArrayLen = windowLen;
                }

                if (map.get(arr.get(i)) == 1)
                    map.remove(arr.get(i));
                
                else
                    map.put(arr.get(i), map.get(arr.get(i)) - 1);
                 
                i++;
            }
        }
 
        if (subArrayLen == n+1){
            return -1;
        }
       return subArrayLen;
    }
    public static void main(String[] args) {
        List<Integer> li=new ArrayList<>();
        li.add(2);
        li.add(2);
        li.add(1);
        li.add(1);
        li.add(3);
        int c = solve(li,5,3);
        System.out.println(c);
    }
}
