import greenfoot.*;
/**
 * waiting for confirm button to be clicked and set MyChar
 * 
 * @author SPAAK 
 * @version 1
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
