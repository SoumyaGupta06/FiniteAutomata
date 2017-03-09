
package stringmatchautomata;
import java.util.*;

/**
 *
 * @author srilakshmi
 */
public class StringMatchAutomata {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your text :- ");
        String txt = in.next();
        System.out.println("\nEnter the pattern you are searching for :- ");
        String pattern = in.next(); 
        search(pattern, txt);
    }    

public static int nextState(String pattern, int M, int state, int x)
{
   //if input character is the same as the next one in your pattern
  // progress to next state
  if (state < M && x == pattern.charAt(state))
     return state+1;
 
  int ns, i; 

  for (ns = state; ns > 0; ns--)
  {
	  if(pattern.charAt(ns-1) == x)
	  {
		  for(i = 0; i < ns-1; i++)
		  {
			  if (pattern.charAt(i) != pattern.charAt(state-ns+1+i))
			  break;
		  }
		  if (i == ns-1)
		  return ns;
	  }
  }
 
  return 0;
}
 

public static void transTable(String pattern, int M, int[][] TF)
{
  int state, x;
  for (state = 0; state <= M; ++state)
  for (x = 0; x < 256; ++x){
    TF[state][x] = nextState(pattern, M, state, x);
    if(TF[state][x] == 1)
       System.out.println("for state "+state+" matching input (ASCII code)-"+x+" transition to state "+TF[state][x]);
   // System.out.println("for state "+state+" matching input (ASCII code)-"+x+" transition to state "+TF[state][x]);
  }
}
 

public static void search(String pattern, String txt)
{
  int M = pattern.length();
  int N = txt.length();
 
  int[][] TF = new int[M+1][256];
 
  transTable(pattern, M, TF);
 
  int i, state=0;
  for (i = 0; i < N; i++)
  {
    state = TF[state][txt.charAt(i)];
    if (state == M){
        //System.out.println("for character at "+state+" matching i/p (ASCII code)-"+txt.charAt(i)+" transition to state "+TF[state][txt.charAt(i)]);
      System.out.println("\n Pattern is present at index "+ (i-M+2));
    }
    
  }
}
    
}
