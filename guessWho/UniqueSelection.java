import java.util.Set;
import java.util.HashSet;

/**
 * Write a description of class UniqueSelection here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UniqueSelection implements Process
{
    //Set<SimpleSelectableActor> objects;
    Set<Selectable> objects;
    protected Selectable lastSelected;
    /**
     * Constructor for objects of class UniqueSelection
     */
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
        boolean noneSelected = true;
        for (Selectable a:objects)
        {
            if(a.isSelected())
            {
                noneSelected = false;
                checkWithLastSelected(a);
            }
        }
        if(noneSelected)
            lastSelected = null;        
    }
    
    private void checkWithLastSelected(Selectable a)
    {
        if(a != lastSelected)
        {
            if(lastSelected!=null)
            {
                System.out.println("deselecting " + lastSelected.getClass().getName());            
                lastSelected.deselect();
            }
            lastSelected=a;
        }
    }    
}
