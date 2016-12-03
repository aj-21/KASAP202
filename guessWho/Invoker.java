/**
 * Invoker interface for command pattern
 * invoker will have a command and invoke it when it needs
 * 
 * @author SPAAK 
 * @version 1
 */
public interface Invoker  
{
    public void setCommand(DisplayCommand cmd);
    public void invoke();
}
