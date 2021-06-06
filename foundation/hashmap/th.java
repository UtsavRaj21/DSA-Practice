import java.util.*;


public class th {

//     public static void basic_01() {
//         HashMap<String, Integer> map = new HashMap<>();
//         map.put("Nepal", 233);
//         map.put("UK", 45);
//         map.put("Germany", 35);
//         map.put("USA", 20);
//         map.put("Russia", 18);
//         map.put("India", 10);
//         map.put("USA", 19);

//         // System.out.println(map);
//         for (String keys : map.keySet()) {
//             System.out.println(keys + " -> " + map.get(keys));
//         }

//         String key = "USA";
//         if (map.containsKey(key))
//             System.out.println(map.get(key));
//         else
//             System.out.println("not Found");

//     }

//     public static void printFrequency(String str) {
//         HashMap<Character, Integer> map = new HashMap<>();
//         // for (int i = 0; i < str.length(); i++) {
//         // char ch = str.charAt(i);
//         // if (map.containsKey(ch))
//         // map.put(ch, map.get(ch) + 1);
//         // else
//         // map.put(ch, 1);
//         // }

//         for (int i = 0; i < str.length(); i++) {
//             char ch = str.charAt(i);
//             map.put(ch, map.getOrDefault(ch, 0) + 1);
//         }
//     }

//     public static void highestFreqChar(String str) {
//         HashMap<Character, Integer> map = new HashMap<>();
//         for (int i = 0; i < str.length(); i++) {
//             char ch = str.charAt(i);
//             map.put(ch, map.getOrDefault(ch, 0) + 1);
//         }

//         char ans = ' ';
//         int freq = 0;
//         for (Character ch : map.keySet()) {
//             if (map.get(ch) > freq) {
//                 freq = map.get(ch);
//                 ans = ch;
//             }
//         }

//         System.out.println(ans);
//     }

//     public static void positionOfAllChar(String str) {
//         //ArrayList<Integer> ans=new ArrayList<Integer>();
//         HashMap<Character,ArrayList<Integer>> map = new HashMap<>();
//         // for(int i=0;i<str.length();i++){
//         //     char ch = str.charAt(i);
           
//         //     if(map.containsKey(ch)){
//         //         ArrayList<Integer> ans = map.get(ch);
//         //         ans.add(i);
//         //         map.put(ch,ans);
//         //     }else{
//         //         ArrayList<Integer> hello = new ArrayList<>();
//         //         hello.add(i);
//         //         map.put(ch,hello);
//         //     }
//         // }

//         for(int i=0;i<str.length();i++){
//                 char ch = str.charAt(i);
//                 if(!map.containsKey(ch)){         
//                     map.put(ch,new ArrayList<>());  //(on the place of if) map.IfAbsent(ch,new ArrayList<>())
//                 }
//                 map.get(ch).add(i);
//             }
//         for(char ch : map.keySet()){
//             System.out.println(ch + " -> "+map.get(ch));
//     }
// }
        

    public static void main(String[] args) {
        // basic_01();
        //positionOfAllChar("abcbbbccbbbbbbbb");

        for(int i=0;i<5;i++){
            for(int j=10;j<15;j++){
                if(i+j>15){
                    break;
                }
                System.out.println(i+j);
            }
        }
    }

}