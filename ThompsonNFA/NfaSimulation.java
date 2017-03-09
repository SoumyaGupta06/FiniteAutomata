/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nfa;

import java.util.ArrayList;

/**
 *
 * @author soumya
 */

//simulate the nfa to match a string input
public class NfaSimulation {
    
    
    public static boolean match(State startstate, String input){
        
        ArrayList <State> cList = new ArrayList<State> ();
        ArrayList <State> nList = new ArrayList<State> ();
        int listID = 0;
        addState(cList, startstate, listID);
        for (int i = 0; i < input.length(); i++){
            char c = input.charAt(i);
            listID = step(cList, c, nList, listID);
            cList = nList;
            nList = new ArrayList<State>();
        }
        return isMatch(cList);
        
    }
    
    public static boolean isMatch(ArrayList<State> list){
        for (int i = 0; i < list.size(); i++){
            State s = list.get(i);
            if (s.isMatch()){
                return true;
            }
        }
        return false;
    }
    private static int step(ArrayList<State> currentList, char c, ArrayList<State> nextList, int listID){
        listID++;
        for (int i = 0; i < currentList.size(); i++){
            State s = currentList.get(i);
            if(c == s.getChar()){
                addState(nextList, s.getOut1(), listID);
            }
        }
        return listID;
    }
    private static void addState(ArrayList<State> list, State s, int listID){
        if (s == null || s.getLastlist() == listID)
            return;
        s.setLastlist(listID);
        if(s.isSplit()){
            addState(list, s.getOut1(), listID);
            addState(list, s.getOut2(), listID);
            return;
        }
        list.add(s);
    }
}
