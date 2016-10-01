import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Characters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Character extends Actor
{   
    int sizeW = 140;
    int sizeH = 192;
     
    boolean selected = false;
    boolean inWorld = false;
    double scale = 1.5;

          
            
    public Character()
    {
        this(1);
    }
    
    public Character(double scale)
    {
        sizeW *= scale;
        sizeH *= scale;
        getImage().scale(sizeW,sizeH);
    }
    
    protected void addedToWorld(World world)
    {
        inWorld = true;
    }
    
    /**
     * Act - do whatever the Characters wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        checkSelected();   
    }
    
    private void checkSelected()
    {
        if(Greenfoot.mousePressed(this))
        {
            toggleSelected();
        }     
    }
    
    private void toggleSelected()
    {
        if(selected)
            unSelect();
        else
            select();
    }
        
    //resize the image
    public void resizeOnScale(double scale)
    {
        sizeW *= scale;
        sizeH *= scale;
        getImage().scale(sizeW,sizeH);
    }
    
    //this set scale variable when the tile is pressed
    public void setSelectedScale(double scale)
    {
        this.scale = scale;
    }
    
    //unselect the tile
     public void unSelect()
    {
        selected = false;
        getImage().scale(sizeW,sizeH);
    }    
    
    //select the tile
    public void select()
    {
        selected = true;
        getImage().scale((int) (sizeW*scale),(int) (sizeH*scale)); 
        
    }
    
    //return if the tile is selected or not
    public boolean isSelected()
    {
        return selected;
    }
    
    
    //return if the object is added into world
        public boolean isInWorld()
    {
        return inWorld;
    }
    
}
