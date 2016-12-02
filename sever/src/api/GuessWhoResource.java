package api ;

import org.restlet.representation.* ;
import org.restlet.ext.jackson.* ;
import org.restlet.resource.* ;
import java.util.Date;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import GuessWhoClasses.* ;

public class GuessWhoResource extends ServerResource {

    GuessWho gameInstance = GuessWho.getInstance() ;

    @Get
    public Representation get() {
        
        GameSession returnSession = new GameSession();
        String currentGameId = getQueryValue("gameId");
        for (GameSession session : gameInstance.gameSessions) {
            if(session.gameId.equals(currentGameId))
                returnSession = session;
        }
        if(!returnSession.gameId.equals(currentGameId) & gameInstance.currentSession != null)
            if(gameInstance.currentSession.gameId.equals(currentGameId))
                returnSession = gameInstance.currentSession;
        
       return new JacksonRepresentation<GameSession>(returnSession) ;
    }


    @Post
    public Representation post(Representation rep) {

        GameSession returnSession = new GameSession();
        JacksonRepresentation<Player> playerRep = new JacksonRepresentation<Player> ( rep, Player.class ) ;
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();
		
        try {
            
            Player request = playerRep.getObject() ;            
			request.lastUpdated = dateFormat.format(date);
			
            if(gameInstance.currentSession == null) {
                gameInstance.currentSession = new GameSession();
                if(gameInstance.currentSession.player1 == null) {
                    gameInstance.currentSession.player1 = new Player();
                    gameInstance.currentSession.player1 = request;
                }
                returnSession = gameInstance.currentSession;
            }
            else {
                gameInstance.currentSession.player2 = new Player();
                gameInstance.currentSession.player2 = request;
                returnSession = gameInstance.currentSession;
                gameInstance.gameSessions.add(returnSession);
                gameInstance.currentSession = null;
            }
        } catch (IOException e) {
            
        }

        return new JacksonRepresentation<GameSession>(returnSession) ;

    }

    @Put
    public Representation put(Representation rep) {

        GameSession returnSession = new GameSession();
        String currentGameId = getQueryValue("gameId");
        JacksonRepresentation<Player> playerRep = new JacksonRepresentation<Player> ( rep, Player.class ) ;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();

        try {
            
            Player request = playerRep.getObject() ;
            
            for (GameSession session : gameInstance.gameSessions) {
                if(session.gameId.equals(currentGameId)) {
                    returnSession = session;
                    request.lastUpdated = dateFormat.format(date);
                    if(session.player1.name.equals(request.name)) {
                        session.player1 = request;
                    }
                    else if(session.player2.name.equals(request.name)) {
                        session.player2 = request;
                    }
                }
            }
        } catch (IOException e) {
            
        }

        return new JacksonRepresentation<GameSession>(returnSession) ;

    }

    @Delete
    public Representation delete() {
        
        String currentGameId = getQueryValue("gameId");
		GameSession rm = null;
		for (GameSession session : gameInstance.gameSessions) {
			if(session.gameId.equals(currentGameId))
				rm = session;
		}
		gameInstance.gameSessions.remove(rm);
		
		if(gameInstance.currentSession != null)
            if(gameInstance.currentSession.gameId.equals(currentGameId))
                gameInstance.currentSession = null;
		
       return null ;
    }
}
