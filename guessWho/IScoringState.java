import greenfoot.*;
import java.util.Set;
import java.util.HashSet;
/**
 * Write a description of class IScoringState here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IScoringState  
{
    World world;
    GameSession gameSession;
    Character yourChar;
    int turnCount = 0;
    int baseScore = 100;

    int tileCount;
    
    int REDUCT_PER_TURN = 2;
    double GUESS = 2;
    double FILTER = 1.05;
    double CORRECT = 1;
    double INCORRECT = 0.9;
    double WIN = 2.5;
    public void IScoringState(World world,GameSession gameSession)
    {
        this.world = world;
        this.gameSession = gameSession;
        yourChar = gameSession.getYourChar();
    }
    
    public void run()
    {
        for(Character c:gameSession.getPlaySet())
        {
            if(c.isSelected())
            {
                guess();
                break;
            }
            filter();
        }
    }
    
    public void guess()
    {
        SimpleContainer ccc = new SimpleContainer(gameSession.getPlaySet());
        Character guessedChar = (Character)ccc.getSelected();
        
        if(guessedChar.getClass() == yourChar.getClass())
        {
            System.out.printf("winnnnnnnnnnnnnnnnnn");
            updateScore(GUESS,CORRECT,1);
        }
        if(guessedChar != null && guessedChar.getClass() != yourChar.getClass())
        {
            System.out.printf("Guess with %s... Wrong guess! Please try again\n", guessedChar.getClass().getName());
            System.out.printf("The right Char should be %s\n", yourChar.getClass().getName());
            
            //two step removing
            world.removeObject(guessedChar);
            gameSession.getPlaySet().remove(guessedChar);
            
            updateScore(GUESS,INCORRECT,1);
        }
        
    }
    
    public void filter()
    {
        
        SimpleContainer o = new SimpleContainer(gameSession.getPropertyInfo().getOptButtons());
        LButton filterOptBut = (LButton)o.getSelected();
        SimpleContainer s = new SimpleContainer(gameSession.getPropertyInfo().getSubOptButtons(filterOptBut));
        LButton filterSubOptBut = (LButton)s.getSelected();
        
        //if no option is currently selected >> no guess no filter
        if(filterOptBut == null || filterSubOptBut == null)
        {
            updateScore(FILTER,INCORRECT,0);
            return;
        }

        
        String filterKey = filterOptBut.getLabel();
        String filterValue = filterSubOptBut.getLabel(); 
        double correctioness = INCORRECT;
        String secretValue = yourChar.getPropertyValue(filterKey);
        if(filterValue.equals(secretValue))
            correctioness = CORRECT;
        
        PropertyCriteria valueFilter = new CriteriaValue(gameSession,filterKey);
        Set<Character> rmSet = valueFilter.notMeetCriteria(gameSession.getPlaySet());
        
        //two step remove
        gameSession.getPlaySet().removeAll(rmSet);
        world.removeObjects(rmSet);
        
        updateScore(FILTER,correctioness,rmSet.size());
        
    }
    
    public void updateScore(double operation,double correctioness,int tileCount)
    {
        int score = baseScore;
        score *= (100 - (turnCount-1)*REDUCT_PER_TURN );
        score /= 100;
        
        score*= correctioness;
        score *= tileCount;
        
        //Score is also affected by operation type.
        //you get higher factor for guessing due to riskiness, but only 1 tile count
        //you can eliminate 2 or more,gfilter is better
        score *= operation;
        
        //if you guess the right one, you get double score for this turn;
        if(operation == GUESS && correctioness == CORRECT )
        {
            score *= WIN;
        }
        
        score += gameSession.getMyScore();
        gameSession.setMyScore(score);
        
        System.out.println(gameSession.getMyScore());
    }
}
