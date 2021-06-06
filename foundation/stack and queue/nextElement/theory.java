import java.io.*;
import java.util.*;

public class theory{

    // public static void main(String[] args) throws Exception {           // array fill
    // int[] ans=new int[5];
    // Arrays.fill(ans,-1);
    // for(int i=0;i<5;i++){
    //     System.out.println(ans[i]); 
    // }


  public static void display(int[] a){
    StringBuilder sb = new StringBuilder();

    for(int val: a){
      sb.append(val + "\n");
    }
    System.out.println(sb);
  }

public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(br.readLine());
    int[] a = new int[n];
    for(int i = 0; i < n; i++){
       a[i] = Integer.parseInt(br.readLine());
    }

    int[] nge = solve(a);
    display(nge);
 }

 //-------------------------------------------------------------------------------------------------------------//
 //-------------------------------------------------------------------------------------------------------------//

 //-------------------Next Greater Element To The Right

//  public static int[] solve(int[] arr){
//       int n = arr.length;
//    LinkedList<Integer> st = new LinkedList<>();
//         int[] ans = new int[n];
//         Arrays.fill(ans, -1);

//         for (int i = 0; i < n; i++) {
//             while (st.size() != 0 && arr[st.getFirst()] < arr[i]) {
//                 ans[st.removeFirst()] = arr[i];
//             }

//             st.addFirst(i);
//         }
//         return ans;
//  }

// }
//-----------------------------------------------------------------------------------------------------

 //-------------------Next Greater Element To The Right

//  public static int[] solve(int[] arr){
//     int[] ans=new int[arr.length];
//     Stack<Integer> st=new Stack<Integer>();
    
//     st.push(0);
    
//     for(int i=1;i<arr.length;i++){
//         while(st.size()>0 && arr[i]>arr[st.peek()]){
//             int pos=st.peek();
//             ans[pos]=arr[i];
//             st.pop();
//         }
//         st.push(i);
//     }
//     while(st.size()>0){
//         int pos=st.peek();
//          ans[pos]=-1;
//          st.pop();
//     }
//     return ans;
//   }
 
//  }

//-------------------------------------------------------------------------------------------------------------//
//-------------------------------------------------------------------------------------------------------------//

 //-------------------Next smaller Element To The right

    // public static int[] solve(int[] arr){
    //     int n=arr.length;
    //     int[] ans=new int[n];
    //     Stack<Integer> st=new Stack<Integer>();
    //     st.push(n-1);
    //     for(int i=n-2;i>=0;i--){
    //         while(st.size()>0 && arr[i]<arr[st.peek()]){
    //             int pos=st.peek();
    //             ans[pos]=arr[i];
    //             st.pop();
    //         }
    //         st.push(i);
    //     }

    //     while(st.size()>0){
    //         ans[st.peek()]=-1;
    //         st.pop();
    //     }
    //     return ans;
    // }

 //-------------------------------------------------------------------------------------------------------------//
 //-------------------------------------------------------------------------------------------------------------//

 //-------------------Next Greater Element To The left
    
    // public static int[] solve(int[] arr){
    //     int n=arr.length;
    //     int[] ans=new int[n];
        
    //     Arrays.fill(ans, -1);
    //     Stack<Integer> st=new Stack<Integer>();
    //     st.push(n-1);
    //     for(int i=n-2;i>=0;i--){
    //         while(st.size()>0 && arr[i]>arr[st.peek()]){
    //             int pos=st.peek();
    //             ans[pos]=arr[i];
    //             st.pop();
    //         }
    //         st.push(i);
    //     }
       
                
    //     return ans;
    // }

    //-------------------------------------------------------------------------------------------------------------//
 //-------------------------------------------------------------------------------------------------------------//

 //-------------------Next Smaller Element To The left


  public static int[] solve(int[] arr){
        int n=arr.length;
        int[] ans=new int[n];
        
        Arrays.fill(ans, -1);
        Stack<Integer> st=new Stack<Integer>();
        st.push(0);
        
        for(int i=1;i<n;i++){
            while(st.size()>0 && arr[i]<arr[st.peek()]){
                int pos=st.peek();
                ans[pos]=arr[i];
                st.pop();
            }
            st.push(i);
        }
                
        return ans;
    }

}