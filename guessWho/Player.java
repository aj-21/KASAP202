/**
 * Player class stores data for a player in game
 * it provides a basic pair of get and set methods for each internal variable
 * 
 * @author SPAAK
 * @version 1
 */
public class Player 
{
    String name;
    Character chosenChar;
    boolean isFinished;
    String lastAction;
    String lastUpdated;
    
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
    
    public void setIsFinished(boolean isFinished)
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
    
    public String getLastAction()
    {
        return this.lastAction;
    }
    
    public void setLastUpdated(String lastUpdated)
    {
        this.lastUpdated = lastUpdated;
    }
    
    public String getLastUpdated()
    {
        return this.lastUpdated;
    }
}
