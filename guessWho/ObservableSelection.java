import java.util.Set;
import java.util.HashSet;

/**
 * ObservableSelection will observe a set of selectable object and notify its observer if something new isclicked
 * in this game, this is used as a pipe line to observe selection in chooseCharScreen and notify confirm button
 * 
 * @author SPAAK
 * @version 1
 */
public class ObservableSelection<T extends Selectable> extends Observable implements Observer
{
    Set<T> objects;
    protected T lastSelected;
    /**
     * Constructor for objects of class UniqueSelectionMaintainer either with a paramenter of objects for maintaining one Selection or no paramenter.
     */
    public ObservableSelection()
    {
        
    }
    
    public ObservableSelection(Observer observer)
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