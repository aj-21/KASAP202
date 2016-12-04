import greenfoot.*;
import java.util.Set;
/**
 * ScoreState performs guess and filter tasks as well and exchanges info with restlet server
 * 
 * @author SPAAK 
 * @version 1
 */
public class ScoreState implements GameState
{
    StatefulWorld world;
    GameSession gameSession;
    DummyImage blockImg;
    
    PlayerAdapter pa;
    long startTime;
    
    /**
     * constructor requires a world which state resides and gameSession for accessing info
     */
    public ScoreState(StatefulWorld world,GameSession gameSession)
    {
        this.world = world;
        this.gameSession = gameSession;
        
        pa = new PlayerAdapter();
        blockImg = new DummyImage("backgroundGreyDimCanvas.png");
        startTime = System.nanoTime();
    }
    
    /**
     * when enter, displaying a block image on top, and investigate to see if player choose to guess or filter
     * then update Me to server
     */
    public void enter()
    {
        //display dim image (background)
        world.addObject(blockImg,blockImg.getImage().getWidth()/2,blockImg.getImage().getHeight()/2);
     
        SimpleContainer ccc = new SimpleContainer(gameSession.getPlaySet());
        Character guessedChar = (Character)ccc.getSelected();
        //if there exists a selection in playset -> guess
        if(guessedChar!=null)
        {
            guess(guessedChar);
            //update after guess
            pa.updateMe(gameSession.getMe(),gameSession.getSessionID());
            return;
        }
        //else filter
        filter();
        //update after filter
        pa.updateMe(gameSession.getMe(),gameSession.getSessionID());
    }
    
    //do nothing in run
    public void stateRun()
    {
        
    }
    
    /**
     * remove the block image
     * when exits, get updated opponent, and investigate if game is finished (either player isfinished) or disconnected then go to result screen
     * else go back to guesswho state to continue the game
     * 
     */
    public void exit()
    {
        //get opponent from server
        Player you = pa.getYou(gameSession.getMe(),gameSession.getSessionID());
        //if session is deleted or timestamp remains the same => no update => disconnected
        if(gameSession.getYou().getLastUpdated().equals(you.getLastUpdated()))
        {
            Greenfoot.setWorld(new ResultScreen(gameSession));
            return;
        }
        
        //else update You
        gameSession.setYou(you);
        
        //if either player win display result
        if(gameSession.getMe().isFinished() || gameSession.getYou().isFinished())
        {
            Greenfoot.setWorld(new ResultScreen(gameSession));
            return;
        }
        
        //else continue the game
        world.removeObject(blockImg);
        world.setState("guessWhoState");
        
    }
       
    private void guess(Character guessedChar)
    {
        //debug
        System.out.println("guessing");
        
        //two step removing
        world.removeObject(guessedChar);
        gameSession.getPlaySet().remove(guessedChar);
        
        //if same class -> win
        //System.out.println("guessedChar: " + guessedChar.getClass().getName());
        //System.out.println("yourChar: " + yourChar.getClass().getName());
        //System.out.println("guessing correct: " + guessedChar.getClass().getName().equals(yourChar.getClass().getName()));
        if(guessedChar.getClass() == gameSession.getYou().getChosenChar().getClass())
        
            gameSession.getMe().setIsFinished(true);
        
        gameSession.getMe().setLastAction("had a WRONG guess, and eliminate 1 tile");     
    }
    
    /**
     * Three cases:
     * 1. not even filter
     * 2. correct filter
     * 3. incorrect filter
     */
    private void filter()
    {
        //debug
        System.out.println("filter");
        
        SimpleContainer o = new SimpleContainer(gameSession.getPropertyInfo().getOptButtons());
        LButton filterOptBut = (LButton)o.getSelected();
        
        gameSession.getMe().setLastAction("was thinking and chose not to act");
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
        if(filterValue.equals(gameSession.getYou().getChosenChar().getPropertyValue(filterKey)))
        {
            rmSet = valueFilter.notMeetCriteria(gameSession.getPlaySet());
            String msg = "had a CORRECT filter, and eliminate " + rmSet.size()+" tile";
            if(rmSet.size() >1)
                msg+="s";
            gameSession.getMe().setLastAction( msg);
        }
        //if filterValue doesn't match secrete value -> inccorect filter, remove only those who matches selected filter suboption
        else
        {
            rmSet = valueFilter.meetCriteria(gameSession.getPlaySet(),filterValue);
            String msg = "had an INCCORECT filter, and eliminate " + rmSet.size()+" tile";
            if(rmSet.size() >1)
                msg+="s";
            gameSession.getMe().setLastAction( msg);
        }
        //two step remove
        if(rmSet != null)
        {
            gameSession.getPlaySet().removeAll(rmSet);
            world.removeObjects(rmSet);
        }
        
    }
    
}
