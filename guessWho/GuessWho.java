import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import java.util.Map;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Write a description of class GuessWho here.
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
        
        guessWhoState = new GuessWhoState(this);
        scoreState = new ScoreState(this,gameSession);
        
        
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
        Set<LButton> optButSet = gameSession.getPropertyInfo().getOptButtons();
        for(LButton button : optButSet )
        {            
            //new command for every button
            DisplayCommand cmd = new UpdSubOptCmd(gameSession.getPropertyInfo());
            //this command work with update sub option receiver
            cmd.setReceiver(updSubRcv);
            //the button invokes command when is pressed (select/deselect)
            button.setCommand(cmd);
        }
        
        //optionButtonCanvas setting
        DisplayCanvas optButCanvas = new IDisplayCanvas(optButSet);
        addObject(optButCanvas,630,255);
        optButCanvas.setBackground("optionCanvas.png").setMargin(2.5,2.5,0,0).setColRow(optButSet.size(),1).display();
        //((IDisplayCanvas)optButCanvas).addObserver(new IUniqueSelection());

        //enable press handler with chain of responsibility
        PressHandlerState pressState = new PressHandlerState(guessWhoState);
        pressState.setSuccessor((PressHandler)charCanvas);
        pressState.setSuccessor((PressHandler)subOptButCanvas);
        pressState.setSuccessor((PressHandler)optButCanvas);
        guessWhoState = pressState;
        
        //keep either filter or guess
        IUniqueSelection guessOrFilter = new IUniqueSelection();
        ((IDisplayCanvas)charCanvas).addObserver(guessOrFilter);
        ((IDisplayCanvas)optButCanvas).addObserver(guessOrFilter);
        //((IDisplayCanvas)subOptButCanvas).addObserver(guessOrFilter);
        
        scoreState= new TimeState( scoreState);
        ((TimeState)scoreState).setTimer(3);
        
        guessWhoState = new TimeState( guessWhoState);
        ((TimeState)guessWhoState).setTimeBoxLoc(this,600,100);
        ((TimeState)guessWhoState).setTimer(10);
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
    
    public GameState getCurrentState()
    {
        return currentState;
    }
    
    public boolean isCurrentState(GameState gameState)
    {
        return currentState == gameState;
    }
    
    public void act()
    {
        currentState.stateRun();
    }
}
