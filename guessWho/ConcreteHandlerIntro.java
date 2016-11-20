/**
 * Write a description of class ConcreteHandlerIntro here.
 * 
 * @SPAAK
 * @latest version
 */
public class ConcreteHandlerIntro implements Handler {

    private Handler successor = null;

	public void handleRequest( int x, int y) {
        System.out.println( "Inside Introduction Class");
        if (x>713 && x<800 && y>441 &y<466 )
        {
            System.out.println( "Intro");
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
