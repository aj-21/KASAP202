import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/*import java.util.Random;
import java.util.List;
import java.util.ArrayList;
*/
/**
 * Write a description of class StringButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StringButton extends ZoomActor
{
    String fileName;
    String label;
    
    Color labelColor = Color.BLUE;
    
    public StringButton(String label, String fileName)
    {
        super(fileName,1);
        this.fileName = fileName;
        setLabel(label);
        //Random rand = new Random();
        //labelColor = new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat());
        
        drawLabel();
    }
    
    public StringButton(String label)
    {
        this(label,"buttonEnabled.png");
    }
    
    protected void setLabel(String label)
    {
        this.label = label;
        drawLabel();
    }
    
    public String getLabel()
    {
        return label;
    }
    
    private void drawLabel()
    {
        drawLabelTo(getDeselectedImage());
        drawLabelTo(getSelectedImage());
    }
    
    private void drawLabelTo(GreenfootImage image)
    {
        
        int w = image.getWidth();
        int h = image.getHeight(); 
        GreenfootImage labelImage = new GreenfootImage(label,h/10*5,labelColor,new Color(0,0,0,0));
        image.drawImage(labelImage,(w - labelImage.getWidth())/2 ,h/20*6);
    }
    
}
