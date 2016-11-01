import java.util.Set;
public interface GameState  
{
    public void enter();
    public void stateRun();
    public void stateProcessesRun();
    public void addProcess(Process process);
    public void exit();
    public void setLabel(String label);
    public String getLabel();
    public Set<Process> getProcesses();
}
