import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WelcomeScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WelcomeScreen extends World
{
    GifImage gImg = new GifImage("Introduction.gif");
    DummyImage di = new DummyImage(gImg, false);
    //GameSession gameSession;
    IntroHandler h1 = new ConcreteHandlerIntro() ; //Introduction
    IntroHandler h2 = new ConcreteHandlerSkip() ; //Skip Introduction
    IntroHandler h3 = new ConcreteHandlerQuit() ; //Quit Game
    boolean isShowIntro = false, isFirst = false;

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
        
        removeObject(di);
        di = new DummyImage(gImg, isFirst);
        
        if(isShowIntro){
            if(isFirst){
                isFirst = false;
            }
            addObject(di, 761, 545);
        }
        
        if(Greenfoot.mousePressed(this)) 
        {  
            MouseInfo mouse = Greenfoot.getMouseInfo();  
            mouseX=mouse.getX();  
            mouseY=mouse.getY();  
            //System.out.println( "sendingcoordinates. x: "+mouseX+" y: "+ mouseY); 
            isShowIntro = isFirst = h1.handleIntroRequest(mouseX, mouseY);
            
        }
    }
}
