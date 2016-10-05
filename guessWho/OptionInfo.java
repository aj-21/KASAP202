import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Write a description of class OptionInfo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OptionInfo  
{
    // instance variables - replace the example below with your own
    Map<String,Set<String>> options = new HashMap<String,Set<String>>();
    
    public void OptionInfo()
    {
    }
    
    public void addSubOption(String option, String subOption)
    {
        Set<String> subOptions = options.get(option);
        if(subOptions != null)
            subOptions.add(subOption);
        else
        {
            subOptions = new HashSet<String>();
            subOptions.add(subOption);
            options.put(option,subOptions);
        }
    }
    
    public Set<String> getSubOptions(String option)
    {
        return options.get(option);
    }
    
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        for(Map.Entry<String,Set<String>> option:options.entrySet())
        {
            String key = option.getKey();
            sb.append(key).append(" has: ");
            sb.append(option.getValue().toString()).append("\n");
        }
        
        return sb.toString();
    }
    
}
