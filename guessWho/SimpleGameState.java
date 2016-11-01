import greenfoot.World;
import java.util.Set;
import java.util.HashSet;
/**
 * Write a description of class SimpleGameState here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class SimpleGameState implements GameState 
{
    Set<Process> processes;
    StatefulWorld statefulWorld;
    String label;
    /*public SimpleGameState(StatefulWorld statefulWorld)
    {
        processes = new HashSet<Process>();
        this.statefulWorld = statefulWorld;
    }*/
    
    public SimpleGameState()
    {
        processes = new HashSet<Process>();
    }
    
    @Override
    public final void stateProcessesRun()
    {
        for(Process process : processes)
            process.processRun();
    }
    
    @Override
    public void addProcess(Process process)
    {
        processes.add(process);
    }
    
    @Override
    public void enter()
    {
    }
    
    @Override
    public void stateRun()
    {
    }
    
    @Override
    public void exit()
    {
        
    }
    
    @Override
    public void setLabel(String label)
    {
        this.label = label;
    }
    
    @Override
    public String getLabel()
    {
        return label;
    }
    
    public Set<Process> getProcesses()
    {
        return processes;
    }
}
