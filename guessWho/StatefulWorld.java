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
    Set<Process> processes;
    GameState currentState;
    
    public StatefulWorld(int width, int height, int size)
    {    
        
        super(width, height, size); 
    }
    
    public final void act()
    {   
        if(currentState != null)
        {
            
            for(Process process:processes)
                process.processRun();
            currentState.stateRun();
            return;
        }
        System.out.println("Current State is null");
    }
    
    public void setState(GameState nextState)
    {
        currentState.exit();
        currentState=nextState;
        processes = currentState.getProcesses();
        currentState.enter();
    }
    
    
    
}
