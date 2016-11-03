import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Map;
import java.util.HashMap;
/**
 * Write a description of class Characters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Character extends ZoomActor
{       
    Map<String,String> options = new HashMap<String,String>();  
    String name;
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
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return this. name;
    }
}
