import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Set;
import java.util.HashSet;

/**
 * Write a description of class chooseCharacterScreen here.
 * 
 * @SPAAK 
 * @version (a version number or a date)
 */
public class ChooseCharacterScreen extends StatefulWorld implements Observer
{
    GameSession gameSession;
    GameState mainState;
    /**
     * Constructor for objects of class chooseCharacterScreen.
     * 
     */
    public ChooseCharacterScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1536, 864, 1); 
        gameSession = new GameSession();
        mainState = new ChooseMyCharState(this,gameSession);
        prepare();
    }


    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {               
        //String name = Greenfoot.ask("name please");   
        //System.out.println(name);
        //resize FullSet Character
        ZoomContainer fullCon = new ZoomContainer(gameSession.getFullSet());
        fullCon.resizeOnScale(0.9);
        //new displayCanvas to display FullSet
        DisplayCanvas disCan = new IDisplayCanvas(gameSession.getFullSet());
        //enable unique selection
        ((IDisplayCanvas)disCan).addObserver(new IUniqueSelection());
        
        disCan.setBackground(getBackground()).setMargin(10,10,18,15);
        addObject(disCan,getWidth()/2,getHeight()/2);
        disCan.display();       
        
        //initial confirmButton
        EnableButton confirmButton = new EnableButton("confirm");
        addObject(confirmButton,743,774);
        
        confirmButton.addObserver(this);
        
        //add IObservableSelection  observing disCan to enable confirm button
        ((IDisplayCanvas)disCan).addObserver(new IObservableSelection(confirmButton));
        
        addObject(new DummyImage("Choose_your_character.png"),getWidth()/2,getHeight()/10);
        
        
        
        //add chain responsibility for press handling
        mainState = new PressHandlerState(mainState);
        ((PressHandlerState)mainState).setSuccessor((PressHandler)disCan);

    }
    
    public void act()
    {   
        mainState.stateRun();
    }
   
    @Override
    public void update(Observable o, Object arg)
    {
       if(((EnableButton)arg).getLabel().equals("confirm"))
            exit();
    }
    
    private void exit()
    {
        SimpleContainer c = new SimpleContainer(gameSession.getFullSet());
        Character myChar = (Character)c.getFirstSelected();
  
        try{
            gameSession.getMe().setChosenChar(myChar.getClass().newInstance());
            Greenfoot.setWorld(new GuessWho(gameSession));
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        
    }
    
    public GameState getState(String stateName)
    {
        return null;
    }
    
    public void setState(String stateName)
    {
        return;
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

