import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class IntructionContainer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InstructionRepository implements Container  
{
    List<String> ins = new ArrayList<String>();
    
    public InstructionRepository()
    {
        ins.add("Try to eliminate as many characters as you can in one round");
        ins.add("You can win if you correctly guess your friend's character");
        ins.add("Make sure you select a character or a suboption before time ends");
        ins.add("Luck plays an important role, but strategy is as important");
                
    }
    
    public Iterator getIterator()
    {
        return new InstructionIterator();
    }
    
    private class InstructionIterator implements Iterator
    {
        int index=0;
        @Override
        public boolean hasNext()
        {
            if(ins.size() == 0)
                return false;
            return true;
        }
        
        @Override
        public Object next()
        {
            if(this.hasNext())
            {
                Object rt = ins.get(index);
                index ++;
                if(index == ins.size())
                    index = 0;
                    
                return rt;
            }    
            return null;
        }
        
    }
    
}
