import greenfoot.*;
/**
 * A decorator of GameState with ability to handle press event.
 * support set successor, which is another PressHandler
 * 
 * @author SPAAK
 * @version 1
 */
public class PressHandlerState extends GameStateDecorator implements PressHandler
{
    private PressHandler successor;
    
    /**
     * constructor takes in a gameState to decorate
     */
    public PressHandlerState(GameState gameState)
    {
        super(gameState);
    }
    
    /**
     * insert the new successor at the front in the chain
     */
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
