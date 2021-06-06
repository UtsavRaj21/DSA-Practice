public class pep {

    //==================================================== friends pairing 2

//     import java.io.*;
// import java.util.*;

// public class Main {
//   static int counter = 1;

//   public static void solution(int i, int n, boolean[] used, String asf) {
//     if(i>n){
//         System.out.println(counter+++"."+asf);
//         return;
//     }
    
//     if(used[i]){
//             solution(i+1,n,used,asf);
//     }else{
//         used[i]=true;
//         solution(i+1,n,used,asf+"("+i+") ");
//         for(int j=i+1;j<=n;j++){
//             if(!used[j]){
//                used[j]=true;
//                 solution(i+1,n,used,asf+"("+i+","+j+") "); 
//                 used[j]=false;
//             }
//         }
//         used[i]=false;
//     }
//   }

//   public static void main(String[] args) throws Exception {
//     Scanner sc = new Scanner(System.in);
//     int n = sc.nextInt();
//     boolean[] used = new boolean[n + 1];
//     solution(1, n, used, "");
//   }
// }

//=========================================================================================================================

//============================================= k partion

// import java.io.*;
// import java.util.*;

// public class Main {
//     static int counter  = 1;
// 	public static void solution(int i, int n, int k, int ssf, ArrayList<ArrayList<Integer>> ans) {
// 	    if(i>n){
// 	        if(k==ssf){
// 	            System.out.print(counter++ + (". "));
// 	            for(ArrayList<Integer> set : ans){
// 	                System.out.print(set+ (" "));
// 	            } 
// 	            System.out.println();
// 	        }
	        
// 	        return;
// 	    }
	    
// 	    for(int j = 0 ; j<ans.size() ; j++){
// 	        if(ans.get(j).size()>0){
// 	            ans.get(j).add(i);
// 	            solution(i+1,n,k,ssf,ans);
// 	            ans.get(j).remove(ans.get(j).size()-1);
// 	        }else{
// 	            ans.get(j).add(i);
// 	            solution(i+1,n,k,ssf+1,ans);
// 	            ans.get(j).remove(ans.get(j).size()-1);
// 	            break;
// 	        }
// 	    }
		
// 	}
// 	public static void main(String[] args) {
// 		Scanner scn = new Scanner(System.in);
// 		int n = scn.nextInt();
// 		int k = scn.nextInt();
// 		ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
// 		for(int i  = 0; i < k; i++) {
// 			ans.add(new ArrayList<>());
// 		}
// 		solution(1, n, k, 0, ans);

// 	}

// }

//======================================================================================================

//===================================================Cryptarithmetic

// import java.io.*;
// import java.util.*;

// public class Main {

//   public static void main(String[] args) {
//     Scanner scn = new Scanner(System.in);
//     String s1 = scn.nextLine();
//     String s2 = scn.nextLine();
//     String s3 = scn.nextLine();

//     HashMap<Character, Integer> charIntMap = new HashMap<>();
//     String unique = "";
//     for (int i = 0; i < s1.length(); i++) {
//       if (!charIntMap.containsKey(s1.charAt(i))) {
//         charIntMap.put(s1.charAt(i), -1);
//         unique += s1.charAt(i);
//       }
//     }

//     for (int i = 0; i < s2.length(); i++) {
//       if (!charIntMap.containsKey(s2.charAt(i))) {
//         charIntMap.put(s2.charAt(i), -1);
//         unique += s2.charAt(i);
//       }
//     }

//     for (int i = 0; i < s3.length(); i++) {
//       if (!charIntMap.containsKey(s3.charAt(i))) {
//         charIntMap.put(s3.charAt(i), -1);
//         unique += s3.charAt(i);
//       }
//     }

//     boolean[] usedNumbers = new boolean[10];
//     solution(unique, 0, charIntMap, usedNumbers, s1, s2, s3);
//   }
    
//    public static int convertStringToNumber(String str, HashMap<Character, Integer> charIntMap) {
//         int res = 0;
//         for (int i = 0; i < str.length(); i++) {
//             res = res * 10 + charIntMap.get(str.charAt(i));
//         }

//         return res;
//     }
//     static int count=0;
  
//   public static void solution(String unique, int idx, HashMap<Character, Integer> charIntMap, boolean[] usedNumbers, 
// 							  String s1, String s2, String s3) {
// 	    if(idx==unique.length()){
// 	        int x = convertStringToNumber(s1,charIntMap);
// 	        int y = convertStringToNumber(s2,charIntMap);
// 	        int z = convertStringToNumber(s3,charIntMap);
	        
// 	        if(x+y==z){
// 	           for(char ch='a';ch<='z';ch++){
// 	               if((charIntMap.containsKey(ch)) && (charIntMap.get(ch)>-1)){
// 	                   System.out.print(ch+"-"+charIntMap.get(ch)+" ");
// 	               }
// 	           }
// 	            System.out.println();
	         
	                
// 	        }
// 	        return ;
// 	    }
// 	    char ch=unique.charAt(idx);
// 	    if(charIntMap.get(ch)>-1){
// 	        return;
// 	    }
// 	    for(int i=0;i<usedNumbers.length;i++){
// 	        if(!usedNumbers[i]){
// 	             charIntMap.put(ch, i);
// 	            usedNumbers[i]=true;
// 	             solution(unique, idx+1, charIntMap, usedNumbers, s1, s2, s3);
// 	            usedNumbers[i]=false;
// 	            charIntMap.put(ch, -1);
// 	        }
// 	    }
//   }

// }

}
