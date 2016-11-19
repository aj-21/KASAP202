import greenfoot.*;
/**
 * Write a description of class PressHandlerState here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PressHandlerState extends GameStateDecorator implements PressHandler
{
    private PressHandler successor;
    public PressHandlerState(GameState gameState)
    {
        super(gameState);
    }
    
    @Override
    public void setSuccessor(PressHandler successor)
    {
        if(this.successor != null)
            successor.setSuccessor(this.successor);

        this.successor = successor;
    }
    
    @Override
    public void pressHandle(int x, int y)
    {
        if(successor != null)
            successor.pressHandle(x,y);
    }
    
    @Override
    public void stateRun()
    {
        
        if(Greenfoot.mousePressed(null))
        {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            int x = mouse.getX();
            int y = mouse.getY();
            pressHandle(x,y);
        }
        super.stateRun();
    }
    
}
