import greenfoot.*;
/**
 * Write a description of class SelectableActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SimpleSelectableActor extends Actor implements Selectable
{
    private Selectable selectable = new SimpleSelectable();
    
    public final void act()
    {
        detectPress();      
    }
    
    protected void detectPress()
    {
        if(Greenfoot.mousePressed(this))
        {
            toggleSelection();
            responseToPress();
        }
    }
    
    private void toggleSelection()
    {
        if(this.isSelected())
            deselect();
        else
            select();
    }
    
    //override this method if you want your actor give visual feedback
    protected void responseToPress()
    {
        
    }
    
    public boolean isInWorld()
    {
        return getWorld() == null;
    }
    
    //basic functionality from SimpleSelectable
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
