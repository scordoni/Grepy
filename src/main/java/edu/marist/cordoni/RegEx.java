package edu.marist.cordoni;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * RegExclass.
*/


import java.util.regex.*;


public class RegEx {


    static ArrayList <Character> regExpressionArray = new ArrayList <Character> ();


    public static void regex(String regularExpression) {

        System.out.println("Regular Expression: " + regularExpression);

        System.out.println("Regular Expression Length: " + regularExpression.length());

        for(int i = 0; i < regularExpression.length(); i++){

            regExpressionArray.add(regularExpression.charAt(i));

        }//for


        //System.out.println(regExpressionArray.toString());

        //pass to NFA class
        NFA.createNFA(regExpressionArray);


    }//regex

    
    
}//class RegEx
