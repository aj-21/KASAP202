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
    //need an image for return button
    EnableButton returnBut;
    PlayerAdapter pa;
    
    long startTime;
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
        pa = new PlayerAdapter();
        //return button
        returnBut = new EnableButton("return");
        returnBut.enable();
        returnBut.addObserver(this);
        //set start time
        startTime = System.nanoTime();
    }
    
    public void enter()
    {
        //for debug 
        System.out.println(gameSession.getMe().getChosenChar().getClass().getName());
        
        world.addObject(blockImg, world.getWidth()/2,world.getHeight()/2);
        world.addObject(returnBut,world.getWidth()/2,world.getHeight()/4*3);
        
        String sessionID = pa.registerMe(gameSession.getMe());
        //if valid ID
        if(sessionID != "")
        {
            gameSession.setSessionID(sessionID);
            System.out.println("GameSessionID " + gameSession.getSessionID());
            
            return;
        }
        //if not valid ID
        //handle not connected or sever not available
    }
    
    public void stateRun()
    {
        //check every 300 ms
        if(System.nanoTime() - startTime >= 300*1000000){
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
        }
        
        //
    }
    
    public void exit()
    {
        world.removeObject(blockImg);
        world.removeObject(returnBut);
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
