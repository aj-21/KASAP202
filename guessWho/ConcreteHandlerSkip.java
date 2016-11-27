import greenfoot.*; 
/**
 * Write a description of class ConcreteHandlerSkip here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ConcreteHandlerSkip implements IntroHandler {

    private IntroHandler successor = null;

    public boolean handleIntroRequest( int x, int y) {
        System.out.println( "Inside Skip Introduction");
        if (x>23 && x<257 && y>808 &y<850)
        {
            //System.out.println("Skip");
            Greenfoot.setWorld(new ChooseCharacterScreen());
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
