import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
        //CharBox setting
        charBox.setMargin(3.5,2.5);
        addObject(charBox,getWidth()/2,600);
        charBox.addAllChars(gameSession.getAllFromPlayList());
        
        //myCharBox Setting
        CharacterBox myCharBox = new CharacterBox(100,100,1,1);
        myCharBox.setImage("yourCharacterCanvas.png");
        myCharBox.setCharScale(1.2);
        myCharBox.setSelectedCharScale(1);
        addObject(myCharBox,1400,600);
        myCharBox.addCharacter(gameSession.getMyChar());
        
        buttonGuess.addConditionalObj(charBox);
        addObject(buttonGuess,750,350);
        
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
    
    public void removeChar(Character c)
    {
        charBox.removeChar(c);
    }
    
    public void setInteractiveState()
    {
        currentState = interactiveState;
    }
    
    public void setGuessingState()
    {
        currentState = guessingState;
    }
    
}
