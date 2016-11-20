/**
 * Write a description of class ConcreteHandlerQuit here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ConcreteHandlerQuit implements Handler {
    private Handler successor = null;
    public void handleRequest( int x, int y ) {
        System.out.println( "Inside Quit");
        if ( x>169 && x<278 && y>749 &y<773)
        {
            System.out.println("Quit");
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
