package GuessWhoClasses ;

/**
 * Write a description of class GuessWho here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GuessWho
{
    // instance variables - replace the example below with your own
    public static GuessWho GuessWhoInstance ;
    public Player player1;
    public Player player2;

    /**
     * Constructor for objects of class GuessWho
     */
    public GuessWho()
    {
        // initialise instance variables
        player1 = new Player();
        player1.name = "Player1";
        player2 = new Player();
        player2.name = "Player2";
    }
    
    public static GuessWho getInstance() {
		if (GuessWhoInstance == null) {
			GuessWhoInstance = new GuessWho() ;
			return GuessWhoInstance ;
		}
		else {
			return GuessWhoInstance ;
		}
	}
}
