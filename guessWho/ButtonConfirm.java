import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Set;
import java.util.HashSet;
/**
 * Write a description of class buttonEnabled here.
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
    ButtonRunnable myRunObj;
    
    /**
     * Act - do whatever the buttonEnabled wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public ButtonConfirm(String label) {
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
        myRunObj = (ButtonRunnable) world;
    }
    
    public void act() 
    {
        // Add your action code here.
        if(conditionalObjs.size() > 0)
            checkConditions();
            
        if(Greenfoot.mousePressed(this) && enable)
        {
            myRunObj.buttonClickedRun(this);
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
}
