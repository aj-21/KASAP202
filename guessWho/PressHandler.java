/**
 * Write a description of class PressHandler here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface PressHandler  
{
    public void pressHandle(int x, int y);
    public void setSuccessor(PressHandler successor);
}
