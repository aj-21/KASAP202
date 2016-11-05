import java.util.Timer;
import java.util.TimerTask;
/**
 * Write a description of class EnableButtonTimerTask here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TimeOutSignal
{
    Timer timer;
    GuessWhoState state;
    public TimeOutSignal(GuessWhoState state, long seconds)
    {
        timer = new Timer();
        timer.schedule(new Signal(),seconds*1000);
        this.state = state;
    }
    
    class Signal extends TimerTask{
        public void run()
        {
            state.timeout();
            timer.cancel();
        }
    }
    
}
