import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Characters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Character extends Actor
{   
    //Character Properties
    String[] hairColorOptions = {"None", "Red", "Gray", "Black", "White", "Brown"};
    String[] hairLengthOptions = {"Bald", "Semi-Bald", "Short", "Long", "Undefined"};
    private boolean hasHat = false;
    private boolean hasEarings = false;
    private boolean hasCoat = false;
    private boolean hasSpectacles = false;
    
    
    public boolean getHasHat(int x){
        if(x == 0)
            hasHat = false;
        if(x == 1)
            hasHat = true;
        
        return hasHat;
    }
    
    public boolean getHasEarings(int x){
        if(x == 0)
            hasEarings = false;
        if(x == 1)
            hasEarings = true;
        
        return hasEarings;
    }
    
    public boolean getHasCoat(int x){
        if(x == 0)
            hasCoat = false;
        if(x == 1)
            hasCoat = true;
        
        return hasCoat;
    }
    
    public boolean getHasSpectacles(int x){
        if(x == 0)
            hasSpectacles = false;
        if(x == 1)
            hasSpectacles = true;
        
        return hasSpectacles;
    }
    
    
    int oriWidth = getImage().getWidth();
    int oriHeight =getImage().getHeight();
     
    boolean selected;
    boolean check = true;
    double scale = 1.5;
    
    
    double w = oriWidth*scale;
    double h = oriHeight*scale;
    
    double sizeW = 141;// = getImage().getWidth();
            double sizeH = 192; //= getImage().getHeight();
          
    
    public Character()
    {
        GreenfootImage image = getImage();
        image.scale(141,192);
    }
    
    /**
     * Act - do whatever the Characters wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
       
        if(getWorld() instanceof GuessWho)
        {
            checkClicked();
            if (selected){
                
                double w = oriWidth*scale;
                double h = oriHeight*scale;
                getImage().scale((int)w,(int)h);
            }
            else 
                getImage().scale(oriWidth,oriHeight);
        }
        
        if(getWorld() instanceof chooseCharacterScreen)
        {
           //getImage().scale(141,192);
            // double sizeW = 141;// = getImage().getWidth();
            // double sizeH = 192; //= getImage().getHeight();
           // getImage().scale((int)sizeW,(int)sizeH);
             // getImage().scale((int)sizeW,(int)sizeH);
            if(Greenfoot.mouseMoved(this) )
            {
              
               sizeW = sizeW*scale;
               sizeH = sizeH*scale;
               if(check){
               getImage().scale((int)sizeW,(int)sizeH);
               //if(check){
                   //getImage().scale((int)wi,(int)he);
                   check = false;
                }   
               //}
               
            }
            else {
                //getImage().scale(sizeW,sizeH);
                sizeW = 141;
                sizeH = 192;
                getImage().scale((int)sizeW,(int)sizeH);
                check = true;
               
            }
           
            if(Greenfoot.mousePressed(this))
            {
                World world = new GuessWho();
                
                buttonEnabled enableButton = new buttonEnabled();
                getWorld().addObject(enableButton,743,774);
                world.addObject(this, 100, 200);
            }
           // checkClicked();
           // if(selected)
           // {
               
               // double wi = sizeW*scale;
               // double he = sizeH*scale;
               // getImage().scale((int)wi,(int)he);
           // }else{
               // getImage().scale(sizeW,sizeH);
           // }
        }       
       
    }

    
    public void resize(int width, int height)
    {
        getImage().scale(width,height);
        oriWidth = getImage().getWidth();
        oriHeight =getImage().getHeight();
    }
    
    private void checkClicked()
    {
        if(Greenfoot.mousePressed(this))
        {
            System.out.println(this.getClass().getName() + " is clicked");
            if(selected) 
                selected = false;
            else
                selected = true;
               
        }     
    }
    
    public boolean isSelected()
    {
        return selected;
    }
    
    public void unSelect()
    {
        selected = false; 
    }
}
