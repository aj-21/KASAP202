import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
/**
 * Write a description of class GameSession here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameSession  
{
    // instance variables - replace the example below with your own
    Set<Character> allCharacters = new HashSet<Character>();
    List<Character> playCharacters = new ArrayList<Character>();
    Character myChar;
    Character yourChar;
    int roomNumber;
    String myID;
    String yourID;

    /**
     * Constructor for objects of class GameSession
     */
    public GameSession()
    {
    }
    
    //add a character into full collection. Duplicate will be ignore
    public void addAllToFullSet(Collection<Character> characters)
    {
        allCharacters.addAll(characters);
    }
    
    public void clearFullSet()
    {
        allCharacters.clear();
    }
    
    public Set<Character> getAllFromFullSet()
    {
        return allCharacters;
    }
    
    ////add a character into full collection. Duplicate is accepted
    public void addAllToPlayList(Collection<Character> characters)
    {
        playCharacters.addAll(characters);
    }
    
    
}
