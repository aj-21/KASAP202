import java.util.Timer;
import java.util.TimerTask;
/**
 * Write a description of class TimeState here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TimeState extends GameStateDecorator implements TimeObserver
{
    Timer timer;
    TimerTask timeoutTask;
    long secRemaining;
    long delay;
    public TimeState(GameState gameState)
    {
        
        super(gameState);
    }
    
    public void setTimer(int seconds)
    {
        delay = seconds*1000;
    }
    
    private void schedule()
    {
        if (delay <= 0)
            return;
        timer = new Timer();
        timeoutTask = new TimeoutTask(this);
        timer.schedule(timeoutTask,delay);
    }
    
    @Override 
    public void updateSecond()
    {
        
    }
    
    @Override 
    public void timeout()
    {
        timer.purge();
        super.exit();
    }
    
    @Override 
    public void enter()
    {
        schedule();
        super.enter();
    }
}
