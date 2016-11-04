
/**
 * Selectable interface requires select() method to select object, deselect to deselect object, and isSelected return if object is select or not
 * 
 * SPAAK
 * version 1
 */

public interface Selectable  
{
    public void select();
    public void deselect();    
    public boolean isSelected();
}
