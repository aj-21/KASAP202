import greenfoot.*;
/**
 * Write a description of class ConcreteHandlerIntro here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ConcreteHandlerIntro implements IntroHandler {

    private IntroHandler successor = null;

	public boolean handleIntroRequest( int x, int y) {
        //System.out.println( "Inside Introduction Class");
        if (x>726 && x<853 && y>809 &y<851 )
        {
            //System.out.println( "Intro");
            return true;
        }
        else
        {
            if ( successor != null )
                successor.handleIntroRequest(x,y);
            return false;
        }

	}

	public void setIntroSuccessor(IntroHandler next) {
        this.successor = next ;
	}

}
