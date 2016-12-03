import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Map;
import java.util.HashMap;
/**
 * Character is a ZoomActor with descriptive properties and a name
 * Character is the main class for this game
 * support method putProperty taking in a pair of String, key and value.Value will be override if new value is given to an existing key.
 * support method getProperties which will return a map of all stored properties
 * support method getPropertyValue which will return just a String value of an existing key
 * also support setName and getName
 * 
 * @author SPAAK 
 * @version 1
 */
public class Character extends ZoomActor
{       
    //a Map of properties contain 2 strings, one for key, one for value
    Map<String,String> properties = new HashMap<String,String>(); 
    //name of Character
    String name = "";

    /**
     * constructor require path to image name. image will be scaledown 50%
     */
    public Character(String filename)
    {
        super(filename,0.5);
    }
    
    //add new property key-value pair if key doesn't exist, otherwise update 
    public void putProperty(String key,String value)
    {
        this.properties.put(key,value);
    }
    
    
    //get all properties
    public Map<String,String> getProperties()
    {
        return properties;
    }
    
    
    //get only property value, property key is needed
    public String getPropertyValue(String propertyKey)
    {
        return properties.get(propertyKey);
    }
    
    
    //assign a name for character
    public void setName(String name)
    {
        this.name = name;
    }
    
    
    //retrive name of character
    public String getName()
    {
        return this. name;
    }
}
