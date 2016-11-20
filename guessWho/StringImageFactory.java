import greenfoot.*;
import java.awt.Color;
/**
 * Write a description of class StringImage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StringImageFactory  
{
    // instance variables - replace the example below with your own
    Color textColor;
    Color backgroundColor;
    int textSize;

    /**
     * Constructor for objects of class StringImage
     */
    public StringImageFactory()
    {
        textColor = Color.BLACK;
        backgroundColor = new Color(0,0,0,0);
    }

    public GreenfootImage createImage(String text,int size)
    {
        GreenfootImage contentImage = new GreenfootImage(text,size,textColor,backgroundColor);
        
        return contentImage;
    }
    
    public void setTextColor(Color color)
    {
        this.textColor = color;
    }
    
    public void setBackgroundColor(Color color)
    {
        this.backgroundColor = color;
    }
    
    public void setSize(int size)
    {
        this.textSize = size;
    }
}
