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
public class UpdSubOptRcv extends Observable implements DisplayReceiver
{
    DisplayCanvas subOptCan;      
   
    public UpdSubOptRcv(DisplayCanvas subOptCan)
    {
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
            
            //add buttons and setup to display
            subOptCan.addAll(buts);
            subOptCan.setColRow(1,buts.size()).display();  
            
            //important to send non-selected new set to observer
            setChanged();
            notifyObservers(buts);
            clearChanged();
            
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
            //unclick buttons
            for (LabelButton but:buts)
            {
                if(but.isSelected())
                {
                    but.deselect();
                    break;
                }
            }
            //remove from suboption canvas if there is
            subOptCan.getAll().removeAll(buts);
            
            //important to send non-selected set to observer
            setChanged();
            notifyObservers(buts);
            clearChanged();
        }
        catch (NullPointerException e)
        {
            System.out.println("suboption display canvas needs to be added in world for use");
        }
    }
}
