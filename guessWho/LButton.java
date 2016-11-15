import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LButton extends LabelButton implements Invoker
{
    private DisplayCommand cmd;
    
    public LButton(String label, String fileName)
    {
        super(label,fileName);
    }
    
    public void setCommand(DisplayCommand cmd)
    {
        this.cmd = cmd;
    }
       
    public void invoke()
    {
        if(cmd!=null)
            cmd.execute(this);
    }
    
    @Override
    public void select()
    {
        super.select();
        invoke();
    }
    
    @Override
    public void deselect()
    {
        super.deselect();
        invoke();
    }
}
