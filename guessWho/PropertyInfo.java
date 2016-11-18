import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * PropertyInfo is a class to store Characters' Proteries Info
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PropertyInfo  
{
    GameSession gameSession;
    Map<String,Set<String>> properties = new HashMap<String,Set<String>>();
    
    Set<LButton> optionButtons;
    Map<LButton,Set<LButton>> subOptButtons;
    
    public PropertyInfo(GameSession gameSession)
    {
        this.gameSession = gameSession;
    }
    
    /**
     * add new key-value pairs of properties.
     */
    public void putProperties(Map<String,String> properties)
    {
        for(Map.Entry<String,String> entry:properties.entrySet())
            putProperty(entry);
    }
    
    /**
     * add a new key-value pair of property
     */
    public void putProperty(Map.Entry<String,String> property)
    {
        putProperty(property.getKey(),property.getValue());
    }
    
    /**
     * mach a new value to a key. If key does not exist, new key will be created
     */
    public void putProperty(String key, String value)
    {
        Set<String> values = properties.get(key);            
        if (values == null)
        {
            values = new HashSet<String>();
            values.add(value);
            properties.put(key,values);
            return;
        }
        values.add(value);
    }
        
    /**
     * get all property Info
     */
    public Set<String> getKeys()
    {
        if(gameSession.getPlaySet() == null || gameSession.getPlaySet().size() == 0 )
        {
            System.out.println("Play set is not initialized");
            return null;
        }
        
        if(properties.size() == 0)
        {
            for(Character each:gameSession.getPlaySet())
                putProperties(each.getProperties());
        }
        
        return properties.keySet();
    }   

    /**
     * get only value info mapped with an option key. Optionkey is required
     */
    public Set<String> getValues(String key)
    {
        if(properties.size() == 0)
            getKeys();
            
        return properties.get(key);
    }
    
    public Set<LButton> getOptButtons()
    {
        if(optionButtons == null)
        {
            optionButtons = new HashSet<LButton>();
            for(String option : getKeys() )
            {            
                //use LButton for command pattern
                LButton button = new LButton(option,"optionButton.png");
                optionButtons.add(button);
            }
        }
        return optionButtons;
    }
    
    public Set<LButton> getSubOptButtons(LButton optBut)
    {
        if (optBut == null)
            return null;
            
            
        if(subOptButtons == null)
            subOptButtons = new HashMap<LButton,Set<LButton>>();
        
        if(subOptButtons.get(optBut) == null)
        {
            Set<LButton> butSet = new HashSet<LButton>();
            for(String subOpt : getValues(optBut.getLabel()) )
            {
                LButton button = new LButton(subOpt,"subOptionsButton.png");
                butSet.add(button);
            }
            subOptButtons.put(optBut,butSet);
        }
        return subOptButtons.get(optBut);
    }
}
