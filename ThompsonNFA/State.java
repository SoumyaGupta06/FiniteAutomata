/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nfa;

/**
 *
 * @author soumya
 */
public class State {
    
    private char c;
    private State out1;
    private State out2;
    private boolean split;
    private boolean match;
    private boolean isChar;
    private int lastlist;
    
    //Initializes a new match state with no outgoing transitions
    public State(){
        
        this.out1 = null;
        this.out2 = null;
        this.isChar = false;
        this.split = false;
        this.match = false;
         this.lastlist = -1;
    }
    /**
     * c is the label for outgoing transition
     */
    public State(char c){
        this.c = c;
        this.out1 = null;
        this.out2 = null;
        this.isChar = true;
        this.split = false;
        this.match = false;
        this.lastlist = -1;
    }
    
    // Initialize a new split state with two outgoing transitions
    public State(State out1, State out2){
        this.out1 = out1;
        this.out2 = out2;
        this.isChar = false;
        this.split = true;
        this.match = false;
        this.lastlist = -1;
    }
    
    public char getChar(){
        return c;
    }
    public boolean isLiteral(){
        return isChar;
    }
    public State getOut1(){
        return out1;
    }
    
     public State getOut2(){
        return out2;
    
    }
     
     public boolean isSplit(){
        return split;
    }
    
    public boolean isMatch(){
        return match;
    }
    public int getLastlist(){
        return lastlist;
    }
    public void setLastlist(int listindex){
        lastlist = listindex;
    }
    public void patch(State s){
        if(out1 == null)
            out1 = s;
        if(out2 == null && split)
            out2 = s;
    }
}
