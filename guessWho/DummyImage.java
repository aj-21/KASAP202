import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * DummyImage is an actor storing only an image and do nothing
 * 
 * @author SPAAK
 * @version 1
 */
public class DummyImage extends Actor
{
    //constructor takes in a path to an image file
    public DummyImage(String filename)
    {
        setImage(new GreenfootImage(filename));
    }
    
    public DummyImage()
    {
        //setImage(new GreenfootImage(1,1));
    }
    
    public final void act() 
    {
        // Add your action code here.
    }    
}
