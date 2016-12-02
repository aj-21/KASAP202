import java.util.Set;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class Observable  
{
    Set<Observer> observers;
    boolean changed = false;
    
    public Observable()
    {
        observers = new LinkedHashSet<Observer>();
    }
    
    public void addObserver(Observer o)
    {
        observers.add(o);
    }
    
    protected void clearChanged()
    {
        changed = false;
    }
    
    public int countObservers()
    {
        return observers.size();
    }
    
    public void deleteObserver(Observer o)
    {
        observers.remove(o);
    }
    
    public void deleteObservers()
    {
        observers.clear();
    }
    
    public boolean hasChanged()
    {
        return changed;
    }
    
    public void notifyObservers()
    {
        notifyObservers(null);
    }
    
    public void notifyObservers(Object arg)
    {
        if(changed)
        {
            for(Observer o:observers)
            {
                o.update(this,arg);
            }
        }
    }
    
    protected void setChanged()
    {
        changed = true;
    }
}
