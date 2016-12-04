import java.util.List;
import java.util.ArrayList;
/**
 * class stores hints as string
 * 
 * @author SPAAK 
 * @version 1
 */
public class InstructionRepository implements Repository
  
{
    List<String> ins = new ArrayList<String>();
    
    public InstructionRepository()
    {
        ins.add("Try to eliminate as many characters as you can in one round");
        ins.add("You can win if you correctly guess your friend's character");
        ins.add("Make sure you select a character or a suboption before time ends");
        ins.add("Luck plays an important role, but strategy is as important");
                
    }
    
    //iterator for iterating
    public Iterator getIterator()
    {
        return new InstructionIterator();
    }
    
    
    /**
     * Iterator returning one hint at a time, when it reaches the end, it goes back to the 1st hint
     */
    private class InstructionIterator implements Iterator
    {
        private int index=0;
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
