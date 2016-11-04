import greenfoot.*;
import java.util.Set;
import java.util.List;
import java.util.HashSet;

/**
 * SimpleDisplayBox extends AbstractDisplayBox
 * It stores a set of Actors extending SimpleSelectableActor and display them to the world passed in when function display is called.
 * display function takes in a world, int x and int y as the top left corner to display stored Actors using the world. 
 * It used predefined setting from number of columns and rows, and allowed with and height to evenly compute precise coordiate for each Actors
 * The maximum number of Actors it can display less than or equal number of rows multiplied by number of columns, the two attributes that can be manually set
 * 
 * @SPAAK
 * @version 1
 */

public class SimpleDisplayBox<T extends SimpleSelectableActor> extends AbstractDisplayBox<T>
{
        
    /**
     * Constructor for objects of class SimpleDisplayBox taking no argument providing default setting for all fields
     */
    public SimpleDisplayBox()
    {
        super();
    }
    
    /**
     * this function need to be call for the box to display its contained set of actors
     */
    @Override
    public void display(World world,int x, int y)
    {
        this.x = x;
        this.y = y;
        int index = 0;
        for(T actor: actors)
        {
            displayActor(world,actor,index);  
            if(index++ >  numCol*numRow)
                return;
        }
    }
    
    /**
     * private display function display one actor at a time according to the index of it.
     */
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
}
