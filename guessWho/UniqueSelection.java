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
    // instance variables - replace the example below with your own
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
    
    public void setContainer(Set<SimpleSelectableActor> objects)
    {
        this.objects = objects;
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
        ((chooseCharacterScreen) world).getGameSession().setMyChar( (Character)lastSelected);
        
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
