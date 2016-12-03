import greenfoot.*;
/**
 * serves as a state for TimeState decorator
 * 
 * @author SPAAK
 * @version 1
 */
public class StartingState implements GameState
{
    World world;
    GameSession gameSession;
    DummyImage blockImg;
    
    public StartingState(World world,GameSession gameSession)
    {
        this.world = world;
        this.gameSession = gameSession;
        blockImg = new DummyImage("backgroundGreyDimCanvas.png");
    }
    
    public void enter()
    {
        world.addObject(blockImg,blockImg.getImage().getWidth()/2,blockImg.getImage().getHeight()/2);
    
    }
    
    public void stateRun()
    {
        //handle animation if desire
    }
    
    public void exit()
    {
        world.removeObject(blockImg);
    
        Greenfoot.setWorld(new GuessWho(gameSession));
    }
}
