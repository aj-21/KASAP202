import greenfoot.*;
import java.util.Set;
import java.util.List;
import java.util.HashSet;
/**
 * Write a description of class SimpleDisplayBox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SimpleDisplayBox<T extends SimpleSelectableActor>  
{
    Set<T> actors;

    int numCol, numRow;
    int width, height; 
    int x, y;
    /**
     * Constructor for objects of class SimpleDisplayBox
     */
    public SimpleDisplayBox()
    {
        numCol = 5;
        numRow = 2;

        actors = new HashSet<T>();
        
    }
    
    public void setContainer(SimpleContainer container)
    {
        this.actors = container.getAll();
    }
    
    public void setActors(Set<T> actors )
    {
        this.actors = actors;
    }
    
    public Set<T> getActors()
    {
        return actors;
    }
    
    public void scale(int width, int height)
    {
        this.width = width;
        this.height = height;
    }
    
    public void display(World world,int x, int y)
    {
        this.x = x;
        this.y = y;
        int index = 0;
        for(T actor: actors)
        {
            displayActor(world,actor,index);  
            index++;
        }
    }
    
    private void displayActor(World world,T actor,int index)
    {        
        int objectX = x + (index % numCol) * (width/numCol) + (width/numCol)/2;
        int objectY = y + (index / numCol) * (height/numRow) + (height/numRow)/2;
        if(actor.isInWorld() && actor.getWorld() == world)
        {
            actor.setLocation(x,y);
            return;
        }
        world.addObject(actor,objectX,objectY);     
    }
    
    public void setColRow(int col, int row)
    {
        this.numCol = col;
        this.numRow = row;
    }
    
    public SimpleContainer getContainer()
    {
        return new SimpleContainer(actors);
    }
}
