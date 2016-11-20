import java.util.TimerTask;
import java.util.Set;
import java.util.HashSet;
/**
 * Write a description of class TimeouTask here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TimeoutTask extends TimerTask
{
    Set<TimeObserver> observers;
    public TimeoutTask(TimeObserver observer)
    {
        observers = new HashSet<TimeObserver>();
        this.observers.add(observer);
    }
    
    public void addObserver(TimeObserver observer)
    {
        observers.add(observer);
    }
    
    @Override
    public void run()
    {
        for(TimeObserver observer:observers)
            observer.timeout();
    }
}
