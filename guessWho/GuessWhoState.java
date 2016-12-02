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
    DummyImage myMsg;
    DummyImage yourMsg;
    
    public GuessWhoState(GuessWho world,GameSession gameSession)
    {
        this.world = world;
        this.gameSession = gameSession;
        myMsg = new DummyImage();
        yourMsg = new DummyImage();
    }
 

    
    public void enter()
    {
        if (myMsg.getWorld() == null)
        {
            world.addObject(myMsg,550,130);
            world.addObject(yourMsg,550,170);
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
        System.out.println(msg);
        myMsg.setImage(a.createImage(msg,30));
        
        msg = gameSession.getYou().getLastAction();
        if(msg != "")
            msg = "Your opponent, " + gameSession.getYou().getName()+ ", " + msg;
            
        System.out.println(msg);
        yourMsg.setImage(a.createImage(msg,30));
    }
    
}
