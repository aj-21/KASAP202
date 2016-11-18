/**
 * Write a description of class IGameState here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class IGameState implements GameState, PressHandler,SecondObserver
{
   public abstract void stateRun();
   public abstract void pressHandle(int x, int Y);
   public abstract void setSuccessor(PressHandler successor);
   public abstract void secondUpdate();
   
}
