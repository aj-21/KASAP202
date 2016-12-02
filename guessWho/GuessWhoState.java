import greenfoot.*;
import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
//import java.util.Observer;
//import java.util.Observable;
/**
 * Write a description of class IGuessWhoState here.
 * 
 * @author SPAAK 
 * 
 */
public class GuessWhoState implements GameState
{
    PressHandler successor;
    GuessWho world;
    GameSession gameSession;
    Iterator insIter;
    DummyImage myMsg;
    DummyImage yourMsg;
    DummyImage insMsg;
    
    public GuessWhoState(GuessWho world,GameSession gameSession)
    {
        this.world = world;
        this.gameSession = gameSession;
        myMsg = new DummyImage();
        yourMsg = new DummyImage();
        insMsg = new DummyImage();
        InstructionRepository insRepo = new InstructionRepository();
        insIter = insRepo.getIterator();
    }
 

    
    public void enter()
    {
        if (myMsg.getWorld() == null)
        {
            world.addObject(myMsg,550,120);
            world.addObject(yourMsg,550,150);
            world.addObject(insMsg,550,180);
        }
        
        updateMessages();
    }
    
    public void stateRun()
    {

    }
    
    public void exit()
    {
        world.setState("scoreState");
    }
    
    private void updateMessages()
    {
        StringImageFactory a = new StringImageFactory();
        
        String msg = gameSession.getMe().getLastAction();
        if(msg != "")
            msg = "You " + msg;  
        a.setTextColor(Color.BLUE);
        myMsg.setImage(a.createImage(msg,30));
        
        msg = gameSession.getYou().getLastAction();
        if(msg != "")
            msg = "Your opponent, " + gameSession.getYou().getName()+ ", " + msg;     
        a.setTextColor(Color.GRAY);
        yourMsg.setImage(a.createImage(msg,30));
        
        if(insIter.hasNext())
        {
            msg = "Hint: " + (String)insIter.next();
            a.setTextColor(Color.CYAN);
            insMsg.setImage(a.createImage(msg,30));
        }
    }
    
}
