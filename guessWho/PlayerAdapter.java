import org.restlet.representation.Representation ;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.ext.jackson.* ;
import org.restlet.resource.* ;
import org.restlet.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
 
/**
 * Write a description of class PlayerAdapter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerAdapter implements PlayerOperations
{
    // instance variables - replace the example below with your own
    private Player player1;
    private Player player2;
    String url = "http://35.164.41.209/GuessWho";
    //String url = "http://localhost:8080/GuessWho";
    
    public Player getYou(Player player, String SessionId)
    {
        GameSessionWrapper response = new GameSessionWrapper();

        ClientResource cr = new ClientResource(url + "?gameId=" + SessionId);
        Representation repr = cr.get();
        
        JacksonRepresentation<GameSessionWrapper> gameSession = new JacksonRepresentation<GameSessionWrapper> ( repr, GameSessionWrapper.class ) ;
        
        try {
            response = gameSession.getObject() ;
            if(!response.player1.name.equals(player.getName())){
                return mapPlayerWrapperToPlayer(response.player1);
            }
            else
            {
                if(response.player2 != null)
                {
                    return mapPlayerWrapperToPlayer(response.player2);
                }
                else
                {
                    return null;
                }
            }
        } catch (Exception e) {
            System.out.println("Exception : " + e);            
        }
        
        return new Player();   
    }
    
    public String registerMe(Player player)
    {
        GameSessionWrapper response = new GameSessionWrapper();
        ClientResource cr = new ClientResource(url);
        ObjectMapper mapper = new ObjectMapper();
        
        try {
            PlayerWrapper playerWrapperRequest = mapPlayerToPlayerWrapper(player);
            String requestString = mapper.writeValueAsString(playerWrapperRequest);
            Representation rep = cr.post(new JsonRepresentation(requestString));
            Representation responseEntity = cr.getResponseEntity();
            
            JacksonRepresentation<GameSessionWrapper> gameSession = new JacksonRepresentation<GameSessionWrapper> ( responseEntity, GameSessionWrapper.class ) ;
            response = gameSession.getObject() ;
            return response.gameId;
        } catch (Exception e) {
            System.out.println("Exception : " + e);
        }
        
        return "";
    }
    
    public void updateMe(Player player, String SessionId)
    {
        GameSessionWrapper response = new GameSessionWrapper();
        ClientResource cr = new ClientResource(url + "?gameId=" + SessionId);
        ObjectMapper mapper = new ObjectMapper();
        
        try {
            PlayerWrapper playerWrapperRequest = mapPlayerToPlayerWrapper(player);
            String requestString = mapper.writeValueAsString(playerWrapperRequest);
            Representation rep = cr.put(new JsonRepresentation(requestString));
            Representation responseEntity = cr.getResponseEntity();
            
        } catch (Exception e) {
            System.out.println("Exception : " + e);
        }
    }
    
    public void delete(String SessionId)
    {
        try {
            GameSessionWrapper response = new GameSessionWrapper();
    
            ClientResource cr = new ClientResource(url + "?gameId=" + SessionId);
            Representation repr = cr.delete();
        } catch (Exception e) {
            System.out.println("Exception : " + e);
        }
    }
    
    public Player mapPlayerWrapperToPlayer(PlayerWrapper playerWrapper)
    {
        Player returnPlayer = new Player();
        returnPlayer.setName(playerWrapper.name);
        returnPlayer.setIsFinished(playerWrapper.isfinished);
        returnPlayer.setLastUpdated(playerWrapper.lastUpdated);
        returnPlayer.setLastAction(playerWrapper.lastAction);
        
        //String file = playerWrapper.name.charAt(4) + ".png";
        switch(playerWrapper.myChar) {
             case "Char1" :
                returnPlayer.setChosenChar(new Char1()); 
                break;
             case "Char2" :
                returnPlayer.setChosenChar(new Char2()); 
                break;
             case "Char3" :
                returnPlayer.setChosenChar(new Char3()); 
                break;
             case "Char4" :
                returnPlayer.setChosenChar(new Char4()); 
                break;
             case "Char5" :
                returnPlayer.setChosenChar(new Char5()); 
                break;
             case "Char6" :
                returnPlayer.setChosenChar(new Char6()); 
                break;
             case "Char7" :
                returnPlayer.setChosenChar(new Char7()); 
                break;
             case "Char8" :
                returnPlayer.setChosenChar(new Char8()); 
                break;
             case "Char9" :
                returnPlayer.setChosenChar(new Char9()); 
                break;
             default :
                System.out.println("Invalid character");
        }
        
        return returnPlayer;
    }
    
    public PlayerWrapper mapPlayerToPlayerWrapper(Player player)
    {
        PlayerWrapper returnPlayer = new PlayerWrapper();
        returnPlayer.name = player.getName();
        
        returnPlayer.lastAction = player.getLastAction();
        returnPlayer.myChar = player.getChosenChar().getClass().getName();
        returnPlayer.isfinished = player.isFinished();
        returnPlayer.lastUpdated = player.getLastUpdated();
        
        return returnPlayer;
    }
}