package edu.marist.cordoni;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
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
        String line;
        int returnCode = 0;

        //initialize
        Utils utils = new Utils();

        CommandLine cmdLine = utils.processArgs(args);

        String[] cmdArgs = cmdLine.getArgs();

        System.out.println(" ");

        System.out.println(cmdArgs.length);

        System.out.println(" ");

        regex = args[0];

        file = args[1]; 

        System.out.println(" ");

        System.out.println(regex);

        System.out.println(file);

        System.out.println(" ");
        
    
        //create the reference to the file
        File myFile = new File(file);
		
	    try
	    {

            //create scanner
            Scanner input = new Scanner(myFile);
            
            //if the file is empty we let the user know there is nothing in the file
            if(input.hasNext() == false) {
                
                System.out.println("This text file is empty.");

            } //if

            //else we can run through the file to create the programs
            else {

                while(input.hasNext()) {

                    //while there is a next line we set it to a temp variable
                    line = input.nextLine();

                    System.out.println(line);

                    //regex to NFA

                    

                    //Check if NFA accepts

                    /*
                    if (NFA.accepts(line)) {
                        System.out.println("Accepted: " + line);
                    }//if

                    else {
                        System.out.println("Rejected: " + line);
                    }//else
                        */  

                    //Check if DFA accepts


                } //while

            } //else

            input.close();

        } //try

        //error for file not found
	    catch(FileNotFoundException ex)	{

            System.out.println("Failed to find file: " + myFile.getAbsolutePath()); 

        } //catch  

        System.out.println(" ");
        System.out.println("Hi World!");

        System.exit(returnCode);

    } //main
} //Class App.java
