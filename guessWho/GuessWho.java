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
    
    IGameState guessWhoState;
    IGameState scoreState;
    
    

    public GuessWho(GameSession gameSession)
    {    
        super(1536, 864, 1); 
        this.gameSession = gameSession;
        
        guessWhoState = new GuessWhoState(this);
        scoreState = new IScoreState(this,gameSession);
        
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
        guessWhoState.setSuccessor((PressHandler)charCanvas);
        guessWhoState.setSuccessor((PressHandler)subOptButCanvas);
        guessWhoState.setSuccessor((PressHandler)optButCanvas);
        
        //transfer option changes to filteringState (for filter later) whenever there is a change  
        
        //keep either filter or guess
        IUniqueSelection guessOrFilter = new IUniqueSelection();
        ((IDisplayCanvas)charCanvas).addObserver(guessOrFilter);
        ((IDisplayCanvas)optButCanvas).addObserver(guessOrFilter);
        //((IDisplayCanvas)subOptButCanvas).addObserver(guessOrFilter);
        
  
        //Confirm button
        EnableButton conBut = new EnableButton("confirm");
        addObject(conBut,1000,100);
      
        //create a new IObservableSelection to listen to updaSubRcv + subOptButCanvas + charCanvas, and notify confirm Button
        IObservableSelection obSel = new IObservableSelection();
        updSubRcv.addObserver(obSel);
        ((IDisplayCanvas)subOptButCanvas).addObserver(obSel);
        ((IDisplayCanvas)charCanvas).addObserver(obSel);
        obSel.addObserver(conBut);

        
        
        Timer t= new Timer();
        SecondObservable secOb = new SecondObservable();
        secOb.addObserver(guessWhoState);
        secOb.addObserver(scoreState);
        
        //t.schedule(secOb,0,1000);
        
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
        guessWhoState.stateRun();
    }
}
