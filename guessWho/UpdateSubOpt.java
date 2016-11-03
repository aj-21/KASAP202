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
    OptionInfo optionInfo;
    DisplayCanvas optDisCan;
    World world;
    UniqueSelection uni;
    SelectionObservable sel;
    public UpdateSubOpt(World world,OptionInfo optionInfo,DisplayCanvas optDisCan,UniqueSelection uni,SelectionObservable sel)
    {
        this.world = world;
        this.optionInfo = optionInfo;
        this.optDisCan = optDisCan;
        this.uni = uni;
        this.sel = sel;
    }
    
    @Override
    public void update(Observable o, Object arg)
    {
        //Set<StringButton> subOptButSet;
        world.removeObjects(optDisCan.getActors());
        if(arg != null)
        {
            Set<StringButton> newSet = generateSubOptButSet(((StringButton)arg).getLabel());
            System.out.println("subopt canvas: number of button = " + newSet.size());
            optDisCan.setActors(newSet).setColRow(1,newSet.size()).display();
            uni.setObjects(newSet);
            sel.setObjects(newSet);
            return;
        }
        uni.clearObjects();
        sel.clearObjects();
    }
    
    private Set<StringButton> generateSubOptButSet(String option)
    {
        Set<String> subOptions = optionInfo.getSubOptions(option);
        Set<StringButton> subOptButSet = new HashSet<StringButton>();
        
        for(String subOpt:subOptions)
        {
            subOptButSet.add(new StringButton(subOpt,"subOptionsButton.png"));
        }
        return subOptButSet;
    }
}
