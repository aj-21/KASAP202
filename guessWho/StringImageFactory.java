import greenfoot.GreenfootImage;
import java.awt.Color;
/**
 * able generate greenfoot image from a string based on setup
 * setup will include text color, background color.
 * text size and text itself will be passed into createImage methods to get back the image
 * 
 * @author SPAAK
 * @version 1
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
}
