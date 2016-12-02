import java.util.Set;
import java.util.HashSet;
/**
 * Write a description of class CriteriaValue here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CriteriaValue implements PropertyCriteria 
{
    String filterKey;
    String secretValue;
    //Character yourChar;
    //GameSession gameSession;
    public CriteriaValue(GameSession gameSession, String filterKey) 
    {
        this.filterKey = filterKey;
        // this.gameSession = gameSession;
        //this.yourChar = gameSession.getYourChar();
        this.secretValue = gameSession.getYou().getChosenChar().getPropertyValue(filterKey);
    }
    
    /**
     * filtering and returning a set of character which matches the secrete value in secret character
     */
    public Set<Character> meetCriteria(Set<Character> characters)
    {
        return meetCriteria(characters,this.secretValue);
    }
    
    /**
     * filtering and returning a set of character which matches the passed in value
     */
    public Set<Character> meetCriteria(Set<Character> characters,String filterValue)
    {
        Set<Character> result = new HashSet<Character>();
        for(Character c:characters)
        {
            if(c.getPropertyValue(filterKey) == filterValue)
                result.add(c);
        }
        return result;
    }
    
    /**
     * filtering and returning a set of character which does not match the secrete value in secret character
     */
    public Set<Character> notMeetCriteria(Set<Character> characters)
    {
        return notMeetCriteria(characters,this.secretValue);
    }
    
    /**
     * filtering and returning a set of character which does not match the passed in value
     */
    public Set<Character> notMeetCriteria(Set<Character> characters,String filterValue)
    {
        Set<Character> result = new HashSet<Character>();
        for(Character c:characters)
        {
            if(c.getPropertyValue(filterKey) != filterValue)
                result.add(c);
        }
        return result;
    }
}
