import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import java.util.Set;
import java.util.HashSet;

/**
 * Write a description of class DisplayBox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DisplayBox extends Actor
{
    protected List<Actor> objects= new ArrayList<Actor>();
    int numCol = 5;
    int numRow = 2;
    int leftMar, rightMar, topMar, bottomMar;
    int disX,disY,disW,disH;
    boolean inWorld = false;
    /**
     * Act - do whatever the CharacterCanvas wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public DisplayBox()
    {
    }
    
    public DisplayBox(int width, int height)
    {
        getImage().scale(width,height); 
        
    }
    
    public DisplayBox(int width, int height,int col, int row)
    {
        this(width,height);
        this.numCol = col;
        this.numRow = row;
    }
    
    public void display()
    {        
                
        disW = (getImage().getWidth() - leftMar - rightMar) ;
        disH = (getImage().getHeight() - topMar - bottomMar) ;
        disX = getX() - getImage().getWidth()/2;
        disY = getY() - getImage().getHeight()/2;
        
        //fixxxxxxxxxxxxxxxxxxxxxxxx
        for(Actor obj: objects)
            displayObject(obj); //***************************
    }
     
    
    //add all Characters in a collection into box
    public void addAllObjects(Collection<Actor> objects)
    {
        for(Actor obj : objects)
            addObject(obj); 
    }
    
    public List<Actor> getAllObjects()
    {
        return objects;
    }
    
    //add a new Character
    public void addObject(Actor object)
    {
        if (objects.size() >= numCol*numRow)
            return;
        
        System.out.println("adding " + object.getClass().getName());
        objects.add(object);
        if(inWorld)
            displayObject(object);
    }
    
    public void removeObject(Actor object)
    {
        if(object == null)
            return;
        
        objects.remove(object);
        //if(inWorld)
        getWorld().removeObject(object);
        
    }
    
    //set 4 margin with percentage.
    //Example, 1 = 1% margin of the total box
    //the larger the margin, the smaller the display area for objects, and the gaps between objects get tinier
    public void setMargin(double left, double right, double top, double bottom)
    {
        int w = getImage().getWidth();
        int h = getImage().getHeight();
        this.leftMar = (int) (w * left/100);
        this.rightMar = (int) (w * right/100);
        this.topMar = (int) (h * top/100);
        this.bottomMar = (int) (h * bottom / 100 );
    }

    private void displayObject(Actor object)
    {
        int index = objects.indexOf(object);          
        int x = (index % numCol) * (disW/numCol)+ disX  + (disW/numCol)/2 + leftMar;
        int y = (index / numCol) * (disH/numRow) + disY + (disH/numRow)/2 + topMar;
        getWorld().addObject(object,x,y);        
    }
}