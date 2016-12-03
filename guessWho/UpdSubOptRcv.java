import java.util.Set;
/**
 * This class is a displayReceiver which performs display and undisplay on a DisplayCanvas (suboption Canvas this case) 
 * 
 * @author SPAAK 
 * @version 1
 */
public class UpdSubOptRcv implements DisplayReceiver
{
    DisplayCanvas subOptCan;      
    
    //constructors requries subOption canvas (DisplayCanvas) to call methods
    public UpdSubOptRcv(DisplayCanvas subOptCan)
    {
        this.subOptCan = subOptCan;
    }
    
    //display new suboption buttons
    @Override
    public void display(Object arg)
    {
        try
        {
            //remove previous buttons if there is
            subOptCan.clearAll();
            //get the buttons
            Set<LabelButton> buts = (Set<LabelButton>)arg;
            
            //add new buttons and setup to display
            subOptCan.addAll(buts);
            subOptCan.setColRow(1,buts.size()).display();              
        }
        catch (NullPointerException e)
        {
            System.out.println("suboption display canvas needs to be added in world for use");
        }
    }
    
    //remove old suboption buttons
    @Override
    public void undisplay(Object arg)
    {
        try
        {
            //get the suboption buttons
            Set<LabelButton> buts = (Set<LabelButton>)arg;
            //remove from world
            subOptCan.getWorld().removeObjects(buts);
            //unclick buttons
            SimpleContainer sc = new SimpleContainer(buts);
            sc.getSelected().deselect();

            //remove old buttons from suboption canvas if there is
            subOptCan.getAll().removeAll(buts);
        }
        catch (NullPointerException e)
        {
            System.out.println("suboption display canvas needs to be added in world for use");
        }
    }
}
