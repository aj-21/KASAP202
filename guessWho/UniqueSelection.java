import java.util.Set;
import java.util.HashSet;
import greenfoot.World;
/**
 * Write a description of class UniqueSelection here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UniqueSelection implements Process
{
    Set<SimpleSelectableActor> objects;
    protected SimpleSelectableActor lastSelected;
    World world;
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
        for (SimpleSelectableActor a:objects)
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
    
    private void checkWithLastSelected(SimpleSelectableActor a)
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
