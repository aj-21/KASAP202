import java.util.Observable;
/**
 * Write a description of class MyObservable here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyObservable extends Observable 
{
    public void setCHANGED()
    {
        setChanged();
    }
    
    public void clearCHANGED()
    {
        clearChanged();
    }
}
