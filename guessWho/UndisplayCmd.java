/**
 * Write a description of class CopyOfSubOptUndisplayCmd here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UndisplayCmd implements DisplayCommand
{
    DisplayReceiver receiver;
    
    public UndisplayCmd()
    {
    }
    
    @Override
    public void execute(Object arg)
    {
        if(receiver != null)
            receiver.undisplay(arg);
    }
    
    @Override
    public void setReceiver(DisplayReceiver receiver)
    {
        this.receiver = receiver;
    }
}
