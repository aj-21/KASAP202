import greenfoot.World;
/**
 * Write a description of class InteractiveState here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InteractiveState implements GameState,ButtonRunnable
{
    World gameWorld;
    
    public InteractiveState(World game)
    {
        this.gameWorld = game;
    }
    
    @Override
    public void run()
    {
        
    }
    
    @Override
    public void buttonClickedRun(ButtonConfirm button)
    {
        if(button.getLabel() == "guessing")
            ((GuessWho)gameWorld).setGuessingState();
        if(button.getLabel() == "filtering")
            ((GuessWho)gameWorld).setFilteringState();
    }
}
