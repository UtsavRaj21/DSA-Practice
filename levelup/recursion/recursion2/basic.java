import java.util.*;
import java.util.ArrayList;

public class basic {
    static String s1 = "send";
    static String s2 = "more";
    static String s3 = "money";

    static boolean[] usedNumber = new boolean[10];
    static int[] mapping = new int[128];

    public static int convertStringToNumber(String str) {
        int res = 0;
        for (int i = 0; i < str.length(); i++) {
            res = res * 10 + mapping[str.charAt(i)];
        }

        return res;
    }

    public static int crypto(String str, int idx) {
        if (idx == str.length()) {
            if (mapping[s1.charAt(0)] == 0 || mapping[s2.charAt(0)] == 0 || mapping[s3.charAt(0)] == 0)
                return 0;

            int x = convertStringToNumber(s1);
            int y = convertStringToNumber(s2);
            int z = convertStringToNumber(s3);

            if (x + y == z) {
                StringBuilder sb = new StringBuilder();
                for (int i = 'a'; i <= 'z'; i++) {
                    if (mapping[i] >= 0) {
                        sb.append((char) i + "-" + mapping[i] + " ");
                    }
                }

                System.out.println(sb.toString());
                return 1;
            }

            return 0;
        }

        int count = 0;
        char ch = str.charAt(idx);
        for (int i = 0; i <= 9; i++) {
            if (!usedNumber[i]) {
                usedNumber[i] = true;
                mapping[ch] = i;

                count += crypto(str, idx + 1);
                usedNumber[i] = false;
                mapping[ch] = -1;
            }
        }

        return count;
    }

    public static void crypto() {
        String str = s1 + s2 + s3;
        int[] freq = new int[26];
        for (int i = 0; i < str.length(); i++)
            freq[str.charAt(i) - 'a']++;

        str = "";
        for (int i = 0; i < 26; i++)
            if (freq[i] > 0)
                str += (char) (i + 'a');

        Arrays.fill(mapping, -1);

        System.out.println(crypto(str, 0));
    }

    // public static int wordBreak(String str, int idx, String ans, int len,
    // HashSet<String> dict) {
    // if (idx >= str.length()) {
    // System.out.println(ans);
    // return 1;
    // }
    // int count = 0;
    // for (int i = idx; i <= str.length(); i++) {
    // String word = str.substring(idx, i);
    // if (word.length() > len)
    // break;
    // if (dict.contains(word)) {
    // count += wordBreak(str, i, ans + word + " ", len, dict);
    // }
    // }
    // return count;
    // }

    // public static void wordBreak(String str, String ans, HashSet<String> dict) {
    // int len = 0;
    // for (String s : dict)
    // len = Math.max(len, s.length());
    // wordBreak(str, 0, ans, len, dict);
    // }

    // Sudoku

    public boolean isSafeToPlaceNumber(char[][] board, int row, int col, int num) {

        int n = board.length, m = board[0].length;
        // row
        for (int j = 0; j < m; j++)
            if ((board[row][j] - '0') == num)
                return false;

        // col
        for (int i = 0; i < n; i++)
            if ((board[i][col] - '0') == num)
                return false;

        // mat
        row = (row / 3) * 3;
        col = (col / 3) * 3;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if ((board[i + row][j + col] - '0') == num)
                    return false;

        return true;
    }

    public boolean sudokuSolver(char[][] board, int idx) {
        if (idx == 81) {
            return true;
        }

        int r = idx / 9;
        int c = idx % 9;

        if (board[r][c] != '.') {
            if (sudokuSolver(board, idx + 1))
                return true;
        } else {
            for (int num = 1; num <= 9; num++) {
                if (isSafeToPlaceNumber(board, r, c, num)) {
                    board[r][c] = (char) (num + '0');
                    if (sudokuSolver(board, idx + 1))
                        return true;
                    board[r][c] = '.';
                }
            }
        }

        return false;
    }

    public boolean sudokuSolver(char[][] board, ArrayList<Integer> IDX, int idx) {
        if (idx == IDX.size()) {
            return true;
        }

        int r = IDX.get(idx) / 9;
        int c = IDX.get(idx) % 9;

        for (int num = 1; num <= 9; num++) {
            if (isSafeToPlaceNumber(board, r, c, num)) {
                board[r][c] = (char) (num + '0');
                if (sudokuSolver(board, IDX, idx + 1))
                    return true;
                board[r][c] = '.';
            }
        }

        return false;
    }

