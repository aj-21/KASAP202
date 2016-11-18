/**
 * Write a description of class IGameState here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class IGameState implements GameState, PressHandler
{
   public abstract void stateRun();
   
   public abstract void pressHandle(int x, int Y);
}
