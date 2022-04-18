package edu.marist.cordoni;

import java.util.ArrayList;

/*
 * A nondeterministic finite-state automaton.
 * We use a backtracking-search
 * approach.
 */
public class NFA {

    
    //import the array from the Regex class 
    public static void createNFA(ArrayList<Character> regularExpression) {

        Graph nfa = new Graph();

        State tempStateTo = new State();

        State tempStateFrom = new State();

        State currentState = new State();


        System.out.println(" ");
        System.out.println("Regular Expression Array: " + RegEx.regExpressionArray.toString());
        System.out.println(" ");
        System.out.println("Start Creation of NFA");

        for(int i = 0; i < RegEx.regExpressionArray.size(); i++){

            //if there is parenthesis in the regular expression
            if(RegEx.regExpressionArray.get(i).compareTo('(') == 0){

                tempStateTo = currentState;


            }//if

            else if(RegEx.regExpressionArray.get(i).compareTo(')') == 0){

                tempStateFrom = currentState;


            }//else if

            //if there is an "or" in the regular expression
            else if((RegEx.regExpressionArray.get(i).compareTo('+') == 0) | (RegEx.regExpressionArray.get(i).compareTo('|') == 0)){




            }//else if


            //if we have a kleene star in the regular expression
            else if(RegEx.regExpressionArray.get(i).compareTo('^') == 0){




            }//else if

            else if(RegEx.regExpressionArray.get(i).compareTo('*') == 0){

                Transition newTransition = new Transition();

                newTransition.setId("epsilon");
                newTransition.setFrom(tempStateFrom);
                newTransition.setTo(tempStateTo);


            }//else if

            //else we have a terminal symbol in our regular expression
            else{

                State newStateFrom = new State();
                State newStateTo = new State();

                Transition newTransition = new Transition();

                newStateFrom.setId("q0");
                newStateTo.setId("q1");

                newTransition.setId(RegEx.regExpressionArray.get(i).toString());
                newTransition.setFrom(newStateFrom);
                newTransition.setTo(newStateTo);

                nfa.states.add(newStateFrom);
                nfa.states.add(newStateTo);

                nfa.transitions.add(newTransition);

                currentState = newStateTo;

            }//else

            

        }//for



        //System.out.println(Graph.states.)

    }//createNFA



    //do I then try to make it look like the delta function below?

    /*
    * The transition function represented as an array.  The
    * entry at delta[s,c-'0'] is an array of 0 or more ints,
    * one for each possible move from state s on character c.
    */
    private static int[][][] delta = 
        {{{0,1},{0}}, // delta[q0,0], delta[q0,1]
        {{2},{}},    // delta[q1,0], delta[q1,1]
        {{2},{2}}};  // delta[q2,0], delta[q2,1]

    /**
    * Test whether there is some path for the NFA to reach
    * an accepting state, from the given state and reading
    * the given string at the given character position.
    * @param s the current state
    * @param in the input string
    * @param pos the index of the next character in the string
    * @return true iff the NFA accepts on some path
    */
    private static boolean accepts(int s, String in, int pos) {

        if (pos==in.length()) { // if no more symbols to read
      
        return (s==2); // accept iff final state is q2
    
        }//if

        char c = in.charAt(pos++); // get char and advance

        int[] nextStates;

        try {

            nextStates = delta[s][c-'0'];
            System.out.print("\u03B4(q"+s+", "+c+ ") -> ");

            for (int i:nextStates) {

                System.out.println("q"+i);
      
            }//for

        }//try

        catch (ArrayIndexOutOfBoundsException ex) {

            System.out.println("Invalid alphabet symbol");
            return false; // no transition, just reject

        }//catch

        // At this point, nextStates is an array of 0 or
        // more next states.  Try each move recursively;
        // if it leads to an accepting state return true.

        for (int i=0; i < nextStates.length; i++) {

            if (accepts(nextStates[i], in, pos)) return true;

        }//for

        return false; // all moves fail, return false
    }//accepts


    /**
    * Test whether the NFA accepts the string.
    * @param in the String to test
    * @return true iff the NFA accepts on some path
    */
    public static boolean accepts(String in) {

        return accepts(0, in, 0); // start in q0 at char 0

    }//accepts

}//NFA Class