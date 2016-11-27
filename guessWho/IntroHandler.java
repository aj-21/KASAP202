/**
 * Write a description of class Handler here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface IntroHandler {

	boolean handleIntroRequest(int x, int y );
	void setIntroSuccessor(IntroHandler next); 

}
