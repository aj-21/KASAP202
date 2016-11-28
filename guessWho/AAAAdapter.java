/**
 * Write a description of class AAAAdapter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AAAAdapter  
{
    static Player you;
    
    public AAAAdapter()
    {
        
    }
    
    public static String register(Player me)
    {
        return "123456";
    }
    
    public static Player getOpponent()
    {
        if (you == null){
            you = new Player();
            you.setName("you");
            you.setChosenChar(new Char5());
        }
        return you;
    }
    
    public static void deRegister(String sessionID)
    {
        return;
    }
}
