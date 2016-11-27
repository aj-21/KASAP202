/**
 * Write a description of class ConcreteHandlerQuit here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ConcreteHandlerQuit implements IntroHandler {
    private IntroHandler successor = null;
    public boolean handleIntroRequest( int x, int y ) {
        System.out.println( "Inside Quit");
        if ( x>1421 && x<1518 && y>807 &y<851)
        {
            System.out.println("Quit");
        }
        else
        {
            if ( successor != null )
                successor.handleIntroRequest(x,y);
        }
        return false;
    }

    public void setIntroSuccessor(IntroHandler next) {
        this.successor = next ;
    }
}
