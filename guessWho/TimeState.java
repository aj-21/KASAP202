import greenfoot.*;
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
    TimerTask secondTask;
    int secRemaining;
    int delay;
    DummyImage timeBox;
    String timeText;
    int textSize;
    int timeBoxX;
    int timeBoxY;
    World world;
    public TimeState(GameState gameState)
    {
        super(gameState);
        timeText = "State will end \n in %d second(s)";
        textSize = 50;
    }
    
    public void setTimer(int seconds)
    {
        delay = seconds;
    }
    
    private void schedule()
    {
        if (delay <= 0)
            return;
        //reset secRemaining
        secRemaining = delay;
        //setup new Timer and tasks
        timer = new Timer();
        timeoutTask = new TimeoutTask(this);
        secondTask = new SecondObservable(this);
        
        //start timer
        timer.schedule(secondTask,0,1000);
        timer.schedule(timeoutTask,delay*1000);
        
    }
    
    public void setTimeBoxLoc(World world,int x, int y)
    {
        if(timeBox == null)
        {
            timeBox = new DummyImage();
            //world.addObject(timeBox,x,y);
            this.timeBoxX = x;
            this.timeBoxY = y;
            this.world = world;
            return;
        }
        timeBox.setLocation(x,y);
    }
    
    public void setTimeBoxSize(int size)
    {
        this.textSize = size;
    }
    
    public void setTimeBoxText(String text)
    {
        this.timeText= text;
    }
    
    @Override 
    public void secondUpdate()
    {
        updateTimeBox();
        if(secRemaining >0)
            secRemaining --;
    }
    
    private void updateTimeBox()
    {
        if(timeBox != null)
        {
            StringImageFactory a = new StringImageFactory();
            String text = timeText.replace("%d",String.valueOf(secRemaining));
            timeBox.setImage(a.createImage(text,textSize));   
        }
    }
    
    @Override 
    public void timeout()
    {
        //cancel timer and tasks
        timer.cancel();
        updateTimeBox();
        world.removeObject(timeBox);
        super.exit();
        
    }
    
    @Override 
    public void enter()
    {
        schedule();
        super.enter();
        world.addObject(this.timeBox,timeBoxX,timeBoxY);
    }
}
