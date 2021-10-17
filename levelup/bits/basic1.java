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
    
    
    public static void main(String[] args) {
        
    }
}