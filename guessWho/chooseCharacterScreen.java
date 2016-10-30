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
    List<Character> allChars = new ArrayList<Character>();
    CharacterBox chooseCharBox = new CharacterBox(getWidth(),getHeight(),5,2);
    ButtonConfirm buttonConfirm= new ButtonConfirm(this, "confirm");
    Character myChar;
    
    OptionInfo optionInfo = new OptionInfo();
    
    UniqueSelection uniqueSelection;
    
    /**
     * Constructor for objects of class chooseCharacterScreen.
     * 
     */
    public chooseCharacterScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1536, 864, 1); 
        gameSession = new GameSession();
        uniqueSelection = new CheckableUniqueSelection(this);
        prepare();
    }


    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {         
        GreenfootImage bg = this.getBackground();
        bg.drawImage(new GreenfootImage("Choose_your_character.png"),getWidth()/7,getHeight()/35);
        //chooseCharBox.setImage(bg);
        //addObject(chooseCharBox,getWidth()/2,getHeight()/2);
        
        //try # margin and setCharScale for the best look, left right top bottom
        //chooseCharBox.setMargin(10,10,18,15);

        //chooseCharBox.addAllCharacters(gameSession.getAllFromFullSet());
        //chooseCharBox.display();

        //setup button confirm to check condition pass out from chooseCharBox
        //buttonConfirm.addConditionalObj(chooseCharBox); 
        //addObject(buttonConfirm,743,774);
        
        
        //Testing option info
        //optionInfo.addSubOption("Hair","Black");
        //optionInfo.addSubOption("Hair","Black");
        //optionInfo.addSubOption("Hair","Red");
        
        //optionInfo.addSubOption("Hat","Yes");
        //optionInfo.addSubOption("Hat","No");
        
        System.out.println(optionInfo);
        
        Set<SimpleSelectableActor> set = new HashSet<SimpleSelectableActor>();
        set.add(new Char1());
        set.add(new Char2());
        set.add(new Char3());
        set.add(new Char4());
        set.add(new Char5());
        set.add(new Char6());
        set.add(new Char7());
        set.add(new Char8());
        set.add(new Char9());
        
        DisplayCanvas disCan = new DisplayCanvas(new SimpleDisplayBox(this));
        disCan.setContainer(set);
        disCan.setBackground(bg);
        addObject(disCan,getWidth()/2,getHeight()/2);
        disCan.setMargin(10,10,18,15);
        disCan.display();
        
        uniqueSelection.setContainer(set);
        
        buttonConfirm.addConditionalObj((ButtonCheckable) uniqueSelection); 
        addObject(buttonConfirm,743,774);
        
    }
    
    public void act()
    {   
        uniqueSelection.run();
    }
   
    @Override
    public void buttonClickedRun(ButtonConfirm button)
    {
        if(button == buttonConfirm)
            exit();
        
    }
    
    private void exit()
    {
        //gameSession.setMyChar(chooseCharBox.getSelectedChar());
        System.out.println("my character is: " + gameSession.getMyChar().getClass().getName());
        Greenfoot.setWorld(new GuessWho(gameSession));
    }
    
    public GameSession getGameSession()
    {
        return gameSession;
    }
}

