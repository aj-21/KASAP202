import greenfoot.World;
import java.awt.Color;
import java.util.Set;
import java.util.HashSet;
/**
 * show property values of character in play set when an option button or a suboption button is click
 * 
 * @author SPAAK
 * @version 1
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
        
        labels = new HashSet<DummyImage>();
    }
    
    /**
     * listen to 3 canvas, optBut, suboptBut, and CharCanvas and refesh every click
     */
    public void update(Observable o, Object arg)
    {
        refresh();
    }
    
    /**
     * 1. remove current labels
     * 2. check if there is an option Button is clicked, then get KEY, if no stop
     * 3. check if there is a suboption Button is clicked, then get VALUE
     * 4. Either 4.1 or 4.2
     *      4.1. display character value correspond to KEY if VALUE = "" (no suboption is chosen yet)
     *      4.2. display character value correspond to KEY if it maches VALUE (yes there is suboption)
     * 
     */
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
    
    /**
     * helper function for setting color 
     */
    private String refine(String value)
    {
        value = value.toUpperCase();
        switch(value)
        {
            case "YES":{
                sif.setTextColor(Color.GREEN);
                sif.setBackgroundColor(new Color(0,0,0,200));
                return "V";}
            
            case "NO":{
                sif.setTextColor(Color.RED);
                sif.setBackgroundColor(new Color(255,255,255,200));
                return "X";}
                
            case "GREY": {
                sif.setTextColor(Color.LIGHT_GRAY);
                sif.setBackgroundColor(new Color(0,0,0,200));
                return value;}
                
            case "YELLOW": {
                sif.setTextColor(Color.YELLOW);
                sif.setBackgroundColor(new Color(0,0,0,200));
                return value;}
                
            case "GREEN": {
                sif.setTextColor(Color.GREEN);
                sif.setBackgroundColor(new Color(0,0,0,200));
                return value;}
                
            case "PURPLE": {
                sif.setTextColor(new Color(128,0,128));
                sif.setBackgroundColor(new Color(255,255,255,200));
                return value;}
                
            case "BROWN": {
                sif.setTextColor(new Color(165,42,42));
                sif.setBackgroundColor(new Color(255,255,255,200));
                return value;}
                
            case "RED": {
                sif.setTextColor(Color.RED);
                sif.setBackgroundColor(new Color(255,255,255,200));
                return value;}
                
            case "BLACK": {
                sif.setTextColor(Color.BLACK);
                sif.setBackgroundColor(new Color(255,255,255,200));
                return value;}
            
            default: {
                sif.setTextColor(Color.WHITE);
                return value;}
        }
    }
}
