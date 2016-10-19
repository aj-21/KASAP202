import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Map;
import java.util.HashMap;
/**
 * Write a description of class Characters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Character extends SelectableActor
{   
    //Character Properties
    String[] hairColorOptions = {"None", "Red", "Gray", "Black", "White", "Brown"};
    String[] hairLengthOptions = {"Bald", "Semi-Bald", "Short", "Long", "Undefined"};
    private boolean hasHat = false;
    private boolean hasEarings = false;
    private boolean hasCoat = false;
    private boolean hasSpectacles = false;
    
    
    public boolean getHasHat(int x){
        if(x == 0)
            hasHat = false;
        if(x == 1)
            hasHat = true;
        
        return hasHat;
    }
    
    public boolean getHasEarings(int x){
        if(x == 0)
            hasEarings = false;
        if(x == 1)
            hasEarings = true;
        
        return hasEarings;
    }
    
    public boolean getHasCoat(int x){
        if(x == 0)
            hasCoat = false;
        if(x == 1)
            hasCoat = true;
        
        return hasCoat;
    }
    
    public boolean getHasSpectacles(int x){
        if(x == 0)
            hasSpectacles = false;
        if(x == 1)
            hasSpectacles = true;
        
        return hasSpectacles;
    }
    
    Map<String,String> options = new HashMap<String,String>();
    
    public Character()
    {
        super(0.4);
    }
    
    public Character(double scale)
    {
        super(0.4*scale);
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
