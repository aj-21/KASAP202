import java.util.Set;
import java.util.HashSet;
/**
 * A helper class to uniformly perform an action on a set of Selectable
 * it provides two categories of methods:
 * 1. normal get and set the internal Set of Selectable
 * 2. special get and set performed on internal Set of Selectable 
 *      2.1 getFirstSelected and getSelected are similar
 *      2.2 getAllSelected
 *      2.3 selectAll
 *      2.4 deselectAll
 * 
 * @author SPAAK 
 * @version 1
 */
public class SimpleContainer <T extends Selectable> 
{
    // instance variables - replace the example below with your own
    protected Set<T> actors;

    /**
     * Constructor for objects of class SimpleContainer
     */
    public SimpleContainer()
    {
        actors = new HashSet<T>();
    }
    
    public SimpleContainer(T actor)
    {
        this();
        add(actor);
    }
    
    public SimpleContainer(Set<T> actors)
    {
        this();
        addAll(actors);
    }
    
    public SimpleContainer addAll(Set<T> actors)
    {
        for (T actor:actors)
            add(actor);
        return this;
    }
    
    public void add(T actor)
    {
        actors.add(actor);
    }
    
    public void remove(T actor)
    {
        actors.remove(actor);
    }
    
    public void selectAll()
    {
        for (T actor:actors)
            actor.select();
    }
    
    public void deselectAll()
    {
        for (T actor:actors)
            actor.deselect();
    }
    
    public Set<T> getAll()
    {
        return actors;
    }
    
    public T getSelected()
    {
        return getFirstSelected();
    }
    
    public T getLastSelected()
    {
        T selectedActor= null;
        for (T actor:actors)
        {
            if (actor.isSelected())
            {
                selectedActor = actor;
            }
        }
        return selectedActor;
    }
    
    public T getFirstSelected()
    {
        T selectedActor= null;
        for (T actor:actors)
        {
            if (actor.isSelected())
            {
                selectedActor = actor;
                break;
            }
        }
        return selectedActor;
    }
    
    public Set<T> getAllSelected()
    {
        Set<T> selectedActors = new HashSet<T>();
        for (T actor:actors)
        {
            if (actor.isSelected())
            {
                selectedActors.add(actor);
            }
        }
        return selectedActors;
    }
}
