import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * flatform for 3 state, chooseCharState, matchingState, and startingState
 * 
 * @author SPAAK 
 * @version 1
 */
public class ChooseCharacterScreen extends StatefulWorld
{
    GameSession gameSession;
    GameState currentState;
    //three main state
    GameState chooseCharState;
    GameState matchingState;
    GameState startingState;
    
    /**
     * Constructor for objects of class chooseCharacterScreen.
     * 
     */
    public ChooseCharacterScreen()
    {    
        // Create a new world with 1536x864 cells with a cell size of 1x1 pixels.
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
        //get the Name
        String name = "";
        while( (name == null) || name.equals(""))
        {
            name = Greenfoot.ask("Please choose a name for yourself");       
        }
        //capilize the first letter
        gameSession.getMe().setName(name.substring(0,1).toUpperCase()+name.substring(1));

              
        //resize FullSet Character
        ZoomContainer fullCon = new ZoomContainer(gameSession.getFullSet());
        fullCon.resizeOnScale(0.9);

        //new displayCanvas to display FullSet, this displayCanvas will have the samebackground as the current world
        IDisplayCanvas fullSetCan = new IDisplayCanvas(gameSession.getFullSet());
        addObject(fullSetCan,getWidth()/2,getHeight()/2);
        fullSetCan.setBackground(getBackground()).setMargin(10,10,15,15).display();
        
        //initial confirmButton to use with ObservableSelection of fullSetCan
        EnableButton confirmButton = new EnableButton("confirm");
        addObject(confirmButton,743,774);
        //chooseCharState will observe this button
        confirmButton.addObserver((Observer)chooseCharState);
        
        //add ObservableSelection observing fullSetCan to enable confirm button
        fullSetCan.addObserver(new ObservableSelection(confirmButton));
        //enable unique selection for fullSet canvas
        fullSetCan.addObserver(new UniqueSelection());
        
        //add "Choose your Character" title to the world
        addObject(new DummyImage("Choose_your_character.png"),getWidth()/2,getHeight()/10);
        
        //add chain responsibility for press handling using GameStateDecorator presshandler decorator
        chooseCharState = new PressHandlerState(chooseCharState); 
        //fullSetCan is IDisplayCanvas which is also a PressHandler successor
        ((PressHandler)chooseCharState).setSuccessor(fullSetCan);
        
        
        //wrap starting state with GameStateDecorator TimeState and set timer for 3 seconds untill game
        ((TimeState)startingState).setTimer(3);
        //set time box text,size, and location for startingState 
        String text = "Hi " + gameSession.getMe().getName() + ", game starts in\n%d second(s)";
        ((TimeState)startingState).setTimeBoxText(text);
        ((TimeState)startingState).setTimeBoxSize(100);
        ((TimeState)startingState).setTimeBoxLoc(this,getWidth()/2,getHeight()/2);
        
        //start with chooseCharState
        setState("chooseCharState");
        
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
    
}

