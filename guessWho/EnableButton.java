import greenfoot.*; 
import java.util.Observer;
import java.util.Observable;

/**
 * EnableButton is a actor that can be enabled and disabled. 
 * States of the button are display with 2 different images, usually by a partially transparent image for disable and a transparent image for enable
 * EnableButton is an observer that will disable itself if object passed from Subject/Observable is null, and enable itself if otherwise.
 * EnableButton is also an observable Object that accept Observers and notify them when the button is clicked while in enable state. The button pass itself to Observers
 * 
 * @author SPAAK
 * @version 1
 */
public class EnableButton extends Actor implements Observer
{
    
    GreenfootImage disableImage;
    GreenfootImage enableImage; 
    String label;
    
    //MyObserver is a proxy of Observer to use protected method such as setChange -> setCHANGED, clearChange -> clearCHANGED
    MyObservable o;
    
    private EnableButton ()
    {
    }
    
    //construction needs a label and optional file to disable image and enable image
    public EnableButton(String label,String disableFilename, String enableFileName)
    {
        disableImage = new GreenfootImage(disableFilename);
        enableImage = new GreenfootImage(enableFileName);
        disable();
        this.label = label;
        o = new MyObservable();
    }
    
    public EnableButton(String label)
    {
        this(label,"confirmDisabled.png","confirmEnabled.png");
        disableImage.scale(300,80);
        enableImage.scale(300,80);
    }
    
    /**
     * button will accept click only when it is enable. then it will notify all Observers if there is one
     */
    public void act()
    {
        if(Greenfoot.mouseClicked(this) && isEnable())
        {
            o.setCHANGED();
            o.notifyObservers(this);
            o.clearCHANGED();
        }
    }
    
    @Override
    public void update(Observable o, Object arg)
    {
        if(arg != null)
        {
            enable();
            return;
        }
        disable();
    }
    
    //helper method to toggle state of button
    private void toggle()
    {
        if(isEnable())
        {
            disable();
            return;
        }
        enable();
    }
    
    //support add Observer
    public void addObserver(Observer observer)
    {
        o.addObserver(observer);
    }
    
    //get button's label name
    public String getLabel()
    {
        return label;
    }
    
    //enable button
    public void enable()
    {
        setImage(enableImage);
    }
    
    //disable button
    public void disable()
    {
        setImage(disableImage);
    }
    
    //check if button is enable, true is enable, false is disable
    public boolean isEnable()
    {
        return (getImage() == enableImage);
    }
}
