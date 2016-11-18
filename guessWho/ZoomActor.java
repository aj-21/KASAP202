import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * ZoomActor extends SimpleImagesActor
 * ZoomActor display itself with the same pictures but in different sizes. By default selected image size is 50% larger than deselected image size
 * ZoomActor supports setting method for zooming Scale image when deselected relative to selected image.
 * ZoomActor also provide a method to resize two imaging representation with a scale (ratio/percentage) rather than pixels
 * 
 * @author SPAAK 
 * @version 1
 */
public class ZoomActor<T extends ZoomActor> extends SelectableImagesActor
{
    //by default zoom scale when selected is 1.5 
    private double zoomScale = 1.5;
    
    /**
     * Constructor takes in a path to image with image name and initial Scale (up or down) for both images
     */
    public ZoomActor(String filename,double initScale)
    {
        setDeselectedImage(new GreenfootImage(filename));
        relativeScale(getDeselectedImage(), initScale);
        setSelectedImage(new GreenfootImage(filename));
        relativeScale(getSelectedImage(), initScale*zoomScale);
    }
    
    //a helper method to rescale
    private void relativeScale(GreenfootImage image,double scale)
    {
        int width = image.getWidth();
        width *= scale;
        int height = image.getHeight();
        height *=scale;
        image.scale(width,height);
    }
    
    /**
     * set zoom scale when this actor is selected. it can be biger or smaller than deselected image, 
     * a scale of 1 means no different in sizes between selected and deselected images.
     * a scale greater than 1 means selected image will be larger than not-selected image
     * a scale less than 1 means selected image will be smaller than not-selected image
     */
    public void setZoomScale(double scale)
    {        
        relativeScale(getSelectedImage(),scale/zoomScale);
        zoomScale=scale;
    }
    
    //resize both images bigger or smaller based on input scale
    public void resizeOnScale(double scale)
    {
        relativeScale(getDeselectedImage(), scale);
        relativeScale(getSelectedImage(), scale);
    }
}
