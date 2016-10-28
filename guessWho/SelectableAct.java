import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SelectableActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SelectableAct extends Actor
{   
    int sizeW;
    int sizeH;

    boolean selected = false;
    boolean inWorld = false;
    double scale = 1.5;
    
    public SelectableAct()
    {
        sizeW=getImage().getWidth();
        sizeH=getImage().getHeight();
    }
    
    public SelectableAct(double scale)
    {
        this();
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
        System.out.println("scale is: " + scale);
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
