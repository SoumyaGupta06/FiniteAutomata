/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nfa;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author soumya
 */
public class Fragment {
    
    private State start;
    private ArrayList<State> out;

   
    public Fragment(){
        this.out = null;
        this.start = null;
    }
    
    //Initializes a new Fragment with starting state and one outwards-pointing state
    public Fragment(State start, State out){
        this.out = new ArrayList<State>();
        this.out.add(out);
        this.start = start;
    }
   
    //Initializes a new Fragment with starting state and an array of outwards-pointing states
    public Fragment(State start, ArrayList outArrows){
        this.out = outArrows;
        this.start = start;
    }

    public State getStart() {
        return start;
    }

    public ArrayList<State> getOut() {
        return out;
    }

    @Override
    public String toString() {
        return "\nstartState: "+this.start+" outArrowList: "+this.out;
    }
    
    
}
