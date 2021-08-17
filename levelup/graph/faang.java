import java.util.*;

public class faang {

    public class Edge {
        int v = 0;
        int w = 0;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    // 127. Word Ladder :- https://leetcode.com/problems/word-ladder/submissions/

    public static String toString(char[] a) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < a.length; i++) {
            sb.append(a[i]);
        }

        return sb.toString();
    }

    public static void search(char[] chArr, HashMap<String, Boolean> map, LinkedList<String> que) {
        String s = toString(chArr);
        for (int i = 0; i < chArr.length; i++) {
            char[] arr = s.toCharArray();
            for (char j = 'a'; j <= 'z'; j++) {
                arr[i] = j;
                String str = toString(arr);
                System.out.println(str);
                if (map.containsKey(str) && !map.get(str)) {
                    map.replace(str, true);
                    que.add(str);

                }
            }
        }
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashMap<String, Boolean> map = new HashMap<>();
        for (String s : wordList) {
            map.put(s, false);
        }

        if (!map.containsKey(endWord)) {
            return 0;
        }

        LinkedList<String> que = new LinkedList<>();

        que.add(beginWord);

        int level = 1;
        while (que.size() != 0) {
            int s = que.size();
            while (s-- > 0) {
                String rStr = que.removeFirst();
                if (rStr.equals(endWord)) {
                    return level;
                }
                char[] chArr = rStr.toCharArray();
                search(chArr, map, que);

            }
            level++;
        }
        return 0;
    }

    // 773. Sliding Puzzle :- https://leetcode.com/problems/sliding-puzzle/

    public static String swap(String str, int i, int j) {
        StringBuilder sb = new StringBuilder(str);

        sb.setCharAt(i, str.charAt(j));
        sb.setCharAt(j, str.charAt(i));

        return sb.toString();
    }

    public static int slidingPuzzle(int[][] board) {
        HashSet<String> map = new HashSet<>();
        String target = "123450";

        LinkedList<String> que = new LinkedList<>();

        int[][] dir = { { 1, 3 }, { 0, 2, 4 }, { 1, 5 }, { 0, 4 }, { 1, 3, 5 }, { 2, 4 } };

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                sb.append(board[i][j]);
            }
        }

        String str = sb.toString();
        if (str.equals(target))
            return 0;
        map.add(str);
        que.add(str);
        System.out.println(str);

        int level = 0;

        while (que.size() != 0) {
            int s = que.size();
            while (s-- > 0) {
                String rstr = que.removeFirst();

                int idx = -1;

                for (int i = 0; i < rstr.length(); i++) {
                    if (rstr.charAt(i) == '0') {
                        idx = i;
                        break;
                    }

                }

                for (int e : dir[idx]) {
                    String st = rstr;
                    String swapString = swap(st, idx, e);
                    // System.out.println(swapString);
                    if (!map.contains(swapString)) {
                        if (swapString.equals(target))
                            return ++level;
                        map.add(swapString);
                        que.add(swapString);
                    }
                }
            }
            level++;
        }

        return -1;
    }

    public static void main(String[] args) {

    }
}
