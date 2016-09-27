import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Characters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Character extends Actor
{   
    int oriWidth = getImage().getWidth();
    int oriHeight =getImage().getHeight();
    boolean selected;
    double scale = 1.5;
    
    public Character()
    {
        GreenfootImage image = getImage();
        image.scale(141,192);
    }
    
    /**
     * Act - do whatever the Characters wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        checkClicked();
        if (selected){
            
            double w = oriWidth*scale;
            double h = oriHeight*scale;
            getImage().scale((int)w,(int)h);
        }
        else 
            getImage().scale(oriWidth,oriHeight);
    }    
    
    public void resize(int width, int height)
    {
        getImage().scale(width,height);
        oriWidth = getImage().getWidth();
        oriHeight =getImage().getHeight();
    }
    
    private void checkClicked()
    {
        if(Greenfoot.mousePressed(this))
        {
            System.out.println(this.getClass().getName() + " is clicked");
            if(selected) 
                selected = false;
            else
                selected = true;
        }     
    }
    
    public boolean isSelected()
    {
        return selected;
    }
    
    public void unSelect()
    {
        selected = false; 
    }
}
