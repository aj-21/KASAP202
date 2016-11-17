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
    String filterValue;
    //Character yourChar;
    //GameSession gameSession;
    public CriteriaValue(GameSession gameSession, String filterKey) 
    {
        this.filterKey = filterKey;
        // this.gameSession = gameSession;
        //this.yourChar = gameSession.getYourChar();
        this.filterValue = gameSession.getYourChar().getPropertyValue(filterKey);
    }
    
    public Set<Character> meetCriteria(Set<Character> characters)
    {
        Set<Character> result = new HashSet<Character>();
        for(Character c:characters)
        {
            if(c.getPropertyValue(filterKey) == filterValue)
                result.add(c);
        }
        return result;
    }
    
    public Set<Character> notMeetCriteria(Set<Character> characters)
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
