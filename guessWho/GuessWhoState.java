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
        EnableButton guessButton = new EnableButton("guess");
        world.addObject(guessButton,600,850);
        guessButton.addObserver(this);
        
        SimpleContainer ccc = new SimpleContainer(gameSession.getPlaySet());
        addProcess(new UniqueSelection(ccc));
        
        addProcess(new SelectionEnablingButton(ccc,guessButton));
    }
    
    @Override
    public void update (Observable o, Object arg)
    {
        if( ((EnableButton)arg).getLabel() == "guess")
            world.setState("guessingState");
    }
    
}
