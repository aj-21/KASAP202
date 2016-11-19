import java.util.TimerTask;
/**
 * Write a description of class TimeouTask here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TimeoutTask extends TimerTask
{
    TimeObserver observer;
    public TimeoutTask(TimeObserver observer)
    {
        this.observer = observer;
    }
    
    @Override
    public void run()
    {
        observer.timeout();
    }
}
