import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * SelectableImagesActor extends SimpleSelectableActor.
 * it is able to represent itself with two images, one for selected and one for deselected
 * 
 * 
 * @SPAAK
 * @version 1
 */
 
public class SelectableImagesActor extends SimpleSelectableActor
{
    //images for selected and deselected
    GreenfootImage deselectedImage;
    GreenfootImage selectedImage;
    
    /**
     * a constructor creates the same copy of image for both selected- and deselected-Images
     */
    public void SelectableImagesActor()
    {
        setDeselectedImage(getImage());
        setSelectedImage(new GreenfootImage(getImage()));
    }
    
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
