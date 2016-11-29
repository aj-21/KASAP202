package GuessWhoClasses ;

import java.util.UUID;

public class GameSession
{
    public String gameId;
    public Player player1;
    public Player player2;

    public GameSession()
    {
        gameId =  UUID.randomUUID().toString();
    }
}
