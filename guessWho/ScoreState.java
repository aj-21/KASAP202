import greenfoot.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import java.text.SimpleDateFormat;
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
    
    PlayerAdapter pa;
    long startTime;
    public ScoreState(GuessWho world,GameSession gameSession)
    {
        this.world = world;
        this.gameSession = gameSession;
        yourChar = gameSession.getYou().getChosenChar();
        pa = new PlayerAdapter();
        blockImg = new DummyImage("backgroundGreyDimCanvas.png");
        startTime = System.nanoTime();
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
            pa.updateMe(gameSession.getMe(),gameSession.getSessionID());
            return;
        }
        
        filter();
        pa.updateMe(gameSession.getMe(),gameSession.getSessionID());
        return;
    }
    
    public void stateRun()
    {
        //exchange info, detect player disconnection
        /*if(System.nanoTime() - startTime >= 1000*1000000){
            //get player back every second
            Player you = pa.getPlayer(gameSession.getMe(), gameSession.getSessionID());
            // and check if valid player update Opponent(you), and auto exit (start game);
            if (you != null && you.getName() != "")
            {
                gameSession.setYou(you);
                System.out.println("GameSessionID: " + gameSession.getSessionID());
                System.out.println("Opponent name:  " + gameSession.getYou().getName());
                System.out.println("secret Char: " + gameSession.getYou().getChosenChar().getClass().getName());
                System.out.println("secret Char name: " + gameSession.getYou().getChosenChar().getName());
                exit();
            }
            startTime = System.nanoTime();
        }*/
        
    }
    
    public void exit()
    {
        Player you = pa.getYou(gameSession.getMe(),gameSession.getSessionID());
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        //SimpleDateFormat format = new SimpleDateFormat("ss S");
        Date old = null;
        try
        {
            old = format.parse(gameSession.getYou().getLastUpdated());
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        //debug
        System.out.println("time old: " + gameSession.getYou().getLastUpdated());
        System.out.println("time new: " + you.getLastUpdated());
        
        long diff = 0;
        try{
            diff = (format.parse(you.getLastUpdated()).getTime() - old.getTime())/1000;
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        
        
        System.out.println("time diff: " + diff);
        if (diff >= 30)
        {
            Greenfoot.setWorld(new ResultScreen(gameSession));
            return;
        }
        
        gameSession.setYou(you);
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
        System.out.println("guessedChar: " + guessedChar.getClass().getName());
        System.out.println("yourChar: " + yourChar.getClass().getName());
        System.out.println("guessing correct: " + guessedChar.getClass().getName().equals(yourChar.getClass().getName()));
        if(guessedChar.getClass().getName().equals(yourChar.getClass().getName()))
        {
        
            gameSession.getMe().setIsFinished(true);
            
        }
        
        gameSession.getMe().setLastAction("has a WRONG guess, and eliminate 1 tile");
            
        
        
        
    }
    
    public void filter()
    {
        //debug
        System.out.println("filter");
        
        SimpleContainer o = new SimpleContainer(gameSession.getPropertyInfo().getOptButtons());
        LButton filterOptBut = (LButton)o.getSelected();
        
        gameSession.getMe().setLastAction("has a INCCORECT filter, and eliminate 0 tile");
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
            String msg = "has a CCORECT filter, and eliminate " + rmSet.size()+" tile";
            gameSession.getMe().setLastAction( msg);
            //the above equals to
            //rmSet = valueFilter.notMeetCriteria(gameSession.getPlaySet(),yourChar.getPropertyValue(filterKey));
        }
        //if filterValue doesn't match secrete value -> inccorect filter, remove only those who matches selected filter suboption
        else
        {
            
            rmSet = valueFilter.meetCriteria(gameSession.getPlaySet(),filterValue);
            String msg = "has a INCCORECT filter, and eliminate " + rmSet.size()+" tile";
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
