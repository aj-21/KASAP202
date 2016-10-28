import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ZoomActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ZoomActor extends SelectableImagesActor
{
    //initial zoom scale is 1.5
    private double zoomScale = 1.5;
    public ZoomActor(String filename,double initScale)
    {
        setDeselectedImage(new GreenfootImage(filename));
        relativeScale(getDeselectedImage(), initScale);
        setSelectedImage(new GreenfootImage(filename));
        relativeScale(getSelectedImage(), initScale*zoomScale);
        
        setImage(getSelectedImage());
    }
    
    private void relativeScale(GreenfootImage image,double scale)
    {
        int width = image.getWidth();
        width *= scale;
        int height = image.getHeight();
        height *=scale;
        image.scale(width,height);
    }
    
    public void setZoomScale(double scale)
    {
        zoomScale = scale;
        relativeScale(getSelectedImage(),zoomScale);
    }
    
    public void resizeOnScale(double scale)
    {
        relativeScale(getDeselectedImage(), scale);
        relativeScale(getSelectedImage(), scale*zoomScale);
    }
}
