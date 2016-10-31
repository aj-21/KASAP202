import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class GuessWho here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GuessWho extends World
{
    GameState interactiveState = new InteractiveState(this);
    GameState guessingState = new GuessingState(this);
    GameState filteringState = new FilteringState(this);
    //GameState interactiveState = new InteractiveState(this);
    GameState currentState;
    GameSession gameSession;
    CharacterBox charBox = new CharacterBox(925,460,5,2);
    ButtonConfirm buttonGuess= new ButtonConfirm((ButtonRunnable)interactiveState, "guessing");
    ButtonConfirm buttonFilter= new ButtonConfirm((ButtonRunnable)interactiveState, "filtering");

    public GuessWho(GameSession gameSession)
    {    
        super(1536, 864, 1); 
        this.gameSession = gameSession;
        currentState = interactiveState;
        setup();
    }
    
    private void setup()
    {
        //charCanvas setting
        ZoomContainer charCon = new ZoomContainer(gameSession.getPlaySet());
        charCon.resizeOnScale(0.8);   
        DisplayCanvas charCanvas = new DisplayCanvas(this,charCon);
        addObject(charCanvas,getWidth()/2,600);
        charCanvas.setBackground("characterCanvas.png").setMargin(3.5,3.5,2.5,2.5).display();     
        
        //myCharCanvas setting
        ZoomContainer myCharCon = new ZoomContainer(gameSession.getMyChar());
        myCharCon.setZoomScale(1);
        myCharCon.resizeOnScale(1.5);
        DisplayCanvas myCharCanvas = new DisplayCanvas(this,myCharCon);
        addObject(myCharCanvas,1400,600);
        myCharCanvas.setBackground("yourCharacterCanvas.png").setColRow(1,1).display();                
    }
    
    public void act()
    {  
        currentState.run();
    }
    
    public Character getGuessedChar()
    {
        return charBox.getSelectedChar();
    }
    
    public Character getYourChar()
    {
        return gameSession.getYourChar();
    }
    
    public List<Character> getAllChars()
    {
        return charBox.getAllCharacters();
    }
    
    public void removeChar(Character c)
    {
        charBox.removeObject(c);
    }
    
    public void setInteractiveState()
    {
        currentState = interactiveState;
    }
    
    public void setGuessingState()
    {
        currentState = guessingState;
    }
    
    public void setFilteringState()
    {
        currentState = filteringState;
    }
}
