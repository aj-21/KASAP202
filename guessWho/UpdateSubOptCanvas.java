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
public class UpdateSubOptCanvas implements Observer
{
    OptionInfo optionInfo;
    DisplayCanvas optDisCan;
    World world;
    UniqueSelection uni;
    public UpdateSubOptCanvas(World world,OptionInfo optionInfo,DisplayCanvas optDisCan,UniqueSelection uni)
    {
        this.world = world;
        this.optionInfo = optionInfo;
        this.optDisCan = optDisCan;
        this.uni = uni;
    }
    
    @Override
    public void update(Observable o, Object arg)
    {
        //Set<StringButton> subOptButSet;
        world.removeObjects(optDisCan.getActors());
        if(arg != null)
        {
            Set<StringButton> newSet = generateSubOptButSet(((StringButton)arg).getLabel());
            optDisCan.setActors(newSet).display();
            uni.setObjects(newSet);
        }
    }
    
    private Set<StringButton> generateSubOptButSet(String option)
    {
        Set<String> subOptions = optionInfo.getSubOptions(option);
        Set<StringButton> subOptButSet = new HashSet<StringButton>();
        
        for(String subOpt:subOptions)
        {
            subOptButSet.add(new StringButton(subOpt));
        }
        new ZoomContainer(subOptButSet).resizeOnScale(0.7);
        return subOptButSet;
    }
}
