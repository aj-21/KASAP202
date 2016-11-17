import java.util.Set;
import java.util.HashSet;

/**
 * UniqueSelectionMaintainer is to maintain a set of Selectable objects so that only 1 last selection will be kept.
 * UniqueSelectionMaintainer is a process rather than an Actor, so it need to be setup and run by us.
 * 
 * @author SPAAK
 * @version 1
 */
public class IUniqueSelection<T extends Selectable> implements Observer
{
    protected T lastSelected;
    /**
     * Constructor for objects of class UniqueSelectionMaintainer either with a paramenter of objects for maintaining one Selection or no paramenter.
     */
    public IUniqueSelection()
    {
        
    }
    
    //main method to maintain 1 or no selection at a time.
    @Override
    public void update(Observable o, Object arg)
    {
        
        Set<T> objects = (Set<T>)arg;
        boolean haveSelection = false;
        for (T a:objects)
        {
            //if something selected
            if(a.isSelected() )
            {
                haveSelection = true;
                //if something new is selected
                if(a!=lastSelected)
                {
                    updateLastSelected(a);
                    return; 
                }
            }
            
        }
        
        if(haveSelection)
            return;
        
        //if nothing is selected => deselection
        lastSelected.deselect();
        lastSelected = null;
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

}
