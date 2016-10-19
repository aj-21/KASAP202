import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

/**
 * Write a description of class CharacterBox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharacterBox extends DisplayBox implements ButtonCheckable
{
    Character lastSelected;
    double charScale = 1;
    double selectedCharScale = 1.5;
    boolean inWorld = false;
    
    public CharacterBox(int width, int height,int col, int row)
    {
        super(width,height,col,row);
    }
    
    //characterbox main task is to check if a character is selected. If another is selected, unselect the previous one
    public void act() 
    {
        selectedCharProcessing();
    }    
    
    public void addAllCharacters(Collection<Character> characters)
    {
        for(Character c: characters)
            addCharacter(c); 
    }
    
    //add a new Character
    public void addCharacter(Character character)
    {
        character.resizeOnScale(charScale);
        character.setSelectedScale(selectedCharScale);
        character.unSelect();
        addObject(character);
    }
    
    public void removeSelectedChar()
    {
        if(lastSelected != null)
        {
            removeObject(lastSelected);            
            lastSelected = null;
        }
    }
    
    public void removeChar(Character c)
    {
        c.unSelect();
        removeObject(c);
    }

    public void setCharScale(double scale)
    {
        if(scale <=0)
            return;

        charScale = scale;
        for (Actor c:objects)
        {
            ((Character)c).resizeOnScale(charScale);
        }
        
    }    
    
    public void setSelectedCharScale(double scale)
    {
        if (scale <=0 )
            return;
            
        selectedCharScale = scale;
        for (Actor c:objects)
        {
            ((Character)c).setSelectedScale(selectedCharScale);
        }
        
    }
    
    private void selectedCharProcessing()
    {
        boolean noneSelected = true;
        for (Actor c:objects)
        {
            Character ch = (Character) c;
            if(ch.isSelected())
            {
                noneSelected = false;
                unClickLastSelected(ch);
            }
        }
        if(noneSelected)
            lastSelected = null;
    }
    
    private void unClickLastSelected(Character character)
    {
        if(character != lastSelected)
        {
            if(lastSelected!=null)
                lastSelected.unSelect();
            lastSelected=character;
        }
    }    
    
    public Character getSelectedChar()
    {
        return lastSelected;
    }
    
    @Override
    public boolean isChecked()
    {
        return (lastSelected != null);
    }
}
