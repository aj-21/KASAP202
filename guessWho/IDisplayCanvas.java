import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Set;
/**
 * Write a description of class IDisplayCanvas here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
                {
                    //only notify if something is pressed (select, deselect)
                    myOb.setCHANGED();
                    myOb.notifyObservers(getAll());
                    myOb.clearCHANGED();
                    
                    break;
                }
            }
            
        }
        
        if (successor!=null)
            successor.pressHandle(x,y);
    }
    
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
