public interface PlayerOperations  
{
    public Player getYou(Player player, String SessionId);
    
    public String registerMe(Player player);

    public void updateMe(Player player, String SessionId);
    
    public void delete(String SessionId);
}