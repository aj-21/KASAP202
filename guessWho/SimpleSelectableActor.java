import greenfoot.*;
/**
 * an extension of greenfoot Actor which is able to be selected, deselected via methods select and deselect, respectively.
 * use method isSelected to check if this actor is being selected.
 * 
 * @author SPAAK 
 * @version 1
 */
public class SimpleSelectableActor extends Actor implements Selectable
{
    private Selectable selectable = new SimpleSelectable();
    
    //just detect mouse pressed and toggle selection
    public void act()
    {
        //detectPress();
    }
    
    /**
     *  detectPress works with Greenfoot.MousePressed method to check press event on this actor.
     *  if this function is called, it will return whether an actor is pressed or not. If true, it will toggle selection
     */
    
    public boolean detectPress()
    {
        if (Greenfoot.mousePressed(this))
        {
            toggleSelection();
            return true;
        }
        return false;
    }

    //a helper funtion for toggle Selection
    protected void toggleSelection()
    {
        if(this.isSelected())
            deselect();
        else
            select();
    }
    
    //return true if Actor in a World, not neccessary the current world if there are multiple worlds
    public boolean isInWorld()
    {
        return getWorld() != null;
    }
    
    /**
     *  basic functionality from SimpleSelectable
     */
    @Override
    public void select()
    {
        selectable.select();
    }
    
    @Override
    public void deselect()
    {
        selectable.deselect();
    }
    
    @Override
    public boolean isSelected()
    {
        return selectable.isSelected();
    }
}
