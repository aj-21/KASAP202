import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
    boolean enable = false;
    String label;
    MyWorld myW;
    
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
        enable = true;
        setImage(enableImage);
    }
    
    public void disableButton()
    {
        enable = false;
        setImage(disableImage);
    }
    
    protected void addedToWorld(World world)
    {
        myW = (MyWorld) world;
    }
    
    public void act() 
    {
        // Add your action code here.
        if(Greenfoot.mousePressed(this) && enable)
        {
            myW.buttonClicked(label);
        }
    }    
}
