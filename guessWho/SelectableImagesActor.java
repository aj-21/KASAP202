import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SelectableImagesActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SelectableImagesActor extends SimpleSelectableActor
{
    GreenfootImage deselectedImage = getImage();
    GreenfootImage selectedImage = getImage();
    
    
    @Override
    protected void responseToPress()
    {
        if(this.isSelected())
            setImage(selectedImage);
        else
            setImage(deselectedImage);
    }
    
    public void setSelectedImage(GreenfootImage image)
    {
        selectedImage = image;
    }
    
    public GreenfootImage getSelectedImage()
    {
        return selectedImage;
    }
    
    public void setDeselectedImage(GreenfootImage image)
    {
        deselectedImage = image;
    }
    
    public GreenfootImage getDeselectedImage()
    {
        return deselectedImage;
    }
}
