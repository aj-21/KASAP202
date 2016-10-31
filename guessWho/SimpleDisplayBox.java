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
public class SimpleDisplayBox  
{
    Set<SimpleSelectableActor> objects;
    World world;
    int numCol, numRow;
    int width, height; 
    int x, y;
    /**
     * Constructor for objects of class SimpleDisplayBox
     */
    public SimpleDisplayBox(World world)
    {
        numCol = 5;
        numRow = 2;
        this.world = world;
        objects = new HashSet<SimpleSelectableActor>();
        
    }

    public Set<SimpleSelectableActor> getObjects()
    {
        return objects;
    }
    
    public void setObjects(Set<SimpleSelectableActor> objects)
    {
        this.objects = objects;
    }
    
    public void scale(int width, int height)
    {
        this.width = width;
        this.height = height;
    }
    
    public void display(int x, int y)
    {
        this.x = x;
        this.y = y;
        int index = 0;
        for(SimpleSelectableActor obj: objects)
        {
            displayObject(obj,index);  
            index++;
        }
    }
    
    private void displayObject(SimpleSelectableActor object,int index)
    {        
        int objectX = x + (index % numCol) * (width/numCol) + (width/numCol)/2;
        int objectY = y + (index / numCol) * (height/numRow) + (height/numRow)/2;
        if(object.isInWorld() && object.getWorld() == this.world)
        {
            System.out.println(object.getClass().getName() + " is in World at: [" + object.getX() + ":" + object.getY() +"]");
            object.setLocation(x,y);
            return;
        }
        world.addObject(object,objectX,objectY);     
    }
    
    public void setColRow(int col, int row)
    {
        this.numCol = col;
        this.numRow = row;
    }
}
