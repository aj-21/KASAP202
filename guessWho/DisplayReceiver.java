/**
 * DisplayReceiver need to support display and undisplay so that a command can call upon
 * 
 * @author SPAAK
 * @version 1
 */
public interface DisplayReceiver  
{
    public void display(Object arg);
    public void undisplay(Object arg);
}
