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
    GameState currentState;
    Object stateRunObj = null;
    
    public StatefulWorld(int width, int height, int size)
    {    
        super(width, height, size); 
    }
    
    public final void act()
    {   
        if(currentState != null)
        {
            currentState.stateProcessesRun();
            currentState.stateRun(stateRunObj);
            return;
        }
        System.out.println("Current State is null");
    }
    
    public void setState(GameState nextState)
    {
        if(currentState!=null)
            currentState.exit();
        currentState=nextState;
        System.out.println("entered " + currentState.getClass().getName());
        currentState.enter();
    }
    
    
    
}
