import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DisplayCanvas here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DisplayCanvas extends Actor
{
    SimpleDisplayBox disBox;
    int leftMar, rightMar, topMar, bottomMar;
    /**
     * Act - do whatever the DisplayCanvas wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public DisplayCanvas()
    {
        
    }
    
    public void addedToWorld(World world)
    {
        disBox = new SimpleDisplayBox(world);
    }
    
    public void setMargin(double left, double right, double top, double bottom)
    {
        int w = getImage().getWidth();
        int h = getImage().getHeight();
        this.leftMar = (int) (w * left/100);
        this.rightMar = (int) (w * right/100);
        this.topMar = (int) (h * top/100);
        this.bottomMar = (int) (h * bottom / 100 );
    }
    
    public void act() 
    {
        // Add your action code here.
    }    
}
