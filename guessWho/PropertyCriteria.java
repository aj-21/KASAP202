import java.util.Set;
/**
 * interface for filtering CriteriaValue, implements Filter pattern
 * 
 * @author SPAAK
 * @version 1
 */
public interface PropertyCriteria  
{
    public Set<Character> meetCriteria(Set<Character> characters, String key, String value );
}
