import greenfoot.*;
import java.awt.Color;
import java.util.Set;
import java.util.HashSet;
/**
 * Write a description of class ShowProperty here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ShowProperty implements Observer
{
    World world;
    GameSession gameSession;
    StringImageFactory sif;
    
    Set<DummyImage> labels;
    public ShowProperty(World world, GameSession gameSession)
    {
        this.world = world;
        this.gameSession = gameSession;
        sif = new StringImageFactory();
        sif.setTextColor(Color.WHITE);
        sif.setBackgroundColor(new Color(0,0,0,120));
        labels = new HashSet<DummyImage>();
    }
    
    public void update(Observable o, Object arg)
    {
        refresh();
    }
    
    public void refresh()
    {
        world.removeObjects(labels);
        labels.clear();
        
        SimpleContainer ccc = new SimpleContainer(gameSession.getPropertyInfo().getOptButtons());
        LabelButton selectedBut = (LabelButton)ccc.getSelected();
        if(selectedBut == null)
            return;
        
        String key = selectedBut.getLabel();
        
        for(Character c:gameSession.getPlaySet())
        {
            int x = c.getX();
            int y = c.getY();
            String value = c.getPropertyValue(key);
            value = "  "+refine(value)+"  ";
            DummyImage img = new DummyImage(sif.createImage(value,30));
            world.addObject(img,x,y-90);
            labels.add(img);
        }
    }
    
    public String refine(String value)
    {
        value = value.toUpperCase();
        switch(value)
        {
            case "YES": {sif.setTextColor(Color.RED);return "X";}
            case "NO": {sif.setTextColor(Color.GREEN);return "V";}
            
            case "GREY": {sif.setTextColor(Color.LIGHT_GRAY);return value;}
            case "YELLOW": {sif.setTextColor(Color.YELLOW);return value;}
            case "GREEN": {sif.setTextColor(Color.GREEN);return value;}
            case "PURPLE": {sif.setTextColor(new Color(128,0,128));return value;}
            case "BROWN": {sif.setTextColor(new Color(165,42,42));return value;}
            case "RED": {sif.setTextColor(Color.RED);return value;}
            case "BLACK": {sif.setTextColor(Color.BLACK);return value;}
            
            default: {sif.setTextColor(Color.WHITE);return value;}
        }
    }
}
