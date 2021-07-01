public class a {
    static int count =1;
    public static void fun(String s){
        
        for(int i=0;i<s.length()-1;i++){
            for(int j=i;j<s.length();j++){
                if(s.charAt(i)!=s.charAt(j)){
                    count++;
                }
            }
        }
    }
    public static void main(String[] args) {
        String s = "stp";
        fun(s);
        System.out.println(count);
    }

}
