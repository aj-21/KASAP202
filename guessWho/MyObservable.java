/**
 * MyObservable is a proxy of Observer. this is created for a derived class of other classes to use Observer pattern
 * MyObservable replace protected setChanged method to public setCHANGED method, protected clearChanged method to public clearCHANGED method
 * 
 * @author SPAAK
 * @version 1
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
