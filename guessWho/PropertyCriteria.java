import java.util.Set;
/**
 * Write a description of class Criteria here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface PropertyCriteria  
{
    public Set<Character> meetCriteria(Set<Character> characters);
    public Set<Character> notMeetCriteria(Set<Character> characters);
}
