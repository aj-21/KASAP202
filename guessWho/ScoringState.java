//import java.util.Observer;
//import java.util.Observable;
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
    Character yourChar;
    
    int baseScore = 100;
    
    int turnCount;
    
    String operationType;
    double operation;
    double correctioness;
    int tileCount;
    
    int REDUCT_PER_TURN = 2;
    double GUESS = 2;
    double FILTER = 1.05;
    double CORRECT = 1;
    double INCORRECT = 0.9;

    /**
     * Constructor for objects of class ScoringState
     */
    public ScoringState(GuessWho world, GameSession gameSession)
    {
        this.world = world;
        this.gameSession = gameSession;
        this.turnCount = 0;
        yourChar = gameSession.getYourChar();
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
        //System.out.println("baseScore is "+ score);
        score *= (100 - (turnCount-1)*REDUCT_PER_TURN );
        score /= 100;
        //System.out.println("turn "+turnCount+" - score is "+ score);
        
        //score depends on correctioness, only 80% if incorrect
        score*= correctioness;
        //System.out.println("correctioness "+correctioness+" - score is "+ score);
        
        //score change according to number of elimination, the more elimination, the higher score for that turn
        score *= tileCount;
        //System.out.println("tileCount "+tileCount+" - score is "+ score);
        
        //Score is also affected by operation type.
        //you get higher factor for guessing due to riskiness, but only 1 tile count
        //you can eliminate 2 or more,gfilter is better
        score *= operation;
        //System.out.println("operation "+operation+" - score is "+ score);
        
        //if you guess the right one, you get double score for this turn;
        if(operation == GUESS && correctioness == CORRECT )
        {
            score *= 2.5;
            //System.out.println("correct guess - score is "+ score);
        }
        
        //System.out.println("");
        updateScore(score);
    }
    
    @Override
    public void update(Observable o, Object arg)
    {
        if(arg instanceof Map)
        {
            Map<String,String> myMap = ((HashMap)arg);
            turnCount ++;
            //System.out.println("turn: " + turnCount);
            operation = computeOp(  myMap.get("operationType") );
            correctioness  = computeCrt( myMap.get("correctioness") );
            tileCount = computeTileCount (myMap.get("tileCount"));
            score();
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
    
    private int computeTileCount(String tileCount)
    {
        return Integer.parseInt(tileCount);
    }
    
    protected void updateScore(int score)
    {
        int myScore = gameSession.getMyScore();
        myScore += score;
        gameSession.setMyScore(myScore);
        System.out.println("You have gained ["+ score+"] point, your new score is [" + myScore + "]" );
    }
}
