import java.util.Set;
import java.util.HashSet;
import greenfoot.World;
import java.util.Observable;
import java.util.Observer;
/**
 * Write a description of class ButtonController here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SelectionObservable<T extends Selectable> extends Observable implements Process
{
    Set<T> objects;
    protected T previous;
    /**
     * Constructor for objects of class ButtonController
     */
    
    public SelectionObservable()
    {
        this.objects = new HashSet<T>();
    }
    
    public SelectionObservable(Set<T> objects)
    {
        this.objects = objects;
    }
    
    public SelectionObservable(Set<T> objects, Observer firstObserver)
    {
        this(objects);
        addObserver(firstObserver);
    }
    
    public SelectionObservable(SimpleContainer container, EnableButton button)
    {
        this(container.getAll(),button);
    }
    
    public void setContainer(SimpleContainer container)
    {
        this.objects = container.getAll();
    }
    
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
            System.out.println(first.getClass().getName());
            updateStatus(first);
            return;
        }
        
        //if nothing is clicked, reset previous if it stores something
        if(first == null &&  previous != null)
        {
            System.out.println("none selected");
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
    
    public void setObjects(Set<T> objects)
    {
        this.objects = objects;
    }
    
    public void clearObjects()
    {
        setObjects(new HashSet<T>());
    }
}
