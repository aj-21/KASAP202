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
        sif.setBackgroundColor(new Color(0,0,0,150));
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
            String value = "  " + c.getPropertyValue(key) + "  ";
            DummyImage img = new DummyImage(sif.createImage(value,25));
            world.addObject(img,x,y-90);
            labels.add(img);
        }
    }
}
