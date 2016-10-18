import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Set;
import java.util.HashSet;
/**
 * ButtonConfirm displays itself as either disable or enable. It's only iteractive when it is enable.
 * 
 * Enability is automated with ButtonCheckable objects added in when all of them return true on isChecked funtion call.
 * When there is one condition returning false, the button disable itself automatically.
 * ButtonCheckable objects must implement ButtonCheckale interface.
 * 
 * ButtonConfirm will call buttonClickedRun function from ButtonRunnable Object.
 * ButtonRunnable object must implement ButtonRunnable interface.
 *
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ButtonConfirm extends Actor
{
    GreenfootImage disableImage = new GreenfootImage("buttonDisabled.png");
    GreenfootImage enableImage = new GreenfootImage("buttonEnabled.png");
    Set<ButtonCheckable> conditionalObjs = new HashSet<ButtonCheckable>();
    boolean enable = false;
    String label;
    ButtonRunnable runObj = null;
    
    /**
     * Act - do whatever the buttonEnabled wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public ButtonConfirm(ButtonRunnable obj, String label) {
        this.runObj = obj;
        disableImage.scale(300,80);
        enableImage.scale(300,80);
        setImage(disableImage);
        this.label = label;
    }
    
    public void enableButton()
    {
        if(enable == false)
        {
            enable = true;
            setImage(enableImage);
        }
    }
    
    public void disableButton()
    {
        if(enable == true)
        {
            enable = false;
            setImage(disableImage);
        }
    }
    
    protected void addedToWorld(World world)
    {
        if(runObj == null)
            runObj = (ButtonRunnable) world;
    }
    
    public void act() 
    {
        // Add your action code here.
        if(conditionalObjs.size() > 0)
            checkConditions();
            
        if(Greenfoot.mousePressed(this) && enable)
        {
            runObj.buttonClickedRun(this);
        }
    }
    
    private void checkConditions()
    {
        for(ButtonCheckable obj:conditionalObjs)
        {
            if(!obj.isChecked())
            {
                disableButton();
                return;
            }
        }
        enableButton();
    }
    
    public void addConditionalObj(ButtonCheckable obj)
    {
        conditionalObjs.add(obj);
    }
    
    public void setRunObj(ButtonRunnable obj)
    {
        this.runObj = obj;
    }
    
    public String getLabel()
    {
        return label;
    }
}
