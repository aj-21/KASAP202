import greenfoot.*; 

/**
 * EnableButton is a actor that can be enabled and disabled. 
 * EnableButton is an observer that will disable itself if object passed from Subject/Observable is null, and enable itself if otherwise.
 * EnableButton is also an observable Object that accept Observers and notify them when itself is clicked while in enable state. 
 * The button also pass itself as the argument to all observers
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
    
    /**
     * constructor needs a label, paths to disable image and enable image
     */
    public EnableButton(String label,String disableFilename, String enableFileName)
    {
        disableImage = new GreenfootImage(disableFilename);
        enableImage = new GreenfootImage(enableFileName);
        disable();
        this.label = label;
        o = new MyObservable();
    }
    
    /**
     * constructor requires only a label. The default images will be confirm.disable.png, and confirm.enabled.png
     * 
     */
    public EnableButton(String label)
    {
        this(label,"confirm.disabled.png","confirm.enabled.png");
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
    
    //enable if arg not null
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
