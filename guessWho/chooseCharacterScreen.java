import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
/**
 * Write a description of class chooseCharacterScreen here.
 * 
 * @author (your name) 
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
        chooseCharBox.setImage(bg);
        addObject(chooseCharBox,getWidth()/2,getHeight()/2);
        
        //try # margin and setCharScale for the best look, left right top bottom
        chooseCharBox.setMargin(10,10,18,15);
        chooseCharBox.setCharScale(1.2);

        addObject(new chooseCharTitle(),876,641);
        
        Set<Character> fullSet = gameSession.getAllFromFullSet();
        chooseCharBox.addAllChars(fullSet);

        //setup button confirm to check condition pass out from chooseCharBox
        buttonConfirm.addConditionalObj(chooseCharBox); 
        addObject(buttonConfirm,743,774);
        
        
        //Testing option info
        
        optionInfo.addSubOption("Hair","Black");
        optionInfo.addSubOption("Hair","Black");
        optionInfo.addSubOption("Hair","Red");
        
        optionInfo.addSubOption("Hat","Yes");
        optionInfo.addSubOption("Hat","No");
        
        System.out.println(optionInfo);
        
        
    }
    
    public void act()
    {   
    }
   
    @Override
    public void buttonClickedRun(ButtonConfirm button)
    {
        if(button == buttonConfirm)
            exit();
        
    }
    
    private void exit()
    {
        gameSession.setMyChar(chooseCharBox.getSelectedChar());  
        Greenfoot.setWorld(new GuessWho(gameSession));
    }
    
}

