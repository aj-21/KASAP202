import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Set;
import java.util.HashSet;
/**
 * Write a description of class GuessWho here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GuessWho extends StatefulWorld
{
    Set<GameState> gameStates = new HashSet<GameState>();
    
    GameState interactiveState;
    GameState guessingState;
    GameState filteringState;
    //GameState interactiveState = new InteractiveState(this);
    
    GameState guessWhoState;
    GameSession gameSession;
    CharacterBox charBox = new CharacterBox(925,460,5,2);

    public GuessWho(GameSession gameSession)
    {    
        super(1536, 864, 1); 
        this.gameSession = gameSession;
        guessWhoState = new GuessWhoState(this,gameSession);
        guessingState = new GuessingState(this,gameSession);
        currentState = guessWhoState;
        setup();
    }
    
    private void setup()
    {
        //charCanvas setting
        ZoomContainer playCon = new ZoomContainer(gameSession.getPlaySet());
        playCon.resizeOnScale(0.8);   
        DisplayCanvas charCanvas = new DisplayCanvas(this,playCon);
        addObject(charCanvas,getWidth()/2,600);
        charCanvas.setBackground("characterCanvas.png").setMargin(3.5,3.5,2.5,2.5).display();     
        
        //myCharCanvas setting
        ZoomContainer myCharCon = new ZoomContainer(gameSession.getMyChar());
        myCharCon.setZoomScale(1);
        myCharCon.resizeOnScale(1.5);
        DisplayCanvas myCharCanvas = new DisplayCanvas(this,myCharCon);
        addObject(myCharCanvas,1400,600);
        myCharCanvas.setBackground("yourCharacterCanvas.png").setColRow(1,1).display();   
        
        //EnableButton guessButton = new EnableButton("guess");
        //addObject(guessButton,600,850);

    }
    
    public GameState getState(String stateName)
    {
        switch (stateName)
        {
            case "guessWhoState": return guessWhoState;
            case "guessingState": return guessingState; 
            default: return guessWhoState;
        }
    }
    
    public void setState(String stateName)
    {
        setState(getState(stateName));
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
