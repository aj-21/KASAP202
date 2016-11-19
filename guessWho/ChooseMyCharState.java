import greenfoot.*;
//import java.util.Observer;
//import java.util.Observable;
/**
 * Write a description of class IGuessWhoState here.
 * 
 * @author SPAAK 
 * 
 */
public class ChooseMyCharState implements GameState
{
    World world;
    GameSession gameSession;
    public ChooseMyCharState(World world,GameSession gameSession)
    {
        this.world = world;
        this.gameSession = gameSession;
        prepare();
    }
    
    private void prepare()
    {

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
