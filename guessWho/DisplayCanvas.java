import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Set;
import java.util.HashSet;

/**
 * Write a description of class DisplayCanvas here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DisplayCanvas extends Actor
{
    SimpleDisplayBox disBox;
    double left, right, top, bottom;
    int leftMar,topMar;
    int disX, disY;
    /**
     * Act - do whatever the DisplayCanvas wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */  
    public DisplayCanvas(World world)
    {
        this.disBox = new SimpleDisplayBox(world);
    }
    
    public DisplayCanvas(World world, SimpleContainer container)
    {
        this(world);
        disBox.setContainer(container);
    }
    
    public DisplayCanvas(SimpleDisplayBox disBox)
    {
        this.disBox = disBox;
    }
    
    public DisplayCanvas(SimpleDisplayBox disBox, SimpleContainer container)
    {
        this(disBox);
        disBox.setContainer(container);
    }
    
    public void addedToWorld(World world)
    {
        disX = getX() - getImage().getWidth()/2;
        disY = getY() - getImage().getHeight()/2;
    }
    
    /*public void setBackground(String filename)
    {
        setBackground(new GreenfootImage(filename));
    }*/
    
    public DisplayCanvas setBackground(String filename)
    {
        setBackground(new GreenfootImage(filename));
        return this;
    }
    
    /*public void setBackground(GreenfootImage image)
    {
        setImage(image);
        setMargin(left,right,top,bottom);
        if(getWorld() != null)
            addedToWorld(getWorld());
    }*/
    
    public DisplayCanvas setBackground(GreenfootImage image)
    {
        setImage(image);
        setMargin(left,right,top,bottom);
        if(getWorld() != null)
            addedToWorld(getWorld());
        return this;
    }
    
    
    public DisplayCanvas setMargin(double left, double right, double top, double bottom)
    {
        this.left = left; this.right = right;this.top=top;this.bottom=bottom;
        int w = getImage().getWidth();
        int h = getImage().getHeight();
        this.leftMar = (int) (w * left/100);
        int rightMar = (int) (w * right/100);
        this. topMar = (int) (h * top/100);
        int bottomMar = (int) (h * bottom / 100 );
        
        disBox.scale(w - leftMar - rightMar, h - topMar - bottomMar);
        return this;
    }    
    
    public void display()
    {
        disBox.display(disX + leftMar,disY + topMar);           
    }
   
    public DisplayCanvas setContainer(SimpleContainer container)
    {
       disBox.setContainer(container);
       return this;
    }
    
    public DisplayCanvas setColRow(int col, int row)
    {
        disBox.setColRow(col,row);
        return this;
    }
    
    public SimpleContainer getContainer()
    {
        return disBox.getContainer();
    }
}
