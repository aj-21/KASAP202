import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Observer;
import java.util.Observable;

/**
 * Write a description of class EnableButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnableButton extends Actor implements Observer
{
    GreenfootImage disableImage;
    GreenfootImage enableImage; 
    String label;
    MyObservable o;
    Object passedArg;
    
    private EnableButton ()
    {
        disableImage = new GreenfootImage("buttonDisabled.png");
        disableImage.scale(300,80);
        enableImage = new GreenfootImage("buttonEnabled.png");
        enableImage.scale(300,80);
        disable();
        o = new MyObservable();
    }
    
    public EnableButton(String label)
    {
        this();
        this.label = label;
    }
    
    public void act()
    {
        if(isEnable() && Greenfoot.mouseClicked(this))
        {
            o.setCHANGED();
            o.notifyObservers(this);
            o.clearCHANGED();
        }
    }
    
    @Override
    public void update(Observable o, Object arg)
    {
        passedArg = arg;
        if(arg != null)
        {
            enable();
            return;
        }
        disable();
    }
    
    private void toggle()
    {
        if(isEnable())
        {
            disable();
            return;
        }
        enable();
    }
    
    public void addObserver(Observer observer)
    {
        o.addObserver(observer);
    }
    
    public String getLabel()
    {
        return label;
    }
    
    public void enable()
    {
        setImage(enableImage);
    }
    
    public void disable()
    {
        setImage(disableImage);
    }
    
    public boolean isEnable()
    {
        return (getImage() == enableImage);
    }
}
