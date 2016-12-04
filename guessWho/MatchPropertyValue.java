import java.util.Set;
import java.util.HashSet;
/**
 * Write a description of class MatchPropertyValue here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MatchPropertyValue implements PropertyCriteria
{
    public Set<Character> meetCriteria(Set<Character> characters, String key, String value )
    {
        Set<Character> meet = new HashSet<Character>();
        
        for (Character c:characters){
            if(c.getPropertyValue(key).equals(value))
                meet.add(c);
        }
        
        return meet;
    }
}
