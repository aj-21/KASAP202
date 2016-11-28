import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
/**
 * Write a description of class GameSession here.
 * 
 * @author SPAAK 
 * @version (a version number or a date)
 */
public class GameSession  
{
    String sessionID;
    
    // instance variables - replace the example below with your own
    Set<Character> allCharacters = new HashSet<Character>();
    Set<Character> playCharacters = new HashSet<Character>();
    
    PropertyInfo propertyInfo = new PropertyInfo(this);
    
    Player me;
    Player you;

    /**
     * Constructor for objects of class GameSession
     */
    public GameSession()
    {
        //by default, new game session has a new set of all characters
        allCharacters.add(new Char1());
        allCharacters.add(new Char2());
        allCharacters.add(new Char3());
        allCharacters.add(new Char4());
        allCharacters.add(new Char5());
        allCharacters.add(new Char6());
        allCharacters.add(new Char7());
        allCharacters.add(new Char8());
        allCharacters.add(new Char9());
        
        // for the purposes of testing, these are created as well
        playCharacters.add(new Char1());
        playCharacters.add(new Char2());
        playCharacters.add(new Char3());
        playCharacters.add(new Char4());
        playCharacters.add(new Char5());
        playCharacters.add(new Char6());
        playCharacters.add(new Char7());
        playCharacters.add(new Char8());
        playCharacters.add(new Char9());
        
        me = new Player();
        //you = new Player();
            
        //and these
        //you.setChosenChar(new Char5());
    }
    
    public Set<Character> getFullSet()
    {
        return allCharacters;
    }
    
    public Set<Character> getPlaySet()
    {
        return playCharacters;
    }
    
    public PropertyInfo getPropertyInfo()
    {
        return propertyInfo;
    }
    
    public Player getMe()
    {
        return me;
    }
    
    public Player getYou()
    {
        return you;
    }
    
    public void setYou(Player you)
    {
        this.you = you;
    }
    
    public String getSessionID()
    {
        return sessionID;
    }
    
    public void setSessionID(String sessionID)
    {
        this.sessionID = sessionID;
    }
}
