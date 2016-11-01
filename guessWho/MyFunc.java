import java.util.Observer;
import java.util.Set;
/**
 * Write a description of class AddObserverToMyObservale here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public final class MyFunc  
{
    private MyFunc()
    {
        
    }
    
    public static void addObserverToMyObservables(Observer observer,SimpleContainer container)
    {
        Set<MyObservableInterface> ooo = container.getAll();
        for(MyObservableInterface o : ooo )
        {
            o.addObserver(observer);
        }
    }
}
