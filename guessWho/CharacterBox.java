import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;

/**
 * Write a description of class CharacterBox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharacterBox extends Actor
{
    List<Character> characters= new ArrayList<Character>();
    Character lastSelected;
    double charScale = 0.8;
    int numCol = 4;
    int numRow = 2;
    int maxNumChar = numCol*numRow;
    int charWidth = getImage().getWidth()/numCol;
    int charHeight = getImage().getHeight()/numRow;
    /**
     * Act - do whatever the CharacterCanvas wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public CharacterBox()
    {
    }
    
    public CharacterBox(int width, int height)
    {
        this();
        getImage().scale(width,height); 
        reCalibrate();
        
    }
    
    
    public CharacterBox(int width, int height,int col, int row)
    {
        this(width,height);
        this.numCol = col;
        this.numRow = row;
        reCalibrate();
    }
    
    private void reCalibrate()
    {
        maxNumChar = numCol*numRow;
        charWidth = getImage().getWidth()/numCol;
        charHeight = getImage().getHeight()/numRow;
        System.out.println ("Canvas Width: " + getImage().getWidth());
        System.out.println ("Canvas Height: " + getImage().getHeight());
        System.out.println ("Char Width: " + charWidth);
        System.out.println ("Char Height: " + charHeight);
    }
    
    public void act() 
    {
        // Add your action code here.
        selectedCharProcessing();
    }    
    
    /*public void addCharList(List<Character> characters)
    {
        for (Character character : characters)
            this.addCharacter(character);
    }*/
    
    public void addCharacter(Character character)
    {
        if (characters.size() < maxNumChar)
        {
            character.resize((int)(charScale * charWidth),(int)(charScale * charHeight));
            this.characters.add(character);
            this.displayChar(character);
        }
    }
    
    /*public void removeCharacter(Character character)
    {
        this.characters.remove(character);
    }
    
    public void clearCharList()
    {
        this.characters.clear();
    }
    
    public Character getSelectedCharacter()
    {
        return selectedCharacter;
    }
    
    public int size()
    {
        return characters.size();
    }*/

    /*private void autoSetLocation(Character character)
    {
        int index = characters.indexOf(character);
        int x = (index % numCol) * charWidth + charWidth/2;
        int y = (index / numRow) * charHeight + charWidth/2;
        character.setLocation(this.getX() + x, this.getY() + y);
    }*/
    
    private void displayChar(Character character)
    {
        int index = characters.indexOf(character);
        int x = (index % numCol) * charWidth + charWidth/2;
        x = this.getX() - getImage().getWidth()/2 + x;
        int y = (index / numCol) * charHeight + charHeight/2;
        y = this.getY() - getImage().getHeight()/2 + y; 
        getWorld().addObject(character,x,y);        
    }
    
    private void selectedCharProcessing()
    {
        for (Character character:characters)
        {
            if(character.isSelected())
                unClickIfNotLastSelected(character);
        }
    }
    
    private void unClickIfNotLastSelected(Character character)
    {
        if(character != lastSelected)
        {
            if(lastSelected!=null)
                lastSelected.unSelect();
            lastSelected=character;
        }
    }    
}
