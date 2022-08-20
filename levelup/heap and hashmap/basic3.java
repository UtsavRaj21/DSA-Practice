//ANAGRAM
import java.util.*;

public class basic3 {
    //    Valid Anagram
    public static boolean validAnagram(String s1, String s2){
		if(s1.length() != s2.length()) return false;
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i = 0 ; i < s1.length() ; i++){
            char ch = s1.charAt(i);
            map.put(ch,map.getOrDefault(ch, 0)+1);
        }
        for(int i = 0 ; i < s1.length() ; i++){
            char ch = s2.charAt(i);
            if(map.containsKey(ch)){
                if(map.get(ch) == 1){
                    map.remove(ch);
                }else{
                    map.put(ch, map.get(ch)-1);
                }
            }else{
                return false;
            }
            
        }

        return map.size()==0;
	}

    //Find Anagram Mappings
    public static int[] anagramMappings(int[] arr1, int[] arr2) {
		HashMap<Integer,LinkedList<Integer>> map = new HashMap<>();
        for(int i = 0 ; i < arr2.length ; i++){
            int ele = arr2[i];
            if(map.containsKey(ele)){
                map.get(ele).addLast(i);
            }else{
                LinkedList<Integer> ll = new LinkedList<>();
                ll.addLast(i);
                map.put(ele,ll);
            }
        }


        int[] res = new int[arr1.length];

        for(int i = 0 ; i < arr1.length ; i++){
            int ele = arr1[i];
            int idx = map.get(ele).removeFirst();
            res[i] = idx;
        }

        return res;
	}

    //Find All Anagrams In A String
    public static boolean compare(HashMap<Character,Integer> smap,HashMap<Character,Integer> pmap){
        for(char c : smap.keySet()){
            if(pmap.getOrDefault(c,0) != smap.get(c)){
                return false;
            }
        }
        return true;
    }
    
    public static void findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int count = 0;
        if(p.length() > s.length()) return;
        HashMap<Character,Integer> smap = new HashMap<>();
        HashMap<Character,Integer> pmap = new HashMap<>();

        for(int i = 0 ; i < p.length() ; i++){
            char ch = p.charAt(i);
            pmap.put(ch, pmap.getOrDefault(ch,0)+1);
        }
        for(int i = 0 ; i < p.length()-1 ; i++){
            char ch = s.charAt(i);
            smap.put(ch, smap.getOrDefault(ch,0)+1);
        }

        int j = 0 ;
        int i = p.length()-1;
        while(i<s.length()){
            char ch = s.charAt(i);
            smap.put(ch, smap.getOrDefault(ch,0)+1);
            if(compare(smap,pmap)){
                count++;
                res.add(j);
            }

            char c = s.charAt(j);
            if(smap.get(c) == 1){
                smap.remove(c);
            }else{
                smap.put(c,smap.get(c)-1);
            }

            i++;
            j++;
        }
        System.out.println(count);
        for(int idx : res){
            System.out.print(idx+" ");
        }
        
	}

    //K Anagrams
    // 1. You are given two strings s1, s2, and a number K.
    // 2. You have to find if two strings are K-anagrams of each other or not.
    // 3. Two strings are called K-anagrams if 
    //    -> Both s1 and s2 have the same number of characters.
    //    -> After changing K characters in any string, s1 and s2 become anagram of each other. 

    public static boolean areKAnagrams(String s, String p, int k) {
		if(s.length()!=p.length()) return false;
        HashMap<Character,Integer> map = new HashMap<>();
        //add s in map
        for(int i = 0 ; i < s.length() ; i++){
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch,0)+1);
        }
        for(int i = 0 ; i < s.length() ; i++){
            char ch = p.charAt(i);
            if(map.getOrDefault(ch, 0) > 0){
                map.put(ch,map.get(ch)-1);
            }
        }

        int count =0;
        for(char ch : map.keySet()){
            count+=map.get(ch);
        }
        return count<=k;
	}

    //Group Anagrams
    public static ArrayList<ArrayList<String>> groupAnagrams(String[] strs) {
		HashMap<HashMap<Character,Integer>,ArrayList<String>> map = new HashMap<>();
        for(String str  : strs){
            HashMap<Character,Integer> fmap = new HashMap<>();
            for(int i = 0; i< str.length() ; i++){
                char ch = str.charAt(i);
                fmap.put(ch,fmap.getOrDefault(ch, 0)+1);
            }

            if(map.containsKey(fmap)){
                map.get(fmap).add(str);
            }else{
                ArrayList<String> list = new ArrayList<>();
                list.add(str);
                map.put(fmap,list);
            }
        }

        ArrayList<ArrayList<String>> res = new ArrayList<>();
        for(ArrayList<String> list : map.values()){
            res.add(list);
        }
        return res;
	}

    public static ArrayList<ArrayList<String>> groupShiftedStrings(String[] strs){
        HashMap<String,ArrayList<String>> map = new HashMap<>();
        for(String str : strs){
            StringBuilder sb = new StringBuilder();
            for(int i = 1 ; i < str.length() ; i++){
                int rem = str.charAt(i) - str.charAt(i-1);
                if(rem<0){
                    rem= rem+26;
                }
                sb.append(rem);
                
                sb.append('#');
                
            }
            sb.append('.');

            if(map.containsKey(sb.toString())){
                map.get(sb.toString()).add(str);
            }else{
                ArrayList<String> ll = new ArrayList<>();
                ll.add(str);
                map.put(sb.toString(),ll);
            }
        }

        ArrayList<ArrayList<String>> res = new ArrayList<>();
        for(ArrayList<String> l : map.values()){
            res.add(l);
        }

        return res;
    }
   
    public static void main(String[] args) {
        
    }
}


