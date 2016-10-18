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
public class CharacterBox extends Actor implements ButtonCheckable
{
    List<Character> characters= new ArrayList<Character>();
    Character lastSelected;
    double charScale = 1;
    double selectedCharScale = 1.5;
    int numCol = 5;
    int numRow = 2;
    int leftMar, rightMar, topMar, bottomMar;
    int disX,disY,disW,disH;
    boolean inWorld = false;
    /**
     * Act - do whatever the CharacterCanvas wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public CharacterBox()
    {
    }
    
    public CharacterBox(int width, int height)
    {
        getImage().scale(width,height); 
        
    }
    
    public CharacterBox(int width, int height,int col, int row)
    {
        this(width,height);
        this.numCol = col;
        this.numRow = row;
    }
    
    protected void addedToWorld(World world)
    {
        inWorld = true;
        reCalibrate();
    }
    
    private void reCalibrate()
    {
        disW = (getImage().getWidth() - leftMar - rightMar) ;
        disH = (getImage().getHeight() - topMar - bottomMar) ;
        disX = getX() - getImage().getWidth()/2;
        disY = getY() - getImage().getHeight()/2;
        for(Character c: characters)
            displayChar(c);
    }
    
    //characterbox main task is to check if a character is selected. If another is selected, unselect the previous one
    public void act() 
    {
        selectedCharProcessing();
    }    
    
    //add all Characters in a collection into box
    public void addAllChars(Collection<Character> characters)
    {
        for(Character character : characters)
            addCharacter(character);       
    }
    
    //add a new Character
    public void addCharacter(Character character)
    {
        if (characters.size() < numCol*numRow)
        {
            System.out.println("adding " + character.getClass().getName());
            character.resizeOnScale(charScale);
            setSelectedScaleOnChar(character);
            characters.add(character);
            if(inWorld)
                displayChar(character);
        }
    }
    
    public void removeSelectedChar()
    {
        if(lastSelected != null)
        {
            characters.remove(lastSelected);
            removeChar(lastSelected);            
            lastSelected = null;
        }
    }
    
    public void removeChar(Character character)
    {
        if(character != null)
        {
            character.unSelect();
            characters.remove(character);
            getWorld().removeObject(character);
        }
    }
    
    //set 4 margin with percentage.
    //Example, 1 = 1% margin of the total box
    //the larger the margin, the smaller the display area for objects, and the gaps between objects get tinier
    public void setMargin(double left, double right, double top, double bottom)
    {
        int w = getImage().getWidth();
        int h = getImage().getHeight();
        this.leftMar = (int) (w * left/100);
        this.rightMar = (int) (w * right/100);
        this.topMar = (int) (h * top/100);
        this.bottomMar = (int) (h * bottom / 100 );
        if(inWorld == true)
            reCalibrate();
    }
    
    //set 4 margin with percentage. left and right are equal, top and botton are equal. 
    //Example, 1 = 1% margin of the total box
    //the larger the margin, the smaller the display area for objects, and the gaps between objects get tinier
    public void setMargin(double leftRight,  double topBottom)
    {
        setMargin(leftRight, leftRight, topBottom, topBottom);
    }
    
    //set default character scale in this box
    //this is where you set the size of your character uniformly
    public void setCharScale(double scale)
    {
        charScale = scale;
        for (Character c:characters)
        {
            c.resizeOnScale(charScale);
        }
        
    }    
    
    //set default character scale in this box
    //set this to 1 if you dont want your character zoon when selected
    //set this to 0 if you want your character zoom by default
    public void setSelectedCharScale(double scale)
    {
        selectedCharScale = scale;
        for (Character c:characters)
        {
            setSelectedScaleOnChar(c);
        }
        
    }
    
    private void setSelectedScaleOnChar(Character c)
    {
        if (selectedCharScale > 0)
                c.setSelectedScale(selectedCharScale);
    }
    
    private void displayChar(Character character)
    {
        int index = characters.indexOf(character);
        int numColInThisRow = numCol;
            
        int x = (index % numCol) * (disW/numCol)+ disX  + (disW/numCol)/2 + leftMar;
        int y = (index / numCol) * (disH/numRow) + disY + (disH/numRow)/2 + topMar;
        getWorld().addObject(character,x,y);        
    }
    
    
    private void selectedCharProcessing()
    {
        boolean noneSelected = true;
        for (Character character:characters)
        {
            if(character.isSelected())
            {
                noneSelected = false;
                unClickLastSelected(character);
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
