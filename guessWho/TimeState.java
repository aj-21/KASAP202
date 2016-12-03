import greenfoot.*;
import java.util.Timer;
import java.util.TimerTask;
/**
 * Write a description of class TimeState here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TimeState extends GameStateDecorator
{  
    DummyImage timeBox;
    String timeText;
    int textSize;
    int timeBoxX;
    int timeBoxY;
    World world;
    
    long startTime;
    long elapsedTime;
    long remainingTime=0;
    int secRemaining;
    long delay;
    public TimeState(GameState gameState)
    {
        super(gameState);
        timeText = "Time left\n  %d";
        textSize = 50;
        setTimer(30);
    }
    
    public void setTimer(int seconds)
    {
        if(seconds > 0)
            delay = seconds*1000;
    }
    
    public void setTimeBoxLoc(World world,int x, int y)
    {
        if(timeBox == null)
        {
            timeBox = new DummyImage();
            
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
    public void enter()
    {
        //get new timestamp
        startTime = System.nanoTime()/1000000; 
        super.enter();
        world.addObject(this.timeBox,timeBoxX,timeBoxY);
    }
    
    @Override
    public void stateRun()
    {
        //keep getting elapsed time
        elapsedTime = System.nanoTime()/1000000 - startTime;
        //detect second        
        remainingTime = delay - elapsedTime;
        if(secRemaining != (int)(remainingTime/1000)+1){
            secRemaining = (int)(remainingTime/1000)+1;
            updateTimeBox();
        }
        
        //detect timeout
        if(remainingTime <=0){
            exit();
            return;
        }
        super.stateRun();
    }
    
    @Override
    public void exit()
    {
        world.removeObject(timeBox);
        super.exit();
    }
}
