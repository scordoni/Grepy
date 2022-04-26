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

    static ArrayList <String> alphabet = new ArrayList <String>();

    
    //import the array from the Regex class 
    public static void createNFA(ArrayList<Character> regularExpression) {

        State currentState = new State();

        State rootState = new State();

        nfa = new Graph();

        State tempStateTo = new State();

        State tempStateFrom = new State();

        Transition tempTransition = new Transition();

        ArrayList <State> temp = new ArrayList <State>();

        alphabet = new ArrayList <String>();

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
            else if((RegEx.regExpressionArray.get(i).compareTo('+') == 0) || (RegEx.regExpressionArray.get(i).compareTo('|') == 0)){

                

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

                tempStateTo.transitions.add(newTransition);

                tempTransition.setNext(newTransition);

                tempTransition = newTransition;

            }//else if

            //else we have a terminal symbol in our regular expression
            else{

                //add to our alphabet
                alphabet.add(RegEx.regExpressionArray.get(i).toString());

                State newStateFrom1 = new State();
                State newStateTo1 = new State();

                Transition newTransition = new Transition();

                if(j == 0){

                    //create our start state
                    newStateFrom1.setId("q0");
                    newStateFrom1.setAccepts(false);

                    rootState = newStateFrom1;

                    j++;

                    newStateTo1.setId("q1");
                    newStateTo1.setAccepts(true);
                    currentState = newStateTo1;
                    j++;

                    newStateFrom1.setNext(newStateTo1);
                    newStateTo1.setParent(newStateFrom1);

                    newTransition.setId(RegEx.regExpressionArray.get(i).toString());
                    newTransition.setFrom(newStateFrom1);
                    newTransition.setTo(newStateTo1);

                    nfa.states.add(newStateFrom1);
                    nfa.states.add(newStateTo1);

                    newStateFrom1.transitions.add(newTransition);

                    tempStateFrom = newStateFrom1;
                    tempStateTo = newStateTo1;
                    tempTransition = newTransition;
                }//if

                else{

                    State newStateFrom2 = new State();
                    State newStateTo2 = new State();

                    //create our start state
                    newStateFrom2.setId("q" + j);
                    newStateFrom2.setAccepts(false);
                    
                    j++;

                    newStateTo2.setId("q"+ j);
                    newStateTo2.setAccepts(true);
                    currentState = newStateTo2;
                    j++;

                    newStateFrom2.setNext(newStateTo2);
                    newStateTo2.setParent(newStateFrom2);

                    newTransition.setId(RegEx.regExpressionArray.get(i).toString());
                    newTransition.setFrom(newStateFrom2);
                    newTransition.setTo(newStateTo2);

                    nfa.states.add(newStateFrom2);
                    nfa.states.add(newStateTo2);

                    newStateFrom2.transitions.add(newTransition);

                    tempStateFrom = newStateFrom2;
                    tempStateTo = newStateTo2;
                    tempTransition = newTransition;

                }//else
                


                if((i != 0) && ((RegEx.regExpressionArray.get(i-1).compareTo('+') == 0) || (RegEx.regExpressionArray.get(i-1).compareTo('|') == 0))){
                    //create new root state
                    State newStateroot = new State();

                    newStateroot.setId("q");
                    newStateroot.setAccepts(false);


                    Transition newTransition1 = new Transition();

                    newTransition1.setId("epsilon");
                    newTransition1.setFrom(newStateroot);
                    newTransition1.setTo(tempStateFrom);

                    Transition newTransition2 = new Transition();

                    newTransition2.setId("epsilon");
                    newTransition2.setFrom(newStateroot);
                    newTransition2.setTo(rootState);



                    //newTransition.setId(RegEx.regExpressionArray.get(i+1).toString());
                    //newTransition.setFrom(tempStateFrom);
                    //newTransition.setTo(tempStateTo);

                    newStateroot.transitions.add(newTransition1);
                    newStateroot.transitions.add(newTransition2);

                    temp.add(newStateroot);

                    for(int h = 0; h < nfa.states.size(); h++){

                        temp.add(nfa.states.get(h));
                    }//for


                    nfa.states.clear();

                    nfa.states = temp;

                    //tempTransition.setNext(newTransition);

                    //tempTransition = newTransition;

                }

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

            System.out.println("Number of transitions from this state: " + nfa.states.get(i).transitions.size());

            System.out.println(" ");

            for(int m = 0 ; m < nfa.states.get(i).transitions.size(); m++){

                System.out.println("Transition Id: " + nfa.states.get(i).transitions.get(m).getId());
                System.out.println("From: " + nfa.states.get(i).transitions.get(m).getFrom().getId());
                System.out.println("To: " + nfa.states.get(i).transitions.get(m).getTo().getId());
                
                System.out.println(" ");
    
            }//for

        }//for

        /*
        for(int i = 0 ; i < nfa.transitions.size(); i++){

            System.out.println("Transition Id: " + nfa.transitions.get(i).getId());
            System.out.println("From: " + nfa.transitions.get(i).getFrom().getId());
            System.out.println("To: " + nfa.transitions.get(i).getTo().getId());
            
            System.out.println(" ");

        }//for
        */

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

            
            //go through each letter in the test array to see if the NFA accepts
            for(int i = 0 ; i < testArray.length; i++){

                /*
                System.out.println(" ");
                System.out.println("current state id " + stateCurrent.getId());
                System.out.println(" ");
                
                System.out.println(" ");
                System.out.println("current transition id " + stateCurrent.transitions.get(j).getId());
                System.out.println(" ");
                */

                //if the char is not in our alphabet then we reject
                if(alphabet.toString().contains(testArray[i]) == false){

                    accepts = false;
                    return accepts;

                }//if

                //if the char matches our first transition we follow to the next state
                if (testArray[i].compareToIgnoreCase(stateCurrent.transitions.get(j).getId()) == 0){

                    /*
                    System.out.println(" ");
                    System.out.println("hello");
                    System.out.println(" ");

                    System.out.println(" ");
                    System.out.println("current state id " + stateCurrent.getId());
                    System.out.println(" ");
                    */

                    //if we are in an accepting state
                    if(stateCurrent.getAccepts() == true){
                        accepts = true;
                    }//if

                    //if we are not in an accepting state
                    if(stateCurrent.getAccepts() == false){
                        accepts = false;
                    }//if

                    //for strings of length 1 made up of the accepting char
                    if(testArray.length == 1){
                        accepts = true;
                        return accepts;
                    }//if

                    //if our next transtion is not null then we move to the next state.
                    if(stateCurrent.transitions.get(j).getNext() != null){

                        /*
                        System.out.println(" ");
                        System.out.println("1 transtion state to id " + stateCurrent.transitions.get(j).getTo().getId());
                        System.out.println(" ");
                        */

                        stateCurrent = stateCurrent.transitions.get(j).getTo();
                        
                        /*
                        System.out.println(" ");
                        System.out.println("after increment new state id " + stateCurrent.getId());
                        System.out.println(" ");
                        */
                    }//if

                    //else we return accepts
                    else{
                        
                        return accepts;
                    }//else

                }//If

                //no transitions left going to look for epsilon
                else if(stateCurrent.transitions.get(j).getId().compareToIgnoreCase("epsilon") == 0){

                    /*
                    System.out.println(" ");
                    System.out.println("current state id " + stateCurrent.getId());
                    System.out.println(" ");
                    */

                    //if we are in an accepting state then we can accept
                    if(stateCurrent.getAccepts() == true){
                        accepts = true;
                    }//if

                    //else we more to the next state 
                    else{

                        //System.out.println("current");

                        stateCurrent = stateCurrent.transitions.get(j).getTo();

                        //System.out.println("current state id " + stateCurrent.getId());
                        
                        //if we are in an accepting state then we can accept
                        if(stateCurrent.getAccepts() == true){
                            accepts = true;
                        }//if

                    }//else

                    //if the next state not not have any transitions then we reject the testline and return
                    if(stateCurrent.transitions.get(j).getTo().transitions == null){
                       
                        accepts = false;
                        return accepts;
                        
                    }//if

                    //if the test char does not match the next transtion then we go back to the root state
                    // applies to "or" cases when we have to go back and try the other epsilon transtion
                    if(testArray[i].compareToIgnoreCase(stateCurrent.transitions.get(j).getId()) != 0){
                        /*
                        System.out.println(" ");
                        System.out.println("3 transtion state to id " + nfa.states.get(0).getId());
                        System.out.println(" ");
                        */

                        j++;

                        stateCurrent = nfa.states.get(0).transitions.get(j).getTo();

                        j--;
                    }//else

                }//else

                //else we fail
                else{

                    accepts = false;

                    return accepts;

                }//else
                
                /*
                System.out.println(" ");
                System.out.println("j " + j);
                System.out.println(" ");
                */
                
            }//for

        }//try

        catch (ArrayIndexOutOfBoundsException ex) {

            System.out.println("Invalid alphabet symbol");
            return false; // no transition, just reject

        }//catch

        return accepts; // all moves fail, return false

    }//accepts
    

}//NFA Class