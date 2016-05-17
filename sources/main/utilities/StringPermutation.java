package utilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
/**
 * @author Arushi
 *
 */
 
public class StringPermutation 
{
	static String username[];
	static int count;
	static ArrayList<String> usernames;
	public static ArrayList<String> generatePermutations(String...s){
		username=new String[18];
		usernames=new ArrayList<String>();
		count=0;
		permute(0,s);
		placeDots(s);
		breakString(s);
		//List<String> temp = Arrays.asList(username);
        //usernames = new ArrayList<String>();
	    //usernames.addAll(temp); 
		for(String x:username){
			if(x!=null){
				usernames.add(x.toLowerCase());
			}
		}
        return usernames;
	}
	
    static void permute(int k,String...a) //send value of k as 0 initially
    {
        if (k == a.length) 
        {
        	
            for (int i = 0; i < a.length; i++) 
            {
                //System.out.print(a[i]);
            	if(i==0){
            		if(StringFormatter.isInt(a[i].substring(0, 1))==false)
            		username[count]=a[i];
            		}
            	else{
            		if(username[count]!=null)
            		username[count]=username[count]+a[i];}
              }
            //System.out.println();
            count++;
        } 
        else 
        {
            for (int i = k; i < a.length; i++) 
            {
                String temp = a[k];
                a[k] = a[i];
                a[i] = temp;
 
                permute(k+1,a);
 
                temp = a[k];
                a[k] = a[i];
                a[i] = temp;
            }
        }
        
    }
    public static void placeDots(String...s){
    	
    	if((s[0]+"."+s[1]).length()>=6){username[count++]=s[0]+"."+s[1];}
    	if((s[1]+"."+s[0]).length()>=6){username[count++]=s[1]+"."+s[0];}
    	if((s[0]+s[1]).length()>=6){username[count++]=s[0]+s[1];}
    	if((s[1]+s[0]).length()>=6){username[count++]=s[1]+s[0];}
    	
    	
    	
    }
    public static void breakString(String...s){
    	String s1[]=new String[s.length-1];
    	for(int i=0;i<s1.length-1;i++){
    		s1[i]=String.valueOf(s[i].charAt(0));
    		//System.out.println(s1[i]);
    	}
    	//System.out.println(s1.length);
    	for(int i=0;i<s1.length-1;i++){
    		for(int j=0;j<s.length-1;j++){
    			if(i!=j){
    				String str1=s1[i]+s[j];
    				String str2=s1[i]+s[j]+s[j+1];
    			if(str1.length()>=6)	
    			username[count++]=s1[i]+s[j];
    			if(j!=s.length-1 && str2.length()>=6)
    			username[count++]=s1[i]+s[j]+s[j+1];
    			}
    		}
    	}
    }
 
    public static void main(String args[]) 
    {
        
        Scanner sc = new Scanner(System.in);
        //System.out.println("Enter the length of list: ");
        //int N = sc.nextInt();
        //String[] sequence = new String[N];
        //System.out.println("enter firstname,lastname and id");
 
        //for (int i = 0; i < N; i++)
        	//sequence[i]=sc.next();
        
          System.out.println("\nThe permuted sequences are: ");
          
          generatePermutations("Om","Prakash","1996");
          Iterator list=usernames.iterator();
          while(list.hasNext()){
        	  System.out.println(list.next());
          }
          
       // permute(0,"arushi","gupta","1012");
       // System.out.println(count);
       //placedots("arushi","gupta","1012");
        
      /*  
        for(int i=0;i<count;i++){
        	
        		System.out.print(username[i]);
        	
        	System.out.println();
        	
        }*/
 
        sc.close();
    }
}