/**
 * Write a description of class PlayerInfo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player 
{
    String name;
    Character chosenChar;
    boolean isFinished;
    String lastAction;
    
    public Player()
    {
        name = "";
        chosenChar = null;
        isFinished = false;
        lastAction = "";
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public void setChosenChar(Character chosenChar)
    {
        this.chosenChar = chosenChar;
    }
    
    public Character getChosenChar()
    {
        return chosenChar;
    }
    
    public void setIsFinished(boolean isFinised)
    {
        this.isFinished = isFinished;
    }
    
    public boolean isFinished()
    {
        return isFinished;
    }
    
    public void setLastAction(String lastAction)
    {
        this.lastAction = lastAction;
    }
}
