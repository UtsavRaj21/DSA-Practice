import java.io.*;
import java.util.*;
import java.lang.*;

public class main {
    
    public static void fun(String str, ArrayList<String> perm)
    {
        
       
    }
	public static void main (String[] args) {
		String str = "ABC";
         ArrayList<String> perm=new  ArrayList<String>();
         perm.add("");

         for(int j=str.length()-1;j>=0;j--){
            ArrayList<String> ans=new  ArrayList<String>();
            char ch=str.charAt(j);
            for(String s:perm){
                for(int i=0;i<=s.length();i++){
                    ans.add(s.substring(i)+ch+s.substring(0,i));
                }
            }
            perm=ans;
        }
        Collections.sort( perm );
         for(String s:perm){
             if(s.charAt(1)!='B'){
                System.out.println(s);
             }
             
         }

		
		
	}
}