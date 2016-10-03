import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GuessWho here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GuessWho extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public GuessWho()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1536, 864, 1); 
        setup();
    }
    
    private void setup()
    {
        CharacterBox charBox = new CharacterBox(925,460);
        //addObject(charBox,getWidth()/2,getHeight()/2);
        addObject(charBox,getWidth()/2,600);
        charBox.addCharacter(new Char1());
        charBox.addCharacter(new Char2());
        charBox.addCharacter(new Char3());
        charBox.addCharacter(new Char4());
        charBox.addCharacter(new Char5());
        charBox.addCharacter(new Char6());
        charBox.addCharacter(new Char7());
        charBox.addCharacter(new Char8());
        charBox.addCharacter(new Char9());
    }
}
