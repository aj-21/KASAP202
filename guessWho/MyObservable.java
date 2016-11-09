//import java.util.Observable;
/**
 * MyObservable is a proxy of Observer. this is created for children of other classes wish to use Observer pattern
 * MyObservable replace protected setChanged method to public setCHANGED method, protected clearChanged method to public clearCHANGED method
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
