import java.util.*;
public class basic1{
    //Basic of Manipulation

    public static void maniplation(int n,int i,int j,int k,int m){
        int onmask = (1<<i);
        int ofmask = ~(1<<j);
        int tmask = ~(1<<k);
        int cmask = ~(1<<m);

        System.out.println(n|onmask);                       // on bit
        System.out.println(n&ofmask);                        // off bit
        System.out.println(n^tmask);                         // toggle bit
        System.out.println((n&cmask) > 0 : true : false);    // check bit if 1 -> true , 0-> false 
    }

    //Print Value Of Rsb Mask

    public static void RSB(int n){
        int mask = n & (~n+1);
        System.out.println(Integer.toBinaryString(mask));
    }

    //Kernighans Algorithm

    public static void Kernighans(int n){
        int count = 0;
        while(n>0){
            int mask = n & (~n+1);
            n = n - mask;
            count++;
        } 
    }
    
    //All Repeating Except One

    public static void one(int[] arr){
        int xor = 0 ;
        for(int x : arr){
            xor^=x;
        }
        System.out.println(xor);
    }
    
    //All Repeating Except Two

    public static void two(int[] arr){
        int xor = 0 ;
        for(int x : arr){
            xor^=x;
        }

        int rsb = xor & -xor; //Rsb Mask

        int a=0;
        int b=0;
        for(int x : arr){
            if((x&rsb) ==0 ){
                a^=x;
            }else{
                b^=x;
            }
        }

        System.out.println(Math.min(a,b));
        System.out.println(Math.max(a,b));
    }
    
    //One Repeating And One Missing

    public static void missReapeat(int[] arr){
         int xor = 0;
         for(int x : arr){
             xor ^= x;
         }

         for(int i = 1 ; i <= arr.length;i++){
            xor ^= i;
         }

         int rsb = xor & -xor;          // RSB
         
         int a =0,b=0;
         for(int x : arr){
             if((x&rsb) ==0){
                 a^=x;
             }else{
                 b^=x;
             }
         }

         for(int x = 1 ; x <= arr.length;x++){
            if((x&rsb) ==0){
                a^=x;
            }else{
                b^=x;
            }
         }

         for(int x : arr){
             if(x == a){
                System.out.println("Missing number->" + b);
                System.out.println("Repeating Number->"+ a);
             }
             if(x == b){
                System.out.println("Missing number->" + a);
                System.out.println("Repeating Number->"+ b);
             }
         }
    }
    
    //All Repeating Three Times Except One
    public static void three(int[] arr){
        int o=0;
        int t =0;

        for(int b : arr){
            int oo = (~t & o & ~b) | (~t & ~o & b);
            int tt = (t & ~o & ~b) | (~t & o & b);
            o=oo;
            t=tt;
        }

        System.out.println(o);
        
    }
    
    //Gray Code
    public static List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<>();
        if(n==0){
            ans.add(0);
            return ans;
        }

        List<Integer> res = grayCode(n-1);
        for(Integer v : res){
            ans.add(v);
        }
        int mask = 1<<(n-1);
        for(int i = res.size()-1;i>=0;i--){
            int v = res.get(i);
            ans.add(v|mask);
        }

        return ans;
    }
    
    //Minimum Number Of Software Developers
    static ArrayList<Integer> sol = new ArrayList<>();
    public static void solution(int[] people, int nskills, int cp, ArrayList<Integer> onesol, int skills) {
		if(cp == people.length){
            if(skills == (1<<nskills) - 1){
                if(sol.size() == 0 || sol.size() > onesol.size()) {
                   sol = new ArrayList<>(onesol);
                }
            }
            return;
        }

        solution(people, nskills, cp+1, onesol, skills);  // exclude

        onesol.add(cp);
        solution(people, nskills, cp+1, onesol, skills | people[cp]);  // include
        onesol.remove(onesol.size()-1);
	}
    
    //Xor Of Sum Of All Pairs
    public static int solution(int[] arr){
        int xor = 0;
        for(int n : arr){
            xor ^= 2*n;
        }
        return xor;
     }

     //Pepcoder And Bits

     public static long ncr(long n, long r){
        if(n < r){
            return 0L;    
        }
        
        long res = 1L;
        
        for(long i = 0L; i < r; i++){
            res = res * (n - i);
            res = res / (i + 1);
        }
        
        return res;
    }

     public static long solution2(long n, int k, int i) {
      if(i==0) return 0;

      long mask = 1L << i;
        if((n&mask) == 0){
            return solution2(n, k, i-1);
        }else{
            long setZero = ncr(i,k);
            long setOne = solution2(n, k-1, i-1);
            return setZero + setOne;
        }
        
    }

    //Is A Power Of 2
    public boolean isPowerOfTwo(int n) {
        // int rsb = n & -n;
        // if(n-rsb == 0){
        //     return true;
        // }else{
        //     return false;
        // }

        //======= OR =========== 

        int rsb = n & (n-1);
        if(rsb == 0){
            return true;
        }else{
             return false;
         }
        }
    }

    public boolean isPowerOfFour(int n) {
        int mask =  0b01010101010101010101010101010101;

        return n > 0 && (n&(n-1)) == 0 && (n&mask) > 0 ;
    }

    //Triplets - 1
    public static void solution(int[] arr){
		int ans = 0;
        for(int i = 0 ; i < arr.length ; i++){
            int xor = arr[i];
            for(int j = i+1 ; j < arr.length ; j++){
                xor ^= arr[j];

                if(xor == 0){
                    ans += j-i;
                }
            }
        }
        System.out.println(ans);
    }

    //Reduce N To 1
    public static int solution(int n) {
        int ans = 0;
        long x = n ;
        while(x!=1){
            if(x%2 == 0 ){
                x = x/2;
            }else if((x&0b111)==0b111){
                x = x+1;
            }else{
                x = x-1;
            }
            ans++;
        }
        return ans;
    }
    
    //Abbreviation 1 - Using Bits
    public static void solve(String str){
        int n = str.length();

        int tnp = 1 <<(n) // 2 ^ n;
        int count = 0;
        for(int val = 0 ; val < tnp ;val++){
            StringBuilder sb = new StringBuilder();
            count = 0;
            for(int j = 0 ; j < n ; j++){
                char ch = str.charAt(j);
                int b = n-i-1;
                int mask = (1<<b);
                if((val&mask) == 0){
                    if(count > 0){
                        sb.append(count);
                        count=0;
                    }
                    sb.append(ch);
                }else{
                    count++;
                }
            }
            if(count > 0){
                sb.append(count);
            }
            System.out.println(sb.toString());
        }
    }
    
    //Utf - 8 Encoding
    public static boolean solution(int[] arr) {
       int rem = 0;
       for(int val : arr){
           if(rem == 0){
                if((val>>7) == 0b0){ //0*******
                    rem =0;
                }else if((val>>5) == 0b110){ //110*****
                    rem =1;
                }else if((val>>4) == 0b1110){ //1110****
                    rem =2;
                }else if((val>>3) == 0b11110){ //11110****
                    rem =3;
                }else{
                    return false;
                }
           }else{
                if((val >> 6) == 0b10){
                    rem--;
                }else{
                    return false
                }
           }
       }
        
        return rem==0;
    }
    
    public static void main(String[] args) {
        
    }
}