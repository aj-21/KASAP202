import greenfoot.*;
import java.awt.Color;

/**
 * A decorator of GameState with Time.
 * support setTime in seconds, time has to be greater than 0, default is 30 seconds
 * support display time remaining every second, message can be set by setTimeBoxText method, input needs to have %d as a slot for second remaining
 * location timebox need to be set via setTimeBoxLoc method for 1 time, other wise, no time message is displayed
 * 
 * @author SPAAK
 * @version 1
 */
public class TimeState extends GameStateDecorator
{  
    DummyImage timeBox;
    String timeText;
    int textSize;
    int timeBoxX;
    int timeBoxY;
    World world;
    StringImageFactory sif; 
    long startTime;
    long elapsedTime;
    long remainingTime=0;
    int secRemaining;
    long delay;
    
    /**
     * constructor requires a GameState to decorate
     */
    public TimeState(GameState gameState)
    {
        super(gameState);
        timeText = "Time left\n  %d";
        textSize = 50;
        setTimer(30);
        sif = new StringImageFactory();
    }
    
    /**
     * set Time for state in second
     */
    public void setTimer(int seconds)
    {
        if(seconds > 0)
            delay = seconds*1000;
    }
    
    /**
     * set location of time Text with a world and coordinate
     */
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
    
    /**
     * set size of time box text
     */
    public void setTimeBoxSize(int size)
    {
        this.textSize = size;
    }
    
    /**
     * set text for time box text, 
     * character '%d' is require, otherwise, no time left will be shown
     */
    public void setTimeBoxText(String text)
    {
        this.timeText= text;
    }
    
    /**
     * set text for time box text, 
     * character '%d' is require, otherwise, no time left will be shown
     */
    public void setTimeBoxTextColor(Color color)
    {
        sif.setTextColor(color);
    }
    
    /**
     * helper method to update time box text
     */
    private void updateTimeBox()
    {
        if(timeBox != null)
        {
            
            String text = timeText.replace("%d",String.valueOf(secRemaining));
            timeBox.setImage(sif.createImage(text,textSize));   
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
