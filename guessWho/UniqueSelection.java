import java.util.Set;
import java.util.HashSet;
import greenfoot.World;
/**
 * Write a description of class UniqueSelection here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UniqueSelection
{
    Set<SimpleSelectableActor> objects;
    protected SimpleSelectableActor lastSelected;
    World world;
    /**
     * Constructor for objects of class UniqueSelection
     */
    public UniqueSelection(World world)
    {
        objects = new HashSet<SimpleSelectableActor>();
        this.world = world;
    }
    
    public void setContainer(SimpleContainer container)
    {
        this.objects = container.getAll();
    }
    
    public void run()
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
