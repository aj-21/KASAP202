import java.util.Set;
/**
 * a DisplayCommand class that wraps around DisplayCmd and UndisplayCmd to decide when to call one instead the other
 * constructor requires PropertyInfo for singleton SubOptionButtons
 * 
 * @author SPAAK
 * @version 1
 */
public class UpdSubOptCmd implements DisplayCommand 
{
    DisplayReceiver receiver;
    DisplayCmd displayCmd;
    UndisplayCmd undisplayCmd;
    Set<LabelButton> butSet;
    PropertyInfo propertyInfo;
    
    //constructor requires PropertyInfo for singleton SubOptionButtons
    public UpdSubOptCmd (PropertyInfo propertyInfo)
    {
        this.propertyInfo = propertyInfo;
        displayCmd = new DisplayCmd();
        undisplayCmd = new UndisplayCmd();
    }
    
    @Override 
    public void execute(Object arg)
    {
        if(receiver == null)
            return;
        
        LButton but = (LButton)arg;
        // call displayCmd to display  suboption buttons when a new option button is selected
        if(but.isSelected())
        {
            displayCmd.execute(propertyInfo.getSubOptButtons(but));
            return;
        }
        // call displayCmd to remove  suboption buttons when a new option button is deselected
        undisplayCmd.execute(propertyInfo.getSubOptButtons(but));
        
    }
    
    //receiver will be set for nested display command also
    @Override
    public void setReceiver(DisplayReceiver receiver)
    {
        this.receiver = receiver;
        displayCmd.setReceiver(receiver);
        undisplayCmd.setReceiver(receiver);
    }
    
}
