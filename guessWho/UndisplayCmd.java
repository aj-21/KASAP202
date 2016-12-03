/**
 * call receiver to undisplay
 * 
 * @author SPAAK
 * @version 1
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
