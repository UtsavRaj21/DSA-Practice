public class basic{

    public static boolean prime(int n){
        for(int i = 2 ; i*i <=n ;i++){
            if(n%i==0){
                return false;
            }
        }
        return true;
    }

    public static void solve(int a,int b,int n){
        int count =0;
        for(int i = a ; i <= b ; i++){
            if(prime(i)){
                int num = i;
                int sum = 0;
                while(num!=0){
                    sum+=num%10;
                    num = num/10;
                }
                if(sum % n == 0){
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        if(n<2) return -1;
        
        int min1 = arr[0];
        int min2 = (int)1e9;

        for(int i = 0 ; i< n ; i++){
            if(min1 > arr[i]){
                min2 = min1;
                min1 = arr[i];
            }else if(min2 > arr[i]){
                min2 = arr[i];
            }
        }

        if(min1+min2 <= sum){
            return min1*min2;
        }
        return 0;
    }
}