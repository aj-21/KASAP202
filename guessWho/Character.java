import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Map;
import java.util.HashMap;
/**
 * Write a description of class Characters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Character extends ObservableZoomActor
{       
    Map<String,String> options = new HashMap<String,String>();  
    
    public Character(String filename)
    {
        super(filename,0.5);
    }
    
    public void putOption(String key,String value)
    {
        this.options.put(key,value);
    }
    
    public Map<String,String> getOptions()
    {
        return options;
    }
    
    public String getSubOpt(String key)
    {
        return options.get(key);
    }
    
}
