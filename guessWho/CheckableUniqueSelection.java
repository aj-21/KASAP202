import greenfoot.World;
/**
 * Write a description of class CheckableUniqueSelection here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CheckableUniqueSelection extends UniqueSelection implements ButtonCheckable
{
    // instance variables - replace the example below with your own
    public CheckableUniqueSelection(World world)
    {
        super(world);
    }
    
    @Override
    public boolean isChecked()
    {
        return lastSelected != null;         
    }
}
