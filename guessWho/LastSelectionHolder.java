import greenfoot.Actor;
import java.util.Observer;
import java.util.Observable;
/**
 * Write a description of class LastSelectionHolder here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LastSelectionHolder extends Observable implements Observer
{
    SimpleSelectableActor lastSelected;
    @Override
    public void update(Observable o, Object arg)
    {
        lastSelected = (SimpleSelectableActor)arg;
        setChanged();
        notifyObservers(lastSelected);
        clearChanged();
        
    }
}
