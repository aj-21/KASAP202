/**
 * Write a description of class Command here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface DisplayCommand  
{
    public void setReceiver(DisplayReceiver receiver);
    public void execute(Object arg);
}
