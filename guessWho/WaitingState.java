//import java.util.Observer;
//import java.util.Observable;

/**
 * Write a description of class WaitingState here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WaitingState extends SimpleGameState implements Observer
{
    GuessWho world;
    GameSession gameSession;
    DummyImage blockLayer;
    EnableButton exitButton;
    public WaitingState(GuessWho world,GameSession gameSession)
    {
        this.world = world;
        this.gameSession = gameSession;
        blockLayer = new DummyImage("backgroundGreyDimCanvas.png");
        exitButton = new EnableButton("exit");
        prepare(); 
    }
    
    private void prepare()
    {
        exitButton.addObserver(this);        
    }
    
    public void enter()
    {
        exitButton.disable();
        world.addObject(blockLayer,world.getWidth()/2,world.getHeight()/2);
        world.addObject(exitButton,world.getWidth()/2,world.getHeight()/2);
        new EnablingButtonTimer(exitButton, 2);
    }
        
    @Override
    public void update(Observable o, Object arg)
    {
        world.setState("guessWhoState");
    }
    
    public void exit()
    {
        world.removeObject(blockLayer);
        world.removeObject(exitButton);
    }
    
}
