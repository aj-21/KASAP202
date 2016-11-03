import java.util.Observer;
import java.util.Observable;
import java.util.Map;
import java.util.HashMap;
/**
 * Write a description of class ScoringState here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScoringState extends SimpleGameState implements Observer
{
    // instance variables - replace the example below with your own
    GuessWho world;
    GameSession gameSession;
    
    int baseScore;
    
    int turnCount;
    
    String operationType;
    double operation;
    double correctioness;
    int tileCount;
    
    int REDUCT_PER_TURN = 2;
    double GUESS = 1.5;
    double FILTER = 1;
    double CORRECT = 1;
    double INCORRECT = 0.8;
    
        
    

    /**
     * Constructor for objects of class ScoringState
     */
    public ScoringState(GuessWho world, GameSession gameSession)
    {
        this.world = world;
        this.gameSession = gameSession;
        this.baseScore = 100;
        this.turnCount = 0;
    }
    
    public void stateRun(Object arg)
    {
        score();
        world.setState("waitingState");
    }
    
    public void score()
    {
        //basescore reduce per turn from 100%, 100% for turn 1, 98% for turn 2
        int score = baseScore;
        score *= (100 - (turnCount-1)*REDUCT_PER_TURN);
        //score depends on correctioness, only 80% if incorrect
        score*= correctioness;
        //score change according to number of elimination, the more elimination, the higher score for that turn
        score *= tileCount;
        //Score is also affected by operation type.
        //you get higher factor for guessing due to riskiness, but only 1 tile count
        //you can eliminate 2 or more,gfilter is better
        score *= operation;
        //if you guess the right one, you get double score for this turn;
        if(operationType.equals("") )
            score *= 2;
        updateScore(score);
    }
    
    @Override
    public void update(Observable o, Object arg)
    {
        if(arg instanceof EnableButton)
        {
            operationType = ((EnableButton)arg).getLabel(); 
            operation = computeOp(  operationType );
            //update turnCount
            turnCount ++;
        }
        if(arg instanceof Map)
        {
            Map<String,String> myMap = ((HashMap)arg);
            correctioness  = computeCrt( myMap.get("correctioness") );
            tileCount = computeTileCount (myMap.get("count"));
        }
    }
    
    private double computeOp(String opLabel)
    {
        if(opLabel.equals("guess"))
            return GUESS;
        return FILTER;
    }

    private double computeCrt(String corec)
    {
        if(corec.equals("correct"))
            return CORRECT;
        return INCORRECT;
    }
    
    private int computeTileCount(String count)
    {
        switch (count)
        {
            case "2": return 2;
            case "3": return 3;
            case "4": return 4;
            case "5": return 5;
            case "6": return 6;
            case "7": return 7;
            case "8": return 8;
            
            default: return 1;
        }
    }
    
    protected void updateScore(int Score)
    {
        
    }
}
