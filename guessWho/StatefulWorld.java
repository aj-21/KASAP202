import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Set;

/**
 * Write a description of class StatefulWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class StatefulWorld extends World
{
    //protected GameState currentState;
    
    public StatefulWorld(int width, int height, int size)
    {    
        super(width, height, size); 
    }

    
    public abstract GameState getState(String stateName);
    
    public abstract void setState(String stateName);
    
    public abstract GameState getCurrentState();
    
    public abstract boolean isCurrentState(GameState gameState);
    
}
