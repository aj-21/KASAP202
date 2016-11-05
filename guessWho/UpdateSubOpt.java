import java.util.Observer;
import java.util.Observable;
import greenfoot.World;
import java.util.Set;
import java.util.HashSet;
/**
 * Write a description of class UpdateSubOpt here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UpdateSubOpt implements Observer
{
    PropertyInfo propertyInfo;
    DisplayCanvas optDisCan;
    
    public UpdateSubOpt(PropertyInfo propertyInfo,DisplayCanvas optDisCan)
    {
        this.propertyInfo = propertyInfo;
        this.optDisCan = optDisCan;
    }
    
    @Override
    public void update(Observable o, Object arg)
    {
        try
        {
            optDisCan.getWorld().removeObjects(optDisCan.getAll());
            optDisCan.clearAll();
            if(arg != null)
            {
                Set<LabelButton> newSet = generateSubOptButSet(((LabelButton)arg).getLabel());
                optDisCan.addAll(newSet);
                optDisCan.setColRow(1,newSet.size()).display();
                return;
            }
        }
        catch (NullPointerException e)
        {
            System.out.println("suboption display canvas needs to be added in world for use");
        }
    }
    
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
