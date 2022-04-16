package edu.marist.cordoni;

import java.io.*;

/*=*
 * A nondeterministic finite-state automaton.
 * We use a backtracking-search
 * approach.
 */
public class NFA {

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
    }

    char c = in.charAt(pos++); // get char and advance
    int[] nextStates;
    try {
      nextStates = delta[s][c-'0'];
      System.out.print("\u03B4(q"+s+", "+c+ ") -> ");
      for (int i:nextStates) {
        System.out.println("q"+i);
      }
    }
    catch (ArrayIndexOutOfBoundsException ex) {
      System.out.println("Invalid alphabet symbol.  Please use {0,1}");
      return false; // no transition, just reject
    }

    // At this point, nextStates is an array of 0 or
    // more next states.  Try each move recursively;
    // if it leads to an accepting state return true.

    for (int i=0; i < nextStates.length; i++) {
      if (accepts(nextStates[i], in, pos)) return true;
    }

    return false; // all moves fail, return false
  }


  /**
   * Test whether the NFA accepts the string.
   * @param in the String to test
   * @return true iff the NFA accepts on some path
   */
  public static boolean accepts(String in) {
    return accepts(0, in, 0); // start in q0 at char 0
  }

  
}