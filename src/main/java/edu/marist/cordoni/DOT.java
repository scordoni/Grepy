package edu.marist.cordoni;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/*
* Got kinda lost on creating dot files
* code for imputting test into a file taken from this website:
* https://www.w3schools.com/java/java_files_create.asp
*
*/



public class DOT {


    

    public static void printNFADFA(){

        try {

            FileWriter myWriter = new FileWriter("DOTGraph.txt");


            myWriter.write("digraph graph {");
            
            for(int i = 0; i < NFA.nfa.states.size(); i++){

                for(int j = 0; j < NFA.nfa.states.get(i).transitions.size(); j++){
                    
                    myWriter.write("\r\n");
                    myWriter.write(NFA.nfa.states.get(i).getId() + " -> " + NFA.nfa.states.get(i).transitions.get(j).getTo().getId());
                    myWriter.write("\r\n");
                    
                }//for
    
            }//for
            
            myWriter.write("}");
            myWriter.close();

          }//try
          
          catch (IOException e) {

            System.out.println("An error occurred.");
            e.printStackTrace();

          }//catch


    }//printNFADFA
    
}//dot 
