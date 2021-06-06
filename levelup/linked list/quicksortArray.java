
public class quicksortArray {
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int  segrigate(int[] arr, int pivotidx, int si, int ei) {
        swap(arr, pivotidx, ei);
        int p = si - 1, itr = si;
        while (itr <= ei) {
            if (arr[itr] <= arr[ei]) {
                swap(arr, ++p, itr);
            }
            itr++;
        }
        return p;
    }

    public static void  quick(int[] arr,int si,int ei) {
        if(si>ei){
            return;
        }
        int pivotidx = ei;
        int idx = segrigate(arr,pivotidx,si,ei);

        quick(arr,si,idx-1);
        quick(arr,idx+1,ei);
    }

    public static void main(String[] args) {
        int[] arr={5,4,6,3,7,2,9,10,-3};
        quick(arr,0,arr.length-1);

        for(int ele:arr){
            System.out.println(ele+ " ");
        }
    }
}