public interface PlayerOperations  
{
    public Player getPlayer(Player player, String SessionId);
    
    public String registerMe(Player player);

    public Player updateMe(Player player);
}
