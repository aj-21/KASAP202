import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Set;
import java.awt.Color;

/**
 * flatform for 2 state, guessWhoState and scoreState
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GuessWho extends StatefulWorld
{
    
    GameSession gameSession;

    GameState currentState; 
    GameState guessWhoState;
    GameState scoreState;
    
    

    public GuessWho(GameSession gameSession)
    {    
        super(1536, 864, 1); 
        this.gameSession = gameSession;
        //delete fullSet to free memory
        gameSession.getFullSet().clear();
        
        guessWhoState = new GuessWhoState(this,gameSession);
        scoreState = new ScoreState(this,gameSession);
        
        setup();
    }
    
    private void setup()
    {
        //draw a "guessWho" title and add to the world
        StringImageFactory a = new StringImageFactory();
        a.setTextColor(new Color(128,0,128));
        DummyImage text = new DummyImage(a.createImage("Guess Who",100));
        addObject(text,getWidth()/2,50);
        
        
        //resize play set down to 80%
        ZoomContainer playCon = new ZoomContainer(gameSession.getPlaySet());
        playCon.resizeOnScale(0.8);   
        //myCharCanvas setting, no press handle, just display
        Character myChar = gameSession.getMe().getChosenChar();
        myChar.resizeOnScale(1.3);
        DisplayCanvas myCharCanvas = new DisplayCanvas(myChar);
        addObject(myCharCanvas,1370,578);
        myCharCanvas.setBackground("yourCharacterCanvas.png").setColRow(1,1).display();  
        
        
        //playSet canvas set up., itwill display playSet from gameSession. IDisplayCanvas is used for chain of responsility presshandler
        DisplayCanvas playSetCanvas = new IDisplayCanvas(gameSession.getPlaySet());
        addObject(playSetCanvas,780,581);
        playSetCanvas.setBackground("characterCanvas.png").setMargin(3.5,3.5,2.5,2.5).display();    
        

        //get option buttons set from propertyInfo in gameSession
        Set<LButton> optButSet = gameSession.getPropertyInfo().getOptButtons();
        //and initialize and setup option button canvas to display this option button set, IDisplayCanvas is used for chain of responsibility
        DisplayCanvas optButCanvas = new IDisplayCanvas(optButSet);
        addObject(optButCanvas,630,255);
        optButCanvas.setBackground("optionCanvas.png").setMargin(2.5,2.5,0,0).setColRow(optButSet.size(),1).display();
        
        
        //To keep both keep either one option button or one character is selected at a time, both IDisplayCanvases have the same UniqueSelection Observer
        UniqueSelection guessOrFilter = new UniqueSelection();
        ((IDisplayCanvas)playSetCanvas).addObserver(guessOrFilter);
        ((IDisplayCanvas)optButCanvas).addObserver(guessOrFilter);
        
        
        //suboption canvas setup without button to display because suboption button will be dynamically displayed depending on which option button is click
        //the display task will be handled by a seperate class UpdateSubOptReceiver
        DisplayCanvas subOptButCanvas = new IDisplayCanvas();
        addObject(subOptButCanvas,175,580);
        subOptButCanvas.setBackground("subOptionsCanvas.png").setMargin(0,0,2,2);
        //enable unique selection for suboption Button Canvas
        ((IDisplayCanvas)subOptButCanvas).addObserver(new UniqueSelection());
        
        
        //updateSubOptionReceiver (UpdSubOptRcv) is responsbile for update subOptionButtonCanvas
        UpdSubOptRcv updSubRcv = new UpdSubOptRcv(subOptButCanvas); 
        //updateSubOptionReceiver (UpdSubOptRcv) will receive commands from option buttons, option buttons need to be able to send command (command pattern)
        for(LButton button : optButSet )
        {            
            //new command for every button
            DisplayCommand cmd = new UpdSubOptCmd(gameSession.getPropertyInfo());
            //this command work with update sub option receiver
            cmd.setReceiver(updSubRcv);
            //the button invokes command when is pressed (select/deselect)
            button.setCommand(cmd);
        }


        //enable Press handling ability for guessWhoState with GameStateDecorator PressHandlerState
        //and setup other pressHandler playSetCanvas, optButCanvas, subOptButCanvas, to handler press events as a chain of responsibility
        PressHandlerState guessWhoStateWithPress = new PressHandlerState(guessWhoState);
        guessWhoStateWithPress.setSuccessor((PressHandler)playSetCanvas);
        guessWhoStateWithPress.setSuccessor((PressHandler)subOptButCanvas);
        guessWhoStateWithPress.setSuccessor((PressHandler)optButCanvas);
        guessWhoState = guessWhoStateWithPress;
        

        //showProperty setup will show character property based on selected option and suboption
        //it need to observe event from optButCanvas, subOptButCanvas, and also playSetCanvas
        ShowProperty showProperty = new ShowProperty(this,gameSession);
        ((IDisplayCanvas)optButCanvas).addObserver(showProperty);
        ((IDisplayCanvas)subOptButCanvas).addObserver(showProperty);
        ((IDisplayCanvas)playSetCanvas).addObserver(showProperty);
        
        
        //setting timer to display for scoreState
        scoreState= new TimeState( scoreState);
        ((TimeState)scoreState).setTimer(5);
        ((TimeState)scoreState).setTimeBoxLoc(this,getWidth()/2,getHeight()/2);
        ((TimeState)scoreState).setTimeBoxTextColor(Color.YELLOW);
        ((TimeState)scoreState).setTimeBoxText("Be patient and strategic,\nin %d second(s)\nyou will know your result");
        ((TimeState)scoreState).setTimeBoxSize(100);
        
        //setting timer for guessWhoState
        guessWhoState = new TimeState( guessWhoState);
        ((TimeState)guessWhoState).setTimeBoxLoc(this,1370,260);
        ((TimeState)guessWhoState).setTimer(25);
        ((TimeState)guessWhoState).setTimeBoxBkgrndColor(new Color(128,0,128,128));
        ((TimeState)guessWhoState).setTimeBoxText(" TIME LEFT \n%d");
        
        //start game with guessWhoState
        setState("guessWhoState");
    }
    
    public GameState getState(String stateName)
    {
        switch (stateName)
        {
            case "guessWhoState": return guessWhoState;
            case "scoreState": return scoreState;
            
            default: return guessWhoState;
        }
        
    }
    
    public void setState(String stateName)
    {
        currentState = getState(stateName);
        currentState.enter();
    }
    
    public void act()
    {
        currentState.stateRun();
    }
}
