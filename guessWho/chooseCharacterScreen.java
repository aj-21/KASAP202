import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.*;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collection;

import java.util.Observer;
import java.util.Observable;

/**
 * Write a description of class chooseCharacterScreen here.
 * 
 * @SPAAK 
 * @version (a version number or a date)
 */
public class chooseCharacterScreen extends World implements Observer
{
    Set<Process> processes;
    GameSession gameSession;

    /**
     * Constructor for objects of class chooseCharacterScreen.
     * 
     */
    public chooseCharacterScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1536, 864, 1); 
        processes = new HashSet<Process>();
        gameSession = new GameSession();
        prepare();
    }


    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {                 
        ZoomContainer fullCon = new ZoomContainer(gameSession.getFullSet());
        fullCon.resizeOnScale(0.9);
        DisplayCanvas disCan = new DisplayCanvas(gameSession.getFullSet());
        disCan.setBackground(getBackground()).setMargin(10,10,18,15);
        addObject(disCan,getWidth()/2,getHeight()/2);
        disCan.display();       
        
        EnableButton confirmButton = new EnableButton("confirm");
        addObject(confirmButton,743,774);
                
        processes.add( new UniqueSelection(gameSession.getFullSet()));
        processes.add( new SelectionObservable(gameSession.getFullSet(),confirmButton));
        confirmButton.addObserver(this);
        
        addObject(new DummyImage("Choose_your_character.png"),getWidth()/2,getHeight()/10);

    }
    
    public void act()
    {   
        for(Process process:processes)
            process.processRun();
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
        gameSession.setMyChar((Character)c.getFirstSelected());
        updateOptionInfo();
        //System.out.println(gameSession.getOptionInfo());
        Greenfoot.setWorld(new GuessWho(gameSession));
    }
    
    //helper func
    private void updateOptionInfo()
    {
        PropertyInfo propertyInfo = gameSession.getPropertyInfo();
        Set<Character> playSet = gameSession.getPlaySet();
        for(Character each:playSet)
        {
            propertyInfo.putProperties(each.getProperties());
        }
    }
    
    public GameSession getGameSession()
    {
        return gameSession;
    }
}

