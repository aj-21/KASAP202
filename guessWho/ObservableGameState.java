import java.util.Observer;
import java.util.Observable;

/**
 * Write a description of class ObservableGameState here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ObservableGameState implements GameState,Observer
{
    @Override
    public void update(Observable o, Object arg)
    {
        System.out.println("???");
    }
    
    @Override
    public void run()
    {
        
    }
}
