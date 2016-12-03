import java.util.Set;
/**
 * extends DisplayCanvas with ability to handle press event
 * also support notifying observer, added via method addObserver, if there is a press
 * support setSuccessor 
 * 
 * @author SPAAK 
 * @version 1
 */
public class IDisplayCanvas<T extends SimpleSelectableActor> extends DisplayCanvas<T> implements PressHandler
{
    PressHandler successor;
    MyObservable myOb = new MyObservable();
    public IDisplayCanvas()
    {
        super();   
    }
    
    public IDisplayCanvas(Set<T> actors)
    {
        super(actors);
    }
    
    public IDisplayCanvas(T actor)
    {
        super(actor);
    }
    
    public void setSuccessor(PressHandler successor)
    {
        if (this.successor != null)
            successor.setSuccessor(this.successor);
        
        this.successor = successor;
    }
    
    public void pressHandle(int x, int y)
    {
        if(isInside(x,y))
        {
            Set<T> charSet = getAll();
            for(T each:charSet)
            {
                if(each.detectPress() == true)
                    break;
            }
            myOb.setCHANGED();
            myOb.notifyObservers(getAll());
            myOb.clearCHANGED();
        }
        
        if (successor!=null)
            successor.pressHandle(x,y);
    }
    
    //helper function to determine if a press is inside this
    protected boolean isInside(int x, int y)
    {
        if(getWorld() != null)
        {
            int startX = getX() - getImage().getWidth()/2;
            int endX = getX() + getImage().getWidth()/2;
            boolean xInside = (x > startX) && (x < endX);
            
            int startY = getY() - getImage().getHeight()/2;
            int endY = getY() + getImage().getHeight()/2;
            boolean yInside = (y > startY) && (y < endY);
            
            return xInside && yInside;
        }
        return false;
    }
    
    
    public void addObserver(Observer observer)
    {
        myOb.addObserver(observer);
    }
}
