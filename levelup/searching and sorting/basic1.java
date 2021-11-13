import java.lang.reflect.Array;
import java.util.Arrays;

import javax.swing.text.AbstractDocument.LeafElement;

import java.util.*;

public class basic1{

    //Marks of PCM :- https://practice.geeksforgeeks.org/problems/marks-of-pcm2529/1#
    public class Students implements Comparable<Students>{
        int p,c,m;
        Students(int p,int c,int m){
            this.p = p;
            this.c = c;
            this.m = m;
        }

        @Override
        public int compareTo(Students o) {
            if(this.p - o.p == 0){
                if(this.c == o.c){
                    return this.m - o.m;
                }else{
                    return o.c - this.c;
                }

            }else{
                return this.p-o.p;
            }
        }
    }

    public void customSort (int phy[], int chem[], int math[], int N)
    {
        Students[] arr =new Students[phy.length];
        for(int i =0;i<phy.length;i++){
            arr[i] = new Students(phy[i], chem[i], math[i]);
        }
        Arrays.sort(arr);
        for(int i =0;i<phy.length;i++){
            phy[i] = arr[i].p;
            chem[i] = arr[i].c;
            math[i] = arr[i].m;
        }
    }

    //Union Of Two Sorted Arrays :- https://practice.geeksforgeeks.org/problems/union-of-two-sorted-arrays/1#

    public static ArrayList<Integer> union(int[] a,int[] b) {
        ArrayList<Integer> res = new ArrayList<>();

        int i = 0 , j = 0;
        while(i < a.length && j < b.length){
            if(a[i] == b[j]){
                if(res.size() ==0 || res.get(res.size()-1) != a[i]){
                    res.add(a[i]);
                }
                i++;
                j++;
            }else if(a[i] < b[j]){
                if(res.size() ==0 || res.get(res.size()-1) != a[i]){
                    res.add(a[i]);
                }
                i++;
            }else{
                if(res.size() ==0 || res.get(res.size()-1) != b[j]){
                    res.add(b[j]);
                }
                j++;
            }
        }

        while( i < a.length){
            if(res.size() ==0 || res.get(res.size()-1) != a[i]){
                res.add(a[i]);
            }
            i++;
        }

        while( j<b.length){
            if(res.size() ==0 || res.get(res.size()-1) != b[j]){
                res.add(b[j]);
            }
            j++;
        }
        return res;
    }

    //Search A 2d Matrix

    public static int findRowIdx(int[][] mat , int tar){
        int idx = -1;
        int lo=0;
        int hi = mat.length-1;  

        while(lo<=hi){
            int mid = lo+(hi-lo)/2;
            if(mat[mid][0] <= tar && tar <= mat[mid][mat[0].length-1]){
                idx = mid;
                break;
            }else if(tar < mat[mid][0]){
                hi = mid-1;
            }else{
                lo = mid+1;
            }
        }

        return idx;
    }

    public static int findColIdx(int[][] mat,int tar,int row){
        int idx =-1;
        int lo =0;
        int hi = mat[0].length-1;
        while(lo<=hi){
            int mid = lo+(hi-lo)/2;
            if(mat[row][mid] == tar){
                idx = mid;
                break;
            }else if(tar < mat[row][mid]){
                hi = mid-1;
            }else{
                lo = mid+1;
            }
        }
        return idx;
    }
    
    public static boolean search(int[][]matrix,int target) {
       int row = findRowIdx(matrix, target);
       if(row == -1) return false;
       int col = findColIdx(matrix, target, row);
       return col!=-1;
    }

    //Search A 2d Matrix - 2
    
    public static boolean search1(int[][]matrix,int target) {
        int r = 0;
        int c = matrix[0].length-1;

        while(r < matrix.length && c >=0){
            if(matrix[r][c] == target){
                return true;
            }else if( matrix[r][c] < target){
                r++;
            }else{
                c--;
            }
        }
        //write your code here
        return false;
    }

    //724:- Find Pivot Index :- https://leetcode.com/problems/find-pivot-index/submissions/
    
    public static int pivot_index(int[]arr) {
        int sum = 0;
        for(int i =0 ; i <arr.length;i++){
            sum+=arr[i];
        }
        int psum = 0;
        for(int i = 0 ; i < arr.length;i++){
            if(psum == (sum - psum - arr[i])){
                return i;
            }
            psum+=arr[i];
        }
        return -1;
    }

    //Find K Closest Elements

    public static class Pair implements Comparable<Pair>{
        int val;
        int dis;
        public Pair(int val,int dis){
            this.val = val;
            this.dis = dis;
        }

        public int compareTo(Pair o){
            if(o.dis == this.dis){
                return this.val - o.val;
            }
            return this.dis - o.dis;
        }
    }
    
    public static ArrayList<Integer> findClosest(int[]arr,int k,int x) {
       ArrayList<Integer> res = new ArrayList<>();
       PriorityQueue<Pair> pq = new PriorityQueue<>(Collections.reverseOrder());

       for(int i=0;i<arr.length;i++){
            if(i<k){
                pq.add(new Pair(arr[i], Math.abs(arr[i]-x)));
            }else{
                if(pq.peek().dis > Math.abs(arr[i]-x)){
                    pq.remove();
                    pq.add(new Pair(arr[i], Math.abs(arr[i]-x)));
                }
            }
       }

       while(pq.size()>0){
           res.add(pq.remove().val);
       }
       Collections.sort(res);
       return res;
    }

}