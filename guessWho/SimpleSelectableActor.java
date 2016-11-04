import greenfoot.*;
/**
 * Write a description of class SelectableActor here.
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
        detectPress();
    }
    
    /**
     *  detectPress works with Greenfoot.MousePressed method to check press event on this actor.
     *  it is important to add this method into act if method act is override, or two other helper function can be used
     */
    protected boolean detectPress()
    {
        if (isPressed())
        {
            toggleSelection();
            return true;
        }
        return false;
    }
    
    //return true if the actor is clicked
    protected boolean isPressed()
    {
        return Greenfoot.mousePressed(this);
    }
    
    //if actor is selected, deselect, and vice versa
    protected void toggleSelection()
    {
        if(this.isSelected())
            deselect();
        else
            select();
    }
    
    //return true if Actor in a World
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
