import greenfoot.*;
import java.util.Set;
import java.util.HashSet;
/**
 * Write a description of class IScoringState here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
