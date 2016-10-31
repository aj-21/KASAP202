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
public class SelectionEnablingButton extends Observable implements Process
{
    SimpleContainer container;
    protected SimpleSelectableActor lastSelected;
    boolean status = false;
    /**
     * Constructor for objects of class ButtonController
     */
    public SelectionEnablingButton(SimpleContainer container, EnableButton button)
    {
        setContainer(container);
        addObserver(button);
    }
    
    public void setContainer(SimpleContainer container)
    {
        this.container = container;
    }
    
    @Override
    public void processRun()
    {
        if(container.getSelected() != null && status == false)
        {
            setStatus(true);
            return;
        }
        if(container.getSelected() == null && status != false)
        {
            setStatus(false);
            return;
        }
    }
    
    public void setStatus(boolean status)
    {
        this.status = status;
        setChanged();
        notifyObservers();
        clearChanged();        
    }  
}
