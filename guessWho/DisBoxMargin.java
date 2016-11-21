/**
 * Write a description of class DisBoxMargin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DisBoxMargin extends DisplayBoxDecorator 
{
    double left, right, top, bottom;
    int leftMar,topMar;
    
    
    public DisBoxMargin(DisplayBox disBox)
    {
        super(disBox);
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
}
