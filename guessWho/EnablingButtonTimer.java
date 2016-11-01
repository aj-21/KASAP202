import java.util.Timer;
import java.util.TimerTask;
/**
 * Write a description of class EnableButtonTimerTask here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnablingButtonTimer 
{
    Timer timer;
    EnableButton button;
    public EnablingButtonTimer(EnableButton button, long seconds)
    {
        timer = new Timer();
        timer.schedule(new EnablingTask(),seconds*1000);
        this.button = button;
    }
    
    class EnablingTask extends TimerTask{
        public void run()
        {
            button.enable();
            timer.cancel();
        }
    }
    
}
