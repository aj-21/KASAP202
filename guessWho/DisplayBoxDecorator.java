import greenfoot.*;
import java.util.Set;
/**
 * Write a description of class DisplayBoxDecorator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DisplayBoxDecorator<T extends Actor> implements DisplayBox<T>
{
    DisplayBox disBox;
    
    public DisplayBoxDecorator(DisplayBox disBox)
    {
        this.disBox = disBox;
    }
    
    public void display()
    {
        disBox.display();
    }
    
    public void display(World world,int x, int y)
    {
        disBox.display(world,x,y);
    }

    public void setActors(Set<T> actors )
    {
        disBox.setActors(actors);
    }
    
    public Set<T> getActors()
    {
        return disBox.getActors();
    }

    public void addAll(Set<T> actors)
    {
        disBox.addAll(actors);
    }
    
    public void clearAll()
    {
        disBox.clearAll();
    }
    
    public void addActor(T actor)
    {
        disBox.addActor(actor);
    }

    public void scale(int width, int height)
    {
        disBox.scale(width,height);
    }

    public void setColRow(int col, int row)
    {
        disBox.setColRow(col,row);
    }
    
    public int getX()
    {
        return disBox.getX();
    }
    
    public int getY()
    {
        return disBox.getY();
    }
    
    public int getWidth()
    {
        return disBox.getWidth();
    }
    
    public int getHeight()
    {
        return disBox.getHeight();
    }
}
