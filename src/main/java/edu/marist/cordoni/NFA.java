package edu.marist.cordoni;

import java.util.ArrayList;

/*
 * A nondeterministic finite-state automaton.
 * We use a backtracking-search
 * approach.
 */
public class NFA {

    static String traversalResult = "";

    public static Graph nfa;

    static State currentState;

    static State rootState;

    
    //import the array from the Regex class 
    public static void createNFA(ArrayList<Character> regularExpression) {

        State currentState = new State();

        State rootState = new State();

        nfa = new Graph();

        State tempStateTo = new State();

        State tempStateFrom = new State();

        int j = 0;


        System.out.println(" ");
        System.out.println("Regular Expression Array: " + RegEx.regExpressionArray.toString());
        System.out.println(" ");
        System.out.println("Start Creation of NFA");

        for(int i = 0; i < RegEx.regExpressionArray.size(); i++){

            //if there is parenthesis in the regular expression
            if(RegEx.regExpressionArray.get(i).compareTo('(') == 0){

                currentState.setLeftParenFlag(1);


            }//if

            else if(RegEx.regExpressionArray.get(i).compareTo(')') == 0){

                currentState.setRightParenFlag(1);


            }//else if

            //if there is an "or" in the regular expression
            else if((RegEx.regExpressionArray.get(i).compareTo('+') == 0) | (RegEx.regExpressionArray.get(i).compareTo('|') == 0)){

                Transition newTransition = new Transition();

                newTransition.setId(RegEx.regExpressionArray.get(i+1).toString());
                newTransition.setFrom(tempStateFrom);
                newTransition.setTo(tempStateTo);

                nfa.transitions.add(newTransition);

            }//else if


            //if we have a kleene star in the regular expression
            else if(RegEx.regExpressionArray.get(i).compareTo('^') == 0){

                //leave blank

            }//else if

            else if(RegEx.regExpressionArray.get(i).compareTo('*') == 0){

                Transition newTransition = new Transition();

                newTransition.setId("epsilon");
                newTransition.setFrom(tempStateTo);
                newTransition.setTo(tempStateFrom);
                tempStateFrom.setAccepts(true);
                tempStateTo.setAccepts(false);

                nfa.transitions.add(newTransition);

            }//else if

            //else we have a terminal symbol in our regular expression
            else{

                State newStateFrom = new State();
                State newStateTo = new State();

                Transition newTransition = new Transition();

                if(j == 0){

                    //create our start state
                    newStateFrom.setId("q0");
                    newStateFrom.setAccepts(false);
                    rootState = newStateFrom;
                    j++;

                    newStateTo.setId("q1");
                    newStateTo.setAccepts(true);
                    currentState = newStateTo;
                    j++;

                    newStateFrom.setNext(newStateTo);
                    newStateTo.setParent(newStateFrom);
                }//if

                else{

                    //create our start state
                    newStateFrom.setId("q" + j);
                    newStateFrom.setAccepts(false);
                    rootState = newStateFrom;
                    j++;

                    newStateTo.setId("q"+ j);
                    newStateTo.setAccepts(true);
                    currentState = newStateTo;
                    j++;

                    newStateFrom.setNext(newStateTo);
                    newStateTo.setParent(newStateFrom);

                }//else
                

                newTransition.setId(RegEx.regExpressionArray.get(i).toString());
                newTransition.setFrom(newStateFrom);
                newTransition.setTo(newStateTo);

                nfa.states.add(newStateFrom);
                nfa.states.add(newStateTo);

                nfa.transitions.add(newTransition);

                tempStateFrom = newStateFrom;
                tempStateTo = newStateTo;

            }//else

            

        }//for

        for(int i = 0 ; i < nfa.states.size(); i++){

            System.out.println("State Id: " + nfa.states.get(i).getId());
            System.out.println("Accepts: " + nfa.states.get(i).getAccepts());

            if(nfa.states.get(i).getNext() != null){
                System.out.println("Next: " + nfa.states.get(i).getNext().getId());
            }//if

            else{
                System.out.println("Next: " + nfa.states.get(i).getNext());
            }//else
            
            System.out.println(" ");

        }//for


        for(int i = 0 ; i < nfa.transitions.size(); i++){

            System.out.println("Transition Id: " + nfa.transitions.get(i).getId());
            System.out.println("From: " + nfa.transitions.get(i).getFrom().getId());
            System.out.println("To: " + nfa.transitions.get(i).getTo().getId());
            
            System.out.println(" ");

        }//for

    }//createNFA


    /*
    * Test whether there is some path for the NFA to reach
    * an accepting state, from the given state and reading
    */
    
    public static boolean accepts(String testline){

        boolean accepts = false;

        State stateCurrent = nfa.states.get(0);

        int j = 0;

        String[] testArray = new String[testline.length()];

        try {

            testArray = testline.split("");

            for(int i = 0 ; i < testArray.length; i++){

                if (testArray[i].compareToIgnoreCase(nfa.transitions.get(j).getId()) == 0){

                    stateCurrent = nfa.transitions.get(j).getTo();
                    j++;

                    //for strings of length 1 made up of the accepting char
                    if(testArray.length == 1){
                        accepts = true;
                    }//

                }//If

                //no transitions left going to look for epsilon
                if(nfa.transitions.get(j).getId().compareToIgnoreCase("epsilon") == 0){

                    if(stateCurrent.getAccepts() == true){
                        accepts = true;
                    }//if

                    else{
                        stateCurrent = nfa.transitions.get(j).getTo();
                    
                        if(stateCurrent.getAccepts() == true){
                            accepts = true;
                        }//if

                    }//else

                }//else
                
    
            }//for

        }//try

        catch (ArrayIndexOutOfBoundsException ex) {

            System.out.println("Invalid alphabet symbol");
            return false; // no transition, just reject

        }//catch

        return accepts; // all moves fail, return false
    }//accepts
    

}//NFA Class