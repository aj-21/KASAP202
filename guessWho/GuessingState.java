import greenfoot.World;

public class GuessingState implements GameState 
{
    World gameWorld;
    
    public GuessingState(World game)
    {
        this.gameWorld = game;
    }
    
    public void run()
    {
        Character guessedChar = ((GuessWho) gameWorld).getGuessedChar();
        Character yourChar = ((GuessWho)gameWorld).getYourChar();
        if(guessedChar != null && guessedChar.getClass() == yourChar.getClass())
        {
            System.out.println("Congratulation! You win");
            //((GuessWho)gameWorld).removeSelectedChar();
        }
        else
        {
            System.out.printf("Guess with %s... Wrong guess! Please try again\n", guessedChar.getClass().getName());
            System.out.printf("The right Char should be %s\n", yourChar.getClass().getName());
            ((GuessWho)gameWorld).removeChar(guessedChar);
        }
        ((GuessWho)gameWorld).setInteractiveState();
    }
}
