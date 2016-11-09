import greenfoot.World;
import java.util.Map;
import java.util.HashMap;
//import java.util.Observable;
//import java.util.Observer;
public class GuessingState extends SimpleGameState 
{
    GuessWho world;
    GameSession gameSession;
    MyObservable o = new MyObservable();
    public GuessingState(GuessWho world,GameSession gameSession)
    {
        this.world = world;
        this.gameSession = gameSession;
        addObserver((Observer)world.getState("scoringState"));
    }
    
    public void stateRun(Object arg)
    {
        SimpleContainer ccc = new SimpleContainer(gameSession.getPlaySet());
        Character guessedChar = (Character)ccc.getSelected();
        Character yourChar = gameSession.getYourChar();
        Map<String,String> guessSession = new HashMap<String,String>();
        guessSession.put("operationType","guess");
        guessSession.put("correctioness","incorrect");
        guessSession.put("tileCount","0");
        if(guessedChar != null && guessedChar.getClass() == yourChar.getClass())
        {
            //System.out.println("Congratulation! You win");
            
            guessSession.put("correctioness","correct");
            guessSession.put("tileCount","1");
        }
        if(guessedChar != null && guessedChar.getClass() != yourChar.getClass())
        {
            //System.out.printf("Guess with %s... Wrong guess! Please try again\n", guessedChar.getClass().getName());
            //System.out.printf("The right Char should be %s\n", yourChar.getClass().getName());
            
            
            guessSession.put("tileCount","1");
            
            //two step removing
            world.removeObject(guessedChar);
            gameSession.getPlaySet().remove(guessedChar);
        }
        
        
        o.setCHANGED();
        o.notifyObservers(guessSession);
        o.clearCHANGED();
        
        world.setState("waitingState");
    }
    
    public void addObserver(Observer observer)
    {
        o.addObserver(observer);
    }
}
