import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Set;

/**
 * flatform for game state to work
 * 
 * @author SPAAK
 * @version 1
 */
public abstract class StatefulWorld extends World
{    
    public StatefulWorld(int width, int height, int size)
    {    
        super(width, height, size); 
    }
 
    public abstract GameState getState(String stateName);
    
    public abstract void setState(String stateName);
       
}
