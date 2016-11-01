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
    
    private EnableButton ()
    {
        disableImage = new GreenfootImage("buttonDisabled.png");
        disableImage.scale(300,80);
        enableImage = new GreenfootImage("buttonEnabled.png");
        enableImage.scale(300,80);
        setImage(disableImage);
        o = new MyObservable();
    }
    
    public EnableButton(String label)
    {
        this();
        this.label = label;
    }
    
    public void act()
    {
        if(getImage() == enableImage && Greenfoot.mouseClicked(this))
        {
            o.setCHANGED();
            o.notifyObservers(this);
            o.clearCHANGED();
        }
    }
    
    @Override
    public void update(Observable o, Object arg)
    {
        toggle();
    }
    
    private void toggle()
    {
        if(getImage() == disableImage)
        {
            setImage(enableImage);
            return;
        }
        setImage(disableImage);
    }
    
    public void addObserver(Observer observer)
    {
        o.addObserver(observer);
    }
    
    public String getLabel()
    {
        return label;
    }
}
