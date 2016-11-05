import greenfoot.*;
import java.util.Set;
import java.util.HashSet;
/**
 * AbstractDisplayBox is an abstract class implements DisplayBox interface.
 * It stores a set of Actors extending SimpleSelectableActor and display them to the world passed in when function display is called.
 * display function takes in a world, int x and int y as the top left corner to display stored Actors using the world. 
 * It also contain other setting useful for displaying. It is required that children of this class override display function to display actors on their own logic
 * 
 * @SPAAK
 * @version 1
 */

public abstract class AbstractDisplayBox<T extends Actor> implements DisplayBox<T>
{
    // set of actors to be display
    Set<T> actors;
    //numCol = number of actors per row, num Row = number of row.
    int numCol, numRow;
    //width and height of this box
    int width, height; 
    //the x and y are the top left coordinates of 
    int x, y;
    
    /**
     * Constructor for objects of class SimpleDisplayBox taking no argument providing default setting for all fields
     */
    public AbstractDisplayBox()
    {
        numCol = 5;
        numRow = 2;
        width = 100;
        height = 100;
        actors = new HashSet<T>();
        
    }
    
    /**
     * this function need to be call for the box to display its contained set of actors
     */
    public abstract void display(World world,int x, int y);
    
    /**
     * support one get and set function to get current set of actors or set to display a new set of actors
     */
    public void setActors(Set<T> actors )
    {
        this.actors = actors;
    }
    
    /**
     * support retriving current set of actors via getActors() and
     */
    public Set<T> getActors()
    {
        return actors;
    }
    
    /**
     * addAll and addActor are to add a set of distinct actors or just one actor into current set, respectively
     */
    public void addAll(Set<T> actors)
    {
        this.actors.addAll(actors);
    }
    
    public void clearAll()
    {
        actors.clear();
    }
    
    public void addActor(T actor)
    {
        this.actors.add(actor);
    }
        
    /** 
     * A setup function for controlling how actors are display relative to another.
     * Resize the size this box, the bigger the size the farther apart actors will be display
     */
    public void scale(int width, int height)
    {
        this.width = width;
        this.height = height;
    }
    
    /** 
     * A setup function for controlling how actors are display relative to another.
     * set maximum number of Col and Row to display. multiplication of these two variable is the maximum number of actors it will display
     */
    public void setColRow(int col, int row)
    {
        this.numCol = col;
        this.numRow = row;
    }
}
