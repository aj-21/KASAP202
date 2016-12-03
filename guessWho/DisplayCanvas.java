import greenfoot.*;   // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Set;

/**
 * DisplayCanvas is an Actor which additionally use children of DisplayBox interface to display. 
 * It support most of the function in DisplayBox interface for easy controlling except the display method.
 * the display method provide by this class doesn't take in any parameter.
 * display function should be the last function to be called after all set up. To reflect new change after setting up, a recallation for display function is needed
 * In addition, it provide setBackgound and setMargin for controlling background and display area
 * 
 * @SPAAK
 * @version 1
 */
public class DisplayCanvas<T extends Actor> extends Actor
{
    //use a display Box as underlying display method
    DisplayBox disBox;
    
    //private field for Margin setting
    double left, right, top, bottom;
    int leftMar,topMar;
    
    /**
     * DisplayCanvas provide 3 constructors, one taking no parameter, one taking one actor and one take in a set of actors
     */  
    public DisplayCanvas()
    {
        this.disBox = new SimpleDisplayBox();
    }
    
    public DisplayCanvas(Set<T> actors)
    {
        this();
        disBox.setActors(actors);
    }
    
    public DisplayCanvas(T actor)
    {
        this();
        addActor(actor);
    }
    
    /**
     * display should be called after each setup to refect the newest set up
     * DisplayCanvas only works if it is added in a world
     */
    public void display()
    {
        try
        {
            int disX = getX() - getImage().getWidth()/2;
            int disY = getY() - getImage().getHeight()/2;
            disBox.display(getWorld(),disX + leftMar,disY + topMar);   
        }
        catch (Exception e)
        {
            System.out.println(e);
            System.out.println(this.getClass().getName() + " need to be added into a world before it can display");
        }
    }
  
    /**
     * setBackground takes in a path to an image
     */
    public DisplayCanvas setBackground(String filename)
    {
        setBackground(new GreenfootImage(filename));
        return this;
    }
    
    /**
     * setBackground takes in an greenfootImage
     */
    public DisplayCanvas setBackground(GreenfootImage image)
    {
        setImage(image);
        setMargin(left,right,top,bottom);
        return this;
    }
    
    /**
     * setMargin with double parameters serve as percentage (not pixel)
     * margins define area which won't be counted as display area. 
     */
    public DisplayCanvas setMargin(double left, double right, double top, double bottom)
    {
        this.left = left; this.right = right;this.top=top;this.bottom=bottom;
        int w = getImage().getWidth();
        int h = getImage().getHeight();
        this.leftMar = (int) (w * left/100);
        int rightMar = (int) (w * right/100);
        this. topMar = (int) (h * top/100);
        int bottomMar = (int) (h * bottom / 100 );
        
        disBox.scale(w - leftMar - rightMar, h - topMar - bottomMar);
        return this;
    }    
    
    /**
     * Some setting function supporting controlling displaybox - read DisplayBox interface for more information
     */
    public DisplayCanvas setColRow(int col, int row)
    {
        disBox.setColRow(col,row);
        return this;
    }
    
    /**
     * get all actor this canvas is displaying
     */
    public Set<T> getAll()
    {
        return disBox.getActors();
    }
    
    /**
     * add all actors to this canvas for displaying
     */
    public DisplayCanvas addAll(Set<T> actors)
    {
        disBox.addAll(actors);
        return this;
    }
    
    /**
     * add an additional actor to this canvas for displaying
     */
    public DisplayCanvas addActor(T actor)
    {
        disBox.addActor(actor);
        return this;
    }
    
    /**
     * remove all actors in this canvas
     */
    public DisplayCanvas clearAll()
    {
        disBox.clearAll();
        return this;
    }
    
    /**
     * set a new set of actors for displaying, old set will be ignore
     */
    public DisplayCanvas setActors(Set<T> actors)
    {
        disBox.setActors(actors);
        return this;
    }
    
    

}
