/**
 * an extension of LabelButton implementing Invoker interface for Command Pattern
 * support methods: setCommand taking in a DisplayCommand, and invoke.
 * any acts on select or deselect will automatically invoke the stored DisplayCommand
 * 
 * @author SPAAK 
 * @version 1
 */
public class LButton extends LabelButton implements Invoker
{
    private DisplayCommand cmd;
    
    public LButton(String label, String fileName)
    {
        super(label,fileName);
    }
    
    public void setCommand(DisplayCommand cmd)
    {
        this.cmd = cmd;
    }
       
    public void invoke()
    {
        if(cmd!=null)
            cmd.execute(this);
    }
    
    @Override
    public void select()
    {
        super.select();
        invoke();
    }
    
    @Override
    public void deselect()
    {
        super.deselect();
        invoke();
    }
}
