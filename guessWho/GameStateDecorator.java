/**
 * Write a description of class GameStateDecorator here.
 * 
 * @SPAAK
 * @latest version
 */
public class GameStateDecorator implements GameState
{
    GameState gameState;
    public GameStateDecorator(GameState gameState)
    {
        this.gameState=gameState;
    }
    
    @Override
    public void enter()
    {
        gameState.enter();
    }
    
    @Override 
    public void stateRun()
    {
        gameState.stateRun();
    }
    
    @Override 
    public void exit()
    {
        gameState.exit();
    }
    
}
