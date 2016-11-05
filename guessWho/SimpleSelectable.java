/**
 * a simple class able to store states of selection and return it.
 * 
 * @author SPAAK
 * @version (a version number or a date)
 */
public class SimpleSelectable implements Selectable 
{
    // instance variables - replace the example below with your own
    private boolean is_selected = false;

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void select()
    {
        this.is_selected = true;
    }
    
    public void deselect()
    {
        this.is_selected = false;
    }
    
    public boolean isSelected()
    {
        return is_selected;
    }
}
