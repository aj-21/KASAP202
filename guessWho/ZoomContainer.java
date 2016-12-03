import java.util.Set;
/**
 * extends functionality of SimpleContainer to be able to uniformly perform action on ZoomActor
 * support resizeOnScale, setZoomScale
 * 
 * @author SPAAK
 * @version 1
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
