import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SelectableImagesActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SelectableImagesActor extends SimpleSelectableActor
{
    GreenfootImage deselectedImage;
    GreenfootImage selectedImage;
    
    public void SelectableImagesActor()
    {
        setDeselectedImage(getImage());
        setSelectedImage(getImage());
    }
    
    
    public void setSelectedImage(GreenfootImage image)
    {
        selectedImage = image;
        //Actor should have the latest image if this is a new image
        if(isSelected())
            setImage(selectedImage);
    }
    
    public GreenfootImage getSelectedImage()
    {
        return selectedImage;

    }
    
    public void setDeselectedImage(GreenfootImage image)
    {
        deselectedImage = image;
        //Actor should have the latest image if this is a new image
        if (!isSelected())
            setImage(deselectedImage);
    }
    
    public GreenfootImage getDeselectedImage()
    {
        return deselectedImage;
    }
    
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
