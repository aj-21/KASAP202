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
        //remove all labels
        world.removeObjects(labels);
        labels.clear();
        
        SimpleContainer ccc = new SimpleContainer(gameSession.getPropertyInfo().getOptButtons());
        LabelButton selectedBut = (LabelButton)ccc.getSelected();
        if(selectedBut == null)
            return;
        String key = selectedBut.getLabel();
        
        ccc = new SimpleContainer(gameSession.getPropertyInfo().getSubOptButtons(  (LButton)selectedBut));
        selectedBut = (LabelButton)ccc.getSelected();
        String subValue = "";
        if(selectedBut != null)
        {
            
            subValue = selectedBut.getLabel();
        }
            
        
        System.out.println("Sub: " + (selectedBut == null) +  " - " +subValue);
        
        for(Character c:gameSession.getPlaySet())
        {
            String value = c.getPropertyValue(key);
            //only add if no subOpt is selected or character value is equals selected subOpt
            if(value.equals(subValue) || subValue.equals( "") )
            {
                value = "  "+refine(value)+"  ";
                DummyImage img = new DummyImage(sif.createImage(value,30));
                world.addObject(img,c.getX(),c.getY()-90);
                labels.add(img);
            }
        }
    }
    
    public String refine(String value)
    {
        value = value.toUpperCase();
        switch(value)
        {
            case "YES": {sif.setTextColor(Color.GREEN);return "V";}
            case "NO": {sif.setTextColor(Color.RED);return "X";}
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
