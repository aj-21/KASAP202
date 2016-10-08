import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Char5 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Char5 extends Character
{
    String hairColor = hairColorOptions[4];
    String hairLength = hairLengthOptions[2];
    Boolean hasHat = getHasHat(0);
    Boolean hasCoat = getHasCoat(0);
    Boolean hasSpecs = getHasSpectacles(0);   
    Boolean hasEarings = getHasEarings(0); 
    
    public Char5()
    {
        putOption("HairColor","White");
        putOption("HairLength","Short");
        putOption("Hat","No");
    }
    
}
