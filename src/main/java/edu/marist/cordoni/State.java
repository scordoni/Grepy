package edu.marist.cordoni;


import java.util.ArrayList;

public class State {

    /**
      * Declare Variables 
     */
    private String myId;
    public ArrayList <State> neighbors= new ArrayList <State>();
    private State myNext;
    

    /**
     * The default Constructor for State
    */
    public State()
    {

        myId = new String();
        myNext = null;

    }//State

    /**
    * The full constructor for State
    * @param newData the incoming data 
    */
    public State(String newData)
    {
        myId = newData;
        myNext = null;
         
    }//State

   /**
    * 
   * the setter for the State id
   * @param newId the incoming data of the State
   */
   public void setId(String newId)
   {myId = newId;} //set data

   /**
   * The getter for the vertex id
   * @return the incoming data of the vertex
   */
   public String getId()
   {return myId;}//get data


   /**
   * 
   * the setter for the next vertex 
   * @param newNext the incoming data of the vertex
   */
   public void setNext(State newNext)
   {myNext = newNext;} //set data

   /**
   * The getter for the vertex 
   * @return the incoming data of the vertex
   */
   public State getNext()
   {return myNext;}//get data
    
}//State
