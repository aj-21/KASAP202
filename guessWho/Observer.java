/**
 * mimic the built-in Observer of java 
 * requires derived class to have update method which accepts observable and an additional Object
 * 
 * @author SPAAK
 * @version 1
 */
public interface Observer  
{
    public void update(Observable o, Object arg);
}
