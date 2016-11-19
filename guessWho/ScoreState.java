import greenfoot.*;
import java.util.Set;
import java.util.HashSet;
/**
 * Write a description of class IScoringState here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScoreState implements GameState
{
    GuessWho world;
    GameSession gameSession;
    Character yourChar;
    DummyImage blockImg;
    
    public ScoreState(GuessWho world,GameSession gameSession)
    {
        this.world = world;
        this.gameSession = gameSession;
        yourChar = gameSession.getYourChar();

        blockImg = new DummyImage("backgroundGreyDimCanvas.png");
    }
    
    public void enter()
    {
        world.addObject(blockImg,blockImg.getImage().getWidth()/2,blockImg.getImage().getHeight()/2);
     
        SimpleContainer ccc = new SimpleContainer(gameSession.getPlaySet());
        Character guessedChar = (Character)ccc.getSelected();
        if(guessedChar!=null)
        {
            guess(guessedChar);
            return;
        }
        filter();
    }
    
    public void stateRun()
    {
        //update info
        //display result
    }
    
    public void exit()
    {
        world.removeObject(blockImg);
        world.setState("guessWhoState");
        
    }
    
       
    public void guess(Character guessedChar)
    {
        System.out.println("guessing");
        if(guessedChar.getClass() != yourChar.getClass())
        {
            //two step removing
            world.removeObject(guessedChar);
            gameSession.getPlaySet().remove(guessedChar);

            return;
        }
        //else win 

        
    }
    
    public void filter()
    {
        System.out.println("filter");
        SimpleContainer o = new SimpleContainer(gameSession.getPropertyInfo().getOptButtons());
        LButton filterOptBut = (LButton)o.getSelected();
        
        //if no option is currently selected >> no guess no filter
        if(filterOptBut == null)
            return;
        
        SimpleContainer s = new SimpleContainer(gameSession.getPropertyInfo().getSubOptButtons(filterOptBut));
        LButton filterSubOptBut = (LButton)s.getSelected();
        if(filterSubOptBut == null)
            return;

        
        String filterKey = filterOptBut.getLabel();
        
        PropertyCriteria valueFilter = new CriteriaValue(gameSession,filterKey);
        Set<Character> rmSet = valueFilter.notMeetCriteria(gameSession.getPlaySet());
        
        //two step remove
        gameSession.getPlaySet().removeAll(rmSet);
        world.removeObjects(rmSet);
        
    }
    
}