    public void solveSudoku(char[][] board) {
        ArrayList<Integer> IDX = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    IDX.add(i * 9 + j);
                }

            }
        }

        // sudokuSolver(board, 0);

    }

    // equal subset

    public static void solve_2subsetHelper(int[] arr, int c1, int c2, ArrayList<Integer> a1, ArrayList<Integer> a2,
            int idx) {
        if (idx == arr.length) {
            if (c1 == c2 && a1.size() == a2.size()) {
                System.out.print(a1);
                System.out.println(a2);
            }
            return;
        }

        if (a1.size() <= (arr.length / 2)) {
            a1.add(arr[idx]);
            solve_2subsetHelper(arr, c1 + arr[idx], c2, a1, a2, idx + 1);
            a1.remove(a1.size() - 1);
        }

        if (a2.size() <= (arr.length / 2)) {
            a2.add(arr[idx]);
            solve_2subsetHelper(arr, c1, c2 + arr[idx], a1, a2, idx + 1);
            a2.remove(a2.size() - 1);
        }
    }

    public static void solve_2subset() {
        int[] arr = { 10, 20, 30, 40, 50, 60, 70, 80 };
        ArrayList<Integer> a1 = new ArrayList<>();
        ArrayList<Integer> a2 = new ArrayList<>();
        a1.add(arr[0]);
        solve_2subsetHelper(arr, arr[0], 0, a1, a2, 1);
    }

    public static void solve_helper(int[] arr, int k, ArrayList<Integer>[] sum, int check,
            ArrayList<ArrayList<Integer>> ans, int idx) {
        if (arr.length == idx) {
            for (ArrayList<Integer> a : sum) {
                int s = 0;
                for (int ele : a) {
                    s += ele;
                }
                if (s != check) {
                    return;
                }
            }
            for (ArrayList<Integer> a : sum) {
                System.out.print(a);
                ans.add(new ArrayList<Integer>(a));
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < k; i++) {
            sum[i].add(arr[idx]);
            solve_helper(arr, k, sum, check, ans, idx + 1);
            sum[i].remove(sum[i].size() - 1);
        }
    }

    public static ArrayList<ArrayList<Integer>> solve_KsubsetHelper(int[] arr, int k) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
        int s = 0;
        for (int ele : arr) {
            s += ele;
        }

        if (s % k != 0 || k > arr.length) {
            return ans;
        }

        int check = s / k;

        ArrayList<Integer>[] sum = new ArrayList[k];
        // boolean[] vis = new boolean[arr.length];

        for (int i = 0; i < k; i++) {
            sum[i] = new ArrayList<>();
         }
         sum[0].add(arr[0]);
        solve_helper(arr, k, sum, check, ans, 1);
        return ans;
    }

    public static void solve_ksubset() {
        int[] arr = { 10, 20, 30, 40, 50, 60, 70, 80 };
        int k = 4;

        // ArrayList<Integer> a2 = new ArrayList<>();
        ArrayList<ArrayList<Integer>> ans = solve_KsubsetHelper(arr, k);
        // for (ArrayList<Integer> ele : ans) {
        //     System.out.print(ele);
        // }
        // System.out.println();
    }

    public static void subset() {
        // solve_2subset();
        solve_ksubset();

    }

    //-*******************************************************************************

	public static void solution(char[][] arr, String[] words, int idx){
		if(idx==words.length){
		    print(arr);
		    return;
		}
		
		for(int i=0;i<arr.length;i++){
		    for(int j=0;j<arr[0].length;j++){
		        if(IsSafeplaceH(arr,i,j,words[idx])){
		            boolean[] loc = placeH(arr,i,j,words[idx]);
		            solution(arr,words,idx+1);
		            unplaceH(arr,i,j,words[idx],loc);
		        }
		        
		        if(IsSafeplaceV(arr,i,j,words[idx])){
		            boolean[] loc = placeV(arr,i,j,words[idx]);
		            solution(arr,words,idx+1);
		            unplaceV(arr,i,j,words[idx],loc);
		        }
		    }
		}
	}
	
	public static boolean IsSafeplaceH(char[][] arr,int r,int c,String words){
	     for(int i=0;i<words.length();i++){
	         if (c + i >= arr[0].length)
                return false;
	         if(arr[r][c+i] != '-'  && arr[r][c+i]!=words.charAt(i)){
	             return false;
	         }
	     }
	     return true;
	}
	
	public static boolean[] placeH(char[][] arr,int r,int c,String words){
	    boolean[] loc = new boolean[words.length()];
	    for(int i=0;i<words.length();i++){
	        if(arr[r][c+i] =='-'){
	            arr[r][c+i] = words.charAt(i);
	            loc[i] = true;
	        }
	    }
	    return loc;
	}
	
	public static void unplaceH(char[][] arr,int r,int c,String words,boolean[] loc){
	    for(int i=0;i<words.length();i++){
	        if(loc[i] == true){
	           arr[r][c+i] ='-';
	        }
	    }
	}
	
	public static boolean IsSafeplaceV(char[][] arr,int r,int c,String words){
	     for(int i=0;i<words.length();i++){
	         if (r + i >= arr.length)
                return false;
	         if(arr[r+i][c] != '-'  && arr[r+i][c]!=words.charAt(i)){
	             return false;
	         }
	     }
	     return true;
	}
	
	public static boolean[] placeV(char[][] arr,int r,int c,String words){
	    boolean[] loc = new boolean[words.length()];
	    for(int i=0;i<words.length();i++){
	        if(arr[r+i][c] =='-'){
	            arr[r+i][c] = words.charAt(i);
	            loc[i] = true;
	        }
	    }
	    return loc;
	}
	
	public static void unplaceV(char[][] arr,int r,int c,String words,boolean[] loc){
	    for(int i=0;i<words.length();i++){
	        if(loc[i] == true){
	           arr[r+i][c] ='-';
	        }
	    }
	}

	
	public static void print(char[][] arr){
		for(int i = 0 ; i < arr.length; i++){
			for(int j = 0 ; j < arr.length; j++){
				System.out.print(arr[i][j]);
			}
                  System.out.println();
		}
		
	}}

    public static void main(String[] args) {
        // crypto();
        Scanner scn = new Scanner(System.in);
		char[][] arr = new char[10][10];
		for(int i = 0 ; i < arr.length; i++){
			String str = scn.next();
			arr[i] = str.toCharArray();
		}
		int n = scn.nextInt();
		String[] words = new String[n];
		for(int i = 0 ; i  < words.length; i++){
			words[i] = scn.next();
		}
	Arrays.sort(words, (a, b) -> {
            return b.length() - a.length();
        });
           solution(arr, words,0);
	
        //subset();
    }
}