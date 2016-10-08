import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Char7 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Char7 extends Character
{
    String hairColor = hairColorOptions[4];
    String hairLength = hairLengthOptions[2];
    Boolean hasHat = getHasHat(1);
    Boolean hasCoat = getHasCoat(1);
    Boolean hasSpecs = getHasSpectacles(0);   
    Boolean hasEarings = getHasEarings(1);
    
    public Char7()
    {
        putOption("HairColor","White");
        putOption("HairLength","Long");
        putOption("Hat","Yes");
    }
}
