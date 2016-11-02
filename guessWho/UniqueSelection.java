import java.util.Set;
import java.util.HashSet;

/**
 * Write a description of class UniqueSelection here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UniqueSelection<T extends Selectable> implements Process
{
    //Set<SimpleSelectableActor> objects;
    Set<T> objects;
    protected T lastSelected;
    /**
     * Constructor for objects of class UniqueSelection
     */
    public UniqueSelection()
    {
        this.objects = new HashSet<T>();
    }
    
    public UniqueSelection(Set<T> objects)
    {
        this.objects = objects;
    }
    
    public UniqueSelection(SimpleContainer container)
    {
        setContainer(container);
    }
    
    public void setContainer(SimpleContainer container)
    {
        this.objects = container.getAll();
    }
    
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
        //lastSelected = null;
    }
    
    private void updateLastSelected(T a)
    {
        if(lastSelected!=null)
        {          
            lastSelected.deselect();
        }
        lastSelected=a;
    }   
    
    public void setObjects(Set<T> objects)
    {
        this.objects = objects;
    }
    
    public void clearObjects()
    {
        setObjects(new HashSet<T>());
    }
}
