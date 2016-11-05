import java.util.Observer;
import java.util.Observable;
/**
 * Write a description of class GuessWhoState here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GuessWhoState extends SimpleGameState implements Observer
{
    // instance variables - replace the example below with your own
    private int x;
    GuessWho world;
    GameSession gameSession;
    /**
     * Constructor for objects of class GuessWhoState
     */
    public GuessWhoState(GuessWho world,GameSession gameSession)
    {
        this.world = world;
        this.gameSession = gameSession;
        prepare();
    }
    
    public void prepare()
    {

    }
    
    @Override
    public void update (Observable o, Object arg)
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
