/**
 * Write a description of class Handler here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface Handler {

	void handleRequest(int x, int y );
	void setSuccessor(Handler next);

}
