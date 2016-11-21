import java.util.Set;
import greenfoot.*;
/**
 * Write a description of class DisBoxPressHandler here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DisBoxPressHandler<T extends SimpleSelectableActor> extends DisplayBoxDecorator<T> implements PressHandler
{
    PressHandler successor;
    MyObservable myOb = new MyObservable();
    
    public DisBoxPressHandler(DisplayBox disBox)
    {
        super(disBox);
    }
    
    public void setSuccessor(PressHandler successor)
    {
        this.successor = successor;
    }
    
    public void pressHandle(int x, int y)
    {
        boolean xInside = (x > getX() && x < getX() + getWidth());
        boolean yInside = (y > getY() && y < getY() + getHeight());
        if(xInside && yInside)
        {
            Set<T> charSet = getActors();
            for(T each:charSet)
            {
                if(each.detectPress() == true)
                {
                    //only notify if something is pressed (select, deselect)
                    myOb.setCHANGED();
                    myOb.notifyObservers(getActors());
                    myOb.clearCHANGED();
                    
                    break;
                }
            }
            
        }
        
        if (successor != null)
            successor.pressHandle(x,y);
    }
    
    public void addObserver(Observer observer)
    {
        myOb.addObserver(observer);
    }
}
