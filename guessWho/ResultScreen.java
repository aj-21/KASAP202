import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ResultScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ResultScreen extends World
{
    GameSession gameSession;
    /**
     * Constructor for objects of class ResultScreen.
     * 
     */
    public ResultScreen(GameSession gameSession)
    {    
        
        super(1536, 864, 1); 
        this.gameSession = gameSession;
        prepare();
    }
    
    private void prepare()
    {
        Character yourChar = gameSession.getYou().getChosenChar();
        yourChar.resizeOnScale(2);
        addObject(yourChar,getWidth()/2,getHeight()/2);
    }
}
