import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Observable;
import java.util.Observer;
/**
 * Write a description of class ObservableZoomActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ObservableZoomActor extends ZoomActor
{
    MyObservable o;
    public ObservableZoomActor(String filename,double initScale)
    {
        super(filename,initScale);
        o = new MyObservable();
    }
    
    public void addObserver(Observer observer)
    {
        o.addObserver(observer);
    }
    
    public void afterAct()
    {
        o.setCHANGED();
        o.notifyObservers(this);
        o.clearCHANGED();
    }
}
