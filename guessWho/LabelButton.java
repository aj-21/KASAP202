import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * LabelButton is a ZoomActor which able to display its label on top of its representational images
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LabelButton extends ZoomActor
{
    String fileName;
    String label;
    
    Color labelColor = Color.BLUE;
    
    //construction take in label name and path to image file
    public LabelButton(String label, String fileName)
    {
        super(fileName,1);
        this.fileName = fileName;
        setLabel(label);
    }
    
    //set a label for button
    protected void setLabel(String label)
    {
        this.label = label;
        drawLabel();
    }
    
    //get the current label name
    public String getLabel()
    {
        return label;
    }
    
    //two private hepler methods to draw label
    private void drawLabel()
    {
        drawLabelTo(getDeselectedImage());
        drawLabelTo(getSelectedImage());
    }
    
    //draw String image with transparent background on current images 
    private GreenfootImage drawLabelTo(GreenfootImage image)
    {
        int w = image.getWidth();
        int h = image.getHeight(); 
        GreenfootImage labelImage = new GreenfootImage(label,h/10*5,labelColor,new Color(0,0,0,0));
        image.drawImage(labelImage,(w - labelImage.getWidth())/2 ,h/20*6);
        return image;
    }
    
}
