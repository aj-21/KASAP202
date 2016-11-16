import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import java.util.Map;
//import java.util.Observer;
//import java.util.Observable;
/**
 * Write a description of class GuessWho here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GuessWho extends StatefulWorld
{
    
    GameSession gameSession;
    
    Set<GameState> gameStates = new HashSet<GameState>();
    GameState guessWhoState;
    GameState guessingState;
    GameState filteringState;
    GameState scoringState;
    GameState waitingState;
    
    IMainState mainState = new IMainState(this);
    
    

    public GuessWho(GameSession gameSession)
    {    
        super(1536, 864, 1); 
        this.gameSession = gameSession;
        guessWhoState = new GuessWhoState(this,gameSession);
        scoringState = new ScoringState(this,gameSession);      
        guessingState = new GuessingState(this,gameSession);
        filteringState = new FilteringState(this,gameSession);
        
        waitingState = new WaitingState(this,gameSession);
        setState("guessWhoState");
        setup();
    }
    
    private void setup()
    {
        //charCanvas setting
        ZoomContainer playCon = new ZoomContainer(gameSession.getPlaySet());
        playCon.resizeOnScale(0.8);   
        //DisplayCanvas charCanvas = new DisplayCanvas(gameSession.getPlaySet());
        
        //use this to for chain of responsility
        DisplayCanvas charCanvas = new IDisplayCanvas(gameSession.getPlaySet());
        
        addObject(charCanvas,780,581);
        charCanvas.setBackground("characterCanvas.png").setMargin(3.5,3.5,2.5,2.5).display();     
        
        //myCharCanvas setting
        Character myChar = gameSession.getMyChar();
        myChar.resizeOnScale(1.3);
        DisplayCanvas myCharCanvas = new DisplayCanvas(myChar);
        addObject(myCharCanvas,1370,578);
        myCharCanvas.setBackground("yourCharacterCanvas.png").setColRow(1,1).display();  
        
        
        //suboption canvas setup without any set for display. the display will be handled by UpdateSubOpt class
        DisplayCanvas subOptButCanvas = new IDisplayCanvas();
        addObject(subOptButCanvas,175,580);
        subOptButCanvas.setBackground("subOptionsCanvas.png").setMargin(0,0,2,2);
        //enable unique selection for suboption Button Canvas
        ((IDisplayCanvas)subOptButCanvas).addObserver(new IUniqueSelection());

        
        //updateSubOptionReceiver is responsbile for update subOptionButtonCanvas
        UpdSubOptRcv updSubRcv = new UpdSubOptRcv(subOptButCanvas); 

        //create new option buttons (one time)
        Set<LabelButton> optButSet = new HashSet<LabelButton>();
        for(String option : gameSession.getPropertyInfo().getKeys() )
        {            
            //use LButton for command pattern
            LButton button = new LButton(option,"optionButton.png");
            //new command for every button
            DisplayCommand cmd = new UpdSubOptCmd(gameSession.getPropertyInfo());
            //this command work with update sub option receiver
            cmd.setReceiver(updSubRcv);
            //the button invokes command when is pressed (select/deselect)
            button.setCommand(cmd);
            //add to set
            optButSet.add(button);
        }
        
        //optionButtonCanvas setting
        DisplayCanvas optButCanvas = new IDisplayCanvas(optButSet);
        addObject(optButCanvas,630,255);
        optButCanvas.setBackground("optionCanvas.png").setMargin(2.5,2.5,0,0).setColRow(optButSet.size(),1).display();

        //enable press handler with chain of responsibility
        mainState.setSuccessor((PressHandler)charCanvas);
        mainState.setSuccessor((PressHandler)subOptButCanvas);
        mainState.setSuccessor((PressHandler)optButCanvas);
        
        //transfer option changes to filteringState (for filter later) whenever there is a change
        SelectionObservable optSel = new SelectionObservable(optButSet,(Observer)filteringState);
        guessWhoState.addProcess(optSel);  
        
        //keep either filter or guess
        IUniqueSelection charOrOpt = new IUniqueSelection();
        ((IDisplayCanvas)charCanvas).addObserver(charOrOpt);
        ((IDisplayCanvas)optButCanvas).addObserver(charOrOpt);
        
  
        //transfer info from this selection observable sets to button
        EnableButton testBut = new EnableButton("confirm");
        addObject(testBut,1000,100);
        
        //guessWhoState responsible to direct to correct state, whether filter or guess
        testBut.addObserver((Observer)guessWhoState);
        
        //create a new IObservableSelection to listen to updaSubRcv + subOptButCanvas + charCanvas, and notify confirm Button
        IObservableSelection obSel = new IObservableSelection();
        updSubRcv.addObserver(obSel);
        ((IDisplayCanvas)subOptButCanvas).addObserver(obSel);
        ((IDisplayCanvas)charCanvas).addObserver(obSel);
        obSel.addObserver(testBut);
    }
    
    public GameState getState(String stateName)
    {
        switch (stateName)
        {
            case "guessWhoState": return guessWhoState;
            case "guessingState": return guessingState; 
            case "filteringState": return filteringState;
            case "scoringState": return scoringState;
            case "waitingState": return waitingState;
            default: return guessWhoState;
        }
    }
    
    public void setState(String stateName)
    {
        setState(getState(stateName));
    }
    
    public void act()
    {
        mainState.run();
    }
}
