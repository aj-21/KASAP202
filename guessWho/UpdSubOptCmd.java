import java.util.Set;
import java.util.HashSet;
/**
 * Write a description of class ConcreteCommand here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UpdSubOptCmd implements DisplayCommand 
{
    DisplayReceiver receiver;
    DisplayCmd displayCmd;
    UndisplayCmd undisplayCmd;
    Set<LabelButton> butSet;
    PropertyInfo propertyInfo;
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
        
        if(but.isSelected())
        {
            displayCmd.execute(propertyInfo.getSubOptButtons(but));
            return;
        }
        undisplayCmd.execute(propertyInfo.getSubOptButtons(but));
        
    }
    
    @Override
    public void setReceiver(DisplayReceiver receiver)
    {
        this.receiver = receiver;
        displayCmd.setReceiver(receiver);
        undisplayCmd.setReceiver(receiver);
    }
    
    private Set<LabelButton> getButSet(LabelButton optBut)
    {
        if (butSet==null)
        {
            
            Set<String> subOptions = propertyInfo.getValues(optBut.getLabel());
            butSet = new HashSet<LabelButton>();
            
            for(String subOpt:subOptions)
            {
                butSet.add(new LabelButton(subOpt,"subOptionsButton.png"));
            }
            
        }
        return butSet;
    }
    
}
