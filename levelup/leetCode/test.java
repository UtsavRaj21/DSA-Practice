import java.util.Scanner;

class Another{
    public void solve(int[] arr){
        for(int ele : arr){
            System.out.print(ele+" -> ");
        }

    }
}
public class test{

    public static void main(String[] args) throws Exception{
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for(int i = 0 ; i < n ; i++){
            arr[i] = scn.nextInt();
        }

        Another a = new Another();
        a.solve(arr);

        
    }
    
}