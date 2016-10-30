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
    int disW, disH; 
    int disX, disY;
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
    
    public void scale(int disW, int disH)
    {
        this.disW = disW;
        this.disH = disH;
    }
    
    public void display(int disX, int disY)
    {
        this.disX = disX;
        this.disY = disY;
        int index = 0;
        for(SimpleSelectableActor obj: objects)
        {
            displayObject(obj,index);  
            index++;
        }
    }
    
    private void displayObject(SimpleSelectableActor object,int index)
    {        
        int x = disX + (index % numCol) * (disW/numCol) + (disW/numCol)/2;
        int y = disY + (index / numCol) * (disH/numRow) + (disH/numRow)/2;
        world.addObject(object,x,y);       
    }
    
    
}
