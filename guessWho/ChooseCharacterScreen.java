import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Set;
import java.util.HashSet;

/**
 * Write a description of class chooseCharacterScreen here.
 * 
 * @SPAAK 
 * @version (a version number or a date)
 */
public class ChooseCharacterScreen extends StatefulWorld
{
    GameSession gameSession;
    GameState currentState;
    GameState chooseCharState;
    GameState matchingState;
    GameState startingState;
    /**
     * Constructor for objects of class chooseCharacterScreen.
     * 
     */
    public ChooseCharacterScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1536, 864, 1); 
        gameSession = new GameSession();
        chooseCharState = new ChooseCharState(this,gameSession);
        matchingState = new MatchingState(this,gameSession);
        startingState = new TimeState(new StartingState(this,gameSession));
        

        prepare();
    }


    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {               
        if(gameSession.getMe().getName() == "")
        {
            String name = Greenfoot.ask("Please choose a name for yourself"); 
            gameSession.getMe().setName(name);
        }
        //System.out.println(gameSession.getMe().getName());
              
        //resize FullSet Character
        ZoomContainer fullCon = new ZoomContainer(gameSession.getFullSet());
        fullCon.resizeOnScale(0.9);

        //new displayCanvas to display FullSet
        IDisplayCanvas disCan = new IDisplayCanvas(gameSession.getFullSet());
        addObject(disCan,getWidth()/2,getHeight()/2);
        disCan.setBackground(getBackground()).setMargin(10,10,15,15).display();
        
        //initial confirmButton
        EnableButton confirmButton = new EnableButton("confirm");
        addObject(confirmButton,743,774);
        
        confirmButton.addObserver((Observer)chooseCharState);
        
        //add IObservableSelection  observing disCan to enable confirm button
        disCan.addObserver(new IObservableSelection(confirmButton));
        disCan.addObserver(new IUniqueSelection());
        
        addObject(new DummyImage("Choose_your_character.png"),getWidth()/2,getHeight()/10);
        
        //add chain responsibility for press handling
        chooseCharState = new PressHandlerState(chooseCharState);       
        ((PressHandler)chooseCharState).setSuccessor(disCan);
        
        currentState = chooseCharState;
        //set timer for startingState
        ((TimeState)startingState).setTimer(5);
        ((TimeState)startingState).setTimeBoxLoc(this,getWidth()/2,getHeight()/2);
        String text = "Hi " + gameSession.getMe().getName() + ", game starts in\n %d second(s)";
        ((TimeState)startingState).setTimeBoxText(text);
        ((TimeState)startingState).setTimeBoxSize(100);
    }
    
    public void act()
    {   
        currentState.stateRun();
    }
    
    public GameState getState(String stateName)
    {
        String sName = stateName.toLowerCase().replace(" ","");
        switch(sName)
        {
            case "matchingstate": return matchingState;
            case "startingstate": return startingState;
            default: return chooseCharState;
        }
    }
    
    public void setState(String stateName)
    {
        
        currentState = getState(stateName);
        currentState.enter();
        
        //for debug
        System.out.println(currentState.getClass().getName());
    }
    
    public GameState getCurrentState()
    {
        return null;
    }
    
    public boolean isCurrentState(GameState gameState)
    {
        return false;
    }
    
}

