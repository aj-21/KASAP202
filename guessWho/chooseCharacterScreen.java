import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class chooseCharacterScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class chooseCharacterScreen extends World implements MyWorld
{
    
    List<Character> allChars = new ArrayList<Character>();
    CharacterBox chooseCharBox = new CharacterBox(getWidth(),getHeight(),5,2);
    ButtonConfirm buttonConfirm= new ButtonConfirm("confirm");
    Character choosenChar;
    
    /**
     * Constructor for objects of class chooseCharacterScreen.
     * 
     */
    public chooseCharacterScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1536, 864, 1); 
        prepare();
    }


    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        /*chooseCharTitle choosechartitle = new chooseCharTitle();
        addObject(choosechartitle,876,641);
        choosechartitle.setLocation(894,640);

        // CharacterBox charBox = new CharacterBox(925,460);
        // //addObject(charBox,getWidth()/2,getHeight()/2);
        // addObject(charBox,getWidth()/2,600);
        // charBox.addCharacter(new Char1());
        // charBox.addCharacter(new Char2());
        // charBox.addCharacter(new Char3());
        // charBox.addCharacter(new Char4());
        // charBox.addCharacter(new Char5());
        // charBox.addCharacter(new Char6());
        // charBox.addCharacter(new Char7());
        // charBox.addCharacter(new Char8());
        // charBox.addCharacter(new Char9());

        Char1 char1 = new Char1();
        Char2 char2 = new Char2();
        Char3 char3 = new Char3();
        Char4 char4 = new Char4();
        Char5 char5 = new Char5();
        Char6 char6 = new Char6();
        Char7 char7 = new Char7();
        Char8 char8 = new Char8();
        Char9 char9 = new Char9();

        addObject(char1,342,308);
        addObject(char2,510,594);        
        addObject(char3,953,309);        
        addObject(char4,810,595);       
        addObject(char5,1095,594);       
        addObject(char6,1354,593);       
        addObject(char7,658,308);        
        addObject(char8,215,595);
        addObject(char9,1225,302);

        char1.setLocation(342,308);
        char2.setLocation(510,594);
        char3.setLocation(953,309);
        char4.setLocation(810,595);
        char5.setLocation(1095,594);
        char6.setLocation(1354,593);
        char7.setLocation(658,308);
        char8.setLocation(215,595);
        char9.setLocation(1225,302);
        
        allChars = getObjects(Character.class);
        
        buttonDisabled buttondisabled = new buttonDisabled();
        addObject(buttondisabled,743,774);*/
        //buttondisabled.setLocation(743,774);
        
        
        //my new implementation
        
        
        GreenfootImage bg = this.getBackground();
        chooseCharBox.setImage(bg);
        addObject(chooseCharBox,getWidth()/2,getHeight()/2);
        //try # margin and setCharScale for the best look, left right top bottom
        chooseCharBox.setMargin(10,10,18,15);
        chooseCharBox.setCharScale(1.2);
        addObject(new chooseCharTitle(),876,641);
        chooseCharBox.addCharacter(new Char1());
        chooseCharBox.addCharacter(new Char2());
        chooseCharBox.addCharacter(new Char3());
        chooseCharBox.addCharacter(new Char4());
        chooseCharBox.addCharacter(new Char5());
        chooseCharBox.addCharacter(new Char6());
        chooseCharBox.addCharacter(new Char7());
        chooseCharBox.addCharacter(new Char8());
        chooseCharBox.addCharacter(new Char9());     
        
        addObject(buttonConfirm,743,774);
        
        
    }
    
    public void act()
    {   
        choosenChar = chooseCharBox.getSelectedChar();
        if(choosenChar!=null)
            buttonConfirm.enableButton();
        else
            buttonConfirm.disableButton();
    }
   
    public void buttonClicked(ButtonConfirm button)
    {
        if(button == buttonConfirm)
            exit();
    }
    
    private void exit()
    {
        Greenfoot.setWorld(new GuessWho(choosenChar));
    }
    
}

