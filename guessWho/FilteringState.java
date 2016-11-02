import greenfoot.World;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observer;
import java.util.Observable;

public class FilteringState extends SimpleGameState implements Observer
{
    GuessWho world;
    GameSession gameSession;
    String option;
    public FilteringState(GuessWho world,GameSession gameSession)
    {
        this.world = world;
        this.gameSession = gameSession;
    }
    
    public void stateRun(Object arg)
    {
        String filterKey = "HairColor";
        String filterValue = gameSession.getMyChar().getSubOpt(filterKey);
        
        Set<Character> playSet = gameSession.getPlaySet();
        Set<Character> rmSet = new HashSet<Character>();
        for(Character c : playSet)
        {
            if(c.getSubOpt(filterKey) != filterValue)
                rmSet.add(c);
        }
            
        for(Character c : rmSet)
        {
            world.removeObject(c);
            playSet.remove(c);
        }
        
        world.setState("waitingState"); 
    }
    
    @Override
    public void update(Observable o, Object arg)
    {
        
    }
    
}
