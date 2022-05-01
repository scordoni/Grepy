package edu.marist.cordoni;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
//import java.util.regex.*;
import org.apache.commons.cli.CommandLine;

/**
 * Main app class.
 */
public final class App {
    /** App class constructor. */
    protected App() {
    }

    /**
     * Says hello to the world!
     * @param args The arguments of the program.
     * @throws FileNotFoundException
     */
    public static void main(final String[] args) throws FileNotFoundException {

        //declare variables
        String file;
        String regex;
        String nfaFile;
        String dfaFile;
        String line;
        int returnCode = 0;

        //initialize
        Utils utils = new Utils();

        CommandLine cmdLine = utils.processArgs(args);

        String[] cmdArgs = cmdLine.getArgs();

        System.out.println(" ");

        System.out.println("Parameter Argument Length: " + cmdArgs.length);

        System.out.println(" ");

        /*
        if(cmdArgs.length == 2){

            regex = args[0];

            file = args[1]; 

        }//if

        else if (cmdArgs.length == 3){

            nfaFile = args[0];

            dfaFile = args[1];

            regex = args[2];

            file = args[3];

        }//else

        */

        regex = args[0];

        file = args[1]; 

        System.out.println(" ");

        System.out.println("Regular Expression: " + regex);

        System.out.println("Input File: " + file);

        System.out.println(" ");
        
    
        //create the reference to the file
        File myFile = new File(file);
		
	    try
	    {

            //create scanner
            Scanner input = new Scanner(myFile);

            //regex to NFA
            RegEx.regex(regex);

            System.out.println(" ");

            
            //if the file is empty we let the user know there is nothing in the file
            if(input.hasNext() == false) {
                
                System.out.println("This text file is empty.");

            } //if

            //else we can run through the file to create the programs
            else {

                System.out.println("Input File: ");

                while(input.hasNext()) {

                    //while there is a next line we set it to a temp variable
                    line = input.nextLine();

                    System.out.println(line);

                    //Check if NFA accepts

                    if (NFA.accepts(line)) {
                        System.out.println(" ");
                        System.out.println("NFA Accepted: " + line);
                        System.out.println(" ");
                    }//if

                    else {
                        System.out.println(" ");
                        System.out.println("NFA Rejected: " + line);
                        System.out.println(" ");
                    }//else
                         

                    //Check if DFA accepts
                    
                    if (DFA.accepts(line)) {
                        System.out.println(" ");
                        System.out.println("DFA Accepted: " + line);
                        System.out.println(" ");
                    }//if

                    else {
                        System.out.println(" ");
                        System.out.println("DFA Rejected: " + line);
                        System.out.println(" ");
                    }//else
                    

                } //while

                System.out.println(" ");
                System.out.println("DOT format outputed to DOT file named: DOTGraph.txt");
                System.out.println(" ");
                DOT.printNFADFA();

            } //else

            input.close();

        } //try

        //error for file not found
	    catch(FileNotFoundException ex)	{

            System.out.println("Failed to find file: " + myFile.getAbsolutePath()); 

        } //catch  

        System.out.println(" ");
        System.out.println("Grep Completed");

        System.exit(returnCode);

    } //main

} //Class App.java
