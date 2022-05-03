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

    static int leftParenFlag;

    static int rightParenFlag;

    static State newStateroot;

    static State rootState;

    static ArrayList <String> alphabet = new ArrayList <String>();

    
    //import the array from the Regex class 
    public static void createNFA(ArrayList<Character> regularExpression) {


        //reset Variables

        State currentState = new State();

        State rootState = new State();

        nfa = new Graph();

        State newStateroot = new State();

        State tempStateTo = new State();

        State tempStateFrom = new State();

        State tempStateLeftParen = new State();

        State tempStateRightParen = new State();

        Transition tempTransition = new Transition();

        ArrayList <State> temp = new ArrayList <State>();

        alphabet = new ArrayList <String>();

        int j = 0;

        leftParenFlag = 0;

        rightParenFlag = 0;

        /*
        System.out.println(" ");
        System.out.println("Regular Expression Array: " + RegEx.regExpressionArray.toString());
        System.out.println(" ");
        System.out.println("Start Creation of NFA");
        */

        //loop through the regular expression array
        for(int i = 0; i < RegEx.regExpressionArray.size(); i++){

            //System.out.println("Current State ID " + currentState.getId());
            //System.out.println("Left State ID " + tempStateLeftParen.getId());

            //if there is parenthesis in the regular expression
            if(RegEx.regExpressionArray.get(i).compareTo('(') == 0){

                /*
                //if the current state exists then mark its left paren flag
                if(currentState.getId().compareToIgnoreCase(" ") == 0){

                    //System.out.println("left par" + currentState.getId() + "k");

                    currentState.setLeftParenFlag(1);

                    tempStateLeftParen = currentState;

                    leftParenFlag = 1;

                }//if

                //if the current state has not been set yet then we just set the general flag to 1
                else{

                    leftParenFlag = 1;

                }//else
                */


            }//if

            else if(RegEx.regExpressionArray.get(i).compareTo(')') == 0){

                /*
                currentState.setRightParenFlag(1);

                tempStateRightParen = currentState;

                rightParenFlag = 1;
                */

            }//else if

            //if there is an "or" in the regular expression
            else if((RegEx.regExpressionArray.get(i).compareTo('+') == 0) || (RegEx.regExpressionArray.get(i).compareTo('|') == 0)){

                

            }//else if


            //if we have a kleene star in the regular expression
            else if(RegEx.regExpressionArray.get(i).compareTo('^') == 0){

                //leave blank

            }//else if

            //else if we have a kleene star
            else if(RegEx.regExpressionArray.get(i).compareTo('*') == 0){

                /*
                if(rightParenFlag == 1){

                    Transition newTransition = new Transition();

                    newTransition.setId("epsilon");
                    newTransition.setFrom(currentState);

                    System.out.println("Flag " + leftParenFlag);

                    if(leftParenFlag == 1){

                        newTransition.setTo(tempStateLeftParen);

                        tempStateLeftParen.setAccepts(true);
                        
                    }//if



                    currentState.transitions.add(newTransition);

                    tempTransition.setNext(newTransition);

                    tempTransition = newTransition;

                }//if

                else{
                */
                    
                    //else we create a new epsilon transition
                    Transition newTransition = new Transition();

                    newTransition.setId("epsilon");
                    newTransition.setFrom(tempStateTo);
                    newTransition.setTo(tempStateFrom);

                    //System.out.println("i " + i);
                    //System.out.println("size " + RegEx.regExpressionArray.size());

                    //if there is still more to process then we cannot accept it
                    //if(RegEx.regExpressionArray.get(i + 1) != null){

                        //tempStateFrom.setAccepts(false);
                        //tempStateTo.setAccepts(false);

                    //}//if

                    //else{

                        tempStateFrom.setAccepts(true);
                        tempStateTo.setAccepts(false);

                    //}//else

                    
                    //add the new transition
                    tempStateTo.transitions.add(newTransition);

                    tempTransition.setNext(newTransition);

                    tempTransition = newTransition;

                //}

            }//else if

            //else we have a terminal symbol in our regular expression
            else{

                //add to our alphabet
                alphabet.add(RegEx.regExpressionArray.get(i).toString());

                //if j == 0, in other words we just started our nfa creation
                if(j == 0){

                    //create 2 new states and a transition
                    State newStateFrom1 = new State();
                    State newStateTo1 = new State();

                    Transition newTransition = new Transition();

                    //create our start state
                    newStateFrom1.setId("q0");
                    newStateFrom1.setAccepts(false);

                    //left commented out in regard to parens
                    /*
                    if((i != 0 ) && (RegEx.regExpressionArray.get(i-1).compareTo('(') == 0)){

                        tempStateLeftParen = newStateFrom1;

                    }//if
                    */

                    //set the root state
                    rootState = newStateFrom1;

                    //if the left paren flag has been set then we give the attribute to
                    //the first state
                    /*
                    if(leftParenFlag == 1){

                        newStateFrom1.setLeftParenFlag(1);
                        tempStateLeftParen = newStateFrom1;

                    }//if
                    */

                    j++;

                    newStateTo1.setId("q1");

                    if((RegEx.regExpressionArray.get(i + 1) == null)||(alphabet.toString().contains(RegEx.regExpressionArray.get(i + 1).toString()) == false)){
                        newStateTo1.setAccepts(true);
                    }//if

                    //set a new current state
                    currentState = newStateTo1;

                    j++;

                    //set next and parent for the respective states
                    newStateFrom1.setNext(newStateTo1);
                    newStateTo1.setParent(newStateFrom1);

                    //set the new transition
                    newTransition.setId(RegEx.regExpressionArray.get(i).toString());
                    newTransition.setFrom(newStateFrom1);
                    newTransition.setTo(newStateTo1);

                    //add the states to the graph
                    nfa.states.add(newStateFrom1);
                    nfa.states.add(newStateTo1);

                    newStateFrom1.transitions.add(newTransition);

                    //set the global temp states to be used elsewhere
                    tempStateFrom = newStateFrom1;
                    tempStateTo = newStateTo1;
                    tempTransition = newTransition;

                }//if

                //if j is not equal to 0 and out current index in the regular expression array is contained in our alphabet
                else if( (j != 0) && (alphabet.toString().contains(RegEx.regExpressionArray.get(i).toString()))){

                    //create 2 new states and transition
                    State newStateFrom2 = new State();
                    State newStateTo2 = new State();

                    Transition newTransition = new Transition();

                    //create our start state
                    newStateFrom2.setId("q" + j);
                    newStateFrom2.setAccepts(false);
                    
                    //left comments out for parens
                    /*
                    if((i != 0 ) && (RegEx.regExpressionArray.get(i-1).compareTo('(') == 0)){

                        tempStateLeftParen = newStateFrom1;

                    }//if
                    */

                    j++;

                    newStateTo2.setId("q"+ j);
                    
                    if(i == RegEx.regExpressionArray.size()){
                        newStateTo2.setAccepts(true);
                    }//if
                    
                    currentState = newStateTo2;
                    j++;

                    //set parent and next for the respective states
                    newStateFrom2.setNext(newStateTo2);
                    newStateTo2.setParent(newStateFrom2);

                    //set new transtiion information
                    newTransition.setId(RegEx.regExpressionArray.get(i).toString());
                    newTransition.setFrom(newStateFrom2);
                    newTransition.setTo(newStateTo2);

                    //add the new states to the graph
                    nfa.states.add(newStateFrom2);
                    nfa.states.add(newStateTo2);

                    //add the new transition to the respective state
                    newStateFrom2.transitions.add(newTransition);

                    //set the global temp variables
                    tempStateFrom = newStateFrom2;
                    tempStateTo = newStateTo2;
                    tempTransition = newTransition;

                }//else
                
                //if we are not in the first spot of our regular expression array and we hit an 'or' statement
                if((i != 0) && ((RegEx.regExpressionArray.get(i-1).compareTo('+') == 0) || (RegEx.regExpressionArray.get(i-1).compareTo('|') == 0))){
                    
                    //create new root state
                    newStateroot.setId("q");
                    newStateroot.setAccepts(false);

                    //left commented out for parens
                    /*
                    if((i != 0 ) && (RegEx.regExpressionArray.get(i-1).compareTo('(') == 0)){

                        tempStateLeftParen = newStateFrom1;

                    }//if
                    */

                    //create 2 new epsilon transitions and set their information
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

                    //add the transitions to the new root state
                    newStateroot.transitions.add(newTransition1);
                    newStateroot.transitions.add(newTransition2);

                    //add the new root state to the graph to the beginning of the graphs state arraylist
                    temp.add(newStateroot);

                    for(int h = 0; h < nfa.states.size(); h++){

                        temp.add(nfa.states.get(h));
                    }//for

                    nfa.states.clear();

                    nfa.states = temp;

                    //tempTransition.setNext(newTransition);
                    //tempTransition = newTransition;

                }//if

                //if i is not equal to 0 and the previous in our array is already in our alphabet
                if((i != 0) && (alphabet.toString().contains(RegEx.regExpressionArray.get(i-1).toString()))){
                    
                    //create a new state and transition
                    State newStateTo2 = new State();

                    Transition newTransition = new Transition();

                    newStateTo2.setId("q"+ j);
                    
                    //if we have reached the end of our array then we set the state to accept
                    if(i == RegEx.regExpressionArray.size()){
                        newStateTo2.setAccepts(true);
                    }//if

                    j++;

                    currentState.setNext(newStateTo2);
                    newStateTo2.setParent(currentState);

                    //possible delete
                    /*
                    if(alphabet.toString().contains(RegEx.regExpressionArray.get(i+1).toString())){
                    
                    }//if
                    
                    else{
                        newStateTo2.setAccepts(true);
                    }//else
                    */

                    //set new transition information
                    newTransition.setId(RegEx.regExpressionArray.get(i).toString());
                    newTransition.setFrom(currentState);
                    newTransition.setTo(newStateTo2);

                    //add new transition to the state
                    nfa.states.add(newStateTo2);

                    currentState.transitions.add(newTransition);

                    tempStateFrom = currentState;
                    tempStateTo = newStateTo2;
                    tempTransition = newTransition;

                    currentState = newStateTo2;

                }//if



            }//else


        }//for

        //uncomment to print out each state and its transitions
        /*
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
        */

        //pass it on

        DFA.createDFA(nfa);

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
                    
                        if (testArray[i].compareToIgnoreCase(stateCurrent.transitions.get(j).getId()) == 0){
    
                            stateCurrent = stateCurrent.transitions.get(j).getTo();
    
                            //if we are in an accepting state
                            if((stateCurrent.getAccepts() == true)&&(stateCurrent.transitions.size() == 0)){
                                accepts = true;
                                return accepts;
                            }//if

                            else if((stateCurrent.getAccepts() == false)&&(stateCurrent.transitions.size() == 0)){
                                accepts = false;
                                return accepts;
                            }//if
                        
                        }//if
    
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