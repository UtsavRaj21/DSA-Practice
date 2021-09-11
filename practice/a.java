public class a {
    
    public static int solve(int pac){
        int sum = 1,prev = 1;
        while(pac > 1){
           for(int i = 2;i <= pac;i++){
               if(pac % i == 0){
                   pac = pac/i;
                   sum += prev*i;
                   prev = prev*i;
                   break;
               }
           }
        }
        return sum;
    }

   public static void main(String args[] ) throws Exception {
   int n=1;
   int moves = 0;
   while(n-->0){
       int ni = 6;
       int m = solve(ni);
       moves+=m;
    //    System.out.println(ni);
   }

   System.out.println(moves);
       

  }

}
