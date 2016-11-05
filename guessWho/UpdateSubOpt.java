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
    World world;
    UniqueSelection uni;
    SelectionObservable sel;
    public UpdateSubOpt(World world,PropertyInfo propertyInfo,DisplayCanvas optDisCan,UniqueSelection uni,SelectionObservable sel)
    {
        this.world = world;
        this.propertyInfo = propertyInfo;
        this.optDisCan = optDisCan;
        this.uni = uni;
        this.sel = sel;
    }
    
    @Override
    public void update(Observable o, Object arg)
    {
        //Set<LabelButton> subOptButSet;
        world.removeObjects(optDisCan.getAll());
        if(arg != null)
        {
            Set<LabelButton> newSet = generateSubOptButSet(((LabelButton)arg).getLabel());
            optDisCan.setActors(newSet).setColRow(1,newSet.size()).display();
            uni.setObjects(newSet);
            sel.setObjects(newSet);
            return;
        }
        uni.clearObjects();
        sel.clearObjects();
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
