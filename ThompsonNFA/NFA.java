package nfa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * @author soumya
 */
public class NFA {
   
    LinkedList<State> s = new LinkedList<State>();
    Fragment frag;
    static Stack operatorStack = new Stack();
	
 
   //postfix expression to NFA 
   public static State postToNfa(String postfix){
       int i;
       char ch;
       Fragment lastFragment2 = null;
       Fragment lastFragment1 = null;
       State newState = null;
       Stack<Fragment> fragmentStack = new Stack<Fragment>();
       Fragment FinalNfa = new Fragment();
       State matchstate = new State();
       for(i=0;i<postfix.length();i++){
           ch = postfix.charAt(i);
           switch(ch){
               case '.': lastFragment2 = fragmentStack.pop();
                         lastFragment1 = fragmentStack.pop();
                         patchFragments(lastFragment1, lastFragment2.getStart());
                         fragmentStack.push(new Fragment(lastFragment1.getStart(), lastFragment2.getOut() ) );
                         
                   break;
               
               case '|': lastFragment2 = fragmentStack.pop();
                         lastFragment1 = fragmentStack.pop();
                         newState = new State(lastFragment1.getStart(), lastFragment2.getStart());
                         fragmentStack.push(new Fragment(newState, appendArrows(lastFragment1.getOut(), lastFragment2.getOut())));
                   break;
                  
               case '*': lastFragment1 = fragmentStack.pop();
                         newState = new State(lastFragment1.getStart(), null);
                         patchFragments(lastFragment1, newState);
                         fragmentStack.push(new Fragment(newState, newState));
                   break;
                   
               case '+': lastFragment1 = fragmentStack.pop();
                         newState = new State(lastFragment1.getStart(),null);
                         patchFragments(lastFragment1, newState);
                         fragmentStack.push(new Fragment(lastFragment1.getStart(),newState));
                   break;
                   
               default: State literalState = new State(ch);
                        fragmentStack.push(new Fragment(literalState, literalState));
                   
           }
       }
       FinalNfa = fragmentStack.pop();
       patchFragments(FinalNfa, matchstate);
       return FinalNfa.getStart();
   }
   
   
   private static void patchFragments(Fragment frag, State s){
        ArrayList<State> toBePatched = frag.getOut();
        for (int i = 0; i < toBePatched.size(); i++){
            State openarrows = toBePatched.get(i);
            openarrows.patch(s);
        }
    }

    private static ArrayList<State> appendArrows(ArrayList<State> a, ArrayList<State> b){
        ArrayList <State> appended = new ArrayList <State> ();
        for (int i = 0; i < a.size(); i++){
            appended.add(a.get(i));
        }
        for (int i = 0; i < b.size(); i++){
            appended.add(b.get(i));
        }
        return appended;
    }
        
    //convert regular experssion to postfix 
    public static String infixToPostfix(String infix){
        
        char ch;
        int i;
        String postfix = "";
        for(i=0;i<infix.length();i++){
            ch= infix.charAt(i);
            if(Character.isLetter(ch))
               postfix = postfix+ch;
            else if(ch == '('){
                
                operatorStack.push(ch);
            }
            else if (ch==')')
            {
                if(!operatorStack.isEmpty()){
                while ((char)operatorStack.peek() != '(')
                {
                        postfix = postfix + operatorStack.pop();
                }
                operatorStack.pop();
                }
            }
            else
            {
                 int precedenceVal=0;
                if(!operatorStack.isEmpty()){
               
                try{
                precedenceVal = precedence(ch, (char)operatorStack.peek());
                }catch(EmptyStackException ex){
                    ex.printStackTrace();
                    }
                }
                while (!operatorStack.isEmpty() && !((char)operatorStack.peek()=='(') && precedenceVal<=0)
                    postfix = postfix + operatorStack.pop();

                operatorStack.push(ch);
                
            }
            
        }
        while (!operatorStack.isEmpty())
            postfix = postfix + operatorStack.pop();
	
        return postfix;
        
    }
    
   //assign integers for precedence 
   private static int precedence(char a, char b){
        if(a=='*'||a=='+'){
            if(b=='*'||b=='+')
                return 0;
            else
                return 1;
        }
        
        else if(a=='.'){
            if(b=='*'||b=='+')
                return -1;
            else if (b == a)
                return 0;
            else
                return 1;
        }

        else if(a=='|'){
            if(a == b)
                return 0;
            else return -1;
        }
        return -2;
   }

    
    public static void main(String[] args) throws IOException {
     
     String infix;
     BufferedReader keyboard = new BufferedReader (new InputStreamReader(System.in));
     
     //input infix expression
     System.out.print("\nEnter expression in infix: ");
     infix = keyboard.readLine();
     String postfix = "";
     postfix = infixToPostfix(infix);
     System.out.println("The expression in postfix is:" + postfix);
     
     State startState = postToNfa(postfix);
     //inputstring for simulation
     System.out.println("\nEnter a string for simulation: ");
     String str = keyboard.readLine();
     
     Boolean matches = NfaSimulation.match(startState, str);
     if(matches)
        System.out.println("t's a match");
     else
        System.out.println("Not a match");
                       
    }
    
}
