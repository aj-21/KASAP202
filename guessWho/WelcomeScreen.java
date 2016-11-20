import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WelcomeScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WelcomeScreen extends World
{
    GameSession gameSession;
    Handler h1 = new ConcreteHandlerIntro() ; //Introduction
    Handler h2 = new ConcreteHandlerSkip() ; //Skip Introduction
    Handler h3 = new ConcreteHandlerQuit() ; //Quit Game

    public WelcomeScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1536, 864, 1);
        prepare();
    }
    
    private void prepare()
    {   
        //addobject();
        h1.setSuccessor(h2);
        h2.setSuccessor(h3);
    }
    
    public void act()
    {
        int mouseX, mouseY ;
             
        if(Greenfoot.mousePressed(this)) 
        {  
            MouseInfo mouse = Greenfoot.getMouseInfo();  
            mouseX=mouse.getX();  
            mouseY=mouse.getY();  
            System.out.println( "sendingcoordinates. x: "+mouseX+" y: "+ mouseY); 
            h1.handleRequest(mouseX, mouseY);

        } 
    }
}
