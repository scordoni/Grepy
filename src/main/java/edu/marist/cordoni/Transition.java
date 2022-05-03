package edu.marist.cordoni;

public class Transition {

    /**
    * Declare Variables 
    */
    private String myId;
    private State myTo;
    private State myFrom;
    private Transition myNext;
    

    /**
    * The default Constructor for Transition
    */
    public Transition()
    {

        myTo = null;
        myFrom = null;
        myNext = null;
        myId = new String();

    }//Transition

    /**
    * The full constructor for Transition
    * @param newData the incoming data of the item
    */
    public Transition(State newTo, State newFrom, String newData)
    {
        myTo = newTo;
        myFrom = newFrom;
        myNext = null;
        myId = newData;
        
    }//Transition

    /**
    * the setter for the Transition data
    * @param newTo the incoming data of the item
    */
    public void setTo(State newTo)
        {myTo = newTo;} //set data

    /**
     * The getter for the Transition data
     * @return the incoming data of the item
     */
    public State getTo()
        {return myTo;}//get data

    /**
    * the setter for the Transition data
    * @param newFrom the incoming data of the item
    */
    public void setFrom(State newFrom)
        {myFrom = newFrom;} //set data

    /**
     * The getter for the edge data
     * @return the incoming data of the item
     */
    public State getFrom()
        {return myFrom;}//get data

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
    public void setNext(Transition newNext)
    {myNext = newNext;} //set data

    /**
     * The getter for the vertex 
     * @return the incoming data of the vertex
     */
    public Transition getNext()
    {return myNext;}//get data
    
}//Transition
