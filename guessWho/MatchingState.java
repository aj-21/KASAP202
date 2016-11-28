import greenfoot.*;
//import java.util.Observer;
//import java.util.Observable;
/**
 * Write a description of class IGuessWhoState here.
 * 
 * @author SPAAK 
 * 
 */
public class MatchingState implements GameState,Observer
{
    World world;
    GameSession gameSession;
    DummyImage blockImg;
    EnableButton rt;
    public MatchingState(World world,GameSession gameSession)
    {
        this.world = world;
        this.gameSession = gameSession;
        prepare();
    }
    
    private void prepare()
    {
        //blocking image
        blockImg = new DummyImage("backgroundGreyDimCanvas.png");
        
        //return button
        rt = new EnableButton("return");
        rt.enable();
        rt.addObserver(this);
        
    }
    
    public void enter()
    {
        //for debug 
        System.out.println(gameSession.getMe().getChosenChar().getClass().getName());
        
        world.addObject(blockImg, world.getWidth()/2,world.getHeight()/2);
        world.addObject(rt,world.getWidth()/2,world.getHeight()/4*3);
        String sessionID = AAAAdapter.register(gameSession.getMe());
        //if valid ID
        if(sessionID != "")
        {
            gameSession.setSessionID(sessionID);
            return;
        }
        //if not valid ID
        //handle not connected or sever not available
    }
    
    public void stateRun()
    {
        //get player back every second
        Player you = AAAAdapter.getOpponent();
        // and check if valid player update Opponent(you), and auto exit (start game);
        if (you.getName() != "")
        {
            gameSession.setYou(you);
            exit();
        }
        //
    }
    
    public void exit()
    {
        world.removeObject(blockImg);
        world.removeObject(rt);
        //no player -> delete instance on server, flip back to choose char screen
        if(gameSession.getYou() == null)
        {
            System.out.println("no player");
            ((ChooseCharacterScreen)world).setState("chooseCharState");
            AAAAdapter.deRegister(gameSession.getSessionID());
            return;
        }
        
        //else go to game after 5 seconds
        ((ChooseCharacterScreen)world).setState("startingState");
        System.out.println("game starts");
        
    }
    
    public void update(Observable o, Object arg)
    {
        if(((EnableButton)arg).getLabel().equals("return"))
            exit();
    }
}
