import java.lang.annotation.Target;
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
            return o.dis - this.dis;
        }
    }
    
    public static ArrayList<Integer> findClosest(int[]arr,int k,int x) {
       ArrayList<Integer> res = new ArrayList<>();
       PriorityQueue<Pair> pq = new PriorityQueue<>();

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


    //Find Pair With Given Difference
    public static void findPair(int[]arr,int target) {
        Arrays.sort(arr);
        int i=0,j=1;
        boolean flag = false;
        while(j<arr.length){
            if(arr[j]-arr[i] == target){
                flag = true;
                System.out.println(arr[i]+" "+arr[j]);
                break;
            }
            else if(arr[j] - arr[i] < target){
                j++;
            }else{
                i++;
            }
        }
        if(!flag){
            System.out.println("-1");
        }
         
    }

    //Roof Top
    public static int findMaxSteps(int[]arr) {
        int step = 0;
        int ans =0;
        int num = arr[0];
        for(int i = 1 ; i < arr.length ; i++){
            if(arr[i] > num){
                step++;
            }else{
                step = 0;
            }
            ans = Math.max(ans, step);
        }
        return step;
    }

    //Maximize Sum Of Arr[i]*i Of An Array

    public static int maximise(int[]arr) {
        Arrays.sort(arr);
        int sum=0;
        for(int i=1;i<arr.length;i++){
            sum+=(i*arr[i]);
        }
        return sum;
    }

    //Max Sum In The Configuration :- The only operation allowed is to rotate(clock-wise or counter clock-wise) the array any number of times.
    
    public static int maximise1(int[]arr) {
        int n = arr.length;
       
        int sum =0;
        int prevSum =0;
        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
            prevSum+=(i*arr[i]);
        }
        int res = prevSum;

        for(int i =1;i<arr.length;i++){
            prevSum = prevSum + sum + (n * arr[n-i]);
            res = Math.max(res , prevSum);
        }
        return res;
    }


   
    //****************** binary search **************************************************

    //Find Transition Point

    public static int findTransition(int[]arr) {
        int tp =-1;
        int lo=0;
        int hi = arr.length -1;
        
        while(lo <= hi){
            int mid =(lo+hi)/2;
            if(arr[mid] == 1){
                tp = mid;
                hi = mid-1;
            }else{
                lo = mid+1;
            }
        }
        return tp;
            
        }

    //First Bad Version
        
    public static int firstBadVersion(int n) {
        int tp=1;
        int lo=1;
        int hi =n;
        while(lo<=hi){
            int mid = lo+(hi-lo)/2;                      // (lo+hi)/2
            if(isBadVersion(mid)){
                tp= mid ;
                hi=mid-1;
            }else{
                lo = mid+1;
            }
        }
        return tp;
    }

    //Guess Number Higher Or Lower
    
    public static int guessNumber(int n) {
        int point = -1;
        int lo=1;
        int hi = n;
        while(lo<=hi){
            int mid = lo+(hi-lo)/2;                      // (lo+hi)/2
            int res = guess(mid);
            if(res == 0){
                return mid;
            }else if(res == 1){
                lo = mid +1;
            }else{
                hi = mid -1;
            }
        }
        return -1;
        }

    //Find K Closest Elements

    public static ArrayList<Integer> findClosest(int[]arr,int k,int x) {  
        LinkedList<Integer> res = new LinkedList<>(); 
        int lo = 0;
        int hi = arr.length-1;
        int min = (int) 1e9;
        int idx = -1;

        while(lo<=hi){
            int mid = lo+(hi-lo)/2;                      // (lo+hi)/2
            if(Math.abs(arr[mid] - x) < min){
                min = Math.abs(arr[mid] - x);
                idx = mid;
            }
            if(arr[mid] == x){
                idx = mid;
                break;
            }else if(arr[mid] > x){
                hi = mid-1;
            }else{
                lo = mid+1;
            }
        }
        lo = idx-1;
        hi = idx; 
        while(res.size()<k ){
            int left = lo!=-1?Math.abs(arr[lo] - x) : (int)1e9;
            int right = hi!=arr.length?Math.abs(arr[hi] - x) : (int)1e9;

            if(left<=right){
                res.addFirst(arr[lo]);
                lo--;
            }else{
                res.addLast(arr[hi]);
                hi++;
            }
        }

        return res;
    }

     // Search in Rotated Sorted Arra
    public static int find(int[]arr,int target) {
       int lo = 0;
        int hi = arr.length-1;

        while(lo<=hi){
            int mid = lo+(hi - lo)/2;
            if(arr[mid] == target){
                return mid;
            }else if (arr[lo] < arr[mid]){
                //left side sorted
                if(target > arr[lo] && arr[mid] > target){
                    hi = mid -1;
                }else{
                    lo = mid + 1;
                }
            }else{
                //right side sorted
                if(arr[hi] > target && arr[mid] < target){
                    lo = mid +1;
                }else{
                    hi = mid -1;
                }
            }
        }
        return -1;
    }
     
    //Find Minimum In Rotated Sorted Array

     public static int findMinimum(int[]arr) {
        int n =arr.length;
        int lo =0;
        int hi = n-1;
        if(arr[lo] <= arr[hi]){
            return arr[0];
        }
 
        while(lo<=hi){
            int mid = lo+(hi-lo)/2;                      // (lo+hi)/2
            if(arr[mid] > arr[mid+1]){
                return mid+1;
            }else if(arr[mid] < arr[mid-1]){
                 return mid;
            }else if(arr[lo] <= arr[mid]){
                lo = mid + 1;
            }else{
                hi = mid-1;
            }
        }
        return -1;
     }
 
     //Find Rotation Count
     
     public static int findRotationCount(int[]arr) {
        int n =arr.length;
        int lo =0;
        int hi = n-1;
        if(arr[lo] <= arr[hi]){
            return 0;
        }
 
        while(lo<=hi){
            int mid = lo+(hi-lo)/2;                      // (lo+hi)/2
            if(arr[mid] > arr[mid+1]){
                return mid+1;
            }else if(arr[mid] < arr[mid-1]){
                 return mid;
            }else if(arr[lo] <= arr[mid]){
                lo = mid + 1;
            }else{
                hi = mid-1;
            }
        }
        return -1;
    }
    
    //Count Inversions
    static int count ;
    public static int countInversion(int[] arr){
        count =0;
        int[] res = mergeSort(arr,0,arr.length-1);
        return count;
    }
    
    public static int[] mergeSort(int[] arr, int si,int end){
        if(si>=end){
            int[] base = {arr[si]};
            return base;
        } 
        int mid = (si+end)/2;
        int[] left = mergeSort(arr, si, mid);
        int[] right = mergeSort(arr, mid+1, end);

        return mergeSortArr(left, right);
    }

    public static int[] mergeSortArr(int[] left,int[] right){
        int n1 = left.length;
        int n2 = right.length;

        int[] res = new int[n1+n2];
        int i=0,j=0,k=0;
        while(i<n1 && j < n2){
            if(left[i] > right[j]){
                res[k++] = right[j];
                j++;
                count+=n1-i;
            }else{
                res[k++] = left[i];
                i++;
            }
         }
         while(i<n1){
            res[k++] = left[i++];
         }
         while(j<n2){
            res[k++] = right[j++];
         }
         return res;
    }
    
    // 
    public static double find(int[]a1,int[]a2) {
        int n1 = a1.length;
        int n2 = a2.length;
        if(n1>n2){
            int[] temp = a1;
            a1 = a2;
            a2 = temp;

            int t = n1;
            n1 = n2;
            n2 =t;
        }

        int lo = 0;
        int hi = n1;

        int total = n1+n2;

        while(lo<=hi){
            int ali = lo+(hi+lo)/2;         //a left index
            int bli = (total + 1)/2-ali;      //bleft index

            int alm1 = ali ==0 ?Integer.MIN_VALUE:a1[ali-1];
            int al = ali==n1 ? Integer.MAX_VALUE:a1[ali];
            int blm1 = bli ==0 ?Integer.MIN_VALUE:a2[bli-1];
            int bl = bli==n2 ? Integer.MAX_VALUE:a2[bli];

            if(alm1 <= bl && blm1 <= al){
                double median = 0.0;
                if(total % 2 ==0){
                    median = (Math.max(alm1, blm1) + Math.min(al, bl)) / 2.0;
                }else{
                    median = Math.max(alm1, blm1);
                }
                return median;
            }else if(blm1 > al ){
                lo = ali+1;
            }else{
                hi = ali-1;
            }
        }
        return 0.0;

    }
}