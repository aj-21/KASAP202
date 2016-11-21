import greenfoot.*;
/**
 * Write a description of class DisBoxBackground here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DisBoxBackground extends DisplayBoxDecorator 
{
    GreenfootImage background;
    double left, right, top, bottom;
    int leftMar,topMar;

    public DisBoxBackground(DisplayBox disBox,GreenfootImage background)
    {
        super(disBox);
        this.background = background;
        super.scale(background.getWidth(),background.getHeight());
        //System.out.
    }
    
    public void setMargin(double left, double right, double top, double bottom)
    {
        this.left = left; this.right = right;this.top=top;this.bottom=bottom;
        int w = getWidth();
        int h = getHeight();
        this.leftMar = (int) (w * left/100);
        int rightMar = (int) (w * right/100);
        this. topMar = (int) (h * top/100);
        int bottomMar = (int) (h * bottom / 100 );
        
        disBox.scale(w - leftMar - rightMar, h - topMar - bottomMar);
    }
    
    @Override 
    public void display(World world, int x, int y)
    {
        int xx = getX()/2;
        int yy = getY()/2;
        world.addObject(new DummyImage(background),xx,yy);
        
        
        super.display(world,x+leftMar,y+topMar);
    }
    
}
