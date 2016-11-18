import greenfoot.*;
import java.util.Set;
import java.util.HashSet;
/**
 * Write a description of class IScoringState here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IScoreState extends IGameState
{
    GuessWho world;
    GameSession gameSession;
    Character yourChar;
    DummyImage blockImg;
    
    int turnCount = 0;
    int baseScore = 100;
    
    int REDUCT_PER_TURN = 2;
    double GUESS = 2;
    double FILTER = 1.05;
    double CORRECT = 1;
    double INCORRECT = 0.9;
    double WIN = 2.5;
    
    int stateTime;
    
    public IScoreState(GuessWho world,GameSession gameSession)
    {
        this.world = world;
        this.gameSession = gameSession;
        yourChar = gameSession.getYourChar();
        stateTime = 10;
        blockImg = new DummyImage("backgroundGreyDimCanvas.png");
    }
    
    public void pressHandle(int x, int y)
    {
    }
    
    public void setSuccessor(PressHandler successor)
    {
        
    }
    
    public void enter()
    {
        world.addObject(blockImg,blockImg.getImage().getWidth()/2,blockImg.getImage().getHeight()/2);
        //send message to the next state?????????????????????????
        SimpleContainer ccc = new SimpleContainer(gameSession.getPlaySet());
        Character guessedChar = (Character)ccc.getSelected();
        if(guessedChar!=null)
        {
            guess(guessedChar);
            return;
        }
        filter();
    }
    
    public void stateRun()
    {
        

    }
    
    public void exit()
    {
        if (world.isCurrentState(this))
        {
            world.setState("guessWhoState");
            stateTime = 10;
            world.removeObject(blockImg);
        }
    }
    
    public void secondUpdate()
    {
        if(stateTime >0)
        {
            stateTime -= 1;
            System.out.println(stateTime);
            if (stateTime ==0)
            {
                exit();
            }
        }
    }
    
    public void guess(Character guessedChar)
    {
        System.out.println("guessing");
        if(guessedChar.getClass() != yourChar.getClass())
        {
            //two step removing
            world.removeObject(guessedChar);
            gameSession.getPlaySet().remove(guessedChar);
            
            updateScore(GUESS,INCORRECT,1);
            return;
        }
        //else win 
        updateScore(GUESS,CORRECT,1);
        
    }
    
    public void filter()
    {
        System.out.println("filter");
        SimpleContainer o = new SimpleContainer(gameSession.getPropertyInfo().getOptButtons());
        LButton filterOptBut = (LButton)o.getSelected();
        
        //if no option is currently selected >> no guess no filter
        if(filterOptBut == null)
        {
            updateScore(FILTER,INCORRECT,0);
            return;
        }
        
        SimpleContainer s = new SimpleContainer(gameSession.getPropertyInfo().getSubOptButtons(filterOptBut));
        LButton filterSubOptBut = (LButton)s.getSelected();
        if(filterSubOptBut == null)
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
