import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WelcomeScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WelcomeScreen extends World
{
    DummyImage di = new DummyImage(new GifImage("Introduction.gif"));
    //GameSession gameSession;
    IntroHandler h1 = new ConcreteHandlerIntro() ; //Introduction
    IntroHandler h2 = new ConcreteHandlerSkip() ; //Skip Introduction
    IntroHandler h3 = new ConcreteHandlerQuit() ; //Quit Game
    boolean isShowIntro = false;

    public WelcomeScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1536, 864, 1);
        prepare();
    }
    
    private void prepare()
    {   
        //addobject();
        h1.setIntroSuccessor(h2);
        h2.setIntroSuccessor(h3);
        
        Greenfoot.start();
    }
    
    public void act()
    {
        int mouseX, mouseY ;
        
        if(Greenfoot.mousePressed(this)) 
        {  
            MouseInfo mouse = Greenfoot.getMouseInfo();  
            mouseX=mouse.getX();  
            mouseY=mouse.getY();  

            if (h1.handleIntroRequest(mouseX, mouseY))
            {
                if(di.getWorld() != null)
                    removeObject(di);
                else
                {
                    addObject(di,761, 545);
                    di.gifResetFrame();
                }
            }
            
        }
    }
}
