import greenfoot.*;
/**
 * matching state will register Me to server to get session ID, and keep checking every 0.5 second to see if there is a valid player joining the room
 * matching state allow return to chooseCharState if do not want to wait
 * 
 * 
 * @author SPAAK 
 * @version 1
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
    
    //display block image, return button, and get sessionID
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
        //else ??
        exit();
    }
    
    //check for valid player every 500 mili seconds
    public void stateRun()
    {
        //check every 500 ms
        if(System.nanoTime() - startTime >= 500*1000000){
            //get player back every second
            Player you = pa.getYou(gameSession.getMe(), gameSession.getSessionID());
            // and check if valid player update Opponent(you), and auto exit (to start game);
            if (you != null && you.getName() != "")
            {
                gameSession.setYou(you);
                //debug
                System.out.println("GameSessionID: " + gameSession.getSessionID());
                System.out.println("Opponent name:  " + gameSession.getYou().getName());
                System.out.println("secret Char: " + gameSession.getYou().getChosenChar().getClass().getName());
                System.out.println("secret Char name: " + gameSession.getYou().getChosenChar().getName());
                System.out.println("time stamp: " + gameSession.getYou().getLastUpdated());
                exit();
            }
            startTime = System.nanoTime();
        }
    }
    
    //remove block image, return button, if there is a valid opponent this will exist to startingstate
    public void exit()
    {
        world.removeObject(blockImg);
        world.removeObject(returnBut);
        //no player -> delete instance on server, flip back to choose char screen
        if(gameSession.getYou() == null)
        {
            System.out.println("no player");
            ((ChooseCharacterScreen)world).setState("chooseCharState");
            pa.delete(gameSession.getSessionID());
            return;
        }
        
        //else go to game after 5 seconds
        ((ChooseCharacterScreen)world).setState("startingState");
        System.out.println("game starts");
    }
    
    //allow return button to call exit
    public void update(Observable o, Object arg)
    {
        if(((EnableButton)arg).getLabel().equals("return"))
            exit();
    }
}
