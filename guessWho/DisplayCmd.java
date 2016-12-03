/**
 * call receiver to display
 * 
 * @author SPAAK
 * @version 1
 */
public class DisplayCmd implements DisplayCommand
{
    DisplayReceiver receiver;
    
    public DisplayCmd()
    {
    }
    
    public void execute(Object arg)
    {
        if(receiver != null)
            receiver.display(arg);
    }
    
    @Override
    public void setReceiver(DisplayReceiver receiver)
    {
        this.receiver = receiver;
    }
}
