//import java.util.Observer;
//import java.util.Observable;
import greenfoot.World;
import java.util.Set;
import java.util.HashSet;
/**
 * This class is used for updating the sub options after selecting the options.
 * 
 * @author SPAAK 
 * @version 1
 */
public class UpdSubOptRcv implements DisplayReceiver
{
    PropertyInfo propertyInfo;
    DisplayCanvas subOptCan;      
   
    public UpdSubOptRcv(PropertyInfo propertyInfo,DisplayCanvas subOptCan)
    {
        this.propertyInfo = propertyInfo;
        this.subOptCan = subOptCan;
    }
    
    @Override
    public void display(Object arg)
    {
        try
        {
            //remove previous buttons if there is
            subOptCan.clearAll();
            //get the buttons
            Set<LabelButton> buts = (Set<LabelButton>)arg;
            //unclick buttons
            for (LabelButton but:buts)
            {
                if(but.isSelected())
                {
                    but.deselect();
                    break;
                }
            }
            
            //add buttons and setup to display
            subOptCan.addAll(buts);
            subOptCan.setColRow(1,buts.size()).display();            
        }
        catch (NullPointerException e)
        {
            System.out.println("suboption display canvas needs to be added in world for use");
        }
    }
    
    @Override
    public void undisplay(Object arg)
    {
        try
        {
            //get the button
            Set<LabelButton> buts = (Set<LabelButton>)arg;
            //remove from world
            subOptCan.getWorld().removeObjects(buts);
            //remove from suboption canvas if there is
            subOptCan.getAll().removeAll(buts);
            return;
        }
        catch (NullPointerException e)
        {
            System.out.println("suboption display canvas needs to be added in world for use");
        }
    }
    
    /***
     * generate new buttons if the set is null
     */
    private Set<LabelButton> generateSubOptButSet(String option)
    {
        Set<String> subOptions = propertyInfo.getValues(option);
        Set<LabelButton> subOptButSet = new HashSet<LabelButton>();
        
        for(String subOpt:subOptions)
        {
            subOptButSet.add(new LabelButton(subOpt,"subOptionsButton.png"));
        }
        return subOptButSet;
    }
}
