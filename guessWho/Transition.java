import greenfoot.World;
import java.util.Observer;
import java.util.Observable;
/**
 * Write a description of class Transition here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Transition implements Observer
{
    GuessWho world;
    GameSession gameSession;
    Object lastArg;
    public Transition(GuessWho world,GameSession gameSession)
    {
        this.world = world;
        this.gameSession = gameSession;
    }

    @Override
    public void update(Observable o, Object arg)
    {
        System.out.println(arg.getClass().getName());
        if(arg instanceof EnableButton)
        {
            transit();
            return;
        }
        lastArg = arg;        
    }
    
    private void transit()
    {
        SimpleContainer ccc = new SimpleContainer(gameSession.getPlaySet());
        if(ccc.getSelected() != null)
        {
            world.setState("guessingState");
            return;
        }
        world.setState("filteringState");        
    }
}
