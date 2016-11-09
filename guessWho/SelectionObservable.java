import java.util.Set;
import java.util.HashSet;
import greenfoot.World;
//import java.util.Observable;
//import java.util.Observer;
/**
 * SelectionObservable is a process that observer a set of Selectable Object to see if there is any selection.
 * If there is a change in status of have/not have a selection it observers, it will notify Observers with firstSelection/null objects
 * 
 * @author SPAAK 
 * @version 1
 */
public class SelectionObservable<T extends Selectable> extends Observable implements Process
{
    Set<T> objects;
    protected T previous;
    
    /**
     * two constructors, either take in an init set of Selectable Objects or None.
     */
    public SelectionObservable()
    {
        this.objects = new HashSet<T>();
    }
    
    public SelectionObservable(Set<T> objects)
    {
        this.objects = objects;
    }
    
    //Another constructor takes in Set of Selectable object  and the first Observer.
    public SelectionObservable(Set<T> objects, Observer firstObserver)
    {
        this(objects);
        addObserver(firstObserver);
    }
    
    
    /**
     * main method to check if there is a change is status of having/not having a selection
     */
    @Override
    public void processRun()
    {
        T first = null;

        for(T object:objects)
        {
            if (object.isSelected())
            {
                    first = object;
                    break;
            }
        }
            
        //if something new is clicked
        if(first!= null && first != previous)
        {
            //System.out.println(first.getClass().getName());
            updateStatus(first);
            return;
        }
        
        //if nothing is clicked, reset previous if it stores something
        if(first == null &&  previous != null)
        {
            //System.out.println("none selected");
            updateStatus(first);
            return;
        }
        
    }
    
    protected void updateStatus(T first)
    {
        this.previous = first;
        setChanged();
        notifyObservers(first);
        clearChanged();        
    }  
    
    //set current set of objects to new set of objects
    public void setObjects(Set<T> objects)
    {
        this.objects = objects;
    }
    
    //clear current set of Objects
    public void clearAll()
    {
        this.objects.clear();
    }
}
