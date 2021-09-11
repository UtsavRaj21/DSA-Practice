import java.util.*;
import java.lang.Math;

class Solution {

    public static int getmin(int n, int[] p) {
        int ans = 0;
        int i=0;
        while (i < n) {
            boolean check = false;
            int idx =0;
            if(p[i] == 1){
                idx = p[i]+1;
            }else{
                idx = p[i];
            }
            int z = i;
            while (z<n) {
                if(p[z] != 1){
                    p[z]--;
                    check = true;
                }
                z+=idx;
            }
            
            if(check){
                ans++;
            }

            if(p[i]==1) {
                i++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] p = new int[n];

        for (int i = 0; i < n; i++) {
            p[i] = scan.nextInt();
        }
        int ans = getmin(n, p);
        System.out.println(ans);
    }
}
