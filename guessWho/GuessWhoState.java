import greenfoot.*;
import java.awt.Color;
//import java.util.Observer;
//import java.util.Observable;
/**
 * Write a description of class IGuessWhoState here.
 * 
 * @author SPAAK 
 * 
 */
public class GuessWhoState extends IGameState implements SecondObserver
{
    PressHandler successor;
    GuessWho world;
    int stateTime;
    
    //...............
    DummyImage timeDisplay;
    
    public GuessWhoState(GuessWho world)
    {
        
        this.world = world;
        timeDisplay = new DummyImage("optionButton.png");
        world.addObject(timeDisplay,100,100);
        stateTime = 5;
        printTime(stateTime);
    }
    
    
    public void setSuccessor(PressHandler successor)
    {
        if(this.successor != null)
            successor.setSuccessor(this.successor);

        this.successor = successor;
    }
    
    public void pressHandle(int x, int y)
    {
        if(successor != null)
            successor.pressHandle(x,y);
    }
    
    public void enter()
    {
        stateTime = 5;        
        printTime(stateTime);
    }
    
    public void stateRun()
    {
        if(Greenfoot.mousePressed(null))
        {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            int x = mouse.getX();
            int y = mouse.getY();
            pressHandle(x,y);
        }
    }
    
    public void exit()
    {
        if (world.isCurrentState(this))
            world.setState("scoreState");
    }
    
    public void secondUpdate()
    {
        if (stateTime>0)
        {
            stateTime -= 1;
            printTime(stateTime);
            if(stateTime == 0 )
            {
                exit();
            }
        }
    }
    
    public void printTime(int time)
    {
        GreenfootImage timeImg = new GreenfootImage("optionButton.png");
        int w = timeImg.getWidth();
        int h = timeImg.getHeight(); 
        GreenfootImage labelImage = new GreenfootImage(String.valueOf(time),h/10*5,Color.BLUE,new Color(0,0,0,0));
        timeImg.drawImage(labelImage,(w - labelImage.getWidth())/2 ,h/20*6);
        timeDisplay.setImage(timeImg);
        
    }
    
}
