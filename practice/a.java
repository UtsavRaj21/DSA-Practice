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

    if(1=="1"){
        System.out.println("true");
    }else{
        System.out.println("false");
    }
    // System.out.println("moves");
       

  }

}
