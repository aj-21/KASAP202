import greenfoot.*;
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
    public GuessWhoState(GuessWho world)
    {
        this.world = world;
        //stateTime = 5;
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
            System.out.println(stateTime);
            if(stateTime == 0 )
            {
                exit();
            }
        }
    }
    
}
