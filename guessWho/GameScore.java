/**
 * Write a description of class Score here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameScore  
{
    final int BASE_SCORE = 100;
    final int REDUCT_PER_TURN = 2;
    int turnCounter;
    int score;
    public GameScore()
    {
        
    }
    
    public void addScore(int addS)
    {
        this.score += addS;
    }
    
    public int getScore()
    {
        return score;
    }
    
    public void incrementTurn()
    {
        turnCounter ++;
    }
    
    public int getTurn()
    {
        return turnCounter;
    }
    
    public int getBaseScoreOnTurn()
    {
        int BaseScore = BASE_SCORE;
        BaseScore *= (100 - (turnCounter-1)*REDUCT_PER_TURN );
        BaseScore /= 100;
        
        return score;
    }
    
}

