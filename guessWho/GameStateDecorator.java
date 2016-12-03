/**
 * class that enables decorating a GameState
 * 
 * @SPAAK
 * @latest version
 */
public abstract class GameStateDecorator implements GameState
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
