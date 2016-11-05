import java.util.Set;
import java.util.HashSet;

/**
 * UniqueSelection is to maintain a set of Selectable objects so that only 1 last selection will be kept.
 * UniqueSelection is a process rather than an Actor, so it need to be setup and run by us.
 * 
 * @author SPAAK
 * @version 1
 */
public class UniqueSelection<T extends Selectable> implements Process
{
    Set<T> objects;
    protected T lastSelected;
    /**
     * Constructor for objects of class UniqueSelection either with a paramenter of objects for maintaining one Selection or no paramenter.
     */
    public UniqueSelection()
    {
        this.objects = new HashSet<T>();
    }
    
    public UniqueSelection(Set<T> objects)
    {
        this.objects = objects;
    }
    
    //main method to maintain 1 or no selection at a time.
    @Override
    public void processRun()
    {
        for (T a:objects)
        {
            if(a.isSelected() && a!=lastSelected)
            {
                updateLastSelected(a);
                return;
            }
        }
    }
    
    //helper method to maintain 1 selection
    private void updateLastSelected(T a)
    {
        if(lastSelected!=null)
        {          
            lastSelected.deselect();
        }
        lastSelected=a;
    }   
    
    //set current set of object to a new set of objects
    public void setObjects(Set<T> objects)
    {
        this.objects = objects;
    }
    
    //clear current set of objects
    public void clearObjects()
    {
        setObjects(new HashSet<T>());
    }
}
