import java.util.*;

public class basic3 {

    public static void printPrimeUsingSieve(int n) {
        // pre-calculation

        boolean[] isprime = new boolean[n + 1];
        Arrays.fill(isprime, true);

        for (int i = 2; i * i <= n; i++) {
            if (isprime[i] == false) {
                continue;
            }
            for (int j = i + i; j <= n; j += i) {
                isprime[j] = false;
            }
        }

        // for quering

        for (int i = 2; i <= n; i++) {
            if (isprime[i] == true) {
                System.out.print(i + " ");
            }
        }
    }

    // segmented sieve

    public static ArrayList<Integer> sieve(int n) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        boolean[] isprime = new boolean[n + 1];

        for (int i = 2; i * i <= n; i++) {
            if (isprime[i] == true) {
                continue;
            }
            for (int j = i + i; j <= n; j += i) {
                isprime[j] = true;
            }
        }

        // for quering

        for (int i = 2; i <= n; i++) {
            if (isprime[i] == false) {
                ans.add(i);
            }
        }
        return ans;
    }

    public static void segmentedSieveAlgo(int a, int b) {
        int rootb = (int) Math.sqrt(b);
        ArrayList<Integer> primes = sieve(rootb);

        int n = b - a;
        boolean[] isprime = new boolean[n + 1];
        // isprime[i] == true -> not prime
        // isprime[i] == false --> prime

        for (int prime : primes) {
            int mul = (int) Math.ceil(a * 1.0 / prime);
            if (mul == 1)
                mul++;
            int si = mul * prime - a;
            for (int i = si; i < isprime.length; i += prime) {
                isprime[i] = true;
            }
        }

        // travel and print prime

        for (int i = 0; i < isprime.length; i++) {
            if (isprime[i] == false && i + a != 1) {
                System.out.println(i + a + " ");
            }
        }
    }

    // Find Pair Given Difference :gfg

    public boolean findPair(int arr[], int n, int target) {
        Arrays.sort(arr);
        int i = 0, j = 1;
        while (i < n && j < n) {
            if ((i != j) && (arr[j] - arr[i] == target)) {
                return true;
            } else if (arr[j] - arr[i] < target) {
                j++;
            } else {
                i++;
            }
        }
        return false;
    }

    // 881. Boats to Save People :-
    // https://leetcode.com/problems/boats-to-save-people/

    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int left = 0;
        int right = people.length - 1;
        int boats = 0;
        while (left <= right) {
            int sum = people[left] + people[right];
            if (sum > limit) {
                right--;
            } else {
                left++;
                right--;
            }
            boats++;
        }

        return boats;
    }

    // 763. Partition Labels :- https://leetcode.com/problems/partition-labels/

    public List<Integer> partitionLabels(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), i);
        }

        int max = 0;
        int prev = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            max = Math.max(max, map.get(ch));
            if (max == i) {
                ans.add(i - prev + 1);
                prev = i + 1;
            }
        }

        return ans;
    }

    // 754. Reach a Number :- https://leetcode.com/problems/reach-a-number/

    public int reachNumber(int target) {
        int sum = 0;
        int i = 1;
        int jump = 0;
        target = Math.abs(target);
        while (sum < target) {
            sum += i;
            i++;
            jump++;
        }
        int diff = sum - target;

        if (diff % 2 != 0) {
            if (i % 2 == 0) {
                jump += 2;
            } else {
                jump++;
            }
        }

        return jump;
    }

    // 867. Transpose Matrix :- https://leetcode.com/problems/transpose-matrix/

    public int[][] transpose(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        int[][] res = new int[row][col];

        for (int r = 0; r < col; r++) {
            for (int c = 0; c < row; c++) {
                res[r][c] = matrix[c][r];
            }
        }

        return res;
    }

    // portal N*N -->> change in matrix (No extra space)

    public static void transpose2(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    // 48. Rotate Image
    public void rotate(int[][] matrix) {
        transpose2(matrix);

        // rev col

        for (int r = 0; r < matrix.length; r++) {
            int left = 0;
            int right = matrix[0].length - 1;

            while (left < right) {
                int temp = matrix[r][left];
                matrix[r][left] = matrix[r][right];
                matrix[r][right] = temp;
                left++;
                right--;
            }
        }
    }

    // 838. Push Dominoes

    public void solveConfig(char[] arr, int i, int j) {
        if (arr[i] == 'L' && arr[j] == 'L') {
            for (int k = i + 1; k < j; k++)
                arr[k] = 'L';
        } else if (arr[i] == 'R' && arr[j] == 'R') {
            for (int k = i + 1; k < j; k++)
                arr[k] = 'R';
        } else if (arr[i] == 'L' && arr[j] == 'R') {
            // nothing to do
        } else {
            int left = i + 1;
            int right = j - 1;
            while (left < right) {
                arr[left] = 'R';
                arr[right] = 'L';

                left++;
                right--;
            }
        }
    }

    public String pushDominoes(String dominoes) {                                            
        int n = dominoes.length();
        char[] arr = new char[n + 2];
        arr[0] = 'L';
        arr[n + 1] = 'R';

        for (int i = 0; i < n; i++) {
            arr[i + 1] = dominoes.charAt(i);
        }

        int i = 0;
        int j = 1;

        StringBuilder sb = new StringBuilder();

        while (j < arr.length) {
            while (arr[j] == '.') {
                j++;
            }

            if(j-i>1){
                solveConfig(arr, i, j);
            }
            
            i = j;
            j = j + 1;

        }
        StringBuilder str = new StringBuilder();
        for (int k = 1; k < arr.length - 1; k++) {
            str.append(arr[k]);
        }
        return str.toString();
    }

    public static void main(String[] args) {

    }
}