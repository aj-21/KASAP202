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
    
    //a path to picture is requires so that a zoomactor can construct with 2 images
    public ZoomActor(String filename,double initScale)
    {
        setDeselectedImage(new GreenfootImage(filename));
        relativeScale(getDeselectedImage(), initScale);
        setSelectedImage(new GreenfootImage(filename));
        relativeScale(getSelectedImage(), initScale*zoomScale);
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
        relativeScale(getSelectedImage(),scale/zoomScale);
        zoomScale=scale;
    }
    
    public void resizeOnScale(double scale)
    {
        relativeScale(getDeselectedImage(), scale);
        relativeScale(getSelectedImage(), scale);
    }
}
