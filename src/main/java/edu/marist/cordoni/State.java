package edu.marist.cordoni;


import java.util.ArrayList;

public class State {

    /**
      * Declare Variables 
     */
    private String myId;
    public ArrayList <State> neighbors = new ArrayList <State>();
    private State myNext;
    private State myParent;
    private boolean myAccepts;
    private int myLeftParenFlag;
    private int myRightParenFlag;

    /**
     * The default Constructor for State
    */
    public State()
    {

        myId = new String();
        myNext = null;
        myParent = null;
        myAccepts = false;
        myLeftParenFlag = 0;
        myRightParenFlag = 0;

    }//State

    /**
    * The full constructor for State
    * @param newData the incoming data 
    */
    public State(String newData, boolean newAccepts, int newLeftParenFlag, int newRightParenFlag)
    {
        myId = newData;
        myNext = null;
        myParent = null;
        myAccepts = newAccepts;
        myLeftParenFlag = newLeftParenFlag;
        myRightParenFlag = newRightParenFlag;

         
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

     /**
     * 
     * the setter for the next vertex 
     * @param newParent the incoming data of the vertex
     */
    public void setParent(State newParent)
    {myParent = newParent;} //set data

    /**
     * The getter for the vertex 
     * @return the incoming data of the vertex
     */
    public State getParent()
    {return myParent;}//get data

    /**
     * 
     * the setter for the next vertex 
     * @param newAccepts the incoming data of the vertex
     */
    public void setAccepts(boolean newAccepts)
    {myAccepts = newAccepts;} //set data

    /**
     * The getter for the vertex 
     * @return the incoming data of the vertex
     */
    public boolean getAccepts()
    {return myAccepts;}//get data

    /**
     * 
     * the setter for the next vertex 
     * @param newLeftParenFlag the incoming data of the vertex
     */
    public void setLeftParenFlag(int newLeftParenFlag )
    {myLeftParenFlag  = newLeftParenFlag ;} //set data

    /**
     * The getter for the vertex 
     * @return the incoming data of the vertex
     */
    public int getLeftParenFlag()
    {return myLeftParenFlag ;}//get data

    /**
     * 
     * the setter for the next vertex 
     * @param newRightParenFlag the incoming data of the vertex
     */
    public void setRightParenFlag(int newRightParenFlag )
    {myRightParenFlag  = newRightParenFlag ;} //set data

    /**
     * The getter for the vertex 
     * @return the incoming data of the vertex
     */
    public int getRightParenFlag()
    {return myRightParenFlag ;}//get data
    

}//State
