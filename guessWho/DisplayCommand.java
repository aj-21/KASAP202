/**
 * interface for display command to display
 * 
 * @author SPAAK
 * @version 1
 */
public interface DisplayCommand  
{
    public void setReceiver(DisplayReceiver receiver);
    //execute will take in an object and perform tasks based on this object infomation on receiver
    public void execute(Object arg);
}
