import greenfoot.*;
import java.util.Set;
import java.util.HashSet;
/**
 * DisplayBox is an interface supporting the following features:
 *   1. display stored actor in to the world passed in to display function, together with x, and y as the top left coordinates of this Box
 *   2. set current stored set of actors to a new set, get current set of actors
 *   3. add one actor to current list, or add a set of actors into current list
 *   4. setting witdth and height of the box (scale function), and set column and rows to display (setColRow row)
 * 
 * @SPAAK
 * @version 1
 */

public interface DisplayBox<T extends Actor>  
{   
    public void display(World world,int x, int y);

    public void setActors(Set<T> actors );
    
    public Set<T> getActors();

    public void addAll(Set<T> actors);
    
    public void addActor(T actor);

    public void scale(int width, int height);

    public void setColRow(int col, int row);
}
