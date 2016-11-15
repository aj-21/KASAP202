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
        DisplayCanvas charCanvas = new DisplayCanvas(gameSession.getPlaySet());
        addObject(charCanvas,780,581);
        charCanvas.setBackground("characterCanvas.png").setMargin(3.5,3.5,2.5,2.5).display();     
        
        //myCharCanvas setting
        Character myChar = gameSession.getMyChar();
        myChar.setZoomScale(1);
        myChar.resizeOnScale(1.3);
        DisplayCanvas myCharCanvas = new DisplayCanvas(myChar);
        addObject(myCharCanvas,1370,578);
        myCharCanvas.setBackground("yourCharacterCanvas.png").setColRow(1,1).display();  
        
        
         //suboption canvas setup without any set for display. the display will be handled by UpdateSubOpt class
        DisplayCanvas subOptButCanvas = new DisplayCanvas();
        addObject(subOptButCanvas,175,580);
        subOptButCanvas.setBackground("subOptionsCanvas.png").setMargin(0,0,2,2);

        //unique selection process for subopt
        UniqueSelection subOptButUni = new UniqueSelection(subOptButCanvas.getAll());
        guessWhoState.addProcess(subOptButUni);
        
        //updateSubOption
        //UpdateSubOpt updSubOpt = new UpdateSubOpt(gameSession.getPropertyInfo(),subOptButCanvas);
        UpdSubOptRcv updSubRcv = new UpdSubOptRcv(subOptButCanvas);
        
        
        
        //create new option buttons (one time)
        Set<LabelButton> optButSet = new HashSet<LabelButton>();
        for(String option : gameSession.getPropertyInfo().getKeys() )
        {
            ///////////////////////////////////optButSet.add(new LabelButton(option,"optionButton.png"));
            
            //use LButton for command pattern
            LButton button = new LButton(option,"optionButton.png");
            //new command
            DisplayCommand cmd = new UpdSubOptCmd(gameSession.getPropertyInfo());
            //this command work with update sub option receiver
            cmd.setReceiver(updSubRcv);
            //button invokes command when is pressed (select/deselect)
            button.setCommand(cmd);
            //add to set
            optButSet.add(button);
            
            
        }
        
        //optionButtonCanvas setting
        DisplayCanvas optButCanvas = new DisplayCanvas(optButSet);
        addObject(optButCanvas,630,255);
        optButCanvas.setBackground("optionCanvas.png").setMargin(2.5,2.5,0,0).setColRow(optButSet.size(),1).display();

        //transfer option changes to filteringState (for filter later) whenever there is a change
        SelectionObservable optSel = new SelectionObservable(optButSet,(Observer)filteringState);
        guessWhoState.addProcess(optSel);  
        
       
        
        //transfer info from opt selectionObserver to updateSubOption
        ////////////////////////optSel.addObserver(updSubOpt);
        
        //keep either filter or guess
        SimpleContainer testCon = new SimpleContainer(optButSet).addAll(gameSession.getPlaySet());        
        guessWhoState.addProcess(new UniqueSelection(testCon.getAll()));
  
        //selection observable for 2 sets character set and subopt button set
        Set<Set<Selectable>> testSets = new HashSet<Set<Selectable>>();
        testSets.add(subOptButCanvas.getAll());
        testSets.add(charCanvas.getAll());
        SelectionSets testSel = new SelectionSets(testSets);
        //transfer info from this selection observable sets to button
        EnableButton testBut = new EnableButton("confirm");
        testSel.addObserver(testBut);
        //rub this selection observable process 
        guessWhoState.addProcess(testSel);
        addObject(testBut,1000,100);
        //guessWhoState responsible to direct to correct state, whether filter or guess
        testBut.addObserver((Observer)guessWhoState);
        //selection observable info is sent to filterstate, only filter need to know, guessing state can retrieve info from SessionInfo
        testSel.addObserver((Observer)filteringState);
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
}
