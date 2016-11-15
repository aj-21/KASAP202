/**
 * Write a description of class SubOptDisplayCmd here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DisplayCmd implements DisplayCommand
{
    DisplayReceiver receiver;
    
    public DisplayCmd()
    {
    }
    
    public void execute(Object arg)
    {
        receiver.display(arg);
    }
    
    @Override
    public void setReceiver(DisplayReceiver receiver)
    {
        this.receiver = receiver;
    }
}
