import greenfoot.*;
import java.util.Observer;
import java.util.Observable;
/**
 * Write a description of class ChooseCharacterScreenState here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ChooseCharacterScreenState extends ObservableGameState 
{
    GameSession gameSession;
    public ChooseCharacterScreenState(GameSession gameSession)
    {
        this.gameSession = gameSession;
    }
    
    @Override
    public void update(Observable o, Object arg)
    {
        System.out.println("aaaaa");
        SimpleContainer container = new SimpleContainer(gameSession.getFullSet()); 
        container.getSelected().deselect();
    }
    
    @Override
    public void run()
    {
        
    }
}
