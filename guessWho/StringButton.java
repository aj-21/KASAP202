import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
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
    Color labelColor = Color.RED;
    
    public StringButton(String label, String fileName)
    {
        super(fileName,0.5);
        this.fileName = fileName;
        setLabel(label);
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
        GreenfootImage labelImage = new GreenfootImage(label,h/10*6,labelColor,new Color(0,0,0,0));
        image.drawImage(labelImage,(w - labelImage.getWidth())/2 ,h/10*2);
    }
    
}
