import java.util.Timer;
import java.util.TimerTask;
/**
 * This class is used for the timer functionality in the game. Used to setting and expiring the time.
 * 
 * @author SPAAK 
 * @version 1
 */
public class TimeOutSignal
{
    Timer timer;
    GuessWhoState state;
    long seconds;
    int print = 0;
    public TimeOutSignal(GuessWhoState state, long seconds)
    {
        timer = new Timer();
        timer.schedule(new Signal(),seconds*1000);
        timer.schedule(new Print(),0,1000);
        this.state = state;
        this.seconds = seconds;
    }
    
    class Signal extends TimerTask{
        public void run()
        {
            state.timeout();
            timer.cancel();
        }
    }
    
    class Print extends TimerTask{
        public void run()
        {
            if (print == 0)
                print = (int)seconds;
            System.out.println(print);
            print --;
        }
    }
}
