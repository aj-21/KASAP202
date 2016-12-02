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
        yourChar = gameSession.getYou().getChosenChar();

        blockImg = new DummyImage("backgroundGreyDimCanvas.png");
    }
    
    public void enter()
    {
        //display dim image (background)
        world.addObject(blockImg,blockImg.getImage().getWidth()/2,blockImg.getImage().getHeight()/2);
     
        SimpleContainer ccc = new SimpleContainer(gameSession.getPlaySet());
        Character guessedChar = (Character)ccc.getSelected();
        if(guessedChar!=null)
        {
            guess(guessedChar);
            return;
        }
        filter();
        return;
    }
    
    public void stateRun()
    {
        //exchange info, detect player disconnection
        
    }
    
    public void exit()
    {
        SimpleContainer ccc = new SimpleContainer(gameSession.getPlaySet());
        Character guessedChar = (Character)ccc.getSelected();
        //if either player win display result
        if(gameSession.getMe().isFinished() || gameSession.getYou().isFinished())
        {
            Greenfoot.setWorld(new ResultScreen(gameSession));
            return;
        }
        world.removeObject(blockImg);
        world.setState("guessWhoState");
        
    }
       
    public void guess(Character guessedChar)
    {
        //debug
        System.out.println("guessing");
        
        //two step removing
        world.removeObject(guessedChar);
        gameSession.getPlaySet().remove(guessedChar);
        
        //if same class -> win
        if(guessedChar.getClass() == yourChar.getClass())
            gameSession.getMe().setIsFinished(true);
        
        
    }
    
    public void filter()
    {
        //debug
        System.out.println("filter");
        
        SimpleContainer o = new SimpleContainer(gameSession.getPropertyInfo().getOptButtons());
        LButton filterOptBut = (LButton)o.getSelected();
        //if no option is currently selected >> no guess no filter
        if(filterOptBut == null)
            return;
        
        SimpleContainer s = new SimpleContainer(gameSession.getPropertyInfo().getSubOptButtons(filterOptBut));
        LButton filterSubOptBut = (LButton)s.getSelected();
        //if no suboption is currently selected >> no guess no filter
        if(filterSubOptBut == null)
            return;

        String filterKey = filterOptBut.getLabel();
        String filterValue = filterSubOptBut.getLabel();
        PropertyCriteria valueFilter = new CriteriaValue(gameSession,filterKey);
        Set<Character> rmSet = null; 
        
        //if filterValue matches secrete value -> correct filter, remove all that don't match secretValue
        if(filterValue.equals(yourChar.getPropertyValue(filterKey)))
        {
            rmSet = valueFilter.notMeetCriteria(gameSession.getPlaySet());
            //the above equals to
            //rmSet = valueFilter.notMeetCriteria(gameSession.getPlaySet(),yourChar.getPropertyValue(filterKey));
        }
        //if filterValue doesn't match secrete value -> inccorect filter, remove only those who matches selected filter suboption
        else
        {
            rmSet = valueFilter.meetCriteria(gameSession.getPlaySet(),filterValue);
        }

        //two step remove
        if(rmSet != null)
        {
            gameSession.getPlaySet().removeAll(rmSet);
            world.removeObjects(rmSet);
        }
        
    }
    
}
