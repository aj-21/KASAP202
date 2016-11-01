import greenfoot.World;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class FilteringState extends SimpleGameState 
{
    World gameWorld;
    
    public FilteringState(World game)
    {
        this.gameWorld = game;
    }
    
    public void run()
    {
        String filterKey = "HairColor";
        String filterValue = ((GuessWho)gameWorld).getYourChar().getSubOpt(filterKey);
        
        List<Character> characters = ((GuessWho)gameWorld).getAllChars();
        List<Character> removingChars = new ArrayList<Character>();
        for(Character c : characters)
        {
            System.out.println(c.getClass().getName() + "> " + filterKey + " : " +  c.getSubOpt(filterKey));
            if(c.getSubOpt(filterKey) != filterValue)
            {
                System.out.println("not match. removing");
                removingChars.add(c);
            }
            else
                System.out.println("match, keep");
        }
            
        for(Character c : removingChars)
            ((GuessWho)gameWorld).removeChar(c);
        
        ((GuessWho)gameWorld).setInteractiveState(); 
    }
}
