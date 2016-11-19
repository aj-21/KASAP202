package api ;

import java.io.IOException;
import org.json.* ;
import org.restlet.representation.* ;
import org.restlet.ext.jackson.* ;
import org.restlet.resource.* ;
import GuessWhoClasses.* ;

public class GuessWhoResource extends ServerResource {

    GuessWho gameInstance = GuessWho.getInstance() ;

    @Get
    public Representation get() throws JSONException {
        
       return new JacksonRepresentation<GuessWho>(gameInstance) ;
    }


    @Post
    public Representation post(Representation rep) {

        JacksonRepresentation<Player> playerRep = new JacksonRepresentation<Player> ( rep, Player.class ) ;
        try {
            
            Player request = playerRep.getObject() ;
        
            if(request.name.equals("Player1"))
                gameInstance.player1 = request;
            else
                gameInstance.player2 = request;
        } catch (IOException e) {
            
        }

        return new JacksonRepresentation<GuessWho>(gameInstance) ;

    }
}
