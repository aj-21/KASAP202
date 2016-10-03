import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GuessWho here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GuessWho extends World implements MyWorld
{
    CharacterBox charBox = new CharacterBox(925,460,5,2);
    Character yourChar;
    Character secretChar;
    Character guessChar;
    ButtonConfirm buttonGuess= new ButtonConfirm("guess");
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public GuessWho(Character choosenChar)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1536, 864, 1); 
        this.yourChar = choosenChar;

        this.secretChar = randomChar();
        System.out.println("your character is: " + choosenChar.getClass().getName());
        setup();
    }
    
    private Character randomChar()
    {
        // for testing
        return yourChar;
        
    }
    
    private void setup()
    {
        //CharacterBox charBox = new CharacterBox(925,460,5,2);
        charBox.setMargin(3.5,2.5);
        addObject(charBox,getWidth()/2,600);
        charBox.addCharacter(new Char1());
        charBox.addCharacter(new Char2());
        charBox.addCharacter(new Char3());
        charBox.addCharacter(new Char4());
        charBox.addCharacter(new Char5());
        charBox.addCharacter(new Char6());
        charBox.addCharacter(new Char7());
        charBox.addCharacter(new Char8());
        charBox.addCharacter(new Char9());
        
        CharacterBox yourCharBox = new CharacterBox(100,100,1,1);
        yourCharBox.setImage("yourCharacterCanvas.png");
        yourCharBox.setCharScale(1.2);
        yourCharBox.setSelectedCharScale(1);
        addObject(yourCharBox,1400,600);
        yourCharBox.addCharacter(yourChar);
        
        addObject(buttonGuess,600,400);
        
    }
    
    public void act()
    {
        guessChar = charBox.getSelectedChar();
        if(guessChar!=null)
            buttonGuess.enableButton();
        else
            buttonGuess.disableButton();   
        
    }
    
    public void buttonClicked(ButtonConfirm button)
    {
        if(button == buttonGuess)
            guessProcessing();
    }
    
    protected void guessProcessing()
    {
        Character selectedChar = charBox.getSelectedChar();
        if(selectedChar != null && selectedChar.getClass() == secretChar.getClass())
        {
            System.out.println("Congratulation! You win");
            charBox.removeSelectedChar();
        }
        else
        {
            System.out.printf("Guess with %s... Wrong guess! Please try again\n", selectedChar.getClass().getName());
            System.out.printf("The right Char should be %s\n", secretChar.getClass().getName());
            charBox.removeSelectedChar();
        }
        
    }
}
