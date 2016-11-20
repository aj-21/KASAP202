import greenfoot.*; 
/**
 * Write a description of class ConcreteHandlerSkip here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ConcreteHandlerSkip implements Handler {

    private Handler successor = null;

    public void handleRequest( int x, int y) {
        System.out.println( "Inside Skip Introduction");
        if (x>1202 && x<1394 && y>740 &y<768)
        {
            System.out.println("Skip");
            Greenfoot.setWorld(new ChooseCharacterScreen());
        }
        else
        {
            if ( successor != null )
                successor.handleRequest(x,y);
        }

    }

    public void setSuccessor(Handler next) {
        this.successor = next ;
    }


}
