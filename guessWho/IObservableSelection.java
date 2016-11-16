import java.util.Set;
import java.util.HashSet;

/**
 * UniqueSelectionMaintainer is to maintain a set of Selectable objects so that only 1 last selection will be kept.
 * UniqueSelectionMaintainer is a process rather than an Actor, so it need to be setup and run by us.
 * 
 * @author SPAAK
 * @version 1
 */
public class IObservableSelection<T extends Selectable> extends Observable implements Observer
{
    Set<T> objects;
    protected T lastSelected;
    /**
     * Constructor for objects of class UniqueSelectionMaintainer either with a paramenter of objects for maintaining one Selection or no paramenter.
     */
    public IObservableSelection()
    {
        
    }
    
    public IObservableSelection(Observer observer)
    {
        addObserver(observer);
    }
    
    //main method to maintain 1 or no selection at a time.
    @Override
    public void update(Observable o, Object arg)
    {
        
        objects = (Set<T>)arg;
        T selected = null;
        for (T a:objects)
        {
            //if something new is clicked
            if(a.isSelected())
            {
                selected = a;
                break;
            }
            //else the selected will be null
        }
        
        setChanged();
        notifyObservers(selected);
        clearChanged();
    }
}
