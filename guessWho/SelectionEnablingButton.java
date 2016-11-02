import java.util.Set;
import java.util.HashSet;
import greenfoot.World;
import java.util.Observable;
/**
 * Write a description of class ButtonController here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SelectionEnablingButton<T extends Selectable> extends Observable implements Process
{
    Set<T> objects;
    protected T lastSelected;
    boolean status = false;
    /**
     * Constructor for objects of class ButtonController
     */
    public SelectionEnablingButton(Set<T> objects, EnableButton button)
    {
        this.objects = objects;
        addObserver(button);
    }
    
    public SelectionEnablingButton(SimpleContainer container, EnableButton button)
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
        T firstSelected = null;
        for(T object:objects)
        {
            if (object.isSelected())
            {
                firstSelected = object;
                break;
            }
        }
        if(firstSelected != null && status == false)
        {
            updateStatus(true,firstSelected);
            return;
        }
        if(firstSelected == null && status == true)
        {
            updateStatus(false,firstSelected);
            return;
        }
    }
    
    private void updateStatus(boolean status,T firstSelected)
    {
        this.status = status;
        setChanged();
        notifyObservers(firstSelected);
        clearChanged();        
    }  
}
