import greenfoot.GreenfootImage; 

/**
 * extension of SimpleImagesActor, ZoomActor should display itself with the same pictures but in different sizes. 
 * By default selected image size is 50% larger than deselected image size (sacle is 1.5)
 * ZoomScale can be set via method setZoomScale
 * ZoomActor also support resize on scale to resize both images down or up relatively
 * 
 * @author SPAAK 
 * @version 1
 */
public class ZoomActor<T extends ZoomActor> extends SelectableImagesActor
{
    //by default zoom scale when selected is 1.5 
    private double zoomScale = 1.5;
    
    /**
     * Constructor takes in a path to an image name and initial scale (up or down) for both images
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
    {   if(scale >0)
        {
            relativeScale(getSelectedImage(),scale/zoomScale);
            zoomScale=scale;
        }
    }
    
    /**
     * resize both images bigger or smaller based on input scale
     */
    public void resizeOnScale(double scale)
    {
        relativeScale(getDeselectedImage(), scale);
        relativeScale(getSelectedImage(), scale);
    }
}
