package api ;

import org.json.* ;
import org.restlet.representation.* ;
import org.restlet.ext.json.* ;
import org.restlet.resource.* ;
import GuessWhoClasses.* ;

public class GuessWhoResource extends ServerResource {

    GuessWho gameInstance = GuessWho.getInstance() ;

    @Get
    public Representation get() throws JSONException {

        String name1 = gameInstance.player1.name ;
        int score1 = gameInstance.player1.score ;
        String char1 = gameInstance.player1.myChar ;
        boolean isfinished1 = gameInstance.player1.isfinished ;
        String name2 = gameInstance.player2.name ;
        int score2 = gameInstance.player2.score ;
        String char2 = gameInstance.player2.myChar ;
        boolean isfinished2 = gameInstance.player2.isfinished ;

        JSONObject json = new JSONObject() ;
        json.put( "name1", name1 ) ;
        json.put( "score1", score1 ) ;
        json.put( "char1", char1 ) ;
        json.put( "isfinished1", isfinished1 ) ;
        json.put( "name2", name2 ) ;
        json.put( "score2", score2 ) ;
        json.put( "char2", char2 ) ;
        json.put( "isfinished2", isfinished2 ) ;

        return new JsonRepresentation ( json ) ;
    }


    @Post
    public Representation post(JsonRepresentation jsonRep) {

        JSONObject json = jsonRep.getJsonObject() ;
	String name1 = json.getString("name1") ;
        int score1 = (int)json.getString("score1") ;
        String char1 = json.getString("char1") ;
        boolean isfinished1 = json.getString("isfinished1") ;
        String name2 = json.getString("name2") ;
        int score2 = (int)json.getString("score2") ;
        String char2 = json.getString("char2") ;
        boolean isfinished2 = json.getString("isfinished2") ;
	
	if(name1 == "player1")
		

        JSONObject response = new JSONObject() ;
        response.put( "success", true ) ;

        return new JsonRepresentation ( response ) ;

    }
}
