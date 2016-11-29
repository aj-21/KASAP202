package GuessWhoClasses ;

import java.util.ArrayList;

public class GuessWho
{
    public static GuessWho GuessWhoInstance ;
    public ArrayList<GameSession> gameSessions = new ArrayList<GameSession>();
    public GameSession currentSession;

    public GuessWho()
    {
        gameSessions = new ArrayList<GameSession>();
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
