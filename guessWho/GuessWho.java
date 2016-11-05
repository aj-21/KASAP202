import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import java.util.Map;
import java.util.Observer;
import java.util.Observable;
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
        ZoomContainer myCharCon = new ZoomContainer(gameSession.getMyChar());
        myCharCon.setZoomScale(1);
        myCharCon.resizeOnScale(1.5);
        DisplayCanvas myCharCanvas = new DisplayCanvas(gameSession.getMyChar());
        addObject(myCharCanvas,1370,578);
        myCharCanvas.setBackground("yourCharacterCanvas.png").setColRow(1,1).display();  
        
        //filter button
        EnableButton filterButton = new EnableButton("filter");
        addObject(filterButton,200,100);
        filterButton.addObserver((Observer)guessWhoState);
        
        //create new option buttons (one time)
        Set<String> optionSet = gameSession.getPropertyInfo().getKeys();
        Set<LabelButton> optButSet = new HashSet<LabelButton>();
        for(String option:optionSet)
            optButSet.add(new LabelButton(option,"optionButton.png"));
        
        //optionButtonCanvas setting
        DisplayCanvas optButCanvas = new DisplayCanvas(optButSet);
        addObject(optButCanvas,630,255);
        optButCanvas.setBackground("optionCanvas.png").setMargin(2.5,2.5,0,0).setColRow(optButSet.size(),1).display();

        //transfer option changes to filteringState (for filter later)
        SelectionObservable optSel = new SelectionObservable(optButSet,(Observer)filteringState);
        guessWhoState.addProcess(optSel);
        
        
        
        //suboption canvas setup without any set for display. the display will be handled by UpdateSubOpt class
        DisplayCanvas subOptButCanvas = new DisplayCanvas();
        addObject(subOptButCanvas,175,580);
        subOptButCanvas.setBackground("subOptionsCanvas.png").setMargin(0,0,2,2);

        //unique selection process for subopt
        UniqueSelection subOptButUni = new UniqueSelection(subOptButCanvas.getAll());
        guessWhoState.addProcess(subOptButUni);
        
        //initialize selectionObservable process for subOpt, transfering to filteringState (for later filtering), filterButton
        SelectionObservable subOptSel = new SelectionObservable(subOptButCanvas.getAll());
        subOptSel.addObserver((Observer)filteringState);
        subOptSel.addObserver(filterButton);
        guessWhoState.addProcess(subOptSel);
        
        //updateSubOption
        UpdateSubOpt updSubOpt = new UpdateSubOpt(this,gameSession.getPropertyInfo(),subOptButCanvas);
        
        //transfer info from opt selectionObserver to updateSubOption
        optSel.addObserver(updSubOpt);
        
        //keep either filter or guess
        SimpleContainer testCon = new SimpleContainer(optButSet).addAll(gameSession.getPlaySet());        
        guessWhoState.addProcess(new UniqueSelection(testCon.getAll()));
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
