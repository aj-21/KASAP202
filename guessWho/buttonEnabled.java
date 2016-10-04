import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class buttonEnabled here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class buttonEnabled extends Actor
{
    /**
     * Act - do whatever the buttonEnabled wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public buttonEnabled() {
        getImage().scale(300,80);
    }
    public void act() 
    {
        // Add your action code here.
        if(Greenfoot.mousePressed(this))
        {
            Greenfoot.setWorld(new GuessWho(new GameSession()));
        }
    }    
}
