  public class heapSort {
     //  increasing for (true)
    private static Boolean compareTo(int a,int b,Boolean isMax){
        if(isMax){
            return a>b;
        }else{
            return b>a;
        }

    }

    public static void swap(int[] arr ,int i,int j){
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
  
    public static void heapify(int arr[], int n, int pi,Boolean isMax) {
      // Find largest among root, left child and right child
      int maxIdx = pi;
      int l = 2 * pi + 1;
      int r = 2 * pi + 2;
  
    //   if (l < n && arr[l] > arr[maxIdx])    // for decreasing use(<) instead of(>)
    //   maxIdx = l;
  
    //   if (r < n && arr[r] > arr[maxIdx])
    //   maxIdx = r;

      if (l < n && compareTo(arr[l],arr[maxIdx],isMax))    
            maxIdx = l;
  
      if (r < n && compareTo(arr[r],arr[maxIdx],isMax))
            maxIdx = r;
  
      // Swap and continue heapifying if root is not largest
      if (maxIdx != pi) {
        swap(arr,pi,maxIdx);
        heapify(arr, n, maxIdx,isMax);
      }
    }
  
    // Driver code
    public static void main(String args[]) {
      int arr[] = { 10, 20, 30, -2, -3, -4, 5, 6, 7, 8, 9, 22, 11, 13 };
  
      //heapSort hs = new heapSort();
     
      int n = arr.length;

      for (int i = n / 2 - 1; i >= 0; i--) {
        heapify(arr, n, i,false );  // true incresing
      }
  
      // Heap sort
      for (int i = n - 1; i >= 0; i--) {
        swap(arr,0,i);
  
        // Heapify root element
        heapify(arr, i, 0,false);
      }

      for (int i = 0; i < n; ++i)
        System.out.print(arr[i] + " ");
      System.out.println();
    }
  }
