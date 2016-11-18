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
    // instance variables - replace the example below with your own
    Set<Character> allCharacters = new HashSet<Character>();
    Set<Character> playCharacters = new HashSet<Character>();
    PropertyInfo propertyInfo = new PropertyInfo(this);
    GameScore gameScore = new GameScore();
    Character myChar;
    Character yourChar;
    int roomNumber;
    String myID;
    int myScore;
    String yourID;

    /**
     * Constructor for objects of class GameSession
     */
    public GameSession()
    {
        myScore = 0;
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
        //and these
        yourChar = new Char5();
    }
    
    public Set<Character> getFullSet()
    {
        return allCharacters;
    }
    
    ////add a character into full collection. Duplicate is accepted
    public void addAllToPlayList(Collection<Character> characters)
    {
        playCharacters.addAll(characters);
    }
    
    public Set<Character> getPlaySet()
    {
        return playCharacters;
    }
    
    public void setMyChar(Character myChar)
    {
        this.myChar = myChar;
    }
    
    public Character getMyChar()
    {
        return myChar;
    }
    
    public void setYourChar(Character yourChar)
    {
        this.yourChar = yourChar;
    }
    
    public Character getYourChar()
    {
        return yourChar;
    }
    
    public PropertyInfo getPropertyInfo()
    {
        return propertyInfo;
    }
    
    public int getMyScore()
    {
        return myScore;
    }
    
    public void setMyScore(int score)
    {
        this.myScore = score;
    }
    
    public GameScore getGameScore()
    {
        return gameScore;
    }
}
