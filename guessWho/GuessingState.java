import greenfoot.World;

public class GuessingState extends SimpleGameState 
{
    GuessWho world;
    GameSession gameSession;
    public GuessingState(GuessWho world,GameSession gameSession)
    {
        this.world = world;
        this.gameSession = gameSession;
    }
    
    public void stateRun()
    {
        SimpleContainer ccc = new SimpleContainer(gameSession.getPlaySet());
        Character guessedChar = (Character)ccc.getSelected();
        Character yourChar = gameSession.getYourChar();
        //System.out.println(guessedChar.getClass().getName() + " is running");
        if(guessedChar != null && guessedChar.getClass() == yourChar.getClass())
        {
            System.out.println("Congratulation! You win");
        }
        else
        {
            System.out.printf("Guess with %s... Wrong guess! Please try again\n", guessedChar.getClass().getName());
            System.out.printf("The right Char should be %s\n", yourChar.getClass().getName());
            
            //two step removing
            world.removeObject(guessedChar);
            gameSession.getPlaySet().remove(guessedChar);
        }
        world.setState("guessWhoState");
    }
}
