import greenfoot.*;
import java.util.Set;

/**
 * SimpleDisplayBox extends AbstractDisplayBox
 * It stores a set of Actors and display them to the world passed in when function display is called.
 * display function takes in a world, int x and int y as the top left corner to display stored Actors using the world. 
 * It used predefined setting from number of columns and rows, and allowed with and height to evenly compute precise coordiate for each Actors
 * The maximum number of Actors it can display less than or equal number of rows multiplied by number of columns, the two attributes that can be manually set
 * It is mandatory to recall display function everytime it is setup with new data/setting to reflect newst changes
 * 
 * @SPAAK
 * @version 1
 */

public class SimpleDisplayBox<T extends Actor> extends AbstractDisplayBox<T>
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
        this.world = world;
        this.x = x;
        this.y = y;
        int index = 0;
        for(T actor: actors)
        {
            displayActor(actor,index);  
            if(index++ >  numCol*numRow)
                return;
        }
    }
    
    /**
     * private display function display one actor at a time according to the index of it.
     * if actor is already in this world, it will be relocated otherwise adding actor to new world
     */
    private void displayActor(T actor,int index)
    {         
        int objectX = this.x + (index % numCol) * (width/numCol) + (width/numCol)/2;
        int objectY = this.y + (index / numCol) * (height/numRow) + (height/numRow)/2;        
        if(actor.getWorld() == this.world)
        {
            //actor.setLocation(objectX,objectY);
            //return;
            world.removeObject(actor);
        }
        this.world.addObject(actor,objectX,objectY);     
    }
}
