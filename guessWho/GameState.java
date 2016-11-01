import java.util.Set;
public interface GameState  
{
    public void enter();
    public void stateRun(Object arg);
    public void stateProcessesRun();
    public void addProcess(Process process);
    public void exit();
    public void setLabel(String label);
    public String getLabel();
    public Set<Process> getProcesses();
}
