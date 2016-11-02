import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import java.util.Map;
import java.util.Observer;
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
    GameState waitingState;

    public GuessWho(GameSession gameSession)
    {    
        super(1536, 864, 1); 
        this.gameSession = gameSession;
        guessWhoState = new GuessWhoState(this,gameSession);
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
        DisplayCanvas charCanvas = new DisplayCanvas(playCon);
        addObject(charCanvas,getWidth()/2,600);
        charCanvas.setBackground("characterCanvas.png").setMargin(3.5,3.5,2.5,2.5).display();     
        
        //myCharCanvas setting
        ZoomContainer myCharCon = new ZoomContainer(gameSession.getMyChar());
        myCharCon.setZoomScale(1);
        myCharCon.resizeOnScale(1.5);
        DisplayCanvas myCharCanvas = new DisplayCanvas(myCharCon);
        addObject(myCharCanvas,1400,600);
        myCharCanvas.setBackground("yourCharacterCanvas.png").setColRow(1,1).display();   
        
        //create new option buttons
        Set<String> optionSet = gameSession.getOptionInfo().getOptions();
        Set<StringButton> optButSet = new HashSet<StringButton>();
        for(String option:optionSet)
            optButSet.add(new StringButton(option));
        
        //optionButtonCanvas setting
        ZoomContainer optButCon = new ZoomContainer(optButSet);
        //optButCon.resizeOnScale(1.5);
        DisplayCanvas optButCanvas = new DisplayCanvas(optButSet);
        addObject(optButCanvas,615,300);
        optButCanvas.setBackground("optionCanvas.png").setColRow(optButSet.size(),1).display();
        
        
        EnableButton filterButton = new EnableButton("filter");
        addObject(filterButton,130,700);
        filterButton.addObserver((Observer)guessWhoState);
        
        
        
        
        
        
        
        
        
        
        
        
        //optional
        //unqine selection process for optionbuttons
        guessWhoState.addProcess(new SelectionObservable(optButSet,filterButton));
        guessWhoState.addProcess(new UniqueSelection(optButSet));
        
        //keep either filter or guess
        SimpleContainer testCon = new SimpleContainer(optButSet).addAll(gameSession.getPlaySet());        
        guessWhoState.addProcess(new UniqueSelection(testCon));
        
        

    }
    
    public GameState getState(String stateName)
    {
        switch (stateName)
        {
            case "guessWhoState": return guessWhoState;
            case "guessingState": return guessingState; 
            case "filteringState": return filteringState;
            case "waitingState": return waitingState;
            default: return guessWhoState;
        }
    }
    
    public void setState(String stateName)
    {
        setState(getState(stateName));
    }
}
