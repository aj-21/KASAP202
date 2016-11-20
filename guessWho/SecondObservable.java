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
    Set<TimeObserver> observers;
    public SecondObservable(TimeObserver observer)
    {
        observers = new HashSet<TimeObserver>();
        observers.add(observer);
    }
    
    public void addObserver(TimeObserver observer)
    {
        observers.add(observer);
    }
    
    public void run()
    {
        for(TimeObserver o:observers)
            o.secondUpdate();
    }
}
