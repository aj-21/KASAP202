/**
 * an interface to handle if press event happen -> using chain of responsibility pattern
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface PressHandler  
{
    public void pressHandle(int x, int y);
    public void setSuccessor(PressHandler successor);
}
