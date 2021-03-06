package edu.marist.cordoni;

import java.util.ArrayList;

/**
 * DFA class.
 */

public class DFA {


    static String traversalResult = "";

    static Graph dfa;

    static State currentState;

    static State rootState;

    static ArrayList <String> alphabet = new ArrayList <String>();

    
    //import the array from the Regex class 
    public static void createDFA(Graph nfa) {

        dfa = new Graph();

        String tempID1 = "";

        String tempID2 = "";

        String doubleTemp = "";

        ArrayList <String> tempID = new ArrayList <String>();

        //System.out.println(" ");
        //System.out.println("Start Creation of DFA");

        for(int i = 0; i < nfa.states.size(); i++){

            //if there is more than one transtion we check to see if each transition has a
            //different id
            if(nfa.states.get(i).transitions.size() > 1){

                //loop through the transitions to see if there are repeating transition id's
                for(int k = 0; k < nfa.states.get(i).transitions.size(); k++){

                    tempID.add(nfa.states.get(i).transitions.get(k).getId());

                }//for

                for(int k = 0; k < nfa.states.get(i).transitions.size(); k++){
                    
                    //if these two indexes are the same then we only have one instance of each transition for this state and we skip
                    if(tempID.indexOf(nfa.states.get(i).transitions.get(k).getId()) == tempID.lastIndexOf(nfa.states.get(i).transitions.get(k).getId())){

                        //each transition has different Id's so we can skip
                        doubleTemp = nfa.states.get(i).transitions.get(k).getId();

                        //if we have one epsilong transition that means we have a kleene star
                        if(nfa.states.get(i).transitions.get(k).getId().compareToIgnoreCase("epsilon") == 0){

                            //create a new transition from the state to itself.
                            Transition tempTransition = new Transition();

                            tempTransition.setId(nfa.states.get(i).transitions.get(k-1).getId());
                            tempTransition.setFrom(nfa.states.get(i));
                            tempTransition.setTo(nfa.states.get(i));

                            //set the current state accepts to true
                            nfa.states.get(i).setAccepts(true);

                            //remove the epsilon transition
                            nfa.states.get(i).transitions.remove(k);

                        }//if


                    }//if

                    //else we have two transitions from this state with the same ID
                    else{

                        //we need to separate some states

                        //in the case we have an apsilong transitition that means we have an 'or'
                        if(nfa.states.get(i).transitions.get(k).getId().compareToIgnoreCase("epsilon") == 0){

                            //System.out.println("hello there " + nfa.states.get(i).getId());

                            //System.out.println("hello there 1 " + nfa.states.get(i).transitions.get(k).getId().toString());

                            //System.out.println("hello there 2 " + nfa.states.get(i).transitions.get(k).getTo().getId());

                            //set out two temp IDS so that we can skip these states
                            if(tempID1.compareToIgnoreCase("") == 0){
                                tempID1 = nfa.states.get(i).transitions.get(k).getTo().getId();
                            }//if

                            else{
                                tempID2 = nfa.states.get(i).transitions.get(k).getTo().getId();
                            }//else

                            State tempState = new State();

                            tempState.setId("q");

                            //add the transitions from our 'to' states
                            for(int d = 0; d < nfa.states.get(i).transitions.get(k).getTo().transitions.size() ; d++){

                                tempState.transitions.add(nfa.states.get(i).transitions.get(k).getTo().transitions.get(d));
                            
                            }//for

                            //check if we need to accpet in this state
                            if(nfa.states.get(i).transitions.get(k).getTo().getAccepts() == true){
                                tempState.setAccepts(true);
                            }//if

                            else{
                                tempState.setAccepts(false);
                            }//else

                            //add to our dfa
                            dfa.states.add(tempState);

                            //set the current state
                            currentState = tempState;

                        }//if

                        //else we have an ID repeated
                        else{

                            //if the transition id matches the is that shows up twice
                            if(nfa.states.get(i).transitions.get(k).getId().compareToIgnoreCase(doubleTemp) == 0){


                                nfa.states.get(i).transitions.remove(k);

                                dfa.states.add(nfa.states.get(i));
                                

                            }//if

                        }//else

                        

                    }//else

                }//for

            }//if

            //else we skip because then our state only has one transition
            //and we set out dfa to equal our nfa
            else{

                //create a new state 
                State newState = new State();

                //set the state to be equal to the nfa state with only 1 transition
                newState = nfa.states.get(i);

                //add it to the dfa states arraylist
                dfa.states.add(newState);

                //set the current state
                currentState = newState;


            }//else



        }//for

        //uncomment to print out each state and its transitions
        /*
        //print out the DFA states
        for(int i = 0 ; i < dfa.states.size(); i++){

            System.out.println("State Id: " + dfa.states.get(i).getId());
            System.out.println("Accepts: " + dfa.states.get(i).getAccepts());

            if(dfa.states.get(i).getNext() != null){
                System.out.println("Next: " + dfa.states.get(i).getNext().getId());
            }//if

            else{
                System.out.println("Next: " + dfa.states.get(i).getNext());
            }//else
            
            System.out.println(" ");

            System.out.println("Number of transitions from this state: " + dfa.states.get(i).transitions.size());

            System.out.println(" ");

            for(int m = 0 ; m < dfa.states.get(i).transitions.size(); m++){

                System.out.println("Transition Id: " + dfa.states.get(i).transitions.get(m).getId());
                System.out.println("From: " + dfa.states.get(i).transitions.get(m).getFrom().getId());
                System.out.println("To: " + dfa.states.get(i).transitions.get(m).getTo().getId());
                
                System.out.println(" ");
    
            }//for

        }//for
        */

    }//createDFA

     /*
    * Test whether there is some path for the NFA to reach
    * an accepting state, from the given state and reading
    */
    
    public static boolean accepts(String testline){

        boolean accepts = false;

        State stateCurrent = dfa.states.get(0);

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
                if(NFA.alphabet.toString().contains(testArray[i]) == false){

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

                    //for strings of length 1 made up of the accepting char
                    if(testArray.length == 1){

                        if (testArray[i].compareToIgnoreCase(stateCurrent.transitions.get(j).getId()) == 0){

                            stateCurrent = stateCurrent.transitions.get(j).getTo();

                            //System.out.println("current state id " + stateCurrent.getId());
    
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

                    //if we are in an accepting state
                    if(stateCurrent.getAccepts() == true){
                        accepts = true;
                    }//if

                    //if we are not in an accepting state
                    if(stateCurrent.getAccepts() == false){
                        accepts = false;
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

                    //System.out.println(stateCurrent.transitions.get(j).getId());

                }//If

                

                //no transitions left going to look for epsilon
                if(stateCurrent.transitions.get(j).getId().compareToIgnoreCase("epsilon") == 0){

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

                        stateCurrent = dfa.states.get(0).transitions.get(j).getTo();

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






}//DFA
