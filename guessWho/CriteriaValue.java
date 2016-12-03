import java.util.Set;
import java.util.HashSet;
/**
 * a class that help to filter out elements matching criteria from playSet in gameSession
 * 
 * @author SPAAK
 * @version 1
 */
public class CriteriaValue implements PropertyCriteria 
{
    String filterKey;
    String secretValue;

    public CriteriaValue(GameSession gameSession, String filterKey) 
    {
        this.filterKey = filterKey;

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
