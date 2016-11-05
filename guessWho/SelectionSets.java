import java.util.Set;
import java.util.HashSet;
/**
 * Write a description of class SelectionSets here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SelectionSets<T extends Selectable> extends SelectionObservable<T>
{
    Set<Set<T>> objectSets;
    //Set<T> mySet = new HashSet<T>();
    public SelectionSets(Set<Set<T>> objectSets)
    {
        this.objectSets = objectSets;
    }
       
    @Override
    public void processRun()
    {
        unifySets();
        super.processRun();
    }
    
    private void unifySets()
    {
        Set<T> newSet = new HashSet<T>();
        clearAll();
        for(Set<T> set:objectSets)
            newSet.addAll(set);
        setObjects(newSet);
    }
}
