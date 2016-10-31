import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.*;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collection;
/**
 * Write a description of class chooseCharacterScreen here.
 * 
 * @SPAAK 
 * @version (a version number or a date)
 */
public class chooseCharacterScreen extends World implements ButtonRunnable
{
    GameSession gameSession;
    ButtonConfirm buttonConfirm= new ButtonConfirm(this, "confirm"); 
    UniqueSelection uniqueSelection;
    SelectionEnablingButton seb;
    /**
     * Constructor for objects of class chooseCharacterScreen.
     * 
     */
    public chooseCharacterScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1536, 864, 1); 
        gameSession = new GameSession();
        prepare();
    }


    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {         
        GreenfootImage bg = this.getBackground();
        bg.drawImage(new GreenfootImage("Choose_your_character.png"),getWidth()/7,getHeight()/35);;
        
        ZoomContainer fullCon = new ZoomContainer(gameSession.getFullSet());
        fullCon.resizeOnScale(0.9);
        DisplayCanvas disCan = new DisplayCanvas(this,fullCon);
        disCan.setBackground(bg).setMargin(10,10,18,15);
        addObject(disCan,getWidth()/2,getHeight()/2);
        disCan.display();       
        
        //buttonConfirm.addConditionalObj((ButtonCheckable) uniqueSelection); 
        //addObject(buttonConfirm,743,774);
        
        EnableButton confirmButton = new EnableButton("confirm");
        addObject(confirmButton,743,774);
                
        uniqueSelection = new CheckableUniqueSelection(fullCon);
        seb = new SelectionEnablingButton(fullCon,confirmButton);
        
        confirmButton.addObserver(new ChooseCharacterScreenState(this.getGameSession()));

    }
    
    public void act()
    {   
        uniqueSelection.processRun();
        seb.processRun();
    }
   
    @Override
    public void buttonClickedRun(ButtonConfirm button)
    {
        if(button == buttonConfirm)
            exit();
        
    }
    
    private void exit()
    {
        SimpleContainer c = new SimpleContainer(gameSession.getFullSet());
        gameSession.setMyChar((Character)c.getFirstSelected());
        System.out.println("my character is: " + gameSession.getMyChar().getClass().getName());
        Greenfoot.setWorld(new GuessWho(gameSession));
    }
    
    public GameSession getGameSession()
    {
        return gameSession;
    }
}

