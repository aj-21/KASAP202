import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * DummyImage is an actor storing only an image and do nothing
 * DummyImage is useful actor which is meant be added to a world as a picture given only a file name
 * support png,jpg,gif
 * @author SPAAK
 * @version 1
 */
public class DummyImage extends Actor
{
    GifImage gImg;
    /**
     * constructor only need a path to an image file
     * support png,jpg
     */
    public DummyImage(String filename)
    {
        setImage(new GreenfootImage(filename));
    }
    
    /**
     * constructor take in a gGreenfootImage
     */
    public DummyImage(GreenfootImage img)
    {
        setImage(img);
    }
    
    public DummyImage()
    {
    }
    
    /**
     * constructor take in a path to a gif image, and a boolean to reset gif
     */
    public DummyImage(GifImage gImg)
    {
        this.gImg = gImg;
    }
    
    /**
     * only useful when this is a GifImage
     */
    public void gifResetFrame()
    {
        if(gImg != null)
            gImg.resetFrame();
    }
    
    /**
     * display frame by frame when this is a gif image
     */
    public final void act() 
    {
        if(gImg != null)
            setImage(gImg.getCurrentImage());
    }    
}
