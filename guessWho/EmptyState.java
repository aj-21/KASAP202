import greenfoot.*;
//import java.util.Observer;
//import java.util.Observable;
/**
 * Write a description of class IGuessWhoState here.
 * 
 * @author SPAAK 
 * 
 */
public class EmptyState implements GameState
{
    World world;
    public EmptyState(World world)
    {
        this.world = world;
    }
    
    public void enter()
    {
        
        
    }
    
    public void stateRun()
    {
    }
    
    public void exit()
    {
        //world.setState("guessWhoState");
    }
    
}
