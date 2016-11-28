import greenfoot.*;
//import java.util.Observer;
//import java.util.Observable;
/**
 * Write a description of class IGuessWhoState here.
 * 
 * @author SPAAK 
 * 
 */
public class ChooseCharState implements GameState,Observer
{
    World world;
    GameSession gameSession;
    public ChooseCharState(World world,GameSession gameSession)
    {
        this.world = world;
        this.gameSession = gameSession;
        prepare();
    }
    
    private void prepare()
    {
        
    }
    
    public void enter()
    {
        
        
    }
    
    public void stateRun()
    {
    }
    
    public void exit()
    {
        SimpleContainer c = new SimpleContainer(gameSession.getFullSet());
        Character myChar = (Character)c.getFirstSelected();
  
        try{
            gameSession.getMe().setChosenChar(myChar.getClass().newInstance());
            //Greenfoot.setWorld(new GuessWho(gameSession));
            ((ChooseCharacterScreen)world).setState("matchingState");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void update(Observable o, Object arg)
    {
        if(((EnableButton)arg).getLabel().equals("confirm"))
            exit();
    }
}
