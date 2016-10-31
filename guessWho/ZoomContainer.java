import greenfoot.*;
import java.util.Set;
import java.util.List;
/**
 * Write a description of class ZoomContainer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ZoomContainer<T extends ZoomActor> extends SimpleContainer<T>
{
    // instance variables - replace the example below with your own

    /**
     * Constructor for objects of class ZoomContainer
     */
    public ZoomContainer()
    {
        super();
    }
    
    public ZoomContainer(T actor)
    {
        this();
        add(actor);
    }
    
    public ZoomContainer(Set<T> actors)
    {
        super(actors);
    }
    
    public ZoomContainer(List<T> actors)
    {
        super(actors);
    }
    
    public void resizeOnScale(double scale)
    {
        for(ZoomActor actor:actors)
        {
            actor.resizeOnScale(scale);
        }
    }
    
    public void setZoomScale(double scale)
    {
        for(ZoomActor actor:actors)
        {
            actor.setZoomScale(scale);
        }
    }
}
