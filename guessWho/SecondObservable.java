import java.util.TimerTask;
import java.util.Set;
import java.util.HashSet;
/**
 * Write a description of class SecondObservable here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SecondObservable extends TimerTask
{
    Set<SecondObserver> observers;
    public SecondObservable()
    {
        observers = new HashSet<SecondObserver>();
    }
    
    public void addObserver(SecondObserver observer)
    {
        observers.add(observer);
    }
    
    public void run()
    {
        for(SecondObserver o:observers)
            o.secondUpdate();
    }
}
