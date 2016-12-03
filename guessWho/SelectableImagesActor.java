import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * an abstract extends SimpleSelectableActor with ablility
 * to represent itself with two different images when it is selected/deselected
 * this class also provides some helper function for extension such as,
 * setSelectedImage and setDeselectedImage, which take in as inputs Greenfoot images
 * retriveing these images via method call getSelectedImage and getDeselectedImage
 * 
 * 
 * @SPAAK
 * @version 1
 */
 
public abstract class SelectableImagesActor extends SimpleSelectableActor
{
    //images for selected and deselected
    GreenfootImage deselectedImage;
    GreenfootImage selectedImage;

    //set and update selected-Image
    public void setSelectedImage(GreenfootImage image)
    {
        selectedImage = image;
        //update
        if(isSelected())
            setImage(selectedImage);
    }
    
    //set and update deselected-Image
    public void setDeselectedImage(GreenfootImage image)
    {
        deselectedImage = image;
        //update
        if (!isSelected())
            setImage(deselectedImage);
    }
    
    //retrive selectedImage
    public GreenfootImage getSelectedImage()
    {
        return selectedImage;
    } 
    
    //retrive deselectedImage
    public GreenfootImage getDeselectedImage()
    {
        return deselectedImage;
    }
    
    /**
     * Override select and deselect methods to witch image according to selection
     */    
    @Override
    public void select()
    {
        super.select();
        setImage(getSelectedImage());
    }
    
    @Override
    public void deselect()
    {
        super.deselect();
        setImage(getDeselectedImage());
    }
}
