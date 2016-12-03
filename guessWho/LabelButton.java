import greenfoot.GreenfootImage;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
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
    
    /**
     * construction will take in label name and path to an image file
     */
    public LabelButton(String label, String fileName)
    {
        super(fileName,1);
        this.fileName = fileName;
        setLabel(label);
    }
    
    //helper setting a label for button
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
    
    //helper function to draw label on to images
    private void drawLabel()
    {
        drawLabelTo(getDeselectedImage());
        drawLabelTo(getSelectedImage());
    }
    
    //helper function to draw String image with transparent background on current images 
    private GreenfootImage drawLabelTo(GreenfootImage image)
    {
        int w = image.getWidth();
        int h = image.getHeight(); 
        StringImageFactory strImgFac = new StringImageFactory();
        strImgFac.setTextColor(labelColor);
        GreenfootImage labelImage = strImgFac.createImage(label,h/10*5);
        image.drawImage(labelImage,(w - labelImage.getWidth())/2 ,h/20*6);
        return image;
    }
    
}
